package com.otece.devsullc2.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otece.devsullc2.dto.AccountReportDTO;
import com.otece.devsullc2.dto.ClientDTO;
import com.otece.devsullc2.entity.Account;
import com.otece.devsullc2.entity.Movement;
import com.otece.devsullc2.exception.MovementException;
import com.otece.devsullc2.repository.AccountRepository;
import com.otece.devsullc2.repository.MovementRepository;
import com.otece.devsullc2.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private MovementRepository movementRepository;
	
	@Autowired
    private ClienteStorageServiceImpl clienteStorageServiceImpl;

	@Override
	public List<AccountReportDTO> generateReport(LocalDate startDate, LocalDate endDate, String clientId) throws MovementException {
		
	   try {
	      List<Account> accounts = accountRepository.findByClienteId(clientId);
	      ClientDTO clientOpt = clienteStorageServiceImpl.getClientInfo(clientId);
          List<AccountReportDTO> report = new ArrayList<>();

          for (Account account : accounts) {
            List<Movement> movements = movementRepository.findByCuentaIdAndFechaBetween(account.getId(), startDate, endDate);
            
            if (!movements.isEmpty()) {
               for (Movement movement : movements) {
            	  AccountReportDTO dto = new AccountReportDTO();
                  dto.setCliente(clientOpt.getNombre());
                  dto.setNumeroCuenta(account.getNumeroCuenta());
                  dto.setTipo(account.getTipoCuenta());
                  dto.setEstado(account.getEstado());
                  dto.setSaldoDisponible(account.getSaldoInicial()); 
                  dto.setMovimiento(movement.getValor());
                  dto.setSaldoInicial(movement.getSaldo());
                  dto.setFecha(movement.getFecha());
                  report.add(dto);
			   }
            }
          }
          return report;
	   } catch (Exception e) {
	      throw new MovementException("se presento un error en el report: " + e.getMessage());
	   }
	}

}
