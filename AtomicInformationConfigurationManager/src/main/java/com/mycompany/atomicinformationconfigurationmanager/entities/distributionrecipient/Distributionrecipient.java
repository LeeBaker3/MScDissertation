/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient;

import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.Artefactdistribution;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
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
@AttributeOverride (name = "id", column = @Column(name = "DistributionRecipientID"))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndDistributionRecipientIDAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.id = :distributionRecipientID AND d.entityActive = :entityActive AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndVersionNumberAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.versionNumber = :versionNumber AND d.entityActive = :entityActive AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.entityActive = :entityActive AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndFirstNameAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.firstName = :firstName AND d.entityActive = :entityActive  AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndSurnameAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.surname = :surname AND d.entityActive = :entityActive  AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndEMailAddressAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.eMailAddress = :eMailAddress AND d.entityActive = :entityActive  AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndMobileNumberAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.mobileNumber = :mobileNumber AND d.entityActive = :entityActive  AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndOfficeNumberAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.officeNumber = :officeNumber AND d.entityActive = :entityActive  AND d.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndCompanyNameAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.companyName = :companyName AND d.entityActive = :entityActive AND d.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Distributionrecipient.findByEntityActiveAndProjectIDAndIsCurrentVersion", query = "SELECT d FROM Distributionrecipient d WHERE d.entityActive = :entityActive AND d.isCurrentVersion = :isCurrentVersion AND d.projectID = :projectID"),
    
    @NamedQuery(name = "Distributionrecipient.findAll", query = "SELECT d FROM Distributionrecipient d"),
    @NamedQuery(name = "Distributionrecipient.findByEntityActive", query = "SELECT d FROM Distributionrecipient d WHERE d.entityActive = :entityActive"),
    @NamedQuery(name = "Distributionrecipient.findByDistributionRecipientID", query = "SELECT d FROM Distributionrecipient d WHERE d.id = :distributionRecipientID"),
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
public class Distributionrecipient extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
    @Column(name = "EMailAddress")
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
        this.id = distributionRecipientID;
    }

    public Distributionrecipient(Integer distributionRecipientID, int versionNumber, boolean isCurrentVersion, boolean entityActive, String firstName, String surname) {
        this.id = distributionRecipientID;
        this.versionNumber = versionNumber;
        this.isCurrentVersion = isCurrentVersion;
        this.entityActive = entityActive;
        this.firstName = firstName;
        this.surname = surname;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributionrecipient)) {
            return false;
        }
        Distributionrecipient other = (Distributionrecipient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getSurname();
    }
}
