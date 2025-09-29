package com.ejemlo.ejemplo.Model;

import com.ejemlo.ejemplo.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmpleadoTest {
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = new Empleado();
    }

    @Test
    @DisplayName("Test de fecha de registro al inicializar instancia")
    void constructorTest() {
        assertNotNull(empleado.getFechaRegistro(), "fechaRegistro tiene que estar inicializada");
    }

    @Test
    void constructorConParametrosTest() {
        Empleado emp = new Empleado();
        empleado.setId("1");
        empleado.setNombre("Juan");
        empleado.setCorreo("juan@mail.com");
        empleado.setTelefono("1122334455");
        empleado.setSueldo("10000");
        empleado.setFechaRegistro(LocalDateTime.now());

        assertEquals("Juan", emp.getNombre());
        assertEquals("juan@mail.com", emp.getCorreo());
        assertEquals("1122334455", emp.getTelefono());
        assertEquals("10000", emp.getSueldo());
        assertNotNull(emp.getFechaRegistro());
    }

    @Test
    void gettersSettersTest() {
        empleado.setId("123");
        empleado.setNombre("Carlos");
        empleado.setCorreo("carlos@mail.com");
        empleado.setTelefono("111222333");
        empleado.setSueldo("10500");
        LocalDateTime fecha = LocalDateTime.of(2020, 1, 1, 12, 0);
        empleado.setFechaRegistro(fecha);

        assertEquals("123", empleado.getId());
        assertEquals("Carlos", empleado.getNombre());
        assertEquals("carlos@mail.com", empleado.getCorreo());
        assertEquals("111222333", empleado.getTelefono());
        assertEquals("10500", empleado.getSueldo());
        assertEquals(fecha, empleado.getFechaRegistro());
    }
}
