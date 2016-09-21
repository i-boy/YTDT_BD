/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.Query;

/**
 *
 * @author james
 */
@Stateless
public class PhieuGiaoBanFacade implements PhieuGiaoBanFacadeLocal, PhieuGiaoBanFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(PhieuGiaoBan phieuGiaoBan) {
        getEm().persist(phieuGiaoBan);
    }

    public void edit(PhieuGiaoBan phieuGiaoBan) {
        getEm().merge(phieuGiaoBan);
    }

    public void remove(PhieuGiaoBan phieuGiaoBan) {
        getEm().remove(getEm().merge(phieuGiaoBan));
    }

    public PhieuGiaoBan find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.PhieuGiaoBan.class, id);
    }

    public List<PhieuGiaoBan> findAll() {
        return getEm().createQuery("select object(o) from PhieuGiaoBan as o").getResultList();
    }

     public List<PhieuGiaoBan> findByPhieuGiaoBan(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = em.createQuery("SELECT c FROM PhieuGiaoBan c WHERE  c.pgbMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public String capNhatPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan, String maPhieuGiaoBan, List<PhieuGiaoBanThanhPhanThamDu> listTPTD,List<CtPhieuGiaoBan> listCTPGB) {
        String result = "";
        try {
            

            if (phieuGiaoBan != null) {
                if (maPhieuGiaoBan == null || maPhieuGiaoBan.equals("")) {
                    maPhieuGiaoBan = Utils.maPhieuGiaoBan(getEm());
                    phieuGiaoBan.setPgbMa(maPhieuGiaoBan);
                    getEm().persist(phieuGiaoBan);
                    System.out.println("INSERT thanh cong Phieu Giao Ban" + maPhieuGiaoBan);

                    PhieuGiaoBanThanhPhanThamDuFacade pgbTPTD= new PhieuGiaoBanThanhPhanThamDuFacade();
                    pgbTPTD.setEm(getEm());
                    pgbTPTD.capNhatPhieuGiaoBanTPTD(listTPTD, phieuGiaoBan);

                    CtPhieuGiaoBanFacade ctPgb=new CtPhieuGiaoBanFacade();
                    ctPgb.setEm(getEm());
                    ctPgb.capNhatPhieuGiaoBanChiTiet(listCTPGB, phieuGiaoBan);

                } else {
                    phieuGiaoBan.setPgbMa(maPhieuGiaoBan);
                    getEm().merge(phieuGiaoBan);
                    System.out.println("UPDATE thanh cong Phieu Giao Ban" + maPhieuGiaoBan);

                    PhieuGiaoBanThanhPhanThamDuFacade pgbTPTD= new PhieuGiaoBanThanhPhanThamDuFacade();
                    pgbTPTD.setEm(getEm());
                    pgbTPTD.capNhatPhieuGiaoBanTPTD(listTPTD, phieuGiaoBan);

                    CtPhieuGiaoBanFacade ctPgb=new CtPhieuGiaoBanFacade();
                    ctPgb.setEm(getEm());
                    ctPgb.capNhatPhieuGiaoBanChiTiet(listCTPGB, phieuGiaoBan);
                }
            }
            result = maPhieuGiaoBan;
        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public void removeAllPhieuGiaoBan(PhieuGiaoBan phieuGiaoBan)
    {
        try {
            PhieuGiaoBanThanhPhanThamDuFacade pgbTPTD= new PhieuGiaoBanThanhPhanThamDuFacade();
            pgbTPTD.setEm(em);
            pgbTPTD.removeAllTPTD(phieuGiaoBan);

            CtPhieuGiaoBanFacade ctPgb=new CtPhieuGiaoBanFacade();
            ctPgb.setEm(getEm());
            ctPgb.removeAllCtPhieuGiaoBan(phieuGiaoBan);

            getEm().remove(getEm().merge(phieuGiaoBan));

        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
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
