package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Entrenador;
import com.equipo.app.Repositorio.EntrenadorRepository;
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
@RequestMapping("/api/entrenadores")
public class EntrenadorRestContolador {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Entrenador> buscarPorId(@PathVariable String id) {
        return entrenadorRepository.findById(id);
    }

    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @PutMapping("/{id}")
    public Entrenador actualizar(@PathVariable String id, @RequestBody Entrenador entrenador) {
        entrenador.setNombre(entrenador.getNombre());
        entrenador.setApellido(entrenador.getApellido());
        entrenador.setEdad(entrenador.getEdad());
        entrenador.setNacionalidad(entrenador.getNacionalidad());
        return entrenadorRepository.save(entrenador);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        if (entrenadorRepository.existsById(id)) {
            entrenadorRepository.deleteById(id);
            return "Entrenador eliminado correctamente";
        }
        return "Entrenador no encontrado";
    }
}