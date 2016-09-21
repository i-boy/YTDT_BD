/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmNguonKinhPhiFacade implements DmNguonKinhPhiFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNguonKinhPhi dmNguonKinhPhi) {
        em.persist(dmNguonKinhPhi);
    }

    public void edit(DmNguonKinhPhi dmNguonKinhPhi) {
        em.merge(dmNguonKinhPhi);
    }

    public void remove(DmNguonKinhPhi dmNguonKinhPhi) {
        em.remove(em.merge(dmNguonKinhPhi));
    }

    public DmNguonKinhPhi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNguonKinhPhi.class, id);
    }

    public List<DmNguonKinhPhi> findAll() {
        return em.createQuery("select object(o) from DmNguonKinhPhi as o").getResultList();
    }

}


