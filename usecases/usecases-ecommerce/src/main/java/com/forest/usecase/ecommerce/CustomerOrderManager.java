package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.CustomerOrder;

public interface CustomerOrderManager {

	public abstract CustomerOrder getCustomerOrder(Integer id);

	public abstract void removeCustomerOrder(CustomerOrder customerOrder);

	public abstract int count();

	public abstract List<CustomerOrder> getAll();

	public abstract List<CustomerOrder> getAllInRange(int... range);

	public abstract List<CustomerOrder> getOrderByCustomerId(Integer id);

	public abstract List<CustomerOrder> getOrderByStatus(int status);

	public abstract void setOrderStatus(int orderId, String newStatus);

	public abstract void cancelCustomerOrder(int customerOrderId);

	public abstract void shippingCanceled(int customerOrderId);

	public abstract void orderShipped(int customerOrderId);

	public abstract void itemsPacked(int customerOrderId);

	public abstract void paymentCanceled(int customerOrderId);

	public abstract void paymentReceived(int customerOrderId);

	public abstract void processOrder(int customerOrderId);

	public abstract void updateNewOrder(CustomerOrder customerOrder);

	public abstract void openNewOrder(CustomerOrder customerOrder);


}