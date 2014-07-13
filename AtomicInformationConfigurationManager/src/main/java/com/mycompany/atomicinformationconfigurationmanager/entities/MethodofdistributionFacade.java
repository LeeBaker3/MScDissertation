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
public class MethodofdistributionFacade extends AbstractFacade<Methodofdistribution> {
    @PersistenceContext(unitName = "com.mycompany_AtomicInformationConfigurationManager_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MethodofdistributionFacade() {
        super(Methodofdistribution.class);
    }
    
}
