package com.example.eventos_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.eventos_vet.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findByIdevento(Long idevento);
}
