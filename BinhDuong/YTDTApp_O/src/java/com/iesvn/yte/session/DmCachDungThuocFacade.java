/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmCachDungThuoc;
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
public class DmCachDungThuocFacade implements DmCachDungThuocFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmCachDungThuoc dmCachDungThuoc) {
        em.persist(dmCachDungThuoc);
    }

    public void edit(DmCachDungThuoc dmCachDungThuoc) {
        em.merge(dmCachDungThuoc);
    }

    public void remove(DmCachDungThuoc dmCachDungThuoc) {
        em.remove(em.merge(dmCachDungThuoc));
    }

    public DmCachDungThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmCachDungThuoc.class, id);
    }

    public List<DmCachDungThuoc> findAll() {
        return em.createQuery("select object(o) from DmCachDungThuoc as o").getResultList();
    }

}


