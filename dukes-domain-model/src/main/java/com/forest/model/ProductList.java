package com.forest.model;

import java.util.LinkedList;
import java.util.List;

public class ProductList extends LinkedList<Product> {


	private static final long serialVersionUID = 5038778844017547797L;

	public static ProductList fromList (List<Product> list) {
		ProductList result = new ProductList();
		result.addAll(list);
		return result;
	}
}
