/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.TamUngKham;
import com.iesvn.yte.dieutri.utils.Utils;

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
public class TamUngKhamFacade implements TamUngKhamFacadeLocal, TamUngKhamFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    /*
    @EJB
    private HsThtoankFacadeLocal hsThtoankFacadeLocal;
    */

    public TamUngKham create(TamUngKham tamUngKham) {
    	String tamUngMa = com.iesvn.yte.dieutri.utils.Utils.maPhieuTamUng(em);
    	tamUngKham.setTamungkhamMa(tamUngMa);
        em.persist(tamUngKham);
        
        /*
        HsThtoank hsThtoank = hsThtoankFacadeLocal.findBytiepdonMa(tamUngKham.getTiepdonMa().getTiepdonMa());
		if (hsThtoank != null) {
			if (hsThtoank.getHsthtoankTamung() != null && tamUngKham.getTamungkhamSotien() != null) {
				hsThtoank.setHsthtoankTamung(hsThtoank.getHsthtoankTamung() + tamUngKham.getTamungkhamSotien());
			}
			//this.hsThtoank.setHsthtoankBntra(this.hsThtoank.getHsthtoankBntra() - tamUngKham.getTamungkhamSotien());
			Utils.setInforForHsThToan(hsThtoank);
			em.merge(hsThtoank);
		}
		*/
        return tamUngKham;
     }

    public void edit(TamUngKham tamUngKham) {
    	//TamUngKham o_t = find(tamUngKham.getTamungkhamMa());
    	//Double o_Sotien = o_t.getTamungkhamSotien();
    	
        em.merge(tamUngKham);
        
        /*
        HsThtoank hsThtoank = hsThtoankFacadeLocal.findBytiepdonMa(tamUngKham.getTiepdonMa().getTiepdonMa());
       // System.out.println("hsThtoank=" + hsThtoank);
		if (hsThtoank != null) {
			if (hsThtoank.getHsthtoankTamung() != null && tamUngKham.getTamungkhamSotien() != null) {
				hsThtoank.setHsthtoankTamung(hsThtoank.getHsthtoankTamung() + tamUngKham.getTamungkhamSotien() - o_Sotien);
			}
			//this.hsThtoank.setHsthtoankBntra(this.hsThtoank.getHsthtoankBntra() - tamUngKham.getTamungkhamSotien());
			Utils.setInforForHsThToan(hsThtoank);
			em.merge(hsThtoank);
			//System.out.println("em.merge(hsThtoan) ... end");
		}
		*/
    }

    public void remove(TamUngKham tamUngKham) {
        em.remove(em.merge(tamUngKham));
    }

    public TamUngKham find(Object id) {
    	String ma = Utils.formatMaPhieu(id + "");
        return em.find(com.iesvn.yte.dieutri.entity.TamUngKham.class, ma);
    }
    
    public Double getTongTamUng(String maTiepdon){
        maTiepdon = Utils.formatMa(em, maTiepdon);
        
        Double ketqua = new Double(0);
        Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon AND c.tamungkhamStatus Is Null");
        q.setParameter("maTiepdon", maTiepdon);
        List<TamUngKham> lstTamUngKham =  q.getResultList();
        if (lstTamUngKham != null && lstTamUngKham.size() > 0){
            for (TamUngKham tamUngKham: lstTamUngKham){
                Double sotien = tamUngKham.getTamungkhamSotien();
                if (sotien == null){
                    sotien = new Double(0);
                }
                ketqua += sotien;
            }
        }
        
        return ketqua;
    }

    public List<TamUngKham> getListTamUngChuaTT(String maTiepdon){
        maTiepdon = Utils.formatMa(em, maTiepdon);

        Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon AND c.tamungkhamStatus Is Null");
        q.setParameter("maTiepdon", maTiepdon);
        List<TamUngKham> lstTamUngKham =  q.getResultList();

        return lstTamUngKham;
    }

    public List<TamUngKham> findAll() {
        return em.createQuery("select object(o) from TamUngKham as o").getResultList();
    }
    
    public List<TamUngKham> getListTamUngDaTT(String maTiepdon){
        maTiepdon = Utils.formatMa(em, maTiepdon);

        Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon AND c.tamungkhamStatus Like :strTT");
        q.setParameter("maTiepdon", maTiepdon);
        q.setParameter("strTT", "TT");
        List<TamUngKham> lstTamUngKham =  q.getResultList();

        return lstTamUngKham;
    }
    
    public int countSolanTamUngByTiepdon(String maTiepdon){
        maTiepdon = Utils.formatMa(em, maTiepdon);

        Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon");
        q.setParameter("maTiepdon", maTiepdon);                
        return q.getResultList().size();
    }

}


