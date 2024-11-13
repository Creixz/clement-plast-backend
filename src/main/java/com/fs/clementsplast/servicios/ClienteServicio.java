package com.fs.clementsplast.servicios;

import com.fs.clementsplast.modelo.Cliente;
import com.fs.clementsplast.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        return clienteRepositorio.findById(idCliente).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
