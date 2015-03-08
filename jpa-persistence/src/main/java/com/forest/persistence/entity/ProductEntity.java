/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.persistence.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.forest.model.Category;
import com.forest.model.Product;

/**
 * 
 * @author markito
 */
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
		@NamedQuery(name = "Product.findAll", query = "SELECT p FROM ProductEntity p"),
		@NamedQuery(name = "Product.findById", query = "SELECT p FROM ProductEntity p WHERE p.id = :id"),
		@NamedQuery(name = "Product.findByName", query = "SELECT p FROM ProductEntity p WHERE p.name = :name"),
		@NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM ProductEntity p WHERE p.price = :price"),
		@NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM ProductEntity p WHERE p.description = :description"),
		@NamedQuery(name = "Product.findByImg", query = "SELECT p FROM ProductEntity p WHERE p.img = :img") })
public class ProductEntity extends Product {

	private static final long serialVersionUID = -9109112921000514199L;

	public ProductEntity() {
	}

	public ProductEntity(Integer id) {
		this.id = id;
	}

	public ProductEntity(Integer id, String name, double price,
			String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 45, message = "{product.name}")
	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic(optional = false)
	@DecimalMax(value = "9999.99", message = "{product.price.max}")
	@Column(name = "PRICE", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Basic(optional = false)
	@Size(min = 3, max = 145, message = "{product.description}")
	@Column(name = "DESCRIPTION", nullable = false, length = 45)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Size(min = 3, max = 45, message = "{product.img}")
	@Column(name = "IMG", length = 45)
	public String getImg() {
		return super.getImg();
	}

	public void setImg(String simg) {
		super.setImg(simg);
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Access(AccessType.FIELD)
	//
	@Column(name = "IMG_SRC")
	@XmlTransient
	public byte[] getImgSrc() {
		return super.getImgSrc();
	}

	public void setImgSrc(byte[] imgSrc) {
		super.setImgSrc(imgSrc);
	}

	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne(targetEntity=CategoryEntity.class, optional = false)
	public Category getCategory() {
		return super.getCategory();
	}

	public void setCategory(CategoryEntity category) {
		super.setCategory(category);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ProductEntity)) {
			return false;
		}
		ProductEntity other = (ProductEntity) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.forest.entity.Product[id=" + id + "]";
	}

}
