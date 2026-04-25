package com.equipo.app.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipo.app.Entidades.Club;
import com.equipo.app.Repositorio.ClubRepository;

@RestController
@RequestMapping("/api/clubes")
public class ClubRestContolador {
	
	@Autowired
	private ClubRepository clubRepository;
	
	@GetMapping
	public List<Club> listar(){
		return clubRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Club> buscarporId(@PathVariable String id){
		return clubRepository.findById(id);
	}
	
	@PostMapping
	public Club crear(@RequestBody Club club) {
		return clubRepository.save(club);
	}
	
	@PutMapping("/{id}")
    public Club actualizar(@PathVariable String id, @RequestBody Club club) {
        club.setNombre(club.getNombre());
        club.setUbicacion(club.getUbicacion());
        club.setEstadio(club.getEstadio());
        club.setAnioFundacion(club.getAnioFundacion());
        club.setNumeroJugadores(club.getNumeroJugadores());
        return clubRepository.save(club);
    }
	
	@DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        if (clubRepository.existsById(id)) {
            clubRepository.deleteById(id);
            return "Club eliminado correctamente";
        }
        return "Club no encontrado";
    }
}
	

