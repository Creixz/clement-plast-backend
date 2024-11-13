package com.fs.clementsplast.dto.detalles_venta;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDetallesDTO {
    private String nFactura;
    private String cliente;
    private String vendedor;
    private LocalDateTime fecha;
    private List<DetalleVentaDetallesDTO> detalleVentaDetallesDTO;
    private Double total;

}
