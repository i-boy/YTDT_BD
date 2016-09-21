/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmKhach;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmKhachFacade implements DtDmKhachFacadeLocal, DtDmKhachFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmKhach dtDmKhach) {
        em.persist(dtDmKhach);
    }

    public void edit(DtDmKhach dtDmKhach) {
        em.merge(dtDmKhach);
    }

    public void remove(DtDmKhach dtDmKhach) {
        em.remove(em.merge(dtDmKhach));
    }

    public DtDmKhach find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmKhach.class, id);
    }

    public List<DtDmKhach> findAll() {
        return em.createQuery("select object(o) from DtDmKhach as o").getResultList();
    }

}


