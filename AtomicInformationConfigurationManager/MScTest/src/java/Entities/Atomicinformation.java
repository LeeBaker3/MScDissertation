/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atomicinformation.findAll", query = "SELECT a FROM Atomicinformation a"),
    @NamedQuery(name = "Atomicinformation.findByAtomicInformationID", query = "SELECT a FROM Atomicinformation a WHERE a.atomicInformationID = :atomicInformationID"),
    @NamedQuery(name = "Atomicinformation.findByVersionNumber", query = "SELECT a FROM Atomicinformation a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Atomicinformation.findByIsCurrentVersion", query = "SELECT a FROM Atomicinformation a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Atomicinformation.findByPreviousVersionReference", query = "SELECT a FROM Atomicinformation a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Atomicinformation.findByEntityActive", query = "SELECT a FROM Atomicinformation a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Atomicinformation.findByContent", query = "SELECT a FROM Atomicinformation a WHERE a.content = :content")})
public class Atomicinformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AtomicInformationID")
    private Integer atomicInformationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VersionNumber")
    private int versionNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsCurrentVersion")
    private boolean isCurrentVersion;
    @Size(max = 45)
    @Column(name = "PreviousVersionReference")
    private String previousVersionReference;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EntityActive")
    private boolean entityActive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Content")
    private String content;
    @JoinColumn(name = "TypeOfAtomicInformationID", referencedColumnName = "TypeOfAtomicInformationID")
    @ManyToOne(optional = false)
    private Typeofatomicinformation typeOfAtomicInformationID;
    @JoinColumn(name = "ArtefactID", referencedColumnName = "ArtefactID")
    @ManyToOne(optional = false)
    private Artefact artefactID;
    @JoinColumn(name = "ProjectID", referencedColumnName = "ProjectID")
    @ManyToOne(optional = false)
    private Project projectID;

    public Atomicinformation() {
    }

    public Atomicinformation(Integer atomicInformationID) {
        this.atomicInformationID = atomicInformationID;
    }

    public Atomicinformation(Integer atomicInformationID, int versionNumber, boolean isCurrentVersion, boolean entityActive, String content) {
        this.atomicInformationID = atomicInformationID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.content = content;
    }

    public Integer getAtomicInformationID() {
        return atomicInformationID;
    }

    public void setAtomicInformationID(Integer atomicInformationID) {
        this.atomicInformationID = atomicInformationID;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public boolean getIsCurrentVersion() {
        return isCurrentVersion;
    }

    public void setIsCurrentVersion(boolean isCurrentVersion) {
        this.isCurrentVersion = isCurrentVersion;
    }

    public String getPreviousVersionReference() {
        return previousVersionReference;
    }

    public void setPreviousVersionReference(String previousVersionReference) {
        this.previousVersionReference = previousVersionReference;
    }

    public boolean getEntityActive() {
        return entityActive;
    }

    public void setEntityActive(boolean entityActive) {
        this.entityActive = entityActive;
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

    public Artefact getArtefactID() {
        return artefactID;
    }

    public void setArtefactID(Artefact artefactID) {
        this.artefactID = artefactID;
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
        hash += (atomicInformationID != null ? atomicInformationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atomicinformation)) {
            return false;
        }
        Atomicinformation other = (Atomicinformation) object;
        if ((this.atomicInformationID == null && other.atomicInformationID != null) || (this.atomicInformationID != null && !this.atomicInformationID.equals(other.atomicInformationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Atomicinformation[ atomicInformationID=" + atomicInformationID + " ]";
    }
    
}
