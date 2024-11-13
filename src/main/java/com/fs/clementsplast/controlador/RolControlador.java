package com.fs.clementsplast.controlador;

import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Material;
import com.fs.clementsplast.modelo.Rol;
import com.fs.clementsplast.servicios.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //Permite recibir y realizar peticiones del tipo Rest
// http://localhost:8080/clements-plast/  utilizar RequestMapping a nivel de clase hace que en todas las URL de la aplicacion tengamos que agregar primero este path
@RequestMapping("clements-plast")  //Nombre general de la aplicacion (ContextPath)
@CrossOrigin(value = "http://localhost:3000")
public class RolControlador {

    @Autowired
    private IRolService rolService;

    // http://localhost:8080/clements-plast/roles
    @GetMapping("/roles")
    public List<Rol> obtenerRoles(){
        return rolService.listarRol();
    }

    // http://localhost:8080/clements-plast/roles
    @PostMapping("/roles")
    public Rol agregarRoles(@RequestBody Rol rol){
        return rolService.guardarRol(rol);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Integer id){
        var rol = rolService.buscarRolPorId(id);
        if(rol == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(rol);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol rolModificado){
        var rol = rolService.buscarRolPorId(id);

        if(rol == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        rol.setRol(rolModificado.getRol());
        return ResponseEntity.ok(rolService.guardarRol(rol));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Map<String, Object>> eliminarRol(@PathVariable Integer id){
        var rol = rolService.buscarRolPorId(id);
        if(rol == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        rolService.eliminarRol(rol);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}


