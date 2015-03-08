/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.forest.store.wiring.catalog;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.model.Category;
import com.forest.persistence.entity.CategoryEntity;
import com.forest.persistence.jpa.CategoryPersistenceJPA;
import com.forest.usecase.catalog.AbstractBaseCategoryManager;
import com.forest.usecase.catalog.persistence.CategoryPersistence;

/**
 * 
 * @author markito
 */
@Stateless
public class CategoryBean extends AbstractBaseCategoryManager {

	@PersistenceContext(unitName = "forestPU")
	private EntityManager entityManager;

	private CategoryPersistenceJPA categoryPersistance = new CategoryPersistenceJPA();

	@PostConstruct
	public void init() {
		categoryPersistance.setEntityManager(entityManager);
	}

	public Category newCategoryInstance() {
		return new CategoryEntity();
	}

	@Override
	protected CategoryPersistence getCategoryPersistence() {
		return categoryPersistance;
	}

}
