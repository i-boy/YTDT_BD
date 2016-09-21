/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiPhieu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmLoaiPhieuFacade implements DmLoaiPhieuFacadeLocal, DmLoaiPhieuFacadeRemote{
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiPhieu dmLoaiPhieu) {
        em.persist(dmLoaiPhieu);
    }

    public void edit(DmLoaiPhieu dmLoaiPhieu) {
        em.merge(dmLoaiPhieu);
    }

    public void remove(DmLoaiPhieu dmLoaiPhieu) {
        em.remove(em.merge(dmLoaiPhieu));
    }

    public DmLoaiPhieu find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiPhieu.class, id);
    }

    public List<DmLoaiPhieu> findAll() {
        return em.createQuery("select object(o) from DmLoaiPhieu o").getResultList();
    }
}


