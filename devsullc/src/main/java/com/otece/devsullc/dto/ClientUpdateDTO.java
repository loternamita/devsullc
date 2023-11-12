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
public class ClientUpdateDTO {
	private String contraseña;
    private String estado;
    private String nombre;
    private String genero;
    private String edad;
    private String direccion;
    private String telefono;
}
