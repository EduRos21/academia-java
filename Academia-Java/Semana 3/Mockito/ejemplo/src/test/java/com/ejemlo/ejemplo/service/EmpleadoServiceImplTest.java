package com.ejemlo.ejemplo.service;

import com.ejemlo.ejemplo.model.Empleado;
import com.ejemlo.ejemplo.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    Empleado empleado;

    @BeforeEach
    void setupEmpleado() {
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
    public void saveEmpleadoTest() {
        when(empleadoService.save(empleado)).thenReturn(empleado);

        Empleado res = empleadoService.save(empleado);
        assertNotNull(res);
        assertEquals("Juan", res.getNombre());
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    @DisplayName("Encontrar un empleado por Id")
    public void findByIdTest() {

        when(empleadoRepository.findById("1")).thenReturn(Optional.of(empleado));

        Optional<Empleado> res = empleadoService.findById("1");

        assertTrue(res.isPresent());
        assertEquals("Juan", res.get().getNombre());
        verify(empleadoRepository, times(1)).findById("1");

        when(empleadoService.findById("10")).thenReturn(Optional.empty());
        Optional<Empleado> result = empleadoRepository.findById("10");
        assertFalse(result.isPresent());
        verify(empleadoRepository, times(1)).findById("10");
    }

    @Test
    @DisplayName("Prueba en el metodo que trae a todos los empleados")
    public void findAllTest() {
        Empleado empleado2 = new Empleado();
        empleado2.setId("2");
        empleado2.setNombre("Xavier");
        empleado2.setCorreo("xavier@mail.com");
        empleado2.setTelefono("1010101010");
        empleado2.setSueldo("10000");
        empleado2.setFechaRegistro(LocalDateTime.now());


        List<Empleado> empleados = Arrays.asList(empleado, empleado2);
        when(empleadoRepository.findAll()).thenReturn(empleados);
        List<Empleado> res = empleadoService.findAll();

        assertNotNull(res);
        assertEquals(2, res.size());
        assertEquals("Juan", res.get(0).getNombre());
        assertEquals("10000", res.get(1).getSueldo());
        verify(empleadoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Pruebas en el metodo para actualizar empleado")
    public void updateTest() {
        Empleado empleadoUpdate = new Empleado();
        empleadoUpdate.setNombre("Juan");
        empleadoUpdate.setTelefono("1111111111");
        empleadoUpdate.setCorreo("juan@mail.com");
        empleadoUpdate.setId("1");
        empleadoUpdate.setSueldo("10000");
        empleadoUpdate.setFechaRegistro(LocalDateTime.now());

        when(empleadoRepository.findById("1")).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoUpdate);

        Empleado empleadoUpdated = empleadoService.update("1", empleadoUpdate);

        assertNotNull(empleadoUpdated);
        assertEquals("juan@mail.com", empleadoUpdated.getCorreo());
        assertEquals("1111111111", empleadoUpdated.getTelefono());
        verify(empleadoRepository, times(1)).findById("1");
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }
    }

