/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;

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
public class DmPhanLoaiThuocFacade implements DmPhanLoaiThuocFacadeLocal, DmPhanLoaiThuocFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        em.persist(dmPhanLoaiThuoc);
    }

    public void edit(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        em.merge(dmPhanLoaiThuoc);
    }

    public void remove(DmPhanLoaiThuoc dmPhanLoaiThuoc) {
        em.remove(em.merge(dmPhanLoaiThuoc));
    }

    public DmPhanLoaiThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmPhanLoaiThuoc.class, id);
    }

    public List<DmPhanLoaiThuoc> findAll() {
        return em.createQuery("select object(o) from DmPhanLoaiThuoc as o").getResultList();
    }
    
    public List<DmPhanLoaiThuoc> findByDtdmloaiMa(String maLoai) {
        System.out.println("-----findByDtdmloaiMa()-----");        
        Query q = em.createQuery("SELECT p FROM DmPhanLoaiThuoc p WHERE p.dmloaithuocMaso.dmloaithuocMa = :maLoai order by p.dmphanloaithuocTen");
        q.setParameter("maLoai", maLoai);
        try {
            List<DmPhanLoaiThuoc> listPl = q.getResultList();
            if (listPl != null) {
                return listPl;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public List<DmPhanLoaiThuoc> findByMaAndTenAndDmloaiMa(String ma, String ten, Integer dmLoaithuocMaso) {
        System.out.println("-----findByDtdmloaiMa()-----");
        String sSQL = "SELECT p FROM DmPhanLoaiThuoc p WHERE (:ma = '' or p.dmphanloaithuocMa like ('%' || :ma || '%')) and (:ten = '' or p.dmphanloaithuocTen like ('%' || :ten || '%')) and (:dmloaithuocMaso is null or p.dmloaithuocMaso.dmloaithuocMaso = :dmloaithuocMaso) and p.dmphanloaithuocDt = true order by p.dmphanloaithuocTen";
        Query q = em.createQuery(sSQL);

        q.setParameter("ma", ma);
        q.setParameter("ten", ten);
        q.setParameter("dmloaithuocMaso", dmLoaithuocMaso);
        try {
            List<DmPhanLoaiThuoc> listPl = q.getResultList();
            if (listPl != null) {
                return listPl;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
}