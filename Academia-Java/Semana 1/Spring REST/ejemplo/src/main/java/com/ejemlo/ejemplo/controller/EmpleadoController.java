package com.ejemlo.ejemplo.controller;

import com.ejemlo.ejemplo.model.Empleado;
import com.ejemlo.ejemplo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoGuardado = empleadoService.save(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Empleado>> obtenerEmpleados () {
        try {
            List<Empleado> empleados = empleadoService.findAll();
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable String id) {
        try {
            Optional<Empleado> empleado = empleadoService.findById(id);
            return empleado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String id, @RequestBody Empleado empleado) {
        try {
            Empleado empleadoActualizado = empleadoService.update(id, empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String id) {
        try {
            if (empleadoService.findById(id).isPresent()) {
                empleadoService.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
