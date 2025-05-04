package com.example.eventos_vet.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.eventos_vet.model.Participante;
import com.example.eventos_vet.service.ParticipanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participante")
@CrossOrigin(origins = "*")
public class ParticipanteController {

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping
    public CollectionModel<EntityModel<Participante>> getAllParticipantes() {
        List<EntityModel<Participante>> participantes = participanteService.getAllParticipantes().stream()
                .map(participante -> EntityModel.of(participante,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(participante.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(participantes,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Participante> getParticipanteById(@PathVariable Long id) {
        Optional<Participante> participanteOpt = participanteService.getParticipanteById(id);

        if (participanteOpt.isPresent()) {
            Participante participante = participanteOpt.get();
            return EntityModel.of(participante,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
        } else {
            throw new RuntimeException("Participante no encontrado con ID: " + id);
        }
    }

    @PostMapping
    public EntityModel<Participante> creaParticipante(@RequestBody Participante participante) {
        Participante creado = participanteService.createParticipante(participante);
        return EntityModel.of(creado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(creado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
    }

    @PutMapping("/{id}")
    public EntityModel<Participante> updateParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        Participante actualizado = participanteService.updateParticipante(id, participante);
        return EntityModel.of(actualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getParticipanteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllParticipantes()).withRel("all-participantes"));
    }

    @DeleteMapping("/{id}")
    public void deleteParticipante(@PathVariable Long id) {
        participanteService.deleteParticipante(id);
    }
}
