/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import com.forest.entity.AdministratorEntity;
import com.forest.entity.GroupsEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ievans
 */
@Stateless
public class AdministratorBean extends AbstractFacade<AdministratorEntity> {
    @PersistenceContext(unitName = "forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorBean() {
        super(AdministratorEntity.class);
    }
    
    @Override
    public void create(AdministratorEntity admin) {
        GroupsEntity adminGroup = (GroupsEntity) em.createNamedQuery("Groups.findByName")
                .setParameter("name", "Administrator")
                .getSingleResult();
        admin.getGroupsList().add(adminGroup);
        adminGroup.getPersonList().add(admin);
        em.persist(admin);
        em.merge(adminGroup);
    }
    
    @Override
    public void remove(AdministratorEntity admin) {
        GroupsEntity adminGroup = (GroupsEntity) em.createNamedQuery("Groups.findByName")
                .setParameter("name", "Administrator")
                .getSingleResult();
        adminGroup.getPersonList().remove(admin);
        em.remove(em.merge(admin));
        em.merge(adminGroup);
    }
    
}
