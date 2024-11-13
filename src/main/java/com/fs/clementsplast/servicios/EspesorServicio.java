package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Espesor;
import com.fs.clementsplast.repositorio.EspesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspesorServicio implements IEspesorServicio{

    @Autowired
    private EspesorRepositorio espesorRepositorio;

    @Override
    public List<Espesor> listarEspesor() {
        return espesorRepositorio.findAll();
    }

    @Override
    public Espesor buscarEspesorPorId(Integer idEspesor) {
        return espesorRepositorio.findById(idEspesor).orElse(null);
    }

    @Override
    public Espesor buscarEspesorPorNombre(String nombreEspesor) {
        return espesorRepositorio.findByNombreEspesor(nombreEspesor);
    }

    @Override
    public Espesor guardarEspesor(Espesor espesor) {
        return espesorRepositorio.save(espesor);
    }

    @Override
    public void eliminarEspesor(Espesor espesor) {
        espesorRepositorio.delete(espesor);
    }
}
