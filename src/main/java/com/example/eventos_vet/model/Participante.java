package com.example.eventos_vet.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "participante")
public class Participante extends RepresentationModel<Participante> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombremascota")
    private String nombremascota;

    @NotNull
    @Column(name = "idevento")
    private Long idevento;

    @NotNull
    @Column(name = "fecha")
    private String fecha;

    // RELACIÓN con Evento (JOIN)
    @ManyToOne
    @JoinColumn(name = "idevento", referencedColumnName = "id", insertable = false, updatable = false)
    private Evento evento;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreMascota() {
        return nombremascota;
    }

    public Long getIdevento() {
        return idevento;
    }

    public String getFecha() {
        return fecha;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreMascota(String nombremascota) {
        this.nombremascota = nombremascota;
    }


    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}