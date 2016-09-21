/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class CtToaLinhkFacade implements CtToaLinhkFacadeLocal, CtToaLinhkFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtToaLinhk ctToaLinhk) {
        em.persist(ctToaLinhk);
    }

    public void edit(CtToaLinhk ctToaLinhk) {
        em.merge(ctToaLinhk);
    }

    public void remove(CtToaLinhk ctToaLinhk) {
        em.remove(em.merge(ctToaLinhk));
    }

    public CtToaLinhk find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtToaLinhk.class, id);
    }

    public List<CtToaLinhk> findAll() {
        return em.createQuery("select object(o) from CtToaLinhk as o").getResultList();
    }
    
    public List<CtToaLinhk> findByTiepDonMa(String tiepdonMa) {
        Query q = em.createQuery("SELECT c FROM CtToaLinhk c WHERE c.toalinhkhamMa.tiepdonMa.tiepdonMa = :tiepdonMa");
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }
}


