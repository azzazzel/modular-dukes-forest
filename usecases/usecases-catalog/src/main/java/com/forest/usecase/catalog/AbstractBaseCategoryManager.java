package com.forest.usecase.catalog;

import java.util.List;

import com.forest.model.Category;
import com.forest.usecase.catalog.persistence.CategoryPersistence;

public abstract class AbstractBaseCategoryManager implements CategoryManager {

	protected abstract CategoryPersistence getCategoryPersistence ();

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#createCategory(com.forest.model.Category)
	 */
	@Override
	public void createCategory(Category category) {
		getCategoryPersistence().createCategory(category);
    }

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#getCategory(java.lang.Integer)
	 */
	@Override
	public Category getCategory(Integer id) {
		return getCategoryPersistence().getCategory(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#updateCategory(com.forest.model.Category)
	 */
	@Override
	public void updateCategory(Category category) {
		getCategoryPersistence().updateCategory(category);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#removeCategory(com.forest.model.Category)
	 */
	@Override
	public void removeCategory(Category category) {
		getCategoryPersistence().removeCategory(category);
	}

    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#count()
	 */
    @Override
	public int count() {
    	return getCategoryPersistence().count();
    }
   
    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#getAll()
	 */
    @Override
	public List<Category> getAll () {
    	return getCategoryPersistence().findAll();
    }

    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.CategoryManager#getAllInRange(int)
	 */
    @Override
	public List<Category> getAllInRange (int... range) {
    	return getCategoryPersistence().findRange(range);
    }
   
}
