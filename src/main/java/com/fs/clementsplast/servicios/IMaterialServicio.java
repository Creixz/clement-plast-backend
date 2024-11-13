package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Material;

import java.util.List;

public interface IMaterialServicio {

    public List<Material> listarMaterial();

    public Material buscarMaterialPorId(Integer idMaterial);

    public Material buscarMaterialPorNombre(String nombreMaterial);

    public Material guardarMaterial(Material material);

    public void eliminarMaterial(Material material);
}
