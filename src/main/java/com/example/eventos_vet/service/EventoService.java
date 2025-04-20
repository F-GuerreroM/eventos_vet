package com.example.eventos_vet.service;

import com.example.eventos_vet.dto.EventoConParticipantesDTO;
import com.example.eventos_vet.model.Evento;
import java.util.List;
import java.util.Optional;

public interface EventoService {
    List<Evento> getAllEventos();
    Optional<Evento> getEventoById(Long id);
    Evento createEvento(Evento factura);
    Evento updateEvento(Long id,Evento factura);
    void deleteEvento(Long id);
    List<EventoConParticipantesDTO> getEventosConParticipantes();
    EventoConParticipantesDTO getEventoConParticipantesPorId(Long id);
}
