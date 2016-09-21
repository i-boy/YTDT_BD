/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HoanThu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HoanThuFacade implements HoanThuFacadeLocal, HoanThuFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HoanThu hoanThu) {
        em.persist(hoanThu);
    }

    public void edit(HoanThu hoanThu) {
        em.merge(hoanThu);
    }

    public void remove(HoanThu hoanThu) {
        em.remove(em.merge(hoanThu));
    }

    public HoanThu find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HoanThu.class, id);
    }

    public List<HoanThu> findAll() {
        return em.createQuery("select object(o) from HoanThu as o").getResultList();
    }

}


