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
import fr.diginamic.service.PersonService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/")
	public Page<Person> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "display", defaultValue = "20") Integer display) {
		if (!personService.findAll().isEmpty()) {
			return this.personService.findAllPageable(PageRequest.of(page - 1, display));
		}
		throw new RuntimeException("-----------personLISTE ACTUELLEMENT VIDE-----------");
	}

	@GetMapping("/{id}")
	public Person findOne(@PathVariable("id") Integer id) {
		Optional<Person> person = personService.findById(id);
		//si l'otional retourne n'est pas vide, l'id existe bien
		if (person.isPresent()) {
			// .get() pour recuperer l'objet
			return person.get();
		}
		throw new RuntimeException("-----------personID INEXISTANT-----------");
	}

	@PostMapping("/")
	public Person createPerson(@RequestBody @Valid Person personToCreate) {
//		if (!personService.findAllIds().contains(personToCreate.getId())) {
//			return this.personService.create(personToCreate);
//		}
//		throw new RuntimeException("-----------ID DEJA EXISTANT-----------");

		if (personToCreate.getId() == null) {
			return this.personService.create(personToCreate);
		}
		throw new RuntimeException("-----------CREEZ ENTITE SANS ID-----------");

	}

	@PutMapping("/{id}")
	// @PutMapping
	public Person updatePerson(@RequestBody @Valid Person updatedPerson, @PathVariable("id") Integer id) {
		//les 2 methodes menent au meme resultat
		if (!personService.findIfExists(updatedPerson.getId()) || personService.findById(id).isEmpty()) {
			throw new RuntimeException("-----------personID INEXISTANT-----------");
		} else if (id != updatedPerson.getId()) {
			throw new RuntimeException("-----------LES personID NE MATCHENT PAS-----------");
		}
		return this.personService.update(updatedPerson);
	}
	
//	//Methode speciale pour test ThrowException TP8
//	@PutMapping("/{id}")
//	// @PutMapping
//	public Person updatePersonTestException(Person updatedPerson, @PathVariable("id") Integer id) {
//		return this.personService.update(updatedPerson);
//	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer personId) {

		if (personService.findById(personId).isPresent()) {
			this.personService.delete(personId);
		}
		throw new RuntimeException("-----------personID INEXISTANT-----------");
	}
}
