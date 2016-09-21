/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiCanBo;
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
public class DmLoaiCanBoFacade implements DmLoaiCanBoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmLoaiCanBo dmLoaiCanBo) {
        em.persist(dmLoaiCanBo);
    }

    public void edit(DmLoaiCanBo dmLoaiCanBo) {
        em.merge(dmLoaiCanBo);
    }

    public void remove(DmLoaiCanBo dmLoaiCanBo) {
        em.remove(em.merge(dmLoaiCanBo));
    }

    public DmLoaiCanBo find(Object id) {
        return em.find(com.iesvn.yte.entity.DmLoaiCanBo.class, id);
    }

    public List<DmLoaiCanBo> findAll() {
        return em.createQuery("select object(o) from DmLoaiCanBo as o").getResultList();
    }
    
}


