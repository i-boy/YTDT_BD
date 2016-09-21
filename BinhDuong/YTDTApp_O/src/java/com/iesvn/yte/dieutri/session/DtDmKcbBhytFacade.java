/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmKcbBhytFacade implements DtDmKcbBhytFacadeLocal, DtDmKcbBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmKcbBhyt dtDmKcbBhyt) {
        em.persist(dtDmKcbBhyt);
    }

    public void edit(DtDmKcbBhyt dtDmKcbBhyt) {
        em.merge(dtDmKcbBhyt);
    }

    public void remove(DtDmKcbBhyt dtDmKcbBhyt) {
        em.remove(em.merge(dtDmKcbBhyt));
    }

    public DtDmKcbBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmKcbBhyt.class, id);
    }

    public List<DtDmKcbBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmKcbBhyt as o").getResultList();
    }

}


