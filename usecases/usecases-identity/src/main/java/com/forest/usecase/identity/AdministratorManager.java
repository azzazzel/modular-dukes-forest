package com.forest.usecase.identity;

import java.util.List;

import com.forest.model.Administrator;

public interface AdministratorManager {

	public abstract int count();

	public abstract List<Administrator> getAll();

	public abstract List<Administrator> getAllInRange(int... range);

	public abstract void createAdministrator(Administrator admin);

	public abstract void updateAdministrator(Administrator admin);

	public abstract void removeAdministrator(Administrator admin);

	public abstract Administrator getAdministrator(Integer id);

}