/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanThanhVien;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.HsbaMo;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.HsbaSan;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import java.util.Date;
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
public class HsbaChuyenMonFacade implements HsbaChuyenMonFacadeLocal, HsbaChuyenMonFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    DieuTriUtilFacadeLocal dieuTriUtil;

     @EJB
     HsbaChuyenVienFacadeLocal hsbaChuyenVienDelegate;
    public void create(HsbaChuyenMon hsbaChuyenMon) {
        em.persist(hsbaChuyenMon);
    }

    public void edit(HsbaChuyenMon hsbaChuyenMon) {
        em.merge(hsbaChuyenMon);
    }

    public void remove(HsbaChuyenMon hsbaChuyenMon) {
        em.remove(em.merge(hsbaChuyenMon));
    }

    public HsbaChuyenMon find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.HsbaChuyenMon.class, id);
    }

    public List<HsbaChuyenMon> findAll() {
        
        return em.createQuery("select object(o) from HsbaChuyenMon as o").getResultList();
    }

    /**
     * 
     * @param soVaoVien
     * @param maKhoa
     * @return
//     */
//    private String findBySoVaoVien_MaKhoa_temp(String soVaoVien, String maKhoa) {
//        String result = "";
//
//
//        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
//        DmKhoa dmKhoa = null;
//        if (dieuTriUtil == null) {
//            dieuTriUtil = new DieuTriUtilFacade();
//            dieuTriUtil.setEm(em);
//
//        }
//        dmKhoa = (DmKhoa) dieuTriUtil.findByMa(maKhoa, "DmKhoa", "dmkhoaMa");
//
//
//
//        System.out.println(dmKhoa.getDmkhoaMaso());
//        Query query = getEm().createNativeQuery("" +
//                " SELECT HSBACM_MA FROM HSBA_CHUYEN_MON CM, DM_KHOA K, HSBA H WHERE" +
//                " H.HSBA_SOVAOVIEN = CM.HSBA_SOVAOVIEN " +
//                " AND K.DMKHOA_MASO = CM.KHOA_MA" +
//                " AND H.HSBA_SOVAOVIEN LIKE '" + soVaoVien + "'" +
//                " AND K.DMKHOA_MASO = " + dmKhoa.getDmkhoaMaso());
//        List<Object> lstObj = query.getResultList();
//
//        if (lstObj != null && lstObj.size() > 0) {
//            for (Object obj : lstObj) {
//                if (result.equals("")) {
//                    result += obj.toString();
//                } else {
//                    result += "," + obj.toString();
//                }
//            }
//
//        } else {
//            return null;
//        }
//
//        return result;
//    }

    /**
     * 
     * @param soVaoVien
     * @return
     */
    public String xoaHSBAChuyenMon(String soVaoVien){
        
        HsbaMoFacade moFacade = new HsbaMoFacade();
        moFacade.setEm(em);
        List<HsbaMo> lstHsbaMo = moFacade.findListByHsbaMa(soVaoVien);
        if (lstHsbaMo != null){
            for (HsbaMo hsbamo : lstHsbaMo){
                em.remove(hsbamo);
            }
        } 
        
        HsbaSanFacade hsbaSanFacade = new HsbaSanFacade();
        hsbaSanFacade.setEm(em);
        List<HsbaSan> lstHsbaSan = hsbaSanFacade.findListByHsbaMa(soVaoVien);
         if (lstHsbaSan != null){
            for (HsbaSan hsbasan : lstHsbaSan){
                em.remove(hsbasan);
            }
        } 
        
        // xoa bien ban hoi chan thanh vien , bien ban hoi chan
        
        HsbaBienBanHoiChanFacade hsbaBBHCFacade = new HsbaBienBanHoiChanFacade();
        hsbaBBHCFacade.setEm(em);
        List<HsbaBienBanHoiChanThanhVien> lstBBHCTV = hsbaBBHCFacade.findBienBanHoiChanThanhVienByHsbaMa(soVaoVien);
         if (lstBBHCTV != null){
            for (HsbaBienBanHoiChanThanhVien bbhctv : lstBBHCTV){
                em.remove(bbhctv);
            }
        } 
        
        List<HsbaBienBanHoiChan> lstBBHC = hsbaBBHCFacade.findBienBanHoiChanByHsbaMa(soVaoVien);
        
        if (lstBBHC != null){
            for (HsbaBienBanHoiChan bbhc : lstBBHC){
                em.remove(bbhc);
            }
        } 
        
        
        // xoa ho so benh an chuyen mo
        
        HsbaChuyenMonFacade hsbaCMFacade = new HsbaChuyenMonFacade();
        hsbaCMFacade.setEm(em);
        List<HsbaChuyenMon> lstHsbaCM = hsbaCMFacade.findBySoVaoVien(soVaoVien);
        if (lstHsbaCM != null){
            for (HsbaChuyenMon hsbacm : lstHsbaCM){
                em.remove(hsbacm);
            }
        } 

        // 20101130 bao.ttc: tao lai 1 HSBACM mac dinh: khoa = khoa vao vien, gio vao khoa  = gio vao vien, lan = 1
        System.out.println("#### Da xoa toan bo HSBACM ! ####");
        HsbaFacade hsbaFaca = new HsbaFacade();
        hsbaFaca.setEm(em);
        Hsba hsba = hsbaFaca.find(soVaoVien);

        if(hsba != null){
            HsbaChuyenMon newhscm = new HsbaChuyenMon();
            newhscm.setHsbaSovaovien(hsba);
            newhscm.setKhoaMa(hsba.getHsbaKhoavaov());
            newhscm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
            newhscm.setHsbacmlan(1);
            em.persist(newhscm);

            hsba.setHsbaKhoadangdt(hsba.getHsbaKhoavaov());
            hsba.setHsbaKhoadangdtCm(hsba.getHsbaKhoavaov()); // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
            em.merge(hsba);

            System.out.println("#### Da xoa toan bo va tao lai 1 HSBACM mac dinh ! ####");
        }


        return soVaoVien;
    }
    
    public HsbaChuyenMon findBySoVaoVien_LastHSBACM(String soVaoVien) {

        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
        System.out.println(soVaoVien);

      
        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbacmMa DESC ").setParameter("soVaoVien", soVaoVien).getResultList();
        System.out.println(hsbaCM);
        if (hsbaCM != null && hsbaCM.size() > 0) {
            System.out.println("-------------------------");
            return hsbaCM.get(0);
        } else {
            return null;
        }
    }
    public HsbaChuyenMon findBySoVaoVien_MaKhoa(String soVaoVien, String maKhoa) {

        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
        System.out.println(soVaoVien);

        System.out.println(maKhoa);
        System.out.println(dieuTriUtil);
        DmKhoa dmKhoa = null;
      if (dieuTriUtil == null){
            dieuTriUtil = new DieuTriUtilFacade();
            dieuTriUtil.setEm(em);
            
        }
        dmKhoa = (DmKhoa)dieuTriUtil.findByMa(maKhoa, "DmKhoa", "dmkhoaMa");
      
        
        System.out.println(dmKhoa.getDmkhoaMaso());
        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien and o.khoaMa.dmkhoaMaso = :dmkhoaMaso order by o.hsbacmlan DESC ").setParameter("soVaoVien", soVaoVien).setParameter("dmkhoaMaso", dmKhoa.getDmkhoaMaso()).getResultList();
        System.out.println(hsbaCM);
        if (hsbaCM != null && hsbaCM.size() > 0) {
            System.out.println("-------------------------");
            return hsbaCM.get(0);
        } else {
            return null;
        }
    }
    
    
    public HsbaChuyenMon findBySoVaoVien_MaSoKhoa(String soVaoVien, Integer maSoKhoa) {

        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
        System.out.println(soVaoVien);

       
        System.out.println(dieuTriUtil);
            
      
        
       
        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien and o.khoaMa.dmkhoaMaso = :dmkhoaMaso  order by o.hsbacmlan DESC " ).setParameter("soVaoVien", soVaoVien).setParameter("dmkhoaMaso", maSoKhoa).getResultList();
        System.out.println(hsbaCM);
        if (hsbaCM != null && hsbaCM.size() > 0) {
            System.out.println("-------------------------");
            return hsbaCM.get(0);
        } else {
            return null;
        }
    }
    
      public List<HsbaChuyenMon> findByMaKhoa_ChuaCapNhat( String maKhoa) {

        System.out.println(maKhoa);
        if (dieuTriUtil == null){
            dieuTriUtil = new DieuTriUtilFacade();
            dieuTriUtil.setEm(em);
            
        }
        DmKhoa dmKhoa =  (DmKhoa)dieuTriUtil.findByMa(maKhoa, "DmKhoa", "dmkhoaMa");
        System.out.println(dmKhoa.getDmkhoaMaso());
        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbacmNgaygiorak is null and  o.hsbaSovaovien.hsbaNgaygiorav is null and  o.khoaMa.dmkhoaMaso = :dmkhoaMaso").setParameter("dmkhoaMaso", dmKhoa.getDmkhoaMaso()).getResultList();
        System.out.println(hsbaCM);
        
        return hsbaCM;
       
    }

