package com.otece.devsullc.repository;

import com.otece.devsullc.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    // Métodos personalizados si se requieren
}
