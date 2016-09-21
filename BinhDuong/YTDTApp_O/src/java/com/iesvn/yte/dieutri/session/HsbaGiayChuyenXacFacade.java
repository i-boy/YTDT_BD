/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author quang
 */
@Stateless
public class HsbaGiayChuyenXacFacade implements HsbaGiayChuyenXacFacadeRemote, HsbaGiayChuyenXacFacadeLocal {
    
   @PersistenceContext
    private EntityManager em;

    public void create(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
        em.persist(hsbaGiayChuyenXac);
    }

    public void edit(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
        em.merge(hsbaGiayChuyenXac);
    }

    public void remove(HsbaGiayChuyenXac hsbaGiayChuyenXac) {
        em.remove(em.merge(hsbaGiayChuyenXac));
    }

    public HsbaGiayChuyenXac find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac.class, id);
    }

    public List<HsbaGiayChuyenXac> findAll() {
        return em.createQuery("select object(o) from HsbaGiayChuyenXac as o").getResultList();
    }

    public String insert(HsbaGiayChuyenXac obj){
        System.out.println("---insert giay ra vien");
        try{
            String ma = Utils.maGiayChuyenXac(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbagcxMa(ma);
            em.persist(obj);
            System.out.println("---insert giay ra vien: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert giay ra vien: err");
            return "";
        }
    }

    public String update(HsbaGiayChuyenXac obj){
        System.out.println("---update giay ra vien");
        try{
            System.out.println("---ma update: " + obj.getHsbagcxMa());
            em.merge(obj);
            System.out.println("---update giay ra vien: success");
            return obj.getHsbagcxMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update giay ra vien: err");
            return "";
        }
    }

    public HsbaGiayChuyenXac findByHsbagcxMa(String ma){
        System.out.println("---find HsbaGiayChuyenXac by HsbagcxMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaGiayChuyenXac  as o where o.hsbagcxMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaGiayChuyenXac> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaGiayChuyenXac) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
 
}
