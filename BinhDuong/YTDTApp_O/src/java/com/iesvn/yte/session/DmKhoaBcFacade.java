/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKhoaBc;
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
public class DmKhoaBcFacade implements DmKhoaBcFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmKhoaBc dmKhoaBc) {
        em.persist(dmKhoaBc);
    }

    public void edit(DmKhoaBc dmKhoaBc) {
        em.merge(dmKhoaBc);
    }

    public void remove(DmKhoaBc dmKhoaBc) {
        em.remove(em.merge(dmKhoaBc));
    }

    public DmKhoaBc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmKhoaBc.class, id);
    }

    public List<DmKhoaBc> findAll() {
        return em.createQuery("select object(o) from DmKhoaBc as o").getResultList();
    }
    
}


