/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmPhauThuatFacade implements DtDmPhauThuatFacadeLocal, DtDmPhauThuatFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPhauThuat dtDmPhauThuat) {
        em.persist(dtDmPhauThuat);
    }

    public void edit(DtDmPhauThuat dtDmPhauThuat) {
        em.merge(dtDmPhauThuat);
    }

    public void remove(DtDmPhauThuat dtDmPhauThuat) {
        em.remove(em.merge(dtDmPhauThuat));
    }

    public DtDmPhauThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPhauThuat.class, id);
    }

    public List<DtDmPhauThuat> findAll() {
        return em.createQuery("select object(o) from DtDmPhauThuat as o").getResultList();
    }

}


