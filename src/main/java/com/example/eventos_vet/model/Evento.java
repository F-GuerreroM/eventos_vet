package com.example.eventos_vet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "evento")
public class Evento {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)        
        @Column(name = "id")        
        private Long id;
        @NotNull
        @Size(max=40)   
        @Column(name= "nombre")
        private String nombre; 
        @NotNull             
        @Column(name= "tipo")
        private String tipo;        
        @NotNull        
        @Column(name= "fecha")        
        private String fecha;
       //Getters and setters    
    public Long getId() {
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }   
}

