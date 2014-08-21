/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.Artefact;

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
public class ArtefactSaveRetrieve extends BaseSaveRetrieveAbstract<Artefact> {
    @PersistenceContext(unitName = "AtomicInformationConfigManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtefactSaveRetrieve() {
        super(Artefact.class);
    }
    
    public List<Artefact> findByEntityActiveAndProjectIDAndIsCurrentVersion (Project project, boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectIDAndIsCurrentVersion", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefact> results = query.getResultList();
        return results;
    }
    
    public List<Artefact> findRangeEntityActiveAndProjectIDAndIsCurrentVersion (int[] range, Boolean entityActive, Project project, boolean isCurrentVersion){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectIDAndIsCurrentVersion", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<Artefact> results = query.getResultList();
        return results;
    }
    
        public int countEntityActiveAndProjectIDAndIsCurrentVersion(Project project, Boolean entityActive, boolean isCurrentVersion){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByEntityActiveAndProjectIDAndIsCurrentVersion", Artefact.class);
        query.setParameter("projectID", project);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<Artefact> results = query.getResultList();
        return results.size();
    }
}
