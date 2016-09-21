/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTrangThaiHoSo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTrangThaiHoSoFacade implements DmTrangThaiHoSoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTrangThaiHoSo dmTrangThaiHoSo) {
        em.persist(dmTrangThaiHoSo);
    }

    public void edit(DmTrangThaiHoSo dmTrangThaiHoSo) {
        em.merge(dmTrangThaiHoSo);
    }

    public void remove(DmTrangThaiHoSo dmTrangThaiHoSo) {
        em.remove(em.merge(dmTrangThaiHoSo));
    }

    public DmTrangThaiHoSo find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTrangThaiHoSo.class, id);
    }

    public List<DmTrangThaiHoSo> findAll() {
        return em.createQuery("select object(o) from DmTrangThaiHoSo as o").getResultList();
    }

}


