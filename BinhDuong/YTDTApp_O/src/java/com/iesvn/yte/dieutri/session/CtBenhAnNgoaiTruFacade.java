/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.jpql.criterion.JpqlString;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
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
public class CtBenhAnNgoaiTruFacade implements CtBenhAnNgoaiTruFacadeLocal, CtBenhAnNgoaiTruFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtBenhAnNgoaiTru ctBenhAnNgoaiTru) {
        em.persist(ctBenhAnNgoaiTru);
    }

    public void edit(CtBenhAnNgoaiTru ctBenhAnNgoaiTru) {
        em.merge(ctBenhAnNgoaiTru);
    }

    public void remove(CtBenhAnNgoaiTru ctBenhAnNgoaiTru) {
        em.remove(em.merge(ctBenhAnNgoaiTru));
    }

    public CtBenhAnNgoaiTru find(Object id) {
        return em.find(CtBenhAnNgoaiTru.class, id);
    }

    public List<CtBenhAnNgoaiTru> findAll() {
        return em.createQuery("select object(o) from CtBenhAnNgoaiTru as o").getResultList();
    }
    
    public List<CtBenhAnNgoaiTru> findByBANTMa(String bantMa) {
        List<CtBenhAnNgoaiTru> result = new ArrayList<CtBenhAnNgoaiTru>();
        JpqlString newsql = new JpqlString();
        newsql.setSelect("select object(o) from CtBenhAnNgoaiTru as o");
        List paramValues = newsql.getParamValues();
        if (bantMa != null && !bantMa.equals("")) {
            newsql.add(com.iesvn.jpql.criterion.util.Utils.addEqExpr(paramValues, bantMa, "o.benhAnNgoaiTru.bantMa"));
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

        //Query q = em.createQuery("select object(o) from CtBenhAnNgoaiTru as o where o.benhAnNgoaiTru.bantMa = :bantMa order by o.ctbantNgay ");
        //q.setParameter("bantMa", bantMa);
        //return q.getResultList();

    }
}
