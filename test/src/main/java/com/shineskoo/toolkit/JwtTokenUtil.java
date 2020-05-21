package com.shineskoo.toolkit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Date:2019/5/20
 * Author: ShinesKoo
 * Desc: JWT生成Token
 */
public class JwtTokenUtil {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.ttl}")
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     * <p>
     * jwt组成：
     * 1）头部（header）
     * SignatureAlgorithm.HS256 = "alg":"HS256"
     * 2）载荷（payload）
     * setId("1001") =  "jti":"1001"
     * setSubject("张三") = "sub":"张三"
     * setIssuedAt(new Date()) = "iat":生成时间
     * 3）签名（signiture）
     * 密钥 就是 "key"=xiumei
     */
    public String createJWT(String openid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(openid)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key);
        if (ttl > 0) {
            //setExpiration: 设置jwt过期时间
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();

    }

    /**
     * 解析JWT
     * <p>
     * setSigningKey: 设置密钥
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
