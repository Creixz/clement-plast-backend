package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"usuario", "cliente", "detalleVentas"})
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "nro_factura", length = 8)
    private String nroFactura;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalleVentas;

}
