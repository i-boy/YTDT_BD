/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HoanUng;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.utils.Utils;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HoanUngFacade implements HoanUngFacadeLocal, HoanUngFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @EJB
    private HsThtoanFacadeLocal hsThtoanFacadeLocal;
    
    public HoanUng create(HoanUng hoanUng) {
    	String hoanUngMa = com.iesvn.yte.dieutri.utils.Utils.maPhieuTamUng(em);
    	hoanUng.setHoanungMa(hoanUngMa);
        em.persist(hoanUng);
        
//        HsThtoan hsThtoan = hsThtoanFacadeLocal.findBySovaovien(hoanUng.getHsbaSovaovien().getHsbaSovaovien());
//		if (hsThtoan != null) {
//			if (hsThtoan.getHsthtoanHoanung() != null && hoanUng.getHoanungSotien() != null) {
//				hsThtoan.setHsthtoanHoanung(hsThtoan.getHsthtoanHoanung() + hoanUng.getHoanungSotien());
//			}
//			//this.hsThtoan.setHsthtoanBntra(this.hsThtoan.getHsthtoanBntra() - hoanUng.getHoanungSotien());
//			Utils.setInforForHsThToan(hsThtoan);	
//			em.merge(hsThtoan);
//			System.out.println("em.merge(hsThtoan) ... end");
//		}
        
        return hoanUng;
    }

    public void edit(HoanUng hoanUng) {
    	HoanUng o_hu = find(hoanUng.getHoanungMa());
    	Double o_Sotien = o_hu.getHoanungSotien();
        em.merge(hoanUng);
        /*
        System.out.println("old so tien = " + o_Sotien);
        System.out.println("old so tien1 = " + o_hu.getHoanungSotien());
        System.out.println("new so tien = " + hoanUng.getHoanungSotien());
        System.out.println("hsThtoanFacadeLocal=" + hsThtoanFacadeLocal);
        */
//        HsThtoan hsThtoan = hsThtoanFacadeLocal.findBySovaovien(hoanUng.getHsbaSovaovien().getHsbaSovaovien());
//        //System.out.println("hsThtoan=" + hsThtoan);
//		if (hsThtoan != null) {
//			if (hsThtoan.getHsthtoanHoanung() != null && hoanUng.getHoanungSotien() != null) {
//				hsThtoan.setHsthtoanHoanung(hsThtoan.getHsthtoanHoanung() + hoanUng.getHoanungSotien() - o_Sotien);
//			}
//			//this.hsThtoan.setHsthtoanBntra(this.hsThtoan.getHsthtoanBntra() - hoanUng.getHoanungSotien());
//			Utils.setInforForHsThToan(hsThtoan);	
//			em.merge(hsThtoan);
//			//System.out.println("em.merge(hsThtoan) ... end");
//		}
    }

    public void remove(HoanUng hoanUng) {
        em.remove(em.merge(hoanUng));
    }

    public HoanUng find(Object id) {
    	String ma = Utils.formatMaPhieu(id + "");
        return em.find(com.iesvn.yte.dieutri.entity.HoanUng.class, ma);
    }

    public List<HoanUng> findAll() {
        return em.createQuery("select object(o) from HoanUng as o").getResultList();
    }
    
     public Double getTongHoanUng(String hsbaSovaovien){
        hsbaSovaovien = Utils.formatMa(em, hsbaSovaovien);
        
        Double ketqua = new Double(0);
        Query q = em.createQuery("Select c from HoanUng c Where c.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien");
        q.setParameter("hsbaSovaovien", hsbaSovaovien);
        List<HoanUng> lstHoanUng =  q.getResultList();
        if (lstHoanUng != null && lstHoanUng.size() > 0){
            for (HoanUng hoanUng: lstHoanUng){
                Double sotien = hoanUng.getHoanungSotien();
                if (sotien == null){
                    sotien = new Double(0);
                }
                ketqua += sotien;
            }
        }
        
        return ketqua;
    }

}


