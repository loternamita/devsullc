package com.otece.devsullc2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.otece.devsullc2.dto.AccountDTO;
import com.otece.devsullc2.dto.AccountResponseDTO;
import com.otece.devsullc2.dto.ErrorResponseDTO;
import com.otece.devsullc2.exception.AccountException;
import com.otece.devsullc2.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
    @Autowired
    private AccountServiceImpl accountService;
    
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
		try {
			
			List<AccountDTO> accountDtos;
			accountDtos = accountService.getAllAccounts();
			return ResponseEntity.ok(accountDtos);
		} catch (AccountException e) {
			return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable Long id) {
        try {
        	AccountDTO account = accountService.getAccountById(id);
            return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
        }catch (AccountException e){
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO account) {
        try {
        	AccountResponseDTO savedAccount = accountService.createAccount(account);
            return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
        } catch (AccountException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDetails) {
        try {
        	AccountDTO updatedAccount = accountService.updateAccount(id, accountDetails);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
        	accountService.deleteAccount(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Se elimino el account con id: " + id);
            return ResponseEntity.ok().body(response);
        } catch (AccountException e) {
        	return new ResponseEntity<>(new ErrorResponseDTO("Error " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}