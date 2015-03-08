/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.micro.catalog.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.forest.model.Category;
import com.forest.model.Product;

@ApiObject (name="Product", description="Product")
@XmlRootElement
public class ProductObject extends Product {
    
    private static final long serialVersionUID = -9109112921000514199L;


	@ApiObjectField(description="Product name", required=true)
    protected String name;

	@ApiObjectField(description="Product price")
	protected double price;

	@ApiObjectField(description="Product description")
	protected String description;
	
	@XmlTransient
	protected Category category;

	@ApiObjectField(description="Category to add to")
	protected Integer categoryId;

    public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
