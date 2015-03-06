package com.forest.usecase.catalog.persistence;

import java.util.List;

import com.forest.model.Product;

public interface ProductPersistence {
	
	public void createProduct(Product product);

	public void updateProduct(Product product);

	public void removeProduct(Product product);
	
	public Product getProduct(int id);
	
	public int count();
	
    public List<Product> findAll();

    public List<Product> findRange(int... range);

    public List<Product> findByCategory(int[] range, int categoryId);
    
}
