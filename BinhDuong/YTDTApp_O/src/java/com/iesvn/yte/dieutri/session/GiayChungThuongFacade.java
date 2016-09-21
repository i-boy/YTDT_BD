/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.GiayChungThuong;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quang
 */
@Stateless
public class GiayChungThuongFacade implements GiayChungThuongFacadeRemote, GiayChungThuongFacadeLocal {
    
     @PersistenceContext
    private EntityManager em;

    public void create(GiayChungThuong giayChungThuong) {
        em.persist(giayChungThuong);
    }

     public Integer create2(GiayChungThuong giayChungThuong) {
        em.persist(giayChungThuong);
        return giayChungThuong.getGctMa();
    }

    public void edit(GiayChungThuong giayChungThuong) {
        em.merge(giayChungThuong);
    }

    public void remove(GiayChungThuong giayChungThuong) {
        em.remove(em.merge(giayChungThuong));
    }

    public GiayChungThuong find(Object id) {
        return em.find(GiayChungThuong.class, id);
    }

    public List<GiayChungThuong> findAll() {
        return em.createQuery("select object(o) from GiayChungThuong as o").getResultList();
    }

    public GiayChungThuong getGiayChungThuong(Integer thamkham){
       System.out.println("call getGiayChungThuong");

       List<GiayChungThuong> lstGiayChungThuong =  em.createQuery("select object(o) from GiayChungThuong as o where o.gctThamkham.thamkhamMa = :thamkham ").setParameter("thamkham", thamkham).getResultList();
       System.out.println("lstgetGiayChungThuong:"+lstGiayChungThuong);


       if (lstGiayChungThuong != null && lstGiayChungThuong.size() > 0){
           return lstGiayChungThuong.get(0);
       }else{
           return null;
       }
     }


    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

 
}
