/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
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
public class BenhNhanGioAnFacade implements BenhNhanGioAnFacadeLocal, BenhNhanGioAnFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(BenhNhanGioAn benhNhanGioAn) {
        em.persist(benhNhanGioAn);
    }

    public void edit(BenhNhanGioAn benhNhanGioAn) {
        em.merge(benhNhanGioAn);
    }

    public void remove(BenhNhanGioAn benhNhanGioAn) {
        em.remove(em.merge(benhNhanGioAn));
    }

    public BenhNhanGioAn find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.BenhNhanGioAn.class, id);
    }

    public List<BenhNhanGioAn> findAll() {
        return em.createQuery("select object(o) from BenhNhanGioAn as o").getResultList();
    }
    
    public List<BenhNhanGioAn> findByBnPbaMaso(Integer bnpbaMaso) {        
        Query q = em.createQuery("select object(o) from BenhNhanGioAn as o where o.bnpbaMaso.bnpbaMaso = :bnpbaMaso ");        
        q.setParameter("bnpbaMaso", bnpbaMaso);
        return q.getResultList();
    }
}
