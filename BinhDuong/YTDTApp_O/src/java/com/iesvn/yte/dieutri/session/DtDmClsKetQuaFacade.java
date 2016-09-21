/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Thanh
 */
@Stateless
public class DtDmClsKetQuaFacade implements DtDmClsKetQuaFacadeRemote, DtDmClsKetQuaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmClsKetQua dtDmClsKetQua) {
        em.persist(dtDmClsKetQua);
    }

    public void edit(DtDmClsKetQua dtDmClsKetQua) {
        em.merge(dtDmClsKetQua);
    }

    public void remove(DtDmClsKetQua dtDmClsKetQua) {
        em.remove(em.merge(dtDmClsKetQua));
    }

    public DtDmClsKetQua find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmClsKetQua.class, id);
    }

    public List<DtDmClsKetQua> findAll() {
        return em.createQuery("select object(o) from DtDmClsKetQua as o").getResultList();
    }
    
    public List<DtDmClsKetQua> findByAllConditions(String ma, String ten, Integer dtdmclsbgMaso) {
        String sql = "";
        sql = "Select object(o) from DtDmClsKetQua as o " + " where 1 = 1 ";
        if (ma.trim().length() > 0) {
            sql += "and o.dtdmclskqMa like ('%' || :ma || '%') ";
        }
        if (ten.trim().length() > 0) {
            sql += "and o.dtdmclskqTen like ('%' || :ten || '%') ";
        }
        if (dtdmclsbgMaso != null) {
            sql += "and o.dtdmclsbgMaso.dtdmclsbgMaso = :dtdmclsbgMaso ";
        }       
        System.out.println("findByAllConditions " + sql);
        System.out.println("ma " + ma);
        System.out.println("ten " + ten);
        System.out.println("dtdmclsbgMaso " + dtdmclsbgMaso);
        Query qrery = em.createQuery(sql);
        if (ma.trim().length() > 0) {
            qrery.setParameter("ma", ma);
        }
        if (ten.trim().length() > 0) {
            qrery.setParameter("ten", ten);
        }
        if (dtdmclsbgMaso != null) {
            qrery.setParameter("dtdmclsbgMaso", dtdmclsbgMaso);
        }
        return qrery.getResultList();
    }
 
}
