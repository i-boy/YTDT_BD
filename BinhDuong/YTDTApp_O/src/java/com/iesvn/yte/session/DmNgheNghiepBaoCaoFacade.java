/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
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
public class DmNgheNghiepBaoCaoFacade implements DmNgheNghiepBaoCaoFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmNgheNghiepBaoCao dmNgheNghiepBaoCao) {
        em.persist(dmNgheNghiepBaoCao);
    }

    public void edit(DmNgheNghiepBaoCao dmNgheNghiepBaoCao) {
        em.merge(dmNgheNghiepBaoCao);
    }

    public void remove(DmNgheNghiepBaoCao dmNgheNghiepBaoCao) {
        em.remove(em.merge(dmNgheNghiepBaoCao));
    }

    public DmNgheNghiepBaoCao find(Object id) {
        return em.find(com.iesvn.yte.entity.DmNgheNghiepBaoCao.class, id);
    }

    public List<DmNgheNghiepBaoCao> findAll() {
        return em.createQuery("select object(o) from DmNgheNghiepBaoCao as o").getResultList();
    }
    
}


