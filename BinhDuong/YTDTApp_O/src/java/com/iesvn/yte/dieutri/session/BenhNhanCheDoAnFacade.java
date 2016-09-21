/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HP
 */
@Stateless
public class BenhNhanCheDoAnFacade implements BenhNhanCheDoAnFacadeLocal, BenhNhanCheDoAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BenhNhanCheDoAn benhNhanCheDoAn) {
        em.persist(benhNhanCheDoAn);
    }

    public void edit(BenhNhanCheDoAn benhNhanCheDoAn) {
        em.merge(benhNhanCheDoAn);
    }

    public void remove(BenhNhanCheDoAn benhNhanCheDoAn) {
        em.remove(em.merge(benhNhanCheDoAn));
    }

    public BenhNhanCheDoAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn.class, id);
    }

    public List<BenhNhanCheDoAn> findAll() {
        return em.createQuery("select object(o) from BenhNhanCheDoAn as o").getResultList();
    }
    
    public List<BenhNhanCheDoAn> findByBnPbaMaso(Integer bnpbaMaso) {        
        Query q = em.createQuery("select object(o) from BenhNhanCheDoAn as o where o.bnpbaMaso.bnpbaMaso = :bnpbaMaso ");        
        q.setParameter("bnpbaMaso", bnpbaMaso);
        return q.getResultList();
    }

}
