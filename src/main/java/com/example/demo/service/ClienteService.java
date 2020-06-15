package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ReservaService reservaService;

    public Cliente crearCliente (Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getCliente (Integer id)
    {
        return clienteRepository.findById(id);
    }

    public void borrarCliente(Integer id)
    {
        clienteRepository.deleteById(id);
    }

    public Cliente editarCliente (Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getClienteByNombreContainsAndEmailContains(String nombre, String email)
    {
        return clienteRepository.getClienteByNombreContainsAndEmailContains(nombre, email);
    }
}
