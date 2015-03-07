package com.forest.usecase.ecommerce.providers;

import com.forest.model.CustomerOrder;

public interface PaymentProvider {
	
	public boolean requestPayment (CustomerOrder customerOrder);
	
	public boolean cancelPaymentRequest (int customerOrderId);
}
