/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.LichMo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class LichMoFacade implements LichMoFacadeLocal, LichMoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(LichMo lichMo) {
        em.persist(lichMo);
    }

    public void edit(LichMo lichMo) {
        em.merge(lichMo);
    }

    public void remove(LichMo lichMo) {
        em.remove(em.merge(lichMo));
    }

    public LichMo find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.LichMo.class, id);
    }

    public List<LichMo> findAll() {
        return em.createQuery("select object(o) from LichMo as o").getResultList();
    }

}


