/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDanToc;
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
public class DmDanTocFacade implements DmDanTocFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDanToc dmDanToc) {
        em.persist(dmDanToc);
    }

    public void edit(DmDanToc dmDanToc) {
        em.merge(dmDanToc);
    }

    public void remove(DmDanToc dmDanToc) {
        em.remove(em.merge(dmDanToc));
    }

    public DmDanToc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDanToc.class, id);
    }

    public List<DmDanToc> findAll() {
        return em.createQuery("select object(o) from DmDanToc as o").getResultList();
    }
    
}


