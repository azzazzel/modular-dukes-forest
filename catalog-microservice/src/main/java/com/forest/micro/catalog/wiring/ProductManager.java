package com.forest.micro.catalog.wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forest.usecase.catalog.AbstractBaseProductManager;
import com.forest.usecase.catalog.persistence.ProductPersistence;

@Component
public class ProductManager extends AbstractBaseProductManager {

	@Autowired
	private ProductPersistence productPersistence;
	
	@Override
	protected ProductPersistence getProductPersistence() {
		return productPersistence;
	}
	
}
