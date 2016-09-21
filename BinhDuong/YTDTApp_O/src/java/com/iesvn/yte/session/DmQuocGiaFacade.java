/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuocGia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmQuocGiaFacade implements DmQuocGiaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmQuocGia dmQuocGia) {
        em.persist(dmQuocGia);
    }

    public void edit(DmQuocGia dmQuocGia) {
        em.merge(dmQuocGia);
    }

    public void remove(DmQuocGia dmQuocGia) {
        em.remove(em.merge(dmQuocGia));
    }

    public DmQuocGia find(Object id) {
        return em.find(com.iesvn.yte.entity.DmQuocGia.class, id);
    }

    public List<DmQuocGia> findAll() {
        return em.createQuery("select object(o) from DmQuocGia as o").getResultList();
    }

}


