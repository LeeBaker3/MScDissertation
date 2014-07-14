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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeofatomicinformation.findAll", query = "SELECT t FROM Typeofatomicinformation t"),
    @NamedQuery(name = "Typeofatomicinformation.findByTypeOfAtomicInformationID", query = "SELECT t FROM Typeofatomicinformation t WHERE t.typeOfAtomicInformationID = :typeOfAtomicInformationID"),
    @NamedQuery(name = "Typeofatomicinformation.findByType", query = "SELECT t FROM Typeofatomicinformation t WHERE t.type = :type")})
public class Typeofatomicinformation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TypeOfAtomicInformationID")
    private Integer typeOfAtomicInformationID;
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
        this.typeOfAtomicInformationID = typeOfAtomicInformationID;
    }

    public Typeofatomicinformation(Integer typeOfAtomicInformationID, String type) {
        this.typeOfAtomicInformationID = typeOfAtomicInformationID;
        this.type = type;
    }

    public Integer getTypeOfAtomicInformationID() {
        return typeOfAtomicInformationID;
    }

    public void setTypeOfAtomicInformationID(Integer typeOfAtomicInformationID) {
        this.typeOfAtomicInformationID = typeOfAtomicInformationID;
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
        hash += (typeOfAtomicInformationID != null ? typeOfAtomicInformationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeofatomicinformation)) {
            return false;
        }
        Typeofatomicinformation other = (Typeofatomicinformation) object;
        if ((this.typeOfAtomicInformationID == null && other.typeOfAtomicInformationID != null) || (this.typeOfAtomicInformationID != null && !this.typeOfAtomicInformationID.equals(other.typeOfAtomicInformationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Typeofatomicinformation[ typeOfAtomicInformationID=" + typeOfAtomicInformationID + " ]";
    }
    
}
