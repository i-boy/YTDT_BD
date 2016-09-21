/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
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
public class NhapKhoDinhDuongFacade implements NhapKhoDinhDuongFacadeLocal, NhapKhoDinhDuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(NhapKhoDinhDuong nhapKhoDinhDuong) {
        em.persist(nhapKhoDinhDuong);
    }

    public void edit(NhapKhoDinhDuong nhapKhoDinhDuong) {
        em.merge(nhapKhoDinhDuong);
    }

    public void remove(NhapKhoDinhDuong nhapKhoDinhDuong) {
        em.remove(em.merge(nhapKhoDinhDuong));
    }

    public NhapKhoDinhDuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong.class, id);
    }

    public List<NhapKhoDinhDuong> findAll() {
        return em.createQuery("select object(o) from NhapKhoDinhDuong as o").getResultList();
    }
    
    public List<NhapKhoDinhDuong> findByDate(Date date){
        return em.createQuery("select object(o) from NhapKhoDinhDuong as o where o.nkddNgaynhap = :ngayNhap").setParameter("ngayNhap", date).getResultList();
    }
    
     public NhapKhoDinhDuong myCreate(NhapKhoDinhDuong nhapkho) {
            if(nhapkho.getNkddMaso() == null) {
                em.persist(nhapkho);
            } else {
                em.merge(nhapkho);
            }   
            return nhapkho;
    }

}
