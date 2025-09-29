package com.ejemlo.ejemplo.controller;

import com.ejemlo.ejemplo.model.Empleado;
import com.ejemlo.ejemplo.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpleadoControllerTest {

    @InjectMocks
    private EmpleadoController empleadoController;

    @Mock
    private EmpleadoService empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUpEmpleado() {
        empleado = new Empleado();
        empleado.setId("1");
        empleado.setNombre("Juan");
        empleado.setCorreo("juan@mail.com");
        empleado.setTelefono("1122334455");
        empleado.setSueldo("10000");
        empleado.setFechaRegistro(LocalDateTime.now());
    }

    @Test
    @DisplayName("Registrando un nuevo empleado")
    void crearEmpleadoTest() {
        when(empleadoService.save(any(Empleado.class))).thenReturn(empleado);

        ResponseEntity<Empleado> response = empleadoController.crearEmpleado(empleado);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
    }

    @Test
    @DisplayName("Test para obtener empleados")
    void getEmpleadosTest() {
        when(empleadoService.findAll()).thenReturn(List.of(empleado));

        ResponseEntity<List<Empleado>> response = empleadoController.obtenerEmpleados();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Juan", response.getBody().get(0).getNombre());
    }

    @Test
    @DisplayName("Buscar por un ID existente")
    void findByIdTest() {
        when(empleadoService.findById("1")).thenReturn(Optional.of(empleado));

        ResponseEntity<Empleado> response = empleadoController.buscarPorId("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan", response.getBody().getNombre());
    }

    @Test
    @DisplayName("Buscar por ID inexistente")
    void findByIdNullTest() {
        when(empleadoService.findById("2")).thenReturn(Optional.empty());

        ResponseEntity<Empleado> response = empleadoController.buscarPorId("2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Eliminar un empleado")
    void actualizarEmpleadoExistenteTest() {
        when(empleadoService.update(eq("1"), any(Empleado.class))).thenReturn(empleado);

        ResponseEntity<Empleado> response = empleadoController.actualizarEmpleado("1", empleado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Juan", response.getBody().getNombre());
    }

    @Test
    @DisplayName("Test para eliminar un empleado que no existe")
    void actualizarEmpleadoNoExistenteTest() {
        when(empleadoService.update(eq("2"), any(Empleado.class))).thenThrow(new RuntimeException());

        ResponseEntity<Empleado> response = empleadoController.actualizarEmpleado("2", empleado);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Eliminar un empleado existente test")
    void eliminarEmpleadoExistenteTest() {
        when(empleadoService.findById("1")).thenReturn(Optional.of(empleado));
        doNothing().when(empleadoService).deleteById("1");

        ResponseEntity<Void> response = empleadoController.eliminarEmpleado("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Eliminar un empleado inexistente test")
    void eliminarEmpleadoNoExistenteTest() {
        when(empleadoService.findById("2")).thenReturn(Optional.empty());

        ResponseEntity<Void> response = empleadoController.eliminarEmpleado("2");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}