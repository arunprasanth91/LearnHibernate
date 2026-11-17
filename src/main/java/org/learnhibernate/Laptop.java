package org.learnhibernate;

import jakarta.persistence.*;

@Entity
public class Laptop {
	@Id
	private int id;
	private String brand;
	private String ram;
	private String processor;
	@ManyToOne
	@JoinColumn(name = "coder_id")
	private Coder coder;

	public Coder getCoder() {
		return coder;
	}

	public void setCoder(Coder coder) {
		this.coder = coder;
	}

	//	public Coder getCoder_id() {
//		return coder_id;
//	}
//
//	public void setCoder_id(Coder coder_id) {
//		this.coder_id = coder_id;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	@Override
	public String toString() {
		return "Laptop{" +
				"id=" + id +
				", brand='" + brand + '\'' +
				", ram='" + ram + '\'' +
				", processor='" + processor + '\'' +
				'}';

	}


}
