package com.example.eventos_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventos_vet.dto.EventoConParticipantesDTO;
import com.example.eventos_vet.model.Evento;
import com.example.eventos_vet.model.Participante;
import com.example.eventos_vet.repository.EventoRepository;
import com.example.eventos_vet.repository.ParticipanteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService{
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }
    
    @Override
    public Evento createEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    @Override
    public Evento updateEvento(Long id, Evento evento) {
        Optional<Evento> eventoExistenteOpt = eventoRepository.findById(id);
        if (eventoExistenteOpt.isPresent()) {
            Evento eventoExistente = eventoExistenteOpt.get();

            if (evento.getNombre() != null)
                eventoExistente.setNombre(evento.getNombre());

            if (evento.getFecha() != null)
                eventoExistente.setFecha(evento.getFecha());

            if (evento.getTipo() != null)
                eventoExistente.setTipo(evento.getTipo());

            return eventoRepository.save(eventoExistente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }
    
    public List<EventoConParticipantesDTO> getEventosConParticipantes() 
    {
        List<Evento> eventos = eventoRepository.findAll();

        return eventos.stream().map(evento -> {
            List<Participante> participantes = participanteRepository.findByIdevento(evento.getId());
            return new EventoConParticipantesDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getTipo(),
                evento.getFecha(),
                participantes
            );
        }).collect(Collectors.toList());

    
    }

    public EventoConParticipantesDTO getEventoConParticipantesPorId(Long id) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);
    
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            List<Participante> participantes = participanteRepository.findByIdevento(evento.getId());
    
            return new EventoConParticipantesDTO(
                evento.getId(),
                evento.getNombre(),
                evento.getTipo(),
                evento.getFecha(),
                participantes
            );
        } else {
            return null; // o lanzar excepción personalizada si prefieres
        }
    }
    
}
