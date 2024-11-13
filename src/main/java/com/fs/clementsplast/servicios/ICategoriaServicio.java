package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Categoria;

import java.util.List;

public interface ICategoriaServicio {

    public List<Categoria> listarCategoria();

    public Categoria buscarCategoriaPorId(Integer idCategoria);

    public Categoria buscarCategoriaPorNombre(String nombreCategoria);

    public Categoria guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);
}
