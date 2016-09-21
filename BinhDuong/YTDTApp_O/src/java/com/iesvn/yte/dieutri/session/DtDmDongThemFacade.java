/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmDongThem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmDongThemFacade implements DtDmDongThemFacadeLocal, DtDmDongThemFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmDongThem dtDmDongThem) {
        em.persist(dtDmDongThem);
    }

    public void edit(DtDmDongThem dtDmDongThem) {
        em.merge(dtDmDongThem);
    }

    public void remove(DtDmDongThem dtDmDongThem) {
        em.remove(em.merge(dtDmDongThem));
    }

    public DtDmDongThem find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmDongThem.class, id);
    }

    public List<DtDmDongThem> findAll() {
        return em.createQuery("select object(o) from DtDmDongThem as o").getResultList();
    }

}
