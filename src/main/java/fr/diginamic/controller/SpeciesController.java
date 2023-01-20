package fr.diginamic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.model.Species;
import fr.diginamic.repository.AnimalRepository;
import fr.diginamic.repository.SpeciesRepository;
import jakarta.validation.Valid;

@Controller
public class SpeciesController {

	@Autowired
	private SpeciesRepository speciesRepository;

	@GetMapping("/species")
	public String listSpecies(Model model) {
		List<Species> species = (List<Species>) speciesRepository.findAll();
		model.addAttribute("species", species);
		return "list_species";
	}

	@GetMapping("/species/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Species> species = speciesRepository.findById(id);
		if (species.isPresent()) {
			model.addAttribute(species.get());
			// model.addAttribute("speciesList", speciesRepository.findAll());
			return "update_species";
		}
		return "error";
	}

	@GetMapping("/species/create")
	public String initCreate(Model model) {
		model.addAttribute(new Species());
		return "create_species";
	}

	@PostMapping("/species")
	public String createOrUpdate(@Valid Species speciesItem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			if(speciesItem.getId()!= null) {
				return "update_species";
			}
			return "create_species";
		}
		this.speciesRepository.save(speciesItem);
		return "redirect:/species";
	}

	@GetMapping("/species/delete/{id}")
	public String delete(@PathVariable("id") Integer speciesId) {
		Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
		speciesToDelete.ifPresent(species -> this.speciesRepository.delete(species));
		return "redirect:/species";
	}
}
