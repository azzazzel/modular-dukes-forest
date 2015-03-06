package com.forest.usecase.ecommerce.persistence;

import java.util.List;

import com.forest.model.OrderDetail;
import com.forest.model.OrderDetailPK;


public interface OrderDetailPersistence {
	
	public void createOrderDetail(OrderDetail orderDetail);

	public void updateOrderDetail(OrderDetail orderDetail);

	public void removeOrderDetail(OrderDetail orderDetail);
	
	public OrderDetail getOrderDetail(OrderDetailPK id);
	
	public int count();
	
    public List<OrderDetail> findAll();

    public List<OrderDetail> findRange(int... range);

	public List<OrderDetail> findOrderDetailByOrder(int orderId);
}
