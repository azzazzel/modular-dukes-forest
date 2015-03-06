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

package com.forest.ejb;

import com.forest.entity.OrderStatusEntity;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author markito
 */
@Stateless
public class OrderStatusBean extends AbstractFacade<OrderStatusEntity> implements Serializable {
    
    private static final long serialVersionUID = 5199196331433553237L;
    @PersistenceContext(unitName="forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderStatusBean() {
        super(OrderStatusEntity.class);
    }

    public OrderStatusEntity getStatusByName(String status) {
        Query createNamedQuery = getEntityManager().createNamedQuery("OrderStatus.findByStatus");

        //SELECT o FROM OrderStatus o WHERE o.status = :status
        createNamedQuery.setParameter("status", status);

        return (OrderStatusEntity) createNamedQuery.getSingleResult();
}
    }
