package com.otece.devsullc.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void testClienteSetAndGet() {
    	// Creación de la instancia y asignación de valores
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setGenero("Masculino");
        cliente.setEdad("30");
        cliente.setIdentificacion("12345678");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("5551234");
        cliente.setContraseña("miContraseñaSegura");
        cliente.setEstado("Activo");

        // Verificación de que los getters devuelven los valores correctos
        assertEquals("Juan Perez", cliente.getNombre());
        assertEquals("Masculino", cliente.getGenero());
        assertEquals("30", cliente.getEdad());
        assertEquals("12345678", cliente.getIdentificacion());
        assertEquals("Calle Falsa 123", cliente.getDireccion());
        assertEquals("5551234", cliente.getTelefono());
        assertEquals("miContraseñaSegura", cliente.getContraseña());
        assertEquals("Activo", cliente.getEstado());
    }
}
