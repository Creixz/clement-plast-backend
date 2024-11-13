package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Color;

import java.util.List;

public interface IColorServicio {

    public List<Color> listarColor();

    public Color buscarColorPorId(Integer idColor);

    public Color buscarColorPorNombre(String nombreColor);

    public Color guardarColor(Color color);

    public void eliminarColor(Color color);
}
