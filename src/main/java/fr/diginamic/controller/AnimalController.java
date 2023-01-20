package fr.diginamic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.model.Animal;
import fr.diginamic.repository.AnimalRepository;
import fr.diginamic.repository.PersonRepository;
import fr.diginamic.repository.SpeciesRepository;
import jakarta.validation.Valid;

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/animal")
	public String listAnimal(Model model) {
		List<Animal> animals = (List<Animal>) animalRepository.findAll();
		model.addAttribute("animals", animals);
		return "list_animal";
	}

	@GetMapping("/animal/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animal = animalRepository.findById(id);
		if (animal.isPresent()) {
			model.addAttribute(animal.get());
			model.addAttribute("persons",personRepository.findAll());
			model.addAttribute("species",speciesRepository.findAll());
			return "update_animal";
		}
		return "error";
	}

	@GetMapping("/animal/create")
	public String initCreate(Model model) {
		model.addAttribute(new Animal());
		model.addAttribute("persons",personRepository.findAll());
		model.addAttribute("species",speciesRepository.findAll());
		return "create_animal";
	}

	@PostMapping("/animal")
	public String createOrUpdate(@Valid Animal animal, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("persons",personRepository.findAll());
			model.addAttribute("species",speciesRepository.findAll());
			if (animal.getId() != null) {
				return "update_animal";
			}
			return "create_animal";
		}
		this.animalRepository.save(animal);
		return "redirect:/animal";
	}

	@GetMapping("/animal/delete/{id}")
	public String delete(@PathVariable("id") Integer animalId) {
		Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
		animalToDelete.ifPresent(animal -> this.animalRepository.delete(animal));
		return "redirect:/animal";
	}

}
