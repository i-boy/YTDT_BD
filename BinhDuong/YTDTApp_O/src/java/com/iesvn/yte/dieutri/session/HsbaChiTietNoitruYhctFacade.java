/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author halylam
 */
@Stateless
public class HsbaChiTietNoitruYhctFacade implements HsbaChiTietNoitruYhctFacadeLocal, HsbaChiTietNoitruYhctFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        em.persist(hsbaChiTietNoitruYhct);
    }

    public void edit(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        em.merge(hsbaChiTietNoitruYhct);
    }

    public void remove(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct) {
        em.remove(em.merge(hsbaChiTietNoitruYhct));
    }

    public HsbaChiTietNoitruYhct find(Object id) {
        return em.find(HsbaChiTietNoitruYhct.class, id);
    }

    public List<HsbaChiTietNoitruYhct> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNoitruYhct as o").getResultList();
    }

    public List<HsbaChiTietNoitruYhct> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietNoitruYhct as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietNoitruYhct as o").getSingleResult()).intValue();
    }

    public HsbaChiTietNoitruYhct findByHsbaCM(Integer hsbacmmaso) {

            List<HsbaChiTietNoitruYhct> lstHSBACTNoitruYhct =  em.createQuery("select object(o) from HsbaChiTietNoitruYhct as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactnoitruYhctMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
           if (lstHSBACTNoitruYhct != null && lstHSBACTNoitruYhct.size() > 0){
               return lstHSBACTNoitruYhct.get(0);
           }else{
               return null;
           }
        }
       
    }
