package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Jugador;
import com.equipo.app.Repositorio.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorRestControlador {

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping
    public List<Jugador> listar() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Jugador> buscarPorId(@PathVariable String id) {
        return jugadorRepository.findById(id);
    }

    @PostMapping
    public Jugador crear(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @PutMapping("/{id}")
    public Jugador actualizar(@PathVariable String id, @RequestBody Jugador jugador) {
        jugador.setNombre(jugador.getNombre());
        jugador.setApellido(jugador.getApellido());
        jugador.setNumero(jugador.getNumero());
        jugador.setPosicion(jugador.getPosicion());
        return jugadorRepository.save(jugador);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        if (jugadorRepository.existsById(id)) {
            jugadorRepository.deleteById(id);
            return "Jugador eliminado correctamente";
        }
        return "Jugador no encontrado";
    }
}