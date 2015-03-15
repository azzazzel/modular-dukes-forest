package com.forest.usecase.catalog;

import java.util.List;

import com.forest.model.Product;
import com.forest.model.ProductList;

public interface ProductManager {

	public abstract void createProduct(Product product);

	public abstract List<Product> getProductsInCategory(int categoryId,
			int... range);

	public abstract Product getProduct(Integer id);

	public abstract void updateProduct(Product product);

	public abstract void removeProduct(Product product);

	public abstract int count();

	public abstract List<Product> getAll();

	public abstract List<Product> getAllInRange(int... range);
	
	public ProductList getAsProductList ();

}