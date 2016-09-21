/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong;
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
public class HsbaGiayChungThuongFacade implements HsbaGiayChungThuongFacadeLocal, HsbaGiayChungThuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    
    public void create(HsbaGiayChungThuong hsbaGiayChungThuong) {
        em.persist(hsbaGiayChungThuong);
    }

    public void edit(HsbaGiayChungThuong hsbaGiayChungThuong) {
        em.merge(hsbaGiayChungThuong);
    }

    public void remove(HsbaGiayChungThuong hsbaGiayChungThuong) {
        em.remove(em.merge(hsbaGiayChungThuong));
    }

    public HsbaGiayChungThuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong.class, id);
    }

    public List<HsbaGiayChungThuong> findAll() {
        return em.createQuery("select object(o) from HsbaGiayChungThuong as o").getResultList();
    }
    
    public String insert(HsbaGiayChungThuong gct){
        System.out.println("---insert giay chung thuong");        
        try{
            String ma = Utils.maGiayChungThuong(em);
            System.out.println("---ma sinh ra: " + ma);        
            gct.setHsbagctMa(ma);
            em.persist(gct);
            System.out.println("---insert giay chung thuong: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert giay chung thuong: err");        
            return "";
        }
    }
    
    public String update(HsbaGiayChungThuong gct){
        System.out.println("---update giay chung thuong");        
        try{
            System.out.println("---ma update: " + gct.getHsbagctMa());                   
            em.merge(gct);
            System.out.println("---update giay chung thuong: success");
            return gct.getHsbagctMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update giay chung thuong: err");        
            return "";
        }
    }
    
    public HsbaGiayChungThuong findByHsbagctMa(String ma){
        System.out.println("---find Hsbagct by HsbagctMa"); 
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaGiayChungThuong  as o where o.hsbagctMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaGiayChungThuong> list = query.getResultList();
            if (list != null && list.size()>0) {
                return (HsbaGiayChungThuong) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}


