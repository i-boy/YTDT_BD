/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaSan;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaSanFacade implements HsbaSanFacadeLocal, HsbaSanFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public HsbaSan create(HsbaSan hsbaSan) {
        getEm().persist(hsbaSan);
        return hsbaSan;
    }

    public void edit(HsbaSan hsbaSan) {
        getEm().merge(hsbaSan);
    }

    public void remove(HsbaSan hsbaSan) {
        getEm().remove(getEm().merge(hsbaSan));
    }

    public HsbaSan find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.HsbaSan.class, id);
    }

    public List<HsbaSan> findAll() {
        return getEm().createQuery("select object(o) from HsbaSan as o").getResultList();
    }
    
     public List<HsbaSan> findListByHsbaMa(String hsbaMa) {
         System.out.println("---find Hsbagct by findByHsbaMa"); 
        hsbaMa = Utils.formatMa(getEm(),hsbaMa);
        System.out.println("---ma giay (format): " + hsbaMa);
        try {
            Query query = getEm().createQuery("select object(o) from HsbaSan  as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :hsbaMa");
            query.setParameter("hsbaMa", hsbaMa);
            List<HsbaSan> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
     }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}


