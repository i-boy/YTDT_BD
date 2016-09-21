/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomHocVi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmNhomHocViFacade implements DmNhomHocViFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNhomHocVi dmNhomHocVi) {
        em.persist(dmNhomHocVi);
    }

    public void edit(DmNhomHocVi dmNhomHocVi) {
        em.merge(dmNhomHocVi);
    }

    public void remove(DmNhomHocVi dmNhomHocVi) {
        em.remove(em.merge(dmNhomHocVi));
    }

    public DmNhomHocVi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNhomHocVi.class, id);
    }

    public List<DmNhomHocVi> findAll() {
        return em.createQuery("select object(o) from DmNhomHocVi as o").getResultList();
    }

}


