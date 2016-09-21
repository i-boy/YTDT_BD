/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhTruyenNhiem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DmBenhTruyenNhiemFacade implements DmBenhTruyenNhiemFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmBenhTruyenNhiem dmBenhTruyenNhiem) {
        em.persist(dmBenhTruyenNhiem);
    }

    public void edit(DmBenhTruyenNhiem dmBenhTruyenNhiem) {
        em.merge(dmBenhTruyenNhiem);
    }

    public void remove(DmBenhTruyenNhiem dmBenhTruyenNhiem) {
        em.remove(em.merge(dmBenhTruyenNhiem));
    }

    public DmBenhTruyenNhiem find(Object id) {
        return em.find(com.iesvn.yte.entity.DmBenhTruyenNhiem.class, id);
    }

    public List<DmBenhTruyenNhiem> findAll() {
        return em.createQuery("select object(o) from DmBenhTruyenNhiem as o").getResultList();
    }
    
}


