package fr.diginamic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.diginamic.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Column(length = 25)
	private String name;
	private String color;
	@Enumerated(EnumType.STRING)
	private Gender sex;

	/**
	 * Relation esclave ManyToMany avec la classe Person
	 */
//	@ManyToMany(mappedBy = "compagnie",cascade = CascadeType.PERSIST)
//	private List<Person> maitres = new ArrayList<Person>();

	public Animal() {
		super();
	}

	/**
	 * Relation maitre ManyTo1 avec la classe Species
	 */
	@ManyToOne 
	@JoinColumn(name = "species_id")
	@NotNull
	@JsonIgnoreProperties("animaux")
	private Species species;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender genre) {
		this.sex = genre;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species specie) {
		this.species = specie;
	}

	
//	public void setMaitres(List<Person> maitres) {
//		this.getMaitres().clear();
//		this.getMaitres().addAll(maitres);
//	}

}
