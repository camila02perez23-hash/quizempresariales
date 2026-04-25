package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Jugador;
import com.equipo.app.Repositorio.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jugadores")
public class JugadorWebControlador {

    @Autowired
    private JugadorRepository jugadorRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugadores/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador) {
        if (jugador.getId() != null && jugador.getId().isEmpty()) {
            jugador.setId(null);
        }
        jugadorRepository.save(jugador);
        return "redirect:/jugadores";
    }
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("jugador", jugadorRepository.findById(id).orElse(null));
        return "jugadores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugadores";
    }
}