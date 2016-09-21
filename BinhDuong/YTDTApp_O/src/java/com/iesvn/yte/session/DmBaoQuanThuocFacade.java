/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBaoQuanThuoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmBaoQuanThuocFacade implements DmBaoQuanThuocFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmBaoQuanThuoc dmBaoQuanThuoc) {
        em.persist(dmBaoQuanThuoc);
    }

    public void edit(DmBaoQuanThuoc dmBaoQuanThuoc) {
        em.merge(dmBaoQuanThuoc);
    }

    public void remove(DmBaoQuanThuoc dmBaoQuanThuoc) {
        em.remove(em.merge(dmBaoQuanThuoc));
    }

    public DmBaoQuanThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmBaoQuanThuoc.class, id);
    }

    public List<DmBaoQuanThuoc> findAll() {
        return em.createQuery("select object(o) from DmBaoQuanThuoc as o").getResultList();
    }
    
}


