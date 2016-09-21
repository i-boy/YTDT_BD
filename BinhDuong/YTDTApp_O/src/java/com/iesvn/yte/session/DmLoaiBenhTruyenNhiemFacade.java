/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiBenhTruyenNhiem;
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
public class DmLoaiBenhTruyenNhiemFacade implements DmLoaiBenhTruyenNhiemFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem) {
        em.persist(dmLoaiBenhTruyenNhiem);
    }

    public void edit(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem) {
        em.merge(dmLoaiBenhTruyenNhiem);
    }

    public void remove(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem) {
        em.remove(em.merge(dmLoaiBenhTruyenNhiem));
    }

    public DmLoaiBenhTruyenNhiem find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiBenhTruyenNhiem.class, id);
    }

    public List<DmLoaiBenhTruyenNhiem> findAll() {
        return em.createQuery("select object(o) from DmLoaiBenhTruyenNhiem as o").getResultList();
    }
    
}


