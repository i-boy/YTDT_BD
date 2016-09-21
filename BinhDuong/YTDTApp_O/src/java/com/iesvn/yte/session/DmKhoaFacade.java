/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKhoa;
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
public class DmKhoaFacade implements DmKhoaFacadeLocal, DmKhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DmKhoa dmKhoa) {
        em.persist(dmKhoa);
    }

    public void edit(DmKhoa dmKhoa) {
        em.merge(dmKhoa);
    }

    public void remove(DmKhoa dmKhoa) {
        em.remove(em.merge(dmKhoa));
    }

    public DmKhoa find(Object id) {
        return em.find(com.iesvn.yte.entity.DmKhoa.class, id);
    }

    public List<DmKhoa> findAll() {
        return em.createQuery("select object(o) from DmKhoa as o").getResultList();
    }
    
    public Integer findMaSoByMa(String dmkhoaMa) {

        Integer result = 0;
        Query q = em.createQuery("SELECT d FROM DmKhoa d WHERE d.dmkhoaMa = :dmkhoaMa");
        q.setParameter("dmkhoaMa", dmkhoaMa);
        List<DmKhoa> list = q.getResultList();
        if (list != null && list.size() > 0) {
            result = list.get(0).getDmkhoaMaso();
        }
        return result;
    }
    
    public List<DmLoaiKhoa> findAllLoaiKhoaHavingKhoa() {
        Query q = em.createQuery("Select o from DmLoaiKhoa as o where o.dmloaikhoaMaso in ( select d.dmloaikhoaMa.dmloaikhoaMaso from DmKhoa as d) ");
        return q.getResultList();
    }

    public List<DmKhoa> getKhoaHavingLoaiKhoaMa(String loaiKhoaMa) {
        System.out.println("getKhoaHavingLoaiKhoaMa");
        System.out.println("loaiKhoaMa:" + loaiKhoaMa);
        Query q = em.createQuery("Select o from DmKhoa as o where o.dtdmloaikhoaMa.dtdmloaikhoaMa like :loaiKhoaMa ");
        q.setParameter("loaiKhoaMa", loaiKhoaMa);
        return q.getResultList();
    }
    //Tho add
    public List<DmKhoa> getKhoChinh_KhoLe() {
        System.out.println("getKhoChinh_KhoLe");
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaMa = 'KCH' or o.dmkhoaMa = 'KBH' or o.dmkhoaMa = 'KTE' or o.dmkhoaMa = 'KNT'");
        
        return q.getResultList();
    }

    public List<DmKhoa> getKhoLe() {
        System.out.println("getKhoLe");
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaMa = 'KBH' or o.dmkhoaMa = 'KTE' or o.dmkhoaMa = 'KNT'");

        return q.getResultList();
    }
    //get kho le, kho thanh ly, don vi tuyen duoi
    public List<DmKhoa> getKhoLe_TL_TD() {
        System.out.println("getKhoLe_TL_TD");
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaMa = 'KBH' or o.dmkhoaMa = 'KTE' or o.dmkhoaMa = 'KNT' or o.dmkhoaMa = 'KTL' or o.dmkhoaMa = 'KTD'");

        return q.getResultList();
    }

    public List<DmKhoa> getKhoaLamSang() {
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmnhomkhoaMaso.dmnhomkhoaMaso = 2");

        return q.getResultList();
    }
    // phuc.lc : 11-01-2011 : Fix bug 2006
    public List<DmKhoa> getKhoaCanLamSang() {        
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaCls = 1");

        return q.getResultList();
    }

    public List<DmKhoa> getKhoaNT(){
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaNoitru = 1");
        return q.getResultList();
    }

    public List<DmKhoa> getKhoaNgT(){
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmkhoaKham = 1");
        return q.getResultList();
    }

    // oracle ok
    public Integer findMaSoByMasoThuoc(Integer dmthuocMaso) {
        Integer result = 0;
        String sSQL = "SELECT plt.dmphanloaithuoc_nhom2 FROM dm_thuoc t INNER JOIN dm_phan_loai_thuoc plt on t.dmphanloaithuoc_maso = plt.dmphanloaithuoc_maso where dmthuoc_maso = "+ dmthuocMaso;
        Query q = em.createNativeQuery(sSQL);
        result = Integer.parseInt((String)q.getSingleResult());
        return result;
    }

    public List<DmKhoa> getKhoaNhom12(){
        Query q = em.createQuery("Select o from DmKhoa as o where o.dmnhomkhoaMaso.dmnhomkhoaMaso = 1 or o.dmnhomkhoaMaso.dmnhomkhoaMaso = 2");
        return q.getResultList();
    }

    public List<DmKhoa> getKhoaNhom12NOTINKhoDuoc(){
        Query q = em.createQuery("Select o from DmKhoa as o where (o.dmnhomkhoaMaso.dmnhomkhoaMaso = 1 or o.dmnhomkhoaMaso.dmnhomkhoaMaso = 2) and o.dmkhoaDuoc <> 1");
        return q.getResultList();
    }
}

