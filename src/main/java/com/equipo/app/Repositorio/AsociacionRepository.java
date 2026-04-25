package com.equipo.app.Repositorio;

import com.equipo.app.Entidades.Asociacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends MongoRepository<Asociacion, String> {
}