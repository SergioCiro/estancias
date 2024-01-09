
package com.estanciasBuscador.servicios;

import com.estanciasBuscador.Excepciones.MyException;
import com.estanciasBuscador.entidades.Casa;
import com.estanciasBuscador.entidades.Familia;
import com.estanciasBuscador.entidades.Usuario;
import com.estanciasBuscador.repositorios.CasaRepositorio;
import com.estanciasBuscador.repositorios.FamiliaRepositorio;
import com.estanciasBuscador.repositorios.UsuarioReposotorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    
@Service
public class FamiliaServicio {
    
    
    @Autowired
    FamiliaRepositorio familiaRepositorio ;
    @Autowired
    CasaRepositorio casaRepositorio;
    
    @Autowired
    UsuarioReposotorio usuarioRepositorio;
    
    @Transactional
    public void crearFamilia(String nombre,Integer edadMin, Integer edadMax, Integer hijos, String email,String idCasa, String idUsuario ) throws MyException{
        
        validar(nombre, edadMin, edadMax, hijos, email, idCasa, idUsuario);
        
        Optional<Casa> respuestaCasa = casaRepositorio.findById(idCasa);
        
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);
        
        
        if (respuestaCasa.isPresent() && respuestaUsuario.isPresent()) {
            
            Casa casa = respuestaCasa.get();
            Usuario usuario = respuestaUsuario.get();
            
            Familia familia = new Familia();
            
            familia.setCasa(casa);
            familia.setUsuario(usuario);
            familia.setEdadMaxima(edadMax);
            familia.setEdadMinima(edadMin);
            familia.setEmail(email);
            familia.setNombre(nombre);
            familia.setNumHijos(hijos);
          
          familiaRepositorio.save(familia);
        
             } else {
            if (!respuestaUsuario.isPresent()) {
                throw new MyException("Usuario no encontrado");
            }
            if (!respuestaCasa.isPresent()) {
                throw new MyException("Casa no encontrada");
            }
      

        }
    
    }
    
    public List<Familia> listarFamilias(){
        
        
        List<Familia> familias = new ArrayList<>();
        
        familias = familiaRepositorio.findAll();
        
        return familias;
        
    }
    
    @Transactional
    public void modificarFamilia(String idFamilia, String nombre,Integer edadMin, Integer edadMax, Integer hijos, String email,String idCasa, String idUsuario) throws MyException{
        
        Optional<Familia> respueta = familiaRepositorio.findById(idFamilia);
        
        if (respueta.isPresent()) {
            
            validar(nombre, edadMin, edadMax, hijos, email);
            
            Familia familia = familiaRepositorio.findById(idFamilia).get();
            
             familia.setEdadMaxima(edadMax);
            familia.setEdadMinima(edadMin);
            familia.setEmail(email);
            familia.setNombre(nombre);
            familia.setNumHijos(hijos);
            
            familiaRepositorio.save(familia);
            
        }else{
            
            throw new MyException("Familia no encontrada al querer modificar");
        }
        
    }
    
@Transactional
public void bajaFamilia(String id) throws MyException {
    Optional<Familia> respuesta = familiaRepositorio.findById(id);

    if (respuesta.isPresent()) {
        Familia familia = respuesta.get();
        familia.setActive(Boolean.FALSE);
        familiaRepositorio.save(familia);
    } else {
        throw new MyException("Familia no encontrada al querer dar de baja");
    }
}

    
    public void validar (String nombre,Integer edadMin, Integer edadMax, Integer hijos, String email) throws MyException{
        
         if (nombre.isEmpty() || nombre == null) {

            throw new MyException("El nombre no puede ser nulo o estar vacio");

        }
          if (email.isEmpty() || email == null) {

            throw new MyException("El email no puede ser nulo o estar vacio");

        }
          
          if (edadMin<0) {
            
              throw new MyException("Edad minima no valida");
        }
        
          if (edadMax<0 || edadMax< edadMin) {
            
              throw new MyException("Edad max no valida o menor a la edad minima");
        }
          if(hijos<0){
              
              
               throw new MyException("Cantidad de hijos, valor no valido");
              
          }
        
    }
    
    
    public void validar(String nombre,Integer edadMin, Integer edadMax, Integer hijos, String email,String idCasa, String idUsuario  ) throws MyException{
        
          if (nombre.isEmpty() || nombre == null) {

            throw new MyException("El nombre no puede ser nulo o estar vacio");

        }
          if (email.isEmpty() || email == null) {

            throw new MyException("El email no puede ser nulo o estar vacio");

        }
          
          if (edadMin<0) {
            
              throw new MyException("Edad minima no valida");
        }
        
          if (edadMax<0 || edadMax< edadMin) {
            
              throw new MyException("Edad max no valida o menor a la edad minima");
        }
          if(hijos<0){
              
              
               throw new MyException("Cantidad de hijos, valor no valido");
              
          }
          
           if (idCasa.isEmpty() || idCasa == null) {

            throw new MyException("El idCasa no puede ser nulo o estar vacio");

        }
         if (idUsuario.isEmpty() || idUsuario == null) {

            throw new MyException("El idUsuario no puede ser nulo o estar vacio");

        }
        
    }
    
    
    
}
