package com.example.eventos_vet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventos_vet.dto.EventoConParticipantesDTO;
import com.example.eventos_vet.model.Evento;
import com.example.eventos_vet.service.EventoService;

import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/evento")
@CrossOrigin(origins = "*")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEventos(){
        return eventoService.getAllEventos();
    }
        
    @GetMapping("/{id}")
    public Optional<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id);
    }    

    @PostMapping
    public Evento creaEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento);
    }
    
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.updateEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id){
        eventoService.deleteEvento(id);
    }
    @GetMapping("/con-participantes")

    public List<EventoConParticipantesDTO> getEventosConParticipantes() {
        return eventoService.getEventosConParticipantes();
    }

    @GetMapping("/{id}/con-participantes")
public ResponseEntity<EventoConParticipantesDTO> getEventoConParticipantes(@PathVariable Long id) {
    EventoConParticipantesDTO dto = eventoService.getEventoConParticipantesPorId(id);
    
    if (dto != null) {
        return ResponseEntity.ok(dto);
    } else {
        return ResponseEntity.notFound().build();
    }
}
}

