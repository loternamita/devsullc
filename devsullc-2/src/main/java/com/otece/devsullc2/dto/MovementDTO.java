package com.otece.devsullc2.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.otece.devsullc2.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementDTO {
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Account cuenta;
}