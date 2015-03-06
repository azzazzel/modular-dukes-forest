package com.forest.usecase.catalog.persistence;

import java.util.List;

import com.forest.model.Category;

public interface CategoryPersistence {
	
	public void createCategory(Category category);

	public void updateCategory(Category category);

	public void removeCategory(Category category);
	
	public Category getCategory(int id);
	
	public int count();
	
    public List<Category> findAll();

    public List<Category> findRange(int... range);

}
