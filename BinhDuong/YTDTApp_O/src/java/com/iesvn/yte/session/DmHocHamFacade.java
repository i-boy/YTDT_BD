/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHocHam;
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
public class DmHocHamFacade implements DmHocHamFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmHocHam dmHocHam) {
        em.persist(dmHocHam);
    }

    public void edit(DmHocHam dmHocHam) {
        em.merge(dmHocHam);
    }

    public void remove(DmHocHam dmHocHam) {
        em.remove(em.merge(dmHocHam));
    }

    public DmHocHam find(Object id) {
        return em.find(com.iesvn.yte.entity.DmHocHam.class, id);
    }

    public List<DmHocHam> findAll() {
        return em.createQuery("select object(o) from DmHocHam as o").getResultList();
    }
    
}


