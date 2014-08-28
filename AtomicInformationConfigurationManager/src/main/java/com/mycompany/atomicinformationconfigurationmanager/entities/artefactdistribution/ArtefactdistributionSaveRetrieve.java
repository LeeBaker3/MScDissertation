/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
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
public class ArtefactdistributionSaveRetrieve extends BaseSaveRetrieveAbstract<Artefactdistribution> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtefactdistributionSaveRetrieve() {
        super(Artefactdistribution.class);
    }
       
    public List<Artefactdistribution> findByEntityActiveAndArtefactIDAndIsCurrentVersion (Artefact artefact, boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactdistribution> results = query.getResultList();
        return results;
    }
    
    public List<Artefactdistribution> findRangeEntityActiveAndArtefactIDAndIsCurrentVersion (int[] range, Boolean entityActive, Artefact artefact, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Artefactdistribution> results = query.getResultList();
        return results;
    }
    
        public int countEntityActiveAndArtefactIDIAndsCurrentVersion(Artefact artefact, Boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefactdistribution> query = em.createNamedQuery("Artefactdistribution.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactdistribution.class);
        query.setParameter("artefactID", artefact);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactdistribution> results = query.getResultList();
        return results.size();
    }
    
    
}
