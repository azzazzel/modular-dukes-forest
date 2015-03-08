package com.forest.persistence.jpa;

import javax.persistence.EntityManager;

import com.forest.model.Category;
import com.forest.persistence.entity.CategoryEntity;
import com.forest.usecase.catalog.persistence.CategoryPersistence;

public class CategoryPersistenceJPA extends AbstractBasePersistence<CategoryEntity, Category> implements CategoryPersistence {

    public CategoryPersistenceJPA() {
		super(CategoryEntity.class);
	}

	private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	@Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

	@Override
	public void createCategory(Category category) {
		create(category);
	}

	@Override
	public void updateCategory(Category category) {
		update(category);
	}

	@Override
	public void removeCategory(Category category) {
		remove(category);
	}

	@Override
	public Category getCategory(int id) {
		return find(id);
	}




}
