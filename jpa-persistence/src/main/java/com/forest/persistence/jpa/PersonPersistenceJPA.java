package com.forest.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.forest.model.Person;
import com.forest.persistence.entity.PersonEntity;
import com.forest.usecase.identity.persistence.PersonPersistence;

public class PersonPersistenceJPA extends AbstractBasePersistence<PersonEntity, Person> implements PersonPersistence {

    public PersonPersistenceJPA() {
		super(PersonEntity.class);
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
	public void createPerson(Person person) {
		super.create(person);

	}

	@Override
	public Person findPersonByEmail(String email) {
		Query createNamedQuery = getEntityManager().createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Person) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
	}

	@Override
	public void updatePerson(Person person) {
		update(person);
	}

	@Override
	public void removePerson(Person person) {
		remove(person);
	}

	@Override
	public Person getPerson(Integer id) {
		return find(id);
	}

}
