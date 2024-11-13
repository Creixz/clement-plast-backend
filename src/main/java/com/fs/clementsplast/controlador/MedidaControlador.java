package com.fs.clementsplast.controlador;


import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Medida;
import com.fs.clementsplast.servicios.IMedidaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MedidaControlador {

    private static final Logger logger = LoggerFactory.getLogger(MedidaControlador.class);

    @Autowired
    private IMedidaServicio medidaServicio;

    // http://localhost:8080/clements-plast/medidas
    @GetMapping("/medidas")
    public List<Medida> obtenerMedidas(){
        var medidas = medidaServicio.listarMedidas();
        medidas.forEach(medida -> logger.info(medida.toString()));
        return medidas;
    }

    // http://localhost:8080/clements-plast/medidas
    @PostMapping("/medidas")
    public Medida agregarMedida(@RequestBody Medida medida){
        logger.info("Medida a agregar: " + medida);
        return medidaServicio.guardarMedida(medida);
    }

    @GetMapping("/medidas/{id}")
    public ResponseEntity<Medida> obtenerMedidaPorId(@PathVariable Integer id){
        var medida = medidaServicio.buscarMedidaPorId(id);
        logger.info("Medida encontrada: " + medida);
        if(medida == null){
            throw new RecursoNoEncontrado("No se encontró el id: " + id);
        }
        return ResponseEntity.ok(medida);
    }

    @PutMapping("/medidas/{id}")
    public ResponseEntity<Medida> actualizarMedida(@PathVariable Integer id, @RequestBody Medida medidaModificada){
        var medida = medidaServicio.buscarMedidaPorId(id);

        if(medida == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        medida.setNombreMedida(medidaModificada.getNombreMedida());
        return ResponseEntity.ok(medidaServicio.guardarMedida(medida));
    }

    @DeleteMapping("/medidas/{id}")
    public ResponseEntity<Map<String, Object>> eliminarMedida(@PathVariable Integer id){
        var medida = medidaServicio.buscarMedidaPorId(id);
        if(medida == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        medidaServicio.eliminarMedida(medida);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado", id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
