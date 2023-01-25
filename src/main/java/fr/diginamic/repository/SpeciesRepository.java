package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.model.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer>{
	
}
