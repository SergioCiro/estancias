package com.estanciasBuscador.servicios;

import com.estanciasBuscador.Excepciones.MyException;
import com.estanciasBuscador.entidades.Casa;
import com.estanciasBuscador.entidades.Cliente;
import com.estanciasBuscador.entidades.Estancia;
import com.estanciasBuscador.repositorios.CasaRepositorio;
import com.estanciasBuscador.repositorios.ClienteRepositorio;
import com.estanciasBuscador.repositorios.EstanciaRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstanciaServicio {

    @Autowired
    EstanciaRepositorio estanciaRepositorio;
    @Autowired
    ClienteRepositorio clienteRepositorio;
    @Autowired
    CasaRepositorio casarepositorio;

    @Transactional
    public void crearEstancia(String huesped, String idCliente, String idCasa, Date fechaDesde, Date fechaHasta) throws MyException {

        validar(huesped, idCliente, idCasa, fechaDesde, fechaHasta);

        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
        Optional<Casa> respuestaCasa = casarepositorio.findById(idCasa);
        Casa casa = new Casa();
        Cliente cliente = new Cliente();

        if (respuestaCliente.isPresent() && respuestaCasa.isPresent()) {
            cliente = respuestaCliente.get();
            casa = respuestaCasa.get();

            Estancia estancia = new Estancia();
            estancia.setCasa(casa);
            estancia.setCliente(cliente);
            estancia.setHuesped(huesped);
            estancia.setActivo(Boolean.TRUE);
            estancia.setFechaDesde(fechaDesde);
            estancia.setFechaHasta(fechaHasta);

            estanciaRepositorio.save(estancia);

        } else {
            if (!respuestaCliente.isPresent()) {
                throw new MyException("Cliente no encontrado");
            }
            if (!respuestaCasa.isPresent()) {
                throw new MyException("Casa no encontrada");
            }

        }
    }

    public List<Estancia> listarEstancias() {

        List<Estancia> estancias = new ArrayList<>();

        estancias = estanciaRepositorio.findAll();

        return estancias;
    }

    @Transactional
    public void modificarEstancia(String id, String huesped, String idCliente, String idCasa, Date fechaDesde, Date fechaHasta) throws MyException {

        Optional<Estancia> respuesta = estanciaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            validar(huesped,fechaDesde, fechaHasta);

            Estancia estancia = respuesta.get();
            estancia.setHuesped(huesped);
             estancia.setFechaDesde(fechaDesde);
            estancia.setFechaHasta(fechaHasta);
            
            estanciaRepositorio.save(estancia);

        }else
            throw new MyException("Estancia no encontrada");

    }
    
    public void bajaEstancia(String idEstancia) throws MyException{
        
        Optional<Estancia> respuesta = estanciaRepositorio.findById(idEstancia);
        if (respuesta.isPresent()) {
            
            Estancia estancia = respuesta.get();
            
            estancia.setActivo(Boolean.FALSE);
            
            estanciaRepositorio.save(estancia);
        }else{
         throw new MyException("Estancia no encontrada al querer dar de baja");
        
    }
        
        
        
    }
    
    public void validar(String huesped, Date fechaDesde, Date fechaHasta) throws MyException{
        
          if (huesped.isEmpty() || huesped == null) {

            throw new MyException("El nombre del huesped no puede ser nulo o estar vacio");

        }
        
        if (fechaDesde == null || fechaHasta == null) {
            throw new MyException("Las fechas no pueden ser nulas");
        }
        if (fechaDesde.after(fechaHasta)) {
            throw new MyException("La fecha de inicio no puede ser posterior a la fecha de fin");
        } 
        
    }

    public void validar(String huesped, String idCliente, String idCasa, Date fechaDesde, Date fechaHasta) throws MyException {

        if (huesped.isEmpty() || huesped == null) {

            throw new MyException("El nombre del huesped no puede ser nulo o estar vacio");

        }
        if (idCliente.isEmpty() || idCliente == null) {

            throw new MyException("El email del cliente no puede ser nulo o estar vacio");

        }
        if (idCasa.isEmpty() || idCasa == null) {

            throw new MyException("El id de la casa no puede ser nulo o estar vacio");

        }

        if (fechaDesde == null || fechaHasta == null) {
            throw new MyException("Las fechas no pueden ser nulas");
        }
        if (fechaDesde.after(fechaHasta)) {
            throw new MyException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

    }

}
