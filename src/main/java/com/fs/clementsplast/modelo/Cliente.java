package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "ventas")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String apellidos;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 9)
    private String celular;

    @Column(length = 30)
    private String correo;

    @Column(length = 120)
    private String direccion;
}
