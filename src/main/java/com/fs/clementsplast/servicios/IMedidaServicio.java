package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Espesor;
import com.fs.clementsplast.modelo.Medida;

import java.util.List;

public interface IMedidaServicio {

    public List<Medida> listarMedidas();

    public Medida buscarMedidaPorId(Integer idMedida);

    public Medida buscarMedidaPorNombre(String nombreMedida);

    public Medida guardarMedida(Medida medida);

    public void eliminarMedida(Medida medida);
}
