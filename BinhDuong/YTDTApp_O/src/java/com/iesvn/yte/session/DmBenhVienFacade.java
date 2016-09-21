/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhVien;
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
public class DmBenhVienFacade implements DmBenhVienFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmBenhVien dmBenhVien) {
        em.persist(dmBenhVien);
    }

    public void edit(DmBenhVien dmBenhVien) {
        em.merge(dmBenhVien);
    }

    public void remove(DmBenhVien dmBenhVien) {
        em.remove(em.merge(dmBenhVien));
    }

    public DmBenhVien find(Object id) {
        return em.find(com.iesvn.yte.entity.DmBenhVien.class, id);
    }
    
    

    public List<DmBenhVien> findAll() {
        return em.createQuery("select object(o) from DmBenhVien as o").getResultList();
    }
    
}


