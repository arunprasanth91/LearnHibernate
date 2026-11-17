package org.learnhibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	private int rollNo;
	private String name;
	private int age;

	@Override
	public String toString() {
		return "Student{" +
				"rollNo='" + rollNo + '\'' +
				", name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
