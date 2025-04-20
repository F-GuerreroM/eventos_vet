package com.example.eventos_vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.eventos_vet.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
  
}