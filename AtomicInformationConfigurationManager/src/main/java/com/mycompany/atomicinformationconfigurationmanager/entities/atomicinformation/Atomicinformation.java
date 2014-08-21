/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.typeofatomicinformation.Typeofatomicinformation;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee Baker
 */
@Entity
@Table(name = "atomicinformation")
@AttributeOverride (name = "id", column = @Column(name = "AtomicInformationID"))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndAtomicInformationIDAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.id = :atomicInformationID AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndVersionNumberAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.versionNumber = :versionNumber AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.isCurrentVersion = :isCurrentVersion AND a.entityActive = :entityActive"),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndPreviousVersionReferenceAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.previousVersionReference = :previousVersionReference AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndEntityActiveAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.entityActive = :entityActive "),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndContentAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.content = :content AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.entityActive = :entityActive AND a.projectID = :projectID AND a.isCurrentVersion = :isCurrentVersion"),
    
    
    @NamedQuery(name = "Atomicinformation.findAll", query = "SELECT a FROM Atomicinformation a"),
    @NamedQuery(name = "Atomicinformation.findByEntityActive", query = "SELECT a FROM Atomicinformation a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Atomicinformation.findByAtomicInformationID", query = "SELECT a FROM Atomicinformation a WHERE a.id = :atomicInformationID"),
    @NamedQuery(name = "Atomicinformation.findByVersionNumber", query = "SELECT a FROM Atomicinformation a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Atomicinformation.findByIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByPreviousVersionReference", query = "SELECT a FROM Atomicinformation a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Atomicinformation.findByEntityActive", query = "SELECT a FROM Atomicinformation a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Atomicinformation.findByContent", query = "SELECT a FROM Atomicinformation a WHERE a.content = :content")})
public class Atomicinformation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "TypeOfAtomicInformationID", referencedColumnName = "TypeOfAtomicInformationID")
    @ManyToOne(optional = false)
    private Typeofatomicinformation typeOfAtomicInformationID;
    @JoinColumn(name = "ProjectID", referencedColumnName = "ProjectID")
    @ManyToOne(optional = false)
    private Project projectID;

    public Atomicinformation() {
    }

    public Atomicinformation(Integer atomicInformationID) {
        this.id = atomicInformationID;
    }

    public Atomicinformation(Integer atomicInformationID, int versionNumber, boolean isCurrentVersion, boolean entityActive, String content) {
        this.id = atomicInformationID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Typeofatomicinformation getTypeOfAtomicInformationID() {
        return typeOfAtomicInformationID;
    }

    public void setTypeOfAtomicInformationID(Typeofatomicinformation typeOfAtomicInformationID) {
        this.typeOfAtomicInformationID = typeOfAtomicInformationID;
    }

    public Project getProjectID() {
        return projectID;
    }

    public void setProjectID(Project projectID) {
        this.projectID = projectID;
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
        if (!(object instanceof Atomicinformation)) {
            return false;
        }
        Atomicinformation other = (Atomicinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.typeOfAtomicInformationID.toString() + ":" + getContent();
    }
}
