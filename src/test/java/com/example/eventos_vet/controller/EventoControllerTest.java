package com.example.eventos_vet.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.example.eventos_vet.model.Evento;
import com.example.eventos_vet.service.EventoService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoService eventoService; // o EventoServiceImpl según lo que uses

    @Test
    public void obtenerTodosConHATEOASTest() throws Exception {
        
        Evento evento1 = new Evento();
        evento1.setId(1L);
        evento1.setNombre("Campaña de vacunación");
        evento1.setTipo("Salud");
        evento1.setFecha("2025-05-10");

        Evento evento2 = new Evento();
        evento2.setId(2L);
        evento2.setNombre("Adopciones masivas");
        evento2.setTipo("Adopción");
        evento2.setFecha("2025-05-12");

        List<Evento> eventos = Arrays.asList(evento1, evento2);

        when(eventoService.getAllEventos()).thenReturn(eventos);

        // Act & Assert
        mockMvc.perform(get("/eventos").accept(MediaTypes.HAL_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._embedded.eventoList", Matchers.hasSize(2)))
            .andExpect(jsonPath("$._embedded.eventoList[0].nombre", Matchers.is("Campaña de vacunación")))
            .andExpect(jsonPath("$._embedded.eventoList[1].nombre", Matchers.is("Adopciones masivas")))
            .andExpect(jsonPath("$._embedded.eventoList[0]._links.self.href", Matchers.containsString("/eventos/1")))
            .andExpect(jsonPath("$._links.self.href", Matchers.containsString("/eventos")));
    }

    @Test
    public void obtenerEventoNoExistenteTest() throws Exception {
        // Simulamos que no existe el evento con ID 99
        when(eventoService.getEventoById(99L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/eventos/99").accept(MediaTypes.HAL_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error", Matchers.is("Evento no encontrado")));
    }
}
