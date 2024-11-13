package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Color;
import com.fs.clementsplast.repositorio.ColorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServicio implements IColorServicio{

    @Autowired
    private ColorRepositorio colorRepositorio;

    @Override
    public List<Color> listarColor() {
        return colorRepositorio.findAll();
    }

    @Override
    public Color buscarColorPorId(Integer idColor) {
        return colorRepositorio.findById(idColor).orElse(null);
    }

    @Override
    public Color buscarColorPorNombre(String nombreColor) {
        return colorRepositorio.findByNombreColor(nombreColor);
    }

    @Override
    public Color guardarColor(Color color) {
        return colorRepositorio.save(color);
    }

    @Override
    public void eliminarColor(Color color) {
        colorRepositorio.delete(color);
    }
}
