package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Rol;
import com.fs.clementsplast.modelo.Usuario;
import com.fs.clementsplast.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
/*
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario userEntity = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

// Suponemos que el método getRoles() devuelve una única entidad de rol, no una colección
        Rol roleEntity = userEntity.getRol();

        Collection<? extends GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + roleEntity.getRol().toUpperCase()));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);

    }


}

 */
