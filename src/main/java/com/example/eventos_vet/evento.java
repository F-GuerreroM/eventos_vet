package com.example.eventos_vet;

import java.util.List;

public class evento {
    private int id;
    private String nombre;
    private String tipo;
    private String fecha;
    private List<String> participantes;

    public evento(int id, String nombre, String tipo, String fecha, List<String> participantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.participantes = participantes;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public List<String> getParticipantes() {
        return participantes;
    }
}
