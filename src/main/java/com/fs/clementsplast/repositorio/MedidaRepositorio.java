package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.Medida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedidaRepositorio extends JpaRepository<Medida,Integer> {

    Medida findByNombreMedida(String nombre);
}
