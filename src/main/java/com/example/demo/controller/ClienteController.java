package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("api/v1/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> crearCliente (@NonNull @RequestBody Cliente cliente)
    {
        Cliente clienteEncontrado = clienteService.findByNombreAndEmailEquals(cliente.getNombre(), cliente.getEmail()).orElse(null);
        if (clienteEncontrado == null)
        {
            Cliente clienteCreado = clienteService.crearCliente(cliente);
            return new ResponseEntity(clienteCreado, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest()
                            .body("El cliente ya existe");
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<?> getCliente(@PathVariable("id") Integer id)
    {
        Optional<Cliente> clienteObtenido = clienteService.getCliente(id);
        if (clienteObtenido.isPresent())
        {
            return new ResponseEntity(clienteObtenido, HttpStatus.OK);
        }
        return ResponseEntity.badRequest()
                .body("El cliente no existe");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity borrarCliente(@PathVariable("id") Integer id)
    {
        if (clienteService.getCliente(id).isPresent())
        {
            clienteService.borrarCliente(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseEntity.badRequest()
                    .body("El cliente no existe");
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> editarcliente(@PathVariable("id") Integer id, @NonNull @RequestBody Cliente cliente)
    {
        if (clienteService.getCliente(id).isPresent())
        {
            return new ResponseEntity(clienteService.editarCliente(cliente), HttpStatus.OK);
        }
        return ResponseEntity.badRequest()
                .body("El cliente no existe");
    }
}
