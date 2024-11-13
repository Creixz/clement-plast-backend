package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.DetalleVenta;
import com.fs.clementsplast.modelo.Producto;
import com.fs.clementsplast.modelo.Venta;
import com.fs.clementsplast.repositorio.DetalleVentaRepositorio;
import com.fs.clementsplast.repositorio.ProductoRepositorio;
import com.fs.clementsplast.repositorio.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VentaServicio implements IVentaServicio{

    @Autowired
    private VentaRepositorio ventaRepositorio;

    @Autowired
    private DetalleVentaRepositorio detalleVentaRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepositorio.findAll();
    }

    @Override
    @Transactional
    public Venta realizarVenta(Venta venta, List<DetalleVenta> detallesVenta) {
        venta = ventaRepositorio.save(venta);

        for(DetalleVenta detalleVenta : detallesVenta) {
            detalleVenta.setVenta(venta);
        }
        detalleVentaRepositorio.saveAll(detallesVenta);

        return venta;
    }

    @Override
    public boolean descontarStock(List<DetalleVenta> detallesVenta) {
        for(DetalleVenta detalleVenta : detallesVenta) {
            Producto producto = productoRepositorio.findById(detalleVenta.getProducto().getIdProducto()).orElse(null);
            if(producto == null){
                return false;
            }

            int cantidadVendida = detalleVenta.getCantidad();
            int stockActual = producto.getStock();

            if(stockActual < cantidadVendida) {
                return false;
            }

            producto.setStock(stockActual - cantidadVendida);
            productoRepositorio.save(producto);
        }
        return true;
    }

    @Override
    public void anularVenta(Integer idVenta) {
        Venta venta = ventaRepositorio.findById(idVenta).orElse(null);
        if(venta != null) {
            for(DetalleVenta detalleVenta : venta.getDetalleVentas()) {
                Producto producto = detalleVenta.getProducto();
                int cantidadVendida = detalleVenta.getCantidad();
                int stockActual = producto.getStock();
                producto.setStock(stockActual + cantidadVendida);
                productoRepositorio.save(producto);
            }
            ventaRepositorio.deleteById(idVenta);
        }
    }

    @Override
    public List<DetalleVenta> obtenerDetallesVentaPorIdVenta(Integer idVenta) {
        return ventaRepositorio.findByIdVenta(idVenta);
    }

    @Override
    public Venta obtenerVentaPorId(Integer idVenta) {
        return ventaRepositorio.findById(idVenta).orElse(null);
    }

    @Override
    public String obtenerUltimaFactura() {
        return ventaRepositorio.findMaxNumeroFactura();
    }
}
