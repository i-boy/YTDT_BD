/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmDienMien;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmDienMienFacade implements DtDmDienMienFacadeLocal, DtDmDienMienFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmDienMien dtDmDienMien) {
        em.persist(dtDmDienMien);
    }

    public void edit(DtDmDienMien dtDmDienMien) {
        em.merge(dtDmDienMien);
    }

    public void remove(DtDmDienMien dtDmDienMien) {
        em.remove(em.merge(dtDmDienMien));
    }

    public DtDmDienMien find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmDienMien.class, id);
    }

    public List<DtDmDienMien> findAll() {
        return em.createQuery("select object(o) from DtDmDienMien as o").getResultList();
    }

}


