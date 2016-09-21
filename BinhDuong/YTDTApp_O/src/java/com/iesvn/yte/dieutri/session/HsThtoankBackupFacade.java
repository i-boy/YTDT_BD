/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class HsThtoankBackupFacade implements HsThtoankBackupFacadeLocal, HsThtoankBackupFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(HsThtoankBackup hsThtoankBackup) {
        if (hsThtoankBackup.getHsthtoankMa() == null || hsThtoankBackup.getHsthtoankMa().equals("")) {
            hsThtoankBackup.setHsthtoankMa(Utils.maPhieuThanhToan(em));
        }
        em.persist(hsThtoankBackup);
    }

    public void edit(HsThtoankBackup hsThtoankBackup) {
        em.merge(hsThtoankBackup);
    }

    public void remove(HsThtoankBackup hsThtoankBackup) {
        em.remove(em.merge(hsThtoankBackup));
    }

    public HsThtoankBackup find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsThtoankBackup.class, id);
    }

    public List<HsThtoankBackup> findAll() {
        return em.createQuery("select object(o) from HsThtoankBackup as o").getResultList();
    }

    public HsThtoankBackup findByMaPhieu(String maPhieuTT) {
        maPhieuTT = Utils.formatMaPhieu(maPhieuTT);
        HsThtoankBackup hsBK = null;
        Query q = em.createQuery("select object(o) from HsThtoankBackup as o Where o.hsthtoankMa = :maPhieuTT");
        q.setParameter("maPhieuTT", maPhieuTT);
        try {
            List<HsThtoankBackup> listHsBK = q.getResultList();
            if (listHsBK != null && listHsBK.size() > 0) {
                hsBK = listHsBK.get(listHsBK.size() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsBK;
    }

    public HsThtoankBackup findBytiepdonMa(String tiepdonMa, int recordReturn) {
        // recordReturn == 0 : Return the first record
        // recordReturn == 1 : Return the last record
        tiepdonMa = Utils.formatMa(em, tiepdonMa);
        HsThtoankBackup hsBK = null;
        Query q = em.createQuery("select object(o) from HsThtoankBackup as o Where o.tiepdonMa.tiepdonMa = :tiepdonMa Order By o.hsthtoankMa ASC");
        q.setParameter("tiepdonMa", tiepdonMa);
        try {
            List<HsThtoankBackup> listHsBK = q.getResultList();
            if (listHsBK != null && listHsBK.size() > 0) {
                if (recordReturn == 0) {
                    hsBK = listHsBK.get(0);
                } else {
                    hsBK = listHsBK.get(listHsBK.size() - 1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hsBK;
    }
     public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
