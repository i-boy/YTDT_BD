/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDiaDiem;
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
public class DmDiaDiemFacade implements DmDiaDiemFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDiaDiem dmDiaDiem) {
        em.persist(dmDiaDiem);
    }

    public void edit(DmDiaDiem dmDiaDiem) {
        em.merge(dmDiaDiem);
    }

    public void remove(DmDiaDiem dmDiaDiem) {
        em.remove(em.merge(dmDiaDiem));
    }

    public DmDiaDiem find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDiaDiem.class, id);
    }

    public List<DmDiaDiem> findAll() {
        return em.createQuery("select object(o) from DmDiaDiem as o").getResultList();
    }
    
}


