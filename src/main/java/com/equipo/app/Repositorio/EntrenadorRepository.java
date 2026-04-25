package com.equipo.app.Repositorio;

import com.equipo.app.Entidades.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {

}
