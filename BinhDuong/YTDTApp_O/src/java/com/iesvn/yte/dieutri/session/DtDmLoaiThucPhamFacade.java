/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmLoaiThucPhamFacade implements DtDmLoaiThucPhamFacadeLocal, DtDmLoaiThucPhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiThucPham dtDmLoaiThucPham) {
        em.persist(dtDmLoaiThucPham);
    }

    public void edit(DtDmLoaiThucPham dtDmLoaiThucPham) {
        em.merge(dtDmLoaiThucPham);
    }

    public void remove(DtDmLoaiThucPham dtDmLoaiThucPham) {
        em.remove(em.merge(dtDmLoaiThucPham));
    }

    public DtDmLoaiThucPham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham.class, id);
    }

    public List<DtDmLoaiThucPham> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiThucPham as o").getResultList();
    }

}
