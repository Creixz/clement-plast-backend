package com.fs.clementsplast.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "ventas")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

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

    @Column(length = 50)
    private LocalDate fecha_nacimiento;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @JsonBackReference
    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.getRol().toUpperCase()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
