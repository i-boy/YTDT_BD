package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatGm;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatPtv;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuatTpk;
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
public class HsbaBienBanHoiChanPhauThuatFacade implements HsbaBienBanHoiChanPhauThuatFacadeRemote, HsbaBienBanHoiChanPhauThuatFacadeLocal {
    @PersistenceContext
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")




    public void create(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
        em.persist(hsbaBienBanHoiChanPhauThuat);
    }

    public void edit(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
        em.merge(hsbaBienBanHoiChanPhauThuat);
    }

    public void remove(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat) {
        em.remove(em.merge(hsbaBienBanHoiChanPhauThuat));
    }

    public HsbaBienBanHoiChanPhauThuat find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat.class, id);
    }

    public List<HsbaBienBanHoiChanPhauThuat> findAll() {
        return em.createQuery("select object(o) from HsbaBienBanHoiChanPhauThuat as o").getResultList();
    }

    public String insert(HsbaBienBanHoiChanPhauThuat obj){
        System.out.println("---insert bien ban hoi chan phau thuat");
        try{
            String ma = Utils.maBienBanHoiChanPhauThuat(em);
            System.out.println("---ma sinh ra: " + ma);
            obj.setHsbabbhcptMa(ma);
            em.persist(obj);
            System.out.println("---insert bien ban hoi chan phau thuat: success");
            return ma;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert bien ban hoi chan phau thuat: err");
            return "";
        }
    }

    public String update(HsbaBienBanHoiChanPhauThuat obj){
        System.out.println("---update bien ban hoi chan phau thuat");
        try{
            System.out.println("---ma update: " + obj.getHsbabbhcptMa());
            em.merge(obj);
            System.out.println("---update bien ban hoi chan phau thuat: success");
            return obj.getHsbabbhcptMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---update bien ban hoi chan phau thuat: err");
            return "";
        }
    }

    public HsbaBienBanHoiChanPhauThuat findByHsbabbhcptMa(String ma){
        System.out.println("---find HsbaBienBanHoiChanPhauThuat by HsbabbhcptMa");
        ma = Utils.formatMaPhieu(ma);
        System.out.println("---ma giay (format): " + ma);
        try {
            Query query = em.createQuery("select object(o) from HsbaBienBanHoiChanPhauThuat  as o where o.hsbabbhcptMa like :ma");
            query.setParameter("ma", ma);
            List<HsbaBienBanHoiChanPhauThuat> list = query.getResultList();
            if (list != null && list.size() > 0) {
                return (HsbaBienBanHoiChanPhauThuat) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<DtDmNhanVien> findBacsiptvByHsbabbhcptMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaBienBanHoiChanPhauThuatPtv as o inner join o.dtdmnhanvienMaso as nv where o.hsbabbhcptMaso.hsbabbhcptMa =?1 order by o.hsbabbhcptptvMaso desc").setParameter(1, ma).getResultList();
    }
    public List<DtDmNhanVien> findBacsigmByHsbabbhcptMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaBienBanHoiChanPhauThuatGm as o inner join o.dtdmnhanvienMaso as nv where o.hsbabbhcptMaso.hsbabbhcptMa =?1 order by o.hsbabbhcptgmMaso desc").setParameter(1, ma).getResultList();
    }
    public List<DtDmNhanVien> findBacsitpkByHsbabbhcptMa(String ma) {
    	ma = Utils.formatMaPhieu(ma);
        return em.createQuery("select object(nv) from HsbaBienBanHoiChanPhauThuatTpk as o inner join o.dtdmnhanvienMaso as nv where o.hsbabbhcptMaso.hsbabbhcptMa =?1 order by o.hsbabbhcpttpkMaso desc").setParameter(1, ma).getResultList();
    }

   


    //public HsbaBienBanHoiChanPhauThuat createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
//    public String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList) {
    public String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList,List<DtDmNhanVien> bacsitpkList) {
        try{
            String ma = Utils.maBienBanHoiChanPhauThuat(em);
            System.out.println("---ma sinh ra: " + ma);
            hsbaBienBanHoiChanPhauThuat.setHsbabbhcptMa(ma);
            em.persist(hsbaBienBanHoiChanPhauThuat);
            System.out.println("---insert bien ban hoi chan phau thuat: success");
            updateHsbaBienBanHoiChanPhauThuatBacsi(hsbaBienBanHoiChanPhauThuat, bacsiptvList, bacsigmList,bacsitpkList);
//            return hsbaBienBanHoiChanPhauThuat;
            return hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMa();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("---insert bien ban hoi chan phau thuat: err");
            return "";
        }
    }
