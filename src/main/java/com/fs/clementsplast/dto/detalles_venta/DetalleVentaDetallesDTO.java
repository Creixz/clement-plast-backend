package com.fs.clementsplast.dto.detalles_venta;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDetallesDTO {
    private String producto;
    private Double precio;
    private Integer cantidad;
    private Double subtotal;
}
