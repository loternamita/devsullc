package com.otece.devsullc2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.otece.devsullc2.dto.ClientDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otece.devsullc2.dto.AccountDTO;
import com.otece.devsullc2.dto.AccountResponseDTO;
import com.otece.devsullc2.entity.Account;
import com.otece.devsullc2.exception.AccountException;
import com.otece.devsullc2.repository.AccountRepository;
import com.otece.devsullc2.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
    private AccountRepository accountRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ClienteStorageServiceImpl clienteStorageServiceImpl;

	@Override
	public AccountResponseDTO createAccount(AccountDTO accountDto) throws AccountException {
	   try {
		  
          Account accountRecent = modelMapper.map(accountDto, Account.class);
          Account findAccount = accountRepository.findByNumeroCuenta(accountDto.getNumeroCuenta());
          ClientDTO clientOpt = clienteStorageServiceImpl.getClientInfo(accountDto.getClienteId());

          if (!clientOpt.getIdentificacion().equals(accountDto.getClienteId())) {
             throw new AccountException(" client is not present in our system");
          }
          
          if (findAccount != null && findAccount.getNumeroCuenta().equals(accountDto.getNumeroCuenta()) && findAccount.getTipoCuenta().equals(accountDto.getTipoCuenta())) {
             throw new AccountException(" the account " + accountDto.getNumeroCuenta() + " is registered whit account number");
          }
          
          String clientId = clientOpt.getIdentificacion();
          Account savedAccount = accountRepository.save(accountRecent);
          AccountResponseDTO accountResponseDto = new AccountResponseDTO();
          accountResponseDto.setNumeroCuenta(savedAccount.getNumeroCuenta());
          accountResponseDto.setTipo(savedAccount.getTipoCuenta());
          accountResponseDto.setSaldoInicial(savedAccount.getSaldoInicial());
          accountResponseDto.setEstado(savedAccount.getEstado());
          accountResponseDto.setCliente(clientId);
          
          return accountResponseDto;
       } catch (Exception e) {
	      throw new AccountException("se presento un error en el accountImpl de crear: " + e.getMessage());
	   }
	}

	@Override
	public AccountDTO updateAccount(Long id, AccountDTO accountDetails) throws AccountException {
		try {
    		Account currentAccount = accountRepository.findById(id)
                    .orElseThrow(() -> new AccountException("Account not found with id: " + id));

            modelMapper.map(accountDetails, currentAccount);
            accountRepository.save(currentAccount);
            return modelMapper.map(currentAccount, AccountDTO.class);
			
		} catch (Exception e) {
			throw new AccountException("se presento un error en el accountImpl de update: " + e.getMessage());
		}
	}

	@Override
	public AccountDTO getAccountById(Long id) throws AccountException {
		try {
    		
    		Account currentAccount = accountRepository.findById(id)
                    .orElseThrow(() -> new AccountException("Account not found with id: " + id));

            return modelMapper.map(currentAccount, AccountDTO.class);
		} catch (Exception e) {
			throw new AccountException("se presento un error en el accountImpl de accountByID: " + e.getMessage());
		}
	}

	@Override
	public List<AccountDTO> getAllAccounts() throws AccountException {
		try {
    		List<Account> accounts = accountRepository.findAll();
    		
            return accounts.stream()
                    .map(account -> modelMapper.map(account, AccountDTO.class))
                    .collect(Collectors.toList());
		} catch (Exception e) {
			throw new AccountException("se presento un error en el accountImpl de allAccounts: " + e.getMessage());
		}	
	}

	@Override
	public void deleteAccount(Long id) throws AccountException {
		try {
    		Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new AccountException("Cliente no encontrado con id: " + id));

    		accountRepository.delete(account);
		} catch (Exception e) {
			throw new AccountException("se presento un error en el accountImpl de delete: " + e.getMessage());
		}
	}
}
