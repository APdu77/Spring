package fr.diginamic.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Species {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50)
	@NotEmpty
	private String commonName;
	@Column(length = 120)
	@NotEmpty
	private String latinName;
	
	public Species() {
		super();
	}
	
	/**
	 * Relation esclave 1toMany avec la classe Animal
	 */
	@OneToMany(mappedBy="species")
	private	List<Animal> animaux = new ArrayList<Animal>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.latinName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}
	
	
	
}
