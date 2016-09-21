/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmThon;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmThonFacade implements DmThonFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmThon dmThon) {
        em.persist(dmThon);
    }

    public void edit(DmThon dmThon) {
        em.merge(dmThon);
    }

    public void remove(DmThon dmThon) {
        em.remove(em.merge(dmThon));
    }

    public DmThon find(Object id) {
        return em.find(com.iesvn.yte.entity.DmThon.class, id);
    }

    public List<DmThon> findAll() {
        return em.createQuery("select object(o) from DmThon as o").getResultList();
    }

}


