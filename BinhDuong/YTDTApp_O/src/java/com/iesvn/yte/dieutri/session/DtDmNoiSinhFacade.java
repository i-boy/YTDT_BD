/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DtDmNoiSinhFacade implements DtDmNoiSinhFacadeLocal, DtDmNoiSinhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmNoiSinh dtDmNoiSinh) {
        em.persist(dtDmNoiSinh);
    }

    public void edit(DtDmNoiSinh dtDmNoiSinh) {
        em.merge(dtDmNoiSinh);
    }

    public void remove(DtDmNoiSinh dtDmNoiSinh) {
        em.remove(em.merge(dtDmNoiSinh));
    }

    public DtDmNoiSinh find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmNoiSinh.class, id);
    }

    public List<DtDmNoiSinh> findAll() {
        return em.createQuery("select object(o) from DtDmNoiSinh as o").getResultList();
    }

}


