package org.learnhibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "alien" )
public class Alien {
	/*
	In this class we have demonstrated how to have a different table name and column
	and, how to exclude a column by using transient keyword.
	 */
	@Id
	@Column(name = "alien_id")
	private int alienId;
	@Column(name = "alien_name")
	private String alienName;
	private String tech;
	@Transient
	private String alienAge;
	@Column(name = "alien_laptop")
	private Alien_Laptop alienLaptop;

	public int getAlienId() {
		return alienId;
	}

	public void setAlienId(int alienId) {
		this.alienId = alienId;
	}

	public String getAlienName() {
		return alienName;
	}

	public void setAlienName(String alienName) {
		this.alienName = alienName;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public Alien_Laptop getLaptop() {
		return alienLaptop;
	}

	public void setLaptop(Alien_Laptop alienLaptop) {
		this.alienLaptop = alienLaptop;
	}

	@Override
	public String toString() {
		return "Alien{" +
				"alienId=" + alienId +
				", alienName='" + alienName + '\'' +
				", tech='" + tech + '\'' +
				", alienAge='" + alienAge + '\'' +
				", laptop=" + alienLaptop +
				'}';
	}
}

// Embeddable means don't create as separate table instead it is part of Alien class.
@Embeddable
class Alien_Laptop {
	private String brand;
	private String ram;
	private String processor;

	public String getBrand() {
		return brand;
	}

	public Alien_Laptop setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public String getRam() {
		return ram;
	}

	public Alien_Laptop setRam(String ram) {
		this.ram = ram;
		return this;
	}

	public String getProcessor() {
		return processor;
	}

	public Alien_Laptop setProcessor(String processor) {
		this.processor = processor;
		return this;
	}

	@Override
	public String toString() {
		return "Laptop{" +
				"brand='" + brand + '\'' +
				", ram='" + ram + '\'' +
				", processor='" + processor + '\'' +
				'}';
	}
}
