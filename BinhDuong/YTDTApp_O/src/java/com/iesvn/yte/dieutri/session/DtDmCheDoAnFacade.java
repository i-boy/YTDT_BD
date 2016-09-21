/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmCheDoAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
public class DtDmCheDoAnFacade implements DtDmCheDoAnFacadeLocal, DtDmCheDoAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmCheDoAn dtDmCheDoAn) {
        em.persist(dtDmCheDoAn);
    }

    public void edit(DtDmCheDoAn dtDmCheDoAn) {
        em.merge(dtDmCheDoAn);
    }

    public void remove(DtDmCheDoAn dtDmCheDoAn) {
        em.remove(em.merge(dtDmCheDoAn));
    }

    public DtDmCheDoAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmCheDoAn.class, id);
    }

    public List<DtDmCheDoAn> findAll() {
        return em.createQuery("select object(o) from DtDmCheDoAn as o").getResultList();
    }

}
