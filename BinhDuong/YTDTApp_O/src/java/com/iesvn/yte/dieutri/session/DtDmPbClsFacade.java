/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPbCls;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmPbClsFacade implements DtDmPbClsFacadeLocal, DtDmPbClsFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPbCls dtDmPbCls) {
        em.persist(dtDmPbCls);
    }

    public void edit(DtDmPbCls dtDmPbCls) {
        em.merge(dtDmPbCls);
    }

    public void remove(DtDmPbCls dtDmPbCls) {
        em.remove(em.merge(dtDmPbCls));
    }

    public DtDmPbCls find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPbCls.class, id);
    }

    public List<DtDmPbCls> findAll() {
        return em.createQuery("select object(o) from DtDmPbCls as o").getResultList();
    }

}