//    public HsbaBienBanHoiChanPhauThuat editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
//    public String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList) {
    public String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList,List<DtDmNhanVien> bacsitpkList) {
    	em.merge(hsbaBienBanHoiChanPhauThuat);
        updateHsbaBienBanHoiChanPhauThuatBacsi(hsbaBienBanHoiChanPhauThuat, bacsiptvList, bacsigmList,bacsitpkList);
        return hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMa();
    }

    private void updateHsbaBienBanHoiChanPhauThuatBacsi(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList, List<DtDmNhanVien> bacsitpkList) {
	//System.out.println("thanhVienList=" + thanhVienList);
	if (bacsitpkList != null) {
		//System.out.println("thanhVienList.size()=" + thanhVienList.size());
		List<DtDmNhanVien> oldList = findBacsitpkByHsbabbhcptMa(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMa());
		//System.out.println("oldList=" + oldList.size());

		if (oldList.size() > 0) {
                    for (int i = oldList.size() - 1; i >= 0 ; i--) {
                        if (!bacsitpkList.contains(oldList.get(i))) {
                                em.remove(findHsbaBienBanHoiChanPhauThuatBacsitpkByHsbabbhcptMasoAndDtdmnhanvienMaso(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMaso(), oldList.get(i).getDtdmnhanvienMaso()));
                        }
                    }
		}
		//System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
		//System.out.println("newList.size()=" + thanhVienList.size());
		if (bacsitpkList.size() > 0) {
			HsbaBienBanHoiChanPhauThuatTpk hsbaBienBanHoiChanPhauThuatBacsiTpk = null;

			for (int i = bacsitpkList.size() - 1; i >= 0 ; i--) {
				//System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
				if (!oldList.contains(bacsitpkList.get(i))) {
					hsbaBienBanHoiChanPhauThuatBacsiTpk = new HsbaBienBanHoiChanPhauThuatTpk();
					hsbaBienBanHoiChanPhauThuatBacsiTpk.setDtdmnhanvienMaso(bacsitpkList.get(i));
					hsbaBienBanHoiChanPhauThuatBacsiTpk.setHsbabbhcptMaso(hsbaBienBanHoiChanPhauThuat);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
					em.persist(hsbaBienBanHoiChanPhauThuatBacsiTpk);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
				}
			}
		}

	}

        if (bacsigmList != null) {
		//System.out.println("thanhVienList.size()=" + thanhVienList.size());
		List<DtDmNhanVien> oldList = findBacsigmByHsbabbhcptMa(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMa());
		//System.out.println("oldList=" + oldList.size());

		if (oldList.size() > 0) {
                    for (int i = oldList.size() - 1; i >= 0 ; i--) {
                        if (!bacsigmList.contains(oldList.get(i))) {
                                em.remove(findHsbaBienBanHoiChanPhauThuatBacsigmByHsbabbhcptMasoAndDtdmnhanvienMaso(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMaso(), oldList.get(i).getDtdmnhanvienMaso()));
                        }
                    }
		}
		//System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
		//System.out.println("newList.size()=" + thanhVienList.size());
		if (bacsigmList.size() > 0) {
			HsbaBienBanHoiChanPhauThuatGm hsbaBienBanHoiChanPhauThuatBacsiGm = null;

			for (int i = bacsigmList.size() - 1; i >= 0 ; i--) {
				//System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
				if (!oldList.contains(bacsigmList.get(i))) {
					hsbaBienBanHoiChanPhauThuatBacsiGm = new HsbaBienBanHoiChanPhauThuatGm();
					hsbaBienBanHoiChanPhauThuatBacsiGm.setDtdmnhanvienMaso(bacsigmList.get(i));
					hsbaBienBanHoiChanPhauThuatBacsiGm.setHsbabbhcptMaso(hsbaBienBanHoiChanPhauThuat);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
					em.persist(hsbaBienBanHoiChanPhauThuatBacsiGm);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
				}
			}
		}

	}

        if (bacsiptvList != null) {
		//System.out.println("thanhVienList.size()=" + thanhVienList.size());
		List<DtDmNhanVien> oldList = findBacsiptvByHsbabbhcptMa(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMa());
		//System.out.println("oldList=" + oldList.size());

		if (oldList.size() > 0) {
                    for (int i = oldList.size() - 1; i >= 0 ; i--) {
                        if (!bacsiptvList.contains(oldList.get(i))) {
                                em.remove(findHsbaBienBanHoiChanPhauThuatBacsiptvByHsbabbhcptMasoAndDtdmnhanvienMaso(hsbaBienBanHoiChanPhauThuat.getHsbabbhcptMaso(), oldList.get(i).getDtdmnhanvienMaso()));
                        }
                    }
		}
		//System.out.println("hsbaBienBanHoiChan.getThanhVienList().size()=" + thanhVienList.size());
		//System.out.println("newList.size()=" + thanhVienList.size());
		if (bacsiptvList.size() > 0) {
			HsbaBienBanHoiChanPhauThuatPtv hsbaBienBanHoiChanPhauThuatBacsiPtv = null;

			for (int i = bacsiptvList.size() - 1; i >= 0 ; i--) {
				//System.out.println("oldList.contains(newList.get(i))=" + oldList.contains(thanhVienList.get(i)));
				if (!oldList.contains(bacsiptvList.get(i))) {
					hsbaBienBanHoiChanPhauThuatBacsiPtv = new HsbaBienBanHoiChanPhauThuatPtv();
					hsbaBienBanHoiChanPhauThuatBacsiPtv.setDtdmnhanvienMaso(bacsiptvList.get(i));
					hsbaBienBanHoiChanPhauThuatBacsiPtv.setHsbabbhcptMaso(hsbaBienBanHoiChanPhauThuat);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)...");
					em.persist(hsbaBienBanHoiChanPhauThuatBacsiPtv);
					//System.out.println("em.persist(hsbaBienBanHoiChanThanhVien)... end");
				}
			}
		}

	}
    }

    public Object findHsbaBienBanHoiChanPhauThuatBacsitpkByHsbabbhcptMasoAndDtdmnhanvienMaso(Integer hsbabbhcptMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaBienBanHoiChanPhauThuatTpk as o where o.hsbabbhcptMaso.hsbabbhcptMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2")
        	.setParameter(1, hsbabbhcptMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
    public Object findHsbaBienBanHoiChanPhauThuatBacsigmByHsbabbhcptMasoAndDtdmnhanvienMaso(Integer hsbabbhcptMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaBienBanHoiChanPhauThuatGm as o where o.hsbabbhcptMaso.hsbabbhcptMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2")
        	.setParameter(1, hsbabbhcptMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
    public Object findHsbaBienBanHoiChanPhauThuatBacsiptvByHsbabbhcptMasoAndDtdmnhanvienMaso(Integer hsbabbhcptMaso, Integer dtdmnhanvienMaso) {
        return em.createQuery("select object(o) from HsbaBienBanHoiChanPhauThuatPtv as o where o.hsbabbhcptMaso.hsbabbhcptMaso =?1 and o.dtdmnhanvienMaso.dtdmnhanvienMaso = ?2")
        	.setParameter(1, hsbabbhcptMaso).setParameter(2, dtdmnhanvienMaso).getSingleResult();
    }
}
