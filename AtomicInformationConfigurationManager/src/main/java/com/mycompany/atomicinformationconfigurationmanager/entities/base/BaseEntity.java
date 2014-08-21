/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

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
public class BaseEntity implements Serializable, Cloneable{

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

@Column(name = "PreviousVersionReference")
protected Integer previousVersionReference;

@Basic(optional = false)
@NotNull
@Column(name = "EntityActive")
protected boolean entityActive;

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

    public Integer getPreviousVersionReference() {
        return previousVersionReference;
    }

    public void setPreviousVersionReference(Integer PreviousVersionReference) {
        this.previousVersionReference = PreviousVersionReference;
    }

    public boolean isEntityActive() {
        return entityActive;
    }

    public void setEntityActive(boolean EntityActive) {
        this.entityActive = EntityActive;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
