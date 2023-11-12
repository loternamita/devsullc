package com.otece.devsullc2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponseDTO {
	
	private String numeroCuenta;
	private String tipo;
	private Double saldoInicial;
	private String estado;
	private String movimiento;

}
