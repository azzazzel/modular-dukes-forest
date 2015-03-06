/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import com.forest.entity.OrderDetailEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author markito
 */
@Stateless
public class OrderDetailBean extends AbstractFacade<OrderDetailEntity> {
    @PersistenceContext(unitName="forestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailBean() {
        super(OrderDetailEntity.class);
    }

    /**
     * Example of usage of NamedQuery
     * @param orderId
     * @return 
     */
    public List<OrderDetailEntity> findOrderDetailByOrder(int orderId) {
        List<OrderDetailEntity> details = getEntityManager().createNamedQuery("OrderDetail.findByOrderId").setParameter("orderId", orderId).getResultList();
        
        return details;
    }
}
