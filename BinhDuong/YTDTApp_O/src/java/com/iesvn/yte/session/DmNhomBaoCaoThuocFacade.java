/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomBaoCaoThuoc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmNhomBaoCaoThuocFacade implements DmNhomBaoCaoThuocFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc) {
        em.persist(dmNhomBaoCaoThuoc);
    }

    public void edit(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc) {
        em.merge(dmNhomBaoCaoThuoc);
    }

    public void remove(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc) {
        em.remove(em.merge(dmNhomBaoCaoThuoc));
    }

    public DmNhomBaoCaoThuoc find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNhomBaoCaoThuoc.class, id);
    }

    public List<DmNhomBaoCaoThuoc> findAll() {
        return em.createQuery("select object(o) from DmNhomBaoCaoThuoc as o").getResultList();
    }

}


