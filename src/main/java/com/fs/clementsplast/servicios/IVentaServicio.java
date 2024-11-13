package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.DetalleVenta;
import com.fs.clementsplast.modelo.Venta;

import java.util.List;

public interface IVentaServicio {

    public List<Venta> listarVentas();
    public Venta realizarVenta(Venta venta, List<DetalleVenta> detallesVenta);

    public boolean descontarStock(List<DetalleVenta> detallesVenta);

    public void anularVenta(Integer idVenta);

    public List<DetalleVenta> obtenerDetallesVentaPorIdVenta(Integer idVenta);

    public Venta obtenerVentaPorId(Integer idVenta);

    public String obtenerUltimaFactura();
}
