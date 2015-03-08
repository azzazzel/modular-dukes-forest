/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.ui.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.forest.model.Customer;
import com.forest.model.Person;
import com.forest.store.qualifiers.LoggedIn;
import com.forest.store.ui.util.AbstractPaginationHelper;
import com.forest.store.ui.util.JsfUtil;
import com.forest.store.ui.util.MD5Util;
import com.forest.store.ui.util.PageNavigation;
import com.forest.store.wiring.identity.UserBean;

@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 2081269066939259737L;

	@Inject
	@LoggedIn
	Person authenticated;
	private Customer current;
	private DataModel items = null;
	@EJB
	private com.forest.store.wiring.identity.UserBean ejbFacade;

	private static final Logger logger = Logger
			.getLogger(CustomerController.class.getCanonicalName());

	private AbstractPaginationHelper pagination;
	private int selectedItemIndex;

	public CustomerController() {
	}

	public Customer getSelected() {
		if (current == null) {
			current = ejbFacade.newCustomerInstance();
			selectedItemIndex = -1;
		}
		return current;
	}

	private UserBean getFacade() {
		return ejbFacade;
	}

	public AbstractPaginationHelper getPagination() {
		if (pagination == null) {
			pagination = new AbstractPaginationHelper(
					AbstractPaginationHelper.DEFAULT_SIZE) {

				@Override
				public int getItemsCount() {
					return getFacade().count();
				}

				@Override
				public DataModel createPageDataModel() {
					return new ListDataModel(getFacade().getAllInRange(
							getPageFirstItem(),
							getPageFirstItem() + getPageSize()));
					// return new ListDataModel(getFacade().findAll());
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
		current = (Customer) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.VIEW;
	}

	public PageNavigation prepareCreate() {
		current = ejbFacade.newCustomerInstance();
		selectedItemIndex = -1;
		return PageNavigation.CREATE;
	}

	private boolean isUserDuplicated(Person p) {
		return (getFacade().getPersonByEmail(p.getEmail()) == null) ? false
				: true;
	}

	public PageNavigation create() {
		try {
			if (!isUserDuplicated(current)) {
				// password encrypt
				current.setPassword(MD5Util.generateMD5(current.getPassword()));
				getFacade().createPerson(current);
				JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
						.getString("CustomerCreated"));

			} else {
				JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE)
						.getString("DuplicatedCustomerError"));

			}
			// return prepareCreate();
			return PageNavigation.INDEX;
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("CustomerCreationError"));
			return null;
		}
	}

	public PageNavigation prepareEdit() {
		current = (Customer) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.EDIT;
	}

	public PageNavigation update() {
		try {
			logger.log(Level.INFO, "Updating customer ID:{0}", current.getId());
			getFacade().updatePerson(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("CustomerUpdated"));
			return PageNavigation.VIEW;
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public PageNavigation destroy() {
		current = (Customer) getItems().getRowData();
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
			getFacade().removePerson(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("CustomerDeleted"));
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
			current = (Customer) getFacade().getAllInRange (selectedItemIndex,
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

	@FacesConverter(forClass = Customer.class)
	public static class CustomerControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			CustomerController controller = (CustomerController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"customerController");
			return controller.ejbFacade.getPerson(getKey(value));
		}

		java.lang.Integer getKey(String value) {
			java.lang.Integer key;
			key = Integer.valueOf(value);
			return key;
		}

		String getStringKey(java.lang.Integer value) {
			StringBuilder sb = new StringBuilder();
			sb.append(value);
			return sb.toString();
		}

		@Override
		public String getAsString(FacesContext facesContext,
				UIComponent component, Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Customer) {
				Customer o = (Customer) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object
						+ " is of type " + object.getClass().getName()
						+ "; expected type: "
						+ CustomerController.class.getName());
			}
		}
	}

	public void setCustomer(Customer user) {
		this.authenticated = user;
	}

	public Person getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Person p) {
		this.authenticated = p;
	}
}
