package com.fs.clementsplast.servicios;

import com.fs.clementsplast.dto.auth.AuthResponse;
import com.fs.clementsplast.dto.auth.LoginRequest;
import com.fs.clementsplast.dto.auth.RegisterRequest;
import com.fs.clementsplast.modelo.Rol;
import com.fs.clementsplast.modelo.Usuario;
import com.fs.clementsplast.repositorio.RolRepositorio;
import com.fs.clementsplast.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RolRepositorio rolRepositorio;

    // Esto se hizo despues de registrar usuario
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        // Esto se hizo despues de registrar usuario
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        UserDetails userDetails = usuarioRepositorio.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        Rol rol = rolRepositorio.findByIdRol(request.getIdRol()).orElseThrow();

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .dni(request.getDni())
                .celular(request.getCelular())
                .correo(request.getCorreo())
                .fecha_nacimiento(request.getFecha_nacimiento())
                .rol(rol)
                .build();

        usuarioRepositorio.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
