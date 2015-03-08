/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.micro.catalog.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.forest.model.Category;

@ApiObject(name = "Category", description = "Category")
@XmlRootElement
public class CategoryObject extends Category {

	private static final long serialVersionUID = -5400424750505982222L;

	@ApiObjectField(description = "Category name", required = true)
	protected String name;

	@ApiObjectField(description = "Tags")
	protected String tags;

}
