/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.distributionrecipient;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseSaveRetrieveAbstract;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lee Baker
 */
@Stateless
public class DistributionrecipientSaveRetrieve extends BaseSaveRetrieveAbstract<Distributionrecipient> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistributionrecipientSaveRetrieve() {
        super(Distributionrecipient.class);
    }

}