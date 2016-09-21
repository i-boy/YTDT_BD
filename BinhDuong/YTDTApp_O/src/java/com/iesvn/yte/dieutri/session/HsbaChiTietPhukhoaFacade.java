/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;
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
public class HsbaChiTietPhukhoaFacade implements HsbaChiTietPhukhoaFacadeLocal, HsbaChiTietPhukhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        em.persist(hsbaChiTietPhukhoa);
    }

    public void edit(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        em.merge(hsbaChiTietPhukhoa);
    }

    public void remove(HsbaChiTietPhukhoa hsbaChiTietPhukhoa) {
        em.remove(em.merge(hsbaChiTietPhukhoa));
    }

    public HsbaChiTietPhukhoa find(Object id) {
        return em.find(HsbaChiTietPhukhoa.class, id);
    }

    public List<HsbaChiTietPhukhoa> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietPhukhoa as o").getResultList();
    }

    public List<HsbaChiTietPhukhoa> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietPhukhoa as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietPhukhoa as o").getSingleResult()).intValue();
    }

    public HsbaChiTietPhukhoa findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietPhukhoa> lstHSBACTPhukhoa =  em.createQuery("select object(o) from HsbaChiTietPhukhoa as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactphukhoaMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTPhukhoa != null && lstHSBACTPhukhoa.size() > 0){
           return lstHSBACTPhukhoa.get(0);
       }else{
           return null;
       }
    }

}
