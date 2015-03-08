/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.ui.controller;

import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.forest.model.OrderDetail;
import com.forest.store.ui.util.AbstractPaginationHelper;
import com.forest.store.ui.util.JsfUtil;
import com.forest.store.ui.util.PageNavigation;
import com.forest.store.wiring.ecommerce.OrderDetailBean;

@Named(value = "orderDetailController")
@RequestScoped
public class OrderDetailController {
	private static final String BUNDLE = "bundles.Bundle";

	private OrderDetail current;
	private DataModel items = null;
	@EJB
	private com.forest.store.wiring.ecommerce.OrderDetailBean ejbFacade;
	private AbstractPaginationHelper pagination;
	private int selectedItemIndex;

	public OrderDetailController() {
	}

	public OrderDetail getSelected() {
		if (current == null) {
			current = ejbFacade.newOrderDetailInstance();
			selectedItemIndex = -1;
		}
		return current;
	}

	private OrderDetailBean getFacade() {
		return ejbFacade;
	}

	public AbstractPaginationHelper getPagination() {
		if (pagination == null) {
			pagination = new AbstractPaginationHelper(10) {

				@Override
				public int getItemsCount() {
					return getFacade().count();
				}

				@Override
				public DataModel createPageDataModel() {
					int orderId = Integer.valueOf(JsfUtil
							.getRequestParameter("orderId"));

					// ListDataModel(getFacade().findRange(new
					// int[]{getPageFirstItem(),
					// getPageFirstItem()+getPageSize()}));
					return new ListDataModel(getFacade()
							.findOrderDetailByOrder(orderId));
				}
			};
		}
		return pagination;
	}

	public PageNavigation prepareList() {
		recreateModel();
		return PageNavigation.LIST;
	}

	public PageNavigation prepareView() {
		current = (OrderDetail) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.VIEW;
	}

	public PageNavigation prepareCreate() {
		current = ejbFacade.newOrderDetailInstance();
		selectedItemIndex = -1;
		return PageNavigation.CREATE;
	}

	public PageNavigation create() {
		try {
			getFacade().createOrderDetail(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("OrderDetailCreated"));
			return prepareCreate();
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public PageNavigation prepareEdit() {
		current = (OrderDetail) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.EDIT;
	}

	public PageNavigation update() {
		try {
			getFacade().updateOrderDetail(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("OrderDetailUpdated"));
			return PageNavigation.VIEW;
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public PageNavigation destroy() {
		current = (OrderDetail) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		performDestroy();
		recreateModel();
		return PageNavigation.LIST;
	}

	public PageNavigation destroyAndView() {
		performDestroy();
		recreateModel();
		updateCurrentItem();
		if (selectedItemIndex >= 0) {
			return PageNavigation.VIEW;
		} else {
			// all items were removed - go back to list
			recreateModel();
			return PageNavigation.LIST;
		}
	}

	private void performDestroy() {
		try {
			getFacade().removeOrderDetail(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("OrderDetailDeleted"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
		}
	}

	private void updateCurrentItem() {
		int count = getFacade().count();
		if (selectedItemIndex >= count) {
			// selected index cannot be bigger than number of items:
			selectedItemIndex = count - 1;
			// go to previous page if last page disappeared:
			if (pagination.getPageFirstItem() >= count) {
				pagination.previousPage();
			}
		}
		if (selectedItemIndex >= 0) {
			current = getFacade().getAllInRange(selectedItemIndex,
					selectedItemIndex + 1).get(0);
		}
	}

	public DataModel getItems() {
		if (items == null) {
			items = getPagination().createPageDataModel();
		}
		return items;
	}

	private void recreateModel() {
		items = null;
	}

	public PageNavigation next() {
		getPagination().nextPage();
		recreateModel();
		return PageNavigation.LIST;
	}

	public PageNavigation previous() {
		getPagination().previousPage();
		recreateModel();
		return PageNavigation.LIST;
	}

	public SelectItem[] getItemsAvailableSelectMany() {
		return JsfUtil.getSelectItems(ejbFacade.getAll(), false);
	}

	public SelectItem[] getItemsAvailableSelectOne() {
		return JsfUtil.getSelectItems(ejbFacade.getAll(), true);
	}

	@FacesConverter(forClass = OrderDetail.class)
	public static class OrderDetailControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			OrderDetailController controller = (OrderDetailController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"orderDetailController");
			return controller.ejbFacade.getOrderDetail(controller.ejbFacade.getKey(value));
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof OrderDetail) {
				OrderDetail o = (OrderDetail) object;
				OrderDetailController controller = (OrderDetailController) facesContext
						.getApplication()
						.getELResolver()
						.getValue(facesContext.getELContext(), null,
								"orderDetailController");
				return controller.ejbFacade.getStringKey(o.getOrderDetailPK());
			} else {
				throw new IllegalArgumentException("object " + object
						+ " is of type " + object.getClass().getName()
						+ "; expected type: "
						+ OrderDetailController.class.getName());
			}
		}
	}
}
