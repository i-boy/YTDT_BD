/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHoatChat;
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
public class DmHoatChatFacade implements DmHoatChatFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(DmHoatChat dmHoatChat) {
        em.persist(dmHoatChat);
    }

    public void edit(DmHoatChat dmHoatChat) {
        em.merge(dmHoatChat);
    }

    public void remove(DmHoatChat dmHoatChat) {
        em.remove(em.merge(dmHoatChat));
    }

    public DmHoatChat find(Object id) {
        return em.find(com.iesvn.yte.entity.DmHoatChat.class, id);
    }

    public List<DmHoatChat> findAll() {
        return em.createQuery("select object(o) from DmHoatChat as o").getResultList();
    }
    
}


