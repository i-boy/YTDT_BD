/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmTinhBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmTinhBhytFacade implements DtDmTinhBhytFacadeLocal, DtDmTinhBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmTinhBhyt dtDmTinhBhyt) {
        em.persist(dtDmTinhBhyt);
    }

    public void edit(DtDmTinhBhyt dtDmTinhBhyt) {
        em.merge(dtDmTinhBhyt);
    }

    public void remove(DtDmTinhBhyt dtDmTinhBhyt) {
        em.remove(em.merge(dtDmTinhBhyt));
    }

    public DtDmTinhBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmTinhBhyt.class, id);
    }

    public List<DtDmTinhBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmTinhBhyt as o").getResultList();
    }

}


