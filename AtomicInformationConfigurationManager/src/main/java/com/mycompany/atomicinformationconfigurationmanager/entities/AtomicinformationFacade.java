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
public class AtomicinformationFacade extends AbstractFacade<Atomicinformation> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtomicinformationFacade() {
        super(Atomicinformation.class);
    }
    
    public List<Atomicinformation> findByEntityActiveAndProjectID(Boolean entityActive, Project project){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectID", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    public List<Atomicinformation> findRangeEntityActiveAndProjectID(int[] range, Boolean entityActive, Project project){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectID", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    public int countEntityActiveAndProjectID(Boolean entityActive, Project project){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectID", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        List<Atomicinformation> results = query.getResultList();
        return results.size();
    }
}
