/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.forest.entity;

import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author ievans
 */
@Entity
public class AdministratorEntity extends PersonEntity {
    
    private static final long serialVersionUID = 7261229187771153310L;

    public AdministratorEntity() {
        this.groupsList = new ArrayList<GroupsEntity>();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministratorEntity)) {
            return false;
        }
        AdministratorEntity other = (AdministratorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.forest.entity.Administrator[ id=" + id + " ]";
    }
    
}