//    public List<HsbaChuyenMon> findByMaKhoa_ChuaCapNhat(String maKhoa) {
//
//        System.out.println(maKhoa);
//
//        String DSMaso = findByMaKhoa_ChuaCapNhat_temp(maKhoa);
//
//        System.out.println("DSMaso:" + DSMaso);
//
//        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o , DmKhoa k where o.khoaMa = k and  o.hsbacmNgaygiorak is null and  o.hsbaSovaovien.hsbaNgaygiorav is null and  k.dmkhoaMaso = :dmkhoaMaso").setParameter("dmkhoaMaso", dmKhoa.getDmkhoaMaso()).getResultList();
//        System.out.println(hsbaCM);
//
//        return hsbaCM;
//
//    }

    //Manh added
//    private String findByMaKhoa_ChuaCapNhat_temp(String maKhoa) {
//
//        String result = "";
//        System.out.println(maKhoa);
//        if (dieuTriUtil == null) {
//            dieuTriUtil = new DieuTriUtilFacade();
//            dieuTriUtil.setEm(em);
//
//        }
//        DmKhoa dmKhoa = (DmKhoa) dieuTriUtil.findByMa(maKhoa, "DmKhoa", "dmkhoaMa");
//
//        Query query = getEm().createNativeQuery("" +
//                " SELECT HSBACM_MA FROM HSBA_CHUYEN_MON CM, DM_KHOA K WHERE" +
//                " K.DMKHOA_MASO = CM.KHOA_MA" +
//                " AND K.DMKHOA_MASO = " + dmKhoa.getDmkhoaMaso());
//        List<Object> lstObj = query.getResultList();
//
//        if (lstObj != null && lstObj.size() > 0) {
//            for (Object obj : lstObj) {
//                if (result.equals("")) {
//                    result += obj.toString();
//                } else {
//                    result += "," + obj.toString();
//                }
//            }
//
//        } else {
//            return null;
//        }
//
//        return result;
//
//    }
    // Manh end

    /*
     * 
     * 
     */
    public List<HsbaChuyenMon> findByMaLoaiKhoaTuNgayAndDenNgay(String maLoaiKhoa, Date tuNgay, Date denNgay) {

        List<HsbaChuyenMon> lstHsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.khoaMa.dtdmloaikhoaMa.dtdmloaikhoaMa = :maLoaiKhoa and  ( o.hsbacmNgaygiovaok >= :tuNgay  and o.hsbacmNgaygiovaok <= :denNgay ) or (  o.hsbacmNgaygiovaok <= :tuNgay and  ( o.hsbacmNgaygiorak <= :denNgay or o.hsbacmNgaygiorak is null) ) ").setParameter("maLoaiKhoa", maLoaiKhoa).setParameter("tuNgay", tuNgay).setParameter("denNgay", denNgay).getResultList();
        return lstHsbaCM;

    }

    /*
     * 
     * 
     */
    public int findTonDauKy(String maLoaiKhoa, Date tuNgay) {

        //List<HsbaChuyenMon> lstHsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.khoaMa.dtdmloaikhoaMa.dtdmloaikhoaMa = :maLoaiKhoa and o.hsbacmNgaygiovaok <= :tuNgay  and ( o.hsbacmNgaygiorak is null or  o.hsbacmNgaygiorak  > :tuNgay ) ").setParameter("maLoaiKhoa", maLoaiKhoa).setParameter("tuNgay", tuNgay).getResultList();
        List<HsbaChuyenMon> lstHsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.khoaMa.dtdmloaikhoaMa.dtdmloaikhoaMa = :maLoaiKhoa and o.hsbacmNgaygiovaok <= :tuNgay  and ( o.hsbaSovaovien.hsbaNgaygiorav is null or  o.hsbaSovaovien.hsbaNgaygiorav  > :tuNgay ) ").setParameter("maLoaiKhoa", maLoaiKhoa).setParameter("tuNgay", tuNgay).getResultList();
        if (lstHsbaCM == null) {
            return 0;
        }
        return lstHsbaCM.size();

    }
    //Manh added
    public List<HsbaChuyenMon> findBySoVaoVien(String soVaoVien) {
        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
        System.out.println("List<HsbaChuyenMon> co Sovaovien: " + soVaoVien);

        List<HsbaChuyenMon> hsbaCM = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbaSovaovien.hsbaSovaovien like :soVaoVien order by o.hsbacmMa ASC").setParameter("soVaoVien", soVaoVien).getResultList();
        System.out.println(hsbaCM);
        if (hsbaCM != null && hsbaCM.size() > 0) {
            System.out.println("------- hsbaCM != null && hsbaCM.size() > 0 --------");
            return hsbaCM;
        } else {
            return null;
        }
    }
    // Manh end
    /**
     * 
     *  In this case: rows of  benh nhan and hsba has existed.
     * 
     * 
     * */
    public String capNhatHoSoBenhAn(Hsba hsba, HsbaChuyenMon hsbaCM, HsbaChuyenVien hsbaCV, HsbaNop hsbaNop, BenhNhan benhNhan) {

        //em.merge(benhNhan);
        System.out.println("hsbaCM.getKetquaMa:"+hsbaCM.getKetquaMa());
        String soVaoVien = hsba.getHsbaSovaovien();
        if (dieuTriUtil == null) {
            dieuTriUtil = new DieuTriUtilFacade();
            dieuTriUtil.setEm(em);

        }

        // 20110728 bao.ttc: remove vi khong can thiet
        // DmKhoa dmKhoa = (DmKhoa) dieuTriUtil.findByMa(hsbaCM.getKhoaMa().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
        // hsbaCM.setKhoaMa(dmKhoa);

        // 20110711 bao.ttc: cap nhat khoa dang DT Chuyen mon cho nhung HSBA chua co gia tri nay
        if (hsba.getHsbaKhoadangdtCm() == null){
            hsba.setHsbaKhoadangdtCm(hsba.getHsbaKhoadangdt());
        }
        
        
        if (hsbaCM.getHsbaSovaovien() != null && hsbaCM.getHsbaSovaovien().getHsbaSovaovien().equals(soVaoVien)) {
            //HSBA CM has existed
            //update 
            System.out.println("update hsbaCM :"+hsbaCM.getHsbacmMa());
            getEm().merge(hsbaCM);
        } else {
            //new HSBA chuyen mon
            //new

            hsbaCM.setHsbaSovaovien(hsba);
            hsbaCM.setHsbacmlan(1);
            getEm().persist(hsbaCM);


            HsbaKhoa hsbaKhoa = new HsbaKhoa();
            hsbaKhoa.setHsbaSovaovien(hsba);
            hsbaKhoa.setKhoaMa(hsbaCM.getKhoaMa());
            hsbaKhoa.setHsbakhoaLan(1); // so lan da duoc tang nhu tren
            
            em.persist(hsbaKhoa);

        }

        if (hsbaCV != null){
            Utils.setInfor(hsbaCV, getEm());

            if (hsbaCV.getHsbaSovaovien() != null && hsbaCV.getHsbaSovaovien().getHsbaSovaovien().equals(soVaoVien)) {
                if (hsbaCV.getHsbacvChvienden() != null || (hsbaCV.getHsbacvLydochuyenv() != null)) {

                    getEm().merge(hsbaCV);
                }else{                
                    hsbaCV = hsbaChuyenVienDelegate.findBySoVaoVien(soVaoVien);
                    em.remove(hsbaCV);
                }
                     
            } else {
                //new HSBA chuyen mon
                //new
                if (hsbaCV.getHsbacvChvienden() != null || (hsbaCV.getHsbacvLydochuyenv() != null)) {
                    hsbaCV.setHsbaSovaovien(hsba);

                    getEm().persist(hsbaCV);
                }
            }
        }

        if (hsbaNop != null){
            Utils.setInfor(hsbaNop, getEm());
            if (hsbaNop.getHsbaSovaovien() != null && hsbaNop.getHsbaSovaovien().getHsbaSovaovien().equals(soVaoVien)) {
                if (hsbaNop.getHsbanopNgaygionop() != null) {

                    getEm().merge(hsbaNop);
                }

            } else {
                if (hsbaNop.getHsbanopNgaygionop() != null) {
                    hsbaNop.setHsbaSovaovien(hsba);

                    getEm().persist(hsbaNop);
                }
            }
        }

        //save another khoa
        if (hsbaCM.getHsbacmChuyenkhoa() != null) {
            
            System.out.print("++++++++++++++++: " + hsbaCM.getHsbacmChuyenkhoa());
            //hsba.setHsbaKhoadangdt(hsbaCM.getHsbacmChuyenkhoa());
            hsba.setHsbaKhoadangdtCm(hsbaCM.getHsbacmChuyenkhoa()); // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
            em.merge(hsba);
            
            HsbaChuyenMon hscm = findBySoVaoVien_MaSoKhoa(soVaoVien, hsbaCM.getHsbacmChuyenkhoa().getDmkhoaMaso());
            if (hscm == null) {
                HsbaChuyenMon newhscm = new HsbaChuyenMon();
                newhscm.setHsbaSovaovien(hsba);
                newhscm.setKhoaMa(hsbaCM.getHsbacmChuyenkhoa());
                //phuc.lc : Ngay ra cua khoa truoc la ngay vao cua khoa sau
                newhscm.setHsbacmNgaygiovaok(hsbaCM.getHsbacmNgaygiorak());
                newhscm.setHsbacmlan(1);
                em.persist(newhscm);
                
                // ----------------------------------------------------------------------------------------------
                // 20110708 bao.ttc: Chuyen khoa trong HSBACM chi tao HSBA Chuyen mon khac, khong tao HSBA Khoa
                // ----------------------------------------------------------------------------------------------
//                HsbaKhoaDelegate hsbakhoaDel = HsbaKhoaDelegate.getInstance();
//                HsbaKhoa hsbaKhoa = hsbakhoaDel.findBySoVaoVienAndKhoaMa(soVaoVien, hsbaCM.getHsbacmChuyenkhoa().getDmkhoaMa());
//                if (hsbaKhoa == null) {
//                    HsbaKhoa hsbaKhoaNew = new HsbaKhoa();
//                    hsbaKhoaNew.setHsbaSovaovien(hsba);
//                    hsbaKhoaNew.setKhoaMa(hsbaCM.getHsbacmChuyenkhoa());
//                    hsbaKhoaNew.setHsbakhoaLan(1);
//                    em.persist(hsbaKhoaNew);
//                }else{
//                    hsbaKhoa.setHsbaSovaovien(hsba);
//                    Integer soLanKhoa = hsbaKhoa.getHsbakhoaLan();
//                    if (soLanKhoa == null){
//                        soLanKhoa = 1; // phai la` 1 vi hsbacm da ton tai roi
//                    }
//                    soLanKhoa ++;
//                    hsbaKhoa.setHsbakhoaLan(soLanKhoa); // so lan da duoc tang nhu tren
//                    em.merge(hsbaKhoa);
//                }

            } else { // (hscm != null): da ton tai hsbaCM ung voi khoa can chuyen den
                
                Integer soLan = hscm.getHsbacmlan();
                if (soLan == null){
                    soLan = 1; // phai la` 1 vi hsbacm da ton tai roi
                }
                soLan ++;  // day la lan chuyen khac
                hscm.setHsbacmlan(soLan); // so lan da duoc tang len

                em.merge(hscm);

                // ----------------------------------------------------------------------------------------------
                // 20110708 bao.ttc: Chuyen khoa trong HSBACM chi tao HSBA Chuyen mon khac, khong tao HSBA Khoa
                // ----------------------------------------------------------------------------------------------
//                HsbaKhoaDelegate hsbakhoaDel = HsbaKhoaDelegate.getInstance();
//                HsbaKhoa hsbaKhoa = hsbakhoaDel.findBySoVaoVienAndKhoaMa(soVaoVien, hsbaCM.getHsbacmChuyenkhoa().getDmkhoaMa());
//                if (hsbaKhoa == null) {
//                    HsbaKhoa hsbaKhoaNew = new HsbaKhoa();
//                    hsbaKhoaNew.setHsbaSovaovien(hsba);
//                    hsbaKhoaNew.setKhoaMa(hsbaCM.getHsbacmChuyenkhoa());
//                    hsbaKhoaNew.setHsbakhoaLan(1); // so lan da duoc tang nhu tren
//                    em.persist(hsbaKhoaNew);
//                }else{
//                    hsbaKhoa.setHsbaSovaovien(hsba);
//                    Integer soLanKhoa = hsbaKhoa.getHsbakhoaLan();
//                    if (soLanKhoa == null){
//                        soLanKhoa = 1; // phai la` 1 vi hsbacm da ton tai roi
//                    }
//                    soLanKhoa ++;
//                    hsbaKhoa.setHsbakhoaLan(soLanKhoa); // so lan da duoc tang nhu tren
//                    em.merge(hsbaKhoa);
//                }
                
            }

        }
        
        getEm().merge(hsba);
        return hsba.getHsbaSovaovien();
    }

    /**
     * 
     * @param maBenhNhan
     * @return
     */
    public HsbaChuyenMon findByTiepDonMa(String maTiepDon) {
        maTiepDon = Utils.formatMa(em, maTiepDon);
        List<HsbaChuyenMon> lstHsbaChuyenMon = getEm().createQuery("select object(o) from HsbaChuyenMon as o where o.hsbaSovaovien.tiepdonMa.tiepdonMa like :maTiepDon  order by o.hsbacmlan DESC ").setParameter("maTiepDon", maTiepDon).getResultList();
        if (lstHsbaChuyenMon != null && lstHsbaChuyenMon.size() > 0) {

            return lstHsbaChuyenMon.get(0);
        } else {
            return null;
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


