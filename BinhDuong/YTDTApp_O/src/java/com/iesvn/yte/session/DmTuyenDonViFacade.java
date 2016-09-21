/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTuyenDonVi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTuyenDonViFacade implements DmTuyenDonViFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTuyenDonVi dmTuyenDonVi) {
        em.persist(dmTuyenDonVi);
    }

    public void edit(DmTuyenDonVi dmTuyenDonVi) {
        em.merge(dmTuyenDonVi);
    }

    public void remove(DmTuyenDonVi dmTuyenDonVi) {
        em.remove(em.merge(dmTuyenDonVi));
    }

    public DmTuyenDonVi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTuyenDonVi.class, id);
    }

    public List<DmTuyenDonVi> findAll() {
        return em.createQuery("select object(o) from DmTuyenDonVi as o").getResultList();
    }

}


