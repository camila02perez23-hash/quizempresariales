package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Asociacion;
import com.equipo.app.Repositorio.AsociacionRepository;
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
@RequestMapping("/api/asociaciones")
public class AsociacionRestControlador {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping
    public List<Asociacion> listar(){
        return asociacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Asociacion> buscarPorId(@PathVariable String id){
        return asociacionRepository.findById(id);
    }

    @PostMapping
    public Asociacion crear(@RequestBody Asociacion asociacion) {
        return asociacionRepository.save(asociacion);
    }

    @PutMapping("/{id}")
    public Asociacion actualizar(@PathVariable String id, @RequestBody Asociacion asociacion) {
        return asociacionRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(asociacion.getNombre());
                    existing.setPais(asociacion.getPais());
                    existing.setPresidente(asociacion.getPresidente());
                    return asociacionRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada"));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id){
        if (asociacionRepository.existsById(id)) {
            asociacionRepository.deleteById(id);
            return "Asociación eliminada correctamente";
        }
        return "Asociación no encontrada";
    }
}
