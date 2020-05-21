package com.shineskoo.pojo;

import java.io.Serializable;


/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 封装后端返回给前端结果的实体类
 */
public class Result implements Serializable {

    private Boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//提示信息
    private Object data;//返回查询结果


    public Result() {
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
