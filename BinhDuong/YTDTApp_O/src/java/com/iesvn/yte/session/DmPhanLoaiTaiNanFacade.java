/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmPhanLoaiTaiNanFacade implements DmPhanLoaiTaiNanFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmPhanLoaiTaiNan dmPhanLoaiTaiNan) {
        em.persist(dmPhanLoaiTaiNan);
    }

    public void edit(DmPhanLoaiTaiNan dmPhanLoaiTaiNan) {
        em.merge(dmPhanLoaiTaiNan);
    }

    public void remove(DmPhanLoaiTaiNan dmPhanLoaiTaiNan) {
        em.remove(em.merge(dmPhanLoaiTaiNan));
    }

    public DmPhanLoaiTaiNan find(Object id) {
        return em.find(com.iesvn.yte.entity.DmPhanLoaiTaiNan.class, id);
    }

    public List<DmPhanLoaiTaiNan> findAll() {
        return em.createQuery("select object(o) from DmPhanLoaiTaiNan as o").getResultList();
    }

}


