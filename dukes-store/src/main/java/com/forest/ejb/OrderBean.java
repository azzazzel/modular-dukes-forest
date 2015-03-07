/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.forest.entity.CustomerOrderEntity;
import com.forest.model.CustomerOrder;
import com.forest.persitence.jpa.OrderPersistenceJPA;
import com.forest.persitence.jpa.OrderStatusPersistenceJPA;
import com.forest.usecase.ecommerce.AbstractBaseCustomerOrderManager;
import com.forest.usecase.ecommerce.persistence.CustomerOrderPersistence;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;

/**
 * OrderBean is an EJB exposed as RESTful service Provides methods to manipulate
 * order status and query orders based on specific status.
 * 
 * @author markito
 */
@Stateless
@Path("/orders")
public class OrderBean extends AbstractBaseCustomerOrderManager implements Serializable {

	private static final long serialVersionUID = -2407971550575800416L;

	@PersistenceContext(unitName = "forestPU")
	private EntityManager entityManager;


	private OrderStatusPersistenceJPA orderStatusPersistance = new OrderStatusPersistenceJPA();
	
	private OrderPersistenceJPA orderPersistance = new OrderPersistenceJPA();

	@PostConstruct
	public void init() {
		orderPersistance.setEntityManager(entityManager);
		orderStatusPersistance.setEntityManager(entityManager);
	}

	
    public CustomerOrder newCustomerOrderInstance() {
    	return new CustomerOrderEntity();
	}
    
	@Override
	protected CustomerOrderPersistence getCustomerOrderPersistence() {
		return orderPersistance;
	}


	@Override
	protected OrderStatusPersistence getOrderStatusPersistence() {
		return orderStatusPersistance;
	}    
    
	/**
	 * *************************************************************************
	 * * Business methods
	 * *******************************************************
	 * ********************
	 */

	@GET
	@Produces({ "application/xml", "application/json" })
	public List<CustomerOrder> getOrderByStatus(@QueryParam("status") int status) {
		return super.getOrderByStatus(status);
	}

	@PUT
	@Path("{orderId}")
	@Produces({ "application/xml", "application/json" })
	public void setOrderStatus(@PathParam("orderId") int orderId,
			String newStatus) {
		super.setOrderStatus(orderId, newStatus);
	}




}
