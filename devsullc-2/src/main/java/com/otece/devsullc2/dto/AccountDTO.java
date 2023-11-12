package com.otece.devsullc2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;
    private String clienteId;
}