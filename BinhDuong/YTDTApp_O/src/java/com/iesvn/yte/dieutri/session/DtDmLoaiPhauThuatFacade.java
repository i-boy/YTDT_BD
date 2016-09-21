/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmLoaiPhauThuatFacade implements DtDmLoaiPhauThuatFacadeLocal, DtDmLoaiPhauThuatFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
        em.persist(dtDmLoaiPhauThuat);
    }

    public void edit(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
        em.merge(dtDmLoaiPhauThuat);
    }

    public void remove(DtDmLoaiPhauThuat dtDmLoaiPhauThuat) {
        em.remove(em.merge(dtDmLoaiPhauThuat));
    }

    public DtDmLoaiPhauThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat.class, id);
    }

    public List<DtDmLoaiPhauThuat> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiPhauThuat as o").getResultList();
    }

}


