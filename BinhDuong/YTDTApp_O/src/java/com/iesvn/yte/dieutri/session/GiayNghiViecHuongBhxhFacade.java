/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.jpql.criterion.JpqlString;
import com.iesvn.yte.dieutri.entity.GiayNghiViecHuongBhxh;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author halylam
 */
@Stateless
public class GiayNghiViecHuongBhxhFacade implements GiayNghiViecHuongBhxhFacadeLocal, GiayNghiViecHuongBhxhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
        em.persist(giayNghiViecHuongBhxh);
    }

    public void edit(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
        em.merge(giayNghiViecHuongBhxh);
    }

    public void remove(GiayNghiViecHuongBhxh giayNghiViecHuongBhxh) {
        em.remove(em.merge(giayNghiViecHuongBhxh));
    }

    public GiayNghiViecHuongBhxh find(Object id) {
        return em.find(GiayNghiViecHuongBhxh.class, id);
    }

    public List<GiayNghiViecHuongBhxh> findAll() {
        return em.createQuery("select object(o) from GiayNghiViecHuongBhxh as o").getResultList();
    }

    public GiayNghiViecHuongBhxh findByThamKhamMa(Integer thamkhamma) {
        GiayNghiViecHuongBhxh result = null;
        JpqlString newsql = new JpqlString();
        newsql.setSelect("select object(o) from GiayNghiViecHuongBhxh as o");
        List paramValues = newsql.getParamValues();
        if (thamkhamma != null) {
            newsql.add(com.iesvn.jpql.criterion.util.Utils.addEqExpr(paramValues, thamkhamma, "o.thamkhamMa.thamkhamMa"));
        }
        String newString = newsql.toSqlString();
        Query qry = em.createQuery(newString);
        for (int i = 1; i <= paramValues.size(); i++) {
            qry.setParameter(i, paramValues.get(i - 1));
        }
        try {
           result = (GiayNghiViecHuongBhxh)qry.getResultList().get(0);
        } catch (Exception e) {}
        return result;
    }

}
