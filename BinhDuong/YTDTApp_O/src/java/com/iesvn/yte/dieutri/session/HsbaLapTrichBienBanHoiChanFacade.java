/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChanBacsi;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author quang
 */
@Stateless
public class HsbaLapTrichBienBanHoiChanFacade implements HsbaLapTrichBienBanHoiChanFacadeRemote, HsbaLapTrichBienBanHoiChanFacadeLocal {
    @PersistenceContext
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")




    public void create(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
        em.persist(hsbaLapTrichBienBanHoiChan);
    }

    public void edit(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
        em.merge(hsbaLapTrichBienBanHoiChan);
    }

    public void remove(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan) {
        em.remove(em.merge(hsbaLapTrichBienBanHoiChan));
    }

    public HsbaLapTrichBienBanHoiChan find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan.class, id);
    }

    public List<HsbaLapTrichBienBanHoiChan> findAll() {
        return em.createQuery("select object(o) from HsbaLapTrichBienBanHoiChan as o").getResultList();
    }

    public String insert(HsbaLapTrichBienBanHoiChan obj){
        System.out.println("---insert lap trich bien ban hoi chan");
        try{
            String ma = Utils.maLapTrichBienBanHoiChan(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbaltbbhcMa(ma);
            em.persist(obj);
            System.out.println("---insert lap trich bien ban hoi chan: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert lap trich bien ban hoi chan: err");
            return "";
        }
    }

    public String update(HsbaLapTrichBienBanHoiChan obj){
        System.out.println("---update lap trich bien ban hoi chan");
        try{
            System.out.println("---ma update: " + obj.getHsbaltbbhcMa());
            em.merge(obj);
            System.out.println("---update lap trich bien ban hoi chan: success");
            return obj.getHsbaltbbhcMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update lap trich bien ban hoi chan: err");
            return "";
        }
    }

    public HsbaLapTrichBienBanHoiChan findByHsbaltbbhcMa(String ma){
        System.out.println("---find HsbaLapTrichBienBanHoiChan by HsbaltbbhcMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaLapTrichBienBanHoiChan  as o where o.hsbaltbbhcMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaLapTrichBienBanHoiChan> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaLapTrichBienBanHoiChan) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<DtDmNhanVien> findBacsiByHsbaltbbhcMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaLapTrichBienBanHoiChanBacsi as o inner join o.dtdmnhanvienMaso as nv where o.hsbaltbbhcMaso.hsbaltbbhcMa =?1 order by o.hsbaltbbhcbsMaso desc").setParameter(1, ma).getResultList();
    }

    public List<DtDmNhanVien> findBacsigmByHsbaltbbhcMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaLapTrichBienBanHoiChanBacsigm as o inner join o.dtdmnhanvienMaso as nv where o.hsbaltbbhcMaso.hsbaltbbhcMa =?1").setParameter(1, ma).getResultList();
    }


    public String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
//    public String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
        try{
            String ma = Utils.maLapTrichBienBanHoiChan(em);
            System.out.println("---ma sinh ra: " + ma);
            hsbaLapTrichBienBanHoiChan.setHsbaltbbhcMa(ma);
            em.persist(hsbaLapTrichBienBanHoiChan);
            System.out.println("---insert lap trich bien ban hoi chan: success");
//            updateHsbaLapTrichBienBanHoiChanBacsi(hsbaLapTrichBienBanHoiChan, bacsiList, bacsigmList);
            updateHsbaLapTrichBienBanHoiChanBacsi(hsbaLapTrichBienBanHoiChan, bacsiList);
//            return hsbaLapTrichBienBanHoiChan;
            return hsbaLapTrichBienBanHoiChan.getHsbaltbbhcMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert lap trich bien ban hoi chan: err");
            return "";
        }
    }
//    public HsbaLapTrichBienBanHoiChan editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
    public String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
//    public String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
    	em.merge(hsbaLapTrichBienBanHoiChan);
        updateHsbaLapTrichBienBanHoiChanBacsi(hsbaLapTrichBienBanHoiChan, bacsiList);
//        updateHsbaLapTrichBienBanHoiChanBacsi(hsbaLapTrichBienBanHoiChan, bacsiList, bacsigmList);
        return hsbaLapTrichBienBanHoiChan.getHsbaltbbhcMa();
    }

//    private void updateHsbaLapTrichBienBanHoiChanBacsi(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
    private void updateHsbaLapTrichBienBanHoiChanBacsi(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList) {
	//System.out.println("thanhVienList=" + thanhVienList);
	if (bacsiList != null) {
		//System.out.println("thanhVienList.size()=" + thanhVienList.size());
		List<DtDmNhanVien> oldList = findBacsiByHsbaltbbhcMa(hsbaLapTrichBienBanHoiChan.getHsbaltbbhcMa());
		//System.out.println("oldList=" + oldList.size());

		if (oldList.size() > 0) {
			for (int i = oldList.size() - 1; i >= 0 ; i--) {
	    		if (!bacsiList.contains(oldList.get(i))) {
	    			em.remove(findHsbaLapTrichBienBanHoiChanBacsiByHsbaltbbhcMasoAndDtdmnhanvienMaso(hsbaLapTrichBienBanHoiChan.getHsbaltbbhcMaso(), oldList.get(i).getDtdmnhanvienMaso()));
	    		}

	        }
		}
		//System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
		//System.out.println("newList.size()=" + thanhVienList.size());
		if (bacsiList.size() > 0) {
			HsbaLapTrichBienBanHoiChanBacsi hsbaLapTrichBienBanHoiChanBacsi = null;

			for (int i = bacsiList.size() - 1; i >= 0 ; i--) {
				//System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
				if (!oldList.contains(bacsiList.get(i))) {
					hsbaLapTrichBienBanHoiChanBacsi = new HsbaLapTrichBienBanHoiChanBacsi();
					hsbaLapTrichBienBanHoiChanBacsi.setDtdmnhanvienMaso(bacsiList.get(i));
					hsbaLapTrichBienBanHoiChanBacsi.setHsbaltbbhcMaso(hsbaLapTrichBienBanHoiChan);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
					em.persist(hsbaLapTrichBienBanHoiChanBacsi);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
				}
			}
		}
	}
    }

    public Object findHsbaLapTrichBienBanHoiChanBacsiByHsbaltbbhcMasoAndDtdmnhanvienMaso(Integer hsbaltbbhcMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaLapTrichBienBanHoiChanBacsi as o where o.hsbaltbbhcMaso.hsbaltbbhcMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2")
        	.setParameter(1, hsbaltbbhcMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
}
