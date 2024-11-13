package com.fs.clementsplast.controlador;

import com.fs.clementsplast.dto.DetalleVentaDTO;
import com.fs.clementsplast.dto.VentaDTO;
import com.fs.clementsplast.dto.VentaListDTO;
import com.fs.clementsplast.dto.detalles_venta.DetalleVentaDetallesDTO;
import com.fs.clementsplast.dto.detalles_venta.VentaDetallesDTO;
import com.fs.clementsplast.modelo.*;
import com.fs.clementsplast.servicios.ClienteServicio;
import com.fs.clementsplast.servicios.ProductoServicio;
import com.fs.clementsplast.servicios.UsuarioServicio;
import com.fs.clementsplast.servicios.VentaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController //Permite recibir y realizar peticiones del tipo Rest
// http://localhost:8080/clements-plast/  utilizar RequestMapping a nivel de clase hace que en todas las URL de la aplicacion tengamos que agregar primero este path
@RequestMapping("clements-plast")  //Nombre general de la aplicacion (ContextPath)
@CrossOrigin(value = "http://localhost:3000")
public class VentaControlador {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private VentaServicio ventaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/ventas")
    public List<VentaListDTO> listarVentas() {

        var listaVentas = ventaServicio.listarVentas();

        // Lista para almacenar los resultados
        List<VentaListDTO> ventasListDTO = new ArrayList<>();

        // Iterar sobre la lista de ventas
        for (Venta venta : listaVentas) {
            // Reiniciar el subtotal para cada venta
            double subtotal = 0.0;

            // Obtener el id de la venta
            Integer idVenta = venta.getIdVenta();

            // Obtener los detalles de venta por idVenta
            List<DetalleVenta> detallesVenta = ventaServicio.obtenerDetallesVentaPorIdVenta(idVenta);

            // Iterar sobre los detalles de venta
            for (DetalleVenta detalle : detallesVenta) {
                // Obtener el id del producto y la cantidad
                Integer idProducto = detalle.getProducto().getIdProducto();
                int cantidad = detalle.getCantidad();

                // Obtener el precio del producto (puedes obtenerlo de la base de datos)
                double precioUnitario = productoServicio.buscarProductoPorId(idProducto).getPrecioPaquete();

                // Calcular el subtotal por producto
                double subtotalProducto = cantidad * precioUnitario;

                // Sumar al subtotal total de la venta
                subtotal += subtotalProducto;
            }

            // Agregar el resultado a la lista de resultados
            ventasListDTO.add(new VentaListDTO(
                    venta.getIdVenta(),
                    venta.getNroFactura(),
                    venta.getFechaVenta(),
                    venta.getUsuario().getNombre().concat(" "+venta.getUsuario().getApellidos()),
                    venta.getCliente().getNombre().concat(" "+venta.getCliente().getApellidos()),
                    subtotal
            ));
        }

        return ventasListDTO;
    }


    @PostMapping("/ventas")
    public VentaReq realizarVenta(@RequestBody VentaRequest request) {
        VentaReq ventaReq = new VentaReq();
        Venta ventaNueva = new Venta();
        try {
            ventaNueva.setNroFactura(request.getVentaDTO().getNroFactura());
            ventaNueva.setFechaVenta(request.getVentaDTO().getFechaVenta());

            // Asigna las relaciones a otras entidades
            Usuario usuario = usuarioServicio.buscarUsuarioPorId(request.getVentaDTO().getIdUsuario());
            Cliente cliente = clienteServicio.buscarClientePorId(request.getVentaDTO().getIdCliente());
            ventaNueva.setUsuario(usuario);
            ventaNueva.setCliente(cliente);

            List<DetalleVenta> detallesVenta = new ArrayList<>();

            for(DetalleVentaDTO detalleVentaDTO : request.getDetallesVentaDTO()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setCantidad(detalleVentaDTO.getCantidad());

                Producto producto = productoServicio.buscarProductoPorId(detalleVentaDTO.getIdProducto());
                detalleVenta.setProducto(producto);
                detalleVenta.setVenta(ventaNueva);
                detallesVenta.add(detalleVenta);
            }

            Venta nuevaVenta = ventaServicio.realizarVenta(ventaNueva, detallesVenta);

            boolean descuentoExitoso = ventaServicio.descontarStock(detallesVenta);

            if(descuentoExitoso) {
                logger.info("Exito");
                ventaReq.setVenta(ventaNueva);
                ventaReq.setDetallesVenta(detallesVenta);
                return ventaReq;
            }
            else {
                ventaServicio.anularVenta(ventaNueva.getIdVenta());
                logger.info("FracasoAnulado");
                return null;
            }
        } catch (Exception e) {
            logger.info(""+ventaNueva);
            logger.info("ErrorTotal");
            return null;
        }
    }

    @GetMapping("/ventas/{id}")
    public VentaDetallesDTO obtenerVentaPorId(@PathVariable Integer id) {

        var venta = ventaServicio.obtenerVentaPorId(id);
        var detallesVenta = ventaServicio.obtenerDetallesVentaPorIdVenta(venta.getIdVenta());

        var detalleVentaDetalles = detallesVenta.stream()
                .map(detalleVenta -> new DetalleVentaDetallesDTO(
                        detalleVenta.getProducto().getCodigo().concat(" / "+detalleVenta.getProducto().getCategoria().getNombreCategoria())
                                .concat(" "+detalleVenta.getProducto().getMaterial().getNombreMaterial())
                                .concat(" "+detalleVenta.getProducto().getColor().getNombreColor())
                                .concat(" "+detalleVenta.getProducto().getMedida().getNombreMedida())
                                .concat(" x "+detalleVenta.getProducto().getEspesor().getNombreEspesor()),
                        detalleVenta.getProducto().getPrecioPaquete(),
                        detalleVenta.getCantidad(),
                        detalleVenta.getProducto().getPrecioPaquete()*detalleVenta.getCantidad()
                ))
                .toList();

        // Calcular la suma de los subtotales
        double total = detalleVentaDetalles.stream()
                .mapToDouble(DetalleVentaDetallesDTO::getSubtotal)
                .sum();

        return VentaDetallesDTO.builder()
                .nFactura(venta.getNroFactura())
                .cliente(venta.getCliente().getNombre().concat(" "+venta.getCliente().getApellidos()))
                .vendedor(venta.getUsuario().getNombre().concat(" "+venta.getUsuario().getApellidos()))
                .fecha(venta.getFechaVenta())
                .detalleVentaDetallesDTO(detalleVentaDetalles)
                .total(total)
                .build();
    }

    @GetMapping("/ventas/ultima-factura")
    public String obtenerUltimaFactura() {
        return ventaServicio.obtenerUltimaFactura();
    }
}
