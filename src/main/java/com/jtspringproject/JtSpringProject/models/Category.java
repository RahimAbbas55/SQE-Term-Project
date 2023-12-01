package com.jtspringproject.JtSpringProject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name="CATEGORY")
public class Category {
	private int category_counter = 0;
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	public Category(){
		this.id = category_counter++;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return id == category.id &&
				Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}