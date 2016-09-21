/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoiDan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
public class DtDmLoiDanFacade implements DtDmLoiDanFacadeLocal, DtDmLoiDanFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoiDan dtDmLoiDan) {
        em.persist(dtDmLoiDan);
    }

    public void edit(DtDmLoiDan dtDmLoiDan) {
        em.merge(dtDmLoiDan);
    }

    public void remove(DtDmLoiDan dtDmLoiDan) {
        em.remove(em.merge(dtDmLoiDan));
    }

    public DtDmLoiDan find(Object id) {
        return em.find(DtDmLoiDan.class, id);
    }

    public List<DtDmLoiDan> findAll() {
        return em.createQuery("select object(o) from DtDmLoiDan as o").getResultList();
    }

}
