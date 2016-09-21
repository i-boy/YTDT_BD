/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmKhoiBhytFacade implements DtDmKhoiBhytFacadeLocal, DtDmKhoiBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmKhoiBhyt dtDmKhoiBhyt) {
        em.persist(dtDmKhoiBhyt);
    }

    public void edit(DtDmKhoiBhyt dtDmKhoiBhyt) {
        em.merge(dtDmKhoiBhyt);
    }

    public void remove(DtDmKhoiBhyt dtDmKhoiBhyt) {
        em.remove(em.merge(dtDmKhoiBhyt));
    }

    public DtDmKhoiBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt.class, id);
    }

    public List<DtDmKhoiBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmKhoiBhyt as o").getResultList();
    }

}


