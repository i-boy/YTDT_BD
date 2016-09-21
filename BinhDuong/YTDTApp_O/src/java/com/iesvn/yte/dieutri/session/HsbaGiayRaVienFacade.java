/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
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
public class HsbaGiayRaVienFacade implements HsbaGiayRaVienFacadeLocal, HsbaGiayRaVienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaGiayRaVien hsbaGiayRaVien) {
        em.persist(hsbaGiayRaVien);
    }

    public void edit(HsbaGiayRaVien hsbaGiayRaVien) {
        em.merge(hsbaGiayRaVien);
    }

    public void remove(HsbaGiayRaVien hsbaGiayRaVien) {
        em.remove(em.merge(hsbaGiayRaVien));
    }

    public HsbaGiayRaVien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayRaVien.class, id);
    }

    public List<HsbaGiayRaVien> findAll() {
        return em.createQuery("select object(o) from HsbaGiayRaVien as o").getResultList();
    }
    
    public String insert(HsbaGiayRaVien obj){
        System.out.println("---insert giay ra vien");        
        try{
            String ma = Utils.maGiayRaVien(em);
            System.out.println("---ma sinh ra: " + ma);        
            obj.setHsbagrvMa(ma);
            em.persist(obj);
            System.out.println("---insert giay ra vien: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert giay ra vien: err");        
            return "";
        }
    }
    
    public String update(HsbaGiayRaVien obj){
        System.out.println("---update giay ra vien");        
        try{
            System.out.println("---ma update: " + obj.getHsbagrvMa());                   
            em.merge(obj);
            System.out.println("---update giay ra vien: success");
            return obj.getHsbagrvMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update giay ra vien: err");        
            return "";
        }
    } 
    
    public HsbaGiayRaVien findByHsbagrvMa(String ma){
        System.out.println("---find HsbaGiayRaVien by HsbagrvMa"); 
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaGiayRaVien  as o where o.hsbagrvMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaGiayRaVien> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaGiayRaVien) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public HsbaGiayRaVien findBySoVaoVien(String soVaoVien) {

         List<HsbaGiayRaVien> hsbaGRV = em.createQuery("select object(o) from HsbaGiayRaVien as o where o.hsbaSovaovien.hsbaSovaovien = :soVaoVien order by o.hsbagrvNgaygiocn DESC , o.hsbagrvMaso DESC " ).setParameter("soVaoVien", soVaoVien).getResultList();
//        System.out.println(hsbaGRV);
        if (hsbaGRV != null && hsbaGRV.size() > 0) {
            System.out.println("-------------------------");
            return hsbaGRV.get(0);
        } else {
            return null;
        }
    }

}


