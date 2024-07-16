package com.jera.Literatura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "libros")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private int numeroDescargas;
    @ManyToOne
    private Autor autor;
    public Book(DataBook dataBook) {
        this.titulo = dataBook.titulo();
        this.idioma = dataBook.idiomas().get(0);
        this.numeroDescargas = dataBook.numeroDescargas();
        this.autor = dataBook.autores().stream().map(Autor::new).toList().get(0);

    }
    public void addAutor(Autor autor) {
        autor.getLibros().add(this);
    }

    @Override
    public String toString() {
        return "LIBRO\n"+
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor.getNombre() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas: " + numeroDescargas + "\n";
    }
}