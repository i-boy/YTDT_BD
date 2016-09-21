/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmLyDoCvFacade implements DtDmLyDoCvFacadeLocal, DtDmLyDoCvFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLyDoCv dtDmLyDoCv) {
        em.persist(dtDmLyDoCv);
    }

    public void edit(DtDmLyDoCv dtDmLyDoCv) {
        em.merge(dtDmLyDoCv);
    }

    public void remove(DtDmLyDoCv dtDmLyDoCv) {
        em.remove(em.merge(dtDmLyDoCv));
    }

    public DtDmLyDoCv find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLyDoCv.class, id);
    }

    public List<DtDmLyDoCv> findAll() {
        return em.createQuery("select object(o) from DtDmLyDoCv as o").getResultList();
    }

}


