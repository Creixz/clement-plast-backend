package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuarioPorId(Integer idUsuario);

    public Usuario buscarUsuarioPorUsername(String username);

    public Usuario guardarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
}
