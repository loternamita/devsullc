package com.otece.devsullc2.service;

import java.util.List;

import com.otece.devsullc2.dto.AccountDTO;
import com.otece.devsullc2.dto.AccountResponseDTO;
import com.otece.devsullc2.exception.AccountException;

public interface IAccountService {

	AccountResponseDTO createAccount(AccountDTO clienteDto) throws AccountException;

	AccountDTO updateAccount(Long id, AccountDTO clienteDetails) throws AccountException;

	AccountDTO getAccountById(Long id) throws AccountException;

	List<AccountDTO> getAllAccounts() throws AccountException;

	void deleteAccount(Long id) throws AccountException;
}