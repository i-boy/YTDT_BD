/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTinh;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTinhFacade implements DmTinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTinh dmTinh) {
        em.persist(dmTinh);
    }

    public void edit(DmTinh dmTinh) {
        em.merge(dmTinh);
    }

    public void remove(DmTinh dmTinh) {
        em.remove(em.merge(dmTinh));
    }

    public DmTinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTinh.class, id);
    }

    public List<DmTinh> findAll() {
        return em.createQuery("select object(o) from DmTinh as o").getResultList();
    }

}


