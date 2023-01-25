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

import fr.diginamic.model.Person;
import fr.diginamic.model.Species;
import fr.diginamic.service.SpeciesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

	@Autowired
	private SpeciesService speciesService;

	@GetMapping("/")
	public Page<Species> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "display", defaultValue = "20") Integer display) {
		if (!speciesService.findAll().isEmpty()) {
			return this.speciesService.findAllPageable(PageRequest.of(page - 1, display));
		}
		throw new RuntimeException("-----------speciesLISTE ACTUELLEMENT VIDE-----------");
	}

	@GetMapping("/{id}")
	public Species getOne(@PathVariable("id") Integer id) {
		Optional<Species> species = speciesService.findById(id);
		if (species.isPresent()) {
			return species.get();
		}
		throw new RuntimeException("-----------speciesID INEXISTANT-----------");
	}

	@PostMapping("/")
	public Species createSpecies(@RequestBody @Valid Species speciesToCreate) {
		if (speciesToCreate.getId() == null) {
			return this.speciesService.create(speciesToCreate);
		}
		throw new RuntimeException("-----------CREEZ VOTRE SPECIES SANS ID-----------");
	}

	@PutMapping("/{id}")
	// @PutMapping
	public Species updateSpecies(@RequestBody @Valid Species updatedSpecies, @PathVariable("id") Integer id) {
		if (speciesService.findById(updatedSpecies.getId()).isEmpty() || speciesService.findById(id).isEmpty()) {
			throw new RuntimeException("-----------speciesID INEXISTANT-----------");
		} else if (id != updatedSpecies.getId()) {
			throw new RuntimeException("-----------LES speciesID NE MATCHENT PAS-----------");
		}
		return this.speciesService.update(updatedSpecies);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer speciesId) {

		if (speciesService.findById(speciesId).isPresent()) {
			this.speciesService.delete(speciesId);
		}
		throw new RuntimeException("-----------speciesID INEXISTANT-----------");
	}

}
