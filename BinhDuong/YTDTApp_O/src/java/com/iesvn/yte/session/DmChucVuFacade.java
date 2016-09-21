/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmChucVu;
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
public class DmChucVuFacade implements DmChucVuFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmChucVu dmChucVu) {
        em.persist(dmChucVu);
    }

    public void edit(DmChucVu dmChucVu) {
        em.merge(dmChucVu);
    }

    public void remove(DmChucVu dmChucVu) {
        em.remove(em.merge(dmChucVu));
    }

    public DmChucVu find(Object id) {
        return em.find(com.iesvn.yte.entity.DmChucVu.class, id);
    }

    public List<DmChucVu> findAll() {
        return em.createQuery("select object(o) from DmChucVu as o").getResultList();
    }
    
}


