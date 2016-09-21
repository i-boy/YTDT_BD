/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan;
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
public class HsbaGiayChungNhanFacade implements HsbaGiayChungNhanFacadeRemote, HsbaGiayChungNhanFacadeLocal {
    
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaGiayChungNhan hsbaGiayChungNhan) {
        em.persist(hsbaGiayChungNhan);
    }

    public void edit(HsbaGiayChungNhan hsbaGiayChungNhan) {
        em.merge(hsbaGiayChungNhan);
    }

    public void remove(HsbaGiayChungNhan hsbaGiayChungNhan) {
        em.remove(em.merge(hsbaGiayChungNhan));
    }

    public HsbaGiayChungNhan find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan.class, id);
    }

    public List<HsbaGiayChungNhan> findAll() {
        return em.createQuery("select object(o) from HsbaGiayChungNhan as o").getResultList();
    }
    public HsbaGiayChungNhan findBySoVaoVien(String soVaoVien) {
        if (soVaoVien.length() < 14) {
            soVaoVien = Utils.formatMa(em, soVaoVien);
        // System.out.println(soVaoVien);
        }

        List<HsbaGiayChungNhan> hsbaCV = em.createQuery("select object(o) from HsbaGiayChungNhan as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbagcnNgaygiocn DESC").setParameter("soVaoVien", soVaoVien).getResultList();
        //System.out.println(hsbaCV);
        if (hsbaCV != null && hsbaCV.size() > 0) {
            System.out.println("-------------------------");
            return hsbaCV.get(0);
        } else {
            return null;
        }
    }

    public String insert(HsbaGiayChungNhan obj){
        System.out.println("---insert hsba chung nhan");
        try{
            String ma = Utils.maGiayChungNhan(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbagcnMa(ma);
            em.persist(obj);
            System.out.println("---insert hsba chung nhan: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert hsba chung nhan: err");
            return "";
        }
    }

    public String update(HsbaGiayChungNhan obj){
        System.out.println("---update hsba chung nhan");
        try{
            System.out.println("---ma update: " + obj.getHsbagcnMa());
            em.merge(obj);
            System.out.println("---update hsba chung nhan: success");
            return obj.getHsbagcnMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update hsba chung nhan: err");
            return "";
        }
    }

    public HsbaGiayChungNhan findByHsbagcnMa(String ma){
        System.out.println("---find HsbaGiayChungNhan by HsbacvMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaGiayChungNhan  as o where o.hsbagcnMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaGiayChungNhan> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaGiayChungNhan) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
 
}
