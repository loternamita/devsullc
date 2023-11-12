package com.otece.devsullc2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otece.devsullc2.dto.MovementDTO;
import com.otece.devsullc2.dto.MovementResponseDTO;
import com.otece.devsullc2.entity.Account;
import com.otece.devsullc2.entity.Movement;
import com.otece.devsullc2.exception.MovementException;
import com.otece.devsullc2.repository.AccountRepository;
import com.otece.devsullc2.repository.MovementRepository;
import com.otece.devsullc2.service.IMovementService;

@Service
public class MovementServiceImpl implements IMovementService{
	
	@Autowired
    private MovementRepository movementRepository;
	@Autowired
	private AccountRepository accountRepository;
    private final ModelMapper modelMapper = new ModelMapper();

	@Override
	public MovementResponseDTO createMovement(MovementDTO movementDto) throws MovementException {
		try {
	       Movement movementRecent = modelMapper.map(movementDto, Movement.class);
	       Account currentAccount = accountRepository.findByNumeroCuenta(movementRecent.getCuenta().getNumeroCuenta());
	       
	       // Validate if account exist
	       if (currentAccount == null) {
	          throw new MovementException("Account not found with account number: " + movementDto.getCuenta().getNumeroCuenta());
	       }
	       
	       if (currentAccount.getEstado().equals("false")) {
	    	  throw new MovementException("Account is disabled: " + movementDto.getCuenta().getNumeroCuenta());
	       }
	       
	       // Validación de saldo para movimientos negativos
	       if (movementRecent.getValor() < 0 && (currentAccount.getSaldoInicial() + movementRecent.getValor()) < 0) {
	          throw new MovementException("Saldo no disponible");
	       }
	       
	       // Actualizar el saldo de la cuenta
	       currentAccount.setSaldoInicial(currentAccount.getSaldoInicial() + movementRecent.getValor());
	       accountRepository.save(currentAccount);

	       // Guardar el movimiento
	       movementRecent.setCuenta(currentAccount);
	       movementRecent.setValor(Math.abs(movementDto.getValor()));
	       Movement savedMovement = movementRepository.save(movementRecent);
	       
	       MovementResponseDTO dtoResponse = new MovementResponseDTO();
	       dtoResponse.setNumeroCuenta(savedMovement.getCuenta().getNumeroCuenta());
	       dtoResponse.setTipo(savedMovement.getCuenta().getTipoCuenta());
	       dtoResponse.setSaldoInicial(savedMovement.getSaldo());
	       dtoResponse.setEstado(savedMovement.getCuenta().getEstado());
	       
	       if (savedMovement.getTipoMovimiento().equals("Retiro")) {
	    	   dtoResponse.setMovimiento("Retiro de " + Math.abs(savedMovement.getValor()));
	       }else if (savedMovement.getTipoMovimiento().equals("Deposito")) {
	    	   dtoResponse.setMovimiento("Deposito de " + Math.abs(savedMovement.getValor()));
	       }
	       
	       return dtoResponse;
	    } catch (Exception e) {
		   throw new MovementException("se presento un error en el mevementtImpl de crear: " + e.getMessage());
		}
	}

	@Override
	public MovementDTO updateMovement(Long id, MovementDTO movementDetails) throws MovementException {
		try {
			Movement currentMovement = movementRepository.findById(id)
                    .orElseThrow(() -> new MovementException("Movement not found with id: " + id));

            modelMapper.map(movementDetails, currentMovement);
            movementRepository.save(currentMovement);
            return modelMapper.map(currentMovement, MovementDTO.class);
			
		} catch (Exception e) {
			throw new MovementException("se presento un error en el accountImpl de update: " + e.getMessage());
		}
	}

	@Override
	public MovementDTO getMovementById(Long id) throws MovementException {
	   try {
		  Movement currentMovement = movementRepository.findById(id)
                    .orElseThrow(() -> new MovementException("Movement not found with id: " + id));

          return modelMapper.map(currentMovement, MovementDTO.class);
	   } catch (Exception e) {
	      throw new MovementException("se presento un error en el movementImpl de movementByID: " + e.getMessage());
	   }
	}

	@Override
	public List<MovementDTO> getAllMovements() throws MovementException {
		try {
    		List<Movement> movements = movementRepository.findAll();
    		
            return movements.stream()
                    .map(movement -> modelMapper.map(movement, MovementDTO.class))
                    .collect(Collectors.toList());
		} catch (Exception e) {
			throw new MovementException("se presento un error en el movementImpl de allMovements: " + e.getMessage());
		}
	}

	@Override
	public void deleteMovement(Long id) throws MovementException {
		try {
			Movement movement = movementRepository.findById(id)
                    .orElseThrow(() -> new MovementException("Movement not found with id: " + id));

			movementRepository.delete(movement);
		} catch (Exception e) {
			throw new MovementException("se presento un error en el accountImpl de delete: " + e.getMessage());
		}
	}
}
