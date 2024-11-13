package com.fs.clementsplast.servicios;



import com.fs.clementsplast.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
