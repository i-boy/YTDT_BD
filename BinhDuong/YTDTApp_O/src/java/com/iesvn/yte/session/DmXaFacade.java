/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmXa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmXaFacade implements DmXaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmXa dmXa) {
        em.persist(dmXa);
    }

    public void edit(DmXa dmXa) {
        em.merge(dmXa);
    }

    public void remove(DmXa dmXa) {
        em.remove(em.merge(dmXa));
    }

    public DmXa find(Object id) {
        return em.find(com.iesvn.yte.entity.DmXa.class, id);
    }

    public List<DmXa> findAll() {
        return em.createQuery("select object(o) from DmXa as o").getResultList();
    }

}


