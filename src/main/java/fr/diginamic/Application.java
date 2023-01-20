package fr.diginamic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.enums.Gender;
import fr.diginamic.model.Animal;
import fr.diginamic.model.Species;
import fr.diginamic.repository.AnimalRepository;
import fr.diginamic.repository.PersonRepository;
import fr.diginamic.repository.SpeciesRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
@RestController
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
