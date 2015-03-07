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

import com.forest.entity.GroupsEntity;
import com.forest.model.Group;
import com.forest.persitence.jpa.GroupPersistenceJPA;
import com.forest.usecase.identity.AbstractBaseGroupManager;
import com.forest.usecase.identity.persistence.GroupPersistence;

/**
 *
 * @author ievans
 */
@Stateless
public class GroupsBean extends AbstractBaseGroupManager {

    @PersistenceContext(unitName="forestPU")
    private EntityManager entityManager;

    private GroupPersistenceJPA groupPersistance = new GroupPersistenceJPA();

    @PostConstruct
    public void init() {
    	groupPersistance.setEntityManager(entityManager);
    }

    public Group newGroupInstance() {
    	return new GroupsEntity();
	}

	@Override
	protected GroupPersistence getGroupPersistence() {
		return groupPersistance;
	}

}
