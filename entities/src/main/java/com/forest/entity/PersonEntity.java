/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.entity;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.Group;
import com.forest.model.Person;

/**
 * 
 * @author ievans
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
		@NamedQuery(name = "Person.findAll", query = "SELECT p FROM PersonEntity p"),
		@NamedQuery(name = "Person.findById", query = "SELECT p FROM PersonEntity p WHERE p.id = :id"),
		@NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM PersonEntity p WHERE p.firstname = :firstname"),
		@NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM PersonEntity p WHERE p.lastname = :lastname"),
		@NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM PersonEntity p WHERE p.email = :email"),
		@NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM PersonEntity p WHERE p.address = :address"),
		@NamedQuery(name = "Person.findByCity", query = "SELECT p FROM PersonEntity p WHERE p.city = :city") })
public class PersonEntity extends Person {

	private static final long serialVersionUID = -6350453022856763936L;

	public PersonEntity() {
		super();
	}

	public PersonEntity(Integer id) {
		super(id);
	}

	public PersonEntity(Integer id, String firstName, String lastName,
			String email, String address, String city) {
		super(id, firstName, lastName, email, address, city);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 50, message = "{person.firstname}")
	@Column(name = "FIRSTNAME")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 100, message = "{person.lastname}")
	@Column(name = "LASTNAME")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "{person.email}")
	@Size(min = 3, max = 45, message = "{person.email}")
	@Basic(optional = false)
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 45, message = "{person.address}")
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 45, message = "{person.city}")
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Add XmlTransient annotation to this field for security reasons.
	 * 
	 * @return the password
	 */
	@XmlTransient
	@Basic(optional = false)
	@Size(min = 7, max = 100, message = "{person.password}")
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@JoinTable(name = "PERSON_GROUPS", joinColumns = { @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL") }, inverseJoinColumns = { @JoinColumn(name = "GROUPS_ID", referencedColumnName = "ID") })
	@ManyToMany(targetEntity=GroupsEntity.class)
	public List<Group> getGroupsList() {
		return groupsList;
	}

	public void setGroups(List<Group> groupsList) {
		this.groupsList = groupsList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PersonEntity)) {
			return false;
		}
		PersonEntity other = (PersonEntity) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.forest.entity.Person[ id=" + id + " ]";
	}

}
