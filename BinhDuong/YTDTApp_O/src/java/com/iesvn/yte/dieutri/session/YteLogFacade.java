/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.YteLog;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author i-boy
 */
@Stateless
public class YteLogFacade implements YteLogFacadeLocal, YteLogFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private DieuTriUtilFacadeLocal dieutriFacade;
    @Resource
    private SessionContext ctx;

    public void create(YteLog yteLog) {
        em.persist(yteLog);

    }

    public void edit(YteLog yteLog) {
        em.merge(yteLog);
    }

    public void remove(YteLog yteLog) {
        em.remove(em.merge(yteLog));
    }

    public YteLog find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.YteLog.class, id);
    }

    public List<YteLog> findAll() {
        return em.createQuery("select object(o) from YteLog as o").getResultList();
    }

    public List<YteLog> findByCondition(String form, String userID, String objectId, String logString, String listData, Date tuNgay, Date denNgay) {
        StringBuffer sql = new StringBuffer("select * from yte_log y where 1 = 1 ");
        if (form != null && !"".equals(form)) {
            sql.append(" and y.form = :form");
        }
        if (userID != null && !"".equals(userID)) {
            sql.append(" and y.user_id = :userID");
        }
        if (objectId != null && !"".equals(objectId)) {
            sql.append(" and y.object_Id like  :objectId");
        }
        if (logString != null && !"".equals(logString)) {
            sql.append(" and y.log_String like :logString");
        }
        if (listData != null && !"".equals(listData)) {
            sql.append(" and y.list_Data like :listData");
        }

        if (tuNgay != null ) {
            sql.append(" and to_date(y.DATE_TIME) >= :tuNgay");
        }

        if (denNgay != null) {
            sql.append(" and to_date(y.DATE_TIME) <= :denNgay");
        }

        System.out.println("sql " + sql.toString());
        Query query = em.createNativeQuery(sql.toString(), YteLog.class);
        if (form != null && !"".equals(form)) {
            query.setParameter("form", form);
        }
        if (userID != null && !"".equals(userID)) {
            query.setParameter("userID", userID);
        }
        if (objectId != null && !"".equals(objectId)) {
            query.setParameter("objectId", "%"+objectId+"%");
        }
        if (logString != null && !"".equals(logString)) {
            query.setParameter("logString", "%"+logString+"%");
        }
        if (listData != null && !"".equals(listData)) {
            query.setParameter("listData", "%"+listData+"%");
        }

        if (tuNgay != null ) {
            query.setParameter("tuNgay", tuNgay);
        }

        if (denNgay != null) {
            query.setParameter("denNgay", denNgay);
        }

        return query.getResultList();
    }
}
