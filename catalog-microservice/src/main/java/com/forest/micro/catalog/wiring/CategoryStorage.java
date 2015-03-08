package com.forest.micro.catalog.wiring;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.forest.model.Category;
import com.forest.usecase.catalog.persistence.CategoryPersistence;

@Component
public class CategoryStorage implements CategoryPersistence {

	/*
	 * In-memory storage (for demo purpose ONLY) !!!
	 */
	private static final TreeMap<Integer, Category> categoryById = new TreeMap<Integer, Category>();
	private static int idGenerator = 1;

	@Override
	public void createCategory(Category category) {
		category.setId(idGenerator++);
		categoryById.put(category.getId(), category);
	}

	@Override
	public void updateCategory(Category category) {
		if (categoryById.containsKey(category.getId())) {
			categoryById.put(category.getId(), category);
		}
	}

	@Override
	public void removeCategory(Category category) {
		categoryById.remove(category.getId());
	}

	@Override
	public Category getCategory(int id) {
		return categoryById.get(id);
	}

	@Override
	public int count() {
		return categoryById.size();
	}

	@Override
	public List<Category> findAll() {
		return new LinkedList<Category>(categoryById.values());
	}

	@Override
	public List<Category> findRange(int... range) {
		fixRange(range, categoryById.size());
		return new LinkedList<Category>(categoryById.subMap(range[0], range[1]).values());
	}

	private void fixRange(int[] range, int size) {
		if (range[0] < 0) range[0] = 0;
		if (range[0] > size) range[0] = size;
		if (range[1] < range[0]) range[1] = range[0];
		if (range[1] > size) range[1] = size;
	}
}
