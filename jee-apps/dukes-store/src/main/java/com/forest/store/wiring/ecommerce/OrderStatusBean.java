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

package com.forest.store.wiring.ecommerce;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.forest.model.OrderStatus;
import com.forest.persistence.entity.OrderStatusEntity;
import com.forest.persistence.jpa.OrderStatusPersistenceJPA;
import com.forest.usecase.ecommerce.AbstractBaseOrderStatusManager;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;

/**
 * 
 * @author markito
 */
@Stateless
public class OrderStatusBean extends AbstractBaseOrderStatusManager {

	@PersistenceContext(unitName = "forestPU")
	private EntityManager entityManager;

	private OrderStatusPersistenceJPA orderStatusPersistance = new OrderStatusPersistenceJPA();

	@PostConstruct
	public void init() {
		orderStatusPersistance.setEntityManager(entityManager);
	}

	public OrderStatus newOrderStatusInstance() {
		return new OrderStatusEntity();
	}

	@Override
	protected OrderStatusPersistence getOrderStatusPersistence() {
		return orderStatusPersistance;
	}

}
