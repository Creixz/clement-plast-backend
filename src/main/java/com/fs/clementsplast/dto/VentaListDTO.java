package com.fs.clementsplast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaListDTO {
    private Integer idVenta;
    private String nroFactura;
    private LocalDateTime fechaVenta;
    private String usuario;
    private String cliente;
    private Double total;
}
