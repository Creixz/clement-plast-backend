package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

    Categoria findByNombreCategoria(String nombre);
}
