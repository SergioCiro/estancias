
package com.estanciasBuscador.repositorios;

import com.estanciasBuscador.entidades.Casa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepositorio extends JpaRepository<Casa,String>{
    
    @Query("SELECT c FROM Casa c WHERE c.pais = :pais")
    public List<Casa> buscarCasaPorPais(@Param("pais") String pais);
    
     @Query("SELECT c FROM Casa c WHERE c.cuidad = :cuidad")
    public List<Casa> buscarCasaPorCuidad(@Param("cuidad") String pais);
    
     @Query("SELECT c FROM Casa c WHERE c.precio = :precio")
    public List<Casa> buscarCasaPorPrecio(@Param("precio") String precio);
    
 @Query("SELECT c FROM Casa c WHERE c.tipo = :tipo")
    List<Casa> buscarCasaTipo(@Param("tipo") String tipo);
    
}
