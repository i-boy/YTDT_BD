/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDieuTri;
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
public class DmDieuTriFacade implements DmDieuTriFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDieuTri dmDieuTri) {
        em.persist(dmDieuTri);
    }

    public void edit(DmDieuTri dmDieuTri) {
        em.merge(dmDieuTri);
    }

    public void remove(DmDieuTri dmDieuTri) {
        em.remove(em.merge(dmDieuTri));
    }

    public DmDieuTri find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDieuTri.class, id);
    }

    public List<DmDieuTri> findAll() {
        return em.createQuery("select object(o) from DmDieuTri as o").getResultList();
    }
    
}


