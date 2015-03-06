package com.forest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;



public class Customer extends Person {

	private static final long serialVersionUID = -1964261708710396652L;
	protected List<CustomerOrder> customerOrderList;

	public Customer() {
		super();
	}

	public Customer(Integer id) {
		super(id);
	}

	public Customer(Integer id, String firstName, String lastName,
			String email, String address, String city) {
		super(id, firstName, lastName, email, address, city);
	}

	@XmlTransient
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
	    if (!(object instanceof Customer)) {
	        return false;
	    }
	    Customer other = (Customer) object;
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