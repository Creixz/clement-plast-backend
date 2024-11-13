package com.fs.clementsplast.repositorio;

import com.fs.clementsplast.modelo.DetalleVenta;
import com.fs.clementsplast.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaRepositorio extends JpaRepository<Venta, Integer> {

    @Query("SELECT dv FROM DetalleVenta dv WHERE dv.venta.id = :idVenta")
    List<DetalleVenta> findByIdVenta(@Param("idVenta") Integer idVenta);

    @Query("SELECT MAX(v.nroFactura) FROM Venta v")
    String findMaxNumeroFactura();


}
