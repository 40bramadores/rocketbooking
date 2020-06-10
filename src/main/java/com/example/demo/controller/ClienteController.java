package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("api/v1/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @DeleteMapping(path = "{id}")
    public ResponseEntity borrarCliente(@PathVariable("id") Integer id)
    {
        if (getCliente(id) != null)
        {
            clienteService.borrarCliente(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Cliente> editarcliente(@PathVariable("id") Integer id, @NonNull @RequestBody Cliente cliente)
    {
        if (getCliente(id) != null)
        {
            return new ResponseEntity(clienteService.editarCliente(cliente), HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }

}
