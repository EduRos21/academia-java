package com.ejemlo.ejemplo.repository;

import com.ejemlo.ejemplo.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.stereotype.Repository; //Si no funciona descomentar ésta línea

public interface EmpleadoRepository extends MongoRepository<Empleado, String> {

}
