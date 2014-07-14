/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lee Baker
 */
@Stateless
public class AtomicinformationFacade extends AbstractFacade<Atomicinformation> {
    @PersistenceContext(unitName = "MScTestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtomicinformationFacade() {
        super(Atomicinformation.class);
    }
    
}
