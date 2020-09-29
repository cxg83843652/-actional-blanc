package cn.actional.phoneix.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 9/26/20 1:52 PM
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     *  密钥，用于签名和解签
     */
    private String secret;
    /**
     *  过期时间
     */
    private Long expiration;
    /**
     *   http请求头的key
     */
    private String header;

    /**
     *  根据用户信息生成 jwt token
     * @param userDetails   用户信息
     * @return  token
     */
    public String generateToken(UserDetails userDetails) {
        /*
            jwt 的Payload 有三个键
            {
                "exp":1600785641,
                "sub":"admin","
                created":1600782041748
            }
         */
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }




    /**
     *  根据 token 获取用户名
     * @param token jwt token
     * @return   用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     *   判断 token 是否有效
     * @param token   token
     * @param userDetails   用户信息
     * @return 失效返回false
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     *  刷新token
     * @param token  旧token
     * @return 新token
     */
    public String refreshToken(String token) {
        String refreshToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshToken = generateToken(claims);
        } catch (Exception e) {
            refreshToken = null;
        }

        return refreshToken;
    }


    /**
     *  获取载荷
     * @param token   jwt token
     * @return   载荷
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}",token);
        }

        return claims;
    }



    /**
     *  判断token是否过期
     * @param token  token
     * @return   过期返回true
     */
    private boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().before(new Date());
    }

    /**
     *  用户主体生成token
     * @param claims   jwt 的Payload 部分
     * @return  token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES512,secret)
                .compact();
    }

    /**
     *  生成过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }






}
