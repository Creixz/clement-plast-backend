package com.fs.clementsplast.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
ProductoIdDTO {

    private Integer idProducto;
    private String codigo;
    private Double millaresPorFardo;
    private Double precioMillar;
    private Double precioPaquete;
    private Integer stock;
    private Integer idCategoria;
    private Integer idMaterial;
    private Integer idMedida;
    private Integer idColor;
    private Integer idEspesor;

}
