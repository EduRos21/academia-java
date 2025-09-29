package com.ejemlo.ejemplo.service;

import com.ejemlo.ejemplo.model.Empleado;
import com.ejemlo.ejemplo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public Empleado save(Empleado empleado) {
        if (empleado.getFechaRegistro() == null) {
            empleado.setFechaRegistro(LocalDateTime.now());
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(String id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado update(String id, Empleado empleado) {
        Optional<Empleado> existeEmpleado = empleadoRepository.findById(id);
        if (existeEmpleado.isPresent()) {
            Empleado nuevoEmpleado = existeEmpleado.get();
            nuevoEmpleado.setNombre(empleado.getNombre());
            nuevoEmpleado.setCorreo(empleado.getCorreo());
            nuevoEmpleado.setTelefono(empleado.getTelefono());
            nuevoEmpleado.setSueldo(empleado.getSueldo());
            return empleadoRepository.save(nuevoEmpleado);
        }
        throw new RuntimeException("No se encontro el empleado con ID: " + id);
    }

    @Override
    public void deleteById(String id) {
        empleadoRepository.deleteById(id);
    }
}
