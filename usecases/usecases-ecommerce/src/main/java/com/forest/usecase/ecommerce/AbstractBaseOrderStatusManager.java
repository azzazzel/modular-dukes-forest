package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.OrderStatus;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;

public abstract class AbstractBaseOrderStatusManager implements OrderStatusManager  {

	protected abstract OrderStatusPersistence getOrderStatusPersistence();
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#createOrderStatus(com.forest.model.OrderStatus)
	 */
	@Override
	public void createOrderStatus(OrderStatus orderStatus) {
		getOrderStatusPersistence().createOrderStatus(orderStatus);

	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#getOrderStatus(java.lang.Integer)
	 */
	@Override
	public OrderStatus getOrderStatus(Integer id) {
		return getOrderStatusPersistence().getOrderStatus(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#updateOrderStatus(com.forest.model.OrderStatus)
	 */
	@Override
	public void updateOrderStatus(OrderStatus orderStatus) {
		getOrderStatusPersistence().updateOrderStatus(orderStatus);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#removeOrderStatus(com.forest.model.OrderStatus)
	 */
	@Override
	public void removeOrderStatus(OrderStatus orderStatus) {
		getOrderStatusPersistence().removeOrderStatus(orderStatus);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#count()
	 */
	@Override
	public int count() {
		return getOrderStatusPersistence().count();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#getAll()
	 */
	@Override
	public List<OrderStatus> getAll() {
		return getOrderStatusPersistence().findAll();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#getAllInRange(int)
	 */
	@Override
	public List<OrderStatus> getAllInRange(int... range) {
		return getOrderStatusPersistence().findRange(range);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderStatusManager#getStatusByName(java.lang.String)
	 */
	@Override
	public OrderStatus getStatusByName(String status) {
		return getOrderStatusPersistence().findOrderStatusByName(status);
	}

}
