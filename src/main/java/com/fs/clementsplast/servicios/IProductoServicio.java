package com.fs.clementsplast.servicios;



import com.fs.clementsplast.modelo.Producto;

import java.util.List;

public interface IProductoServicio {

    public List<Producto> listarProductos();

    public Producto guardarProducto(Producto producto);

    public Producto buscarProductoPorId(Integer idProducto);

    public void eliminarProducto(Producto producto);


}
