package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "espesor_producto")
public class Espesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espesor")
    private Integer idEspesor;

    @Column(name ="nombre_espesor", length = 20, nullable = false)
    private String nombreEspesor;

    @JsonIgnore
    @OneToMany(mappedBy = "espesor")
    private List<Producto> productos;
}
