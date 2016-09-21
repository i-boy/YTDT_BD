/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPhong;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
public class DtDmPhongFacade implements DtDmPhongFacadeLocal, DtDmPhongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPhong dtDmPhong) {
        em.persist(dtDmPhong);
    }

    public void edit(DtDmPhong dtDmPhong) {
        em.merge(dtDmPhong);
    }

    public void remove(DtDmPhong dtDmPhong) {
        em.remove(em.merge(dtDmPhong));
    }

    public DtDmPhong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPhong.class, id);
    }

    public List<DtDmPhong> findAll() {
        return em.createQuery("select object(o) from DtDmPhong as o").getResultList();
    }

}
