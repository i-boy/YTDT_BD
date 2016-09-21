/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPhongMo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmPhongMoFacade implements DtDmPhongMoFacadeLocal, DtDmPhongMoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPhongMo dtDmPhongMo) {
        em.persist(dtDmPhongMo);
    }

    public void edit(DtDmPhongMo dtDmPhongMo) {
        em.merge(dtDmPhongMo);
    }

    public void remove(DtDmPhongMo dtDmPhongMo) {
        em.remove(em.merge(dtDmPhongMo));
    }

    public DtDmPhongMo find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPhongMo.class, id);
    }

    public List<DtDmPhongMo> findAll() {
        return em.createQuery("select object(o) from DtDmPhongMo as o").getResultList();
    }

}


