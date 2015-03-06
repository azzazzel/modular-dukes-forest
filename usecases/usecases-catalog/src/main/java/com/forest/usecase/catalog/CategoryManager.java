package com.forest.usecase.catalog;

import java.util.List;

import com.forest.model.Category;

public interface CategoryManager {

	public abstract void createCategory(Category category);

	public abstract Category getCategory(Integer id);

	public abstract void updateCategory(Category category);

	public abstract void removeCategory(Category category);

	public abstract int count();

	public abstract List<Category> getAll();

	public abstract List<Category> getAllInRange(int... range);

}