package com.forest.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderStatus;
import com.forest.persistence.entity.CustomerOrderEntity;
import com.forest.usecase.ecommerce.persistence.CustomerOrderPersistence;

public class OrderPersistenceJPA extends AbstractBasePersistence<CustomerOrderEntity, CustomerOrder> implements CustomerOrderPersistence {

    public OrderPersistenceJPA() {
		super(CustomerOrderEntity.class);
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
	public void createCustomerOrder(CustomerOrder product) {
		create(product);
	}

	@Override
	public void updateCustomerOrder(CustomerOrder product) {
		update(product);
	}

	@Override
	public void removeCustomerOrder(CustomerOrder product) {
		remove(product);
	}

	@Override
	public CustomerOrder getCustomerOrder(int id) {
		return find(id);
	}

	@Override
	public List<CustomerOrder> getOrdersByCustomerId(Integer id) {
		Query createNamedQuery = getEntityManager().createNamedQuery("CustomerOrder.findByCustomerId");

        createNamedQuery.setParameter("id", id);

        return createNamedQuery.getResultList();
	}

	@Override
	public List<CustomerOrder> getOrdersByStatus(OrderStatus status) {
		Query createNamedQuery = getEntityManager().createNamedQuery("CustomerOrder.findByStatus");
        createNamedQuery.setParameter("status", status.getStatus());
        List<CustomerOrder> orders = createNamedQuery.getResultList();
        return orders;
	}


}
