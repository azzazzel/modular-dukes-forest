package com.forest.persitence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.forest.entity.CategoryEntity;
import com.forest.entity.ProductEntity;
import com.forest.model.Product;
import com.forest.usecase.catalog.persistence.ProductPersistence;

public class ProductPersistenceJPA extends AbstractBasePersistence<ProductEntity, Product> implements ProductPersistence {

    public ProductPersistenceJPA() {
		super(ProductEntity.class);
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
	public void createProduct(Product product) {
		create(product);
	}

	@Override
	public void updateProduct(Product product) {
		update(product);
	}

	@Override
	public void removeProduct(Product product) {
		remove(product);
	}

	@Override
	public Product getProduct(int id) {
		return find(id);
	}

	@Override
	public List<Product> findByCategory(int[] range, int categoryId) {
        CategoryEntity cat = new CategoryEntity();
        cat.setId(categoryId);
        
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = qb.createQuery(ProductEntity.class);
        Root<ProductEntity> product = query.from(ProductEntity.class);
        query.where(qb.equal(product.get("category"), cat));

 		return this.findRange(range, query);
	}




}
