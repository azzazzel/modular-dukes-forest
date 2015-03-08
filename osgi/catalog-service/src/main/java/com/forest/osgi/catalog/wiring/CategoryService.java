package com.forest.osgi.catalog.wiring;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.forest.usecase.catalog.AbstractBaseCategoryManager;
import com.forest.usecase.catalog.CategoryManager;
import com.forest.usecase.catalog.persistence.CategoryPersistence;

@Component(
		immediate = true, 
		service = CategoryManager.class)
public class CategoryService extends AbstractBaseCategoryManager {

	private CategoryPersistence categoryPersistence;
	
	@Reference
	protected void bindProductPersistence(CategoryPersistence categoryPersistence) {
		this.categoryPersistence = categoryPersistence;
	}

	protected void unbindCategoryPersistence(CategoryPersistence categoryPersistence) {
		this.categoryPersistence = null;
	}

	@Override
	protected CategoryPersistence getCategoryPersistence() {
		return categoryPersistence;
	}

	
	
}
