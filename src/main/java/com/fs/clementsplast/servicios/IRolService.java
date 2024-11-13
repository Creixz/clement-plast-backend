package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Material;
import com.fs.clementsplast.modelo.Rol;

import java.util.List;

public interface IRolService {

    public List<Rol> listarRol();

    public Rol buscarRolPorId(Integer idRol);

    public Rol guardarRol(Rol rol);

    public void eliminarRol(Rol rol);
}
