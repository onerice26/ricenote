package priv.onerice.ricenote.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author onerice
 * @date 2023/4/16
 * @apiNote
 */
public class JwtTokenUtil {

    private static long expire = 604800;// 七天
    private static String signKey = "3c8bdf976f3c30bd9467cb2d7835a1d2589404905DF3E7BE192966710D5038B5FFE0ADDEB61402E9225B0A28F00A66D8";
    private static String authorization = "Authorization";

    public static String createToken(String userName) {
        return Jwts.builder().setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, signKey).compressWith(CompressionCodecs.GZIP).compact();
    }

    public static String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(signKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(signKey).parseClaimsJws(token).getBody();
    }

    public static String getAuthorization() {
        return authorization;
    }

    public static long getExpire() {
        return expire;
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (!claims.getSubject().equals(username)) {
                return false;
            }
            return isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(signKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
