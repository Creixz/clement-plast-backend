package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}
