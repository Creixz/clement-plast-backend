package com.fs.clementsplast.controlador;

import com.fs.clementsplast.dto.auth.RegisterRequest;
import com.fs.clementsplast.dto.auth.UsuarioRequestUpd;
import com.fs.clementsplast.dto.auth.UsuarioResponseUpd;
import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Rol;
import com.fs.clementsplast.modelo.Usuario;
import com.fs.clementsplast.repositorio.RolRepositorio;
import com.fs.clementsplast.servicios.IRolService;
import com.fs.clementsplast.servicios.IUsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController //Permite recibir y realizar peticiones del tipo Rest
// http://localhost:8080/clements-plast/  utilizar RequestMapping a nivel de clase hace que en todas las URL de la aplicacion tengamos que agregar primero este path
@RequestMapping("/clements-plast")  //Nombre general de la aplicacion (ContextPath)
@CrossOrigin(value = "http://localhost:3000")
public class UsuarioControlador {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);


    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Autowired
    private IRolService rolService;

    // http://localhost:8080/clements-plast/usuarios
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        var usuarios = usuarioServicio.listarUsuarios();
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
        return usuarios;
    }

    // http://localhost:8080/clements-plast/usuarios
    @PostMapping("/usuarios")
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario a agregar: "+usuario);
        return usuarioServicio.guardarUsuario(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseUpd> obtenerUsuarioPorId(@PathVariable Integer id){
        var usuario = usuarioServicio.buscarUsuarioPorId(id);
        logger.info("usuario encontrado: " + usuario);
        if(usuario == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        UsuarioResponseUpd usuarioResponseUpd = UsuarioResponseUpd.builder()
                .idUsuario(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .dni(usuario.getDni())
                .celular(usuario.getCelular())
                .correo(usuario.getCorreo())
                .fecha_nacimiento(usuario.getFecha_nacimiento())
                .idRol(usuario.getRol().getIdRol())
                .build();

        return ResponseEntity.ok(usuarioResponseUpd);
    }

    @GetMapping("/usuarios/username/{username}")
    public ResponseEntity<UsuarioResponseUpd> obtenerUsuarioPorUsername(@PathVariable String username){
        var usuario = usuarioServicio.buscarUsuarioPorUsername(username);
        logger.info("usuario encontrado: " + usuario);
        if(usuario == null){
            throw new RecursoNoEncontrado("No se encontró el username: "+ username);
        }
        UsuarioResponseUpd usuarioResponseUpd = UsuarioResponseUpd.builder()
                .idUsuario(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .dni(usuario.getDni())
                .celular(usuario.getCelular())
                .correo(usuario.getCorreo())
                .fecha_nacimiento(usuario.getFecha_nacimiento())
                .idRol(usuario.getRol().getIdRol())
                .build();
        return ResponseEntity.ok(usuarioResponseUpd);

    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestUpd usuarioModificado){
        var usuario = usuarioServicio.buscarUsuarioPorId(id);

        Rol rol = rolService.buscarRolPorId(usuarioModificado.getIdRol());
        if(usuario == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        usuario.setUsername(usuarioModificado.getUsername());
        usuario.setNombre(usuarioModificado.getNombre());
        usuario.setApellidos(usuarioModificado.getApellidos());
        usuario.setDni(usuarioModificado.getDni());
        usuario.setCelular(usuarioModificado.getCelular());
        usuario.setCorreo(usuarioModificado.getCorreo());
        usuario.setFecha_nacimiento(usuarioModificado.getFecha_nacimiento());
        usuario.setRol(rol);
        return ResponseEntity.ok(usuarioServicio.guardarUsuario(usuario));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Integer id){
        var usuario = usuarioServicio.buscarUsuarioPorId(id);
        if(usuario == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        usuarioServicio.eliminarUsuario(usuario);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
