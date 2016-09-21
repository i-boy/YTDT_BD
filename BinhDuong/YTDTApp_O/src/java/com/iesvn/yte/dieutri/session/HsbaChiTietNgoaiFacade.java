/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoai;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quang
 */
@Stateless
public class HsbaChiTietNgoaiFacade implements HsbaChiTietNgoaiFacadeRemote, HsbaChiTietNgoaiFacadeLocal {

        @PersistenceContext
    private EntityManager em;

     public void create(HsbaChiTietNgoai hsbaChiTietNgoai) {
        em.persist(hsbaChiTietNgoai);
    }

    public void edit(HsbaChiTietNgoai hsbaChiTietNgoai) {
        em.merge(hsbaChiTietNgoai);
    }

    public void remove(HsbaChiTietNgoai hsbaChiTietNgoai) {
        em.remove(em.merge(hsbaChiTietNgoai));
    }

    public HsbaChiTietNgoai find(Object id) {

       List<HsbaChiTietNgoai> lstHSBACTNgoai =  em.createQuery("select object(o) from HsbaChiTietNgoai as o where o.hsbactngoaiMa = :id ").setParameter("id", id).getResultList();
       if (lstHSBACTNgoai != null && lstHSBACTNgoai.size() >= 0){
           return lstHSBACTNgoai.get(0);
       }else{
           return null;
       }
    }
    public HsbaChiTietNgoai findByHsbaCM(Integer hsbacmmaso) {

       List<HsbaChiTietNgoai> lstHSBACTNgoai =  em.createQuery("select object(o) from HsbaChiTietNgoai as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactngoaiMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();

       if (lstHSBACTNgoai != null && lstHSBACTNgoai.size() > 0){
           return lstHSBACTNgoai.get(0);
       }else{
           return null;
       }
    }

    public List<HsbaChiTietNgoai> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNgoai as o").getResultList();
    }


}
