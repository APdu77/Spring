package fr.diginamic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.diginamic.model.Person;
import fr.diginamic.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public Person create(@Valid Person personToCreate) {
		return this.personRepository.save(personToCreate);
	}

	public Person update(@Valid Person updatedPerson) {
		return this.personRepository.save(updatedPerson);
	}

	public Page<Person> findAllPageable(Pageable pageable) {
		return this.personRepository.findAll(pageable);
	}

	public List<Person> findAll() {
		return this.personRepository.findAll();
	}

//	public List<Integer> findAllIds() {
//		List<Integer> ids = new ArrayList<>();
//		for (Person p : this.personRepository.findAll()) {
//			ids.add(p.getId());
//		}
//		return ids;
//	}

	public Boolean findIfExists(Integer id) {
		return personRepository.existsById(id);
	}

	// retourne l'objet
	public Person findById2(Integer id) {
		// return this.personRepository.findById(id);
		return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	// retourne un optional de l'objet
	public Optional<Person> findById(Integer id) {
		// return this.personRepository.findById(id);
		return this.personRepository.findById(id);
	}

	public void delete(Integer id) {
		this.personRepository.deleteById(id);
	}

}
