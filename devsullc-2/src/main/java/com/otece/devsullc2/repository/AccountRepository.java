package com.otece.devsullc2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.otece.devsullc2.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByNumeroCuenta(String numeroCuenta);
	List<Account> findByClienteId(String clienteId);
}
