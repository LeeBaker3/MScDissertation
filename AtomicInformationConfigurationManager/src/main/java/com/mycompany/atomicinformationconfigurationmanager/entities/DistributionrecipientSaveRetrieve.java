/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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