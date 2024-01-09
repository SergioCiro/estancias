
package com.estanciasBuscador.repositorios;

import com.estanciasBuscador.entidades.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, String>{
    
       @Query("SELECT f FROM Familia f WHERE fa.email = :email")
    public Familia buscarFamiliaPorEmail(@Param("email") String email);
    
    
}
