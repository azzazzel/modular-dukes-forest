package com.forest.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class Group implements Serializable {

	private static final long serialVersionUID = 1205082528194257031L;
	protected Integer id;
	protected String name;
	protected String description;
	protected List<Person> personList;

	public Group() {
		super();
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	@XmlTransient
	public List<Person> getPersonList() {
	    return personList;
	}

	public void setPersonList(List<Person> personList) {
	    this.personList = personList;
	}

	@Override
	public int hashCode() {
	    int hash = 0;
	    hash += (id != null ? id.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object object) {
	    // TODO: Warning - this method won't work in the case the id fields are not set
	    if (!(object instanceof Group)) {
	        return false;
	    }
	    Group other = (Group) object;
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
	    return "com.forest.entity.Groups[ id=" + id + " ]";
	}

}