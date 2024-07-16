package com.jera.Literatura;

import com.jera.Literatura.model.Autor;
import com.jera.Literatura.prin.Men;
import com.jera.Literatura.repository.AutorRepository;
import com.jera.Literatura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	AutorRepository autorRepository;
	@Override
	public void run(String... args)throws Exception{
		Men menu = new Men(bookRepository, autorRepository);
		menu.showMenu();
	}
	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}
}