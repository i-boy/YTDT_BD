/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDonVi;
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
public class DmDonViFacade implements DmDonViFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDonVi dmDonVi) {
        em.persist(dmDonVi);
    }

    public void edit(DmDonVi dmDonVi) {
        em.merge(dmDonVi);
    }

    public void remove(DmDonVi dmDonVi) {
        em.remove(em.merge(dmDonVi));
    }

    public DmDonVi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDonVi.class, id);
    }

    public List<DmDonVi> findAll() {
        return em.createQuery("select object(o) from DmDonVi as o").getResultList();
    }
    
}


