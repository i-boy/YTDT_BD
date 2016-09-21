/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HoanThuKham;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HoanThuKhamFacade implements HoanThuKhamFacadeLocal, HoanThuKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HoanThuKham hoanThuKham) {
        em.persist(hoanThuKham);
    }

    public void edit(HoanThuKham hoanThuKham) {
        em.merge(hoanThuKham);
    }

    public void remove(HoanThuKham hoanThuKham) {
        em.remove(em.merge(hoanThuKham));
    }

    public HoanThuKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HoanThuKham.class, id);
    }

    public List<HoanThuKham> findAll() {
        return em.createQuery("select object(o) from HoanThuKham as o").getResultList();
    }

}


