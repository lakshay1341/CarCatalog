package in.lakshay.rentACarBackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// handles JWT token generation and validation
@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    // create JWT token from authentication object
    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        return createToken(userPrincipal);
    }

    // create JWT token from user principal
    public String createToken(UserPrincipal userPrincipal) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
        
        // create secret key from token secret
        SecretKey secretKey = Keys.hmacShaKeyFor(appProperties.getAuth().getTokenSecret().getBytes());
        
        // add claims to token
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER"); // default role for now
        
        // build and sign token
        return Jwts.builder()
                .subject(Integer.toString(userPrincipal.getId()))
                .issuedAt(new Date())
                .expiration(expiryDate)
                .claims(claims)
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    // extract user ID from token
    public Integer getUserIdFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(appProperties.getAuth().getTokenSecret().getBytes());
        
        Claims claims = Jwts.parser()
                .verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload();

        return Integer.parseInt(claims.getSubject());
    }

    // validate token
    public boolean validateToken(String authToken) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(appProperties.getAuth().getTokenSecret().getBytes());
            
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty");
        }
        return false;
    }
}
