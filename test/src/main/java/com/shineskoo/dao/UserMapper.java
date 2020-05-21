package com.shineskoo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 数据访问层(DAL)
 */
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String userId);


    String selectByPrimaryKey(String userId);

    /**
     * 查询数据库的userID跟token
     */
    List<String> selectUserIDAndToken(HashMap<String, Object> map);

}