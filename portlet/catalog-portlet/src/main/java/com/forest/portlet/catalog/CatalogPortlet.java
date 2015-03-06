package com.forest.portlet.catalog;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.forest.usecase.catalog.ProductManager;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(immediate = true, property = {
		"com.liferay.portlet.css-class-wrapper=portlet-catalog",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.reasultinstanceable=false",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"javax.portlet.display-name=Duke's Catalog",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CatalogPortlet extends MVCPortlet {

	ProductManager productManager;

	@Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.OPTIONAL)
	void bindProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	void unbindProductManager(ProductManager productManager) {
		this.productManager = null;
	}

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		if (productManager == null) {
			include("/error.jsp", renderRequest, renderResponse);
		} else {
			renderRequest.setAttribute("productManager", productManager);
			super.doView(renderRequest, renderResponse);
		}
	}

}
