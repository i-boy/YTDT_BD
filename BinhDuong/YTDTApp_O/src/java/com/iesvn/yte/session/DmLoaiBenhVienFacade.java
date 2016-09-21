/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiBenhVien;
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
public class DmLoaiBenhVienFacade implements DmLoaiBenhVienFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiBenhVien dmLoaiBenhVien) {
        em.persist(dmLoaiBenhVien);
    }

    public void edit(DmLoaiBenhVien dmLoaiBenhVien) {
        em.merge(dmLoaiBenhVien);
    }

    public void remove(DmLoaiBenhVien dmLoaiBenhVien) {
        em.remove(em.merge(dmLoaiBenhVien));
    }

    public DmLoaiBenhVien find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiBenhVien.class, id);
    }

    public List<DmLoaiBenhVien> findAll() {
        return em.createQuery("select object(o) from DmLoaiBenhVien as o").getResultList();
    }
    
}


