package com.forest.usecase.identity;

import java.util.List;

import com.forest.model.Group;
import com.forest.usecase.identity.persistence.GroupPersistence;

public abstract class AbstractBaseGroupManager implements GroupManager {

	protected abstract GroupPersistence getGroupPersistence();
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#count()
	 */
	@Override
	public int count() {
		return getGroupPersistence().count();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#getAll()
	 */
	@Override
	public List<Group> getAll() {
		return getGroupPersistence().findAll();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#getAllInRange(int)
	 */
	@Override
	public List<Group> getAllInRange(int... range) {
		return getGroupPersistence().findRange(range);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#createGroup(com.forest.model.Groups)
	 */
	@Override
	public void createGroup(Group group) {
		getGroupPersistence().createGroup(group);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#updateGroup(com.forest.model.Groups)
	 */
	@Override
	public void updateGroup(Group group) {
		getGroupPersistence().updateGroup(group);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#removeGroup(com.forest.model.Groups)
	 */
	@Override
	public void removeGroup(Group group) {
		getGroupPersistence().removeGroup(group);
	}
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.GroupManager#getGroup(java.lang.Integer)
	 */
	@Override
	public Group getGroup(Integer id) {
		return getGroupPersistence().getGroup(id);
	}

}
