/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTaiNan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTaiNanFacade implements DmTaiNanFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTaiNan dmTaiNan) {
        em.persist(dmTaiNan);
    }

    public void edit(DmTaiNan dmTaiNan) {
        em.merge(dmTaiNan);
    }

    public void remove(DmTaiNan dmTaiNan) {
        em.remove(em.merge(dmTaiNan));
    }

    public DmTaiNan find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTaiNan.class, id);
    }

    public List<DmTaiNan> findAll() {
        return em.createQuery("select object(o) from DmTaiNan as o").getResultList();
    }

}


