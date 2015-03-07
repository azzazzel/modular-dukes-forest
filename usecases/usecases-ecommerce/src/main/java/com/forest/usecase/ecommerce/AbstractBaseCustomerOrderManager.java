package com.forest.usecase.ecommerce;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderStatus;
import com.forest.usecase.ecommerce.persistence.CustomerOrderPersistence;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;
import com.forest.usecase.ecommerce.providers.PackingProvider;
import com.forest.usecase.ecommerce.providers.PaymentProvider;
import com.forest.usecase.ecommerce.providers.ShippingProvider;

public abstract class AbstractBaseCustomerOrderManager implements CustomerOrderManager {

	private static final Logger logger = Logger
			.getLogger(AbstractBaseCustomerOrderManager.class
					.getCanonicalName());

	protected abstract CustomerOrderPersistence getCustomerOrderPersistence();

	protected abstract OrderStatusPersistence getOrderStatusPersistence();

	protected abstract PaymentProvider getPaymentProvider();
	
	protected abstract PackingProvider getPackingProvider();
	
	protected abstract ShippingProvider getShippingProvider();

	
	@Override
	public CustomerOrder getCustomerOrder(Integer id) {
		return getCustomerOrderPersistence().getCustomerOrder(id);
	}

	@Override
	public void openNewOrder(CustomerOrder customerOrder) {
		customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.NEW.getStatus()));
		getCustomerOrderPersistence().createCustomerOrder(customerOrder);
		logger.log(Level.INFO, " --> New order with id {} created !!!", customerOrder.getId());
	}

	@Override
	public void updateNewOrder(CustomerOrder customerOrder) {
//		System.out.println("ORDER STATUS: " + getStatus(customerOrder));
//		System.out.println("EXPECTED STATUS: " + Status.NEW);
//		if (!Status.NEW.equals(getStatus(customerOrder))) {
//			throw new IllegalStateException("Can not update complete order!");
//		}
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
		logger.log(Level.INFO, " --> Order {0} updated !!!", customerOrder.getId());
	}

	@Override
	public void processOrder(int customerOrderId) {
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		if (getPaymentProvider().requestPayment(customerOrder)) {
			customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.PENDING_PAYMENT.getStatus()));
		}
		logger.log(Level.INFO, " --> Requested payment for order {0} !!!", customerOrderId);
	}
	
	
	@Override
	public void paymentReceived(int customerOrderId) {
		logger.log(Level.INFO, " --> Received payment for order {0} !!!", customerOrderId);
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		/* 
		 * Perhaps status needs to be changed to something like PAYED here! 
		 */
		if (getPackingProvider().requestPacking(customerOrder)) {
			logger.log(Level.INFO, " --> Requested packing items for order {0} !!!", customerOrderId);
			/* 
			 * Perhaps status needs to be changed to something like PENDING_PACKING here! 
			 */
		}
	}

	@Override
	public void paymentCanceled(int customerOrderId) {
		logger.log(Level.INFO, " --> Canceled payment for order {0} !!!", customerOrderId);
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.CANCELLED_PAYMENT.getStatus()));
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
	}

	@Override
	public void itemsPacked(int customerOrderId) {
		logger.log(Level.INFO, " --> Items for order {0} are now packed!!!", customerOrderId);
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.READY_TO_SHIP.getStatus()));
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
		if (getShippingProvider().requestShipping(customerOrder)) {
			logger.log(Level.INFO, " --> Requested shipping of order {0} !!!", customerOrderId);
			/* 
			 * Perhaps status needs to be changed to something like PENDING_SHIPPING here! 
			 */
		}
	}

	@Override
	public void orderShipped(int customerOrderId) {
		logger.log(Level.INFO, " --> Order {0} was shipped !!!", customerOrderId);
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.SHIPPED.getStatus()));
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
	}

	@Override
	public void shippingCanceled(int customerOrderId) {
		logger.log(Level.INFO, " --> Canceled shipping for order {0} !!!", customerOrderId);
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.CANCELLED_MANUAL.getStatus()));
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
	}	

	@Override
	public void removeCustomerOrder(CustomerOrder customerOrder) {
		Status status = getStatus(customerOrder);
		if (Status.PENDING_PAYMENT.equals(status) || Status.READY_TO_SHIP.equals(status)) {
			throw new IllegalStateException("Can not delete pending orders! Please cancel it first!");
		}
		getCustomerOrderPersistence().removeCustomerOrder(customerOrder);
		logger.log(Level.INFO, " --> Order {0} was removed !!!", customerOrder.getId());
	}
	
	@Override
	public void cancelCustomerOrder(int customerOrderId) {
		CustomerOrder customerOrder = getCustomerOrderPersistence().getCustomerOrder(customerOrderId);
		Status currentStatus = getStatus(customerOrder);

		switch (currentStatus) {
		
		case SHIPPED: 
			throw new IllegalStateException("Can not cancel shipped order!");
		case CANCELLED_MANUAL: 
		case CANCELLED_PAYMENT: 
			throw new IllegalStateException("Can not cancel already canceled order!");
		case PENDING_PAYMENT: 
			getPaymentProvider().cancelPaymentRequest(customerOrderId);
			break;
//		case PENDING_PACKING: 
//			packingProvider.cancelPackingRequest(customerOrderId);
//			break;
		case READY_TO_SHIP: 
			getShippingProvider().cancelShippingRequest(customerOrderId);
			break;
		default:
			customerOrder.setOrderStatus(getOrderStatusPersistence().getOrderStatus(Status.CANCELLED_MANUAL.getStatus()));
			getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
			logger.log(Level.INFO, " --> Order {0} was canceled !!!", customerOrder.getId());
		}
		
		
	}

	private Status getStatus(CustomerOrder customerOrder) {
		Status currentStatus = Status.values()[customerOrder.getOrderStatus().getId()];
		return currentStatus;
	}


	
	@Override
	public int count() {
		return getCustomerOrderPersistence().count();
	}

	@Override
	public List<CustomerOrder> getAll() {
		return getCustomerOrderPersistence().findAll();
	}

	@Override
	public List<CustomerOrder> getAllInRange(int... range) {
		return getCustomerOrderPersistence().findRange(range);
	}

	
    @Override
	public List<CustomerOrder> getOrderByCustomerId(Integer id) {
		return getCustomerOrderPersistence().getOrdersByCustomerId(id);
    }
  
    @Override
	public List<CustomerOrder> getOrderByStatus(int status) {
        OrderStatus orderStatus = getOrderStatusPersistence().getOrderStatus(status);
        return getCustomerOrderPersistence().getOrdersByStatus(orderStatus);
    }
	
    enum Status {

    	NEW(1),
        PENDING_PAYMENT(2),
        READY_TO_SHIP(3),
        SHIPPED(4),
        CANCELLED_PAYMENT(5),
        CANCELLED_MANUAL(6);
        private int status;

        private Status(int pStatus) {
            status = pStatus;
        }

        public int getStatus() {
            return status;
        }
    }

}
