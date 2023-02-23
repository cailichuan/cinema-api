package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public final class JwtTokenUtil {
    //http header中的名字
    public final static String TOKEN_HEADER = "Authorization";

    //一个星期过期
    public final static long REMEMBER_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    //一天过期
    public final static long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    //应用密钥
    private static final String APP_SECRET = "cinema.api";

    //角色权限声明
    private static final String ROLE_CLAIMS = "roles";

    /**
     * 生成token
     *
     * @param userName
     * @param roles
     * @param expiration
     * @return
     */
    public static String createToken(String userName, List<String> roles, long expiration) {
        Map<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roles);
        return Jwts.builder()
                .setSubject(userName)
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }


    /**
     * 获取token body
     *
     * @param token
     * @return
     */
    private static Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(APP_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }


    /**
     * 从token中获取userName
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        return getTokenClaims(token).getSubject();
    }


    /**
     *
     * 从token中获取用户角色
     *
     * @param token
     * @return
     */
    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        Object o = getTokenClaims(token).get(ROLE_CLAIMS);
        if (o instanceof ArrayList<?>) {
            for (Object o1 : (List<?>) o) {
                roles.add((String) o1);
            }
        }
        return roles;

    }


    /**
     *
     * 校验token是否过期
     *
     * @param token
     * @return
     */
    public static  boolean isExpiration(String token){
        return getTokenClaims(token).getExpiration().before(new Date());
    }


}
