/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artefactdistribution.findAll", query = "SELECT a FROM Artefactdistribution a"),
    @NamedQuery(name = "Artefactdistribution.findByArtefactDistributionID", query = "SELECT a FROM Artefactdistribution a WHERE a.artefactDistributionID = :artefactDistributionID"),
    @NamedQuery(name = "Artefactdistribution.findByMethodOfDistributionID", query = "SELECT a FROM Artefactdistribution a WHERE a.methodOfDistributionID = :methodOfDistributionID"),
    @NamedQuery(name = "Artefactdistribution.findByVersionNumber", query = "SELECT a FROM Artefactdistribution a WHERE a.versionNumber = :versionNumber"),
    @NamedQuery(name = "Artefactdistribution.findByIsCurrentVersion", query = "SELECT a FROM Artefactdistribution a WHERE a.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Artefactdistribution.findByPreviousVersionReference", query = "SELECT a FROM Artefactdistribution a WHERE a.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Artefactdistribution.findByEntityActive", query = "SELECT a FROM Artefactdistribution a WHERE a.entityActive = :entityActive"),
    @NamedQuery(name = "Artefactdistribution.findByDateOfArtefactDistribution", query = "SELECT a FROM Artefactdistribution a WHERE a.dateOfArtefactDistribution = :dateOfArtefactDistribution")})
public class Artefactdistribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArtefactDistributionID")
    private Integer artefactDistributionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MethodOfDistributionID")
    private int methodOfDistributionID;
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
        this.artefactDistributionID = artefactDistributionID;
    }

    public Artefactdistribution(Integer artefactDistributionID, int methodOfDistributionID, int versionNumber, boolean isCurrentVersion, boolean entityActive, Date dateOfArtefactDistribution) {
        this.artefactDistributionID = artefactDistributionID;
        this.methodOfDistributionID = methodOfDistributionID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.dateOfArtefactDistribution = dateOfArtefactDistribution;
    }

    public Integer getArtefactDistributionID() {
        return artefactDistributionID;
    }

    public void setArtefactDistributionID(Integer artefactDistributionID) {
        this.artefactDistributionID = artefactDistributionID;
    }

    public int getMethodOfDistributionID() {
        return methodOfDistributionID;
    }

    public void setMethodOfDistributionID(int methodOfDistributionID) {
        this.methodOfDistributionID = methodOfDistributionID;
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
        hash += (artefactDistributionID != null ? artefactDistributionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artefactdistribution)) {
            return false;
        }
        Artefactdistribution other = (Artefactdistribution) object;
        if ((this.artefactDistributionID == null && other.artefactDistributionID != null) || (this.artefactDistributionID != null && !this.artefactDistributionID.equals(other.artefactDistributionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Artefactdistribution[ artefactDistributionID=" + artefactDistributionID + " ]";
    }
    
}
