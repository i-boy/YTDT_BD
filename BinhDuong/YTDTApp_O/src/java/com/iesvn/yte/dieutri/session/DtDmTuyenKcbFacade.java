/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmTuyenKcbFacade implements DtDmTuyenKcbFacadeLocal, DtDmTuyenKcbFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmTuyenKcb dtDmTuyenKcb) {
        em.persist(dtDmTuyenKcb);
    }

    public void edit(DtDmTuyenKcb dtDmTuyenKcb) {
        em.merge(dtDmTuyenKcb);
    }

    public void remove(DtDmTuyenKcb dtDmTuyenKcb) {
        em.remove(em.merge(dtDmTuyenKcb));
    }

    public DtDmTuyenKcb find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmTuyenKcb.class, id);
    }

    public List<DtDmTuyenKcb> findAll() {
        return em.createQuery("select object(o) from DtDmTuyenKcb as o").getResultList();
    }

}


