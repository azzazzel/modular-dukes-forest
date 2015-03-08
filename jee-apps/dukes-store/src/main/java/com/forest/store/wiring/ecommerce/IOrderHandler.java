/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.store.wiring.ecommerce;

import com.forest.events.OrderEvent;

/**
 *
 * @author markito
 */
public interface IOrderHandler  {
    
    public void onNewOrder(OrderEvent event);
    
}
