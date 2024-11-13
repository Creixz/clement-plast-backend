package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    public String getToken(UserDetails userDetails) {
        return generateToken(userDetails, generateExtraClaims(userDetails));
    }

    @Value("${jwt.time.expiration}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    private String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {

        Date issueAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTES * 60 * 1000) + issueAt.getTime());

        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(issueAt)
                .expiration(expiration)
                .signWith(getSignatureKey(), Jwts.SIG.HS256)
                .compact();
    }

    // Obtener firma del token
    public SecretKey getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Map<String, Object> generateExtraClaims(UserDetails usuario) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("authorities", usuario.getAuthorities());

        return extraClaims;
    }

}
