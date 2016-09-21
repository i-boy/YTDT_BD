/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmPhuongThucGayTaiNanFacade implements DmPhuongThucGayTaiNanFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan) {
        em.persist(dmPhuongThucGayTaiNan);
    }

    public void edit(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan) {
        em.merge(dmPhuongThucGayTaiNan);
    }

    public void remove(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan) {
        em.remove(em.merge(dmPhuongThucGayTaiNan));
    }

    public DmPhuongThucGayTaiNan find(Object id) {
        return em.find(com.iesvn.yte.entity.DmPhuongThucGayTaiNan.class, id);
    }

    public List<DmPhuongThucGayTaiNan> findAll() {
        return em.createQuery("select object(o) from DmPhuongThucGayTaiNan as o").getResultList();
    }

}


