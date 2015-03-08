package com.forest.persistence.jpa;

import javax.persistence.EntityManager;

import com.forest.model.Group;
import com.forest.persistence.entity.GroupsEntity;
import com.forest.usecase.identity.persistence.GroupPersistence;

public class GroupPersistenceJPA extends AbstractBasePersistence<GroupsEntity, Group> implements GroupPersistence {

    public GroupPersistenceJPA() {
		super(GroupsEntity.class);
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
	public void createGroup(Group group) {
		super.create(group);

	}

	@Override
	public void updateGroup(Group group) {
		update(group);
	}

	@Override
	public void removeGroup(Group group) {
		remove(group);
	}

	@Override
	public Group getGroup(int id) {
		return find(id);
	}

	@Override
	public Group findGroupByName(String name) {
		return (Group) entityManager.createNamedQuery("Groups.findByName")
	                .setParameter("name", "Administrator")
	                .getSingleResult();
	}



}
