package com.otece.devsullc2.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountReportDTO {
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;
	private String cliente;
	private String numeroCuenta;
	private String tipo;
	private Double saldoInicial;
	private String estado;
	private Double movimiento;
    private Double saldoDisponible;
}
