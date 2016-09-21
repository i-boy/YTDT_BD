/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuy;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmQuyFacade implements DmQuyFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmQuy dmQuy) {
        em.persist(dmQuy);
    }

    public void edit(DmQuy dmQuy) {
        em.merge(dmQuy);
    }

    public void remove(DmQuy dmQuy) {
        em.remove(em.merge(dmQuy));
    }

    public DmQuy find(Object id) {
        return em.find(com.iesvn.yte.entity.DmQuy.class, id);
    }

    public List<DmQuy> findAll() {
        return em.createQuery("select object(o) from DmQuy as o").getResultList();
    }

}


