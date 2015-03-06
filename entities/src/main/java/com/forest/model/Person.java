package com.forest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class Person implements Serializable {

	private static final long serialVersionUID = 6253057722726297688L;

	protected List<Group> groupsList;
	protected Integer id;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String address;
	protected String city;
	protected String password;

	public Person () {
		this.groupsList = new ArrayList<Group>();
	}

	public Person (Integer id) {
		this.id = id;
		this.groupsList = new ArrayList<Group>();
	}

	public Person (Integer id, String firstName, String lastName,
			String email, String address, String city) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.groupsList = new ArrayList<Group>();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public List<Group> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(List<Group> groupsList) {
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
		if (!(object instanceof Person)) {
			return false;
		}
		Person other = (Person) object;
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