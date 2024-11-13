package com.fs.clementsplast.controlador;


import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Espesor;
import com.fs.clementsplast.servicios.IEspesorServicio;
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
public class EspesorControlador {

    private static final Logger logger = LoggerFactory.getLogger(EspesorControlador.class);

    @Autowired
    private IEspesorServicio espesorServicio;

    // http://localhost:8080/clements-plast/espesores
    @GetMapping("/espesores")
    public List<Espesor> obtenerEspesores(){
        var espesores = espesorServicio.listarEspesor();
        espesores.forEach(espesor -> logger.info(espesor.toString()));
        return espesores;
    }

    // http://localhost:8080/clements-plast/espesores
    @PostMapping("/espesores")
    public Espesor agregarEspesor(@RequestBody Espesor espesor){
        logger.info("Espesor a agregar: "+espesor);
        return espesorServicio.guardarEspesor(espesor);
    }

    @GetMapping("/espesores/{id}")
    public ResponseEntity<Espesor> obtenerEspesorPorId(@PathVariable Integer id){
        var espesor = espesorServicio.buscarEspesorPorId(id);
        logger.info("espesor encontrado: " + espesor);
        if(espesor == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(espesor);
    }

    @PutMapping("/espesores/{id}")
    public ResponseEntity<Espesor> actualizarEspesor(@PathVariable Integer id, @RequestBody Espesor espesorModificado){
        var espesor = espesorServicio.buscarEspesorPorId(id);

        if(espesor == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        espesor.setNombreEspesor(espesorModificado.getNombreEspesor());
        return ResponseEntity.ok(espesorServicio.guardarEspesor(espesor));
    }

    @DeleteMapping("/espesores/{id}")
    public ResponseEntity<Map<String, Object>> eliminarEspesor(@PathVariable Integer id){
        var espesor = espesorServicio.buscarEspesorPorId(id);
        if(espesor == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        espesorServicio.eliminarEspesor(espesor);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
