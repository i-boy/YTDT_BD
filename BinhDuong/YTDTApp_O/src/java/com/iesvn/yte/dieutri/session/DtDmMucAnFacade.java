/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmMucAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
public class DtDmMucAnFacade implements DtDmMucAnFacadeLocal, DtDmMucAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmMucAn dtDmMucAn) {
        em.persist(dtDmMucAn);
    }

    public void edit(DtDmMucAn dtDmMucAn) {
        em.merge(dtDmMucAn);
    }

    public void remove(DtDmMucAn dtDmMucAn) {
        em.remove(em.merge(dtDmMucAn));
    }

    public DtDmMucAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmMucAn.class, id);
    }

    public List<DtDmMucAn> findAll() {
        return em.createQuery("select object(o) from DtDmMucAn as o").getResultList();
    }

}
