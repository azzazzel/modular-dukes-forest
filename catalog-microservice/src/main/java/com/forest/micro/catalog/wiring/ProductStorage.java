package com.forest.micro.catalog.wiring;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.forest.micro.catalog.model.ProductObject;
import com.forest.model.Product;
import com.forest.usecase.catalog.persistence.ProductPersistence;

@Component
public class ProductStorage implements ProductPersistence {

	/*
	 * In-memory storage (for demo purpose ONLY) !!!
	 */
	private static final TreeMap<Integer, Product> productsById = new TreeMap<Integer, Product>();
	private static final TreeMap<Integer, List<Product>> productsByCategory = new TreeMap<Integer, List<Product>>();
	private static int idGenerator = 1;

	@Override
	public void createProduct(Product product) {
		product.setId(idGenerator++);
		productsById.put(product.getId(), product);
		updateCategory(product);
	}

	@Override
	public void updateProduct(Product product) {
		if (productsById.containsKey(product.getId())) {
			productsById.put(product.getId(), product);
			updateCategory(product);
		}
	}

	@Override
	public void removeProduct(Product product) {
		productsById.remove(product.getId());
		productsByCategory.get(product.getCategory().getId()).remove(product);
	}

	@Override
	public Product getProduct(int id) {
		return productsById.get(id);
	}

	@Override
	public int count() {
		return productsById.size();
	}

	@Override
	public List<Product> findAll() {
		return new LinkedList<Product>(productsById.values());
	}

	@Override
	public List<Product> findRange(int... range) {
		fixRange(range, productsById.size());
		return new LinkedList<Product>(productsById.subMap(range[0], range[1])
				.values());
	}

	@Override
	public List<Product> findByCategory(int[] range, int categoryId) {
		List<Product> products = productsByCategory.get(categoryId);
		if (products != null) {
			fixRange(range, products.size());
			return products.subList(range[0], range[1]);
		}
		return Collections.emptyList();
	}

	private void fixRange(int[] range, int size) {
		if (range[0] < 0) range[0] = 0;
		if (range[0] > size) range[0] = size;
		if (range[1] < range[0]) range[1] = range[0];
		if (range[1] > size) range[1] = size;
	}

	private void updateCategory(Product product) {
		ProductObject productObject = (ProductObject) product;
		Integer categoryId = productObject.getCategoryId();
		if (categoryId != null) {
			List<Product> products = productsByCategory.get(categoryId);
			if (products == null) {
				products = new LinkedList<Product>();
				productsByCategory.put(categoryId, products);
			}
			products.add(product);
		}
	}

}
