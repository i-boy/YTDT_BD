/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNgheNghiep;
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
public class DmNgheNghiepFacade implements DmNgheNghiepFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNgheNghiep dmNgheNghiep) {
        em.persist(dmNgheNghiep);
    }

    public void edit(DmNgheNghiep dmNgheNghiep) {
        em.merge(dmNgheNghiep);
    }

    public void remove(DmNgheNghiep dmNgheNghiep) {
        em.remove(em.merge(dmNgheNghiep));
    }

    public DmNgheNghiep find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNgheNghiep.class, id);
    }

    public List<DmNgheNghiep> findAll() {
        return em.createQuery("select object(o) from DmNgheNghiep as o").getResultList();
    }

}


