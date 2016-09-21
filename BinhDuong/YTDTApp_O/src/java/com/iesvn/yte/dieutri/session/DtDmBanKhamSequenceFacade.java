/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmBanKhamSequence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DtDmBanKhamSequenceFacade implements DtDmBanKhamSequenceFacadeLocal, DtDmBanKhamSequenceFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmBanKhamSequence dtDmBanKhamSequence) {
        em.persist(dtDmBanKhamSequence);
    }

    public void edit(DtDmBanKhamSequence dtDmBanKhamSequence) {
        em.merge(dtDmBanKhamSequence);
    }

    public void remove(DtDmBanKhamSequence dtDmBanKhamSequence) {
        em.remove(em.merge(dtDmBanKhamSequence));
    }

    public DtDmBanKhamSequence find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmBanKhamSequence.class, id);
    }

    public List<DtDmBanKhamSequence> findAll() {
        return em.createQuery("select object(o) from DtDmBanKhamSequence as o").getResultList();
    }

    public int reset() {
        return em.createQuery("Update DtDmBanKhamSequence t Set t.currentValue = 1, t.currentNext = 2").executeUpdate();
    }
}
