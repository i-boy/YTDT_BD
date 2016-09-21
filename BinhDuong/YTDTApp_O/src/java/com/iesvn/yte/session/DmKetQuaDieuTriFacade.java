/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKetQuaDieuTri;
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
public class DmKetQuaDieuTriFacade implements DmKetQuaDieuTriFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmKetQuaDieuTri dmKetQuaDieuTri) {
        em.persist(dmKetQuaDieuTri);
    }

    public void edit(DmKetQuaDieuTri dmKetQuaDieuTri) {
        em.merge(dmKetQuaDieuTri);
    }

    public void remove(DmKetQuaDieuTri dmKetQuaDieuTri) {
        em.remove(em.merge(dmKetQuaDieuTri));
    }

    public DmKetQuaDieuTri find(Object id) {
        return em.find(com.iesvn.yte.entity.DmKetQuaDieuTri.class, id);
    }

    public List<DmKetQuaDieuTri> findAll() {
        return em.createQuery("select object(o) from DmKetQuaDieuTri as o").getResultList();
    }
    
}


