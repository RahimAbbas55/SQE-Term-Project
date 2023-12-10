package com.jtspringproject.JtSpringProject.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.models.Product;

@Repository
public class productDao {
	@Autowired
    private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Transactional
	public List<Product> getProducts(){
		return this.sessionFactory.getCurrentSession().createQuery("from PRODUCT").list();
	}

	@Transactional
	public Product addProduct(Product product) {
		this.sessionFactory.getCurrentSession().save(product);
		return product;
	}

	@Transactional
	public Product getProduct(int id) {
		return this.sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Transactional
	public Product updateProduct(Product product) {
		this.sessionFactory.getCurrentSession().update(product);
		return product;
	}

	@Transactional
	public Boolean deletProduct(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Object persistenceInstance = session.load(Product.class, id);

		if (persistenceInstance != null) {
			session.delete(persistenceInstance);
			return true;
		}
		return false;
	}

	@Transactional
	public List<Product> getProductsByCategory(String categoryName) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("FROM PRODUCT WHERE category.name = :categoryName", Product.class)
				.setParameter("categoryName", categoryName)
				.getResultList();
	}

	@Transactional
	public List<Product> getProducts(String sortBy) {
		String queryString = "FROM PRODUCT p";

		if ("price".equals(sortBy)) {
			queryString += " ORDER BY p.price DESC";
		} else if ("name".equals(sortBy)) {
			queryString += " ORDER BY p.name";
		}

		return this.sessionFactory.getCurrentSession().createQuery(queryString, Product.class).list();
	}



}
