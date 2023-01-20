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

import fr.diginamic.model.Person;
import fr.diginamic.repository.AnimalRepository;
import fr.diginamic.repository.PersonRepository;
import jakarta.validation.Valid;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AnimalRepository animalRepository;

	@GetMapping("/person")
	public String listPerson(Model model) {
		List<Person> persons = (List<Person>) personRepository.findAll();
		model.addAttribute("persons", persons);
		return "list_person";
	}

	@GetMapping("/person/{id}")
	public String initUpdate(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			model.addAttribute(person.get());
			model.addAttribute("animals", animalRepository.findAll());
			return "update_person";
		}
		return "error";
	}

	@GetMapping("/person/create")
	public String initCreate(Model model) {
		model.addAttribute(new Person());
		model.addAttribute("animals", animalRepository.findAll());
		return "create_person";
	}

	@PostMapping("/person")
	public String createOrUpdate(@Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("animals", animalRepository.findAll());
			if (person.getId() != null) {
				return "update_person";
			} else {
				return "create_person";
			}
		}
		this.personRepository.save(person);
		return "redirect:/person";
	}

	@GetMapping("/person/delete/{id}")
	public String delete(@PathVariable("id") Integer personId) {
		Optional<Person> personToDelete = this.personRepository.findById(personId);
		personToDelete.ifPresent(person -> this.personRepository.delete(person));
		return "redirect:/person";
	}

}
