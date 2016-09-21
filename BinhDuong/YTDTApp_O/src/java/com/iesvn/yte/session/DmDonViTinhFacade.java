/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDonViTinh;
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
public class DmDonViTinhFacade implements DmDonViTinhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmDonViTinh dmDonViTinh) {
        em.persist(dmDonViTinh);
    }

    public void edit(DmDonViTinh dmDonViTinh) {
        em.merge(dmDonViTinh);
    }

    public void remove(DmDonViTinh dmDonViTinh) {
        em.remove(em.merge(dmDonViTinh));
    }

    public DmDonViTinh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmDonViTinh.class, id);
    }

    public List<DmDonViTinh> findAll() {
        return em.createQuery("select object(o) from DmDonViTinh as o").getResultList();
    }
    
}


