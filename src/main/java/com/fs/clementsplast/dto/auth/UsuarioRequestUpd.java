package com.fs.clementsplast.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestUpd {

    private String username;
    private String nombre;
    private String apellidos;
    private String dni;
    private String celular;
    private String correo;
    private LocalDate fecha_nacimiento;
    private Integer idRol;
}
