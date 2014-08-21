/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseSaveRetrieveAbstract;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.Project;
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
public class AtomicinformationSaveRetrieve extends BaseSaveRetrieveAbstract<Atomicinformation> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtomicinformationSaveRetrieve() {
        super(Atomicinformation.class);
    }
    
    public List<Atomicinformation> findByEntityActiveAndProjectIDAndIsCurrentVersion(Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    public List<Atomicinformation> findRangeEntityActiveAndProjectIDAndISCurrentVersion(int[] range, Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Atomicinformation> results = query.getResultList();
        return results;
    }
    
    public int countEntityActiveAndProjectIDAndIsCurrentVersion(Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Atomicinformation> query = em.createNamedQuery("Atomicinformation.findByEntityActiveAndProjectIDAndIsCurrentVersion", Atomicinformation.class);
        query.setParameter("entityActive", entityActive);
        query.setParameter("projectID", project);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Atomicinformation> results = query.getResultList();
        return results.size();
    }
}
