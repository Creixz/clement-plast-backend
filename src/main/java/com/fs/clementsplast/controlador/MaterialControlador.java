package com.fs.clementsplast.controlador;


import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Material;
import com.fs.clementsplast.servicios.IMaterialServicio;
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
public class MaterialControlador {

    private static final Logger logger = LoggerFactory.getLogger(MaterialControlador.class);

    @Autowired
    private IMaterialServicio materialServicio;

    // http://localhost:8080/clements-plast/materiales
    @GetMapping("/materiales")
    public List<Material> obtenerMateriales(){
        var materiales = materialServicio.listarMaterial();
        materiales.forEach(material -> logger.info(material.toString()));
        return materiales;
    }

    // http://localhost:8080/clements-plast/materiales
    @PostMapping("/materiales")
    public Material agregarMateriales(@RequestBody Material material){
        logger.info("Material a agregar: "+material);
        return materialServicio.guardarMaterial(material);
    }

    @GetMapping("/materiales/{id}")
    public ResponseEntity<Material> obtenerMaterialPorId(@PathVariable Integer id){
        var material = materialServicio.buscarMaterialPorId(id);
        logger.info("material encontrado: " + material);
        if(material == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(material);
    }

    @PutMapping("/materiales/{id}")
    public ResponseEntity<Material> actualizarMaterial(@PathVariable Integer id, @RequestBody Material materialModificado){
        var material = materialServicio.buscarMaterialPorId(id);

        if(material == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        material.setNombreMaterial(materialModificado.getNombreMaterial());
        return ResponseEntity.ok(materialServicio.guardarMaterial(material));
    }

    @DeleteMapping("/materiales/{id}")
    public ResponseEntity<Map<String, Object>> eliminarMaterial(@PathVariable Integer id){
        var material = materialServicio.buscarMaterialPorId(id);
        if(material == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        materialServicio.eliminarMaterial(material);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
