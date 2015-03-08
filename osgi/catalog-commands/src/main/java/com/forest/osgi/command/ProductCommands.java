package com.forest.osgi.command;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.forest.model.Product;
import com.forest.usecase.catalog.ProductManager;

@Component(
		immediate=true,
		service = Object.class,
		property = { 
			"osgi.command.function=list",
			"osgi.command.function=add",
			"osgi.command.scope=product" 
			})
public class ProductCommands {

	/*
	 * As this is only simple DEMO we are going to only implement commands for 
	 * listing and adding products!
	 */
	
	ProductManager productManager;
	
	@Reference
	void bindProductManager (ProductManager productManager) {
		this.productManager = productManager;
	}
	
	void unbindProductManager (ProductManager productManager) {
		this.productManager = null;
	}
	
	public void add(String name, String description, double price) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		productManager.createProduct(product);
		System.out.println("Product saved!");
	}

	public void list() {
		List<Product> products = productManager.getAll();
		System.out.format("%-15s | %-10s | %-40s\n", "NAME", "PRICE", "DESCRIPTION");
		for (Product product : products) {
			System.out.format("%-15s | %10.2f | %-40s\n", product.getName(), product.getPrice(), product.getDescription());
		}
	}
}
