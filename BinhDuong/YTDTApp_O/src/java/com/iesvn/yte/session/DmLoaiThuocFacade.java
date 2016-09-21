/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiThuoc;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmLoaiThuocFacade implements DmLoaiThuocFacadeLocal, DmLoaiThuocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiThuoc dmLoaiThuoc) {
        em.persist(dmLoaiThuoc);
    }

    public void edit(DmLoaiThuoc dmLoaiThuoc) {
        em.merge(dmLoaiThuoc);
    }

    public void remove(DmLoaiThuoc dmLoaiThuoc) {
        em.remove(em.merge(dmLoaiThuoc));
    }

    public DmLoaiThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiThuoc.class, id);
    }

    public HashMap<String,DmLoaiThuoc> findAllDm() {
        List<DmLoaiThuoc> lst = em.createQuery("select object(o) from DmLoaiThuoc as o").getResultList();
        HashMap<String,DmLoaiThuoc> hm = new HashMap<String,DmLoaiThuoc>();
        for(DmLoaiThuoc o : lst){
            hm.put(o.getDmloaithuocMa(), o);
        }
        return hm;
    }

    public List<DmLoaiThuoc> findAll() {
        return em.createQuery("select object(o) from DmLoaiThuoc as o").getResultList();
    }    
}


