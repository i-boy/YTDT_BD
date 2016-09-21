/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BaNgoaiTruYhct;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author i-boy
 */
@Stateless
public class BaNgoaiTruYhctFacade implements BaNgoaiTruYhctFacadeLocal, BaNgoaiTruYhctFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(BaNgoaiTruYhct baNgoaiTruYhct) {

        String maBenhAn = baNgoaiTruYhct.getBantYhctMa();

        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnNgoai(em);
        }

        System.out.println("maBenhAn:"+maBenhAn);
        baNgoaiTruYhct.setBantYhctMa(maBenhAn);

        em.persist(baNgoaiTruYhct);
    }

     public String create2(BaNgoaiTruYhct baNgoaiTruYhct) {

        String maBenhAn = baNgoaiTruYhct.getBantYhctMa();

        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnNgoai(em);
        }

        System.out.println("maBenhAn:"+maBenhAn);
        baNgoaiTruYhct.setBantYhctMa(maBenhAn);

        em.persist(baNgoaiTruYhct);
        return maBenhAn;
    }

    public void edit(BaNgoaiTruYhct baNgoaiTruYhct) {
        em.merge(baNgoaiTruYhct);
    }

    public void remove(BaNgoaiTruYhct baNgoaiTruYhct) {
        em.remove(em.merge(baNgoaiTruYhct));
    }

    public BaNgoaiTruYhct find(Object id) {
        return em.find(BaNgoaiTruYhct.class, id);
    }

    public List<BaNgoaiTruYhct> findAll() {
        return em.createQuery("select object(o) from BaNgoaiTruYhct as o").getResultList();
    }

    public BaNgoaiTruYhct getBANgoaiTruYhct(Integer thamkham){
          System.out.println("call getBANgoaiTruYhct");

       List<BaNgoaiTruYhct> lstBANgoaiTruYhct =  em.createQuery("select object(o) from BaNgoaiTruYhct as o where o.bantYhctThamkham.thamkhamMa = :thamkham ").setParameter("thamkham", thamkham).getResultList();
       System.out.println("lstBANgoaiTruYhct: " + lstBANgoaiTruYhct);


       if (lstBANgoaiTruYhct != null && lstBANgoaiTruYhct.size() > 0){
           return lstBANgoaiTruYhct.get(0);
       } else {
           return null;
       }
    }

    public String capNhatBenhAnNgoaiTruYhct(BaNgoaiTruYhct obj, String sMaPhieu) {
        String result = "";

        // 20111119 bao.ttc: them BANT_SOVAOVIEN (giong SOVAOVIEN khi dung chuc nang Lap Benh an ngoai tru) de hien thi khi In
        if (obj.getBantYhctSovaovien() == null || obj.getBantYhctSovaovien().equals("")){
            obj.setBantYhctSovaovien(Utils.getSoVaoVien(getEm(),5));
            System.out.println("******* BenhAnNgoaiTruYhct - SOVAOVIEN: " + obj.getBantYhctSovaovien());
        }

        if (sMaPhieu == null || sMaPhieu.equals("")) {
            sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
            result = sMaPhieu;
            obj.setBantYhctMa(sMaPhieu);
            getEm().persist(obj);
            System.out.println("******* INSERT SUCCESS BenhAnNgoaiTruYhct ******");
        } else {
            result = sMaPhieu;
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS BenhAnNgoaiTruYhct ******");
        }
        System.out.println("******* Ma BenhAnNgoaiTruYhct: " + result);
        ///////////////////////////////////////////////////////////////////////


        return result;
    }


    public BaNgoaiTruYhct findBySotheBHAndMaBNAndBanKhamYhct(String sotheBH, String maBN, Integer bankham){

       System.out.println("call findBySotheBHAndMaBNAndBanKhamYhct");
       // String strQuery = "select object(o) from BaNgoaiTruYhct as o where o.bantYhctBenhnhan.benhnhanMa = :maBN AND o.bantYhctBankham.dtdmbankhamMaso = :bankham ";
       // 20111220 bao.ttc: dieu chinh nghiep vu: Khong can search theo ban kham
       String strQuery = "select object(o) from BaNgoaiTruYhct as o where o.bantYhctBenhnhan.benhnhanMa = :maBN ";

       if (sotheBH != null) {
           strQuery += "AND o.bantYhctSothebh = :sotheBH ";
       }

       Query q = em.createQuery(strQuery);
       q.setParameter("maBN", maBN);
       // q.setParameter("bankham", bankham);

       if (sotheBH != null) {
            q.setParameter("sotheBH", sotheBH);
       }

       List<BaNgoaiTruYhct> lstBANgoaiTruYhct =  q.getResultList();
       System.out.println("lstBANgoaiTruYhct: " + lstBANgoaiTruYhct);

       if (lstBANgoaiTruYhct != null && lstBANgoaiTruYhct.size() > 0){
           return lstBANgoaiTruYhct.get(lstBANgoaiTruYhct.size() - 1);
       }else{
           return null;
       }
     }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
