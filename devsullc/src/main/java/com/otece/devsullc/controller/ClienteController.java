package com.otece.devsullc.controller;

import com.otece.devsullc.dto.ClientResponseDTO;
import com.otece.devsullc.dto.ClientUpdateDTO;
import com.otece.devsullc.dto.ClienteDTO;
import com.otece.devsullc.dto.ErrorResponse;
import com.otece.devsullc.exception.ClientException;
import com.otece.devsullc.service.impl.ClienteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
    private ClienteServiceImpl clientService;

    @GetMapping
    public ResponseEntity<?> getAllClients() {
		try {
			
			List<ClienteDTO> clientDtos;
			clientDtos = clientService.getAllClient();
			return ResponseEntity.ok(clientDtos);
		} catch (ClientException e) {
			return new ResponseEntity<>(new ErrorResponse("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            ClienteDTO cliente = clientService.getClientById(id);
            return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
        }catch (ClientException e){
        	return new ResponseEntity<>(new ErrorResponse("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClienteDTO cliente) {
        try {
        	ClientResponseDTO savedCliente = clientService.createClient(cliente);
            return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
        } catch (ClientException e) {
        	return new ResponseEntity<>(new ErrorResponse("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientUpdateDTO clienteDetails) {
        try {
        	ClientResponseDTO updatedCliente = clientService.updateClient(id, clienteDetails);
            return ResponseEntity.ok(updatedCliente);
        } catch (ClientException e) {
        	return new ResponseEntity<>(new ErrorResponse("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Se elimino el cliente con id: " + id);
            return ResponseEntity.ok().body(response);
        } catch (ClientException e) {
        	return new ResponseEntity<>(new ErrorResponse("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

