package com.otece.devsullc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personas")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;
    private String nombre;
    private String genero;
    private String edad;
    private String direccion;
    private String telefono;
}
