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
public class ArtefactFacade extends AbstractFacade<Artefact> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtefactFacade() {
        super(Artefact.class);
    }
    
    public List<Artefact> findByEntityActiveAndProjectID (Project project, boolean entityActive){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectID", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        List<Artefact> results = query.getResultList();
        return results;
    }
    
    public List<Artefact> findRangeEntityActiveAndProjectID (int[] range, Boolean entityActive, Project project){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectID", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Artefact> results = query.getResultList();
        return results;
    }
    
        public int countEntityActiveAndProjectID(Project project, Boolean entityActive){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectID", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        List<Artefact> results = query.getResultList();
        return results.size();
    }
}
