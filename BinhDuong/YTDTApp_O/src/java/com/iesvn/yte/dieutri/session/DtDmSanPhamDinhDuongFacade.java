/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmSanPhamDinhDuong;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmSanPhamDinhDuongFacade implements DtDmSanPhamDinhDuongFacadeLocal, DtDmSanPhamDinhDuongFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong) {
        em.persist(dtDmSanPhamDinhDuong);
    }

    public void edit(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong) {
        em.merge(dtDmSanPhamDinhDuong);
    }

    public void remove(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong) {
        em.remove(em.merge(dtDmSanPhamDinhDuong));
    }

    public DtDmSanPhamDinhDuong find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmSanPhamDinhDuong.class, id);
    }

    public List<DtDmSanPhamDinhDuong> findAll() {
        return em.createQuery("select object(o) from DtDmSanPhamDinhDuong as o").getResultList();
    }

}
