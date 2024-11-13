package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepositorio extends JpaRepository<Color, Integer> {

    Color findByNombreColor(String nombre);
}
