/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderDetail;
import com.forest.model.Product;

/**
 *
 * @author markito
 */
@Entity
@Table(name = "ORDER_DETAIL")
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetailEntity o"),
    @NamedQuery(name = "OrderDetail.findByOrderId", query = "SELECT o FROM OrderDetailEntity o WHERE o.orderDetailPK.orderId = :orderId"),
    @NamedQuery(name = "OrderDetail.findByProductId", query = "SELECT o FROM OrderDetailEntity o WHERE o.orderDetailPK.productId = :productId"),
    @NamedQuery(name = "OrderDetail.findByQty", query = "SELECT o FROM OrderDetailEntity o WHERE o.qty = :qty")})
public class OrderDetailEntity extends OrderDetail {
    
    private static final long serialVersionUID = 5604812482204021100L;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(OrderDetailPKEntity orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public OrderDetailEntity(OrderDetailPKEntity orderDetailPK, int qty) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
    }

    public OrderDetailEntity(int orderId, int productId) {
        this.orderDetailPK = new OrderDetailPKEntity(orderId, productId);
    }

    @EmbeddedId
    @Access(AccessType.FIELD)
    public OrderDetailPKEntity getOrderDetailPK() {
        return (OrderDetailPKEntity)orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPKEntity orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    @Basic(optional = false)
    @Column(name = "QTY")
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity=ProductEntity.class, optional = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(targetEntity=CustomerOrderEntity.class, optional = false)
    @XmlTransient
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderEntity customerOrder) {
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
        if (!(object instanceof OrderDetailEntity)) {
            return false;
        }
        OrderDetailEntity other = (OrderDetailEntity) object;
        if ((this.orderDetailPK == null && other.orderDetailPK != null) || (this.orderDetailPK != null && !this.orderDetailPK.equals(other.orderDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.OrderDetail[orderDetailPK=" + orderDetailPK + "]";
    }

}
