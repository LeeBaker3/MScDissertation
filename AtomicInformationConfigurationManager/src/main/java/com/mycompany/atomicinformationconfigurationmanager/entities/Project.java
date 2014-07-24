/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lee Baker
 */
@Entity
@Table(name = "project")
@AttributeOverride(name = "id", column = @Column(name = "ProjectID"))

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByProjectID", query = "SELECT p FROM Project p WHERE p.id = :projectID"),
    @NamedQuery(name = "Project.findByProjectReference", query = "SELECT p FROM Project p WHERE p.projectReference = :projectReference"),
    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "Project.findByVersionNumber", query = "SELECT p FROM Project p WHERE p.versionNumber = :versionNumber"),
    @NamedQuery(name = "Project.findByIsCurrentVersion", query = "SELECT p FROM Project p WHERE p.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Project.findByPreviousVersionReference", query = "SELECT p FROM Project p WHERE p.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Project.findByEntityActive", query = "SELECT p FROM Project p WHERE p.entityActive = :entityActive")})
public class Project extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(max = 45)
    @Column(name = "ProjectReference")
    private String projectReference;
    @Size(max = 45)
    @Column(name = "ProjectName")
    private String projectName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Atomicinformation> atomicinformationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Artefact> artefactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Distributionrecipient> distributionrecipientCollection;

    public Project() {
    }

    public Project(Integer projectID) {
        this.id = projectID;
    }

    public Project(Integer projectID, int versionNumber, boolean isCurrentVersion, boolean entityActive) {
        this.id = projectID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @XmlTransient
    public Collection<Atomicinformation> getAtomicinformationCollection() {
        return atomicinformationCollection;
    }

    public void setAtomicinformationCollection(Collection<Atomicinformation> atomicinformationCollection) {
        this.atomicinformationCollection = atomicinformationCollection;
    }

    @XmlTransient
    public Collection<Artefact> getArtefactCollection() {
        return artefactCollection;
    }

    public void setArtefactCollection(Collection<Artefact> artefactCollection) {
        this.artefactCollection = artefactCollection;
    }

    @XmlTransient
    public Collection<Distributionrecipient> getDistributionrecipientCollection() {
        return distributionrecipientCollection;
    }

    public void setDistributionrecipientCollection(Collection<Distributionrecipient> distributionrecipientCollection) {
        this.distributionrecipientCollection = distributionrecipientCollection;
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.atomicinformationconfigurationmanager.entities.Project[ projectID=" + id + " ]";
    }
    
    @Override
    public String getColumnString() {
        return getProjectName() + " " + getProjectReference();
    } 
}
