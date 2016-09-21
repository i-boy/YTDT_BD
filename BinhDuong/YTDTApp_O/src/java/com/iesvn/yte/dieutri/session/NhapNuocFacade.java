/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.NhapNuoc;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class NhapNuocFacade implements NhapNuocFacadeLocal, NhapNuocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(NhapNuoc nhapNuoc) {
        em.persist(nhapNuoc);
    }

    public void edit(NhapNuoc nhapNuoc) {
        em.merge(nhapNuoc);
    }

    public void remove(NhapNuoc nhapNuoc) {
        em.remove(em.merge(nhapNuoc));
    }

    public NhapNuoc find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.NhapNuoc.class, id);
    }

    public List<NhapNuoc> findAll() {
        return em.createQuery("select object(o) from NhapNuoc as o").getResultList();
    }
    
    public List<NhapNuoc> findByNgayNhap(Date ngayNhap) {
        Query q;
        q = em.createQuery("SELECT n FROM NhapNuoc n WHERE  n.nhapnuocNgay =  :ngayNhap");                
        q.setParameter("ngayNhap", ngayNhap);
        if (q.getResultList().size() > 0) {
            return q.getResultList();
        } else {
            return null;
        }
    }
    
    public NhapNuoc findByBuongNgayNhap(Integer buongMaso, Date ngayNhap) {
        if(findAll().size() > 0) {
            Query q;
            q = em.createQuery("SELECT n FROM NhapNuoc n WHERE  n.dmtangMaso.dmtangMaso = :buongMaso and n.nhapnuocNgay =  :ngayNhap");
            q.setParameter("buongMaso", buongMaso);
            q.setParameter("ngayNhap", ngayNhap);
            if (q.getResultList().size() > 0) {
                return (NhapNuoc)q.getResultList().get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public NhapNuoc myCreate(NhapNuoc nhapnuoc) {
            if(nhapnuoc.getNhapnuocMaso() == null) {
                em.persist(nhapnuoc);
            } else {
                em.merge(nhapnuoc);
            }   
            return nhapnuoc;
    }
}
