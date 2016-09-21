/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiNguonChuongTrinh;
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
public class DmLoaiNguonChuongTrinhFacade implements DmLoaiNguonChuongTrinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh) {
        em.persist(dmLoaiNguonChuongTrinh);
    }

    public void edit(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh) {
        em.merge(dmLoaiNguonChuongTrinh);
    }

    public void remove(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh) {
        em.remove(em.merge(dmLoaiNguonChuongTrinh));
    }

    public DmLoaiNguonChuongTrinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiNguonChuongTrinh.class, id);
    }

    public List<DmLoaiNguonChuongTrinh> findAll() {
        return em.createQuery("select object(o) from DmLoaiNguonChuongTrinh as o").getResultList();
    }
    
}


