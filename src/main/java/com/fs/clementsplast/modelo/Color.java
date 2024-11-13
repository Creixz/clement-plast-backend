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
@Table(name = "color_producto")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private Integer idColor;

    @Column(name ="nombre_color", length = 25, nullable = false)
    private String nombreColor;

    @JsonIgnore
    @OneToMany(mappedBy = "color")
    private List<Producto> productos;
}
