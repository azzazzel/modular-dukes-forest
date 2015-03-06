package com.forest.usecase.identity;

import java.util.List;

import com.forest.model.Person;
import com.forest.usecase.identity.persistence.PersonPersistence;

public abstract class AbstractBasePersonManager implements PersonManager {

	protected abstract PersonPersistence getPersonPersistence();

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#createPerson(com.forest.model.Person)
	 */
	@Override
	public boolean createPerson(Person person) {

        // check if user exists
        if (getPersonByEmail(person.getEmail()) == null) {

        	getPersonPersistence().createPerson(person);
        	
        	/*
        	 * This is where one can add more logic related to "create person" use case!
        	 * For example 
        	 *  - send e-mail notification 
        	 *  - update 3rd party systems (cache, search engine, CRM, ...)
        	 *  - provision some services 
        	 *  - ....
        	 */
        	
            return true;
        } else {
            return false;
        }
    }

    /* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#getPersonByEmail(java.lang.String)
	 */
    @Override
	public Person getPersonByEmail(String email) {
    	
    	Person person = getPersonPersistence().findPersonByEmail(email);
    	
    	/*
    	 * While this implementation simply uses the persistence layer to find the person, 
    	 * it may be safely changed to  
    	 *  - use caching
    	 *  - use external search engine
    	 *  - ....
    	 *  at any time without modifying the use case itself!
    	 */
    	
    	return person;
    }
    

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#getPerson(java.lang.Integer)
	 */
	@Override
	public Person getPerson(Integer id) {
		return getPersonPersistence().getPerson(id);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#updatePerson(com.forest.model.Person)
	 */
	@Override
	public void updatePerson(Person person) {
		getPersonPersistence().updatePerson(person);
	}

	/* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#removePerson(com.forest.model.Person)
	 */
	@Override
	public void removePerson(Person person) {
		getPersonPersistence().removePerson(person);
	}

    /* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#count()
	 */
    @Override
	public int count() {
    	return getPersonPersistence().count();
    }
   
    /* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#getAll()
	 */
    @Override
	public List<Person> getAll () {
    	return getPersonPersistence().findAll();
    }

    /* (non-Javadoc)
	 * @see com.forest.usecase.identity.PersonManager#getAllInRange(int)
	 */
    @Override
	public List<Person> getAllInRange (int... range) {
    	return getPersonPersistence().findRange(range);
    }
   
}
