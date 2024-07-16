package com.jera.Literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jera.Literatura.model.Autor;
import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>  {
    Autor findByName(String name);
    @Query("select a from Autor a where a.anioNacimiento <=:anio and a.anioFallecimiento >=:anio")
    List<Autor> searchAutorAnio(int anio);
}
