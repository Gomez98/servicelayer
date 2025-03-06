package org.llamagas.servicelayer.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private static final String[] WHITELISTED_PATHS = {
            "/api/auth/",
            "/v3/api-docs",
            "/swagger-ui/",
            "/swagger-resources/",
            "/webjars/",
            "/favicon.ico",
            // Endpoints de Zipkin
            "/zipkin/",
            "/actuator/zipkin",
            // Endpoints de Prometheus
            "/actuator/prometheus"
    };
    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String requestURI = request.getRequestURI();

            if (Arrays.stream(WHITELISTED_PATHS).anyMatch(requestURI::startsWith)) {
                chain.doFilter(request, response);
                return;
            }

            String token = getTokenFromRequest(request);
            if (token == null) {
                handleAuthenticationError(response, "Token no proporcionado en la solicitud");
                return;
            }

            if (tokenProvider.isTokenRevoked(token)) {
                handleAuthenticationError(response, "Token revocado, inicie sesión nuevamente");
                return;
            }

            String updatedToken = tokenProvider.validateAndRefreshToken(token);
            if (!updatedToken.equals(token)) {
                response.setHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + updatedToken);
            }

            String username = tokenProvider.getUsernameFromToken(updatedToken);
            Set<String> roles = tokenProvider.getRolesFromToken(updatedToken);
            Set<String> permissions = tokenProvider.getPermissionsFromToken(updatedToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            Set<GrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toSet());

            authorities.addAll(permissions.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet()));

            if (authorities.isEmpty()) {
                handleAuthenticationError(response, "Acceso denegado: No tienes permisos.");
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        } catch (Exception e) {
            handleAuthenticationError(response, e.getMessage());
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private void handleAuthenticationError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
