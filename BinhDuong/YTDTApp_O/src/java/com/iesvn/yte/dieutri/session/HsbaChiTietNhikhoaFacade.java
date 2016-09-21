/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;
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
public class HsbaChiTietNhikhoaFacade implements HsbaChiTietNhikhoaFacadeLocal, HsbaChiTietNhikhoaFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        em.persist(hsbaChiTietNhikhoa);
    }

    public void edit(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        em.merge(hsbaChiTietNhikhoa);
    }

    public void remove(HsbaChiTietNhikhoa hsbaChiTietNhikhoa) {
        em.remove(em.merge(hsbaChiTietNhikhoa));
    }

    public HsbaChiTietNhikhoa find(Object id) {
        return em.find(HsbaChiTietNhikhoa.class, id);
    }

    public List<HsbaChiTietNhikhoa> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNhikhoa as o").getResultList();
    }

    public List<HsbaChiTietNhikhoa> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietNhikhoa as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public HsbaChiTietNhikhoa findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietNhikhoa> lstHSBACTNhikhoa =  em.createQuery("select object(o) from HsbaChiTietNhikhoa as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactnhikhoaMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTNhikhoa != null && lstHSBACTNhikhoa.size() > 0){
           return lstHSBACTNhikhoa.get(0);
       }else{
           return null;
       }
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietNhikhoa as o").getSingleResult()).intValue();
    }

}
