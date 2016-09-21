/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmGioAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmGioAnFacade implements DtDmGioAnFacadeLocal, DtDmGioAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmGioAn dtDmGioAn) {
        em.persist(dtDmGioAn);
    }

    public void edit(DtDmGioAn dtDmGioAn) {
        em.merge(dtDmGioAn);
    }

    public void remove(DtDmGioAn dtDmGioAn) {
        em.remove(em.merge(dtDmGioAn));
    }

    public DtDmGioAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmGioAn.class, id);
    }

    public List<DtDmGioAn> findAll() {
        return em.createQuery("select object(o) from DtDmGioAn as o").getResultList();
    }

}
