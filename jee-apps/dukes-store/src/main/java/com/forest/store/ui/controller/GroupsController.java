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

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.forest.model.Group;
import com.forest.store.ui.util.AbstractPaginationHelper;
import com.forest.store.ui.util.JsfUtil;
import com.forest.store.ui.util.PageNavigation;
import com.forest.store.wiring.identity.GroupsBean;

@Named(value = "groupsController")
@SessionScoped
public class GroupsController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 915049365977089806L;

	private Group current;
	private DataModel items = null;
	@EJB
	private com.forest.store.wiring.identity.GroupsBean ejbFacade;
	private AbstractPaginationHelper pagination;
	private int selectedItemIndex;

	public GroupsController() {
	}

	public Group getSelected() {
		if (current == null) {
			current = ejbFacade.newGroupInstance();
			selectedItemIndex = -1;
		}
		return current;
	}

	private GroupsBean getFacade() {
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
					return new ListDataModel(getFacade().getAllInRange(
							getPageFirstItem(),
							getPageFirstItem() + getPageSize()));
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
		current = (Group) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.VIEW;
	}

	public PageNavigation prepareCreate() {
		current = ejbFacade.newGroupInstance();
		selectedItemIndex = -1;
		return PageNavigation.CREATE;
	}

	public PageNavigation create() {
		try {
			getFacade().createGroup(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("GroupsCreated"));
			return prepareCreate();
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public PageNavigation prepareEdit() {
		current = (Group) getItems().getRowData();
		selectedItemIndex = pagination.getPageFirstItem()
				+ getItems().getRowIndex();
		return PageNavigation.EDIT;
	}

	public PageNavigation update() {
		try {
			getFacade().updateGroup(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("GroupsUpdated"));
			return PageNavigation.VIEW;
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE)
					.getString("PersistenceErrorOccured"));
			return null;
		}
	}

	public PageNavigation destroy() {
		current = (Group) getItems().getRowData();
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
			getFacade().removeGroup(current);
			JsfUtil.addSuccessMessage(ResourceBundle.getBundle(BUNDLE)
					.getString("GroupsDeleted"));
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

	@FacesConverter(forClass = Group.class)
	public static class GroupsControllerConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext facesContext,
				UIComponent component, String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			GroupsController controller = (GroupsController) facesContext
					.getApplication()
					.getELResolver()
					.getValue(facesContext.getELContext(), null,
							"groupsController");
			return controller.ejbFacade.getGroup(getKey(value));
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
			if (object instanceof Group) {
				Group o = (Group) object;
				return getStringKey(o.getId());
			} else {
				throw new IllegalArgumentException("object " + object
						+ " is of type " + object.getClass().getName()
						+ "; expected type: "
						+ GroupsController.class.getName());
			}
		}
	}
}
