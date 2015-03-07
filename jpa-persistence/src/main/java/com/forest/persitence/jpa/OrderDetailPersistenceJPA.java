package com.forest.persitence.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.forest.entity.OrderDetailEntity;
import com.forest.model.OrderDetail;
import com.forest.model.OrderDetailPK;
import com.forest.usecase.ecommerce.persistence.OrderDetailPersistence;

public class OrderDetailPersistenceJPA extends
		AbstractBasePersistence<OrderDetailEntity, OrderDetail> implements
		OrderDetailPersistence {

	public OrderDetailPersistenceJPA() {
		super(OrderDetailEntity.class);
	}

	private EntityManager entityManager;

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void createOrderDetail(OrderDetail orderDetail) {
		create(orderDetail);
	}

	@Override
	public void updateOrderDetail(OrderDetail orderDetail) {
		update(orderDetail);
	}

	@Override
	public void removeOrderDetail(OrderDetail orderDetail) {
		remove(orderDetail);
	}

	@Override
	public OrderDetail getOrderDetail(OrderDetailPK id) {
		return find(id);
	}

	@Override
	public List<OrderDetail> findOrderDetailByOrder(int orderId) {
		List<OrderDetail> details = getEntityManager()
				.createNamedQuery("OrderDetail.findByOrderId")
				.setParameter("orderId", orderId).getResultList();

		return details;
	}

}
