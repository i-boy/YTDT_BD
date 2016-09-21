/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class XuatKhoDinhDuongFacade implements XuatKhoDinhDuongFacadeLocal, XuatKhoDinhDuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(XuatKhoDinhDuong xuatKhoDinhDuong) {
        em.persist(xuatKhoDinhDuong);
    }

    public void edit(XuatKhoDinhDuong xuatKhoDinhDuong) {
        em.merge(xuatKhoDinhDuong);
    }

    public void remove(XuatKhoDinhDuong xuatKhoDinhDuong) {
        em.remove(em.merge(xuatKhoDinhDuong));
    }

    public XuatKhoDinhDuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong.class, id);
    }

    public List<XuatKhoDinhDuong> findAll() {
        return em.createQuery("select object(o) from XuatKhoDinhDuong as o").getResultList();
    }
    
     public List<XuatKhoDinhDuong> findByDate(Date date) {
        return em.createQuery("select object(o) from XuatKhoDinhDuong as o where o.xkddNgayxuat = :date").setParameter("date", date).getResultList();
    }

     public XuatKhoDinhDuong myCreate(XuatKhoDinhDuong xuatkho) {
            if(xuatkho.getXkddMaso() == null) {
                em.persist(xuatkho);
            } else {
                em.merge(xuatkho);
            }   
            return xuatkho;
    }

    
}
