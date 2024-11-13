package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
