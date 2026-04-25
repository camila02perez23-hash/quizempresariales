package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Competicion;
import com.equipo.app.Repositorio.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competiciones")
public class CompeticionWebContolador {

    @Autowired
    private CompeticionRepository competicionRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competiciones/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competiciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion) {
        if (competicion.getId() != null && competicion.getId().isEmpty()) {
            competicion.setId(null);
        }
        competicionRepository.save(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("competicion", competicionRepository.findById(id).orElse(null));
        return "competiciones/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        competicionRepository.deleteById(id);
        return "redirect:/competiciones";
    }
}