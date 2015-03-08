/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.wiring.ecommerce;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.forest.events.OrderEvent;
import com.forest.model.CustomerOrder;
import com.forest.model.OrderDetail;
import com.forest.persistence.entity.CustomerOrderEntity;
import com.forest.persistence.entity.OrderDetailEntity;
import com.forest.persistence.entity.OrderDetailPKEntity;
import com.forest.persistence.jpa.OrderPersistenceJPA;
import com.forest.persistence.jpa.OrderStatusPersistenceJPA;
import com.forest.usecase.ecommerce.AbstractBaseCustomerOrderManager;
import com.forest.usecase.ecommerce.persistence.CustomerOrderPersistence;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;
import com.forest.usecase.ecommerce.providers.PackingProvider;
import com.forest.usecase.ecommerce.providers.PaymentProvider;
import com.forest.usecase.ecommerce.providers.ShippingProvider;

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

    @EJB
    PaymentAndPackingProvider paymentAndPackingProvider;
    
    @EJB
    ShippingProvider shippingProvider;

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

    public OrderDetail newOrderDetailInstance() {
    	return new OrderDetailEntity();
	}
    
    public OrderDetailPKEntity createOrderDetailPK(int orderId, int personId) {
    	return new OrderDetailPKEntity(orderId, personId);
	}
    
	@Override
	protected CustomerOrderPersistence getCustomerOrderPersistence() {
		return orderPersistance;
	}


	@Override
	protected OrderStatusPersistence getOrderStatusPersistence() {
		return orderStatusPersistance;
	}    
    
	
	@Override
	protected PackingProvider getPackingProvider() {
		return paymentAndPackingProvider;
	}
	
	
	@Override
	protected PaymentProvider getPaymentProvider() {
		return paymentAndPackingProvider;
	}
	
	@Override
	protected ShippingProvider getShippingProvider() {
		return shippingProvider;
	}
	
	/**
	 * *************************************************************************
	 * * Business methods
	 * *******************************************************
	 * ********************
	 */

	@GET
	@Produces({ "application/xml", "application/json" })
	public List<OrderEvent> getOrderEventsByStatus(@QueryParam("status") int status) {
		
		List<OrderEvent> result = new LinkedList<OrderEvent>();
		for (CustomerOrder customerOrder: super.getOrderByStatus(status)) {
			OrderEvent orderEvent = new OrderEvent();
			orderEvent.setAmount(customerOrder.getAmount());
			orderEvent.setCustomerID(customerOrder.getCustomer().getId());
			orderEvent.setDateCreated(customerOrder.getDateCreated());
			orderEvent.setOrderID(customerOrder.getId());
			orderEvent.setStatusID(customerOrder.getOrderStatus().getId());
			result.add(orderEvent); 
		}
		
		return result;
	}

	@PUT
	@Path("/shipping/{orderId}")
	@Produces({ "application/xml", "application/json" })
	public void setOrderStatus(@PathParam("orderId") int orderId,
			String newStatus) {
		
		if ("SHIPPED".equalsIgnoreCase(newStatus)) {
			orderShipped(orderId);
		} else if ("CANCELED".equalsIgnoreCase(newStatus)) {
			shippingCanceled(orderId);
		}
		
	}

}
