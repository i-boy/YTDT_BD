/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNguonChuongTrinh;
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
public class DmNguonChuongTrinhFacade implements DmNguonChuongTrinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNguonChuongTrinh dmNguonChuongTrinh) {
        em.persist(dmNguonChuongTrinh);
    }

    public void edit(DmNguonChuongTrinh dmNguonChuongTrinh) {
        em.merge(dmNguonChuongTrinh);
    }

    public void remove(DmNguonChuongTrinh dmNguonChuongTrinh) {
        em.remove(em.merge(dmNguonChuongTrinh));
    }

    public DmNguonChuongTrinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNguonChuongTrinh.class, id);
    }

    public List<DmNguonChuongTrinh> findAll() {
        return em.createQuery("select object(o) from DmNguonChuongTrinh as o").getResultList();
    }
    
}


