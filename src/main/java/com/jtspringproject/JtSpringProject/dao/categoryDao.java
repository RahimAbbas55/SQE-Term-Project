package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Category;

@Repository
public class categoryDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Category addCategory(String name) {
		Category category = new Category();
		category.setName(name);
		this.sessionFactory.getCurrentSession().saveOrUpdate(category);
		return category;
	}

	@Transactional
	public List<Category> getCategories() {
		return this.sessionFactory.getCurrentSession().createQuery("from Category").list();
	}

	@Transactional
	public boolean deleteCategory(int id) {
		Session session = this.sessionFactory.openSession();
		Category category = session.get(Category.class, id);

		if (category != null) {
			session.delete(category);
			session.close();
			return true;
		}
		session.close();
		return false;
	}

	@Transactional
	public Category updateCategory(int id, String name) {
		Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
		if (category != null) {
			category.setName(name);
			this.sessionFactory.getCurrentSession().update(category);
		}
		return category;
	}

	@Transactional
	public Category getCategory(int id) {
		return this.sessionFactory.getCurrentSession().get(Category.class, id);
	}
}
