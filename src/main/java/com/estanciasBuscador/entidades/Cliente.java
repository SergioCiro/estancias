package com.estanciasBuscador.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import java.io.Serializable;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;
    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String calle;
    private Integer numero;
    private String codigoPostal;
    private String Pais;
    private String cuidad;
    
    private Boolean activo;
    
    @OneToOne
    private Usuario usuario;
}
