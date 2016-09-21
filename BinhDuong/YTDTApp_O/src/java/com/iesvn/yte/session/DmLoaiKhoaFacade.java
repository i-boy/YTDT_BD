/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiKhoa;
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
public class DmLoaiKhoaFacade implements DmLoaiKhoaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiKhoa dmLoaiKhoa) {
        em.persist(dmLoaiKhoa);
    }

    public void edit(DmLoaiKhoa dmLoaiKhoa) {
        em.merge(dmLoaiKhoa);
    }

    public void remove(DmLoaiKhoa dmLoaiKhoa) {
        em.remove(em.merge(dmLoaiKhoa));
    }

    public DmLoaiKhoa find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiKhoa.class, id);
    }

    public List<DmLoaiKhoa> findAll() {
        return em.createQuery("select object(o) from DmLoaiKhoa as o").getResultList();
    }
    
}


