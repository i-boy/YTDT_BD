/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmLoaiMienFacade implements DtDmLoaiMienFacadeLocal, DtDmLoaiMienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmLoaiMien dtDmLoaiMien) {
        em.persist(dtDmLoaiMien);
    }

    public void edit(DtDmLoaiMien dtDmLoaiMien) {
        em.merge(dtDmLoaiMien);
    }

    public void remove(DtDmLoaiMien dtDmLoaiMien) {
        em.remove(em.merge(dtDmLoaiMien));
    }

    public DtDmLoaiMien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmLoaiMien.class, id);
    }

    public List<DtDmLoaiMien> findAll() {
        return em.createQuery("select object(o) from DtDmLoaiMien as o").getResultList();
    }

}


