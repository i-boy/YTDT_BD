/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDoiTuong;
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
public class DmDoiTuongFacade implements DmDoiTuongFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDoiTuong dmDoiTuong) {
        em.persist(dmDoiTuong);
    }

    public void edit(DmDoiTuong dmDoiTuong) {
        em.merge(dmDoiTuong);
    }

    public void remove(DmDoiTuong dmDoiTuong) {
        em.remove(em.merge(dmDoiTuong));
    }

    public DmDoiTuong find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDoiTuong.class, id);
    }

    public List<DmDoiTuong> findAll() {
        return em.createQuery("select object(o) from DmDoiTuong as o").getResultList();
    }

}


