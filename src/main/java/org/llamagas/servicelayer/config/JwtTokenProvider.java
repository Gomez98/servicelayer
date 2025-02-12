package org.llamagas.servicelayer.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.llamagas.servicelayer.model.domain.Permission;
import org.llamagas.servicelayer.model.domain.Role;
import org.llamagas.servicelayer.model.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long expirationTime;
    private final long renewalThreshold;
    private final RedisTemplate<String, String> redisTemplate;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") long expirationTime,
                            @Value("${jwt.renewalThreshold}") long renewalThreshold,
                            RedisTemplate<String, String> redisTemplate) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expirationTime = expirationTime;
        this.renewalThreshold = renewalThreshold;
        this.redisTemplate = redisTemplate;
    }

    public String generateToken(User user) {
        String rolesString = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(","));

        String permissionsString = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", rolesString)
                .claim("permissions", permissionsString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Set<String> getRolesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String rolesString = claims.get("roles", String.class);
        return Set.of(rolesString.split(","));
    }

    public Set<String> getPermissionsFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        String permissionsString = claims.get("permissions", String.class);
        return Set.of(permissionsString.split(","));
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public boolean isTokenRevoked(String token) {
        return redisTemplate.hasKey(token);
    }

    public String validateAndRefreshToken(String token) {
        try {
            if (isTokenRevoked(token)) {
                throw new RuntimeException("El token ha sido revocado");
            }
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            long timeToExpire = expiration.getTime() - System.currentTimeMillis();

            if (timeToExpire < renewalThreshold) {
                String newToken = updateTokenExpiration(claims);

                // Guardar el token en Redis con su tiempo de expiración
                redisTemplate.opsForValue().set(token, "revoked", expirationTime, TimeUnit.MILLISECONDS);

                return newToken;


            }
            return token;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("El token ha expirado", e);
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    private String updateTokenExpiration(Claims claims) {
        return Jwts.builder()
                .setSubject(claims.getSubject())
                .claim("roles", claims.get("roles"))
                .claim("permissions", claims.get("permissions"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
