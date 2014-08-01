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
    
    public List<Artefact> findByProjectID (String projectID){
        TypedQuery<Artefact> query = em.createNamedQuery("Artefact.findByProjectID", Artefact.class);
        query.setParameter("projectID", projectID);
        List<Artefact> result = query.getResultList();
        return result;
    }
}
