/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiThietBi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author root
 */
@Stateless
public class DmLoaiThietBiFacade implements DmLoaiThietBiFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiThietBi dmLoaiThietBi) {
        em.persist(dmLoaiThietBi);
    }

    public void edit(DmLoaiThietBi dmLoaiThietBi) {
        em.merge(dmLoaiThietBi);
    }

    public void remove(DmLoaiThietBi dmLoaiThietBi) {
        em.remove(em.merge(dmLoaiThietBi));
    }

    public DmLoaiThietBi find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiThietBi.class, id);
    }

    public List<DmLoaiThietBi> findAll() {
        return em.createQuery("select object(o) from DmLoaiThietBi as o").getResultList();
    }
    
}


