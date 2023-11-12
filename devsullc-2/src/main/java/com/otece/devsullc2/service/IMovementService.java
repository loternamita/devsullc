package com.otece.devsullc2.service;

import java.util.List;

import com.otece.devsullc2.dto.MovementDTO;
import com.otece.devsullc2.dto.MovementResponseDTO;
import com.otece.devsullc2.exception.MovementException;

public interface IMovementService {
	
	MovementResponseDTO createMovement(MovementDTO clienteDto) throws MovementException;

	MovementDTO updateMovement(Long id, MovementDTO clienteDetails) throws MovementException;

	MovementDTO getMovementById(Long id) throws MovementException;

	List<MovementDTO> getAllMovements() throws MovementException;

	void deleteMovement(Long id) throws MovementException;
}


