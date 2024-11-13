package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepositorio extends JpaRepository<Material, Integer> {

    Material findByNombreMaterial(String nombre);
}
