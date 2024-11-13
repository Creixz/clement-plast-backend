package com.fs.clementsplast.controlador;

import com.fs.clementsplast.dto.ProductoIdDTO;
import com.fs.clementsplast.dto.ProductoNameDTO;
import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.*;
import com.fs.clementsplast.servicios.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clements-plast")  //Nombre general de la aplicacion (ContextPath)
@CrossOrigin(value = "http://localhost:3000")
public class ProductoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    private final IProductoServicio productoServicio;
    private final ICategoriaServicio categoriaServicio;
    private final IMaterialServicio materialServicio;
    private final IColorServicio colorServicio;
    private final IEspesorServicio espesorServicio;
    private final IMedidaServicio medidaServicio;

    @Autowired
    public ProductoControlador(
            IProductoServicio productoServicio,
            ICategoriaServicio categoriaServicio,
            IMaterialServicio materialServicio,
            IColorServicio colorServicio,
            IEspesorServicio espesorServicio,
            IMedidaServicio medidaServicio
    ) {
        this.productoServicio = productoServicio;
        this.categoriaServicio = categoriaServicio;
        this.materialServicio = materialServicio;
        this.colorServicio = colorServicio;
        this.espesorServicio = espesorServicio;
        this.medidaServicio = medidaServicio;
    }

    // http://localhost:8080/clements-plast/productos
    @GetMapping("/productos")
    public List<ProductoNameDTO> obtenerProductos(){
        var productos = productoServicio.listarProductos();
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos.stream()
                .map(producto -> new ProductoNameDTO(
                        producto.getIdProducto(),
                        producto.getCodigo(),
                        producto.getMillaresPorFardo(),
                        producto.getPrecioMillar(),
                        producto.getPrecioPaquete(),
                        producto.getStock(),
                        producto.getCategoria().getNombreCategoria(),
                        producto.getMaterial().getNombreMaterial(),
                        producto.getMedida().getNombreMedida(),
                        producto.getColor().getNombreColor(),
                        producto.getEspesor().getNombreEspesor()
                ))
                .collect(Collectors.toList());
    }

    // http://localhost:8080/clements-plast/productos
    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody ProductoIdDTO nuevoProductoDTO) {
        // Convierte el DTO en una entidad Producto
        Producto nuevoProducto = new Producto();
        nuevoProducto.setCodigo(nuevoProductoDTO.getCodigo());
        nuevoProducto.setMillaresPorFardo(nuevoProductoDTO.getMillaresPorFardo());
        nuevoProducto.setPrecioMillar(nuevoProductoDTO.getPrecioMillar());
        nuevoProducto.setPrecioPaquete(nuevoProductoDTO.getPrecioPaquete());
        nuevoProducto.setStock(nuevoProductoDTO.getStock());

        // Asigna las relaciones a otras entidades (por ejemplo, categoría, material, medida, color, espesor)
        Categoria categoria = categoriaServicio.buscarCategoriaPorId(nuevoProductoDTO.getIdCategoria());
        Material material = materialServicio.buscarMaterialPorId(nuevoProductoDTO.getIdMaterial());
        Medida medida = medidaServicio.buscarMedidaPorId(nuevoProductoDTO.getIdMedida());
        Color color = colorServicio.buscarColorPorId(nuevoProductoDTO.getIdColor());
        Espesor espesor = espesorServicio.buscarEspesorPorId(nuevoProductoDTO.getIdEspesor());

        nuevoProducto.setCategoria(categoria);
        nuevoProducto.setMaterial(material);
        nuevoProducto.setMedida(medida);
        nuevoProducto.setColor(color);
        nuevoProducto.setEspesor(espesor);

        return productoServicio.guardarProducto(nuevoProducto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoIdDTO> obtenerProductoPorId(@PathVariable Integer id){
        var producto = productoServicio.buscarProductoPorId(id);
        var productoIdDTO = new ProductoIdDTO();
        productoIdDTO.setIdProducto(producto.getIdProducto());
        productoIdDTO.setCodigo(producto.getCodigo());
        productoIdDTO.setMillaresPorFardo(producto.getMillaresPorFardo());
        productoIdDTO.setPrecioMillar(producto.getPrecioMillar());
        productoIdDTO.setPrecioPaquete(producto.getPrecioPaquete());
        productoIdDTO.setStock(producto.getStock());
        productoIdDTO.setIdCategoria(producto.getCategoria().getIdCategoria());
        productoIdDTO.setIdMaterial(producto.getMaterial().getIdMaterial());
        productoIdDTO.setIdMedida(producto.getMedida().getIdMedida());
        productoIdDTO.setIdColor(producto.getColor().getIdColor());
        productoIdDTO.setIdEspesor(producto.getEspesor().getIdEspesor());

        logger.info("usuario encontrado: " + producto);
        return ResponseEntity.ok(productoIdDTO);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoIdDTO productoDTO) {
        // Busca el producto existente por su ID
        var productoExistente = productoServicio.buscarProductoPorId(id);

        if (productoExistente == null) {
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        }

        // Actualiza los campos del producto existente con los valores del DTO
        productoExistente.setCodigo(productoDTO.getCodigo());
        productoExistente.setMillaresPorFardo(productoDTO.getMillaresPorFardo());
        productoExistente.setPrecioMillar(productoDTO.getPrecioMillar());
        productoExistente.setPrecioPaquete(productoDTO.getPrecioPaquete());
        productoExistente.setStock(productoDTO.getStock());

        // Actualiza las relaciones con otras entidades (por ejemplo, categoría, material, medida, color, espesor)
        Categoria categoria = categoriaServicio.buscarCategoriaPorId(productoDTO.getIdCategoria());
        Material material = materialServicio.buscarMaterialPorId(productoDTO.getIdMaterial());
        Medida medida = medidaServicio.buscarMedidaPorId(productoDTO.getIdMedida());
        Color color = colorServicio.buscarColorPorId(productoDTO.getIdColor());
        Espesor espesor = espesorServicio.buscarEspesorPorId(productoDTO.getIdEspesor());

        productoExistente.setCategoria(categoria);
        productoExistente.setMaterial(material);
        productoExistente.setMedida(medida);
        productoExistente.setColor(color);
        productoExistente.setEspesor(espesor);

        // Guarda el producto actualizado
        return ResponseEntity.ok(productoServicio.guardarProducto(productoExistente));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Object>> eliminarProducto(@PathVariable Integer id){
        var producto = productoServicio.buscarProductoPorId(id);
        if(producto == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        productoServicio.eliminarProducto(producto);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }


}
