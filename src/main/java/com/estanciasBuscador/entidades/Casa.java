package com.estanciasBuscador.entidades;

import com.estanciasBuscador.enums.Tipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Casa implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;
    private String calle;
    private Integer numero;
    private String codPostal;
    private String cuidad;
    private String pais;
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Temporal(TemporalType.DATE)
    private Date FechaHasta;
    private Integer minDias;
    private Integer maxDias;
    private Double precio;
    private Boolean activo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
