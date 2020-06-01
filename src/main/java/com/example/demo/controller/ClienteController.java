package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("api/v1/cliente")
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) { this.clienteService = clienteService; }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente (@NonNull @RequestBody Cliente cliente)
    {
        Cliente clienteCreado = clienteService.crearCliente(cliente);
        return new ResponseEntity(clienteCreado, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<?> getCliente(@PathVariable("id") Integer id)
    {
        Optional<Cliente> clienteObtenido = clienteService.getCliente(id);
        return new ResponseEntity(clienteObtenido, HttpStatus.OK);
    }
}
