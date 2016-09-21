/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhomBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thanh
 */
@Stateless
public class DtDmNhomBhytFacade implements DtDmNhomBhytFacadeRemote, DtDmNhomBhytFacadeLocal {
    
    @PersistenceContext
    private EntityManager em;

    public DtDmNhomBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmNhomBhyt.class, id);
    }

    public List<DtDmNhomBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmNhomBhyt as o").getResultList();
    }

    public void create(DtDmNhomBhyt dtDmNhomBhyt) {
        em.persist(dtDmNhomBhyt);
    }

    public void edit(DtDmNhomBhyt dtDmNhomBhyt) {
        em.merge(dtDmNhomBhyt);
    }

    public void remove(DtDmNhomBhyt dtDmNhomBhyt) {
          em.remove(dtDmNhomBhyt);
    }
 
}
