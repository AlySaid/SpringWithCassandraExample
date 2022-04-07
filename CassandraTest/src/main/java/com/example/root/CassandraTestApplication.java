package com.example.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.root.model.Person;
import com.example.root.repository.PersonRepository;

@SpringBootApplication
public class CassandraTestApplication {

	private final static Logger log = LoggerFactory.getLogger(CassandraTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CassandraTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(PersonRepository personRepository) {
		return args -> {
			personRepository.deleteAll();

			Person john = new Person("John", "Doe", 35);
			Person cena = new Person("cena", "youk", 50);

			Person savedJohn = personRepository.save(john);
			Person savedCena = personRepository.save(cena);

			personRepository.findAll().forEach(v -> log.info("Person : {}", v.getName()));

			personRepository.findById(savedJohn.getId()).ifPresent(v -> log.info("Person by id: {}", v.getName()));
		};

	}
}