/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtToaThuock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class CtToaThuockFacade implements CtToaThuockFacadeLocal, CtToaThuockFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtToaThuock ctToaThuock) {
        em.persist(ctToaThuock);
    }

    public void edit(CtToaThuock ctToaThuock) {
        em.merge(ctToaThuock);
    }

    public void remove(CtToaThuock ctToaThuock) {
        em.remove(em.merge(ctToaThuock));
    }

    public CtToaThuock find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtToaThuock.class, id);
    }

    public List<CtToaThuock> findAll() {
        return em.createQuery("select object(o) from CtToaThuock as o").getResultList();
    }

}


