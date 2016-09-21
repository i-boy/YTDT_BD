/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thanh
 */
@Stateless
public class HsbaChiTietNoiFacade implements HsbaChiTietNoiFacadeRemote, HsbaChiTietNoiFacadeLocal {

        @PersistenceContext
    private EntityManager em;

     public void create(HsbaChiTietNoi hsbaChiTietNoi) {
        em.persist(hsbaChiTietNoi);      
    }

    public void edit(HsbaChiTietNoi hsbaChiTietNoi) {
        em.merge(hsbaChiTietNoi);
    }

    public void remove(HsbaChiTietNoi hsbaChiTietNoi) {
        em.remove(em.merge(hsbaChiTietNoi));
    }

    public HsbaChiTietNoi find(Object id) {
      
       List<HsbaChiTietNoi> lstHSBACTNoi =  em.createQuery("select object(o) from HsbaChiTietNoi as o where o.hsbactnoiMa = :id ").setParameter("id", id).getResultList();
       if (lstHSBACTNoi != null && lstHSBACTNoi.size() >= 0){
           return lstHSBACTNoi.get(0);
       }else{
           return null;
       }
    }
    public HsbaChiTietNoi findByHsbaCM(Integer hsbacmmaso) {

       List<HsbaChiTietNoi> lstHSBACTNoi =  em.createQuery("select object(o) from HsbaChiTietNoi as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactnoiMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTNoi != null && lstHSBACTNoi.size() > 0){
           return lstHSBACTNoi.get(0);
       }else{
           return null;
       }
    }

    public List<HsbaChiTietNoi> findAll() {
        return em.createQuery("select object(o) from HsbaChiTietNoi as o").getResultList();
    }
    
 
}
