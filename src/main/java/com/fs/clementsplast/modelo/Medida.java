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
@Table(name = "medida_producto")
public class Medida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medida")
    private Integer idMedida;

    @Column(name ="nombre_medida", length = 20, nullable = false)
    private String nombreMedida;

    @JsonIgnore
    @OneToMany(mappedBy = "medida")
    private List<Producto> productos;

}
