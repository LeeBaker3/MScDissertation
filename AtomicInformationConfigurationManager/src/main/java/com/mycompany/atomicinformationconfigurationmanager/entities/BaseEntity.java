/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Lee Baker
*/
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Basic(optional = false)
protected Integer id;

@Basic(optional = false)
@NotNull
@Column(name = "VersionNumber")
protected int versionNumber;

@Basic(optional = false)
@NotNull
@Column(name = "IsCurrentVersion")
protected boolean isCurrentVersion;

@Size(max = 45)
@Column(name = "PreviousVersionReference")
protected String previousVersionReference;

@Basic(optional = false)
@NotNull
@Column(name = "EntityActive")
protected boolean entityActive;

/*  The columnString variable is used to build the entity identifier
    that will be displayed to the user but doesn't contain the fully
    qualified name of the toString method
*/
@Transient
protected String columnString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int VersionNumber) {
        this.versionNumber = VersionNumber;
    }

    public boolean isIsCurrentVersion() {
        return isCurrentVersion;
    }

    public void setIsCurrentVersion(boolean IsCurrentVersion) {
        this.isCurrentVersion = IsCurrentVersion;
    }

    public String getPreviousVersionReference() {
        return previousVersionReference;
    }

    public void setPreviousVersionReference(String PreviousVersionReference) {
        this.previousVersionReference = PreviousVersionReference;
    }

    public boolean isEntityActive() {
        return entityActive;
    }

    public void setEntityActive(boolean EntityActive) {
        this.entityActive = EntityActive;
    }
    
    public String getColumnString() {
        return columnString;
    }

}
