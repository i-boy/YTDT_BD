/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.jpql.criterion.JpqlString;
import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathaiCt;
import java.util.ArrayList;
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
public class HsbaChiTietNaophathaiCtFacade implements HsbaChiTietNaophathaiCtFacadeLocal, HsbaChiTietNaophathaiCtFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public HsbaChiTietNaophathaiCt create(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        em.persist(hsbaChiTietNaophathaiCt);
        return hsbaChiTietNaophathaiCt;
    }

    public void edit(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        em.merge(hsbaChiTietNaophathaiCt);
    }

    public void remove(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt) {
        em.remove(em.merge(hsbaChiTietNaophathaiCt));
    }

    public HsbaChiTietNaophathaiCt find(Object id) {
        return em.find(HsbaChiTietNaophathaiCt.class, id);
    }

    public List<HsbaChiTietNaophathaiCt> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNaophathaiCt as o").getResultList();
    }

    public List<HsbaChiTietNaophathaiCt> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietNaophathaiCt as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietNaophathaiCt as o").getSingleResult()).intValue();
    }

    public List<HsbaChiTietNaophathaiCt> findByHsbaChiTietNaophathaiMaso(Integer hsbaChiTietNaophathaiMa){
        List<HsbaChiTietNaophathaiCt> result = new ArrayList<HsbaChiTietNaophathaiCt>();
        JpqlString newsql = new JpqlString();
        newsql.setSelect("select object(o) from HsbaChiTietNaophathaiCt as o");
        List paramValues = newsql.getParamValues();
        if (hsbaChiTietNaophathaiMa != null && hsbaChiTietNaophathaiMa.intValue() > 0) {
            newsql.add(com.iesvn.jpql.criterion.util.Utils.addEqExpr(paramValues, hsbaChiTietNaophathaiMa, "o.hsbactnaophathaiMa.hsbactnaophathaiMa"));
        }
        String newString = newsql.toSqlString();
        Query qry = em.createQuery(newString);
        for (int i = 1; i <= paramValues.size(); i++) {
            qry.setParameter(i, paramValues.get(i - 1));
        }
        try {
           result = qry.getResultList();
        } catch (Exception e) {}
        return result;
    }


}
