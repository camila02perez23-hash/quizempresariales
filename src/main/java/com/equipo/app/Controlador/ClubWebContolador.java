package com.equipo.app.Controlador;

import com.equipo.app.Entidades.Club;

import com.equipo.app.Repositorio.ClubRepository;
import com.equipo.app.Repositorio.AsociacionRepository;
import com.equipo.app.Repositorio.EntrenadorRepository;

import com.equipo.app.Repositorio.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/clubes")
public class ClubWebContolador {

    @Autowired private ClubRepository clubRepository;
    @Autowired private AsociacionRepository asociacionRepository;
    @Autowired private EntrenadorRepository entrenadorRepository;

    @Autowired private CompeticionRepository competicionRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepository.findAll());
        return "clubes/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "clubes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Club club,
            @RequestParam(value = "asociacionId", required = false) String asociacionId,
            @RequestParam(value = "entrenadorId", required = false) String entrenadorId,
            @RequestParam(value = "competicionesIds", required = false) String competicionId,
            RedirectAttributes redirectAttributes) {

        // Limpiar ID vacío para que Mongo genere uno nuevo
        if (club.getId() != null && club.getId().isEmpty()) {
            club.setId(null);
        }

        // Asociación
        if (asociacionId != null && !asociacionId.isEmpty()) {
            club.setAsociacion(asociacionRepository.findById(asociacionId).orElse(null));
        } else {
            club.setAsociacion(null);
        }

        // Entrenador
        if (entrenadorId != null && !entrenadorId.isEmpty()) {
            club.setEntrenador(entrenadorRepository.findById(entrenadorId).orElse(null));
        } else {
            club.setEntrenador(null);
        }

        // Competición (una sola, guardada como lista de un elemento)
        if (competicionId != null && !competicionId.isEmpty()) {
            club.setCompeticiones(competicionRepository.findAllById(List.of(competicionId)));
        } else {
            club.setCompeticiones(null);
        }

        
        clubRepository.save(club);
        redirectAttributes.addFlashAttribute("mensaje", "Club guardado exitosamente");
        return "redirect:/clubes";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable String id, Model model) {
        Club club = clubRepository.findById(id).orElse(null);
        if (club == null) return "redirect:/clubes";
        model.addAttribute("club", club);
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "clubes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        clubRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Club eliminado correctamente");
        return "redirect:/clubes";
    }
}