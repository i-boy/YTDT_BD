/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmCum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DtDmCumFacade implements DtDmCumFacadeLocal, DtDmCumFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmCum dtDmCum) {
        em.persist(dtDmCum);
    }

    public void edit(DtDmCum dtDmCum) {
        em.merge(dtDmCum);
    }

    public void remove(DtDmCum dtDmCum) {
        em.remove(em.merge(dtDmCum));
    }

    public DtDmCum find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmCum.class, id);
    }

    public List<DtDmCum> findAll() {
        return em.createQuery("select object(o) from DtDmCum as o").getResultList();
    }

}
