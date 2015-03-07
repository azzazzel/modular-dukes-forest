/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.entity.CustomerEntity;
import com.forest.model.Customer;
import com.forest.persitence.jpa.PersonPersistenceJPA;
import com.forest.usecase.identity.AbstractBasePersonManager;
import com.forest.usecase.identity.persistence.PersonPersistence;

/**
 *
 * @author markito
 */
@Stateless
public class UserBean extends AbstractBasePersonManager {

    @PersistenceContext(unitName="forestPU")
    private EntityManager entityManager;

    private PersonPersistenceJPA personPersistance = new PersonPersistenceJPA();

    @PostConstruct
    public void init() {
    	personPersistance.setEntityManager(entityManager);
    }

    public Customer newCustomerInstance() {
    	return new CustomerEntity();
	}

	@Override
	protected PersonPersistence getPersonPersistence() {
		return personPersistance;
	}


}
