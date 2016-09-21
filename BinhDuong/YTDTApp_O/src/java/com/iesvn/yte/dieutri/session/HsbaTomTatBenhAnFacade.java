/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn;
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
public class HsbaTomTatBenhAnFacade implements HsbaTomTatBenhAnFacadeRemote, HsbaTomTatBenhAnFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void create(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
        em.persist(hsbaTomTatBenhAn);
    }

    public void edit(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
        em.merge(hsbaTomTatBenhAn);
    }

    public void remove(HsbaTomTatBenhAn hsbaTomTatBenhAn) {
        em.remove(em.merge(hsbaTomTatBenhAn));
    }

    public HsbaTomTatBenhAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn.class, id);
    }

    public List<HsbaTomTatBenhAn> findAll() {
        return em.createQuery("select object(o) from HsbaTomTatBenhAn as o").getResultList();
    }
    public HsbaTomTatBenhAn findBySoVaoVien(String soVaoVien) {
        if (soVaoVien.length() < 14) {
            soVaoVien = Utils.formatMa(em, soVaoVien);
        // System.out.println(soVaoVien);
        }

        List<HsbaTomTatBenhAn> hsbaTTBA = em.createQuery("select object(o) from HsbaTomTatBenhAn as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien").setParameter("soVaoVien", soVaoVien).getResultList();
        //System.out.println(hsbaCV);
        if (hsbaTTBA != null && hsbaTTBA.size() > 0) {
            System.out.println("-------------------------");
            return hsbaTTBA.get(0);
        } else {
            return null;
        }
    }

    public String insert(HsbaTomTatBenhAn obj){
        System.out.println("---insert hsba chung nhan");
        try{
            String ma = Utils.maTomTatBenhAn(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbattbaMa(ma);
            em.persist(obj);
            System.out.println("---insert hsba chung nhan: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert hsba chung nhan: err");
            return "";
        }
    }

    public String update(HsbaTomTatBenhAn obj){
        System.out.println("---update hsba chung nhan");
        try{
            System.out.println("---ma update: " + obj.getHsbattbaMa());
            em.merge(obj);
            System.out.println("---update hsba chung nhan: success");
            return obj.getHsbattbaMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update hsba chung nhan: err");
            return "";
        }
    }

    public HsbaTomTatBenhAn findByHsbattbaMa(String ma){
        System.out.println("---find HsbaTomTatBenhAn by HsbattbaMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaTomTatBenhAn  as o where o.hsbattbaMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaTomTatBenhAn> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaTomTatBenhAn) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

}
