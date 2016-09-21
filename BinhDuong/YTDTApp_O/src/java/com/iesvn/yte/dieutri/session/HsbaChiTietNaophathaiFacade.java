/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;
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
public class HsbaChiTietNaophathaiFacade implements HsbaChiTietNaophathaiFacadeLocal, HsbaChiTietNaophathaiFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public HsbaChiTietNaophathai create(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        em.persist(hsbaChiTietNaophathai);
        return hsbaChiTietNaophathai;
    }

    public void edit(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        em.merge(hsbaChiTietNaophathai);
    }

    public void remove(HsbaChiTietNaophathai hsbaChiTietNaophathai) {
        em.remove(em.merge(hsbaChiTietNaophathai));
    }

    public HsbaChiTietNaophathai find(Object id) {
        return em.find(HsbaChiTietNaophathai.class, id);
    }

    public List<HsbaChiTietNaophathai> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNaophathai as o").getResultList();
    }

    public List<HsbaChiTietNaophathai> findRange(int[] range) {
        Query q = em.createQuery("select object(o) from HsbaChiTietNaophathai as o");
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        return ((Long) em.createQuery("select count(o) from HsbaChiTietNaophathai as o").getSingleResult()).intValue();
    }

     public HsbaChiTietNaophathai findByHsbaCM(Integer hsbacmmaso) {

       List<HsbaChiTietNaophathai> lstHSBACTNaophathai =  em.createQuery("select object(o) from HsbaChiTietNaophathai as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactnaophathaiMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTNaophathai != null && lstHSBACTNaophathai.size() > 0){
           return lstHSBACTNaophathai.get(0);
       }else{
           return null;
       }
    }

}
