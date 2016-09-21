/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmThanhPhanGiaDinh;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmThanhPhanGiaDinhFacade implements DmThanhPhanGiaDinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmThanhPhanGiaDinh dmThanhPhanGiaDinh) {
        em.persist(dmThanhPhanGiaDinh);
    }

    public void edit(DmThanhPhanGiaDinh dmThanhPhanGiaDinh) {
        em.merge(dmThanhPhanGiaDinh);
    }

    public void remove(DmThanhPhanGiaDinh dmThanhPhanGiaDinh) {
        em.remove(em.merge(dmThanhPhanGiaDinh));
    }

    public DmThanhPhanGiaDinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmThanhPhanGiaDinh.class, id);
    }

    public List<DmThanhPhanGiaDinh> findAll() {
        return em.createQuery("select object(o) from DmThanhPhanGiaDinh as o").getResultList();
    }

}


