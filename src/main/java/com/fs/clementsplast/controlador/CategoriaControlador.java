package com.fs.clementsplast.controlador;


import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Categoria;
import com.fs.clementsplast.modelo.Cliente;
import com.fs.clementsplast.servicios.ICategoriaServicio;
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
public class CategoriaControlador {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaControlador.class);

    @Autowired
    private ICategoriaServicio categoriaServicio;

    // http://localhost:8080/clements-plast/categorias
    @GetMapping("/categorias")
    public List<Categoria> obtenerCategorias(){
        var categorias = categoriaServicio.listarCategoria();
        categorias.forEach(categoria -> logger.info(categoria.toString()));
        return categorias;
    }

    // http://localhost:8080/clements-plast/categorias
    @PostMapping("/categorias")
    public Categoria agregarCategorias(@RequestBody Categoria categoria){
        logger.info("Categoria a agregar: "+categoria);
        return categoriaServicio.guardarCategoria(categoria);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Integer id){
        var categoria = categoriaServicio.buscarCategoriaPorId(id);
        logger.info("cliente encontrado: " + categoria);
        if(categoria == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaModificado){
        var categoria = categoriaServicio.buscarCategoriaPorId(id);

        if(categoria == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        categoria.setNombreCategoria(categoriaModificado.getNombreCategoria());
        return ResponseEntity.ok(categoriaServicio.guardarCategoria(categoria));
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Map<String, Object>> eliminarCategoria(@PathVariable Integer id){
        var categoria = categoriaServicio.buscarCategoriaPorId(id);
        if(categoria == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        categoriaServicio.eliminarCategoria(categoria);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
