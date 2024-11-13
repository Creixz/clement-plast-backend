package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Espesor;

import java.util.List;

public interface IEspesorServicio {

    public List<Espesor> listarEspesor();

    public Espesor buscarEspesorPorId(Integer idEspesor);

    public Espesor buscarEspesorPorNombre(String nombreEspesor);

    public Espesor guardarEspesor(Espesor espesor);

    public void eliminarEspesor(Espesor espesor);
}
