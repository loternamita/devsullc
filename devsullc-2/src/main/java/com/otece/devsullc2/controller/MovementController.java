package com.otece.devsullc2.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otece.devsullc2.dto.MovementDTO;
import com.otece.devsullc2.dto.MovementResponseDTO;
import com.otece.devsullc2.dto.AccountReportDTO;
import com.otece.devsullc2.dto.ErrorResponseDTO;
import com.otece.devsullc2.exception.MovementException;
import com.otece.devsullc2.service.impl.MovementServiceImpl;
import com.otece.devsullc2.service.impl.ReportServiceImpl;

@RestController
@RequestMapping("/movements")
public class MovementController {
	
	@Autowired
	private MovementServiceImpl movementService;
	
	@Autowired
    private ReportServiceImpl reportService;
	
	@GetMapping
    public ResponseEntity<?> getAllMovements() {
		try {
			
			List<MovementDTO> movementDtos;
			movementDtos = movementService.getAllMovements();
			return ResponseEntity.ok(movementDtos);
		} catch (MovementException e) {
			return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovementById(@PathVariable Long id) {
        try {
        	MovementDTO movement = movementService.getMovementById(id);
            return movement != null ? ResponseEntity.ok(movement) : ResponseEntity.notFound().build();
        }catch (MovementException e){
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMovement(@RequestBody MovementDTO movement) {
        try {
        	MovementResponseDTO savedMovement = movementService.createMovement(movement);
            return new ResponseEntity<>(savedMovement, HttpStatus.CREATED);
        } catch (MovementException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovement(@PathVariable Long id, @RequestBody MovementDTO movementDetails) {
        try {
        	MovementDTO updatedMovement = movementService.updateMovement(id, movementDetails);
            return ResponseEntity.ok(updatedMovement);
        } catch (MovementException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovement(@PathVariable Long id) {
        try {
        	movementService.deleteMovement(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Se elimino el account con id: " + id);
            return ResponseEntity.ok().body(response);
        } catch (MovementException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @GetMapping("/reports")
    public ResponseEntity<?> getAccountReport(@RequestParam("fechaInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
                                                                   @RequestParam("fechaFin") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
                                                                   @RequestParam("clienteId") String clienteId) {
        List<AccountReportDTO> report;
		try {
			report = reportService.generateReport(startDate, endDate, clienteId);
			return ResponseEntity.ok(report);
		} catch (MovementException e) {
			return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}