/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKhoaByt;
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
public class DmKhoaBytFacade implements DmKhoaBytFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmKhoaByt dmKhoaByt) {
        em.persist(dmKhoaByt);
    }

    public void edit(DmKhoaByt dmKhoaByt) {
        em.merge(dmKhoaByt);
    }

    public void remove(DmKhoaByt dmKhoaByt) {
        em.remove(em.merge(dmKhoaByt));
    }

    public DmKhoaByt find(Object id) {
        return em.find(com.iesvn.yte.entity.DmKhoaByt.class, id);
    }

    public List<DmKhoaByt> findAll() {
        return em.createQuery("select object(o) from DmKhoaByt as o").getResultList();
    }
    
}


