/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.MienGiam;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class MienGiamFacade implements MienGiamFacadeLocal, MienGiamFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public MienGiam create(MienGiam mienGiam) {
    	String maPhieuMienGiam = com.iesvn.yte.dieutri.utils.Utils.maPhieuMienGiam(em); 
    	mienGiam.setMiengiamMaphieu(maPhieuMienGiam); 	//Cho ma tu tang
        em.persist(mienGiam);
        return mienGiam;
    }

    public void edit(MienGiam mienGiam) {
        em.merge(mienGiam);
    }

    public void remove(MienGiam mienGiam) {
        em.remove(em.merge(mienGiam));
    }

    public MienGiam find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.MienGiam.class, id);
    }

    public List<MienGiam> findAll() {
        return em.createQuery("select object(o) from MienGiam as o").getResultList();
    }
    
    public List<MienGiam> getBySoVaoVien(String soVaoVien) {
        
       Query q = em.createQuery("select object(o) from MienGiam as o where o.hsbaSovaovien.hsbaSovaovien = :soVaoVien");
       q.setParameter("soVaoVien", soVaoVien);
       List<MienGiam> list = q.getResultList();
       return  list;
    }

}


