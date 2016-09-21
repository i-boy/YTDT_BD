/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thanh
 */
@Stateless
public class BenhAnNgoaiTruFacade implements BenhAnNgoaiTruFacadeLocal, BenhAnNgoaiTruFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BenhAnNgoaiTru benhAnNgoaiTru) {

        String maBenhAn = benhAnNgoaiTru.getBantMa();
        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnNgoai(em);
        
        }
        System.out.println("maBenhAn:"+maBenhAn);
        benhAnNgoaiTru.setBantMa(maBenhAn);

        em.persist(benhAnNgoaiTru);
    }

     public String create2(BenhAnNgoaiTru benhAnNgoaiTru) {

        String maBenhAn = benhAnNgoaiTru.getBantMa();
        if (maBenhAn == null || maBenhAn.equals("")){
            maBenhAn = com.iesvn.yte.dieutri.utils.Utils.maBenhAnNgoai(em);

        }
        System.out.println("maBenhAn:"+maBenhAn);
        benhAnNgoaiTru.setBantMa(maBenhAn);

        em.persist(benhAnNgoaiTru);
        return maBenhAn;
    }

    public void edit(BenhAnNgoaiTru benhAnNgoaiTru) {
        em.merge(benhAnNgoaiTru);
    }

    public void remove(BenhAnNgoaiTru benhAnNgoaiTru) {
        em.remove(em.merge(benhAnNgoaiTru));
    }

    public BenhAnNgoaiTru find(Object id) {
        return em.find(BenhAnNgoaiTru.class, id);
    }

    public List<BenhAnNgoaiTru> findAll() {
        return em.createQuery("select object(o) from BenhAnNgoaiTru as o").getResultList();
    }

    public BenhAnNgoaiTru getBANgoaiTru(Integer thamkham){
          System.out.println("call getBANgoaiTru");

       List<BenhAnNgoaiTru> lstBANgoaiTru =  em.createQuery("select object(o) from BenhAnNgoaiTru as o where o.bantThamkham.thamkhamMa = :thamkham ").setParameter("thamkham", thamkham).getResultList();
       System.out.println("lstBANgoaiTru:"+lstBANgoaiTru);


       if (lstBANgoaiTru != null && lstBANgoaiTru.size() > 0){
           return lstBANgoaiTru.get(0);
       }else{
           return null;
       }
     }

    public String capNhatBenhAnNgoaiTru(BenhAnNgoaiTru obj, String sMaPhieu, List<CtBenhAnNgoaiTru> lstCT) {
        String result = "";
        
        // 20111119 bao.ttc: them BANT_SOVAOVIEN (giong SOVAOVIEN khi dung chuc nang Lap Benh an ngoai tru) de hien thi khi In
        if (obj.getBantSovaovien() == null || obj.getBantSovaovien().equals("")){
            obj.setBantSovaovien(Utils.getSoVaoVien(getEm(),5));
            System.out.println("******* BenhAnNgoaiTru - SOVAOVIEN: " + obj.getBantSovaovien());
        }
        if (sMaPhieu == null || sMaPhieu.equals("")) {
            sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
            result = sMaPhieu;
            obj.setBantMa(sMaPhieu);
            getEm().persist(obj);
            System.out.println("******* INSERT SUCCESS BenhAnNgoaiTru ******");
        } else {
            result = sMaPhieu;
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS BenhAnNgoaiTru ******");
        }
        System.out.println("******* Ma BenhAnNgoaiTru: " + result);
        ///////////////////////////////////////////////////////////////////////
        try {
            String listpcsMa = "";
            for (int i = 0; i < lstCT.size(); i++) {
                CtBenhAnNgoaiTru pcs = lstCT.get(i);
                if (pcs.getCtbantMaso() != null) {
                    if (listpcsMa.equals("")) {
                        listpcsMa += pcs.getCtbantMaso();
                    } else {
                        listpcsMa += "," + pcs.getCtbantMaso();
                    }
                }
            }
            System.out.print("listpcsMa:" + listpcsMa);
            this.removeItem(listpcsMa, obj.getBantThamkham().getThamkhamMa());
            for (CtBenhAnNgoaiTru pcs : lstCT) {
                pcs.setBenhAnNgoaiTru(obj);
                if (pcs.getCtbantMaso() != null) {
                    getEm().merge(pcs);
                    System.out.println("update thanh cong chi tiet pcs");
                } else {
                    getEm().persist(pcs);
                    System.out.println("insert them thanh cong chi tiet ");
                }
            }
            //result = "";
        } catch (Exception ex) {
            //result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        
        return result;
    }

        private void removeItem(String listpcsMa, Integer thamkhamma) {
        Query q;
        if (listpcsMa == null || listpcsMa.equals("")) {
            q = getEm().createQuery("SELECT c FROM CtBenhAnNgoaiTru c WHERE   c.benhAnNgoaiTru.bantThamkham.thamkhamMa = :thamkhamma ");
        } else {
            q = getEm().createQuery("SELECT c FROM CtBenhAnNgoaiTru c WHERE  c.ctbantMaso NOT IN (" + listpcsMa + ") AND  c.benhAnNgoaiTru.bantThamkham.thamkhamMa = :thamkhamma ");
        }
        q.setParameter("thamkhamma", thamkhamma);
        System.out.println("thamkhamma:" + thamkhamma);
        List<CtBenhAnNgoaiTru> listCLS = q.getResultList();
        System.out.println("listCLS:" + listCLS);
        for (CtBenhAnNgoaiTru pcs : listCLS) {
            getEm().remove(pcs);
        }
    }
    
    public BenhAnNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham){
       System.out.println("call findBySotheBHAndMaBNAndBanKham");
       // String strQuery = "select object(o) from BenhAnNgoaiTru as o where o.bantBenhnhan.benhnhanMa = :maBN AND o.bantBankham.dtdmbankhamMaso = :bankham ";
       // 20111220 bao.ttc: dieu chinh nghiep vu: Khong can search theo ban kham
       String strQuery = "select object(o) from BenhAnNgoaiTru as o where o.bantBenhnhan.benhnhanMa = :maBN ";
       if (sotheBH != null) {
           strQuery += "AND o.bantSothebh = :sotheBH ";
       }
       Query q = em.createQuery(strQuery);
       q.setParameter("maBN", maBN);
       // q.setParameter("bankham", bankham);
       if (sotheBH != null) {
            q.setParameter("sotheBH", sotheBH);
       }
       List<BenhAnNgoaiTru> lstBANgoaiTru =  q.getResultList();
       System.out.println("lstBANgoaiTru:"+lstBANgoaiTru);


       if (lstBANgoaiTru != null && lstBANgoaiTru.size() > 0){
           return lstBANgoaiTru.get(lstBANgoaiTru.size() - 1);
       }else{
           return null;
       }
     }
    
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
