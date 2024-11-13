package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private Integer idDetalleVenta;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
}
