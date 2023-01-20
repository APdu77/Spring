package fr.diginamic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.model.Species;

@Repository
public interface SpeciesRepository extends CrudRepository<Species, Integer>{
	
	Species findFirstSpeciesByCommonName(String commonName);
	
	List<Species> findSpeciesByLatinNameContainingIgnoreCase(String part);
	
	//méthode @Query qui va aller chercher toutes les Species, ordonnées par nom commun ascendant
	@Query("FROM Species ORDER BY latinName")
	List<Species> orderByLatinName();
	
	//méthode @Query qui retourne les Species avec un nom commun LIKE le paramètre fourni
	//@Query("FROM Species WHERE commonName LIKE %:partOfNameWanted%")
	@Query("FROM Species s WHERE s.commonName LIKE %:partOfNameWanted%")
	List<Species> findByCommonName(@Param("partOfNameWanted") String ThePartOfNameWanted);
	
//	@Query("FROM Species ORDER BY :dataWanted")
//	List<Species> orderBy2(@Param("dataWanted")String myData);
}
