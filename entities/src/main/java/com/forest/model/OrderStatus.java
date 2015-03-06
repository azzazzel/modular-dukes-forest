package com.forest.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 232654980834071737L;
	protected Integer id;
	protected String status;
	protected List<CustomerOrder> customerOrderList;
	protected String description;

	public OrderStatus() {
		super();
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
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
	    if (!(object instanceof OrderStatus)) {
	        return false;
	    }
	    OrderStatus other = (OrderStatus) object;
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
	    return "com.forest.entity.OrderStatus[id=" + id + "]";
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
	    return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
	    this.description = description;
	}

}