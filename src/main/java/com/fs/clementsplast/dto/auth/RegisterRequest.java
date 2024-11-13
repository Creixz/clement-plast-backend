package com.fs.clementsplast.dto.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String dni;
    private String celular;
    private String correo;
    private LocalDate fecha_nacimiento;
    private Integer idRol;
}
