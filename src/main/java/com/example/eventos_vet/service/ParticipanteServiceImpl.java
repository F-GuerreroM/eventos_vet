package com.example.eventos_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventos_vet.model.Participante;
import com.example.eventos_vet.repository.ParticipanteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements ParticipanteService{
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public List<Participante> getAllParticipantes() {
        return participanteRepository.findAll();
    }

    @Override
    public Optional<Participante> getParticipanteById(Long id) {
        return participanteRepository.findById(id);
    }
    
    @Override
    public Participante createParticipante(Participante evento){
        return participanteRepository.save(evento);
    }

    @Override
    public Participante updateParticipante(Long id, Participante participante) {
        Optional<Participante> participanteExistenteOpt = participanteRepository.findById(id);
        if (participanteExistenteOpt.isPresent()) {
            Participante participanteExistente = participanteExistenteOpt.get();

            if (participante.getNombre() != null)
                participanteExistente.setNombre(participante.getNombre());

            if (participante.getNombreMascota() != null)
                participanteExistente.setNombreMascota(participante.getNombreMascota());

            if (participante.getFecha() != null)
                participanteExistente.setFecha(participante.getFecha());

            if (participante.getIdevento() != null)
                participanteExistente.setIdevento(participante.getIdevento());

            return participanteRepository.save(participanteExistente);
        } else {
            return null;
        }
    }

    @Override
    public void deleteParticipante(Long id){
        participanteRepository.deleteById(id);
    }
    
}
