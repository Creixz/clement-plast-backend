package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Material;
import com.fs.clementsplast.repositorio.MaterialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServicio implements IMaterialServicio{

    @Autowired
    private MaterialRepositorio materialRepositorio;

    @Override
    public List<Material> listarMaterial() {
        return materialRepositorio.findAll();
    }

    @Override
    public Material buscarMaterialPorId(Integer idMaterial) {
        return materialRepositorio.findById(idMaterial).orElse(null);
    }

    @Override
    public Material buscarMaterialPorNombre(String nombreMaterial) {
        return materialRepositorio.findByNombreMaterial(nombreMaterial);
    }

    @Override
    public Material guardarMaterial(Material material) {
        return materialRepositorio.save(material);
    }

    @Override
    public void eliminarMaterial(Material material) {
        materialRepositorio.delete(material);
    }
}
