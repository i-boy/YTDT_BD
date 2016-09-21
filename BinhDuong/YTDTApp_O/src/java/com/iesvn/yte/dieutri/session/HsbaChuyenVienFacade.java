/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaChuyenVienFacade implements HsbaChuyenVienFacadeLocal, HsbaChuyenVienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChuyenVien hsbaChuyenVien) {
        em.persist(hsbaChuyenVien);
    }

    public void edit(HsbaChuyenVien hsbaChuyenVien) {
        em.merge(hsbaChuyenVien);
    }

    public void remove(HsbaChuyenVien hsbaChuyenVien) {
        em.remove(em.merge(hsbaChuyenVien));
    }

    public HsbaChuyenVien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaChuyenVien.class, id);
    }

    public List<HsbaChuyenVien> findAll() {
        return em.createQuery("select object(o) from HsbaChuyenVien as o").getResultList();
    }
    public HsbaChuyenVien findBySoVaoVien(String soVaoVien) {
        if (soVaoVien.length() < 14) {
            soVaoVien = Utils.formatMa(em, soVaoVien);
        // System.out.println(soVaoVien);
        }

 List<HsbaChuyenVien> hsbaCV = em.createQuery("select object(o) from HsbaChuyenVien as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbacvNgaygiocn DESC").setParameter("soVaoVien", soVaoVien).getResultList();
        //System.out.println(hsbaCV);
        if (hsbaCV != null && hsbaCV.size() > 0) {
            System.out.println("-------------------------");
            return hsbaCV.get(0);
        } else {
            return null;
        }
    }
    
    public String insert(HsbaChuyenVien obj){
        
        try{
            String ma = Utils.maHsbaChuyenVien(em);
            System.out.println("--- HsbaChuyenVien ma sinh ra (insert): " + ma);
            obj.setHsbacvMa(ma);
            if (obj.getHsbachuyenvienMaso() == null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            System.out.println("---insert hsba chuyen vien: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert hsba chuyen vien: err");        
            return "";
        }
    }
    
    public String update(HsbaChuyenVien obj){
        
        try{
            if (obj.getHsbacvMa() == null){
                String ma = Utils.maHsbaChuyenVien(em);
                System.out.println("--- setHsbacvMa (update): " + ma);
                obj.setHsbacvMa(ma);
            }
            System.out.println("--- HsbaChuyenVien ma sinh ra (update): " + obj.getHsbacvMa());
            if (obj.getHsbachuyenvienMaso() == null){
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            System.out.println("---update hsba chuyen vien: success");
            return obj.getHsbacvMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update hsba chuyen vien: err");        
            return "";
        }
    }
    
    public HsbaChuyenVien findByHsbacvMa(String ma){
        System.out.println("---find HsbaChuyenVien by HsbacvMa"); 
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaChuyenVien  as o where o.hsbacvMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaChuyenVien> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaChuyenVien) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}


