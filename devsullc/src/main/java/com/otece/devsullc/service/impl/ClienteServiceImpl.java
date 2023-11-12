package com.otece.devsullc.service.impl;

import com.otece.devsullc.dto.ClientResponseDTO;
import com.otece.devsullc.dto.ClientUpdateDTO;
import com.otece.devsullc.dto.ClienteDTO;
import com.otece.devsullc.entity.Cliente;
import com.otece.devsullc.exception.ClientException;
import com.otece.devsullc.exception.ClientNotFoundException;
import com.otece.devsullc.repository.ClienteRepository;
import com.otece.devsullc.service.IClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.*;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
    private ClienteRepository clientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ClientResponseDTO createClient(ClienteDTO clienteDto) throws ClientException {
    	try {
    		
    		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
            Cliente savedCliente = clientRepository.save(cliente);
            
            ClientResponseDTO response = new ClientResponseDTO();
            response.setNombre(savedCliente.getNombre());
            response.setDireccion(savedCliente.getDireccion());
            response.setTelefono(savedCliente.getTelefono());
            response.setContraseña(savedCliente.getContraseña());
            response.setEstado(savedCliente.getEstado());
            
            return response;
		} catch (Exception e) {
			throw new ClientException("se presento un error en el serviceImpl de crear: " + e.getMessage());
		}
        
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientUpdateDTO clienteDetails) throws ClientException {
    	try {
    		Cliente currentCliente = clientRepository.findById(String.valueOf(id))
                    .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con id: " + id));

    		currentCliente.setIdentificacion(String.valueOf(id));
            modelMapper.map(clienteDetails, currentCliente);
            clientRepository.save(currentCliente);
            
            ClientResponseDTO response = new ClientResponseDTO();
            response.setNombre(currentCliente.getNombre());
            response.setDireccion(currentCliente.getDireccion());
            response.setTelefono(currentCliente.getTelefono());
            response.setContraseña(currentCliente.getContraseña());
            response.setEstado(currentCliente.getEstado());
            
            return response;
			
		} catch (Exception e) {
			throw new ClientException("se presento un error en el serviceImpl de update: " + e.getMessage());
		}
    }

    @Override
    public ClienteDTO getClientById(Long id) throws ClientException {
    	
    	try {
    		
    		Cliente currentCliente = clientRepository.findById(String.valueOf(id))
                    .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con id: " + id));
    		
            return modelMapper.map(currentCliente, ClienteDTO.class);
		} catch (Exception e) {
			throw new ClientException("se presento un error en el serviceImpl de clietnByID: " + e.getMessage());
		}
        
    }

    @Override
    public List<ClienteDTO> getAllClient() throws ClientException {
    	try {
    		List<Cliente> clients = clientRepository.findAll();
    		
            return clients.stream()
                    .map(client -> modelMapper.map(client, ClienteDTO.class))
                    .collect(Collectors.toList());
		} catch (Exception e) {
			throw new ClientException("se presento un error en el serviceImpl de allClients: " + e.getMessage());
		}
    }

    @Override
    public void deleteClient(Long id) throws ClientException {
    	try {
    		Cliente cliente = clientRepository.findById(String.valueOf(id))
                    .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con id: " + id));

            clientRepository.delete(cliente);
		} catch (Exception e) {
			throw new ClientException("se presento un error en el serviceImpl de delete: " + e.getMessage());
		}
    }
}
