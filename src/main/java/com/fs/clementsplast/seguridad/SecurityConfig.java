package com.fs.clementsplast.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(HttpMethod.POST,"/clements-plast/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/clements-plast/usuarios")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/register").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/usuarios/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/usuarios/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/usuarios/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/clientes")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/clientesAgr")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/clientes/{id}")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/clientes/{id}")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/clientes/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/productos")
                            .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/productos").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/productos/{id}").hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/productos/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/productos/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/categorias")
                        .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/categorias").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/categorias/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/categorias/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/categorias/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/materiales")
                        .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/materiales").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/materiales/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/materiales/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/materiales/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/colores")
                        .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/colores").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/colores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/colores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/colores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/espesores")
                        .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/espesores").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/espesores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/espesores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/espesores/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/medidas")
                        .hasAnyAuthority("EMPLEADO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST,"/clements-plast/medidas").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/clements-plast/medidas/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/clements-plast/medidas/{id}").hasAuthority("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/clements-plast/medidas/{id}").hasAuthority("ADMINISTRADOR")
                        .anyRequest().permitAll())
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
