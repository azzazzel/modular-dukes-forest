package com.forest.usecase.identity.persistence;

import java.util.List;

import com.forest.model.Administrator;

public interface AdministratorPersistence {
	
	public void createAdministrator(Administrator administrator);

	public void updateAdministrator(Administrator administrator);

	public void removeAdministrator(Administrator administrator);
	
	public Administrator getAdministrator(int id);
	
	public int count();
	
    public List<Administrator> findAll();

    public List<Administrator> findRange(int... range);

}
