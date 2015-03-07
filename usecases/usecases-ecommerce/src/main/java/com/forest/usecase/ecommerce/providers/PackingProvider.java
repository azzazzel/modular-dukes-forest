package com.forest.usecase.ecommerce.providers;

import com.forest.model.CustomerOrder;

public interface PackingProvider {

	public boolean requestPacking (CustomerOrder customerOrder);

	public boolean cancelPackingRequest (int customerOrderId);
}
