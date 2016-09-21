/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
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
public class CtXuatBhFacade implements CtXuatBhFacadeLocal, CtXuatBhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(CtXuatBh ctXuatBh) {
        em.persist(ctXuatBh);
    }

    public void edit(CtXuatBh ctXuatBh) {
        em.merge(ctXuatBh);
    }

    public void remove(CtXuatBh ctXuatBh) {
        em.remove(em.merge(ctXuatBh));
    }

    public CtXuatBh find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtXuatBh.class, id);
    }

    public List<CtXuatBh> findAll() {
        return em.createQuery("select object(o) from CtXuatBh as o").getResultList();
    }
    public List<CtXuatBh> findByPhieuxuatBhMa(String phieuxuatBhMa) {
        Query q = em.createQuery("SELECT c FROM CtXuatBh c WHERE c.phieuxuatbhMa.phieuxuatbhMa = :phieuxuatbhMa");
        q.setParameter("phieuxuatbhMa", phieuxuatBhMa);
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


