package com.estanciasBuscador.servicios;

import com.estanciasBuscador.Excepciones.MyException;
import com.estanciasBuscador.entidades.Casa;
import com.estanciasBuscador.entidades.Comentario;
import com.estanciasBuscador.repositorios.CasaRepositorio;
import com.estanciasBuscador.repositorios.ComentarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicio {

    @Autowired
    ComentarioRepositorio comentarioRepositorio;
    @Autowired
    CasaRepositorio casaRepositorio;

    @Transactional
    public void crearComentario(String descripcion, String casaId) throws MyException {
        validar(descripcion, casaId);
        Optional<Casa> respuesta = casaRepositorio.findById(casaId);

        if (respuesta.isPresent()) {

            Casa casa = respuesta.get();
            Comentario comentario = new Comentario();
            comentario.setDescripcion(descripcion);
            comentario.setCasa(casa);
            comentario.setActive(Boolean.TRUE);

            comentarioRepositorio.save(comentario);

        } else {

            throw new MyException("Casa no encontrada");
        }

    }

    public List<Comentario> listarComentarios() {

        List<Comentario> comentarios = new ArrayList<>();

        comentarios = comentarioRepositorio.findAll();
        return comentarios;

    }
    
    @Transactional
    public void modificarComentario(String idComentario, String descripcion, String casaId) throws MyException{
        
        validar(descripcion, casaId);
        
        Optional<Comentario> respuestaComentario = comentarioRepositorio.findById(idComentario);
        
        Optional<Casa> respuestaCasa = casaRepositorio.findById(casaId);
             
        if (respuestaComentario.isPresent() && respuestaCasa.isPresent()) {
            
            Comentario comentario = new Comentario();
            
            comentario.setCasa(respuestaCasa.get());
            comentario.setDescripcion(descripcion);
        }else{
            
            throw new MyException("Coomentario o casa no encontrada");
        }
        
        
    }
    
    @Transactional
    public void bajaComentario(String id) throws MyException{
        
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Comentario comentario = respuesta.get();
            
            comentario.setActive(Boolean.FALSE);
            
            comentarioRepositorio.save(comentario);
            
        }else{
            
            throw  new MyException("Comentario no encontrado al querer dar de baja");
        }
        
    }
    
    

    public void validar(String descripcion, String casaId) throws MyException {

        if (descripcion.isEmpty() || descripcion == null) {

            throw new MyException("La descripcion del comentario no puede ser nulo o estar vacio");

        }

        if (casaId.isEmpty() || casaId == null) {

            throw new MyException("El ID de la casa no puede ser nulo o estar vacio");

        }

    }

}
