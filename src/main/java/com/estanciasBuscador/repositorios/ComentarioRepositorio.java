
package com.estanciasBuscador.repositorios;

import com.estanciasBuscador.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, String>{
    
    @Query("SELECT com FROM Comentario com WHERE com.idCasa = :idCasa")
    public Comentario buscarComentariosPorIdCasa(@Param("idCasa")String idCasa);
    
}
