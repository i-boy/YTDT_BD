/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHocVi;
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
public class DmHocViFacade implements DmHocViFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmHocVi dmHocVi) {
        em.persist(dmHocVi);
    }

    public void edit(DmHocVi dmHocVi) {
        em.merge(dmHocVi);
    }

    public void remove(DmHocVi dmHocVi) {
        em.remove(em.merge(dmHocVi));
    }

    public DmHocVi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmHocVi.class, id);
    }

    public List<DmHocVi> findAll() {
        return em.createQuery("select object(o) from DmHocVi as o").getResultList();
    }
    
}


