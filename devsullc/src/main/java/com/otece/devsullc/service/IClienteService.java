package com.otece.devsullc.service;

import java.util.List;

import com.otece.devsullc.dto.ClientResponseDTO;
import com.otece.devsullc.dto.ClientUpdateDTO;
import com.otece.devsullc.dto.ClienteDTO;
import com.otece.devsullc.exception.ClientException;

public interface IClienteService {

	ClientResponseDTO createClient(ClienteDTO clienteDto) throws ClientException;

	ClientResponseDTO updateClient(Long id, ClientUpdateDTO clienteDetails) throws ClientException;

	ClienteDTO getClientById(Long id) throws ClientException;

	List<ClienteDTO> getAllClient() throws ClientException;

	void deleteClient(Long id) throws ClientException;
}
