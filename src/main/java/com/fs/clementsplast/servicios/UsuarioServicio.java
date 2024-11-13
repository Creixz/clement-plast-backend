package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Usuario;
import com.fs.clementsplast.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        // Al usar findById retorna un valor del tipo Optional o sea regresa lo pedido pero si no lo encuentra debemos
        // usar .orElse para que regrese null en caso no lo encuentre.
        return usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario buscarUsuarioPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }
}
