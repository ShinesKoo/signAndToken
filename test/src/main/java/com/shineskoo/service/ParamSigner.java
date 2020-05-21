package com.shineskoo.service;

import com.shineskoo.toolkit.md5.MD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Date: 2020/5/20
 * Author: ShinesKoo
 * Desc: 验证签名(sign)
 */
public class ParamSigner {


    //存放参数的Map集合
    private HashMap<String, Object> pamsMap = new HashMap<>();

    //签名秘钥(自定义)
    public static final String APPKEY = "appKeyshineskoochenguangyao666";

    // 构造器
    public ParamSigner(HashMap<String, Object> pams) {

        if (pams.size() == 0) {
            throw new IllegalArgumentException("参数pams的参数数量为0");
        }

        //赋值给存放参数的Map集合
        pamsMap = pams;
    }


    /**
     * 生成签名(跟前端规则一样)
     */
    public String Sign() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //获取Map集合的键名到一个Set集合(去重)
        Set<String> keys = pamsMap.keySet();
        //迭代器
        Iterator<String> iterator1 = keys.iterator();
        //获取Map集合的键名到List集合(用这个集合排序)
        List<String> list = new ArrayList<>();
        while (iterator1.hasNext()) {
            list.add(iterator1.next());
        }
        //排序
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        //循环拼接参数,比如:将foo=1，bar=2，baz=3 排序为bar=2，baz=3，foo=1，参数名和参数值链接后，得到拼装字符串 bar2baz3foo1
        for (String s : list) {
            sb.append(s);
            if (pamsMap.get(s) != null) {
                sb.append(pamsMap.get(s).toString());
            }

        }
        //最后加上秘钥
        sb.append(APPKEY);
        //拼接字符串
        String combin = sb.toString();

        byte[] bytelist = combin.getBytes("utf-8");
        //把拼接起来的字符串进行MD5加密，再转大写
        return new MD5().Encrypt(bytelist).toUpperCase();
    }

    /**
     * 检查签名
     */
    public boolean CheckSign(String tobeCheck) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String signed = Sign();
        System.out.println("服务端sign:" + signed);
        System.out.println("客户端sign:" + tobeCheck);
        return signed.equals(tobeCheck) ? true : false;
    }


}
