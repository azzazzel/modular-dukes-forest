/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.Customer;
import com.forest.model.CustomerOrder;
import com.forest.model.Group;

/**
 *
 * @author markito
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM CustomerEntity c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM CustomerEntity c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByFirstname", query = "SELECT c FROM CustomerEntity c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM CustomerEntity c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM CustomerEntity c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM CustomerEntity c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM CustomerEntity c WHERE c.city = :city")})
public class CustomerEntity extends Customer {
    
    private static final long serialVersionUID = -1964261708710396652L;
    
    public CustomerEntity() {
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupsList = new ArrayList<Group>();
    }

    public CustomerEntity(Integer id) {
        this.id = id;
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupsList = new ArrayList<Group>();
    }

    public CustomerEntity(Integer id, String firstname, String lastname, String email, String address, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupsList = new ArrayList<Group>();
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	
    @XmlTransient
    @OneToMany(targetEntity=CustomerOrderEntity.class, cascade = CascadeType.ALL, mappedBy = "customer")
    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
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
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.Customer[id=" + id + "]";
    }
    
	@Basic(optional = false)
	@Size(min = 3, max = 50, message = "{person.firstname}")
	@Column(name = "FIRSTNAME")
	public String getFirstname() {
		return firstname;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 100, message = "{person.lastname}")
	@Column(name = "LASTNAME")
	public String getLastname() {
		return lastname;
	}

	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "{person.email}")
	@Size(min = 3, max = 45, message = "{person.email}")
	@Basic(optional = false)
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 45, message = "{person.address}")
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 45, message = "{person.city}")
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

}
