/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsThtoanFacade implements HsThtoanFacadeLocal, HsThtoanFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(HsThtoan hsThtoan) {
        if (hsThtoan.getHsthtoanMa() == null || hsThtoan.getHsthtoanMa().equals("")) {
            hsThtoan.setHsthtoanMa(Utils.createMaPhieu(em));
        }
        em.persist(hsThtoan);
    }

    public void edit(HsThtoan hsThtoan) {
        em.merge(hsThtoan);
    }

    public void remove(HsThtoan hsThtoan) {
        em.remove(em.merge(hsThtoan));
    }

    public HsThtoan find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsThtoan.class, id);
    }

    public List<HsThtoan> findAll() {
        return em.createQuery("select object(o) from HsThtoan as o").getResultList();
    }

    public HsThtoan findBySovaovien(String hsbaSovaovien) {

        hsbaSovaovien = Utils.formatMa(em, hsbaSovaovien);

        HsThtoan result = null;
        Hsba hsba = em.find(com.iesvn.yte.dieutri.entity.Hsba.class, hsbaSovaovien);
        Query q = em.createQuery("select object(o) from HsThtoan as o where o.hsbaSovaovien = :hsbaSovaovien");
        q.setParameter("hsbaSovaovien", hsba);
        List<HsThtoan> listHSTT = q.getResultList();
        System.out.println("List HsThtoan Size = " + listHSTT.size());
        if (listHSTT.size() > 0) {
            result = listHSTT.get(0);
            for(int i = 1; i < listHSTT.size(); i++) {
                remove(listHSTT.get(i));
            }
            System.out.println("Lay thanh cong " + result);
        }
        return result;
    }

    public String capNhatTTRaVien(HsThtoan hstt, Hsba hsba, HsbaChuyenVien hsbaCV) throws Exception {

        System.out.println("---capNhatTTRaVien----");
        String result = "";

        HsThtoan hsThtoan_temp = findBySovaovien(hsba.getHsbaSovaovien());
        if (hsThtoan_temp != null && hsThtoan_temp.getHsthtoanNgaytt() == null) {
            em.remove(hsThtoan_temp);
          
        }
        
        if (hsThtoan_temp != null && hsThtoan_temp.getHsthtoanNgaytt() != null) {
           
            return "";
        }

        System.out.println("Hsba:" + hsba.getHsbaSovaovien());
        try {
            if (hsba.getHsbaSovaovien() != null && !hsba.getHsbaSovaovien().equals("")) {
                em.merge(hsba);
                System.out.println("Update thanh cong Hsba" + hsba.getHsbaSovaovien());
            }
//            if (hstt.getHsthtoanMa() != null && !hstt.getHsthtoanMa().equals("")) {
//                hstt.setHsthtoanDatt(true);
//                double thToan = hstt.getHsthtoanThtoan()==null?0:hstt.getHsthtoanThtoan().doubleValue();
//                double bnTra =  hstt.getHsthtoanBntra()==null?0:hstt.getHsthtoanBntra().doubleValue();
//                
//                System.out.println("thToan:"+thToan);
//                System.out.println("bnTra:"+bnTra);
//                
//                
//                hstt.setHsthtoanBntra(bnTra + thToan);
//                
//                //hstt.setHsthtoanThtoan(new Double(0));
//                
////                Utils.setInforForHsThToan(hstt);
//                
//                em.merge(hstt);
//                System.out.println("Update thanh cong Hsthtoan" + hstt.getHsthtoanMa());
//            }
            if (hsbaCV.getHsbachuyenvienMaso() != null) {
                em.merge(hsbaCV);
                System.out.println("Update thanh cong Hsba chuyen vien" + hsbaCV.getHsbachuyenvienMaso().toString());
            } else {
                if (hsbaCV.getHsbacvChvienden() != null) {
                    hsbaCV.setHsbaSovaovien(hsba);

                    em.persist(hsbaCV);
                    System.out.println("insert thanh cong Hsthtoan" + hsbaCV.getHsbachuyenvienMaso().toString());
                }

            }
            //
            // luu tru ho so thanh toan
            if (hstt.getHsthtoanMa() == null || hstt.getHsthtoanMa().equals("")) {
                hstt.setHsthtoanMa(Utils.createMaPhieu(em));
                em.persist(hstt);
            } else {
                em.merge(hstt);
            }


            result = hstt.getHsthtoanMa();
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
            throw e;

        }
        return result;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<HsThtoan> findHsttCoThuPhi(String maSovaovien, String hoten) {
        if (!maSovaovien.trim().equals("")) {
            maSovaovien = Utils.formatMa(em, maSovaovien.trim());
            if (hoten.trim().equals("")) {
                System.out.println("---Tim kiem hstt co thu phi theo hsba");
                Query q = em.createQuery("select object(o) from HsThtoan as o where o.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien");
                q.setParameter("hsbaSovaovien", maSovaovien);
                return q.getResultList();
            } else {
                System.out.println("---Tim kiem hstt co thu phi theo hsba va ho ten");
                Query q = em.createQuery("select object(o) from HsThtoan as o where o.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien or lower(o.hsbaSovaovien.benhnhanMa.benhnhanHoten) like :hoten");
                q.setParameter("hsbaSovaovien", maSovaovien);
                q.setParameter("hoten", "%" + hoten.toLowerCase().trim() + "%");
                return q.getResultList();
            }
        } else {
            if (!hoten.trim().equals("")) {
                System.out.println("---Tim kiem hstt co thu phi theo ho ten");
                Query q = em.createQuery("select object(o) from HsThtoan as o where lower(o.hsbaSovaovien.benhnhanMa.benhnhanHoten) like :hoten");
                q.setParameter("hoten", "%" + hoten.toLowerCase().trim() + "%");
                return q.getResultList();
            }
        }
        return null;
    }
 public List<HsThtoan> findByNgayVaoVien(Date tungay, Date denngay) {
        try {
            String sSQL = "SELECT * FROM hs_thtoan h" +
            " where hsba_sovaovien IN(" +
            " SELECT hs.hsba_sovaovien FROM hsba hs where to_date(hs.hsba_ngaygiovaov) >= :tungay AND to_date(hs.hsba_ngaygiovaov) <= :denngay)";
            Query q = getEm().createNativeQuery(sSQL, HsThtoan.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<HsThtoan> findByNgayRaVien(Date tungay, Date denngay) {
        try {
            String sSQL = "SELECT * FROM hs_thtoan h" +
            " where hsba_sovaovien IN(" +
            " SELECT hs.hsba_sovaovien FROM hsba hs where to_date(hs.hsba_ngaygiorav) >= :tungay AND to_date(hs.hsba_ngaygiorav) <= :denngay)";
            Query q = getEm().createNativeQuery(sSQL, HsThtoan.class);
            q.setParameter("tungay", tungay);
            q.setParameter("denngay", denngay);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
