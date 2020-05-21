package com.shineskoo.service;

import com.shineskoo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 验证Token
 */
@Service
public class TokenManager {

    @Autowired
    private UserMapper userMapper;

    /**
     * 验证Token
     */
    public boolean Check(String userId, String token) {
        //参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("token", token);

        //查询数据库
        List<String> list = userMapper.selectUserIDAndToken(map);

        if (list == null) {
            return false;
        }
        return list.size() > 0 ? true : false;

    }
}