/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.wiring.catalog;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.model.Product;
import com.forest.persistence.entity.ProductEntity;
import com.forest.persistence.jpa.ProductPersistenceJPA;
import com.forest.usecase.catalog.AbstractBaseProductManager;
import com.forest.usecase.catalog.persistence.ProductPersistence;

/**
 * 
 * @author markito
 */
@Stateless
public class ProductBean extends AbstractBaseProductManager {

	@PersistenceContext(unitName = "forestPU")
	private EntityManager entityManager;

	private ProductPersistenceJPA productPersistance = new ProductPersistenceJPA();

	@PostConstruct
	public void init() {
		productPersistance.setEntityManager(entityManager);
	}

	public Product newProductInstance() {
		return new ProductEntity();
	}

	@Override
	protected ProductPersistence getProductPersistence() {
		return productPersistance;
	}

}
