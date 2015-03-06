package com.forest.usecase.ecommerce;

import java.util.List;

import com.forest.model.OrderDetail;
import com.forest.model.OrderDetailPK;
import com.forest.usecase.ecommerce.persistence.OrderDetailPersistence;

public abstract class AbstractBaseOrderDetailManager implements OrderDetailManager {

	protected abstract OrderDetailPersistence getOrderDetailPersistence();
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#createOrderDetail(com.forest.model.OrderDetail)
	 */
	@Override
	public void createOrderDetail(OrderDetail orderDetail) {
		getOrderDetailPersistence().createOrderDetail(orderDetail);
	}
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#getOrderDetail(com.forest.model.OrderDetailPK)
	 */
	@Override
	public OrderDetail getOrderDetail(OrderDetailPK id) {
		return getOrderDetailPersistence().getOrderDetail(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#updateOrderDetail(com.forest.model.OrderDetail)
	 */
	@Override
	public void updateOrderDetail(OrderDetail orderDetail) {
		getOrderDetailPersistence().updateOrderDetail(orderDetail);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#removeOrderDetail(com.forest.model.OrderDetail)
	 */
	@Override
	public void removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetailPersistence().removeOrderDetail(orderDetail);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#count()
	 */
	@Override
	public int count() {
		return getOrderDetailPersistence().count();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#getAll()
	 */
	@Override
	public List<OrderDetail> getAll() {
		return getOrderDetailPersistence().findAll();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#getAllInRange(int)
	 */
	@Override
	public List<OrderDetail> getAllInRange(int... range) {
		return getOrderDetailPersistence().findRange(range);
	}
	
    /* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.OrderDetailManager#findOrderDetailByOrder(int)
	 */
    @Override
	public List<OrderDetail> findOrderDetailByOrder(int orderId) {
		return getOrderDetailPersistence().findOrderDetailByOrder(orderId);
    }

}
