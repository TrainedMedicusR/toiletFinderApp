package com.example.toiletfinderapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    private static final String CLAIM_KEY_USERNAME = "username";

    private static final String CLAIM_KEY_CREATED = "createTime";

    private static final String CLAIM_KEY_USERID = "userID";

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.expired}")
    private Long expired;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>(5);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_USERID, userDetails.getId());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(key)
                .compact();
    }


    /**
     * get expiration timestamp
     *
     * @param token JWT token
     * @return expiration time
     */
    public long getExpirationTime(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return 0;
        }
        return claims.getExpiration().getTime();
    }

    /**
     * get username from token
     *
     * @param token token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            Date expiration = claims.getExpiration();
            if (new Date().after(expiration)) {
                throw new InsufficientAuthenticationException("session timeout");
            }
            return (String) claims.get(CLAIM_KEY_USERNAME);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get userID from token
     *
     * @param token token
     * @return userID
     */
    public Integer getUserIDFromToken(String token) {
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            Date expiration = claims.getExpiration();
            if (new Date().after(expiration)) {
                throw new InsufficientAuthenticationException("session timeout");
            }
            return (Integer) claims.get(CLAIM_KEY_USERID);
        } catch (Exception e) {
            return null;
        }
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

}
