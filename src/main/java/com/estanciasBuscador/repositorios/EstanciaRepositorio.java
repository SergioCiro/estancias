
package com.estanciasBuscador.repositorios;

import com.estanciasBuscador.entidades.Estancia;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EstanciaRepositorio extends JpaRepository<Estancia, String>{
    
    
    @Query("SELECT e FROM Estancia e WHERE e.huesped = :huesped")
    public List<Estancia> buscarEntanciaPorHuesped(@Param("huesped")String huesped);
    
    @Query("SELECT e FROM  Estancia e WHERE e.fechaDesde = :fechaDesde")
    public List<Estancia> buscarPorFechaDesde(@Param("fechaDesde") java.sql.Date fechaDesde);
    
    
      @Query("SELECT e FROM  Estancia e WHERE e.fechaDesde = :fechaHasta")
    public List<Estancia> buscarPorFechaHasta(@Param("fechaHasta") java.sql.Date fechaDesde);
}
