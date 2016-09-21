/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChiTietRhm;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quang
 */

@Stateless
public class HsbaChiTietRhmFacade implements HsbaChiTietRhmFacadeLocal, HsbaChiTietRhmFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    public void create(HsbaChiTietRhm hsbaChiTietRhm) {
        getEm().persist(hsbaChiTietRhm);
    }

    public void edit(HsbaChiTietRhm hsbaChiTietRhm) {
        getEm().merge(hsbaChiTietRhm);;
    }

    public HsbaChiTietRhm find(Object id) {
       return getEm().find(com.iesvn.yte.dieutri.entity.HsbaChiTietRhm.class, id);
    }

    public List<HsbaChiTietRhm> findAll() {
        return getEm().createQuery("select object(o) from HsbaChiTietRhm as o").getResultList();
    }

    public void remove(HsbaChiTietRhm hsbaChiTietRhm) {
         getEm().remove(getEm().merge(hsbaChiTietRhm));
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public HsbaChiTietRhm findByHsbaCM(Integer hsbacmmaso) {

       List<HsbaChiTietRhm> lstHSBACTRhm =  em.createQuery("select object(o) from HsbaChiTietRhm as o where o.hsbacmMa.hsbacmMa = :hsbacmmaso order by o.hsbactrhmMa DESC").setParameter("hsbacmmaso", hsbacmmaso).getResultList();
       if (lstHSBACTRhm != null && lstHSBACTRhm.size() > 0){
           return lstHSBACTRhm.get(0);
       }else{
           return null;
       }
    }


}
