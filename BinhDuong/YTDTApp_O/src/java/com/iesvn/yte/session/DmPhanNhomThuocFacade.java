/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhanNhomThuoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmPhanNhomThuocFacade implements DmPhanNhomThuocFacadeLocal, DmPhanNhomThuocFacadeRemote{
    @PersistenceContext
    private EntityManager em;

    public void create(DmPhanNhomThuoc dmPhanNhomThuoc) {
        em.persist(dmPhanNhomThuoc);
    }

    public void edit(DmPhanNhomThuoc dmPhanNhomThuoc) {
        em.merge(dmPhanNhomThuoc);
    }

    public void remove(DmPhanNhomThuoc dmPhanNhomThuoc) {
        em.remove(em.merge(dmPhanNhomThuoc));
    }

    public DmPhanNhomThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmPhanNhomThuoc.class, id);
    }

    public List<DmPhanNhomThuoc> findAll() {
        return em.createQuery("select object(o) from DmPhanNhomThuoc as o").getResultList();
    }

}


