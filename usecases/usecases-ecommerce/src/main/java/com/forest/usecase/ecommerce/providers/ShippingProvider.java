package com.forest.usecase.ecommerce.providers;

import com.forest.model.CustomerOrder;

public interface ShippingProvider {
	
	public boolean requestShipping (CustomerOrder customerOrder);

	public boolean cancelShippingRequest (int customerOrderId);

}
