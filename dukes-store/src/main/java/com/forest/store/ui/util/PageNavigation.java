/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.web.util;

/**
 * Simple ENUM to centralize strings for common navigation destinations
 *
 * @author markito
 */
public enum PageNavigation {

    CREATE("Create"),
    LIST("List"),
    EDIT("Edit"),
    VIEW("View"),
    INDEX("index");
    private String text;

    PageNavigation(final String s) {
        this.text = s;
    }

    public String getText() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
