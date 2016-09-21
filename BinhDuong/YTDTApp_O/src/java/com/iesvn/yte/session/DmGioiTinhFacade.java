/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmGioiTinh;
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
public class DmGioiTinhFacade implements DmGioiTinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmGioiTinh dmGioiTinh) {
        em.persist(dmGioiTinh);
    }

    public void edit(DmGioiTinh dmGioiTinh) {
        em.merge(dmGioiTinh);
    }

    public void remove(DmGioiTinh dmGioiTinh) {
        em.remove(em.merge(dmGioiTinh));
    }

    public DmGioiTinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmGioiTinh.class, id);
    }

    public List<DmGioiTinh> findAll() {
        return em.createQuery("select object(o) from DmGioiTinh as o").getResultList();
    }
    
}


