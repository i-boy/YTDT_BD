/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmPlBhytFacade implements DtDmPlBhytFacadeLocal, DtDmPlBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPlBhyt dtDmPlBhyt) {
        em.persist(dtDmPlBhyt);
    }

    public void edit(DtDmPlBhyt dtDmPlBhyt) {
        em.merge(dtDmPlBhyt);
    }

    public void remove(DtDmPlBhyt dtDmPlBhyt) {
        em.remove(em.merge(dtDmPlBhyt));
    }

    public DtDmPlBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPlBhyt.class, id);
    }

    public List<DtDmPlBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmPlBhyt as o").getResultList();
    }

}


