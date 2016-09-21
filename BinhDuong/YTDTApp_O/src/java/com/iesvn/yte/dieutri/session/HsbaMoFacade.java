/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaMo;
import com.iesvn.yte.dieutri.utils.Utils;
import java.util.ArrayList;
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
public class HsbaMoFacade implements HsbaMoFacadeLocal, HsbaMoFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(HsbaMo hsbaMo) {
        getEm().persist(hsbaMo);
    }

    public void edit(HsbaMo hsbaMo) {
        getEm().merge(hsbaMo);
    }

    public void remove(HsbaMo hsbaMo) {
        getEm().remove(getEm().merge(hsbaMo));
    }

    public HsbaMo find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.HsbaMo.class, id);
    }

    public List<HsbaMo> findAll() {
        return getEm().createQuery("select object(o) from HsbaMo as o").getResultList();
    }
     public List<HsbaMo> findListByHsbaMa(String hsbaMa) {
         System.out.println("---find Hsbagct by findByHsbaMa"); 
        hsbaMa = Utils.formatMa(getEm(),hsbaMa);
        System.out.println("---ma giay (format): " + hsbaMa);
        try {
            Query query = getEm().createQuery("select object(o) from HsbaMo  as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :hsbaMa");
            query.setParameter("hsbaMa", hsbaMa);
            List<HsbaMo> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
     }
    public HsbaMo findByHsbaMa(String hsbaMa) {
        System.out.println("---find Hsbagct by findByHsbaMa"); 
        hsbaMa = Utils.formatMa(getEm(),hsbaMa);
        System.out.println("---ma giay (format): " + hsbaMa);
        try {
            Query query = getEm().createQuery("select object(o) from HsbaMo  as o where o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :hsbaMa");
            query.setParameter("hsbaMa", hsbaMa);
            List<HsbaMo> list = query.getResultList();
            if (list != null) {
                return (HsbaMo) list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
  public List<HsbaMo> findBySoSoVaoVienVaKhoaMa(String soVaoVien, String khoaMa) {


        soVaoVien = Utils.formatMa(getEm(),soVaoVien);
        System.out.println(soVaoVien);


        System.out.print("soVaoVien:" + soVaoVien);
        System.out.print("khoaMa:" + khoaMa);

       
        return getEm().createQuery("select object(o) from HsbaMo as o where o.hsbacmMa.khoaMa.dmkhoaMa = :khoaMa and o.hsbacmMa.hsbaSovaovien.hsbaSovaovien like :soVaoVien").setParameter("soVaoVien", soVaoVien).setParameter("khoaMa", khoaMa).getResultList();
    }

    /**
     * @atuthor thanhdo
     * @param lstKqMo
     * @return
     */
    public String capNhatKetQuaMo(List<HsbaMo> lstHsbaMo, String soVaoVien, String khoaMa) {

       System.out.println("lstHsbaMo:" + lstHsbaMo.size());

        java.util.ArrayList<Integer> listNew = new ArrayList<Integer>();
        
        
               
        for (HsbaMo hsbaMo : lstHsbaMo) {
            if (hsbaMo.getHsbamoMa() != null && !hsbaMo.getHsbamoMa().equals("")) {

                getEm().merge(hsbaMo);
                
            } else {

                HsbaChuyenMonFacade facade = new HsbaChuyenMonFacade();
                facade.setEm(getEm());
                HsbaChuyenMon hsbaCM = facade.findBySoVaoVien_MaKhoa(soVaoVien, khoaMa);
                hsbaMo.setHsbacmMa(hsbaCM);              
                getEm().persist(hsbaMo);

                listNew.add(hsbaMo.getHsbamoMa());
            }
        }
        if (soVaoVien != null && !soVaoVien.equals("") &&
                khoaMa != null && !khoaMa.equals("")) {
            List<HsbaMo> list = findBySoSoVaoVienVaKhoaMa(soVaoVien, khoaMa);
            if (list != null && list.size() > 0) {
                for (HsbaMo hsbaMo : list) {
                    if (!contain(lstHsbaMo, hsbaMo)) {
                        
                        
                        if (containNew(listNew, hsbaMo.getHsbamoMa())) {
                            continue;
                        }
                        
                        System.out.println("remove row:" + hsbaMo.getHsbamoMa());
                        getEm().remove(hsbaMo);
                    }
                }

            }

        }

        //thanh do add here to edit la thu thuat, la phau thuat in hsbacm
        
        List<HsbaMo> list = findBySoSoVaoVienVaKhoaMa(soVaoVien, khoaMa);
        Boolean bThuThuat = new Boolean(Boolean.FALSE);
        Boolean bPhauThuat = new Boolean(Boolean.FALSE);
        if (list != null && list.size() > 0) {
            for (HsbaMo hsbaMo : list) {
               
                if (hsbaMo.getHsbamoMamo() != null && hsbaMo.getHsbamoMamo().getDtdmphauthuatLoai1() != null ) {
                    
                    if (  hsbaMo.getHsbamoMamo().getDtdmphauthuatLoai1().getDtdmloaiptPhauthuat().booleanValue() == true) { // 1

                        bPhauThuat = new Boolean(Boolean.TRUE);
                    } 
                     if (  hsbaMo.getHsbamoMamo().getDtdmphauthuatLoai1().getDtdmloaiptThuthuat().booleanValue() == true) { // 1

                        bThuThuat = new Boolean(Boolean.TRUE);
                    }
                }                
            }
        }
        
        HsbaChuyenMonFacade hsbacmFacade = new HsbaChuyenMonFacade();
        hsbacmFacade.setEm(getEm());
        HsbaChuyenMon hsbacm = hsbacmFacade.findBySoVaoVien_MaKhoa(soVaoVien, khoaMa);
        if (hsbacm != null) {
            hsbacm.setHsbacmLaphauthuat(bPhauThuat);
            hsbacm.setHsbacmLathuthuat(bThuThuat);
            getEm().merge(hsbacm);
        }
        
        return "";
    }

    /**
     * @author thanhdo
     * @param listNew
     * @param iMa
     * @return
     */
    private boolean containNew(java.util.ArrayList<Integer> listNew, Integer iMa) {
        for (Integer i : listNew) {
            if (i != null && iMa != null && i.intValue() == iMa.intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param lstKqMo
     * @param kqMo
     * @return
     */
    private boolean contain(List<HsbaMo> lstHsbaMo, HsbaMo hsbaMo) {
        for (HsbaMo hs : lstHsbaMo) {
            if (hs != null && hs.getHsbamoMa() != null && hs.getHsbamoMa().intValue() == hsbaMo.getHsbamoMa().intValue()) {
                return true;
            }
        }
        return false;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}


