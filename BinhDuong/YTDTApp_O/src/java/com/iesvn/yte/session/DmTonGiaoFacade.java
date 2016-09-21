/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTonGiao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmTonGiaoFacade implements DmTonGiaoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmTonGiao dmTonGiao) {
        em.persist(dmTonGiao);
    }

    public void edit(DmTonGiao dmTonGiao) {
        em.merge(dmTonGiao);
    }

    public void remove(DmTonGiao dmTonGiao) {
        em.remove(em.merge(dmTonGiao));
    }

    public DmTonGiao find(Object id) {
        return em.find(com.iesvn.yte.entity.DmTonGiao.class, id);
    }

    public List<DmTonGiao> findAll() {
        return em.createQuery("select object(o) from DmTonGiao as o").getResultList();
    }

}


