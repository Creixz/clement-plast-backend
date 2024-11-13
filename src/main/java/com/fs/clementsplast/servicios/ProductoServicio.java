package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Producto;
import com.fs.clementsplast.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio{

    @Autowired
    private ProductoRepositorio productoRepositorio;


    @Override
    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        return productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRepositorio.delete(producto);
    }
}
