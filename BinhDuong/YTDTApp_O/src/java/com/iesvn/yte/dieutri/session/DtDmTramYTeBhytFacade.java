/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thanh
 */
@Stateless
public class DtDmTramYTeBhytFacade implements DtDmTramYTeBhytRemote, DtDmTramYTeBhytLocal {
     @PersistenceContext
    private EntityManager em;
    public void create(DtDmTramYTeBhyt dtDmTramYTeBhyt) {
         em.persist(dtDmTramYTeBhyt);
    }

    public void edit(DtDmTramYTeBhyt dtDmTramYTeBhyt) {
          em.merge(dtDmTramYTeBhyt);
    }

    public void remove(DtDmTramYTeBhyt dtDmTramYTeBhyt) {
       em.remove(em.merge(dtDmTramYTeBhyt));
    }

    public DtDmTramYTeBhyt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt.class, id);
    }

    public List<DtDmTramYTeBhyt> findAll() {
       return em.createQuery("select object(o) from DtDmTramYTeBhyt as o").getResultList();
    }
    
   
}
