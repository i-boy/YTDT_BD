/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmKho;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmKhoFacade implements DtDmKhoFacadeLocal, DtDmKhoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmKho dtDmKho) {
        em.persist(dtDmKho);
    }

    public void edit(DtDmKho dtDmKho) {
        em.merge(dtDmKho);
    }

    public void remove(DtDmKho dtDmKho) {
        em.remove(em.merge(dtDmKho));
    }

    public DtDmKho find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmKho.class, id);
    }

    public List<DtDmKho> findAll() {
        return em.createQuery("select object(o) from DtDmKho as o").getResultList();
    }

}


