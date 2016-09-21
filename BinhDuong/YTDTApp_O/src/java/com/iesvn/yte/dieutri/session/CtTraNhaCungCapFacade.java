/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import com.iesvn.yte.dieutri.entity.PhieuTraNhaCungCap;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.Query;
/**
 *
 * @author thanh
 */
@Stateless
public class CtTraNhaCungCapFacade implements CtTraNhaCungCapFacadeLocal,CtTraNhaCungCapFacadeRemote{
    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(CtTraNhaCungCap ctTraNhaCungCap) {
        getEm().persist(ctTraNhaCungCap);
    }

    public void edit(CtTraNhaCungCap ctTraNhaCungCap) {
        getEm().merge(ctTraNhaCungCap);
    }

    public void remove(CtTraNhaCungCap ctTraNhaCungCap) {
        getEm().remove(getEm().merge(ctTraNhaCungCap));
    }

    public CtTraNhaCungCap find(Object id) {
        return getEm().find(CtTraNhaCungCap.class, id);
    }

    public List<CtTraNhaCungCap> findAll() {
        return getEm().createQuery("select object(o) from CtTraNhaCungCap as o").getResultList();
    }

    public List<CtTraNhaCungCap> findCtTraNhaCungCapByMaPhieu(String maPhieu) {
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM CtTraNhaCungCap as c WHERE  c.phieutranhacungcapMa.phieutranhacungcapMa = :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        List<CtTraNhaCungCap> list=q.getResultList();
        System.out.println("----- list.size: "+list.size());
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return new ArrayList<CtTraNhaCungCap>();
        }
    }

     public void updateCtTraNhaCungCap(PhieuTraNhaCungCap objPhieuTraNCC, List<CtTraNhaCungCap> listCtTraNCC) {
        String sListIdDetail = "";

        try {
            for (int i = 0; i < listCtTraNCC.size(); i++) {
                listCtTraNCC.get(i).setPhieutranhacungcapMa(objPhieuTraNCC);
            }

            for (int i = 0; i < listCtTraNCC.size(); i++) {
                CtTraNhaCungCap obj = listCtTraNCC.get(i);
                if (obj != null) {
                    if (sListIdDetail.equals("")) {
                        sListIdDetail += obj.getCttranhacungcapMa();
                    } else {
                        sListIdDetail += "," + obj.getCttranhacungcapMa();
                    }
                }
            }

            this.removeItem(sListIdDetail, objPhieuTraNCC.getPhieutranhacungcapMa());

            for (CtTraNhaCungCap obj : listCtTraNCC) {
                if (obj.getCttranhacungcapMa() != null) {
                    getEm().merge(obj);
                    System.out.println("UPDATE SUCCESS CtTraNhaCungCap");
                } else {
                    getEm().persist(obj);
                    System.out.println("UPDATE SUCCESS CtTraNhaCungCap");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            getContext().setRollbackOnly();
        }
    }

    private void removeItem(String listIdMaSo, String maPhieuTraNCC) {
        System.out.println("***** CtTraNhaCungCap listIdMaSo: " + listIdMaSo);
        try {
            Query q;
            int numRecordDeleted = 0;
            if (listIdMaSo == null || listIdMaSo.equals("")) {
                q = getEm().createQuery("DELETE FROM CtTraNhaCungCap c WHERE  c.phieutranhacungcapMa.phieutranhacungcapMa = :maPhieuTraNCC");
            } else {
                q = getEm().createQuery("DELETE FROM CtTraNhaCungCap c WHERE  c.phieutranhacungcapMa.phieutranhacungcapMa = :maPhieuTraNCC AND  c.cttranhacungcapMa NOT IN (" + listIdMaSo + ")  ");
                System.out.println("***** CtTraNhaCungCap WERE DELETING: " + listIdMaSo);
            }
            q.setParameter("maPhieuTraNCC", maPhieuTraNCC);
            numRecordDeleted = q.executeUpdate();
            System.out.println("***** CtTraNhaCungCap NUMBER RECORDS WERE DELETED: " + numRecordDeleted);
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
