package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Medida;
import com.fs.clementsplast.repositorio.MedidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedidaServicio implements IMedidaServicio{

    @Autowired
    private MedidaRepositorio medidaRepositorio;

    @Override
    public List<Medida> listarMedidas() {
        return medidaRepositorio.findAll();
    }

    @Override
    public Medida buscarMedidaPorId(Integer idMedida) {
        return medidaRepositorio.findById(idMedida).orElse(null);
    }

    @Override
    public Medida buscarMedidaPorNombre(String nombreMedida) {
        return medidaRepositorio.findByNombreMedida(nombreMedida);
    }

    @Override
    public Medida guardarMedida(Medida medida) {
        return medidaRepositorio.save(medida);
    }

    @Override
    public void eliminarMedida(Medida medida) {
        medidaRepositorio.delete(medida);
    }
}
