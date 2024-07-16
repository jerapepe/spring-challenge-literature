package com.jera.Literatura.prin;

import com.jera.Literatura.api.ConvData;
import com.jera.Literatura.model.*;
import com.jera.Literatura.repository.AutorRepository;
import com.jera.Literatura.repository.BookRepository;
import com.jera.Literatura.api.RequestAPI;
import java.util.List;
import java.util.Scanner;

public class Men {
    private Scanner input = new Scanner(System.in);

    private final String URL_BASE = "http://gutendex.com/books/";
    private RequestAPI requestAPI = new RequestAPI();
    private ConvData convData = new ConvData();

    private BookRepository bookRepository;
    private AutorRepository autorRepository;

    public Men(BookRepository libroRepository, AutorRepository autorRepository){
        this.bookRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    Bienvenido.
                    Elija la opcion a traves de su numero:
                    1 - Buscar por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año
                    5 - Listar por idioma                       
                    0 - Salir
                    """;
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1 -> searchBook();
                case 2 -> showBooks();
                case 3 -> showAutors();
                case 4 -> autoresVivosAnio();
                case 5 -> listBookLanguage();
                case 0 -> System.out.println("Salir");
                default -> System.out.println("No exite esa opcion");
            }
        }
    }

    private DataBook getDataBook() {
        System.out.println("Escribe el nombre del libro: ");
        var nombreLibro = input.nextLine();
        var json = requestAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        DataResult datos = convData.getData(json, DataResult.class);
        return datos.book().get(0);
    }


    private void searchBook() {
        try{
            DataBook data = getDataBook();
            Book book = new Book(data);

            Autor autor = autorRepository.findByName(data.autores().get(0).name());

            if(autor != null){
                book.addAutor(autor);
                book.setAutor(autor);
            }else {
                autorRepository.save(book.getAutor());
            }

            bookRepository.save(book);
            System.out.println(book);
        }catch (IndexOutOfBoundsException e){
            System.out.println("libro no encontrado");
        }catch (Exception e){
            System.out.println("no se puede registrar un libro mas de una vez");

        }

    }

    private void showBooks() {
        List<Book> libros = bookRepository.findAll();
        libros.forEach(System.out::println);

    }

    private void showAutors() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void autoresVivosAnio() {
        System.out.println("Ingrese el año: ");
        var anio = input.nextInt();
        List<Autor> autoresVivos = autorRepository.searchAutorAnio(anio);
        if(autoresVivos.isEmpty()){
            System.out.println("No se encontro ningun autor");
        }else {
            autoresVivos.forEach(System.out::println);
        }
    }

    private void listBookLanguage() {
        String opciones = """
                Ingrese el idioma para buscar los libros:
                es - español
                en - ingles              
                """;
        System.out.println(opciones);
        var opcion = input.nextLine();
        if(opcion.equalsIgnoreCase("es") || opcion.equalsIgnoreCase("en")){
            List<Book> librosIdioma = bookRepository.findByIdiomaIgnoreCase(opcion);
            if(librosIdioma.isEmpty()){
                System.out.println("No hay libros en el idioma seleccionado");
            }else{
                librosIdioma.forEach(System.out::println);
            }
        }else{
            System.out.println("Incorrecto");
        }
    }
}