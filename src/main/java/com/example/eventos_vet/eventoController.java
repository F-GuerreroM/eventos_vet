package com.example.eventos_vet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class eventoController {

    private List<evento> eventos = new ArrayList<>();

    public eventoController() {
        // Inicializar eventos con participantes
        eventos.add(new evento(1, "Feria de Adopción", "Feria", "2025-04-15", List.of("Juan Pérez", "María González")));
        eventos.add(new evento(2, "Competencia Canina", "Competición", "2025-05-20", List.of("Carlos López", "Ana Fernández")));
        eventos.add(new evento(3, "Expo Mascotas", "Exposición", "2025-06-10", List.of("Luis Ramírez", "Sofía Méndez")));
    }

    // GET para obtener todos los eventos
    @GetMapping
    public List<evento> obtenerEventos() {
        return eventos;
    }

    // GET para obtener un evento por ID
    @GetMapping("/{id}")
    public evento obtenerEventoPorId(@PathVariable int id) {
        for (evento evento : eventos) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }

    // GET para obtener los participantes de un evento
    @GetMapping("/{id}/participantes")
    public List<String> obtenerParticipantes(@PathVariable int id) {
        for (evento evento : eventos) {
            if (evento.getId() == id) {
                return evento.getParticipantes();
            }
        }
        return List.of();
    }
}
