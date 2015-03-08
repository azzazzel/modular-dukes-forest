package com.forest.micro.catalog;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forest.micro.catalog.model.CategoryObject;
import com.forest.micro.catalog.wiring.CategoryManager;
import com.forest.model.Category;

@RestController
@Api(name = "Category", description = "Methods for managing categories")
@ApiVersion(since = "1.0")
@ApiAuthNone
public class CategoryEndpoint {

	private CategoryManager categoryManager;

	@Autowired
	public CategoryEndpoint(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	@ApiMethod(produces = "application/json", description = "Get a list of all categories")
	@ApiResponseObject
	@RequestMapping("/cetegories")
	List<Category> getCategories() {
		return categoryManager.getAll();
	}

	@ApiMethod(produces = "application/json", description = "Returns a caregory with given id")
	@ApiResponseObject
	@RequestMapping(value = "/category/{id}", produces = "application/json")
	CategoryObject getCategory(
			@PathVariable("id") @ApiPathParam(description = "The id of the category") int id) {
		return (CategoryObject) categoryManager.getCategory(id);
	}

	@ApiMethod(produces = "application/json", description = "Creates new category")
	@ApiResponseObject
	@RequestMapping(method = RequestMethod.POST, value = "/category", consumes = "application/json")
	void addCategory(@RequestBody @ApiBodyObject CategoryObject category) {
		categoryManager.createCategory(category);
	}

	@ApiMethod(produces = "application/json", description = "Updates existing category")
	@ApiResponseObject
	@RequestMapping(method = RequestMethod.PUT, value = "/category/{id}", consumes = "application/json")
	void updateCategory(
			@PathVariable("id") @ApiPathParam(description = "The id of the category") int id,
			@RequestBody @ApiBodyObject CategoryObject category) {

		category.setId(id);
		categoryManager.updateCategory(category);
	}
}
