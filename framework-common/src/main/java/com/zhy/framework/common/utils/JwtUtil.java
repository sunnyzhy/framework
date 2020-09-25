package com.zhy.framework.common.utils;

import com.zhy.framework.common.to.UserTo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用JWT生成token，并做登录验证
 */
public class JwtUtil {
    private static final String KEY = "z*h%&y_86";
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_USER_NAME = "username";
    private static final String CLAIM_KEY_TENANT_ID = "tenantId";

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String generateToken(UserTo user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, user.getId());
        claims.put(CLAIM_KEY_USER_NAME, user.getUsername());
        claims.put(CLAIM_KEY_TENANT_ID, user.getTenantId());
        //  生成token
        String token = Jwts.builder().
                setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 7200 * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        return token;
    }

    /**
     * 请求被路由时，检验token
     *
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        if (token == null || token.length() == 0) {
            return false;
        }
        //  解析token
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        }
        //  判断token是否过期
        if (claims.getExpiration().before(new Date())) {
            return false;
        }
        return true;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    public static UserTo getUserFromToken(String token) {
        //  解析token
        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }
        UserTo user = new UserTo();
        user.setUsername(claims.get(CLAIM_KEY_USER_NAME).toString());
        user.setId(Integer.parseInt(claims.get(CLAIM_KEY_ID).toString()));
        user.setTenantId(claims.get(CLAIM_KEY_TENANT_ID).toString());
        return user;
    }

    private static Claims getClaims(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();
            if (claims == null ||
                    !claims.containsKey(CLAIM_KEY_ID) ||
                    !claims.containsKey(CLAIM_KEY_USER_NAME) ||
                    !claims.containsKey(CLAIM_KEY_TENANT_ID)) {
                return null;
            }
            return claims;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 退出登录时，使token失效
     *
     * @param token
     */
    public static void invalidToken(String token) {
        //  解析token
        Claims claims = getClaims(token);
        if (claims == null) {
            return;
        }
    }
}
