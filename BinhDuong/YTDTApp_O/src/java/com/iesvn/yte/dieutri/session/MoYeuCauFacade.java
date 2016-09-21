/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.MoYeuCau;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class MoYeuCauFacade implements MoYeuCauFacadeLocal, MoYeuCauFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(MoYeuCau moYeuCau) {
        em.persist(moYeuCau);
    }

    public void edit(MoYeuCau moYeuCau) {
        em.merge(moYeuCau);
    }

    public void remove(MoYeuCau moYeuCau) {
        em.remove(em.merge(moYeuCau));
    }

    public MoYeuCau find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.MoYeuCau.class, id);
    }

    public List<MoYeuCau> findAll() {
        return em.createQuery("select object(o) from MoYeuCau as o").getResultList();
    }

}


