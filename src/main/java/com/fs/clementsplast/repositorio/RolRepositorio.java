package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByIdRol(Integer idRol);
}
