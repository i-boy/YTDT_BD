/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;
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
public class HsbaChiTietNgoaitruYhctFacade implements HsbaChiTietNgoaitruYhctFacadeLocal, HsbaChiTietNgoaitruYhctFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        em.persist(hsbaChiTietNgoaitruYhct);
    }

    public void edit(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        em.merge(hsbaChiTietNgoaitruYhct);
    }

    public void remove(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct) {
        em.remove(em.merge(hsbaChiTietNgoaitruYhct));
    }

    public HsbaChiTietNgoaitruYhct find(Object id) {
        return em.find(HsbaChiTietNgoaitruYhct.class, id);
    }

    public List<HsbaChiTietNgoaitruYhct> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNgoaitruYhct as o").getResultList();
    }

    public List<HsbaChiTietNgoaitruYhct> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietNgoaitruYhct as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietNgoaitruYhct as o").getSingleResult()).intValue();
    }

    public HsbaChiTietNgoaitruYhct findByHsbaCM(Integer hsbacmmaso) {
       
       List<HsbaChiTietNgoaitruYhct> lstHSBACTNgoaitruYhct =  em.createQuery("select object(o) from HsbaChiTietNgoaitruYhct as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactngoaitruYhctMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTNgoaitruYhct != null && lstHSBACTNgoaitruYhct.size() > 0){
           return lstHSBACTNgoaitruYhct.get(0);
       }else{
           return null;
       }
    }

}
