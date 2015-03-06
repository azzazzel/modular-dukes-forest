package com.forest.usecase.identity;

import java.util.List;

import com.forest.model.Person;

public interface PersonManager {

	public abstract boolean createPerson(Person person);

	public abstract Person getPersonByEmail(String email);

	public abstract Person getPerson(Integer id);

	public abstract void updatePerson(Person person);

	public abstract void removePerson(Person person);

	public abstract int count();

	public abstract List<Person> getAll();

	public abstract List<Person> getAllInRange(int... range);

}