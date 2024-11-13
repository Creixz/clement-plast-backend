package com.fs.clementsplast.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoNameDTO {

    private Integer idProducto;
    private String codigo;
    private Double millaresPorFardo;
    private Double precioMillar;
    private Double precioPaquete;
    private Integer stock;
    private String nombreCategoria;
    private String nombreMaterial;
    private String nombreMedida;
    private String nombreColor;
    private String nombreEspesor;
}
