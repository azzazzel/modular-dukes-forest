package com.forest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomerOrder implements Serializable{

	private static final long serialVersionUID = 2705492120685275910L;

	protected Date dateCreated;
	protected Integer id;
	protected double amount;
	protected OrderStatus orderStatus;
	protected Customer customer;
	protected List<OrderDetail> orderDetailList;

	public CustomerOrder() {
		super();
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public double getAmount() {
	    return amount;
	}

	public void setAmount(double amount) {
	    this.amount = amount;
	}

	public Date getDateCreated() {
	    return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
	    this.dateCreated = dateCreated;
	}

	public OrderStatus getOrderStatus() {
	    return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
	    this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}

	public void setCustomer(Person person) {
	    this.customer = (Customer) person;
	}

	public List<OrderDetail> getOrderDetailList() {
	    return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
	    this.orderDetailList = orderDetailList;
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
	    if (!(object instanceof CustomerOrder)) {
	        return false;
	    }
	    CustomerOrder other = (CustomerOrder) object;
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
	    return "com.forest.entity.CustomerOrder[id=" + id + "]";
	}

}