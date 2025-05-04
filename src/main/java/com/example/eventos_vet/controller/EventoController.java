package com.example.eventos_vet.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.eventos_vet.dto.EventoConParticipantesDTO;
import com.example.eventos_vet.model.Evento;
import com.example.eventos_vet.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // HATEOAS: Listado con links
    @GetMapping
    public CollectionModel<EntityModel<Evento>> getAllEventos() {
        List<EntityModel<Evento>> eventos = eventoService.getAllEventos().stream()
                .map(evento -> EntityModel.of(evento,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(evento.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(eventos,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withSelfRel());
    }

    // HATEOAS: Buscar por ID con links
    @GetMapping("/{id}")
    public EntityModel<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> eventoOpt = eventoService.getEventoById(id);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            return EntityModel.of(evento,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
        } else {
            throw new RuntimeException("Evento no encontrado con ID: " + id);
        }
    }

    @PostMapping
    public EntityModel<Evento> creaEvento(@RequestBody Evento evento) {
        Evento creado = eventoService.createEvento(evento);
        return EntityModel.of(creado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(creado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }

    @PutMapping("/{id}")
    public EntityModel<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento actualizado = eventoService.updateEvento(id, evento);
        return EntityModel.of(actualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEventoById(actualizado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEventos()).withRel("all-eventos"));
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
    }

    
    @GetMapping("/con-participantes")
    public List<EventoConParticipantesDTO> getEventosConParticipantes() {
        return eventoService.getEventosConParticipantes();
    }

    @GetMapping("/{id}/con-participantes")
    public ResponseEntity<EventoConParticipantesDTO> getEventoConParticipantes(@PathVariable Long id) {
        EventoConParticipantesDTO dto = eventoService.getEventoConParticipantesPorId(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
}
