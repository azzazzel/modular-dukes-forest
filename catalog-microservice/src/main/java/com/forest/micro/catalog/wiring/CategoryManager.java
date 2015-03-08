package com.forest.micro.catalog.wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.forest.usecase.catalog.AbstractBaseCategoryManager;
import com.forest.usecase.catalog.persistence.CategoryPersistence;


@Component
public class CategoryManager extends AbstractBaseCategoryManager {

	@Autowired
	private CategoryPersistence categoryPersistence;
	
	@Override
	protected CategoryPersistence getCategoryPersistence() {
		return categoryPersistence;
	}
	
}
