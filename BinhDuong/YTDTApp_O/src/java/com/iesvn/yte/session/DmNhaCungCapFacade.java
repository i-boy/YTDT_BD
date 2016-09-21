/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhaCungCap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmNhaCungCapFacade implements DmNhaCungCapFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNhaCungCap dmNhaCungCap) {
        em.persist(dmNhaCungCap);
    }

    public void edit(DmNhaCungCap dmNhaCungCap) {
        em.merge(dmNhaCungCap);
    }

    public void remove(DmNhaCungCap dmNhaCungCap) {
        em.remove(em.merge(dmNhaCungCap));
    }

    public DmNhaCungCap find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNhaCungCap.class, id);
    }

    public List<DmNhaCungCap> findAll() {
        return em.createQuery("select object(o) from DmNhaCungCap as o").getResultList();
    }

}


