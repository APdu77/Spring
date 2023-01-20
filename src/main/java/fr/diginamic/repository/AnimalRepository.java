package fr.diginamic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.enums.Gender;
import fr.diginamic.model.Animal;
import fr.diginamic.model.Species;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer>{
	List<Animal> findBySpecies(Species specie);
	
	List<Animal> findBySpeciesCommonNameIgnoreCase(String commonName);
	
	List<Animal> findBySpeciesLatinNameContainingIgnoreCase(String part);
	
	List<Animal> findAnimalByColorIn(List<String> colorList);
	
	//méthode qui renvoie le nombre d’Animaux dont le Sex est égal à la valeur donnée en paramètres
		//@Query("SELECT COUNT(Animal.sex) FROM Animal WHERE sex = :gender")
		@Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :gender")
		Integer countByGender(@Param("gender") Enum<Gender> theGender);
		
		//méthode qui renvoie un booléen si l’animal fourni « appartient » à au moins une personne
		@Query("SELECT CASE WHEN (COUNT(a)>0) THEN true ELSE false END FROM Animal a WHERE a.name = :petName AND a.maitres IS NOT EMPTY")
		Boolean hasOwner(@Param("petName") String thePetName);
}
