package com.forest.osgi.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.forest.model.Product;
import com.forest.usecase.catalog.ProductManager;

@Path("product")
@Component(immediate = true, service = Object.class)
public class ProductResource {
	
	private ProductManager productManager;
	
	@Reference
	protected void bindProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	protected void unbindProductManager(ProductManager productManager) {
		this.productManager = null;
	}
	
	@GET
	@Produces("application/json")
	public List<Product> getAll () {
		return productManager.getAll();
	}

	@GET
	@Produces("application/json")
	@Path("{id}")
	public Product get (@PathParam ("id") int id) {
		return productManager.getProduct(id);
	}

}
