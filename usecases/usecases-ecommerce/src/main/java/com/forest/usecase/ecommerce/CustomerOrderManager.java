package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.CustomerOrder;

public interface CustomerOrderManager {

	public abstract void createCustomerOrder(CustomerOrder customerOrder);

	public abstract CustomerOrder getCustomerOrder(Integer id);

	public abstract void updateCustomerOrder(CustomerOrder customerOrder);

	public abstract void removeCustomerOrder(CustomerOrder customerOrder);

	public abstract int count();

	public abstract List<CustomerOrder> getAll();

	public abstract List<CustomerOrder> getAllInRange(int... range);

	public abstract List<CustomerOrder> getOrderByCustomerId(Integer id);

	public abstract List<CustomerOrder> getOrderByStatus(int status);

	public abstract void setOrderStatus(int orderId, String newStatus);

}