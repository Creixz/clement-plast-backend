package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Rol;
import com.fs.clementsplast.repositorio.RolRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServicio implements IRolService{

    private final RolRepositorio rolRepositorio;

    @Override
    public List<Rol> listarRol() {
        return rolRepositorio.findAll();
    }

    @Override
    public Rol buscarRolPorId(Integer idRol) {
        Optional<Rol> rolOptional = rolRepositorio.findByIdRol(idRol);
        // Verifica si el Optional tiene un valor antes de llamar a orElseThrow
        return rolOptional.orElseThrow(() -> new NoSuchElementException("Mensaje de error"));
    }

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepositorio.save(rol);
    }

    @Override
    public void eliminarRol(Rol rol) {
        rolRepositorio.delete(rol);
    }
}
