/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "artefactatomicinformation")
@AttributeOverride (name = "id", column = @Column(name = "ArtefactAtomicInformationID"))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActiveAndArtefactIDAndIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.artefactID = :artefactID AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActiveAndArtefactAtomicInformationIDAndIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.id = :artefactAtomicInformationID AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActiveAndVersionNumberAndIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.versionNumber = :versionNumber AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActiveAndIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.isCurrentVersion = :isCurrentVersion AND a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActiveAndPreviousVersionReferenceAndIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.previousVersionReference = :previousVersionReference AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Artefactatomicinformation.findAll", query = "SELECT a FROM Artefactatomicinformation a"),
    @NamedQuery(name = "Artefactatomicinformation.findByArtefactID", query = "SELECT a FROM Artefactatomicinformation a WHERE a.artefactID = :artefactID"),
    @NamedQuery(name = "Artefactatomicinformation.findByArtefactAtomicInformationID", query = "SELECT a FROM Artefactatomicinformation a WHERE a.id = :artefactAtomicInformationID"),
    @NamedQuery(name = "Artefactatomicinformation.findByVersionNumber", query = "SELECT a FROM Artefactatomicinformation a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Artefactatomicinformation.findByIsCurrentVersion", query = "SELECT a FROM Artefactatomicinformation a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactatomicinformation.findByPreviousVersionReference", query = "SELECT a FROM Artefactatomicinformation a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Artefactatomicinformation.findByEntityActive", query = "SELECT a FROM Artefactatomicinformation a WHERE a.entityActive = :entityActive")})
public class Artefactatomicinformation extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "ArtefactID", referencedColumnName = "ArtefactID")
    @ManyToOne(optional = false)
    private Artefact artefactID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atomicInformationID")
    private Collection<Artefactatomicinformation> artefactatomicinformationCollection;
    @JoinColumn(name = "AtomicInformationID", referencedColumnName = "AtomicInformationID")
    @ManyToOne(optional = false)
    private Atomicinformation atomicInformationID;

    public Artefactatomicinformation() {
    }

    public Artefactatomicinformation(Integer artefactAtomicInformationID) {
        this.id = artefactAtomicInformationID;
    }

    public Artefactatomicinformation(Integer artefactAtomicInformationID, int versionNumber, boolean isCurrentVersion, boolean entityActive) {
        this.id = artefactAtomicInformationID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
    }

    public Integer getArtefactAtomicInformationID() {
        return id;
    }

    public void setArtefactAtomicInformationID(Integer artefactAtomicInformationID) {
        this.id = artefactAtomicInformationID;
    }

    public Artefact getArtefactID() {
        return artefactID;
    }

    public void setArtefactID(Artefact artefactID) {
        this.artefactID = artefactID;
    }

    @XmlTransient
    public Collection<Artefactatomicinformation> getArtefactatomicinformationCollection() {
        return artefactatomicinformationCollection;
    }

    public void setArtefactatomicinformationCollection(Collection<Artefactatomicinformation> artefactatomicinformationCollection) {
        this.artefactatomicinformationCollection = artefactatomicinformationCollection;
    }

    public Atomicinformation getAtomicInformationID() {
        return atomicInformationID;
    }

    public void setAtomicInformationID(Atomicinformation atomicInformationID) {
        this.atomicInformationID = atomicInformationID;
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
        if (!(object instanceof Artefactatomicinformation)) {
            return false;
        }
        Artefactatomicinformation other = (Artefactatomicinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return artefactID.getArtefactName() + ":" + atomicInformationID.getContent();
    }
    
}
