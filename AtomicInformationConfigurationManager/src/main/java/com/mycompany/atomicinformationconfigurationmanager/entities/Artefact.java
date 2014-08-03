/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lee Baker
 */
@Entity
@Table(name = "artefact")
@AttributeOverride (name = "id", column = @Column(name = "ArtefactID"))

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artefact.findAll", query = "SELECT a FROM Artefact a"),
    @NamedQuery(name = "Artefact.findAllEntityActive", query = "SELECT a FROM Artefact a WHERE a.entityActive = TRUE"),
    @NamedQuery(name = "Artefact.findByProjectID", query = "SELECT a FROM Artefact a WHERE a.projectID = :projectID"),
    @NamedQuery(name = "Artefact.findByArtefactID", query = "SELECT a FROM Artefact a WHERE a.id = :artefactID"),
    @NamedQuery(name = "Artefact.findByVersionNumber", query = "SELECT a FROM Artefact a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Artefact.findByIsCurrentVersion", query = "SELECT a FROM Artefact a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefact.findByPreviousVersionReference", query = "SELECT a FROM Artefact a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Artefact.findByEntityActive", query = "SELECT a FROM Artefact a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefact.findByArtefactName", query = "SELECT a FROM Artefact a WHERE a.artefactName = :artefactName"),
    @NamedQuery(name = "Artefact.findByArtefactMajorVersionNumber", query = "SELECT a FROM Artefact a WHERE a.artefactMajorVersionNumber = :artefactMajorVersionNumber"),
    @NamedQuery(name = "Artefact.findByArtefactMinorVersionNumber", query = "SELECT a FROM Artefact a WHERE a.artefactMinorVersionNumber = :artefactMinorVersionNumber")})
public class Artefact extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ArtefactName")
    private String artefactName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ArtefactMajorVersionNumber")
    private String artefactMajorVersionNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ArtefactMinorVersionNumber")
    private String artefactMinorVersionNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artefactID")
    private Collection<Atomicinformation> atomicinformationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artefactID")
    private Collection<Artefactdistribution> artefactdistributionCollection;
    @JoinColumn(name = "ProjectID", referencedColumnName = "ProjectID")
    @ManyToOne(optional = false)
    private Project projectID;

    public Artefact() {
    }

    public Artefact(Integer artefactID) {
        this.id = artefactID;
    }

    public Artefact(Integer artefactID, int versionNumber, boolean isCurrentVersion, boolean entityActive, String artefactName, String artefactMajorVersionNumber, String artefactMinorVersionNumber) {
        this.id = artefactID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.artefactName = artefactName;
        this.artefactMajorVersionNumber = artefactMajorVersionNumber;
        this.artefactMinorVersionNumber = artefactMinorVersionNumber;
    }

    public String getArtefactName() {
        return artefactName;
    }

    public void setArtefactName(String artefactName) {
        this.artefactName = artefactName;
    }

    public String getArtefactMajorVersionNumber() {
        return artefactMajorVersionNumber;
    }

    public void setArtefactMajorVersionNumber(String artefactMajorVersionNumber) {
        this.artefactMajorVersionNumber = artefactMajorVersionNumber;
    }

    public String getArtefactMinorVersionNumber() {
        return artefactMinorVersionNumber;
    }

    public void setArtefactMinorVersionNumber(String artefactMinorVersionNumber) {
        this.artefactMinorVersionNumber = artefactMinorVersionNumber;
    }

    @XmlTransient
    public Collection<Atomicinformation> getAtomicinformationCollection() {
        return atomicinformationCollection;
    }

    public void setAtomicinformationCollection(Collection<Atomicinformation> atomicinformationCollection) {
        this.atomicinformationCollection = atomicinformationCollection;
    }

    @XmlTransient
    public Collection<Artefactdistribution> getArtefactdistributionCollection() {
        return artefactdistributionCollection;
    }

    public void setArtefactdistributionCollection(Collection<Artefactdistribution> artefactdistributionCollection) {
        this.artefactdistributionCollection = artefactdistributionCollection;
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
        if (!(object instanceof Artefact)) {
            return false;
        }
        Artefact other = (Artefact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getArtefactName();
    }
}
