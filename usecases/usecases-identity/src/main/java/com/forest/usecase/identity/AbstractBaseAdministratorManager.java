package com.forest.usecase.identity;


import java.util.List;

import com.forest.model.Administrator;
import com.forest.model.Group;
import com.forest.usecase.identity.persistence.AdministratorPersistence;
import com.forest.usecase.identity.persistence.GroupPersistence;

public abstract class AbstractBaseAdministratorManager implements AdministratorManager {

	protected abstract AdministratorPersistence getAdministratorPersistence();

	protected abstract GroupPersistence getGroupPersistence();
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#count()
	 */
	@Override
	public int count() {
		return getAdministratorPersistence().count();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#getAll()
	 */
	@Override
	public List<Administrator> getAll() {
		return getAdministratorPersistence().findAll();
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#getAllInRange(int)
	 */
	@Override
	public List<Administrator> getAllInRange(int... range) {
		return getAdministratorPersistence().findRange(range);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#createAdministrator(com.forest.model.Administrator)
	 */
	@Override
	public void createAdministrator(Administrator admin) {
		Group adminGroup = getGroupPersistence().findGroupByName("Administrator");
		admin.getGroupsList().add(adminGroup);
		adminGroup.getPersonList().add(admin);
		getAdministratorPersistence().createAdministrator(admin);
		getGroupPersistence().updateGroup(adminGroup);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#updateAdministrator(com.forest.model.Administrator)
	 */
	@Override
	public void updateAdministrator(Administrator admin) {
		getAdministratorPersistence().updateAdministrator(admin);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#removeAdministrator(com.forest.model.Administrator)
	 */
	@Override
	public void removeAdministrator(Administrator admin) {
		Group adminGroup = getGroupPersistence().findGroupByName("Administrator");
		adminGroup.getPersonList().remove(admin);
		getAdministratorPersistence().updateAdministrator(admin);
		getAdministratorPersistence().removeAdministrator(admin);
		getGroupPersistence().updateGroup(adminGroup);
	}
	
	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.AdministratorManager#getAdministrator(java.lang.Integer)
	 */
	@Override
	public Administrator getAdministrator(Integer id) {
		return getAdministratorPersistence().getAdministrator(id);
	}

}
