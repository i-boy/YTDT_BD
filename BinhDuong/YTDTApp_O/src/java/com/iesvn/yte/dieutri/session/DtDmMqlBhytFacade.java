/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HP
 */
@Stateless
public class DtDmMqlBhytFacade implements DtDmMqlBhytFacadeLocal, DtDmMqlBhytFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(DtDmMqlBhyt dtDmMqlBhyt) {
        em.persist(dtDmMqlBhyt);
    }

    public void edit(DtDmMqlBhyt dtDmMqlBhyt) {
        em.merge(dtDmMqlBhyt);
    }

    public void remove(DtDmMqlBhyt dtDmMqlBhyt) {
        em.remove(em.merge(dtDmMqlBhyt));
    }

    public DtDmMqlBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmMqlBhyt.class, id);
    }

    public List<DtDmMqlBhyt> findAll() {
        return em.createQuery("select object(o) from DtDmMqlBhyt as o").getResultList();
    }
    
    public DtDmMqlBhyt findByMa(String maQL) {
        List<DtDmMqlBhyt> listMql = em.createQuery("SELECT d FROM DtDmMqlBhyt d WHERE d.dtdmmqlbhytMa = :dtdmmqlbhytMa").setParameter("dtdmmqlbhytMa", maQL).getResultList();
        if (listMql == null || listMql.size() < 1 ) {
            return null;
        } else {
            return listMql.get(0);
        }                
    }

}
