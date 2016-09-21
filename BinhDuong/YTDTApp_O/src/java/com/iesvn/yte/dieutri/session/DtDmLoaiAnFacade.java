/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
public class DtDmLoaiAnFacade implements DtDmLoaiAnFacadeLocal, DtDmLoaiAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiAn dtDmLoaiAn) {
        em.persist(dtDmLoaiAn);
    }

    public void edit(DtDmLoaiAn dtDmLoaiAn) {
        em.merge(dtDmLoaiAn);
    }

    public void remove(DtDmLoaiAn dtDmLoaiAn) {
        em.remove(em.merge(dtDmLoaiAn));
    }

    public DtDmLoaiAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiAn.class, id);
    }

    public List<DtDmLoaiAn> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiAn as o").getResultList();
    }

}
