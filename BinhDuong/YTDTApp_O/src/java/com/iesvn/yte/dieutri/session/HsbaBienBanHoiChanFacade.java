/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanThanhVien;
import com.iesvn.yte.dieutri.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaBienBanHoiChanFacade implements HsbaBienBanHoiChanFacadeLocal, HsbaBienBanHoiChanFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
        getEm().persist(hsbaBienBanHoiChan);
    }

    public HsbaBienBanHoiChan createHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList) {
    	String hsbabbhcMa = com.iesvn.yte.dieutri.utils.Utils.maPhieuBienBanHoiChan(getEm());
    	hsbaBienBanHoiChan.setHsbabbhcMa(hsbabbhcMa);
    	
        getEm().persist(hsbaBienBanHoiChan);
        
        updateHsbaBienBanHoiChanThanhVien(hsbaBienBanHoiChan, thanhVienList);
        return hsbaBienBanHoiChan;
    }
    public HsbaBienBanHoiChan editHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList) {
    	getEm().merge(hsbaBienBanHoiChan);
        
        updateHsbaBienBanHoiChanThanhVien(hsbaBienBanHoiChan, thanhVienList);
        return hsbaBienBanHoiChan;
    }

private void updateHsbaBienBanHoiChanThanhVien(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList) {
	//System.out.println("thanhVienList=" + thanhVienList);
	if (thanhVienList != null) {
		//System.out.println("thanhVienList.size()=" + thanhVienList.size());
		List<DtDmNhanVien> oldList = findThanhVienByHsbabbhcMa(hsbaBienBanHoiChan.getHsbabbhcMa());
		//System.out.println("oldList=" + oldList.size());
		
		if (oldList.size() > 0) {
			for (int i = oldList.size() - 1; i >= 0 ; i--) {
	    		if (!thanhVienList.contains(oldList.get(i))) {
	    			getEm().remove(findHsbaBienBanHoiChanThanhVienByHsbabbhcMasoAndDtdmnhanvienMaso(hsbaBienBanHoiChan.getHsbabbhcMaso(), oldList.get(i).getDtdmnhanvienMaso()));
	    		}
	    		
	        }	
		}
		//System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
		//System.out.println("newList.size()=" + thanhVienList.size());
		if (thanhVienList.size() > 0) {
			HsbaBienBanHoiChanThanhVien hsbaBienBanHoiChanThanhVien = null;
			
			for (int i = thanhVienList.size() - 1; i >= 0 ; i--) {
				//System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
				if (!oldList.contains(thanhVienList.get(i))) {
					hsbaBienBanHoiChanThanhVien = new HsbaBienBanHoiChanThanhVien();
					hsbaBienBanHoiChanThanhVien.setDtdmnhanvienMaso(thanhVienList.get(i));
					hsbaBienBanHoiChanThanhVien.setHsbabbhcMaso(hsbaBienBanHoiChan);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
					getEm().persist(hsbaBienBanHoiChanThanhVien);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
				}
			}
		}	
	    
	}
}
    
    
    public void edit(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
        getEm().merge(hsbaBienBanHoiChan);
    }

    public void remove(HsbaBienBanHoiChan hsbaBienBanHoiChan) {
        getEm().remove(getEm().merge(hsbaBienBanHoiChan));
    }

    public HsbaBienBanHoiChan find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan.class, id);
    }

    public List<HsbaBienBanHoiChan> findAll() {
        return getEm().createQuery("select object(o) from HsbaBienBanHoiChan as o").getResultList();
    }
    public List<DtDmNhanVien> findThanhVienByHsbabbhcMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return getEm().createQuery("select object(nv) from HsbaBienBanHoiChanThanhVien as o inner join o.dtdmnhanvienMaso as nv where o.hsbabbhcMaso.hsbabbhcMa =?1").setParameter(1, ma).getResultList();
    }
    public Object findHsbaBienBanHoiChanThanhVienByHsbabbhcMasoAndDtdmnhanvienMaso(Integer hsbabbhcMaso, Integer dtdmnhanvienMaso) {
        return getEm().createQuery("select object(o) from HsbaBienBanHoiChanThanhVien as o where o.hsbabbhcMaso.hsbabbhcMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2")
        	.setParameter(1, hsbabbhcMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
    
    public List<HsbaBienBanHoiChanThanhVien> findBienBanHoiChanThanhVienByHsbaMa(String soVaoVien) {
    	soVaoVien = Utils.formatMaPhieu(soVaoVien);
        return getEm().createQuery("select object(o) from HsbaBienBanHoiChanThanhVien as o where o.hsbabbhcMaso.hsbacmMa.hsbaSovaovien.hsbaSovaovien =?1").setParameter(1, soVaoVien).getResultList();
    }
    
     public List<HsbaBienBanHoiChan> findBienBanHoiChanByHsbaMa(String soVaoVien) {
    	soVaoVien = Utils.formatMaPhieu(soVaoVien);
        return getEm().createQuery("select object(o) from HsbaBienBanHoiChan as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien =?1").setParameter(1, soVaoVien).getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


