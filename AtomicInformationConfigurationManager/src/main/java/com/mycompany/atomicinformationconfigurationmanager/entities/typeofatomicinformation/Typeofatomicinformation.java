/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.typeofatomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseEntity;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "typeofatomicinformation")
@AttributeOverride(name = "id", column = @Column(name = "TypeOfAtomicInformationID"))

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeofatomicinformation.findByEntityActiveAndTypeOfAtomicInformationIDAndIsCurrentVersion", query = "SELECT t FROM Typeofatomicinformation t WHERE t.id = :typeOfAtomicInformationID AND t.entityActive = :entityActive  AND t.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Typeofatomicinformation.findByEntityActiveAndTypeAndIsCurrentVersion", query = "SELECT t FROM Typeofatomicinformation t WHERE t.type = :type AND t.entityActive = :entityActive  AND t.isCurrentVersion = :isCurrentVersion"),
    @NamedQuery(name = "Typeofatomicinformation.findByEntityActiveAndIsCurrentVersion", query = "SELECT t FROM Typeofatomicinformation t WHERE t.entityActive = :entityActive AND t.isCurrentVersion = :isCurrentVersion"),
    
    @NamedQuery(name = "Typeofatomicinformation.findAll", query = "SELECT t FROM Typeofatomicinformation t"),
    @NamedQuery(name = "Typeofatomicinformation.findByEntityActive", query = "SELECT t FROM Typeofatomicinformation t WHERE t.entityActive = :entityActive"),
    @NamedQuery(name = "Typeofatomicinformation.findByTypeOfAtomicInformationID", query = "SELECT t FROM Typeofatomicinformation t WHERE t.id = :typeOfAtomicInformationID"),
    @NamedQuery(name = "Typeofatomicinformation.findByType", query = "SELECT t FROM Typeofatomicinformation t WHERE t.type = :type")})
public class Typeofatomicinformation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfAtomicInformationID")
    private Collection<Atomicinformation> atomicinformationCollection;

    public Typeofatomicinformation() {
    }

    public Typeofatomicinformation(Integer typeOfAtomicInformationID) {
        this.id = typeOfAtomicInformationID;
    }

    public Typeofatomicinformation(Integer typeOfAtomicInformationID, String type) {
        this.id = typeOfAtomicInformationID;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Atomicinformation> getAtomicinformationCollection() {
        return atomicinformationCollection;
    }

    public void setAtomicinformationCollection(Collection<Atomicinformation> atomicinformationCollection) {
        this.atomicinformationCollection = atomicinformationCollection;
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
        if (!(object instanceof Typeofatomicinformation)) {
            return false;
        }
        Typeofatomicinformation other = (Typeofatomicinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getType();
    }
}
