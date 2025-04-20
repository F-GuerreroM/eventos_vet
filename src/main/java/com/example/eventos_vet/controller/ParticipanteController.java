package com.example.eventos_vet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventos_vet.model.Participante;
import com.example.eventos_vet.service.ParticipanteService;

import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/participante")
@CrossOrigin(origins = "*")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public List<Participante> getAllEventos(){
        return participanteService.getAllParticipantes();
    }
        
    @GetMapping("/{id}")
    public Optional<Participante> getEventoById(@PathVariable Long id) {
        return participanteService.getParticipanteById(id);
    }    

    @PostMapping
    public Participante creaEvento(@RequestBody Participante participante) {
        return participanteService.createParticipante(participante);
    }
    
    @PutMapping("/{id}")
    public Participante updateEvento(@PathVariable Long id, @RequestBody Participante participante) {
        return participanteService.updateParticipante(id, participante);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id){
        participanteService.deleteParticipante(id);
    }
}