package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Competicion;
import com.equipo.app.Repositorio.CompeticionRepository;
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
@RequestMapping("/api/competiciones")
public class CompeticionRestControlador {

    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping
    public List<Competicion> listar() {
        return competicionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Competicion> buscarPorId(@PathVariable String id) {
        return competicionRepository.findById(id);
    }

    @PostMapping
    public Competicion crear(@RequestBody Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    @PutMapping("/{id}")
    public Competicion actualizar(@PathVariable String id, @RequestBody Competicion competicion) {
        competicion.setNombre(competicion.getNombre());
        competicion.setMontoPremio(competicion.getMontoPremio());
        competicion.setFechaInicio(competicion.getFechaInicio());
        competicion.setFechaFin(competicion.getFechaFin());
        return competicionRepository.save(competicion);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        if (competicionRepository.existsById(id)) {
            competicionRepository.deleteById(id);
            return "Competicion eliminada correctamente";
        }
        return "Competicion no encontrada";
    }
}
