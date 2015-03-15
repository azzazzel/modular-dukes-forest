package com.forest.usecase.catalog;

import java.util.List;

import com.forest.model.Product;
import com.forest.model.ProductList;
import com.forest.usecase.catalog.persistence.ProductPersistence;

public abstract class AbstractBaseProductManager implements ProductManager  {

	protected abstract ProductPersistence getProductPersistence();

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#createProduct(com.forest.model.Product)
	 */
	@Override
	public void createProduct(Product product) {
		getProductPersistence().createProduct(product);
    }

    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#getProductsInCategory(int, int)
	 */
    @Override
	public List<Product> getProductsInCategory(int categoryId, int... range) {
    	return getProductPersistence().findByCategory(range, categoryId);
    }
    

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#getProduct(java.lang.Integer)
	 */
	@Override
	public Product getProduct(Integer id) {
		return getProductPersistence().getProduct(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#updateProduct(com.forest.model.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		getProductPersistence().updateProduct(product);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#removeProduct(com.forest.model.Product)
	 */
	@Override
	public void removeProduct(Product product) {
		getProductPersistence().removeProduct(product);
	}

    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#count()
	 */
    @Override
	public int count() {
    	return getProductPersistence().count();
    }
   
    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#getAll()
	 */
    @Override
	public List<Product> getAll () {
    	return getProductPersistence().findAll();
    }

    /* (non-Javadoc)
	 * @see com.forest.usecase.catalog.ProductManager#getAllInRange(int)
	 */
    @Override
	public List<Product> getAllInRange (int... range) {
    	return getProductPersistence().findRange(range);
    }
   
	public ProductList getAsProductList () {
		return ProductList.fromList(getAll());
	}
}
