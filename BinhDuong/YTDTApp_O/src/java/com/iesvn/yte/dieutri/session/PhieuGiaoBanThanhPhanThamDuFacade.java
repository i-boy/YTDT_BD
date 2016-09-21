/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
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
public class PhieuGiaoBanThanhPhanThamDuFacade implements PhieuGiaoBanThanhPhanThamDuFacadeLocal, PhieuGiaoBanThanhPhanThamDuFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu) {
        getEm().persist(phieuGiaoBanThanhPhanThamDu);
    }

    public void edit(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu) {
        getEm().merge(phieuGiaoBanThanhPhanThamDu);
    }

    public void remove(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu) {
        getEm().remove(getEm().merge(phieuGiaoBanThanhPhanThamDu));
    }

    public PhieuGiaoBanThanhPhanThamDu find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu.class, id);
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findAll() {
        return getEm().createQuery("select object(o) from PhieuGiaoBanThanhPhanThamDu as o").getResultList();
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByPhieuGiaoBan(String maPhieu) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuGiaoBanThanhPhanThamDu c WHERE  c.pgbMa.pgbMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByThamDu(String maPhieu) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuGiaoBanThanhPhanThamDu c WHERE  c.pgbMa.pgbMa like :maPhieu AND c.pgbtptdVaitro = true");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<PhieuGiaoBanThanhPhanThamDu> findByVangMat(String maPhieu) {
        Query q;
        q = em.createQuery("SELECT c FROM PhieuGiaoBanThanhPhanThamDu c WHERE  c.pgbMa.pgbMa like :maPhieu AND c.pgbtptdVaitro = false");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public void capNhatPhieuGiaoBanTPTD(List<PhieuGiaoBanThanhPhanThamDu> listTPTD, PhieuGiaoBan objPhieuGiaoBan) {
        try {

            String listpbaMaSo = "";

            for (int i = 0; i < listTPTD.size(); i++) {
                listTPTD.get(i).setPgbMa(objPhieuGiaoBan);
            }

            for (int i = 0; i < listTPTD.size(); i++) {

                PhieuGiaoBanThanhPhanThamDu pba = listTPTD.get(i);
                if (pba.getPgbtptdMaso() != null) {

                    if (listpbaMaSo.equals("")) {
                        listpbaMaSo += pba.getPgbtptdMaso();
                    } else {
                        listpbaMaSo += "," + pba.getPgbtptdMaso();
                    }
                }

            }

            //System.out.println("*****listpbaMaSo: "+listpbaMaSo);
            //System.out.println("*****Ma Phieu GB: "+objPhieuGiaoBan.getPgbMa());

            this.removeItem(listpbaMaSo, objPhieuGiaoBan.getPgbMa());

            for (PhieuGiaoBanThanhPhanThamDu pba : listTPTD) {

                if (pba.getPgbtptdMaso() != null) {
                    getEm().merge(pba);
                    System.out.println("update thanh cong Thanh Phan Tham Du");
                } else {
                    getEm().persist(pba);
                    System.out.println("insert them thanh cong  Thanh Phan Tham Du");

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

        if (listpbaMaSo == null || listpbaMaSo.equals("")) {
            q = getEm().createQuery("SELECT c FROM PhieuGiaoBanThanhPhanThamDu c WHERE  c.pgbMa.pgbMa like :maPhieuGB");
        } else {
            q = getEm().createQuery("SELECT c FROM PhieuGiaoBanThanhPhanThamDu c WHERE  c.pgbMa.pgbMa like :maPhieuGB AND  c.pgbtptdMaso NOT IN (" + listpbaMaSo + ")  ");
        }
        q.setParameter("maPhieuGB", maPhieuGB);

        List<PhieuGiaoBanThanhPhanThamDu> listPBA = q.getResultList();

        System.out.println("listPBA:" + listPBA);

        for (PhieuGiaoBanThanhPhanThamDu pba : listPBA) {
            getEm().remove(pba);
        }
    }

    public void removeAllTPTD(PhieuGiaoBan objPhieuGiaoBan) {
        Query q = null;
        String sMaPhieuGB = "";
        int numRecordDeleted = 0;
        try {
            if (objPhieuGiaoBan != null) {
                q = getEm().createQuery("DELETE FROM PhieuGiaoBanThanhPhanThamDu WHERE  pgbMa.pgbMa = :maPhieuGB");
                sMaPhieuGB = objPhieuGiaoBan.getPgbMa();
            }
            q.setParameter("maPhieuGB", sMaPhieuGB);
            numRecordDeleted = q.executeUpdate();
            System.out.println("Number of records Thanh Phan Tham Du were deleted: " + numRecordDeleted);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
        }

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
