package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.OrderStatus;

public interface OrderStatusManager {

	public abstract void createOrderStatus(OrderStatus orderStatus);

	public abstract OrderStatus getOrderStatus(Integer id);

	public abstract void updateOrderStatus(OrderStatus orderStatus);

	public abstract void removeOrderStatus(OrderStatus orderStatus);

	public abstract int count();

	public abstract List<OrderStatus> getAll();

	public abstract List<OrderStatus> getAllInRange(int... range);

	public abstract OrderStatus getStatusByName(String status);

}