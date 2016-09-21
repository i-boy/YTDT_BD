/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietTmh;
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
public class HsbaChiTietTmhFacade implements HsbaChiTietTmhFacadeLocal, HsbaChiTietTmhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietTmh hsbaChiTietTmh) {
        em.persist(hsbaChiTietTmh);
    }

    public void edit(HsbaChiTietTmh hsbaChiTietTmh) {
        em.merge(hsbaChiTietTmh);
    }

    public void remove(HsbaChiTietTmh hsbaChiTietTmh) {
        em.remove(em.merge(hsbaChiTietTmh));
    }

    public HsbaChiTietTmh find(Object id) {
        return em.find(HsbaChiTietTmh.class, id);
    }

    public List<HsbaChiTietTmh> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietTmh as o").getResultList();
    }

    public List<HsbaChiTietTmh> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietTmh as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietTmh as o").getSingleResult()).intValue();
    }

    public HsbaChiTietTmh findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietTmh> lstHSBACTTmh =  em.createQuery("select object(o) from HsbaChiTietTmh as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbacttmhMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTTmh != null && lstHSBACTTmh.size() > 0){
           return lstHSBACTTmh.get(0);
       }else{
           return null;
       }
    }

}
