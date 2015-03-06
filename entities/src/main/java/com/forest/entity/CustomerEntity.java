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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author markito
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM CustomerEntity c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM CustomerEntity c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByFirstname", query = "SELECT c FROM CustomerEntity c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM CustomerEntity c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM CustomerEntity c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM CustomerEntity c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM CustomerEntity c WHERE c.city = :city")})
public class CustomerEntity extends PersonEntity {
    
    private static final long serialVersionUID = -1964261708710396652L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<CustomerOrderEntity> customerOrderList;

    public CustomerEntity() {
        this.customerOrderList = new ArrayList<CustomerOrderEntity>();
        this.groupsList = new ArrayList<GroupsEntity>();
    }

    public CustomerEntity(Integer id) {
        this.id = id;
        this.customerOrderList = new ArrayList<CustomerOrderEntity>();
        this.groupsList = new ArrayList<GroupsEntity>();
    }

    public CustomerEntity(Integer id, String firstname, String lastname, String email, String address, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.customerOrderList = new ArrayList<CustomerOrderEntity>();
        this.groupsList = new ArrayList<GroupsEntity>();
    }
    @XmlTransient
    public List<CustomerOrderEntity> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrderEntity> customerOrderList) {
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
}
