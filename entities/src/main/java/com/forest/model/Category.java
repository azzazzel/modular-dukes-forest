package com.forest.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

public class Category implements Serializable {

	private static final long serialVersionUID = -5400424750505982222L;
	protected Integer id;
	protected String name;
	protected String tags;
	protected List<Product> productList;

	public Category() {
		super();
	}

	public Integer getId() {
	    return id;
	}

	public void setId(Integer id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getTags() {
	    return tags;
	}

	public void setTags(String tags) {
	    this.tags = tags;
	}

	@XmlTransient
	public List<Product> getProductList() {
	    return productList;
	}

	public void setProductList(List<Product> productList) {
	    this.productList = productList;
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
	    if (!(object instanceof Category)) {
	        return false;
	    }
	    Category other = (Category) object;
	    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	        return false;
	    }
	    return true;
	}

	@Override
	public String toString() {
	    return getName() + " [ID: " + id + "]";
	}

}