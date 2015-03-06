package com.forest.usecase.ecommerce;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderStatus;
import com.forest.usecase.ecommerce.persistence.CustomerOrderPersistence;
import com.forest.usecase.ecommerce.persistence.OrderStatusPersistence;

public abstract class AbstractBaseCustomerOrderManager implements CustomerOrderManager {

	private static final Logger logger = Logger
			.getLogger(AbstractBaseCustomerOrderManager.class
					.getCanonicalName());

	protected abstract CustomerOrderPersistence getCustomerOrderPersistence();

	protected abstract OrderStatusPersistence getOrderStatusPersistence();

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#createCustomerOrder(com.forest.model.CustomerOrder)
	 */
	@Override
	public void createCustomerOrder(CustomerOrder customerOrder) {
		getCustomerOrderPersistence().createCustomerOrder(customerOrder);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#getCustomerOrder(java.lang.Integer)
	 */
	@Override
	public CustomerOrder getCustomerOrder(Integer id) {
		return getCustomerOrderPersistence().getCustomerOrder(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#updateCustomerOrder(com.forest.model.CustomerOrder)
	 */
	@Override
	public void updateCustomerOrder(CustomerOrder customerOrder) {
		getCustomerOrderPersistence().updateCustomerOrder(customerOrder);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#removeCustomerOrder(com.forest.model.CustomerOrder)
	 */
	@Override
	public void removeCustomerOrder(CustomerOrder customerOrder) {
		getCustomerOrderPersistence().removeCustomerOrder(customerOrder);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#count()
	 */
	@Override
	public int count() {
		return getCustomerOrderPersistence().count();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#getAll()
	 */
	@Override
	public List<CustomerOrder> getAll() {
		return getCustomerOrderPersistence().findAll();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#getAllInRange(int)
	 */
	@Override
	public List<CustomerOrder> getAllInRange(int... range) {
		return getCustomerOrderPersistence().findRange(range);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#getOrderByCustomerId(java.lang.Integer)
	 */
	@Override
	public List<CustomerOrder> getOrderByCustomerId(Integer id) {
		return getCustomerOrderPersistence().getOrdersByCustomerId(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#getOrderByStatus(int)
	 */
	@Override
	public List<CustomerOrder> getOrderByStatus(int status) {
		OrderStatus orderStatus = getOrderStatusPersistence().getOrderStatus(
				status);
		return getCustomerOrderPersistence().getOrdersByStatus(orderStatus);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.ecommerce.CustomerOrderManager#setOrderStatus(int, java.lang.String)
	 */
	@Override
	public void setOrderStatus(int orderId, String newStatus) {
		CustomerOrder order;
		try {
			order = (CustomerOrder) getCustomerOrderPersistence()
					.getCustomerOrder(orderId);

			if (order != null) {
				logger.log(Level.FINEST, "Updating order {0} status to {1}",
						new Object[] { order.getId(), newStatus });

				OrderStatus oStatus = getOrderStatusPersistence()
						.getOrderStatus(new Integer(newStatus));
				order.setOrderStatus(oStatus);

				getCustomerOrderPersistence().updateCustomerOrder(order);

				logger.info("Order Updated!");
			}

		} catch (Exception ex) {

			logger.log(Level.SEVERE, ex.getMessage());
		}
	}

	public enum Status {

		PENDING_PAYMENT(2), READY_TO_SHIP(3), SHIPPED(4), CANCELLED_PAYMENT(5), CANCELLED_MANUAL(
				6);
		private int status;

		private Status(int pStatus) {
			status = pStatus;
		}

		public int getStatus() {
			return status;
		}
	}

}
