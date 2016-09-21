/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmBacSiBanKham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DtDmBacSiBanKhamFacade implements DtDmBacSiBanKhamFacadeLocal, DtDmBacSiBanKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmBacSiBanKham dtDmBacSiBanKham) {
        em.persist(dtDmBacSiBanKham);
    }

    public void edit(DtDmBacSiBanKham dtDmBacSiBanKham) {
        em.merge(dtDmBacSiBanKham);
    }

    public void remove(DtDmBacSiBanKham dtDmBacSiBanKham) {
        em.remove(em.merge(dtDmBacSiBanKham));
    }

    public DtDmBacSiBanKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmBacSiBanKham.class, id);
    }

    public List<DtDmBacSiBanKham> findAll() {
        return em.createQuery("select object(o) from DtDmBacSiBanKham as o").getResultList();
    }

}


