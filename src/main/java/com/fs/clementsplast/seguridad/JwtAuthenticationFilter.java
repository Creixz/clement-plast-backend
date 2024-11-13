package com.fs.clementsplast.seguridad;

import com.fs.clementsplast.servicios.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

                SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(authority -> {
                    System.out.println("Rol: " + authority.getAuthority());
                });
            }
        }

        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
            response.setStatus(HttpServletResponse.SC_OK);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        // Paso 1: Obtener el encabezado de autorización de la solicitud
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Paso 2: Verificar si el encabezado de autorización es válido y comienza con "Bearer "
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // Paso 3: Extraer el token del encabezado
            return authHeader.substring(7);
        }

        // Paso 4: Si el encabezado de autorización no es válido, retornar null
        return null;
    }
}
