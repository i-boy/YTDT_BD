/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;
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
public class HsbaChiTietSankhoaFacade implements HsbaChiTietSankhoaFacadeLocal, HsbaChiTietSankhoaFacadeRemote {
    @PersistenceContext(unitName = "YTDTAppPU")
    private EntityManager em;

    public void create(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        em.persist(hsbaChiTietSankhoa);
    }

    public void edit(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        em.merge(hsbaChiTietSankhoa);
    }

    public void remove(HsbaChiTietSankhoa hsbaChiTietSankhoa) {
        em.remove(em.merge(hsbaChiTietSankhoa));
    }

    public HsbaChiTietSankhoa find(Object id) {
        return em.find(HsbaChiTietSankhoa.class, id);
    }

    public List<HsbaChiTietSankhoa> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietSankhoa as o").getResultList();
    }

    public List<HsbaChiTietSankhoa> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietSankhoa as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietSankhoa as o").getSingleResult()).intValue();
    }

    public HsbaChiTietSankhoa findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietSankhoa> lstHSBACTSankhoa =  em.createQuery("select object(o) from HsbaChiTietSankhoa as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactsankhoaMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTSankhoa != null && lstHSBACTSankhoa.size() > 0){
           return lstHSBACTSankhoa.get(0);
       }else{
           return null;
       }
    }
}
