package com.forest.micro.catalog;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forest.micro.catalog.model.ProductObject;
import com.forest.micro.catalog.wiring.ProductManager;
import com.forest.model.Product;

@RestController
@Api(name = "Product", description = "Methods for managing products")
@ApiVersion(since = "1.0")
@ApiAuthNone
public class ProductEndpoint {

	private ProductManager productManager;

	@Autowired
	public ProductEndpoint(ProductManager productManager) {
		this.productManager = productManager;
	}

	@ApiMethod(produces = "application/json", description = "Get a list of all products")
	@ApiResponseObject
	@RequestMapping("/products")
	List<Product> getProducts() {
		return productManager.getAll();
	}

	@ApiMethod(produces = "application/json", description = "Returns a product with given id")
	@ApiResponseObject
	@RequestMapping(value = "/product/{id}", produces = "application/json")
	ProductObject getProduct(
			@PathVariable("id") @ApiPathParam(description = "The id of the product") int id) {
		return (ProductObject) productManager.getProduct(id);
	}

	@ApiMethod(produces = "application/json", description = "Creates new product")
	@ApiResponseObject
	@RequestMapping(method = RequestMethod.POST, value = "/product", consumes = "application/json")
	void addProduct(@RequestBody @ApiBodyObject ProductObject product) {
		productManager.createProduct(product);
	}

	@ApiMethod(produces = "application/json", description = "Updates existing product")
	@ApiResponseObject
	@RequestMapping(method = RequestMethod.PUT, value = "/product/{id}", consumes = "application/json")
	void updateProduct(
			@PathVariable("id") @ApiPathParam(description = "The id of the product") int id,
			@RequestBody @ApiBodyObject ProductObject product) {

		product.setId(id);
		productManager.updateProduct(product);
	}

	@ApiMethod(produces = "application/json", description = "Gets products in given category")
	@ApiResponseObject
	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}/products", produces = "application/json")
	List<Product> getProductInCategory(
			@PathVariable("id") @ApiPathParam(description = "The id of the category") int id,
			@RequestParam(defaultValue = "0", required = false) @ApiQueryParam(name = "from", defaultvalue = "0", required = false) int from,
			@RequestParam(defaultValue = "10", required = false) @ApiQueryParam(name = "to", defaultvalue = "10", required = false) int to) {

		return productManager.getProductsInCategory(id, from, to);
	}
}
