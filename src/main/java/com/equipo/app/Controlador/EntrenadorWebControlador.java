package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Entrenador;
import com.equipo.app.Repositorio.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorWebControlador {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenadores/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        if (entrenador.getId() != null && entrenador.getId().isEmpty()) {
            entrenador.setId(null);
        }
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores";
    }
    

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("entrenador", entrenadorRepository.findById(id).orElse(null));
        return "entrenadores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores";
    }
}