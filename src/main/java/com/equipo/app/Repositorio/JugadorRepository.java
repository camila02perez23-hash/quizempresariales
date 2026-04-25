package com.equipo.app.Repositorio;

import com.equipo.app.Entidades.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends MongoRepository<Jugador, String>{

}
