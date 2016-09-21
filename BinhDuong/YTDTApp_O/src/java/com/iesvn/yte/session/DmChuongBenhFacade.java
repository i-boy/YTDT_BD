/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmChuongBenh;
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
public class DmChuongBenhFacade implements DmChuongBenhFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmChuongBenh dmChuongBenh) {
        em.persist(dmChuongBenh);
    }

    public void edit(DmChuongBenh dmChuongBenh) {
        em.merge(dmChuongBenh);
    }

    public void remove(DmChuongBenh dmChuongBenh) {
        em.remove(em.merge(dmChuongBenh));
    }

    public DmChuongBenh find(Object id) {
        return em.find(com.iesvn.yte.entity.DmChuongBenh.class, id);
    }

    public List<DmChuongBenh> findAll() {
        return em.createQuery("select object(o) from DmChuongBenh as o").getResultList();
    }
    
}


