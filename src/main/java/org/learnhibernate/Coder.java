package org.learnhibernate;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coder {
	@Id
	private int coder_id;
	private String name;
	private String tech;
	@OneToMany (mappedBy = "coder")
	private List<Laptop> laptops;

	public int getCoder_id() {
		return coder_id;
	}

	public void setCoder_id(int coder_id) {
		this.coder_id = coder_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public List<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(List<Laptop> laptops) {
		this.laptops = laptops;
	}

	@Override
	public String toString() {
		return "Coder{" +
				"coder_id=" + coder_id +
				", name='" + name + '\'' +
				", tech='" + tech + '\'' +
				", laptops=" + laptops +
				'}';
	}
}
