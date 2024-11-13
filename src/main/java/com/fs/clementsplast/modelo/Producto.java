package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(length = 15, nullable = false)
    private String codigo;

    @Column(name = "millares_por_fardo")
    private Double millaresPorFardo;

    @Column(name = "precio_millar")
    private Double precioMillar;

    @Column(name = "precio_paquete")
    private Double precioPaquete;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_medida")
    private Medida medida;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_espesor")
    private Espesor espesor;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<ItemPedido> itemPedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalleVentas;
}
