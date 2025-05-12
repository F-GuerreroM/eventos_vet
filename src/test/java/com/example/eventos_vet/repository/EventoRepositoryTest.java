package com.example.eventos_vet.repository;

import com.example.eventos_vet.model.Evento;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void guardarEventoTest() {
        Evento evento = new Evento();
        evento.setNombre("Vacunación gratuita");
        evento.setTipo("Vacunación");
        evento.setFecha("2025-06-15");

        Evento resultado = eventoRepository.save(evento);

        assertNotNull(resultado.getId());
        assertEquals("Vacunación gratuita", resultado.getNombre());
        assertEquals("Vacunación", resultado.getTipo());
        assertEquals("2025-06-15", resultado.getFecha());
    }

    @Test
    public void guardarEventoSinNombre() {
        Evento evento = new Evento();
        evento.setTipo("Vacunación");
        evento.setFecha("2025-06-15");

        assertThrows(ConstraintViolationException.class, () -> {
            eventoRepository.save(evento);
            eventoRepository.flush(); 
        });
    }    

    @Test
    public void guardarEventoSinTipo() {
        // Crear un evento sin tipo
        Evento evento = new Evento();
        evento.setNombre("Vacunación gratuita");
        evento.setFecha("2025-06-15");

        // Verificar que se lance la excepción adecuada cuando intentemos guardar el evento sin tipo
        assertThrows(ConstraintViolationException.class, () -> {
            eventoRepository.save(evento); // Intentamos guardar el evento sin tipo
            eventoRepository.flush(); // Forzamos el guardado en la base de datos
        });
    }

   
}
