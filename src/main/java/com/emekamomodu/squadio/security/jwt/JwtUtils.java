package com.emekamomodu.squadio.security.jwt;

import com.emekamomodu.squadio.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 11:54 AM
 */
@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${squadio.app.jwtSecret}")
    private String jwtSecret;

    @Value("${squadio.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException signatureException) {
            logger.error("Invalid JWT signature: {}", signatureException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            logger.error("Invalid JWT token: {}", malformedJwtException.getMessage());
        } catch (ExpiredJwtException expiredJwtException) {
            logger.error("JWT token is expired: {}", expiredJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", unsupportedJwtException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error("JWT claims string is empty: {}", illegalArgumentException.getMessage());
        }

        return false;
    }

}
