/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DuTruThucPham;
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
public class DuTruThucPhamFacade implements DuTruThucPhamFacadeLocal, DuTruThucPhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DuTruThucPham duTruThucPham) {
        em.persist(duTruThucPham);
    }

    public void edit(DuTruThucPham duTruThucPham) {
        em.merge(duTruThucPham);
    }

    public void remove(DuTruThucPham duTruThucPham) {
        em.remove(em.merge(duTruThucPham));
    }

    public DuTruThucPham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DuTruThucPham.class, id);
    }

    public List<DuTruThucPham> findAll() {
        return em.createQuery("select object(o) from DuTruThucPham as o").getResultList();
    }
    public DuTruThucPham findByLoaiTPNgayDutru(String maLoaiTP, Date ngaynhap) {
        Query q;
        q = em.createQuery("SELECT c FROM DuTruThucPham c WHERE  c.dtdmltpMaso.dtdmltpMa = :maLoaiTP AND c.dttpNgaydutru =  :ngaynhap");
        q.setParameter("maLoaiTP", maLoaiTP);
        q.setParameter("ngaynhap", ngaynhap);
        if (q.getResultList().size() > 0) {
            return (DuTruThucPham)q.getResultList().get(0);
        } else {
            return null;
        }
    }    
    
     public List<DuTruThucPham> findByDate(Date date) {
        return em.createQuery("select object(o) from DuTruThucPham as o where o.dttpNgaydutru = :date").setParameter("date", date).getResultList();
    }
}
