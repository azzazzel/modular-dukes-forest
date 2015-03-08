/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.persistence.entity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.CustomerOrder;
import com.forest.model.OrderStatus;

/**
 *
 * @author markito
 */
@Entity
@Table(name = "ORDER_STATUS")
@NamedQueries({
    @NamedQuery(name = "OrderStatus.findAll", query = "SELECT o FROM OrderStatusEntity o"),
    @NamedQuery(name = "OrderStatus.findById", query = "SELECT o FROM OrderStatusEntity o WHERE o.id = :id"),
    @NamedQuery(name = "OrderStatus.findByStatus", query = "SELECT o FROM OrderStatusEntity o WHERE o.status = :status")})
public class OrderStatusEntity extends OrderStatus {
    
    private static final long serialVersionUID = 232654980834071737L;

    public OrderStatusEntity() {
    }

    public OrderStatusEntity(Integer id) {
        this.id = id;
    }

    public OrderStatusEntity(Integer id, String status) {
        this.id = id;
        this.status = status;
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
    @Column(name = "STATUS")
    @Size(min=3, max=45, message="{order.status}")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @XmlTransient
    @OneToMany(targetEntity=CustomerOrderEntity.class, cascade = CascadeType.ALL, mappedBy = "orderStatus")
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
        if (!(object instanceof OrderStatusEntity)) {
            return false;
        }
        OrderStatusEntity other = (OrderStatusEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.OrderStatus[id=" + id + "]";
    }

    @Basic(optional=true)
    @Size(min=0, max=200, message="Description has maximum of 200 characters")
    @Column(name= "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
