package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.OrderDetail;
import com.forest.model.OrderDetailPK;

public interface OrderDetailManager {

	public abstract void createOrderDetail(OrderDetail orderDetail);

	public abstract OrderDetail getOrderDetail(OrderDetailPK id);

	public abstract void updateOrderDetail(OrderDetail orderDetail);

	public abstract void removeOrderDetail(OrderDetail orderDetail);

	public abstract int count();

	public abstract List<OrderDetail> getAll();

	public abstract List<OrderDetail> getAllInRange(int... range);

	public abstract List<OrderDetail> findOrderDetailByOrder(int orderId);

}