/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities;

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
public class ArtefactatomicinformationSaveRetrieve extends BaseSaveRetrieveAbstract<Artefactatomicinformation> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtefactatomicinformationSaveRetrieve() {
        super(Artefactatomicinformation.class);
    }
    
    public List<Artefactatomicinformation> findByEntityActiveAndArtefactIDAndIsCurrentVersion(Boolean entityActive, Artefact artefact, boolean isCurrentVersion){
        TypedQuery<Artefactatomicinformation> query = em.createNamedQuery("Artefactatomicinformation.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactatomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("artefactID", artefact);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactatomicinformation> results = query.getResultList();
        return results;
    }
    
    public List<Artefactatomicinformation> findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(int[] range, Boolean entityActive, Artefact artefact, boolean isCurrentVersion){
        TypedQuery<Artefactatomicinformation> query = em.createNamedQuery("Artefactatomicinformation.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactatomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("artefactID", artefact);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Artefactatomicinformation> results = query.getResultList();
        return results;
    }
    
    public int countEntityActiveAndArtefactIDAndIsCurrentVersion(Boolean entityActive, Artefact artefact, boolean isCurrentVersion){
        TypedQuery<Artefactatomicinformation> query = em.createNamedQuery("Artefactatomicinformation.findByEntityActiveAndArtefactIDAndIsCurrentVersion", Artefactatomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("artefactID", artefact);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefactatomicinformation> results = query.getResultList();
        return results.size();
    }   
}
