package com.fs.clementsplast.controlador;

import com.fs.clementsplast.excepciones.RecursoNoEncontrado;
import com.fs.clementsplast.modelo.Cliente;
import com.fs.clementsplast.servicios.IClienteServicio;
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
public class ClienteControlador {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private IClienteServicio clienteServicio;

    // http://localhost:8080/clements-plast/clientes
    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        var clientes = clienteServicio.listarClientes();
        clientes.forEach(cliente -> logger.info(cliente.toString()));
        return clientes;
    }

    // http://localhost:8080/clements-plast/clientes
    @PostMapping("/clientesAgr")
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: "+cliente);
        return clienteServicio.guardarCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id){
        var cliente = clienteServicio.buscarClientePorId(id);
        logger.info("cliente encontrado: " + cliente);
        if(cliente == null){
            throw new RecursoNoEncontrado("No se encontró el id: "+ id);
        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteModificado){
        var cliente = clienteServicio.buscarClientePorId(id);

        if(cliente == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);

        cliente.setNombre(clienteModificado.getNombre());
        cliente.setApellidos(clienteModificado.getApellidos());
        cliente.setDni(clienteModificado.getDni());
        cliente.setCelular(clienteModificado.getCelular());
        cliente.setCorreo(clienteModificado.getCorreo());
        cliente.setDireccion(clienteModificado.getDireccion());
        return ResponseEntity.ok(clienteServicio.guardarCliente(cliente));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Object>> eliminarCliente(@PathVariable Integer id){
        var cliente = clienteServicio.buscarClientePorId(id);
        if(cliente == null)
            throw new RecursoNoEncontrado("El id recibido no existe: " + id);
        clienteServicio.eliminarCliente(cliente);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        respuesta.put("idEliminado",id);
        respuesta.put("fechaHoraEliminacion", LocalDateTime.now());

        return ResponseEntity.ok(respuesta);
    }
}
