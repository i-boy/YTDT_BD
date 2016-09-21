/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietMat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quang
 */

@Stateless
public class HsbaChiTietMatFacade implements HsbaChiTietMatFacadeLocal, HsbaChiTietMatFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    public void create(HsbaChiTietMat hsbaChiTietMat) {
        getEm().persist(hsbaChiTietMat);
    }

    public void edit(HsbaChiTietMat hsbaChiTietMat) {
        getEm().merge(hsbaChiTietMat);;
    }

    public HsbaChiTietMat find(Object id) {
       return getEm().find(com.iesvn.yte.dieutri.entity.HsbaChiTietMat.class, id);
    }

    public List<HsbaChiTietMat> findAll() {
        return getEm().createQuery("select object(o) from HsbaChiTietMat as o").getResultList();
    }

    public void remove(HsbaChiTietMat hsbaChiTietMat) {
         getEm().remove(getEm().merge(hsbaChiTietMat));
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public HsbaChiTietMat findByHsbaCM(Integer hsbacmmaso) {

       List<HsbaChiTietMat> lstHSBACTMat =  em.createQuery("select object(o) from HsbaChiTietMat as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactmatMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTMat != null && lstHSBACTMat.size() > 0){
           return lstHSBACTMat.get(0);
       }else{
           return null;
       }
    }


}
