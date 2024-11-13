package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Categoria;
import com.fs.clementsplast.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicio implements ICategoriaServicio{

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepositorio.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Integer idCategoria) {
        return categoriaRepositorio.findById(idCategoria).orElse(null);
    }

    @Override
    public Categoria buscarCategoriaPorNombre(String nombreCategoria) {
        return categoriaRepositorio.findByNombreCategoria(nombreCategoria);
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaRepositorio.delete(categoria);
    }
}
