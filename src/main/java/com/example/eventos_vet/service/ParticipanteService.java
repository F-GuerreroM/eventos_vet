package com.example.eventos_vet.service;

import com.example.eventos_vet.model.Participante;
import java.util.List;
import java.util.Optional;

public interface ParticipanteService {
    List<Participante> getAllParticipantes();
    Optional<Participante> getParticipanteById(Long id);
    Participante createParticipante(Participante participante);
    Participante updateParticipante(Long id,Participante participante);
    void deleteParticipante(Long id);      
}
