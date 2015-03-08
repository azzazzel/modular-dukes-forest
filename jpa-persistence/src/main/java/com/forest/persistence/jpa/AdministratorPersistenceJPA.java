package com.forest.persitence.jpa;

import javax.persistence.EntityManager;

import com.forest.entity.AdministratorEntity;
import com.forest.model.Administrator;
import com.forest.usecase.identity.persistence.AdministratorPersistence;

public class AdministratorPersistenceJPA extends AbstractBasePersistence<AdministratorEntity, Administrator> implements AdministratorPersistence {

    public AdministratorPersistenceJPA() {
		super(AdministratorEntity.class);
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
	public void createAdministrator(Administrator administrator) {
		super.create(administrator);
	}


	@Override
	public void updateAdministrator(Administrator person) {
		update(person);
	}

	@Override
	public void removeAdministrator(Administrator person) {
		remove(person);
	}

	@Override
	public Administrator getAdministrator(int id) {
		return find(id);
	}

}
