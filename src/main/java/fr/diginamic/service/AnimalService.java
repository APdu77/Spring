package fr.diginamic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.model.Animal;
import fr.diginamic.model.Person;
import fr.diginamic.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class AnimalService {

	@Autowired
	AnimalRepository animalRepository;

	public Animal create(@Valid Animal animalToCreate) {
		return this.animalRepository.save(animalToCreate);
	}

	public Animal update(@Valid Animal updatedAnimal) {
		return this.animalRepository.save(updatedAnimal);
	}

	public Page<Animal> findAllPageable(Pageable pageable) {
		return this.animalRepository.findAll(pageable);
	}

	public List<Animal> findAll() {
		return this.animalRepository.findAll();
	}

	// retourne un optional de l'objet
	public Optional<Animal> findById(Integer id) {
		return this.animalRepository.findById(id);
	}

	public Animal findById2(Integer id) {
		return this.animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public void delete(Integer id) {
		this.animalRepository.deleteById(id);
	}

}
