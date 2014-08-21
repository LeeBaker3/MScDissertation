/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.atomicinformationconfigurationmanager.entities.base;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.TypedQuery;
import sun.awt.SunHints;

/**
 *
 * @author Lee Baker
 */
public abstract class BaseSaveRetrieveAbstract<T> {
    private Class<T> entityClass;

    public BaseSaveRetrieveAbstract(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    /*
    *   03/08/14  @Lee Baker
    *   Method added to to set EntityActive property of Entity to 'false'
    */
    public void entityInactive (T entity) {
       getEntityManager().merge(entity); 
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    /*
    *   04/08/14 Following 3 methoods return all records of an entity type
    * filtered based on whether the EntityActive attribute is set to true or false
    */
    
 
    //   03/08/14 @Lee Baker
    //   Added Method to find only Database Records that have the attribute Entity Active set to TRUE       
    public List<T> findAllEntityActiveIsCurrentVersion(Boolean entityActive, Boolean isCurrentVersion) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<T> results = query.getResultList();
        return results;
    }
    
    //   03/08/14 @Lee Baker
    //  Added Method to find only Database Records that have the attribute Entity Active set to TRUE 
    //  when disbaled after an edit 
    public List<T> findRangeEntityActiveIsCurrentVersion(int[] range, Boolean entityActive, Boolean isCurrentVersion) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        List<T> results = query.getResultList();
        return results;
    }
    
    public int countEntityActive(Boolean entityActive, Boolean isCurrentVersion){
        TypedQuery<T> query = getEntityManager().createNamedQuery(entityClass.getSimpleName() + ".findByEntityActiveAndIsCurrentVersion", entityClass);
        query.setParameter("entityActive", entityActive);
        query.setParameter("isCurrentVersion", isCurrentVersion);
        List<T> results = query.getResultList();
        return results.size();
    }
    

    /*  
    *   04/08/14 @Lee Baker
    *   Follow 3 methods are IDE generated an return all records of an entity type
    */
    
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
