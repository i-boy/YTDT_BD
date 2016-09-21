/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHinhThucXuLy;
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
public class DmHinhThucXuLyFacade implements DmHinhThucXuLyFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmHinhThucXuLy dmHinhThucXuLy) {
        em.persist(dmHinhThucXuLy);
    }

    public void edit(DmHinhThucXuLy dmHinhThucXuLy) {
        em.merge(dmHinhThucXuLy);
    }

    public void remove(DmHinhThucXuLy dmHinhThucXuLy) {
        em.remove(em.merge(dmHinhThucXuLy));
    }

    public DmHinhThucXuLy find(Object id) {
        return em.find(com.iesvn.yte.entity.DmHinhThucXuLy.class, id);
    }

    public List<DmHinhThucXuLy> findAll() {
        return em.createQuery("select object(o) from DmHinhThucXuLy as o").getResultList();
    }
    
}


