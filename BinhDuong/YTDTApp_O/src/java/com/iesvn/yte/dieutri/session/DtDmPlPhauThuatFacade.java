/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmPlPhauThuat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmPlPhauThuatFacade implements DtDmPlPhauThuatFacadeLocal, DtDmPlPhauThuatFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmPlPhauThuat dtDmPlPhauThuat) {
        em.persist(dtDmPlPhauThuat);
    }

    public void edit(DtDmPlPhauThuat dtDmPlPhauThuat) {
        em.merge(dtDmPlPhauThuat);
    }

    public void remove(DtDmPlPhauThuat dtDmPlPhauThuat) {
        em.remove(em.merge(dtDmPlPhauThuat));
    }

    public DtDmPlPhauThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmPlPhauThuat.class, id);
    }

    public List<DtDmPlPhauThuat> findAll() {
        return em.createQuery("select object(o) from DtDmPlPhauThuat as o").getResultList();
    }

}


