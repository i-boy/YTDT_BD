/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHuyen;
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
public class DmHuyenFacade implements DmHuyenFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmHuyen dmHuyen) {
        em.persist(dmHuyen);
    }

    public void edit(DmHuyen dmHuyen) {
        em.merge(dmHuyen);
    }

    public void remove(DmHuyen dmHuyen) {
        em.remove(em.merge(dmHuyen));
    }

    public DmHuyen find(Object id) {
        return em.find(com.iesvn.yte.entity.DmHuyen.class, id);
    }

    public List<DmHuyen> findAll() {
        return em.createQuery("select object(o) from DmHuyen as o").getResultList();
    }
    
}


