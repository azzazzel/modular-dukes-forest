package com.forest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 5604812482204021100L;
	protected OrderDetailPK orderDetailPK;
	protected int qty;
	protected Product product;
	protected CustomerOrder customerOrder;


	public OrderDetailPK getOrderDetailPK() {
	    return orderDetailPK;
	}

	public void setOrderDetailPK(OrderDetailPK orderDetailPK) {
	    this.orderDetailPK = orderDetailPK;
	}

	public int getQty() {
	    return qty;
	}

	public void setQty(int qty) {
	    this.qty = qty;
	}

	public Product getProduct() {
	    return product;
	}

	public void setProduct(Product product) {
	    this.product = product;
	}

	@XmlTransient
	public CustomerOrder getCustomerOrder() {
	    return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
	    this.customerOrder = customerOrder;
	}

	@Override
	public int hashCode() {
	    int hash = 0;
	    hash += (orderDetailPK != null ? orderDetailPK.hashCode() : 0);
	    return hash;
	}

	@Override
	public boolean equals(Object object) {
	    // TODO: Warning - this method won't work in the case the id fields are not set
	    if (!(object instanceof OrderDetail)) {
	        return false;
	    }
	    OrderDetail other = (OrderDetail) object;
	    if ((this.orderDetailPK == null && other.orderDetailPK != null) || (this.orderDetailPK != null && !this.orderDetailPK.equals(other.orderDetailPK))) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
	    return "com.forest..OrderDetail[orderDetailPK=" + orderDetailPK + "]";
	}

}