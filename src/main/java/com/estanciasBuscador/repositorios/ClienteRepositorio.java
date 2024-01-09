
package com.estanciasBuscador.repositorios;

import com.estanciasBuscador.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente , String>{
    
    @Query("SELECT cli FROM Cliente cli WHERE cli.email = :email")
    public Cliente buscarClientePorEmail(@Param("email") String email);
    
}
