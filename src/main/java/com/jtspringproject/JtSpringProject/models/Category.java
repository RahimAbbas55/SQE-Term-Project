package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;

@Entity(name="CATEGORY")
public class Category {
	private int category_counter = 0;
	@Id
	@Column(name = "category_id")
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
	
}
