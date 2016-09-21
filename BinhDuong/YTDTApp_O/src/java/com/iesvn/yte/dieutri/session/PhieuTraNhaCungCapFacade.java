/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thanh
 */
@Stateless
public class PhieuTraNhaCungCapFacade implements PhieuTraNhaCungCapFacadeLocal, PhieuTraNhaCungCapFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @Resource
    private SessionContext context;

    public void create(PhieuTraNhaCungCap phieuTraNhaCungCap) {
        getEm().persist(phieuTraNhaCungCap);
    }

    public void edit(PhieuTraNhaCungCap phieuTraNhaCungCap) {
        getEm().merge(phieuTraNhaCungCap);
    }

    public void remove(PhieuTraNhaCungCap phieuTraNhaCungCap) {
        getEm().remove(getEm().merge(phieuTraNhaCungCap));
    }

    public PhieuTraNhaCungCap find(Object id) {
        return getEm().find(PhieuTraNhaCungCap.class, id);
    }

    public List<PhieuTraNhaCungCap> findAll() {
        return getEm().createQuery("select object(o) from PhieuTraNhaCungCap as o").getResultList();
    }

    /**
     * Cap nhat phieu xuat va chi tiet xuat
     * @param pxk
     * @param listCtXuatKho
     * @return
     */
    public String createPhieuTraNhaCungCap(PhieuTraNhaCungCap pxk, List<CtTraNhaCungCap> listCtTraNhaCungCap, List<TonKho> listTkXuat) {

        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(getEm());

        System.out.println("-----createPhieuTraNhaCungCap()-----");
        String result = "";
        String pxkMa = ""; //+ pxk.getPhieuxuatkhoMa();

        System.out.println("nhan vien cn: " + pxk.getDtdmnhanvienCn().getDtdmnhanvienMaso());
        System.out.println("listCtTraNhaCungCap.size: " + listCtTraNhaCungCap.size());

        if (pxkMa.equals("") || pxkMa.equals("null")) {
            // them moi phieu xuat
            System.out.println("-----Insert phieu xuat");
            pxkMa = Utils.maPhieuXuat(getEm());
            pxk.setPhieutranhacungcapMa(pxkMa);
            getEm().persist(pxk);
            System.out.println("Insert phieu xuat: " + pxk.getPhieutranhacungcapMa());
        }

        for (int i = 0; i < listCtTraNhaCungCap.size(); i++) {
            System.out.println("-----Insert ct xuat");
            CtTraNhaCungCap ctx = listCtTraNhaCungCap.get(i);

            TonKho tkXuat = listTkXuat.get(i);

            tkXuat.setDmkhoaMaso(pxk.getDmkhoaXuat());
//            tkNhan.setTonkhoMalienket(ctx.getCtxuatkhoMalk());
//            tkXuat.setTonkhoMalienket(ctx.getCtxuatkhoMalk());
            if (tkFacade.insertTonKho(tkXuat)) {

                System.out.println("ton kho xuat: " + tkXuat.getTonkhoMa());
                ctx.setPhieutranhacungcapMa(pxk);
                ctx.setTonKhoMa(tkXuat.getTonkhoMa());
                getEm().persist(ctx);
                System.out.println("ct xuat ma: " + ctx.getPhieutranhacungcapMa());
                result = pxk.getPhieutranhacungcapMa();
            } else {
                result = "";
            }
        }

        System.out.println("result: " + result);
        return result;
    }

    public String updatePhieuTraNhaCungCap(PhieuTraNhaCungCap objPhieuTraNCC, List<CtTraNhaCungCap> listCtTraNCC, List<TonKho> listTonKhoXuat) {
        String result = "";
        try {

            // Truong hop cap nhat
            if (objPhieuTraNCC.getPhieutranhacungcapMa() != null) {
                getEm().merge(objPhieuTraNCC);
                System.out.println("UPDATE SUCCESS PhieuTraNhaCungCap" + objPhieuTraNCC.getPhieutranhacungcapMa());

                CtTraNhaCungCapFacade objCtTraNhaCC = new CtTraNhaCungCapFacade();
                objCtTraNhaCC.setEm(getEm());
                objCtTraNhaCC.updateCtTraNhaCungCap(objPhieuTraNCC, listCtTraNCC);
                result = objPhieuTraNCC.getPhieutranhacungcapMa();
            } // Truong hop them moi
            else {
                String sMaPhieu = Utils.maPhieuTraNhacCungCap(getEm());
                objPhieuTraNCC.setPhieutranhacungcapMa(sMaPhieu);
                getEm().persist(objPhieuTraNCC);
                System.out.println("INSERT SUCCESS PhieuTraNhaCungCap" + objPhieuTraNCC.getPhieutranhacungcapMa());

                CtTraNhaCungCapFacade objCtTraNCC = new CtTraNhaCungCapFacade();
                objCtTraNCC.setEm(getEm());
                objCtTraNCC.updateCtTraNhaCungCap(objPhieuTraNCC, listCtTraNCC);


                System.out.println("-----START INSERT TON KHO-----");
                for (int i = 0; i < listCtTraNCC.size(); i++) {
                    System.out.println("-----Insert Ton Kho ");
                    CtTraNhaCungCap ctx = listCtTraNCC.get(i);
                    TonKho tkXuat = listTonKhoXuat.get(i);
                    tkXuat.setDmkhoaMaso(objPhieuTraNCC.getDmkhoaXuat());
                    if (tkFacade.insertTonKho(tkXuat)) {
                        System.out.println("ton xuat: " + tkXuat.getTonkhoXuat());
                        System.out.println("ton kho nhap: " + tkXuat.getTonkhoNhap());
                        System.out.println("ton kho : " + tkXuat.getTonkhoTon());
                        ctx.setTonKhoMa(tkXuat.getTonkhoMa());
                        em.persist(ctx);
                        System.out.println("ct xuat ma: " + ctx.getCttranhacungcapMa());
                    }
                }
                result = objPhieuTraNCC.getPhieutranhacungcapMa();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public PhieuTraNhaCungCap findPhieuTraNhaCungCapByMa(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        try {
            Query q;
            q = getEm().createQuery("SELECT object(c) FROM PhieuTraNhaCungCap as c WHERE  c.phieutranhacungcapMa like :maPhieu ");
            q.setParameter("maPhieu", maPhieu);
            List<PhieuTraNhaCungCap> list = new ArrayList<PhieuTraNhaCungCap>();
            list = q.getResultList();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * @return the context
     */
    public SessionContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(SessionContext context) {
        this.context = context;
    }
}
