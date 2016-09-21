/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DeNghiCc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DeNghiCcFacade implements DeNghiCcFacadeLocal, DeNghiCcFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DeNghiCc deNghiCc) {
        em.persist(deNghiCc);
    }

    public void edit(DeNghiCc deNghiCc) {
        em.merge(deNghiCc);
    }

    public void remove(DeNghiCc deNghiCc) {
        em.remove(em.merge(deNghiCc));
    }

    public DeNghiCc find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DeNghiCc.class, id);
    }

    public List<DeNghiCc> findAll() {
        return em.createQuery("select object(o) from DeNghiCc as o").getResultList();
    }

}


