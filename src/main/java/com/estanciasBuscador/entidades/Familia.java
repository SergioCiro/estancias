package com.estanciasBuscador.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

public class Familia {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private String nombre;
    private String email;

    private Integer edadMinima;

    private Integer edadMaxima;

    private Integer numHijos;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Casa casa;

}
