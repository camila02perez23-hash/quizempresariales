package com.equipo.app.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.equipo.app.Entidades.Club;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {

}
