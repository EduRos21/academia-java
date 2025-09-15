package com.ejemlo.ejemplo.service;

import com.ejemlo.ejemplo.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    Empleado save(Empleado empleado);

    List<Empleado> findAll();

    Optional<Empleado> findById(String id);

    Empleado update(String id, Empleado empleado);

    void deleteById(String id);
}
