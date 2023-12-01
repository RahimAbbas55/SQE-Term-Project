package com.jtspringproject.JtSpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.categoryDao;
import com.jtspringproject.JtSpringProject.models.Category;

@Service
public class categoryService {
	@Autowired
	private categoryDao categoryDaoo;

	public Category addCategory(String name) {
		return this.categoryDaoo.addCategory(name);
	}

	public List<Category> getCategories() {
		return this.categoryDaoo.getCategories();
	}

	public boolean deleteCategory(int id) {
		return this.categoryDaoo.deleteCategory(id);
	}

	public Category updateCategory(int id, String name) {
		return this.categoryDaoo.updateCategory(id, name);
	}

	public Category getCategory(int id) {
		return this.categoryDaoo.getCategory(id);
	}
}
