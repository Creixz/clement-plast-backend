package com.fs.clementsplast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDTO {
    private Integer idDetalleVenta;
    private Integer cantidad;
    private Integer idProducto;
}
