package com.fs.clementsplast.controlador;


import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Color;
import com.fs.clementsplast.servicios.IColorServicio;
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
public class ColorControlador {

    private static final Logger logger = LoggerFactory.getLogger(ColorControlador.class);

    @Autowired
    private IColorServicio colorServicio;

    // http://localhost:8080/clements-plast/colores
    @GetMapping("/colores")
    public List<Color> obtenerColores(){
        var colores = colorServicio.listarColor();
        colores.forEach(color -> logger.info(color.toString()));
        return colores;
    }

    // http://localhost:8080/clements-plast/colores
    @PostMapping("/colores")
    public Color agregarColores(@RequestBody Color color){
        logger.info("Color a agregar: "+color);
        return colorServicio.guardarColor(color);
    }

    @GetMapping("/colores/{id}")
    public ResponseEntity<Color> obtenerColorPorId(@PathVariable Integer id){
        var color = colorServicio.buscarColorPorId(id);
        logger.info("color encontrado: " + color);
        if(color == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(color);
    }

    @PutMapping("/colores/{id}")
    public ResponseEntity<Color> actualizarColor(@PathVariable Integer id, @RequestBody Color colorModificado){
        var color = colorServicio.buscarColorPorId(id);

        if(color == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        color.setNombreColor(colorModificado.getNombreColor());
        return ResponseEntity.ok(colorServicio.guardarColor(color));
    }

    @DeleteMapping("/colores/{id}")
    public ResponseEntity<Map<String, Object>> eliminarColor(@PathVariable Integer id){
        var color = colorServicio.buscarColorPorId(id);
        if(color == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        colorServicio.eliminarColor(color);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
