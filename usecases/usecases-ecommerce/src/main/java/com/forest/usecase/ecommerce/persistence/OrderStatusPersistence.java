package com.forest.usecase.ecommerce.persistence;

import java.util.List;

import com.forest.model.OrderStatus;

public interface OrderStatusPersistence {
	
	public void createOrderStatus(OrderStatus orderStatus);

	public void updateOrderStatus(OrderStatus orderStatus);

	public void removeOrderStatus(OrderStatus orderStatus);
	
	public OrderStatus getOrderStatus(int id);
	
	public int count();
	
    public List<OrderStatus> findAll();

    public List<OrderStatus> findRange(int... range);

    public OrderStatus findOrderStatusByName(String status);
}
