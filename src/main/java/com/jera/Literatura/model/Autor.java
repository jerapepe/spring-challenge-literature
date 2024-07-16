package com.jera.Literatura.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private int anioNacimiento;
    private int anioFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> libros = new ArrayList<>();

    public Autor(DataAutor dataAutor){
        this.name = dataAutor.name();
        this.anioNacimiento = dataAutor.anioNacimiento();
        this.anioFallecimiento = dataAutor.anioFallecimiento();
    }

    @Override
    public String toString() {
        return "\n Autor: " + name + "\n" +
                "Fecha de nacimiento: " + anioNacimiento + "\n" +
                "Fecha de fallecimiento: " + anioFallecimiento +"\n" +
                "Libros: " + libros.stream().map(Book::getTitulo).toList() + "\n";

    }
    public String getNombre() {
        return name;
    }
}
