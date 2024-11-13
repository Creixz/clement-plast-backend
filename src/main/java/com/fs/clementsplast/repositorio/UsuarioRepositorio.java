package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
}
