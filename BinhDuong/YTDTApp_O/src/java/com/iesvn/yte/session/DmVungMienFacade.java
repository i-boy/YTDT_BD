/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmVungMien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmVungMienFacade implements DmVungMienFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmVungMien dmVungMien) {
        em.persist(dmVungMien);
    }

    public void edit(DmVungMien dmVungMien) {
        em.merge(dmVungMien);
    }

    public void remove(DmVungMien dmVungMien) {
        em.remove(em.merge(dmVungMien));
    }

    public DmVungMien find(Object id) {
        return em.find(com.iesvn.yte.entity.DmVungMien.class, id);
    }

    public List<DmVungMien> findAll() {
        return em.createQuery("select object(o) from DmVungMien as o").getResultList();
    }

}


