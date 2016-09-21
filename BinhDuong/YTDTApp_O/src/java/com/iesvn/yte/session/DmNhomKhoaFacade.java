/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomKhoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author root
 */
@Stateless
public class DmNhomKhoaFacade implements DmNhomKhoaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNhomKhoa dmNhomKhoa) {
        em.persist(dmNhomKhoa);
    }

    public void edit(DmNhomKhoa dmNhomKhoa) {
        em.merge(dmNhomKhoa);
    }

    public void remove(DmNhomKhoa dmNhomKhoa) {
        em.remove(em.merge(dmNhomKhoa));
    }

    public DmNhomKhoa find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNhomKhoa.class, id);
    }

    public List<DmNhomKhoa> findAll() {
        return em.createQuery("select object(o) from DmNhomKhoa as o").getResultList();
    }

}


