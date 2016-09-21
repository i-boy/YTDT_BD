/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhIcd;
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
public class DmBenhIcdFacade implements DmBenhIcdFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmBenhIcd dmBenhIcd) {
        em.persist(dmBenhIcd);
    }

    public void edit(DmBenhIcd dmBenhIcd) {
        em.merge(dmBenhIcd);
    }

    public void remove(DmBenhIcd dmBenhIcd) {
        em.remove(em.merge(dmBenhIcd));
    }

    public DmBenhIcd find(Object id) {
        return em.find(com.iesvn.yte.entity.DmBenhIcd.class, id);
    }

    public List<DmBenhIcd> findAll() {
        return em.createQuery("select object(o) from DmBenhIcd as o").getResultList();
    }
    
}


