package fr.diginamic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.model.Animal;
import fr.diginamic.model.Species;
import fr.diginamic.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class SpeciesService {

	@Autowired
	SpeciesRepository speciesRepository;

	public Species create(@Valid Species speciesToCreate) {
		return this.speciesRepository.save(speciesToCreate);
	}

	public Species update(@Valid Species updatedSpecies) {
		return this.speciesRepository.save(updatedSpecies);
	}

	public Page<Species> findAllPageable(Pageable pageable) {
		return this.speciesRepository.findAll(pageable);
	}

	public List<Species> findAll() {
		return this.speciesRepository.findAll();
	}

	// retourne un optional de l'objet
	public Optional<Species> findById(Integer id) {
		return this.speciesRepository.findById(id);
	}

	public Species findById2(Integer id) {
		return this.speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public void delete(Integer id) {
		this.speciesRepository.deleteById(id);
	}

}
