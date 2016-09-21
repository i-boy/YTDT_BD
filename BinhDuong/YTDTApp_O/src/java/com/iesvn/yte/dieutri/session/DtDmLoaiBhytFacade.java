/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmLoaiBhytFacade implements DtDmLoaiBhytFacadeLocal, DtDmLoaiBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiBhyt dtDmLoaiBhyt) {
        em.persist(dtDmLoaiBhyt);
    }

    public void edit(DtDmLoaiBhyt dtDmLoaiBhyt) {
        em.merge(dtDmLoaiBhyt);
    }

    public void remove(DtDmLoaiBhyt dtDmLoaiBhyt) {
        em.remove(em.merge(dtDmLoaiBhyt));
    }

    public DtDmLoaiBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiBhyt.class, id);
    }

    public List<DtDmLoaiBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiBhyt as o").getResultList();
    }

}


