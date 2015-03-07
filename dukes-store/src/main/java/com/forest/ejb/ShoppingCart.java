/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.forest.entity.CustomerOrderEntity;
import com.forest.entity.OrderDetailEntity;
import com.forest.entity.OrderDetailPKEntity;
import com.forest.entity.OrderStatusEntity;
import com.forest.entity.PersonEntity;
import com.forest.entity.ProductEntity;
import com.forest.events.OrderEvent;
import com.forest.model.Group;
import com.forest.model.OrderDetail;
import com.forest.qualifiers.LoggedIn;
import com.forest.web.util.JsfUtil;
import com.forest.web.util.PageNavigation;

@Named(value = "shoppingCart")
@ConversationScoped
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 3313992336071349028L;
    @Inject
    Conversation conversation;
    @EJB
    OrderBean facade;
    @Inject
    @LoggedIn
    PersonEntity user;
    private static final Logger LOGGER = Logger.getLogger(ShoppingCart.class.getCanonicalName());
    private List<ProductEntity> cartItems;
    @EJB
    EventDispatcherBean eventDispatcher;

    public void init() {
        cartItems = new ArrayList<>();
    }

    public String addItem(final ProductEntity p) {

        if (cartItems == null) {
            cartItems = new ArrayList<>();
            if (conversation.isTransient()) {
                conversation.begin();
            }
        }

        LOGGER.log(Level.FINEST, "Adding product {0}", p.getName());
        LOGGER.log(Level.FINEST, "Cart Size: {0}", cartItems.size());

        if (!cartItems.contains(p)) {
            cartItems.add(p);
        }

        return "";
    }

    public boolean removeItem(ProductEntity p) {
        if (cartItems.contains(p)) {
            return cartItems.remove(p);
        } else {
            // no items removed
            return false;
        }
    }

    public double getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0f;
        }

        double total = 0f;
        for (ProductEntity item : cartItems) {
            total += item.getPrice();
        }

        LOGGER.log(Level.FINEST, "Actual Total:{0}", total);
        return total;
    }

    /**
     * This annotation will mark the ejb to be removed from memory
     */
    public PageNavigation checkout() {

        if (user == null) {
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle("bundles.Bundle", "LoginBeforeCheckout"));

        } else {

            for (Group g : user.getGroupsList()) {
                if (g.getName().equals("ADMINS")) {

                    JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle("bundles.Bundle", "AdministratorNotAllowed"));
                    return PageNavigation.INDEX;
                }
            }

            CustomerOrderEntity order = new CustomerOrderEntity();
            List<OrderDetail> details = new ArrayList<>();

            OrderStatusEntity orderStatus = new OrderStatusEntity();
            orderStatus.setId(1); //by default the initial status

            order.setDateCreated(Calendar.getInstance().getTime());
            order.setOrderStatus(orderStatus);
            order.setAmount(getTotal());
            order.setCustomer(user);

            facade.createCustomerOrder(order);

            for (ProductEntity p : getCartItems()) {
                OrderDetailEntity detail = new OrderDetailEntity();

                OrderDetailPKEntity pk = new OrderDetailPKEntity(order.getId(), p.getId());
                //TODO: next version will handle qty on shoppingCart 
                detail.setQty(1);
                detail.setProduct(p);
                //detail.setCustomerOrder(order);
                detail.setOrderDetailPK(pk);

                details.add(detail);
            }

            order.setOrderDetailList(details);
            facade.updateCustomerOrder(order);

            OrderEvent event = orderToEvent(order);

            LOGGER.log(Level.FINEST, "{0} Sending event from ShoppingCart", Thread.currentThread().getName());
            eventDispatcher.publish(event);

            JsfUtil.addSuccessMessage(JsfUtil.getStringFromBundle("bundles.Bundle", "Cart_Checkout_Success"));
            clear();
        }


        return PageNavigation.INDEX;
    }

    public void clear() {
        cartItems.clear();
    }

    public List<ProductEntity> getCartItems() {
        return cartItems;
    }

    public Conversation getConversation() {
        return conversation;
    }

    private OrderEvent orderToEvent(CustomerOrderEntity order) {
        OrderEvent event = new OrderEvent();

        event.setAmount(order.getAmount());
        event.setCustomerID(order.getCustomer().getId());
        event.setDateCreated(order.getDateCreated());
        event.setStatusID(order.getOrderStatus().getId());
        event.setOrderID(order.getId());

        return event;
    }
}
