package com.jera.Literatura.repository;

import com.jera.Literatura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository <Book, Long>{
    List<Book> findByIdiomaIgnoreCase(String idioma);
}
