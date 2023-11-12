package com.otece.devsullc2.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otece.devsullc2.entity.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByCuentaId(Long cuentaId);
    @Query(value = "SELECT m FROM Movement m WHERE m.cuenta.id = :cuentaId AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movement> findByCuentaIdAndFechaBetween(@Param("cuentaId") Long cuentaId, @Param("fechaInicio") LocalDate startDate, @Param("fechaFin") LocalDate endDate);
}
