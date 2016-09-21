/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.jpql.criterion.JpqlString;
import com.iesvn.yte.dieutri.delegate.CtPhieuKhamDtNgoaiTruDelegate;
import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author james
 */
@Stateless
public class PhieuKhamDtNgoaiTruFacade implements PhieuKhamDtNgoaiTruFacadeLocal, PhieuKhamDtNgoaiTruFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru) {
        getEm().persist(phieuKhamDtNgoaiTru);
    }

    public void edit(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru) {
        getEm().merge(phieuKhamDtNgoaiTru);
    }

    public void remove(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru) {
        getEm().remove(getEm().merge(phieuKhamDtNgoaiTru));
    }

    public PhieuKhamDtNgoaiTru find(Object id) {
        return getEm().find(PhieuKhamDtNgoaiTru.class, id);
    }

    public List<PhieuKhamDtNgoaiTru> findAll() {
        return getEm().createQuery("select object(o) from PhieuKhamDtNgoaiTru as o").getResultList();
    }

    public List<PhieuKhamDtNgoaiTru> findByPhieuKhamDtNgoaiTru(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = getEm().createQuery("SELECT c FROM PhieuKhamDtNgoaiTru c WHERE  c.pkdtntMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public PhieuKhamDtNgoaiTru findByMaThamKham(Integer mathamkham) {
        PhieuKhamDtNgoaiTru result = null;
        JpqlString newsql = new JpqlString();
        newsql.setSelect("select object(o) from PhieuKhamDtNgoaiTru as o");
        List paramValues = newsql.getParamValues();
        if (mathamkham != null) {
            newsql.add(com.iesvn.jpql.criterion.util.Utils.addEqExpr(paramValues, mathamkham, "o.pkdtntThamkham.thamkhamMa"));
        }
        String newString = newsql.toSqlString();
        Query qry = getEm().createQuery(newString);
        for (int i = 1; i <= paramValues.size(); i++) {
            qry.setParameter(i, paramValues.get(i - 1));
        }
        try {
            result = (PhieuKhamDtNgoaiTru) qry.getResultList().get(0);
        } catch (Exception e) {
        }
        return result;
    }

    public String capNhatPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru obj, String sMaPhieu, List<CtPhieuKhamDtNgoaiTru> lstCT) {
        String result = "";
        if (obj.getPkdtntSovaovien() == null || obj.getPkdtntSovaovien().equals("")){
            obj.setPkdtntSovaovien(Utils.getSoVaoVien(getEm(),5));
            System.out.println("******* PhieuKhamDtNgoaiTru - SOVAOVIEN: " + obj.getPkdtntSovaovien());
        }
        if (sMaPhieu == null || sMaPhieu.equals("")) {
            sMaPhieu = Utils.maGiayCvNbBhyt(getEm());
            result = sMaPhieu;
            obj.setPkdtntMa(sMaPhieu);
            getEm().persist(obj);
            System.out.println("******* INSERT SUCCESS NhatPhieuKhamDtNgoaiTru ******");
        } else {
            result = sMaPhieu;
            obj.setPkdtntMa(sMaPhieu);
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS NhatPhieuKhamDtNgoaiTru ******");
        }
        System.out.println("******* Ma NhatPhieuKhamDtNgoaiTru: " + result);
        ///////////////////////////////////////////////////////////////////////
        try {
            String listpcsMa = "";
            for (int i = 0; i < lstCT.size(); i++) {
                CtPhieuKhamDtNgoaiTru pcs = lstCT.get(i);
                if (pcs.getCtpkdtntMaso() != null) {
                    if (listpcsMa.equals("")) {
                        listpcsMa += pcs.getCtpkdtntMaso();
                    } else {
                        listpcsMa += "," + pcs.getCtpkdtntMaso();
                    }
                }
            }
            System.out.print("listpcsMa:" + listpcsMa);
            this.removeItem(listpcsMa, obj.getPkdtntThamkham().getThamkhamMa());
            for (CtPhieuKhamDtNgoaiTru pcs : lstCT) {
                pcs.setPhieuKcbNgoaiTru(obj);
                if (pcs.getCtpkdtntMaso() != null) {
                    getEm().merge(pcs);
                    System.out.println("update thanh cong chi tiet pcs");
                } else {
                    getEm().persist(pcs);
                    System.out.println("insert them thanh cong chi tiet ");
                }
            }
            //result = "";
        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        System.out.println("****************result " + result);
        ///////////////////////////////////////////////////////////////////////
        return result;
    }

    private void removeItem(String listpcsMa, Integer thamkhamma) {
        Query q;
        if (listpcsMa == null || listpcsMa.equals("")) {
            q = getEm().createQuery("SELECT c FROM CtPhieuKhamDtNgoaiTru c WHERE   c.phieuKcbNgoaiTru.pkdtntThamkham.thamkhamMa = :thamkhamma ");
        } else {
            q = getEm().createQuery("SELECT c FROM CtPhieuKhamDtNgoaiTru c WHERE  c.ctpkdtntMaso NOT IN (" + listpcsMa + ") AND  c.phieuKcbNgoaiTru.pkdtntThamkham.thamkhamMa = :thamkhamma ");
        }
        q.setParameter("thamkhamma", thamkhamma);
        System.out.println("thamkhamma:" + thamkhamma);
        List<CtPhieuKhamDtNgoaiTru> listCLS = q.getResultList();
        System.out.println("listCLS:" + listCLS);
        for (CtPhieuKhamDtNgoaiTru pcs : listCLS) {
            getEm().remove(pcs);
        }
    }

    public void removeAllPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru) {
        Query q = null;
        try {
            if (phieuKhamDtNgoaiTru != null) {
                CtPhieuKhamDtNgoaiTruFacade objCtPhieuKhamDtNgoaiTruFa=new CtPhieuKhamDtNgoaiTruFacade();
                objCtPhieuKhamDtNgoaiTruFa.setEm(getEm());
                objCtPhieuKhamDtNgoaiTruFa.removeAllCtPhieuKhamDtNgoaiTru(phieuKhamDtNgoaiTru);

                PhieuKhamDtNgoaiTruFacade objPhieuKhamDtNgoaiTruFa=new PhieuKhamDtNgoaiTruFacade();
                objPhieuKhamDtNgoaiTruFa.setEm(em);
                objPhieuKhamDtNgoaiTruFa.remove(phieuKhamDtNgoaiTru);
                System.out.println("****** DELETE SUCCESSFUL PhieuKhamDtNgoaiTru AND CtPhieuKhamDtNgoaiTru");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    public PhieuKhamDtNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham){          
       // String strQuery = "select object(o) from PhieuKhamDtNgoaiTru as o where o.pkdtntBenhnhan.benhnhanMa = :maBN AND o.pkdtntBankham.dtdmbankhamMaso = :bankham ";
       // 20111220 bao.ttc: dieu chinh nghiep vu: Khong can search theo ban kham
       String strQuery = "select object(o) from PhieuKhamDtNgoaiTru as o where o.pkdtntBenhnhan.benhnhanMa = :maBN ";

       if (sotheBH != null) {
           strQuery += "AND o.pkdtntSothebh = :sotheBH ";
       }
       Query q = em.createQuery(strQuery);
       q.setParameter("maBN", maBN);
       // q.setParameter("bankham", bankham);
       if (sotheBH != null) {
            q.setParameter("sotheBH", sotheBH);
       }
       List<PhieuKhamDtNgoaiTru> lstPhieuDTNgoaiTru =  q.getResultList();

       if (lstPhieuDTNgoaiTru != null && lstPhieuDTNgoaiTru.size() > 0){
           return lstPhieuDTNgoaiTru.get(lstPhieuDTNgoaiTru.size() - 1);
       }else{
           return null;
       }
     }

    /**
     * @return the em
     */
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
