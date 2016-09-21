/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
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
public class CtPhieuKhamDtNgoaiTruFacade implements CtPhieuKhamDtNgoaiTruFacadeLocal, CtPhieuKhamDtNgoaiTruFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru) {
        getEm().persist(ctPhieuKhamDtNgoaiTru);
    }

    public void edit(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru) {
        getEm().merge(ctPhieuKhamDtNgoaiTru);
    }

    public void remove(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru) {
        getEm().remove(getEm().merge(ctPhieuKhamDtNgoaiTru));
    }

    public CtPhieuKhamDtNgoaiTru find(Object id) {
        return getEm().find(CtPhieuKhamDtNgoaiTru.class, id);
    }

    public List<CtPhieuKhamDtNgoaiTru> findAll() {
        return getEm().createQuery("select object(o) from CtPhieuKhamDtNgoaiTru as o").getResultList();
    }

    public List<CtPhieuKhamDtNgoaiTru> findByPKDTNTMa(String pkdtntMa) {
        List<CtPhieuKhamDtNgoaiTru> result = new ArrayList<CtPhieuKhamDtNgoaiTru>();
        com.iesvn.jpql.criterion.JpqlString newsql = new com.iesvn.jpql.criterion.JpqlString();
        newsql.setSelect("select object(o) from CtPhieuKhamDtNgoaiTru as o");
        List paramValues = newsql.getParamValues();
        if (pkdtntMa != null && !pkdtntMa.equals("")) {
            newsql.add(com.iesvn.jpql.criterion.util.Utils.addEqExpr(paramValues, pkdtntMa, "o.phieuKcbNgoaiTru.pkdtntMa"));
        }
        String newString = newsql.toSqlString();
        Query qry = getEm().createQuery(newString);
        for (int i = 1; i <= paramValues.size(); i++) {
            qry.setParameter(i, paramValues.get(i - 1));
        }
        try {
            result = qry.getResultList();
        } catch (Exception e) {
        }
        return result;
    }

    public void removeAllCtPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru objPhieuKhamDtnt) {
        Query q = null;
        String sMaPhieu = "";
        int numRecordDeleted = 0;
        try {
            if (objPhieuKhamDtnt != null)
            {
                sMaPhieu = objPhieuKhamDtnt.getPkdtntMa();
                q = getEm().createQuery("DELETE FROM CtPhieuKhamDtNgoaiTru c WHERE  c.phieuKcbNgoaiTru.pkdtntMa = :maPhieu");
                q.setParameter("maPhieu", sMaPhieu);
                numRecordDeleted = q.executeUpdate();
                System.out.println("Number of records Chi Tiet PhieuKhamDtNgoaiTru were deleted: " + numRecordDeleted);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
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

    

}
