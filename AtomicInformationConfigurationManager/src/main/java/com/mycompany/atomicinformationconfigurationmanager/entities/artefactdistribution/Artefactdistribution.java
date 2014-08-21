/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient.Distributionrecipient;
import com.mycompany.atomicinformationconfigurationmanager.entities.methodofdistribution.Methodofdistribution;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lee Baker
 */
@Entity
@Table(name = "artefactdistribution")
@AttributeOverride (name = "id", column = @Column(name = "ArtefactDistributionID"))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndArtefactDistributionIDAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.id = :artefactDistributionID AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndMethodOfDistributionIDAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.methodOfDistributionID = :methodOfDistributionID AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndVersionNumberAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.versionNumber = :versionNumber AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.isCurrentVersion = :isCurrentVersion AND a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndPreviousVersionReferenceAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.previousVersionReference = :previousVersionReference AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.entityActive = :entityActive AND a.artefactID = :artefactID AND a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActiveAndDateOfArtefactDistributionAndIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.dateOfArtefactDistribution = :dateOfArtefactDistribution AND a.entityActive = :entityActive AND a.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Artefactdistribution.findAll", query = "SELECT a FROM Artefactdistribution a"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActive", query = "SELECT a FROM Artefactdistribution a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefactdistribution.findByArtefactDistributionID", query = "SELECT a FROM Artefactdistribution a WHERE a.id = :artefactDistributionID"),
    @NamedQuery(name = "Artefactdistribution.findByMethodOfDistributionID", query = "SELECT a FROM Artefactdistribution a WHERE a.methodOfDistributionID = :methodOfDistributionID"),
    @NamedQuery(name = "Artefactdistribution.findByVersionNumber", query = "SELECT a FROM Artefactdistribution a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Artefactdistribution.findByIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByPreviousVersionReference", query = "SELECT a FROM Artefactdistribution a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActive", query = "SELECT a FROM Artefactdistribution a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefactdistribution.findByDateOfArtefactDistribution", query = "SELECT a FROM Artefactdistribution a WHERE a.dateOfArtefactDistribution = :dateOfArtefactDistribution")})
public class Artefactdistribution extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "MethodOfDistributionID", referencedColumnName = "MethodOfDistributionID")
    @ManyToOne(optional = false)
    private Methodofdistribution methodOfDistributionID;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOfArtefactDistribution")
    @Temporal(TemporalType.DATE)
    private Date dateOfArtefactDistribution;
    @JoinColumn(name = "DistributionRecipientID", referencedColumnName = "DistributionRecipientID")
    @ManyToOne(optional = false)
    private Distributionrecipient distributionRecipientID;
    @JoinColumn(name = "ArtefactID", referencedColumnName = "ArtefactID")
    @ManyToOne(optional = false)
    private Artefact artefactID;

    public Artefactdistribution() {
    }

    public Artefactdistribution(Integer artefactDistributionID) {
        this.id = artefactDistributionID;
    }

    public Artefactdistribution(Integer artefactDistributionID, int methodOfDistributionID, int versionNumber, boolean isCurrentVersion, boolean entityActive, Date dateOfArtefactDistribution) {
        this.id = artefactDistributionID;
        //this.methodOfDistributionID = methodOfDistributionID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.dateOfArtefactDistribution = dateOfArtefactDistribution;
    }

    public Integer getArtefactDistributionID() {
        return id;
    }

    public void setArtefactDistributionID(Integer artefactDistributionID) {
        this.id = artefactDistributionID;
    }

    public Methodofdistribution getMethodOfDistributionID() {
        return methodOfDistributionID;
    }

    public void setMethodOfDistributionID(Methodofdistribution methodOfDistributionID) {
        this.methodOfDistributionID = methodOfDistributionID;
    }

    public Date getDateOfArtefactDistribution() {
        return dateOfArtefactDistribution;
    }

    public void setDateOfArtefactDistribution(Date dateOfArtefactDistribution) {
        this.dateOfArtefactDistribution = dateOfArtefactDistribution;
    }

    public Distributionrecipient getDistributionRecipientID() {
        return distributionRecipientID;
    }

    public void setDistributionRecipientID(Distributionrecipient distributionRecipientID) {
        this.distributionRecipientID = distributionRecipientID;
    }

    public Artefact getArtefactID() {
        return artefactID;
    }

    public void setArtefactID(Artefact artefactID) {
        this.artefactID = artefactID;
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
        if (!(object instanceof Artefactdistribution)) {
            return false;
        }
        Artefactdistribution other = (Artefactdistribution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.artefactID.getArtefactName() + "distributed to " + this.distributionRecipientID.toString();
    }
}
