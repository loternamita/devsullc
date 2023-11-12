package com.otece.devsullc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientResponseDTO {
	private String nombre;
	private String direccion;
	private String telefono;
	private String contraseña;
	private String estado;
}
