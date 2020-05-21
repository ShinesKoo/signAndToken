package com.shineskoo.controller;

import com.shineskoo.pojo.Result;
import com.shineskoo.pojo.StatusCode;
import com.shineskoo.service.VerifierService;
import com.shineskoo.toolkit.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 测试签名Controller(表现层)
 */
@RestController
@CrossOrigin   //解决跨域的注解
@RequestMapping("/test")
public class TestSignController {


    @Autowired
    private VerifierService verifierService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * token是在第一次登录的时候,后端利用JWT生成token,然后保存token在user数据库,也要返回给前端
     */
    public String createToken(String userId) {

        String token = jwtTokenUtil.createJWT(userId);
        System.out.println("生成的token为：" + token);

        return token;
    }

    /**
     * 测试验证sign跟token
     * <p>
     * 测试地址:http://localhost:8080/test/signAndToken?userId=00000062&token=eyJhbGciOiJIUzI1NiJ9.eyJqdGki&sign=D951FD6A651CBFA81FAEAB94252600F0
     */
    @RequestMapping(value = "/signAndToken", method = RequestMethod.GET)
    public Result composition(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        if (userId == null || userId.isEmpty()) {
            return new Result(false, StatusCode.PARAMETERS_ERROR, "userId为空");
        }
        String token = req.getParameter("token");
        if (token == null || token.isEmpty()) {
            return new Result(false, StatusCode.PARAMETERS_ERROR, "token为空");
        }
        String sign = req.getParameter("sign");
        if (sign == null || sign.isEmpty()) {
            return new Result(false, StatusCode.PARAMETERS_ERROR, "sign为空");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("token", token);

        try {
            //验证sign跟token
            Result result = verifierService.Check(map, sign);
            if (result != null) {
                return result;
            }

            /**
             * 这里可以开始写业务代码
             */

            return new Result(true, StatusCode.OK, "验证成功", null);
        } catch (NoSuchAlgorithmException e) {
            return new Result(false, StatusCode.SIGN_ERROR, "签名生成错误：" + e.getMessage());
        } catch (Exception ex) {
            return new Result(false, StatusCode.ERROR, "验证出错：" + ex.getMessage(), null);
        }
    }
}
