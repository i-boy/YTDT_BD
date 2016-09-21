/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmDoiTuongAnFacade implements DtDmDoiTuongAnFacadeLocal, DtDmDoiTuongAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmDoiTuongAn dtDmDoiTuongAn) {
        em.persist(dtDmDoiTuongAn);
    }

    public void edit(DtDmDoiTuongAn dtDmDoiTuongAn) {
        em.merge(dtDmDoiTuongAn);
    }

    public void remove(DtDmDoiTuongAn dtDmDoiTuongAn) {
        em.remove(em.merge(dtDmDoiTuongAn));
    }

    public DtDmDoiTuongAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn.class, id);
    }

    public List<DtDmDoiTuongAn> findAll() {
        return em.createQuery("select object(o) from DtDmDoiTuongAn as o").getResultList();
    }

}
