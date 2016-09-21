/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmChiDan;
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
public class DtDmChiDanFacade implements DtDmChiDanFacadeLocal, DtDmChiDanFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmChiDan dtDmChiDan) {
        em.persist(dtDmChiDan);
    }

    public void edit(DtDmChiDan dtDmChiDan) {
        em.merge(dtDmChiDan);
    }

    public void remove(DtDmChiDan dtDmChiDan) {
        em.remove(em.merge(dtDmChiDan));
    }

    public DtDmChiDan find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmChiDan.class, id);
    }

    public DtDmChiDan findByMa(String maChiDan) {
        maChiDan = maChiDan.toUpperCase();
        try {
            Query query = em.createQuery("select object(o) from DtDmChiDan  as o where o.dtdmchidanMa like :maChiDan");
            query.setParameter("maChiDan", maChiDan);
            List<DtDmChiDan> list = query.getResultList();
            if (list != null && list.size()>0) {
                return (DtDmChiDan) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<DtDmChiDan> findAll() {
        return em.createQuery("select object(o) from DtDmChiDan as o").getResultList();
    }

}


