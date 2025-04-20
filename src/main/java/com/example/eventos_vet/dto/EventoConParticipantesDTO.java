package com.example.eventos_vet.dto;

import com.example.eventos_vet.model.Participante;
import java.util.List;

public class EventoConParticipantesDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String fecha;
    private List<Participante> participantes;

    // Constructor vacío
    public EventoConParticipantesDTO() {}

    // Constructor completo
    public EventoConParticipantesDTO(Long id, String nombre, String tipo, String fecha, List<Participante> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.participantes = participantes;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public List<Participante> getParticipantes() { return participantes; }
    public void setParticipantes(List<Participante> participantes) { this.participantes = participantes; }
}
