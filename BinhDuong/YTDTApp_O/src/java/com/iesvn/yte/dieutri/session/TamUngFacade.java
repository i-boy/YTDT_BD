/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.TamUng;
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
public class TamUngFacade implements TamUngFacadeLocal, TamUngFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    /*
    @EJB
    private HsThtoanFacadeLocal hsThtoanFacadeLocal;
     */
    
    public TamUng create(TamUng tamUng) {
    	String tamUngMa = com.iesvn.yte.dieutri.utils.Utils.maPhieuTamUng(em);
    	tamUng.setTamungMa(tamUngMa);
        em.persist(tamUng);
        /*
        HsThtoan hsThtoan = hsThtoanFacadeLocal.findBySovaovien(tamUng.getHsbaSovaovien().getHsbaSovaovien());
		if (hsThtoan != null) {
			if (hsThtoan.getHsthtoanHoanung() != null && tamUng.getTamungSotien() != null) {
				hsThtoan.setHsthtoanHoanung(hsThtoan.getHsthtoanHoanung() + tamUng.getTamungSotien());
			}
			//this.hsThtoan.setHsthtoanBntra(this.hsThtoan.getHsthtoanBntra() - hoanUng.getTamungSotien());
			Utils.setInforForHsThToan(hsThtoan);	
			em.merge(hsThtoan);
			//System.out.println("em.merge(hsThtoan) ... end");
		}
		*/
        return tamUng;
    }

    public void edit(TamUng tamUng) {
    	//TamUng o_t = find(tamUng.getTamungMa());
    	//Double o_Sotien = o_t.getTamungSotien();
    	
        em.merge(tamUng);
        /*
        HsThtoan hsThtoan = hsThtoanFacadeLocal.findBySovaovien(tamUng.getHsbaSovaovien().getHsbaSovaovien());
        //System.out.println("hsThtoan=" + hsThtoan);
		if (hsThtoan != null) {
			if (hsThtoan.getHsthtoanHoanung() != null && tamUng.getTamungSotien() != null) {
				hsThtoan.setHsthtoanHoanung(hsThtoan.getHsthtoanHoanung() + tamUng.getTamungSotien() - o_Sotien);
			}
			//this.hsThtoan.setHsthtoanBntra(this.hsThtoan.getHsthtoanBntra() - hoanUng.getTamungSotien());
			Utils.setInforForHsThToan(hsThtoan);	
			em.merge(hsThtoan);
			//System.out.println("em.merge(hsThtoan) ... end");
		}
		*/
    }

    public void remove(TamUng tamUng) {
        em.remove(em.merge(tamUng));
    }

    public TamUng find(Object id) {
    	String ma = Utils.formatMaPhieu(id + "");
        return em.find(com.iesvn.yte.dieutri.entity.TamUng.class, ma);
    }

    public List<TamUng> findAll() {
        return em.createQuery("select object(o) from TamUng as o").getResultList();
    }

     public TamUng findByMaHsba(String maHsba) {
        maHsba = Utils.formatMa(em, maHsba);
        TamUng tamUng = null;
        Query q = getEm().createQuery("Select h From TamUng h Where h.hsbaSovaovien.hsbaSovaovien = :maHsba");
        q.setParameter("maHsba", maHsba);
        try {
            List<TamUng> listHsttk = q.getResultList();
            if (listHsttk != null && listHsttk.size() > 0) {
                tamUng = listHsttk.get(listHsttk.size() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tamUng;
    }
    
     public Double getTongTamUng(String hsbaSovaovien){
        hsbaSovaovien = Utils.formatMa(em, hsbaSovaovien);
        
        Double ketqua = new Double(0);
        Query q = em.createQuery("Select c from TamUng c Where c.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien");
        q.setParameter("hsbaSovaovien", hsbaSovaovien);
        List<TamUng> lstTamUng =  q.getResultList();
        if (lstTamUng != null && lstTamUng.size() > 0){
            for (TamUng tamUng: lstTamUng){
                Double sotien = tamUng.getTamungSotien();
                if (sotien == null){
                    sotien = new Double(0);
                }
                ketqua += sotien;
            }
        }
        
        return ketqua;
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public int countSolanTamUngByHsba(String hsbaSovaovien) {        
        hsbaSovaovien = Utils.formatMa(em, hsbaSovaovien);
        try {
            Query q = em.createQuery("Select c from TamUng c Where c.hsbaSovaovien.hsbaSovaovien = :hsbaSovaovien");
            q.setParameter("hsbaSovaovien", hsbaSovaovien);
            return q.getResultList().size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }        
    }
}


