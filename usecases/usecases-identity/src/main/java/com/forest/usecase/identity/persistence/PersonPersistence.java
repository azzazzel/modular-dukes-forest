package com.forest.usecase.identity.persistence;

import java.util.List;

import com.forest.model.Person;

public interface PersonPersistence {

	public void createPerson(Person person);
	
	public void updatePerson(Person person);

	public void removePerson(Person person);
	
	public Person getPerson(Integer id);
	
	public Person findPersonByEmail(String email);

	public int count();
	
    public List<Person> findAll();

    public List<Person> findRange(int... range);

}
