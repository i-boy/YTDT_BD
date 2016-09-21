/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;
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
public class HsbaChiTietSosinhFacade implements HsbaChiTietSosinhFacadeLocal, HsbaChiTietSosinhFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietSosinh hsbaChiTietSosinh) {
        em.persist(hsbaChiTietSosinh);
    }

    public void edit(HsbaChiTietSosinh hsbaChiTietSosinh) {
        em.merge(hsbaChiTietSosinh);
    }

    public void remove(HsbaChiTietSosinh hsbaChiTietSosinh) {
        em.remove(em.merge(hsbaChiTietSosinh));
    }

    public HsbaChiTietSosinh find(Object id) {
        return em.find(HsbaChiTietSosinh.class, id);
    }

    public List<HsbaChiTietSosinh> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietSosinh as o").getResultList();
    }

    public List<HsbaChiTietSosinh> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietSosinh as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietSosinh as o").getSingleResult()).intValue();
    }

     public HsbaChiTietSosinh findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietSosinh> lstHSBACTSosinh =  em.createQuery("select object(o) from HsbaChiTietSosinh as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactsosinhMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTSosinh != null && lstHSBACTSosinh.size() > 0){
           return lstHSBACTSosinh.get(0);
       }else{
           return null;
       }
    }

}
