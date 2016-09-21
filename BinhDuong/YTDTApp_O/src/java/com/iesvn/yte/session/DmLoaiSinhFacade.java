/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiSinh;
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
public class DmLoaiSinhFacade implements DmLoaiSinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiSinh dmLoaiSinh) {
        em.persist(dmLoaiSinh);
    }

    public void edit(DmLoaiSinh dmLoaiSinh) {
        em.merge(dmLoaiSinh);
    }

    public void remove(DmLoaiSinh dmLoaiSinh) {
        em.remove(em.merge(dmLoaiSinh));
    }

    public DmLoaiSinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiSinh.class, id);
    }

    public List<DmLoaiSinh> findAll() {
        return em.createQuery("select object(o) from DmLoaiSinh as o").getResultList();
    }
    
}


