/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.wiring.ecommerce;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.forest.events.OrderEvent;
import com.forest.model.CustomerOrder;
import com.forest.store.qualifiers.New;
import com.forest.store.qualifiers.Paid;
import com.forest.store.wiring.ecommerce.PaymentAndPackingProvider;

/**
 * 
 * @author markito
 */
@Named("EventDisptacherBean")
@Stateless
public class EventDispatcherBean implements PaymentAndPackingProvider {

	private static final Logger logger = Logger
			.getLogger(EventDispatcherBean.class.getCanonicalName());

	@Inject
	@New
	Event<OrderEvent> paymentManager;

	@Inject
	@Paid
	Event<OrderEvent> packingManager;

	public boolean requestPacking(CustomerOrder customerOrder) {
		logger.log(Level.FINEST, "{0} Sending event from EJB", Thread
				.currentThread().getName());
		packingManager.fire(orderToEvent(customerOrder));
		return true;
	}

	public boolean requestPayment(CustomerOrder customerOrder) {
		logger.log(Level.FINEST, "{0} Sending event from EJB", Thread
				.currentThread().getName());
		paymentManager.fire(orderToEvent(customerOrder));
		return true;
	}

	@Override
	public boolean cancelPackingRequest(int customerOrderid) {
		/*
		 * a real provider would have to deal with this request but for this
		 * demo we'll simply ignore it
		 */
		return true;
	}

	@Override
	public boolean cancelPaymentRequest(int customerOrderid) {
		/*
		 * a real provider would have to deal with this request but for this
		 * demo we'll simply ignore it
		 */
		return true;
	}

	private OrderEvent orderToEvent(CustomerOrder order) {
		OrderEvent event = new OrderEvent();

		event.setAmount(order.getAmount());
		event.setCustomerID(order.getCustomer().getId());
		event.setDateCreated(order.getDateCreated());
		event.setStatusID(order.getOrderStatus().getId());
		event.setOrderID(order.getId());

		return event;
	}

}
