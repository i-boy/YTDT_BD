/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.iesvn.yte.dieutri.utils.Utils;

/**
 *
 * @author ies
 */
@Stateless
public class ChuyenVaoNoiTruFacade implements ChuyenVaoNoiTruFacadeLocal, ChuyenVaoNoiTruFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        em.persist(chuyenVaoNoiTru);
    }

    public void edit(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        em.merge(chuyenVaoNoiTru);
    }

    public void remove(ChuyenVaoNoiTru chuyenVaoNoiTru) {
        em.remove(em.merge(chuyenVaoNoiTru));
    }

    public ChuyenVaoNoiTru find(Object id) {
        return em.find(ChuyenVaoNoiTru.class, id);
    }

    public ChuyenVaoNoiTru findByMaTiepDon(String maTiepDon) {

        maTiepDon = Utils.formatMa(getEm(), maTiepDon);

        Query q = em.createQuery("select object(o) from ChuyenVaoNoiTru as o where o.cvntTIEPDONMA like :tiepdonMa");
        q.setParameter("tiepdonMa", maTiepDon);

        List<ChuyenVaoNoiTru> listCvnt = q.getResultList();

        if (listCvnt != null && listCvnt.size() > 0) {
            return listCvnt.get(0);
        } else {
            return null;
        }
    }

    public List<ChuyenVaoNoiTru> findAll() {
        return em.createQuery("select object(o) from ChuyenVaoNoiTru as o").getResultList();
    }

    public List<ChuyenVaoNoiTru> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from ChuyenVaoNoiTru as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from ChuyenVaoNoiTru as o").getSingleResult()).intValue();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public ChuyenVaoNoiTru findBySoVaoVien(String sovaovien) {
        
        sovaovien = Utils.formatMa(em, sovaovien);

        Query q = em.createQuery("select object(o) from ChuyenVaoNoiTru as o where o.cvntSOVAOVIEN.hsbaSovaovien like :sovaovien");
        q.setParameter("sovaovien", sovaovien);

        List<ChuyenVaoNoiTru> listCvnt = q.getResultList();

        if (listCvnt != null && listCvnt.size() > 0) {
            return listCvnt.get(0);
        } else {
            return null;
        }
    }
}
