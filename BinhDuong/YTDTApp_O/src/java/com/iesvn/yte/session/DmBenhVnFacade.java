/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhVn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DmBenhVnFacade implements DmBenhVnFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmBenhVn dmBenhVn) {
        em.persist(dmBenhVn);
    }

    public void edit(DmBenhVn dmBenhVn) {
        em.merge(dmBenhVn);
    }

    public void remove(DmBenhVn dmBenhVn) {
        em.remove(em.merge(dmBenhVn));
    }

    public DmBenhVn find(Object id) {
        return em.find(com.iesvn.yte.entity.DmBenhVn.class, id);
    }

    public List<DmBenhVn> findAll() {
        return em.createQuery("select object(o) from DmBenhVn as o").getResultList();
    }
    
}


