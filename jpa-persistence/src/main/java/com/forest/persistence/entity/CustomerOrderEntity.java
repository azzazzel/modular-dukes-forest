/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.forest.model.Customer;
import com.forest.model.CustomerOrder;
import com.forest.model.OrderDetail;
import com.forest.model.OrderStatus;

/**
 *
 * @author markito
 */
@Entity
@Table(name = "CUSTOMER_ORDER")
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrderEntity c"),
    @NamedQuery(name = "CustomerOrder.findById", query = "SELECT c FROM CustomerOrderEntity c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerOrder.findByStatus", query = "SELECT c FROM CustomerOrderEntity c, OrderStatusEntity s WHERE c.orderStatus = s and s.status = :status"),
    @NamedQuery(name = "CustomerOrder.findByStatusId", query = "SELECT c FROM CustomerOrderEntity c, OrderStatusEntity s WHERE c.orderStatus = s and s.id = :id"),
    @NamedQuery(name = "CustomerOrder.findByCustomerId", query = "SELECT c FROM CustomerOrderEntity c WHERE c.customer.id = :id"),
    @NamedQuery(name = "CustomerOrder.findByAmount", query = "SELECT c FROM CustomerOrderEntity c WHERE c.amount = :amount"),
    @NamedQuery(name = "CustomerOrder.findByDateCreated", query = "SELECT c FROM CustomerOrderEntity c WHERE c.dateCreated = :dateCreated")})
@XmlRootElement(name = "CustomerOrder")
public class CustomerOrderEntity extends CustomerOrder {
    
    private static final long serialVersionUID = 2705492120685275910L;


    public CustomerOrderEntity() {
    }

    public CustomerOrderEntity(Integer id) {
        this.id = id;
    }

    public CustomerOrderEntity(Integer id, double amount, Date dateCreated) {
        this.id = id;
        this.amount = amount;
        this.dateCreated = dateCreated;
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
    @DecimalMin(value="0.1", message="{c.order.amount}")
    @Column(name = "AMOUNT")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne(targetEntity=OrderStatusEntity.class, optional = false)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    @ManyToOne(targetEntity=CustomerEntity.class, optional = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(targetEntity=OrderDetailEntity.class, cascade = CascadeType.ALL, mappedBy = "customerOrder")
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
        if (!(object instanceof CustomerOrderEntity)) {
            return false;
        }
        CustomerOrderEntity other = (CustomerOrderEntity) object;
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
