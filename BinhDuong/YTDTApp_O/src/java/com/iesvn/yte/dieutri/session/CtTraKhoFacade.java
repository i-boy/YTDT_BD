/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
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
public class CtTraKhoFacade implements CtTraKhoFacadeLocal, CtTraKhoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(CtTraKho ctTraKho) {
        getEm().persist(ctTraKho);
    }

    public void edit(CtTraKho ctTraKho) {
        getEm().merge(ctTraKho);
    }

    public void remove(CtTraKho ctTraKho) {
        getEm().remove(getEm().merge(ctTraKho));
    }

    public CtTraKho find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.CtTraKho.class, id);
    }

    public List<CtTraKho> findAll() {
        return getEm().createQuery("select object(o) from CtTraKho as o").getResultList();
    }

    public List<CtTraKho> findByphieutrakhoMa(String phieutraMa) {
        Query q = getEm().createQuery("SELECT c FROM CtTraKho c WHERE c.phieutrakhoMa.phieutrakhoMa = :phieutraMa order by c.dmthuocMaso.dmthuocTen");
        q.setParameter("phieutraMa", phieutraMa);
        return q.getResultList();

    }

    public void updateCtTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho) {
        String sListIdDetail = "";

        try {
            for (int i = 0; i < listCtTraKho.size(); i++) {
                listCtTraKho.get(i).setPhieutrakhoMa(objPhieuTraKho);
            }

            for (int i = 0; i < listCtTraKho.size(); i++) {
                CtTraKho obj = listCtTraKho.get(i);
                if (obj != null) {
                    if (sListIdDetail.equals("")) {
                        sListIdDetail += obj.getCttrakhoMa();
                    } else {
                        sListIdDetail += "," + obj.getCttrakhoMa();
                    }
                }
            }
            this.removeItem(sListIdDetail, objPhieuTraKho.getPhieutrakhoMa());
            
            for (CtTraKho obj : listCtTraKho) {
                if (obj.getCttrakhoMa() != null) {
                    getEm().merge(obj);
                    System.out.println("UPDATE SUCCESS CtTraKho");
                } else {
                    getEm().persist(obj);
                    System.out.println("UPDATE SUCCESS CtTraKho");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            getContext().setRollbackOnly();
        }
    }

    private void removeItem(String listIdMaSo, String maPhieuTraKho) {
        System.out.println("***** CtTraKho listIdMaSo: " + listIdMaSo);
        try {
            Query q;
            int numRecordDeleted = 0;
            if (listIdMaSo == null || listIdMaSo.equals("")) {
                q = getEm().createQuery("DELETE FROM CtTraKho c WHERE  c.phieutrakhoMa.phieutrakhoMa = :maPhieuTraKho");
            } else {
                q = getEm().createQuery("DELETE FROM CtTraKho c WHERE  c.phieutrakhoMa.phieutrakhoMa = :maPhieuTraKho AND  c.cttrakhoMa NOT IN (" + listIdMaSo + ")  ");
                System.out.println("***** CtTraKho WERE DELETING: " + listIdMaSo);
            }
            q.setParameter("maPhieuTraKho", maPhieuTraKho);
            numRecordDeleted = q.executeUpdate();
            System.out.println("***** CtTraKho NUMBER RECORDS WERE DELETED: " + numRecordDeleted);
        } catch (Exception e) {
            System.out.println(e.toString());
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


