package com.shineskoo.service;


import com.shineskoo.pojo.Result;
import com.shineskoo.pojo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Date:2019/5/20
 * Author:ShinesKoo
 * Desc:接口调用验证（验证顺序：签名》token）
 */
@Service
public class VerifierService {

    @Autowired
    private TokenManager tokenManager;

    /**
     * 接口验证sign跟token
     */
    public Result Check(HashMap<String, Object> map, String sign) throws Exception {
        String userId = map.get("userId").toString();
        if (userId == null) {
            return new Result(false, StatusCode.PARAMETERS_ERROR, "userId为空");
        }
        String token = map.get("token").toString();
        if (token == null) {
            return new Result(false, StatusCode.PARAMETERS_ERROR, "token为空");
        }
        ParamSigner ps = new ParamSigner(map);
        try {
            //首先验证sign
            if (ps.CheckSign(sign)) {
                //然后验证token
                if (tokenManager.Check(userId, token)) {
                    return null;//验证通过
                } else {//token校验失败
                    System.out.println("验证失败userId：" + userId);
                    System.out.println("验证失败token：" + token);
                    return new Result(false, StatusCode.TOKEN_ERROR, "token错误");
                }
            } else {
                return new Result(false, StatusCode.SIGN_ERROR, "签名错误");
            }
        } catch (NoSuchAlgorithmException e) {
            return new Result(false, StatusCode.SIGN_ERROR, "签名生成错误：" + e.getLocalizedMessage());
        } catch (UnsupportedEncodingException e) {
            return new Result(false, StatusCode.SIGN_ERROR, "签名生成错误：" + e.getLocalizedMessage());
        }
    }
}
