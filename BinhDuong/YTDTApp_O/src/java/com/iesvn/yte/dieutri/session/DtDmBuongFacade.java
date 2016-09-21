/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmBuong;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmBuongFacade implements DtDmBuongFacadeLocal, DtDmBuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmBuong dtDmBuong) {
        em.persist(dtDmBuong);
    }

    public void edit(DtDmBuong dtDmBuong) {
        em.merge(dtDmBuong);
    }

    public void remove(DtDmBuong dtDmBuong) {
        em.remove(em.merge(dtDmBuong));
    }

    public DtDmBuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmBuong.class, id);
    }

    public List<DtDmBuong> findAll() {
        return em.createQuery("select object(o) from DtDmBuong as o").getResultList();
    }

}
