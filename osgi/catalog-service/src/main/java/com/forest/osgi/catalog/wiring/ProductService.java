package com.forest.osgi.catalog.wiring;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.remoteserviceadmin.RemoteConstants;

import com.forest.usecase.catalog.AbstractBaseProductManager;
import com.forest.usecase.catalog.ProductManager;
import com.forest.usecase.catalog.persistence.ProductPersistence;

@Component(
		immediate = true, 
		property = {
				RemoteConstants.SERVICE_EXPORTED_INTERFACES + "=com.forest.usecase.catalog.ProductManager"
		},
		service = ProductManager.class)
public class ProductService extends AbstractBaseProductManager {

	private ProductPersistence productPersistence;
	
	@Reference
	protected void bindProductPersistence(ProductPersistence productPersistence) {
		this.productPersistence = productPersistence;
	}

	protected void unbindProductPersistence(ProductPersistence productPersistence) {
		this.productPersistence = null;
	}

	@Override
	protected ProductPersistence getProductPersistence() {
		return productPersistence;
	}


}
