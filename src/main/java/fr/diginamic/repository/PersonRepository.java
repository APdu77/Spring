package fr.diginamic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>,PersonRepositoryCustom{
	List<Person> findPersonByFirstnameOrLastname(String lastname, String firstname);
	
	List<Person> findPersonByAgeBetween(int ageMin, int ageMax);
	
	List<Person> findPersonByAgeGreaterThanEqualAndAgeLessThanEqual(int ageMin, int ageMax);
	
	//méthode qui va chercher les Personnes dont l’âge est entre « age min » et « age max »
	@Query("FROM Person WHERE age >= :ageMin AND age <= :ageMax")
	List<Person> findByAgeField(
			@Param("ageMin") int theAgeMin,
			@Param("ageMax") int theAgeMax);
	
	//méthode qui va chercher toutes les Personnes qui possèdent l’animal donné en paramètre
	@Query("FROM Person INNER JOIN compagnie WHERE name = :petName")
	//@Query("FROM Person p INNER JOIN p.compagnie c WHERE c.name = :petName")
	List<Person> findByPetName(@Param("petName") String thePetName);
	
	
}
