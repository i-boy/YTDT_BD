/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuanHeGiaDinh;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmQuanHeGiaDinhFacade implements DmQuanHeGiaDinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmQuanHeGiaDinh dmQuanHeGiaDinh) {
        em.persist(dmQuanHeGiaDinh);
    }

    public void edit(DmQuanHeGiaDinh dmQuanHeGiaDinh) {
        em.merge(dmQuanHeGiaDinh);
    }

    public void remove(DmQuanHeGiaDinh dmQuanHeGiaDinh) {
        em.remove(em.merge(dmQuanHeGiaDinh));
    }

    public DmQuanHeGiaDinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmQuanHeGiaDinh.class, id);
    }

    public List<DmQuanHeGiaDinh> findAll() {
        return em.createQuery("select object(o) from DmQuanHeGiaDinh as o").getResultList();
    }

}


