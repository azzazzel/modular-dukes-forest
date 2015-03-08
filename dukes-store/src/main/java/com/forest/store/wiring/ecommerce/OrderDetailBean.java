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

import com.forest.entity.OrderDetailEntity;
import com.forest.model.OrderDetail;
import com.forest.model.OrderDetailPK;
import com.forest.persitence.jpa.OrderDetailPersistenceJPA;
import com.forest.usecase.ecommerce.AbstractBaseOrderDetailManager;
import com.forest.usecase.ecommerce.persistence.OrderDetailPersistence;

/**
 *
 * @author markito
 */
@Stateless
public class OrderDetailBean extends AbstractBaseOrderDetailManager {

	private static final String SEPARATOR = "#";
	private static final String SEPARATOR_ESCAPED = "\\#";
	
	@PersistenceContext(unitName="forestPU")
    private EntityManager entityManager;
	
	private OrderDetailPersistenceJPA orderDetailPersistance = new OrderDetailPersistenceJPA();
	
	@PostConstruct
	public void init() {
		orderDetailPersistance.setEntityManager(entityManager);
	}


	public OrderDetailPK getKey(String value) {
		com.forest.entity.OrderDetailPKEntity key;
		String values[] = value.split(SEPARATOR_ESCAPED);
		key = new com.forest.entity.OrderDetailPKEntity();
		key.setOrderId(Integer.parseInt(values[0]));
		key.setProductId(Integer.parseInt(values[1]));
		return key;
	}

	public String getStringKey(OrderDetailPK value) {
		StringBuilder sb = new StringBuilder();
		sb.append(value.getOrderId());
		sb.append(SEPARATOR);
		sb.append(value.getProductId());
		return sb.toString();
	}
	
    public OrderDetail newOrderDetailInstance() {
    	return new OrderDetailEntity();
	}


	@Override
	protected OrderDetailPersistence getOrderDetailPersistence() {
		return orderDetailPersistance;
	}

}
