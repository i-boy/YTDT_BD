/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTuyen;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTuyenFacade implements DmTuyenFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTuyen dmTuyen) {
        em.persist(dmTuyen);
    }

    public void edit(DmTuyen dmTuyen) {
        em.merge(dmTuyen);
    }

    public void remove(DmTuyen dmTuyen) {
        em.remove(em.merge(dmTuyen));
    }

    public DmTuyen find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTuyen.class, id);
    }

    public List<DmTuyen> findAll() {
        return em.createQuery("select object(o) from DmTuyen as o").getResultList();
    }

}


