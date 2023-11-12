package com.otece.devsullc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Cliente extends Persona {
    private String contraseña;
    private String estado;
}
