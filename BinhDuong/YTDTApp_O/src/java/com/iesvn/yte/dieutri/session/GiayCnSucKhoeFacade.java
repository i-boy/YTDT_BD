/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.GiayCnSucKhoe;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author James
 */
@Stateless
public class GiayCnSucKhoeFacade implements GiayCnSucKhoeFacadeLocal, GiayCnSucKhoeFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public void create(GiayCnSucKhoe giayCnSucKhoe) {
        getEm().persist(giayCnSucKhoe);
    }

    public void edit(GiayCnSucKhoe giayCnSucKhoe) {
        getEm().merge(giayCnSucKhoe);
    }

    public void remove(GiayCnSucKhoe giayCnSucKhoe) {
        getEm().remove(getEm().merge(giayCnSucKhoe));
    }

    public GiayCnSucKhoe find(Object id) {
        return getEm().find(GiayCnSucKhoe.class, id);
    }

    public List<GiayCnSucKhoe> findAll() {
        return getEm().createQuery("select object(o) from GiayCnSucKhoe as o").getResultList();
    }

    public List<GiayCnSucKhoe> findByGiayCnSucKhoe(String maPhieu) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM GiayCnSucKhoe c WHERE  c.gcnskMa like :maPhieu ");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public GiayCnSucKhoe findByMaThamKham(Integer iMaThamKham) {
        Query q;
        q = getEm().createQuery("SELECT object(c) FROM GiayCnSucKhoe c WHERE  c.gcnskThamkham.thamkhamMa = :maPhieu ");
        q.setParameter("maPhieu", iMaThamKham);
        GiayCnSucKhoe obj = null;
        List<GiayCnSucKhoe> list = q.getResultList();
        if (list != null && list.size() > 0) {
            obj = list.get(0);
        }
        return obj;
    }
    
    /*1-4-2010 Trung.th sua capNhatGiayCnSucKhoe() 2 paramater thanh 1
     * giaycnsuckhoe_ma se tu tang
     */
    public void capNhatGiayCnSucKhoe(GiayCnSucKhoe obj) {
        if (obj == null) {
            getEm().persist(obj);
            System.out.println("******* INSERT SUCCESS GiayCnSucKhoe ******");
        } else {
            getEm().merge(obj);
            System.out.println("******* UPDATE SUCCESS GiayCnSucKhoe ******");
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
