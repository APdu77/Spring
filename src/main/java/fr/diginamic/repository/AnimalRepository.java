package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{

}
