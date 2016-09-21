/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ToaThuocKham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class ToaThuocKhamFacade implements ToaThuocKhamFacadeLocal, ToaThuocKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(ToaThuocKham toaThuocKham) {
        em.persist(toaThuocKham);
    }

    public void edit(ToaThuocKham toaThuocKham) {
        em.merge(toaThuocKham);
    }

    public void remove(ToaThuocKham toaThuocKham) {
        em.remove(em.merge(toaThuocKham));
    }

    public ToaThuocKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ToaThuocKham.class, id);
    }

    public List<ToaThuocKham> findAll() {
        return em.createQuery("select object(o) from ToaThuocKham as o").getResultList();
    }

}


