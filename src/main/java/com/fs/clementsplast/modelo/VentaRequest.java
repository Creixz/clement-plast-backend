package com.fs.clementsplast.modelo;

import com.fs.clementsplast.dto.DetalleVentaDTO;
import com.fs.clementsplast.dto.VentaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaRequest {
    private VentaDTO ventaDTO;
    private List<DetalleVentaDTO> detallesVentaDTO;
}
