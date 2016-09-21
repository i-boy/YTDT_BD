/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDuAnDeTai;
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
public class DmDuAnDeTaiFacade implements DmDuAnDeTaiFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDuAnDeTai dmDuAnDeTai) {
        em.persist(dmDuAnDeTai);
    }

    public void edit(DmDuAnDeTai dmDuAnDeTai) {
        em.merge(dmDuAnDeTai);
    }

    public void remove(DmDuAnDeTai dmDuAnDeTai) {
        em.remove(em.merge(dmDuAnDeTai));
    }

    public DmDuAnDeTai find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDuAnDeTai.class, id);
    }

    public List<DmDuAnDeTai> findAll() {
        return em.createQuery("select object(o) from DmDuAnDeTai as o").getResultList();
    }
    
}


