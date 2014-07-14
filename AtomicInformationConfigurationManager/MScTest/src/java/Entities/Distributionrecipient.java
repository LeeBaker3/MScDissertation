/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "distributionrecipient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributionrecipient.findAll", query = "SELECT d FROM Distributionrecipient d"),
    @NamedQuery(name = "Distributionrecipient.findByDistributionRecipientID", query = "SELECT d FROM Distributionrecipient d WHERE d.distributionRecipientID = :distributionRecipientID"),
    @NamedQuery(name = "Distributionrecipient.findByVersionNumber", query = "SELECT d FROM Distributionrecipient d WHERE d.versionNumber = :versionNumber"),
    @NamedQuery(name = "Distributionrecipient.findByIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByPreviousVersionReference", query = "SELECT d FROM Distributionrecipient d WHERE d.previousVersionReference = :previousVersionReference"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActive", query = "SELECT d FROM Distributionrecipient d WHERE d.entityActive = :entityActive"),
    @NamedQuery(name = "Distributionrecipient.findByFirstName", query = "SELECT d FROM Distributionrecipient d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "Distributionrecipient.findBySurname", query = "SELECT d FROM Distributionrecipient d WHERE d.surname = :surname"),
    @NamedQuery(name = "Distributionrecipient.findByEMailAddress", query = "SELECT d FROM Distributionrecipient d WHERE d.eMailAddress = :eMailAddress"),
    @NamedQuery(name = "Distributionrecipient.findByMobileNumber", query = "SELECT d FROM Distributionrecipient d WHERE d.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "Distributionrecipient.findByOfficeNumber", query = "SELECT d FROM Distributionrecipient d WHERE d.officeNumber = :officeNumber"),
    @NamedQuery(name = "Distributionrecipient.findByCompanyName", query = "SELECT d FROM Distributionrecipient d WHERE d.companyName = :companyName")})
public class Distributionrecipient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DistributionRecipientID")
    private Integer distributionRecipientID;
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
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Surname")
    private String surname;
    @Size(max = 100)
    @Column(name = "E-MailAddress")
    private String eMailAddress;
    @Size(max = 45)
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Size(max = 45)
    @Column(name = "OfficeNumber")
    private String officeNumber;
    @Size(max = 50)
    @Column(name = "CompanyName")
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distributionRecipientID")
    private Collection<Artefactdistribution> artefactdistributionCollection;
    @JoinColumn(name = "ProjectID", referencedColumnName = "ProjectID")
    @ManyToOne(optional = false)
    private Project projectID;

    public Distributionrecipient() {
    }

    public Distributionrecipient(Integer distributionRecipientID) {
        this.distributionRecipientID = distributionRecipientID;
    }

    public Distributionrecipient(Integer distributionRecipientID, int versionNumber, boolean isCurrentVersion, boolean entityActive, String firstName, String surname) {
        this.distributionRecipientID = distributionRecipientID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Integer getDistributionRecipientID() {
        return distributionRecipientID;
    }

    public void setDistributionRecipientID(Integer distributionRecipientID) {
        this.distributionRecipientID = distributionRecipientID;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEMailAddress() {
        return eMailAddress;
    }

    public void setEMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        hash += (distributionRecipientID != null ? distributionRecipientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributionrecipient)) {
            return false;
        }
        Distributionrecipient other = (Distributionrecipient) object;
        if ((this.distributionRecipientID == null && other.distributionRecipientID != null) || (this.distributionRecipientID != null && !this.distributionRecipientID.equals(other.distributionRecipientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Distributionrecipient[ distributionRecipientID=" + distributionRecipientID + " ]";
    }
    
}
