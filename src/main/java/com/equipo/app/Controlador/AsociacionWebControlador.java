package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Asociacion;
import com.equipo.app.Repositorio.AsociacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionWebControlador {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociacion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        if (asociacion.getId() != null && asociacion.getId().isEmpty()) {
            asociacion.setId(null);
        }
        asociacionRepository.save(asociacion);
        return "redirect:/asociaciones";
    }
    

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model) {
        Asociacion asociacion = asociacionRepository.findById(id).orElse(null);
        if (asociacion == null) return "redirect:/asociaciones";
        model.addAttribute("asociacion", asociacion);
        return "asociacion/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociaciones";
    }
}