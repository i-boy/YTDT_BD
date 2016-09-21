/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmVoCamFacade implements DtDmVoCamFacadeLocal, DtDmVoCamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmVoCam dtDmVoCam) {
        em.persist(dtDmVoCam);
    }

    public void edit(DtDmVoCam dtDmVoCam) {
        em.merge(dtDmVoCam);
    }

    public void remove(DtDmVoCam dtDmVoCam) {
        em.remove(em.merge(dtDmVoCam));
    }

    public DtDmVoCam find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmVoCam.class, id);
    }

    public List<DtDmVoCam> findAll() {
        return em.createQuery("select object(o) from DtDmVoCam as o").getResultList();
    }
    

            

}


