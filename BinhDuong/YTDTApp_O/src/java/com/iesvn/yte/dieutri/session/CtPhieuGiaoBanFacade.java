/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author james
 */
@Stateless
public class CtPhieuGiaoBanFacade implements CtPhieuGiaoBanFacadeLocal, CtPhieuGiaoBanFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(CtPhieuGiaoBan ctPhieuGiaoBan) {
        getEm().persist(ctPhieuGiaoBan);
    }

    public void edit(CtPhieuGiaoBan ctPhieuGiaoBan) {
        getEm().merge(ctPhieuGiaoBan);
    }

    public void remove(CtPhieuGiaoBan ctPhieuGiaoBan) {
        getEm().remove(getEm().merge(ctPhieuGiaoBan));
    }

    public CtPhieuGiaoBan find(Object id) {
        return getEm().find(CtPhieuGiaoBan.class, id);
    }

    public List<CtPhieuGiaoBan> findAll() {
        return getEm().createQuery("select object(o) from CtPhieuGiaoBan as o").getResultList();
    }

    public List<CtPhieuGiaoBan> findByPhieuGiaoBan(String maPhieu) {
        Query q;
        q = getEm().createQuery("SELECT c FROM CtPhieuGiaoBan c WHERE  c.ctpgbPgbMa.pgbMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public void capNhatPhieuGiaoBanChiTiet(List<CtPhieuGiaoBan> listPGB, PhieuGiaoBan objPhieuGiaoBan) {
        try {

            String listpgbMaSo = "";

            for (int i = 0; i < listPGB.size(); i++) {
                listPGB.get(i).setCtpgbPgbMa(objPhieuGiaoBan);
            }

            for (int i = 0; i < listPGB.size(); i++) {

                CtPhieuGiaoBan pgbct = listPGB.get(i);
                if (pgbct.getCtpgbMaso() != null) {

                    if (listpgbMaSo.equals("")) {
                        listpgbMaSo += pgbct.getCtpgbMaso();
                    } else {
                        listpgbMaSo += "," + pgbct.getCtpgbMaso();
                    }
                }
            }

            //System.out.println("*****listpbaMaSo: "+listpgbMaSo);
            //System.out.println("*****Ma Phieu GB: "+objPhieuGiaoBan.getPgbMa());

            this.removeItem(listpgbMaSo, objPhieuGiaoBan.getPgbMa());

            for (CtPhieuGiaoBan pba : listPGB) {

                if (pba.getCtpgbMaso() != null) {
                    getEm().merge(pba);
                    System.out.println("update thanh cong Chi Tiet Phieu Giao Ban");
                } else {
                    getEm().persist(pba);
                    System.out.println("insert them thanh cong  Chi Tiet Phieu Giao Ban");

                }

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
        }
    }

    public void removeItem(String listpbaMaSo, String maPhieuGB) {
        Query q;
        try {
            if (listpbaMaSo == null || listpbaMaSo.equals("")) {
                q = getEm().createQuery("SELECT c FROM CtPhieuGiaoBan c WHERE  c.ctpgbPgbMa.pgbMa like :maPhieuGB");
            } else {
                q = getEm().createQuery("SELECT c FROM CtPhieuGiaoBan c WHERE  c.ctpgbPgbMa.pgbMa like :maPhieuGB AND  c.ctpgbMaso NOT IN (" + listpbaMaSo + ")  ");
            }
            q.setParameter("maPhieuGB", maPhieuGB);

            List<CtPhieuGiaoBan> listPBA = q.getResultList();
            
            for (CtPhieuGiaoBan pba : listPBA) {
                getEm().remove(pba);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
        }
    }


    public void removeAllCtPhieuGiaoBan(PhieuGiaoBan objPhieuGiaoBan) {
        Query q=null;
        String sMaPhieuGiaoBan="";
        int numRecordDeleted=0;
        try {
            if (objPhieuGiaoBan != null) {
                sMaPhieuGiaoBan=objPhieuGiaoBan.getPgbMa();
                q = getEm().createQuery("DELETE FROM CtPhieuGiaoBan WHERE  ctpgbPgbMa.pgbMa = :maPhieuGB");
            } 
            q.setParameter("maPhieuGB", sMaPhieuGiaoBan);

            numRecordDeleted=q.executeUpdate();
            System.out.println("Number of records Chi Tiet Phieu Giao Ban were deleted: "+numRecordDeleted);
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
