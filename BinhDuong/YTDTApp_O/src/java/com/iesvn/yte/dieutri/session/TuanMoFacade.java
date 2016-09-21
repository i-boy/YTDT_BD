/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.TuanMo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class TuanMoFacade implements TuanMoFacadeLocal, TuanMoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(TuanMo tuanMo) {
        em.persist(tuanMo);
    }

    public void edit(TuanMo tuanMo) {
        em.merge(tuanMo);
    }

    public void remove(TuanMo tuanMo) {
        em.remove(em.merge(tuanMo));
    }

    public TuanMo find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.TuanMo.class, id);
    }

    public List<TuanMo> findAll() {
        return em.createQuery("select object(o) from TuanMo as o").getResultList();
    }

}


