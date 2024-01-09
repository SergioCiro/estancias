package com.estanciasBuscador.servicios;

import com.estanciasBuscador.Excepciones.MyException;
import com.estanciasBuscador.entidades.Cliente;
import com.estanciasBuscador.entidades.Usuario;
import com.estanciasBuscador.repositorios.ClienteRepositorio;
import com.estanciasBuscador.repositorios.UsuarioReposotorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;
    @Autowired
    UsuarioReposotorio usuarioReposotorio;

    @Transactional
    public void crearCliente(String nombre, String email, String calle, Integer numero, String codigoPostal, String pais, String cuidad, String usarioId) throws MyException {

        validar(nombre, email, calle, numero, codigoPostal, pais, cuidad, usarioId);

        Optional<Usuario> respuesta = usuarioReposotorio.findById(usarioId);

        if (respuesta.isPresent()) {

            Cliente cliente = new Cliente();
            cliente.setUsuario(respuesta.get());
            cliente.setActivo(Boolean.TRUE);
            cliente.setCalle(calle);
            cliente.setCodigoPostal(codigoPostal);
            cliente.setCuidad(cuidad);
            cliente.setEmail(email);
            cliente.setNombre(nombre);
            cliente.setNumero(numero);
            cliente.setPais(pais);

            clienteRepositorio.save(cliente);

        } else {
            if (!respuesta.isPresent()) {
                throw new MyException("Usuario no encontrado");
            }

        }
    }

    public List<Cliente> listarClientes() {

        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepositorio.findAll();

        return clientes;
    }
    
    @Transactional
    public void modificarCliente(String idCliente, String nombre, String email, String calle, Integer numero, String codigoPostal, String pais, String cuidad, String usarioId) throws MyException{
        
        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
         Optional<Usuario> respuesta = usuarioReposotorio.findById(usarioId);
        
        if (respuestaCliente.isPresent()&& respuesta.isPresent()) {
            
            Cliente cliente = respuestaCliente.get();
            Usuario usuario = respuesta.get();
            validar(nombre, email, calle, numero, codigoPostal, pais, cuidad, usarioId);
              cliente.setCalle(calle);
            cliente.setCodigoPostal(codigoPostal);
            cliente.setCuidad(cuidad);
            cliente.setEmail(email);
            cliente.setNombre(nombre);
            cliente.setNumero(numero);
            cliente.setPais(pais);
            clienteRepositorio.save(cliente);
        }else{
            
            throw new MyException("Cliente no encontrado al querer modificar");
        }

    }

    @Transactional
    public void bajaCliente(String id) throws MyException{
        
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Cliente cliente = respuesta.get();
            cliente.setActivo(Boolean.FALSE);
            clienteRepositorio.save(cliente);
         }else{
            
            throw new MyException("Cliente no encontrado al querer dar de baja");
        }
        
        
    }
    
    public void validar(String nombre, String email, String calle, Integer numero, String codigoPostal, String pais, String cuidad, String usarioId) throws MyException {

        if (nombre.isEmpty() || nombre == null) {

            throw new MyException("El nombre no puede ser nulo o estar vacio");
        }
        if (email.isEmpty() || email == null) {

            throw new MyException("El correo no puede ser nulo o estar vacio");
        }
        if (calle.isEmpty() || calle == null) {

            throw new MyException("La calle no puede ser nulo o estar vacio");
        }

        if (numero < 0) {

            throw new MyException("La numeracion no puede ser negativa");
        }
        if (codigoPostal.isEmpty() || codigoPostal == null) {

            throw new MyException("El codigo postal no puede ser nulo o estar vacio");
        }
        if (pais.isEmpty() || pais == null) {

            throw new MyException("El pais no puede ser nulo o estar vacio");
        }
        if (cuidad.isEmpty() || cuidad == null) {

            throw new MyException("La cuidad no puede ser nulo o estar vacio");
        }

        if (usarioId.isEmpty() || usarioId == null) {

            throw new MyException("El id de usuario no puede ser nulo o estar vacio");
        }

    }
}
