/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.XetGiamKham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class XetGiamKhamFacade implements XetGiamKhamFacadeLocal, XetGiamKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(XetGiamKham xetGiamKham) {
        em.persist(xetGiamKham);
    }

    public void edit(XetGiamKham xetGiamKham) {
        em.merge(xetGiamKham);
    }

    public void remove(XetGiamKham xetGiamKham) {
        em.remove(em.merge(xetGiamKham));
    }

    public XetGiamKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.XetGiamKham.class, id);
    }

    public List<XetGiamKham> findAll() {
        return em.createQuery("select object(o) from XetGiamKham as o").getResultList();
    }

}


