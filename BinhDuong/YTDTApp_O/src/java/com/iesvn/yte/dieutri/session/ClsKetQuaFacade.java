/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.ClsKetQua;
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
public class ClsKetQuaFacade implements ClsKetQuaFacadeRemote, ClsKetQuaFacadeLocal {

    @PersistenceContext
    private EntityManager em;

    public void create(ClsKetQua clsKetQua) {
        em.persist(clsKetQua);
    }

    public void edit(ClsKetQua clsKetQua) {
        em.merge(clsKetQua);
    }

    public void remove(ClsKetQua clsKetQua) {
        em.remove(em.merge(clsKetQua));
    }

    public ClsKetQua find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ClsKetQua.class, id);
    }

    public List<ClsKetQua> findAll() {
        return em.createQuery("select object(o) from ClsKetQua as o").getResultList();
    }
}
