package fr.diginamic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Min(value = 11)
	@Max(113)
	private int age;
	@Column(length = 50)
	@NotEmpty
	private String lastname;
	@Column(length = 50)
	@NotEmpty
	private String firstname;

	/**
	 * Relation maitre ManyToMany avec la classe Animal
	 */
	@ManyToMany
	@JoinTable(name = "person_animals", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "animals_id", referencedColumnName = "id"))
	private List<Animal> compagnie = new ArrayList<Animal>();

	public Person() {
		super();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.firstname + " " + this.lastname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public List<Animal> getCompagnie() {
		return compagnie;
	}

	public void setCompagnie(List<Animal> compagnie) {
		this.compagnie = compagnie;
	}

	public void removeAnimal(Animal animal) {
		this.compagnie.remove(animal);
	}
	
	public void addAnimal(Animal animal) {
		this.compagnie.add(animal);
	}
}
