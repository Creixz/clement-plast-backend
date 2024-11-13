package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Espesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspesorRepositorio extends JpaRepository<Espesor,Integer> {

    Espesor findByNombreEspesor(String nombre);
}
