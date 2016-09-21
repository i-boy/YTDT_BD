/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiHoSo;
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
public class DmLoaiHoSoFacade implements DmLoaiHoSoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiHoSo dmLoaiHoSo) {
        em.persist(dmLoaiHoSo);
    }

    public void edit(DmLoaiHoSo dmLoaiHoSo) {
        em.merge(dmLoaiHoSo);
    }

    public void remove(DmLoaiHoSo dmLoaiHoSo) {
        em.remove(em.merge(dmLoaiHoSo));
    }

    public DmLoaiHoSo find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiHoSo.class, id);
    }

    public List<DmLoaiHoSo> findAll() {
        return em.createQuery("select object(o) from DmLoaiHoSo as o").getResultList();
    }
    
}


