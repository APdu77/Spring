package fr.diginamic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.model.Animal;
import fr.diginamic.model.Person;
import fr.diginamic.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/")
	public Page<Animal> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "display", defaultValue = "20") Integer display) {
		if (!animalService.findAll().isEmpty()) {
			return this.animalService.findAllPageable(PageRequest.of(page - 1, display));
		}
		throw new RuntimeException("-----------animalLISTE ACTUELLEMENT VIDE-----------");
	}

	@GetMapping("/{id}")
	public Animal findOne(@PathVariable("id") Integer id) {
		Optional<Animal> animal = animalService.findById(id);
		if (animal.isPresent()) {
			return animal.get();
		}
		throw new RuntimeException("-----------animalID INEXISTANT-----------");
	}

	@PostMapping("/")
	public Animal createAnimal(@RequestBody @Valid Animal animalToCreate) {
		if (animalToCreate.getId() == null) {
			return this.animalService.create(animalToCreate);
		}
		throw new RuntimeException("-----------CREEZ VOTRE ANIMAL SANS ID-----------");
	}

	@PutMapping("/{id}")
	// @PutMapping
	public Animal updateAnimal(@RequestBody @Valid Animal updatedAnimal, @PathVariable("id") Integer id) {
		if (animalService.findById(updatedAnimal.getId()).isEmpty() || animalService.findById(id).isEmpty()) {
			throw new RuntimeException("-----------animalID INEXISTANT-----------");
		} else if (id != updatedAnimal.getId()) {
			throw new RuntimeException("-----------LES animalID NE MATCHENT PAS-----------");
		}
		return this.animalService.update(updatedAnimal);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer animalId) {

		if (animalService.findById(animalId).isPresent()) {
			this.animalService.delete(animalId);
		}
		throw new RuntimeException("-----------animalID INEXISTANT-----------");
	}

}
