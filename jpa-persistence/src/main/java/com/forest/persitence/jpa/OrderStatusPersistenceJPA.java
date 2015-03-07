package com.forest.persitence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.forest.entity.OrderStatusEntity;
import com.forest.model.OrderStatus;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;

public class OrderStatusPersistenceJPA extends
		AbstractBasePersistence<OrderStatusEntity, OrderStatus> implements
		OrderStatusPersistence {

	public OrderStatusPersistenceJPA() {
		super(OrderStatusEntity.class);
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
	public void createOrderStatus(OrderStatus orderStatus) {
		create(orderStatus);
	}

	@Override
	public void updateOrderStatus(OrderStatus orderStatus) {
		update(orderStatus);
	}

	@Override
	public void removeOrderStatus(OrderStatus orderStatus) {
		remove(orderStatus);
	}

	@Override
	public OrderStatus getOrderStatus(int id) {
		return find(id);
	}

	@Override
	public OrderStatus findOrderStatusByName(String status) {
		Query createNamedQuery = getEntityManager().createNamedQuery(
				"OrderStatus.findByStatus");

		// SELECT o FROM OrderStatus o WHERE o.status = :status
		createNamedQuery.setParameter("status", status);

		return (OrderStatus) createNamedQuery.getSingleResult();
	}

}
