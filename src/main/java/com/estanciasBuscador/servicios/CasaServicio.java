package com.estanciasBuscador.servicios;

import com.estanciasBuscador.Excepciones.MyException;
import com.estanciasBuscador.entidades.Casa;
import com.estanciasBuscador.enums.Tipo;
import com.estanciasBuscador.repositorios.CasaRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaServicio {

    @Autowired
    CasaRepositorio casaRepositorio;

    @Transactional
    public void crearCasa(String calle, String codPostal, String cuidad, String pais, Date fechaDesde, Date fechaHasta, Integer minDias, Integer maxDias, Double precio, Tipo tipo) throws MyException {

        validar(calle, codPostal, cuidad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipo);

        Casa casa = new Casa();

        casa.setActivo(Boolean.TRUE);
        casa.setCalle(calle);
        casa.setCodPostal(codPostal);
        casa.setCuidad(cuidad);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setMaxDias(maxDias);
        casa.setMinDias(minDias);
        casa.setNumero(maxDias);
        casa.setPais(pais);
        casa.setPrecio(precio);
        casa.setTipo(tipo);

        casaRepositorio.save(casa);

    }

    public List<Casa> listarCasas() {

        List<Casa> casas = new ArrayList<>();

        casas = casaRepositorio.findAll();

        return casas;
    }

    @Transactional
    public void modificarCasaString(String idCasa, String calle, String codPostal, String cuidad, String pais, Date fechaDesde, Date fechaHasta, Integer minDias, Integer maxDias, Double precio, Tipo tipo) throws MyException {

        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);

        if (respuesta.isPresent()) {

            validar(calle, codPostal, cuidad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipo);

            Casa casa = respuesta.get();

            casa.setCalle(calle);
            casa.setCodPostal(codPostal);
            casa.setCuidad(cuidad);
            casa.setFechaDesde(fechaDesde);
            casa.setFechaHasta(fechaHasta);
            casa.setMaxDias(maxDias);
            casa.setMinDias(minDias);
            casa.setNumero(maxDias);
            casa.setPais(pais);
            casa.setPrecio(precio);
            casa.setTipo(tipo);
            
            casaRepositorio.save(casa);
        }else{
            
            throw new MyException("Casa no encontrada al querer modificar");
            
        }

    }
    
    @Transactional
    public void bajaCasa(String id) throws MyException{
        
        Optional<Casa> respuesta = casaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Casa casa = respuesta.get();
            casa.setActivo(Boolean.FALSE);
            casaRepositorio.save(casa);
        }else{
            
            throw new MyException("Casa no encontrada al querer dar de baja");
            
        }
    }
    
    

    public void validar(String calle, String codPostal, String cuidad, String pais, Date fechaDesde, Date fechaHasta, Integer minDias, Integer maxDias, Double precio, Tipo tipo) throws MyException {

        if (calle.isEmpty() || calle == null) {

            throw new MyException("La calle no puede ser nulo o estar vacio");

        }

        if (codPostal.isEmpty() || codPostal == null) {

            throw new MyException("El codigo postal no puede ser nulo o estar vacio");

        }
        if (cuidad.isEmpty() || cuidad == null) {

            throw new MyException("La cuidad no puede ser nulo o estar vacio");

        }
        if (pais.isEmpty() || pais == null) {

            throw new MyException("El pais no puede ser nulo o estar vacio");

        }

        if (minDias <= 0) {

            throw new MyException("Dias minimos, valor no valido");
        }

        if (maxDias < minDias) {

            throw new MyException("Dias maximos, valor no valido");

        }

        if (fechaDesde == null || fechaHasta == null) {
            throw new MyException("Las fechas no pueden ser nulas");
        }
        if (fechaDesde.after(fechaHasta)) {
            throw new MyException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        if (precio <= 0) {

            throw new MyException("Debe definir un precio mayor a cero");

        }

    }

}
