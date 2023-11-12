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
public class PersonaDTO {

    private String nombre;
    private String genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
