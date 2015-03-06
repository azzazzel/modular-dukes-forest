/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import com.forest.entity.CustomerEntity;
import com.forest.entity.PersonEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author markito
 */
@Stateless
public class UserBean extends AbstractFacade<CustomerEntity> {

    @PersistenceContext(unitName="forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Create a new user verifying if the user already exists
     * TODO: Create custom exceptions ?
     * @param customer
     * @return 
     */
    public boolean createUser(CustomerEntity customer) {

        // check if user exists
        if (getUserByEmail(customer.getEmail()) == null) {
            super.create(customer);
            return true;
        } else {
            return false;
        }
    }

    public PersonEntity getUserByEmail(String email) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (PersonEntity) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }

    public UserBean() {
        super(CustomerEntity.class);
    }
}
