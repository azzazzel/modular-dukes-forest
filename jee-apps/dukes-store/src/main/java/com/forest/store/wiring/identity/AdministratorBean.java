/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.wiring.identity;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.model.Administrator;
import com.forest.persistence.entity.AdministratorEntity;
import com.forest.persistence.jpa.AdministratorPersistenceJPA;
import com.forest.persistence.jpa.GroupPersistenceJPA;
import com.forest.usecase.identity.AbstractBaseAdministratorManager;
import com.forest.usecase.identity.persistence.AdministratorPersistence;
import com.forest.usecase.identity.persistence.GroupPersistence;

/**
 * 
 * @author ievans
 */
@Stateless
public class AdministratorBean extends AbstractBaseAdministratorManager {

	@PersistenceContext(unitName = "forestPU")
	private EntityManager entityManager;

	private AdministratorPersistenceJPA administratorPersistence = new AdministratorPersistenceJPA();

	private GroupPersistenceJPA groupPersistance = new GroupPersistenceJPA();
	
    @PostConstruct
    public void init() {
		administratorPersistence.setEntityManager(entityManager);
		groupPersistance.setEntityManager(entityManager);
	}

	public Administrator newAdministratorInstance() {
		return new AdministratorEntity();
	}

	@Override
	protected AdministratorPersistence getAdministratorPersistence() {
		return administratorPersistence;
	}

	@Override
	protected GroupPersistence getGroupPersistence() {
		return groupPersistance;
	}
}
