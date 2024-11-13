package com.fs.clementsplast.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaReq {
    private Venta venta;
    private List<DetalleVenta> detallesVenta;
}
