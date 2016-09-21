/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmNhaSxSpddFacade implements DtDmNhaSxSpddFacadeLocal, DtDmNhaSxSpddFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmNhaSxSpdd dtDmNhaSxSpdd) {
        em.persist(dtDmNhaSxSpdd);
    }

    public void edit(DtDmNhaSxSpdd dtDmNhaSxSpdd) {
        em.merge(dtDmNhaSxSpdd);
    }

    public void remove(DtDmNhaSxSpdd dtDmNhaSxSpdd) {
        em.remove(em.merge(dtDmNhaSxSpdd));
    }

    public DtDmNhaSxSpdd find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd.class, id);
    }

    public List<DtDmNhaSxSpdd> findAll() {
        return em.createQuery("select object(o) from DtDmNhaSxSpdd as o").getResultList();
    }

}
