package com.forest.usecase.ecommerce.persistence;

import java.util.List;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderStatus;

public interface CustomerOrderPersistence {
	
	public void createCustomerOrder(CustomerOrder customerOrder);

	public void updateCustomerOrder(CustomerOrder customerOrder);

	public void removeCustomerOrder(CustomerOrder customerOrder);
	
	public CustomerOrder getCustomerOrder(int id);
	
	public int count();
	
    public List<CustomerOrder> findAll();

    public List<CustomerOrder> findRange(int... range);

    public List<CustomerOrder> getOrdersByCustomerId(Integer id);

	public List<CustomerOrder> getOrdersByStatus(OrderStatus status);
	
}
