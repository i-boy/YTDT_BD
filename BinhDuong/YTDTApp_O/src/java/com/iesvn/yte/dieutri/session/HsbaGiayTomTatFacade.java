/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaGiayTomTat;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaGiayTomTatFacade implements HsbaGiayTomTatFacadeLocal, HsbaGiayTomTatFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public String create(HsbaGiayTomTat hsbaGiayTomTat) {
        String maGiay = Utils.maGiayTomTatHsba(em);
        System.out.println("ma giay: " + maGiay);
        hsbaGiayTomTat.setHsbagiaytomtatMa(maGiay);
        try {
            em.persist(hsbaGiayTomTat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maGiay;
    }

    public void edit(HsbaGiayTomTat hsbaGiayTomTat) {
        em.merge(hsbaGiayTomTat);
    }

    public void remove(HsbaGiayTomTat hsbaGiayTomTat) {
        em.remove(em.merge(hsbaGiayTomTat));
    }

    public HsbaGiayTomTat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaGiayTomTat.class, id);
    }

    public List<HsbaGiayTomTat> findAll() {
        return em.createQuery("select object(o) from HsbaGiayTomTat as o").getResultList();
    }

    public HsbaGiayTomTat findByMa(String maGiay) {
        HsbaGiayTomTat hsbaTt = null;
        maGiay = Utils.formatMaPhieu(maGiay);
        Query q = em.createQuery("Select h from HsbaGiayTomTat h Where h.hsbagiaytomtatMa = :maGiay");
        q.setParameter("maGiay", maGiay);
        try {
            List<HsbaGiayTomTat> listHsba = q.getResultList();
            if (listHsba != null) {
                if (listHsba.size() > 0) {
                    hsbaTt = listHsba.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hsbaTt;
    }

    public HsbaGiayTomTat findBySovaovien(String sovaovien) {
        sovaovien = Utils.formatMa(em, sovaovien);
Query q = em.createQuery("Select h from HsbaGiayTomTat h Where h.hsbaSovaovien.hsbaSovaovien = :sovaovien order by h.hsbagttNgaygiocn DESC");
        q.setParameter("sovaovien", sovaovien);
        try {
            List<HsbaGiayTomTat> listHsba = q.getResultList();
            if (listHsba != null) {
                if (listHsba.size() > 0) {
                    return listHsba.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}


