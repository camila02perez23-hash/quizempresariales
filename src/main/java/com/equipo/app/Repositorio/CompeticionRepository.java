package com.equipo.app.Repositorio;

import com.equipo.app.Entidades.Competicion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepository extends MongoRepository<Competicion, String>{

}
