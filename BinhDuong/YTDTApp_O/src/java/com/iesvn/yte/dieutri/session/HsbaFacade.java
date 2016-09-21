/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.*;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmTang;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class HsbaFacade implements HsbaFacadeLocal, HsbaFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;



    public void create(Hsba hsba) {
        em.persist(hsba);
    }

    public void edit(Hsba hsba) {
        em.merge(hsba);
    }

    public void remove(Hsba hsba) {
        em.remove(em.merge(hsba));
    }

    public Hsba find(Object id) {
        String soVaoVien = (String) id;

        soVaoVien = Utils.formatMa(getEm(), soVaoVien);
        System.out.println(soVaoVien);

        return getEm().find(com.iesvn.yte.dieutri.entity.Hsba.class, soVaoVien);
    }

    public List<Hsba> findAll() {
        return em.createQuery("select object(o) from Hsba as o").getResultList();
    }
    public List<Hsba> findBySoVaoVienHoTenNgayGioVaoVien(
            String soVaoVien,
            String hoTen,
            Date tuNgay,
            Date denNgay
            ) {
        if (soVaoVien == null || soVaoVien.equals("")){
            soVaoVien = null;
        }else{
            soVaoVien = Utils.formatMa(em, soVaoVien);
        }
        if (hoTen == null || hoTen.equals("")){
            hoTen = null;
        }else{
            hoTen = "%" + hoTen + "%";
        }


        return em.createQuery("select object(o) from Hsba as o" +
                " where ( :soVaoVien is null or o.hsbaSovaovien like :soVaoVien )" +
                " and ( :hoTen is null or lower(o.benhnhanMa.benhnhanHoten) like concat('',lower(:hoTen)) )" +
                " and (to_date(o.hsbaNgaygiovaov) >= :tuNgay)" +
                " and (to_date(o.hsbaNgaygiovaov) <= :denNgay)" +
                " order by o.benhnhanMa.benhnhanHoten").setParameter("soVaoVien", soVaoVien).
                setParameter("hoTen", hoTen).
                setParameter("tuNgay", tuNgay).
                setParameter("denNgay", denNgay).
                getResultList();
    }
    public List<Hsba> findByBenhNhan(String maBenhNhan) {

        maBenhNhan = Utils.formatMa(em, maBenhNhan);
        return getEm().createQuery("select object(o) from Hsba as o where o.benhnhanMa like :maBenhNhan order by o.hsbaSovaovien DESC").setParameter("maBenhNhan", maBenhNhan).getResultList();
    }

    public Hsba findByHsbaAndKhoaDangDieuTri(String sovaovien, String makhoa) {

        sovaovien = Utils.formatMa(em, sovaovien);

        List<Hsba> hsba = getEm().createQuery("select object(o) from Hsba as o where o.hsbaSovaovien like :hsbaSovaovien and o.hsbaKhoadangdt.dmkhoaMa like :dmkhoaMa ").setParameter("hsbaSovaovien", sovaovien).setParameter("dmkhoaMa", makhoa).getResultList();
        if (hsba != null && hsba.size() > 0) {
            return hsba.get(0);
        } else {
            return null;
        }
    }

    // khoa dang dieu tri and chua co' ngay gio ra vien, ra khoa
    // 20110711 bao.ttc: Khoa dang dieu tri Chuyen Mon (de cap nhat HSBC Chuyen Mon)
    // Phan biet voi khoa dang DT that su trong HSBA_KHOA dung cho: vien phi, thuoc, CLS
    /*
     * Thanh add 29/09/2011
     * Oracle: syntax ok
     */
    public List<Hsba> findKhoaDangDieuTri(String makhoa) {

        int masoKhoa = java.lang.Integer.parseInt(makhoa);
        //System.out.println("findKhoaDangDieuTri: " + masoKhoa); // Truyen vao Ma so Khoa de search truc tiep

//        String sSQL = "select object(o) from Hsba as o" +
//            " where ( o.hsbaNgaygiorav is null and o.hsbaKhoadangdtCm is null and o.hsbaKhoadangdt.dmkhoaMa like :dmkhoaMa )" +
//            " or ( o.hsbaNgaygiorav is null and o.hsbaKhoadangdtCm is not null and o.hsbaKhoadangdtCm.dmkhoaMa like :dmkhoaMa )";
//        Query q = getEm().createQuery(sSQL);
//        q.setParameter("dmkhoaMa", makhoa);

        String sSQL = "select * from Hsba o" +
            " where ( o.HSBA_NGAYGIORAV is null )" +
            " and ( ( o.HSBA_KHOADANGDT_CM is null and o.HSBA_KHOADANGDT = :dmkhoaMaso )" +
            " or ( o.HSBA_KHOADANGDT_CM = :dmkhoaMaso ) )";
        Query q = getEm().createNativeQuery(sSQL, Hsba.class);
        q.setParameter("dmkhoaMaso", masoKhoa);
        List<Hsba> lstHsba = q.getResultList();

        //System.out.println("findKhoaDangDieuTri END: " + lstHsba);
        return lstHsba;
    }

    public Hsba findByBenhNhanLastHsba(String maBenhNhan) {
        maBenhNhan = Utils.formatMa(em, maBenhNhan);
        List<Hsba> hsba = getEm().createQuery("select object(o) from Hsba as o where o.benhnhanMa like :maBenhNhan order by o.hsbaSovaovien DESC limit 1").setParameter("maBenhNhan", maBenhNhan).getResultList();
        if (hsba != null && hsba.size() > 0) {

            return hsba.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param maBenhNhan
     * @return
     */
    public Hsba findByTiepDonMa(String maTiepDon) {
        maTiepDon = Utils.formatMa(em, maTiepDon);
        List<Hsba> hsba = getEm().createQuery("select object(o) from Hsba as o where o.tiepdonMa.tiepdonMa like :maTiepDon").setParameter("maTiepDon", maTiepDon).getResultList();
        if (hsba != null && hsba.size() > 0) {

            return hsba.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     *
     *
     *
     * */
    public String capNhatThongTinNhapVien(Hsba hsba,HsbaBhyt hsbaBhyt, BenhNhan benhNhan, DmTang tangChuyenDen) {
        System.out.println("----------------Begin: capNhatThongTinNhapVien-----------------------");
        try {

            String maTiepDon = hsba.getTiepdonMa();
            String soVaoVien = hsba.getHsbaSovaovien();
            String maBenhNhan = benhNhan.getBenhnhanMa();

            if (maBenhNhan == null || maBenhNhan.trim().equals("")) {
                maBenhNhan = Utils.getMaBenhNhan(getEm());
                benhNhan.setBenhnhanMa(maBenhNhan);
                Utils.setInfor(benhNhan, em);
                getEm().persist(benhNhan);
            } else {
                Utils.setInfor(benhNhan, em);
                getEm().merge(benhNhan);
            }

            // 20110224 bao.ttc: kiem tra Ma tiep don da co hay chua
            if (maTiepDon == null || maTiepDon.trim().equals("")) {

                maTiepDon = Utils.getMaTiepDon(em);
                TiepDon tiepdon = new TiepDon();
                tiepdon.setTiepdonMa(maTiepDon);
                tiepdon.setBenhnhanMa(benhNhan);
                //phuc.lc 26-10-2010
                tiepdon.setDoituongMa(hsba.getDoituongMa());
                tiepdon.setTiepdonNgaygio(hsba.getHsbaNgaygiovaov());
                //bao.ttc 28-10-2010
                if(hsbaBhyt != null) {
                    tiepdon.setKhoibhytMa(hsbaBhyt.getHsbabhytKhoibh());
                    tiepdon.setTinhbhytMa(hsbaBhyt.getHsbabhytTinhbh());
                    tiepdon.setTiepdonMacoquan(hsbaBhyt.getHsbabhytCoquanbh());
                    tiepdon.setTiepdonSothebh(hsbaBhyt.getHsbabhytSothebh());
                    tiepdon.setTiepdonTuyen(hsbaBhyt.getHsbabhytTuyen());
                    tiepdon.setKcbbhytMa(hsbaBhyt.getHsbabhytMakcb());
                    tiepdon.setTiepdonGiatri1(hsbaBhyt.getHsbabhytGiatri0());
                    tiepdon.setTiepdonGiatri2(hsbaBhyt.getHsbabhytGiatri1());
                    tiepdon.setTiepdonGiatri3(hsbaBhyt.getHsbabhytGiatri2());
                    tiepdon.setTiepdonGiatri4(hsbaBhyt.getHsbabhytGiatri3());
                    tiepdon.setTiepdonMoc1(hsbaBhyt.getHsbabhytMoc1());
                    tiepdon.setTiepdonMoc2(hsbaBhyt.getHsbabhytMoc2());
                    tiepdon.setTiepdonMoc3(hsbaBhyt.getHsbabhytMoc3());
                    tiepdon.setTiepdonCoGiayGioiThieu(hsbaBhyt.getHsbabhytCoGiayChuyenVien());
                }
                em.persist(tiepdon);

            } else {   // TH HSBA co luu Ma Tiepdon

                TiepDonFacade tiepdonFacade = new TiepDonFacade();
                tiepdonFacade.setEm(em);
                TiepDon td = tiepdonFacade.findByTiepdonMa(maTiepDon);

                if (td == null){   // Ko tim thay ma Tiep don da luu, set New
                    maTiepDon = Utils.getMaTiepDon(em);
                    TiepDon tiepdon = new TiepDon();
                    tiepdon.setTiepdonMa(maTiepDon);
                    tiepdon.setBenhnhanMa(benhNhan);
                    //phuc.lc 26-10-2010
                    tiepdon.setDoituongMa(hsba.getDoituongMa());
                    tiepdon.setTiepdonNgaygio(hsba.getHsbaNgaygiovaov());
                    //bao.ttc 28-10-2010
                    if(hsbaBhyt != null) {
                        tiepdon.setKhoibhytMa(hsbaBhyt.getHsbabhytKhoibh());
                        tiepdon.setTinhbhytMa(hsbaBhyt.getHsbabhytTinhbh());
                        tiepdon.setTiepdonMacoquan(hsbaBhyt.getHsbabhytCoquanbh());
                        tiepdon.setTiepdonSothebh(hsbaBhyt.getHsbabhytSothebh());
                        tiepdon.setTiepdonTuyen(hsbaBhyt.getHsbabhytTuyen());
                        tiepdon.setKcbbhytMa(hsbaBhyt.getHsbabhytMakcb());
                        tiepdon.setTiepdonGiatri1(hsbaBhyt.getHsbabhytGiatri0());
                        tiepdon.setTiepdonGiatri2(hsbaBhyt.getHsbabhytGiatri1());
                        tiepdon.setTiepdonGiatri3(hsbaBhyt.getHsbabhytGiatri2());
                        tiepdon.setTiepdonGiatri4(hsbaBhyt.getHsbabhytGiatri3());
                        tiepdon.setTiepdonMoc1(hsbaBhyt.getHsbabhytMoc1());
                        tiepdon.setTiepdonMoc2(hsbaBhyt.getHsbabhytMoc2());
                        tiepdon.setTiepdonMoc3(hsbaBhyt.getHsbabhytMoc3());
                        tiepdon.setTiepdonCoGiayGioiThieu(hsbaBhyt.getHsbabhytCoGiayChuyenVien());
                    }
                    em.persist(tiepdon);
                }   // Ko tim thay ma Tiep don da luu, set New
                else {   // Tim thay ma Tiep don da luu, edit lai thong tin
                     //phuc.lc : 01/11/2011 : Chi cap nhat cac tiep don duoc tao ra o noi tru (HSBA_TYPE = null),
                    // khong cap nhat cac truong hop tiep don chuyen tu ngoai tru vao noi tru
                    if(hsba.getHsbaType() == null) {
                        td.setBenhnhanMa(benhNhan);
                        //phuc.lc 26-10-2010
                        td.setDoituongMa(hsba.getDoituongMa());
                        td.setTiepdonNgaygio(hsba.getHsbaNgaygiovaov());
                        //bao.ttc 28-10-2010
                        if(hsbaBhyt != null) {
                            td.setKhoibhytMa(hsbaBhyt.getHsbabhytKhoibh());
                            td.setTinhbhytMa(hsbaBhyt.getHsbabhytTinhbh());
                            td.setTiepdonMacoquan(hsbaBhyt.getHsbabhytCoquanbh());
                            td.setTiepdonSothebh(hsbaBhyt.getHsbabhytSothebh());
                            td.setTiepdonTuyen(hsbaBhyt.getHsbabhytTuyen());
                            td.setKcbbhytMa(hsbaBhyt.getHsbabhytMakcb());
                            td.setTiepdonGiatri1(hsbaBhyt.getHsbabhytGiatri0());
                            td.setTiepdonGiatri2(hsbaBhyt.getHsbabhytGiatri1());
                            td.setTiepdonGiatri3(hsbaBhyt.getHsbabhytGiatri2());
                            td.setTiepdonGiatri4(hsbaBhyt.getHsbabhytGiatri3());
                            td.setTiepdonMoc1(hsbaBhyt.getHsbabhytMoc1());
                            td.setTiepdonMoc2(hsbaBhyt.getHsbabhytMoc2());
                            td.setTiepdonMoc3(hsbaBhyt.getHsbabhytMoc3());
                            td.setTiepdonCoGiayGioiThieu(hsbaBhyt.getHsbabhytCoGiayChuyenVien());
                        }
                        em.merge(td);
                    }   // Tim thay ma Tiep don da luu, edit lai thong tin
                }
            }


            if (hsba.getHsbaKhoadangdt() != null) {

                if (hsba.getHsbaKhoavaov() == null) {
                    hsba.setHsbaKhoavaov(hsba.getHsbaKhoadangdt());
                }

                // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
                //if (hsba.getHsbaKhoadangdtCm() == null) {
                //    hsba.setHsbaKhoadangdtCm(hsba.getHsbaKhoadangdt());
                //}
                
                // 20120206 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, thay doi trong truong hop chinh o form Cap nhat thong tin nhap vien
                hsba.setHsbaKhoadangdtCm(hsba.getHsbaKhoadangdt());
                
                // 20120315 bao.ttc: set Tang dang Dieu Tri
                if (tangChuyenDen == null) {
                    if (hsba.getHsbaKhoadangdt().getDmkhoaMaso() != null) {
                        DieuTriUtilFacade dieutriUtilFacade = new DieuTriUtilFacade();
                        dieutriUtilFacade.setEm(em);
                        tangChuyenDen = (DmTang) dieutriUtilFacade.findByDmTangDefault(hsba.getHsbaKhoadangdt().getDmkhoaMaso());
                        System.out.println("### Tang == null, tim tang default: " + tangChuyenDen );
                    }
                }
                hsba.setTangDangdt(tangChuyenDen);

            }

            hsba.setTiepdonMa(maTiepDon);
            hsba.setBenhnhanMa(benhNhan);
            hsba.setHsbaIsNoitru(true); // 20110519 bao.ttc: la BN noi tru (phan biet voi cac TH tao hsba tam thoi)

            if (soVaoVien == null || soVaoVien.trim().equals("")) {
                String soVaoVien_2 = Utils.getSoVaoVien(getEm(),2);
                hsba.setHsbaSovaovien(soVaoVien_2);
                getEm().persist(hsba);
            } else {
                getEm().merge(hsba);
            }

            //if (hsbaBhyt != null && !"TP".equals( hsba.getDoituongMa(true).getDmdoituongMa())) {
             //phuc.lc 04/10/2011 : chi tao ho so BA BHYT cho doi tuong bao hiem, khong tao cho doi tuong mien phi
           if (hsbaBhyt != null && "BH".equals( hsba.getDoituongMa(true).getDmdoituongMa())) {
               //phuc.lc 31/10/2011 : Set null trong truong hop soVaoVien = null de luu hsba_bhyt moi
                if (soVaoVien == null || soVaoVien.trim().equals("")) {
                    hsbaBhyt.setHsbabhytMa(null);
                }
                hsbaBhyt.setHsbaSovaovien(hsba);

                if (hsbaBhyt.getHsbabhytMa() == null) { // HSBA_BHYT not yet exist

                    Utils.setInfor(hsbaBhyt, em);
                    getEm().persist(hsbaBhyt);
                } else {
                    Utils.setInfor(hsbaBhyt, em);
                    getEm().merge(hsbaBhyt);
                }
            }else{
                HsbaBhytFacade hsbaFacade = new HsbaBhytFacade();
                hsbaFacade.setEm(em);
                List<HsbaBhyt> lstHsbaBHYT =  hsbaFacade.findBySoVaoVien(hsba.getHsbaSovaovien());
                if (lstHsbaBHYT != null){
                    for (HsbaBhyt hsbaBHYTTmp: lstHsbaBHYT){
                        em.remove(hsbaBHYTTmp);
                    }
                }
            }

            if (hsba.getHsbaKhoadangdt() != null) {

                // 20101123 bao.ttc: Truong hop dieu chinh thong tin hanh chinh BN thi phai search de khong tao moi HSBACM
                HsbaChuyenMonFacade hsbacmFacade = new HsbaChuyenMonFacade();
                hsbacmFacade.setEm(em);

                HsbaChuyenMon hscm = null;
                List<HsbaChuyenMon> listHscm = hsbacmFacade.findBySoVaoVien(hsba.getHsbaSovaovien());
                if (listHscm != null && listHscm.size() == 1) {
                    hscm = listHscm.get(0);                    
                } else {
                    hscm = hsbacmFacade.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt().getDmkhoaMa());
                }
                
                if (hscm == null) {
                    HsbaChuyenMon newhscm = new HsbaChuyenMon();
                    newhscm.setHsbaSovaovien(hsba);
                    newhscm.setKhoaMa(hsba.getHsbaKhoadangdt());
                    //phuc.lc : Ngay vao khoa chinh la ngay vao vien
                    newhscm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
                    newhscm.setHsbacmlan(1);
                    hsbacmFacade.create(newhscm);
                } else{
                    hscm.setKhoaMa(hsba.getHsbaKhoadangdt());
                    hscm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
                    hsbacmFacade.edit(hscm);
                    //System.out.println("Da co HSBACM tai khoa :" + hsba.getHsbaKhoadangdt().getDmkhoaMa());
                }


                HsbaKhoaFacade hsbaKhoaFacade = new HsbaKhoaFacade();
                hsbaKhoaFacade.setEm(em);

                HsbaKhoa hsbakhoa = null;

                // ------------------------------------------------------------
                // 20120416 bao.ttc: CODE cho phep thay doi Khoa - Tang trong TH chi co 1 hsbaKhoa ung voi Sovaovien
//                List<HsbaKhoa> listHsbaKhoa = hsbaKhoaFacade.findBySoVaoVien(hsba.getHsbaSovaovien());
//                if (listHsbaKhoa != null && listHsbaKhoa.size() == 1) {
//                    hsbakhoa = listHsbaKhoa.get(0);
//                } else {
//                    // 20120315 bao.ttc: tim hsbaKhoa theo: Sovaovien - Khoa - Tang de xem xet co tao moi hay khong
//                    hsbakhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt().getDmkhoaMa(), tangChuyenDen.getDmtangMaso());
//                }
//
//		if (hsbakhoa == null) {
//                    hsbakhoa = new HsbaKhoa();
//                    hsbakhoa.setHsbaSovaovien(hsba);
//                    hsbakhoa.setKhoaMa(hsba.getHsbaKhoadangdt());
//                    hsbakhoa.setHsbakhoaLan(1);
//                    if(tangChuyenDen != null){
//                        hsbakhoa.setDmtangMaso(tangChuyenDen);
//                    }
//                    em.persist(hsbakhoa);
//                } else {
//                    if (listHsbaKhoa != null && listHsbaKhoa.size() == 1) {
//                        // phuc.lc add code below
//                        // khi thay doi thoi tin chuyen khoa tren form cap nhat thong tin nhap vien thi chi cap nhat lai hsbakhoa (TH: chi moi ton tai 1 hsbaKhoa cho Sovaovien do)
//                        hsbakhoa.setKhoaMa(hsba.getHsbaKhoadangdt());
//                        hsbakhoa.setHsbakhoaLan(1);
//                        if(tangChuyenDen != null){
//                            hsbakhoa.setDmtangMaso(tangChuyenDen);
//                        }
//                        em.merge(hsbakhoa);
//                    }
//                }
                // END: 20120416 bao.ttc: CODE cho phep thay doi Khoa - Tang trong TH chi co 1 hsbaKhoa ung voi Sovaovien
                // ------------------------------------------------------------

                // 20120416 bao.ttc: CODE KHONG cho phep thay doi Khoa - Tang
                hsbakhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), hsba.getHsbaKhoadangdt().getDmkhoaMa(), tangChuyenDen.getDmtangMaso());
                if (hsbakhoa == null) {
                    hsbakhoa = new HsbaKhoa();
                    hsbakhoa.setHsbaSovaovien(hsba);
                    hsbakhoa.setKhoaMa(hsba.getHsbaKhoadangdt());
                    hsbakhoa.setHsbakhoaLan(1);
                    if(tangChuyenDen != null){
                        hsbakhoa.setDmtangMaso(tangChuyenDen);
                        System.out.println("### capNhatThongTinNhapVien: tao hsbaKhoa moi !!!");
                        em.persist(hsbakhoa);
                    }
                } else {
                    System.out.println("### capNhatThongTinNhapVien: hsbaKhoa da ton tai, khong can thay doi !!!");
                }
                // END: 20120416 bao.ttc: CODE KHONG cho phep thay doi Khoa - Tang

            }

        } catch (Exception ex) {
			context.setRollbackOnly();
            System.out.println("Error: " + ex.toString());
            ex.printStackTrace();
            return "error";
        }
        System.out.println("----------------End: capNhatThongTinNhapVien-----------------------");
        return hsba.getHsbaSovaovien();
    }

    public String capNhatThongTinHanhChinhBN(Hsba hsba,HsbaBhyt hsbaBhyt, BenhNhan benhNhan) {
        System.out.println("---------------- Begin: capNhatThongTinHanhChinhBN -------------------");
        try {

            String maTiepDon = hsba.getTiepdonMa();
            String soVaoVien = hsba.getHsbaSovaovien();
            String maBenhNhan = benhNhan.getBenhnhanMa();

            if ( soVaoVien == null || soVaoVien.trim().equals("")
                || maBenhNhan == null || maBenhNhan.trim().equals("")
                || maTiepDon == null || maTiepDon.trim().equals("")) {
                System.out.println("###### capNhatThongTinHanhChinhBN: soVaoVien OR maBenhNhan OR maTiepDon : NULL");
                return "error";
            }

            getEm().merge(benhNhan);

            TiepDonFacade tiepdonFacade = new TiepDonFacade();
            tiepdonFacade.setEm(em);
            TiepDon td = tiepdonFacade.findByTiepdonMa(maTiepDon);

            if (td == null){   // Ko tim thay ma Tiep don da luu, set New
                maTiepDon = Utils.getMaTiepDon(em);
                TiepDon tiepdon = new TiepDon();
                tiepdon.setTiepdonMa(maTiepDon);
                tiepdon.setBenhnhanMa(benhNhan);
                tiepdon.setDoituongMa(hsba.getDoituongMa());
                tiepdon.setTiepdonNgaygio(hsba.getHsbaNgaygiovaov());

                if(hsbaBhyt != null) {
                    tiepdon.setKhoibhytMa(hsbaBhyt.getHsbabhytKhoibh());
                    tiepdon.setTinhbhytMa(hsbaBhyt.getHsbabhytTinhbh());
                    tiepdon.setTiepdonMacoquan(hsbaBhyt.getHsbabhytCoquanbh());
                    tiepdon.setTiepdonSothebh(hsbaBhyt.getHsbabhytSothebh());
                    tiepdon.setTiepdonTuyen(hsbaBhyt.getHsbabhytTuyen());
                    tiepdon.setKcbbhytMa(hsbaBhyt.getHsbabhytMakcb());
                    tiepdon.setTiepdonGiatri1(hsbaBhyt.getHsbabhytGiatri0());
                    tiepdon.setTiepdonGiatri2(hsbaBhyt.getHsbabhytGiatri1());
                    tiepdon.setTiepdonGiatri3(hsbaBhyt.getHsbabhytGiatri2());
                    tiepdon.setTiepdonGiatri4(hsbaBhyt.getHsbabhytGiatri3());
                    tiepdon.setTiepdonMoc1(hsbaBhyt.getHsbabhytMoc1());
                    tiepdon.setTiepdonMoc2(hsbaBhyt.getHsbabhytMoc2());
                    tiepdon.setTiepdonMoc3(hsbaBhyt.getHsbabhytMoc3());
                    tiepdon.setTiepdonCoGiayGioiThieu(hsbaBhyt.getHsbabhytCoGiayChuyenVien());
                }
                em.persist(tiepdon);
            } else {   // Tim thay ma Tiep don da luu, edit lai thong tin
                 //phuc.lc : 01/11/2011 : Chi cap nhat cac tiep don duoc tao ra o noi tru (HSBA_TYPE = null), khong cap nhat cac truong hop tiep don chuyen tu ngoai tru vao noi tru
                if(hsba.getHsbaType() == null) {
                    td.setBenhnhanMa(benhNhan);
                    td.setDoituongMa(hsba.getDoituongMa());
                    td.setTiepdonNgaygio(hsba.getHsbaNgaygiovaov());

                    if(hsbaBhyt != null) {
                        td.setKhoibhytMa(hsbaBhyt.getHsbabhytKhoibh());
                        td.setTinhbhytMa(hsbaBhyt.getHsbabhytTinhbh());
                        td.setTiepdonMacoquan(hsbaBhyt.getHsbabhytCoquanbh());
                        td.setTiepdonSothebh(hsbaBhyt.getHsbabhytSothebh());
                        td.setTiepdonTuyen(hsbaBhyt.getHsbabhytTuyen());
                        td.setKcbbhytMa(hsbaBhyt.getHsbabhytMakcb());
                        td.setTiepdonGiatri1(hsbaBhyt.getHsbabhytGiatri0());
                        td.setTiepdonGiatri2(hsbaBhyt.getHsbabhytGiatri1());
                        td.setTiepdonGiatri3(hsbaBhyt.getHsbabhytGiatri2());
                        td.setTiepdonGiatri4(hsbaBhyt.getHsbabhytGiatri3());
                        td.setTiepdonMoc1(hsbaBhyt.getHsbabhytMoc1());
                        td.setTiepdonMoc2(hsbaBhyt.getHsbabhytMoc2());
                        td.setTiepdonMoc3(hsbaBhyt.getHsbabhytMoc3());
                        td.setTiepdonCoGiayGioiThieu(hsbaBhyt.getHsbabhytCoGiayChuyenVien());
                    }
                    em.merge(td);
                }
            }

            hsba.setTiepdonMa(maTiepDon);
            hsba.setBenhnhanMa(benhNhan);
            getEm().merge(hsba);

             //phuc.lc 04/10/2011 : chi tao ho so BA BHYT cho doi tuong bao hiem, khong tao cho doi tuong mien phi
            if (hsbaBhyt != null && "BH".equals( hsba.getDoituongMa(true).getDmdoituongMa())) {
                hsbaBhyt.setHsbaSovaovien(hsba);
                if (hsbaBhyt.getHsbabhytMa() == null) { // HSBA_BHYT not yet exist
                    Utils.setInfor(hsbaBhyt, em);
                    getEm().persist(hsbaBhyt);
                } else {
                    Utils.setInfor(hsbaBhyt, em);
                    getEm().merge(hsbaBhyt);
                }
            } else {
                HsbaBhytFacade hsbaFacade = new HsbaBhytFacade();
                hsbaFacade.setEm(em);
                List<HsbaBhyt> lstHsbaBHYT = hsbaFacade.findBySoVaoVien(hsba.getHsbaSovaovien());
                if (lstHsbaBHYT != null){
                    for (HsbaBhyt hsbaBHYTTmp: lstHsbaBHYT){
                        em.remove(hsbaBHYTTmp);
                    }
                }
            }


        } catch (Exception ex) {
            context.setRollbackOnly();
            System.out.println("ERROR: capNhatThongTinHanhChinhBN: " + ex.toString());
            ex.printStackTrace();
            return "error";
        }
        System.out.println("---------------- End: capNhatThongTinHanhChinhBN -------------------");
        return hsba.getHsbaSovaovien();
    }

    public String xoaHoSoBenhAn(Hsba hsba) {
        String soVaoVien = hsba.getHsbaSovaovien();



        return soVaoVien;
    }

    public String capNhatThongTinHSBA(Hsba hsba,
            HsbaNop hsbaNop, HsbaChuyenVien hsbaCV) {

        try {


            getEm().merge(hsba);

            if (hsbaNop != null){
                if (hsbaNop.getHsbanopMa() == null) {
                    hsbaNop.setHsbaSovaovien(hsba);
                    //lay so luu tru trung voi so vao vien
//                    String soLuuTru = Utils.getSoLuuTru(em);
                    hsbaNop.setHsbanopSoluutru(hsba.getHsbaSovaovien());
                    getEm().persist(hsbaNop);
                } else {
                    getEm().merge(hsbaNop);
                }
            }

            if (hsbaCV != null){
                if (hsbaCV.getHsbachuyenvienMaso() == null) {
                    hsbaCV.setHsbaSovaovien(hsba);
                    getEm().persist(hsbaCV);
                } else {
                    getEm().merge(hsbaCV);
                }
            }


        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return "1";
        }
        return hsbaNop.getHsbanopSoluutru();
    }

    private int xoaDuLieuTam(){
         Query q = getEm().createQuery("delete from TmpDataBhyt");
         return q.executeUpdate();
    }

    /*
     * Thanh add 05/10/2011
     * Date ok
     */
     public List<TmpDataBhyt> exportDataNoiTru(Date fromDate, Date toDate, boolean xoaDulieuTam, String congSoNgayDieuTri){
        if(xoaDulieuTam){
            xoaDuLieuTam();
        }

        String v_benhnhan_nt_datt_detail = "SELECT A.HSBA_SOVAOVIEN benhan, G.BENHNHAN_HOTEN ho_ten, G.BENHNHAN_NAMSINH nam_sinh, "
             + "GIOI.DMGT_MA gioi_tinh, "
             + "C.HSBABHYT_SOTHEBH mathe, (select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = C.HSBABHYT_TINHBH) tinh_kcb, "
             + "(substr(BENHVIEN.DMBENHVIEN_MA,-3,3)) ma_kcb, "
             + "BENH.DMBENHICD_MA ma_benh, "
             + "A.HSBA_NGAYGIOVAOV ngay_vao, A.HSBA_NGAYGIORAV ngay_ra, (select DMBENHICD_TEN from dm_benh_icd where DMBENHICD_MASO = A.HSBA_MACHDRAVIEN) ten_benh, "
             + "BENHVIEN.DMBENHVIEN_MA noi_kham, B.HSTHTOAN_NGAYTT ngay_tt, "
             + "extract(month from A.HSBA_NGAYGIORAV) thangqt, extract(year from A.HSBA_NGAYGIORAV) namqt, C.HSBABHYT_GIATRI0 gtri_tu, C.HSBABHYT_GIATRI1 gtri_den, G.BENHNHAN_DIACHI dia_chi, "
             + "'NOI' loaikcb, 'CSKCB' noi_ttoan, B.HSTHTOAN_MA sophieu, A.HSBA_KHOARAV ma_khoa, D.DTDMKHOIBHYT_MA ma_dt, "
             + "(select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where DTDMMQLBHYT_MASO = substr(C.HSBABHYT_SOTHEBH,3,1)) vanchuyen, "
             + "substr(C.HSBABHYT_SOTHEBH,3,1) loai_tt, C.HSBABHYT_MOC1 ngay_g1, C.HSBABHYT_MOC2 ngay_g2, C.HSBABHYT_MOC3 ngay_g3, "
             + "C.HSBABHYT_TUYEN dieu_tri, coalesce(C.HSBABHYT_CO_GIAY_CHUYEN_VIEN,0) GIAY_CV, "
             + "BENHVIEN_GOI.DMBENHVIEN_TEN noi_den, "
             + "(select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = C.HSBABHYT_TINHBH) ma_bhxh, "
             + "'' BENH_PHU "
             + "FROM hs_thtoan B LEFT JOIN hsba A ON B.HSBA_SOVAOVIEN = A.HSBA_SOVAOVIEN "
	     + "LEFT JOIN benh_nhan G ON A.BENHNHAN_MA = G.BENHNHAN_MA "
             + "LEFT JOIN DM_GIOI_TINH GIOI ON G.DMGT_MASO = GIOI.DMGT_MASO "
             + "LEFT JOIN hsba_bhyt C ON C.HSBA_SOVAOVIEN = A.HSBA_SOVAOVIEN "
	     + "LEFT JOIN DM_BENH_ICD BENH ON A.HSBA_MACHDOANBD = BENH.DMBENHICD_MASO "
             + "LEFT JOIN dt_dm_khoi_bhyt D ON C.HSBABHYT_KHOIBH = D.DTDMKHOIBHYT_MASO "
             + "LEFT JOIN DT_DM_NHOM_BHYT NHOMBHYT ON D.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
             + "LEFT JOIN DM_BENH_VIEN BENHVIEN ON C.HSBABHYT_MAKCB = BENHVIEN.DMBENHVIEN_MASO "
	     + "LEFT JOIN DM_BENH_VIEN BENHVIEN_GOI ON A.HSBA_DONVIGOI = BENHVIEN_GOI.DMBENHVIEN_MASO "
             + "WHERE A.DOITUONG_MA = 2 AND A.HSBA_NGAYGIORAV Is Not Null "
             + "AND to_date(A.HSBA_NGAYGIORAV) >= :fromDate AND to_date(A.HSBA_NGAYGIORAV) <= :toDate "
             + "AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
             // ########################################
             // phuc.lc : 24/11/2011
             // Khong can Group By theo HSBA_SOVAOVIEN, truoc day do loi code mot SOVAOVIEN co nhieu hon 1 hs_thtoan
             // nen moi can Group By, code bay gio da fix loi nay nen khong can Group By nua
             // khi xuat du lieu sai, can kiem tra lai van de nay
             // phai bao dam moi SOVAOVIEN chi co 1 hs_thtoan
             //##############################################
             /*     + "GROUP BY A.HSBA_SOVAOVIEN "  */
             + "ORDER BY A.HSBA_SOVAOVIEN";
        /*String v_benhnhan_nt_datt_detail = "SELECT A.HSBA_SOVAOVIEN benhan, G.BENHNHAN_HOTEN ho_ten, G.BENHNHAN_NAMSINH nam_sinh, "
            + "(select DMGT_MA from dm_gioi_tinh where DMGT_MASO = G.DMGT_MASO) gioi_tinh, "
            + "C.HSBABHYT_SOTHEBH mathe, (select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = C.HSBABHYT_TINHBH) tinh_kcb, "
            + "(select RIGHT(DMBENHVIEN_MA,3) from dm_benh_vien where DMBENHVIEN_MASO = C.HSBABHYT_MAKCB) ma_kcb, "
            + "(select DMBENHICD_MA from dm_benh_icd where DMBENHICD_MASO = A.HSBA_MACHDRAVIEN) ma_benh, "
            + "A.HSBA_NGAYGIOVAOV ngay_vao, A.HSBA_NGAYGIORAV ngay_ra, (select DMBENHICD_TEN from dm_benh_icd where DMBENHICD_MASO = A.HSBA_MACHDRAVIEN) ten_benh, "
            + "(select DMBENHVIEN_MA  from dm_benh_vien where DMBENHVIEN_MASO = C.HSBABHYT_MAKCB) noi_kham, B.HSTHTOAN_NGAYTT ngay_tt, "
            + "month(A.HSBA_NGAYGIORAV) thangqt, year(A.HSBA_NGAYGIORAV) namqt, C.HSBABHYT_GIATRI0 gtri_tu, C.HSBABHYT_GIATRI1 gtri_den, G.BENHNHAN_DIACHI dia_chi, "
            + "'NOI' loaikcb, 'CSKCB' noi_ttoan, B.HSTHTOAN_MA sophieu, A.HSBA_KHOARAV ma_khoa, D.DTDMKHOIBHYT_MA ma_dt, "
            + "(select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where DTDMMQLBHYT_MASO = substring(C.HSBABHYT_SOTHEBH,3,1)) vanchuyen, "
            + "substring(C.HSBABHYT_SOTHEBH,3,1) loai_tt, C.HSBABHYT_MOC1 ngay_g1, C.HSBABHYT_MOC2 ngay_g2, C.HSBABHYT_MOC3 ngay_g3, "
            + "C.HSBABHYT_TUYEN dieu_tri, coalesce(C.HSBABHYT_CO_GIAY_CHUYEN_VIEN,0) GIAY_CV, "
            + "(select DMBENHVIEN_TEN from dm_benh_vien where DMBENHVIEN_MASO = A.HSBA_DONVIGOI) noi_den, "
            + "(select DMTINH_MABHYT from dm_tinh where DMTINH_MASO = C.HSBABHYT_TINHBH) ma_bhxh, "
            + "'' BENH_PHU "
            + "FROM hsba A LEFT JOIN hs_thtoan B ON A.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN "
            + "LEFT JOIN hsba_bhyt C ON C.HSBA_SOVAOVIEN = A.HSBA_SOVAOVIEN "
            + "LEFT JOIN dt_dm_khoi_bhyt D ON C.HSBABHYT_KHOIBH = D.DTDMKHOIBHYT_MASO "
            + "LEFT JOIN DT_DM_NHOM_BHYT NHOMBHYT ON D.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
            + "INNER JOIN benh_nhan G ON A.BENHNHAN_MA = G.BENHNHAN_MA "
            + "WHERE A.DOITUONG_MA = 2 AND A.HSBA_NGAYGIORAV Is Not Null "
            + "AND DATE(A.HSBA_NGAYGIORAV) >= :fromDate AND DATE(A.HSBA_NGAYGIORAV) <= :toDate "
            + "AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
            + "GROUP BY A.HSBA_SOVAOVIEN "
            + "ORDER BY A.HSBA_SOVAOVIEN";
         *
         */
        Query q = getEm().createNativeQuery(v_benhnhan_nt_datt_detail, VBenhnhanNtDattDetail.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VBenhnhanNtDattDetail> listVBenhnhanNtDattDetail = q.getResultList();
        System.out.println("listVBenhnhanNtDattDetail.size: "+listVBenhnhanNtDattDetail.size());
        //sSQL = "delete from TmpDataNoitru";
        //q = em.createQuery(sSQL);
        //int result = q.executeUpdate();

        //Thuoc
        String sSQL = "SELECT (tnt.HSBA_SOVAOVIEN || tnt.stt_nh) Id, tnt.HSBA_SOVAOVIEN, tnt.stt_nh, sum(tnt.tong_tien) tong_tien, sum(tnt.tien_bn) tien_bn "
              +"FROM "
              +"( "
              +"    SELECT B.HSBA_SOVAOVIEN, C.KHOA_MA ma_khoa, A.THUOCNOITRU_NGAYGIO, A.THUOCNOITRU_MATHUOC, DECODE(D.DMTHUOC_PLBHYT , NULL, 1,D.DMTHUOC_PLBHYT) stt_nh, D.DMTHUOC_MA ma_hieu,(A.THUOCNOITRU_SOLUONG - coalesce(A.THUOCNOITRU_TRA,0)) sl, A.THUOCNOITRU_DONGIATT gia, "
              +"        coalesce(A.THUOCNOITRU_DONGIABH,0) gia_bhxh, coalesce(A.THUOCNOITRU_TIENBNTRA,0) tien_bn, "
              +"        coalesce(A.THUOCNOITRU_THANHTIEN,0) tong_tien, "
              +"        (A.THUOCNOITRU_SOLUONG - coalesce(A.THUOCNOITRU_TRA,0))*( A.THUOCNOITRU_DONGIATT-coalesce(A.THUOCNOITRU_DONGIABH,0)) as tien_chenh, "
              +"        (coalesce(A.THUOCNOITRU_THANHTIEN,0) - coalesce(A.THUOCNOITRU_TIENBNTRA,0)) tien_bhxh "
              +"    FROM thuoc_noi_tru A, hsba B, hsba_khoa C, dm_thuoc D, HSBA_BHYT HSBABHYT, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT "
              +"    WHERE B.HSBA_SOVAOVIEN = C.HSBA_SOVAOVIEN "
              +"    AND HSBABHYT.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN "
              +"    AND HSBABHYT.HSBABHYT_KHOIBH = KHOIBHYT.DTDMKHOIBHYT_MASO "
              +"    AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
              +"    AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
              +"    AND to_DATE(B.HSBA_NGAYGIORAV) >= :fromDate AND to_DATE(B.HSBA_NGAYGIORAV) <= :toDate "
              +"    AND A.HSBA_KHOA = C.HSBAKHOA_MASO AND B.DOITUONG_MA = 2 "
              +"    AND A.THUOCNOITRU_MATHUOC = D.DMTHUOC_MASO "
              +"    AND A.THUOCDONGY_MASO IS NULL "
              +") tnt "
              +"GROUP BY tnt.stt_nh, tnt.HSBA_SOVAOVIEN "
              +"ORDER BY tnt.HSBA_SOVAOVIEN asc, tnt.stt_nh asc";
//        sSQL ="SELECT a.* FROM v_thuoc_noitru_groupbysovaovien_sttnhExcel a, " +
//                    "(SELECT v.benhan FROM v_benhnhan_nt_datt_detail v where DATE(ngay_tt) >= :fromDate "+
//                    "and DATE(ngay_tt) <= :toDate) b" +
//                    " where a.HSBA_SOVAOVIEN = b.benhan" +
//                    " order by a.HSBA_SOVAOVIEN, a.Stt_nh";
        q = em.createNativeQuery(sSQL, VThuocNoitruGroupbysovaovienSttnhexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VThuocNoitruGroupbysovaovienSttnhexcel> listVThuocNoitruGroupbysovaovienSttnh = q.getResultList();
        System.out.println("listVThuocNoitruGroupbysovaovienSttnh.size: "+listVThuocNoitruGroupbysovaovienSttnh.size());
        java.util.HashMap<String, ArrayList> hmNhomThuocNTs = new java.util.HashMap<String, ArrayList>();
        ArrayList<VThuocNoitruGroupbysovaovienSttnhexcel> arrThuocVTYTs = new ArrayList<VThuocNoitruGroupbysovaovienSttnhexcel>();
        String strSovaovienTemp = "";
        for(VThuocNoitruGroupbysovaovienSttnhexcel vThuocNoitruGroupbysovaovienSttnh:listVThuocNoitruGroupbysovaovienSttnh){
            String strSovaovien = vThuocNoitruGroupbysovaovienSttnh.getHsbaSovaovien();
            if(!strSovaovienTemp.equals(strSovaovien)){
                arrThuocVTYTs = new ArrayList<VThuocNoitruGroupbysovaovienSttnhexcel>();
            }else{
                hmNhomThuocNTs.remove(strSovaovien);
            }
            arrThuocVTYTs.add(vThuocNoitruGroupbysovaovienSttnh);
            strSovaovienTemp = strSovaovien;
            hmNhomThuocNTs.put(strSovaovien, arrThuocVTYTs);
        }

        //Bai thuoc
        sSQL ="SELECT (A.HSBA_SOVAOVIEN ||'1') Id, A.HSBA_SOVAOVIEN, 1 stt_nh, sum(coalesce(A.THUOCDONGY_TIENBNTRA,0)) tien_bn, "
             +" SUM(A.THUOCDONGY_SOLUONG * A.THUOCDONGY_DONGIA) tong_tien "
             +"FROM thuoc_dong_y_noi_tru A, HSBA B, HSBA_BHYT HSBABHYT, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT "
             +"WHERE A.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN AND B.HSBA_NGAYGIORAV Is Not Null AND B.DOITUONG_MA = 2 "
             +"AND HSBABHYT.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN "
             +"AND HSBABHYT.HSBABHYT_KHOIBH = KHOIBHYT.DTDMKHOIBHYT_MASO "
             +"AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
             +"AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
             +"AND to_date(B.HSBA_NGAYGIORAV) >= :fromDate AND to_date(B.HSBA_NGAYGIORAV) <= :toDate "
             +"GROUP BY A.HSBA_SOVAOVIEN "
             +"ORDER BY A.HSBA_SOVAOVIEN asc";
//        sSQL ="SELECT a.* FROM v_thuoc_dongy_noitruExcel a, " +
//                    "(SELECT v.benhan  FROM v_benhnhan_nt_datt_detail v where DATE(ngay_tt) >= :fromDate "+
//                    "and DATE(ngay_tt) <= :toDate) b" +
//              " where a.HSBA_SOVAOVIEN = b.benhan group by a.HSBA_SOVAOVIEN, a.stt_nh order by a.HSBA_SOVAOVIEN, a.Stt_nh";
        q = em.createNativeQuery(sSQL, VThuocDongyNoitruexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VThuocDongyNoitruexcel> listVThuocDongyNoitru = q.getResultList();
        System.out.println("listVThuocDongyNoitru.size: "+listVThuocDongyNoitru.size());
        java.util.HashMap<String, ArrayList> hmNhomThuocDYNTs = new java.util.HashMap<String, ArrayList>();
        ArrayList<VThuocDongyNoitruexcel> arrThuocDYVTYTs = new ArrayList<VThuocDongyNoitruexcel>();
        strSovaovienTemp = "";
        for(VThuocDongyNoitruexcel vThuocDongyNoitru:listVThuocDongyNoitru){
            String strSovaovien = vThuocDongyNoitru.getHsbaSovaovien();
            if(!strSovaovienTemp.equals(strSovaovien)){
                arrThuocDYVTYTs = new ArrayList<VThuocDongyNoitruexcel>();
            }else{
                hmNhomThuocDYNTs.remove(strSovaovien);
            }
            arrThuocDYVTYTs.add(vThuocDongyNoitru);
            strSovaovienTemp = strSovaovien;
            hmNhomThuocDYNTs.put(strSovaovien, arrThuocVTYTs);
        }

        //CLS
        sSQL ="SELECT (A.HSBA_SOVAOVIEN || A.stt_nh) Id, A.HSBA_SOVAOVIEN, A.stt_nh, sum(A.tong_tien) tong_tien, sum(A.tien_bn) tien_bn  "
             +"FROM "
             +"( "
             +"     SELECT B.HSBA_SOVAOVIEN,  D.DTDMCLSBG_MALOAI stt_nh,  "
             +"     SUM(coalesce(A.CLSMO_DONGIABNTRA,0)) tien_bn, "
             +"     SUM((A.CLSMO_LAN - coalesce(A.CLSMO_TRA,0))* coalesce(A.CLSMO_DONGIA,0)) tong_tien "
             +"     FROM cls_mo A, HSBA B, hsba_khoa C, dt_dm_cls_bang_gia D, HSBA_BHYT HSBABHYT, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT  "
             +"     WHERE B.HSBA_SOVAOVIEN = C.HSBA_SOVAOVIEN AND B.HSBA_NGAYGIORAV Is Not Null AND to_DATE(B.HSBA_NGAYGIORAV) >= :fromDate AND to_DATE(B.HSBA_NGAYGIORAV) <= :toDate "
             +"     AND B.DOITUONG_MA = 2 "
             +"     AND A.HSBAKHOA_MASO = C.HSBAKHOA_MASO "
             +"     AND A.CLSMO_MAHANG = D.DTDMCLSBG_MASO "
             +"     AND HSBABHYT.HSBA_SOVAOVIEN = B.HSBA_SOVAOVIEN "
             +"     AND HSBABHYT.HSBABHYT_KHOIBH = KHOIBHYT.DTDMKHOIBHYT_MASO "
             +"     AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
             +"     AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
             +"     GROUP BY B.HSBA_SOVAOVIEN,D.DTDMCLSBG_MALOAI "

             +") A "
             +"GROUP BY A.stt_nh, A.HSBA_SOVAOVIEN "
             +"ORDER BY A.HSBA_SOVAOVIEN asc, A.stt_nh asc";
//        sSQL ="SELECT a.* FROM v_cls_noitru_groupbysovaovien_sttnhExcel a, " +
//                    "(SELECT v.benhan  FROM v_benhnhan_nt_datt_detail v where DATE(ngay_tt) >= :fromDate "+
//                    "and DATE(ngay_tt) <= :toDate) b" +
//                    " where a.HSBA_SOVAOVIEN = b.benhan order by a.HSBA_SOVAOVIEN, a.Stt_nh";
        q = em.createNativeQuery(sSQL, VClsNoitruGroupbysovaovienSttnhexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VClsNoitruGroupbysovaovienSttnhexcel> listVClsNoitruGroupbysovaovienSttnh = q.getResultList();
        System.out.println("listVClsNoitruGroupbysovaovienSttnh.size: "+listVClsNoitruGroupbysovaovienSttnh.size());
        java.util.HashMap<String, ArrayList> hmNhomCLSs = new java.util.HashMap<String, ArrayList>();
        ArrayList<VClsNoitruGroupbysovaovienSttnhexcel> arrCLSs = new ArrayList<VClsNoitruGroupbysovaovienSttnhexcel>();
        strSovaovienTemp = "";
        for(VClsNoitruGroupbysovaovienSttnhexcel vClsNoitruGroupbysovaovienSttnh:listVClsNoitruGroupbysovaovienSttnh){
            String strSovaovien = vClsNoitruGroupbysovaovienSttnh.getHsbaSovaovien();
            if(!strSovaovienTemp.equals(strSovaovien)){
                arrCLSs = new ArrayList<VClsNoitruGroupbysovaovienSttnhexcel>();
            }else{
                hmNhomCLSs.remove(strSovaovien);
            }
            arrCLSs.add(vClsNoitruGroupbysovaovienSttnh);
            strSovaovienTemp = strSovaovien;
            hmNhomCLSs.put(strSovaovien, arrCLSs);
        }
        List<TmpDataBhyt> lstTmpDataNoitru = new ArrayList<TmpDataBhyt>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for(VBenhnhanNtDattDetail vBenhnhanNtDattDetail:listVBenhnhanNtDattDetail){
            Double tienBNTra = new Double(0.0);
            Double tienThuoc = new Double(0.0);
            Double tienVTYTTH = new Double(0.0);
            Double tienXN = new Double(0.0);
            Double tienCDHA = new Double(0.0);
            Double tienMau = new Double(0.0);
            Double tienThuThuat = new Double(0.0);
            Double tienPhauThuat = new Double(0.0);
            Double tienDVKTC = new Double(0.0);
            Double tienKTG = new Double(0.0);
            Double tienKham = new Double(0.0);
            Double tienGiuong = new Double(0.0);
            Double tienVanChuyen = new Double(0.0);
            Double tongchiphi = new Double(0.0);
            Double tienBHXH = new Double(0.0);
            String strSovaovien = vBenhnhanNtDattDetail.getBenhan();
            TmpDataBhyt dataNoiTru = new TmpDataBhyt();

            dataNoiTru.setTiepDon(vBenhnhanNtDattDetail.getBenhan());
            dataNoiTru.setHoTen(vBenhnhanNtDattDetail.getHoTen());
            String namsinh = "0";
            if(vBenhnhanNtDattDetail.getNamSinh() != null){
                namsinh = vBenhnhanNtDattDetail.getNamSinh();
            }
            dataNoiTru.setNamSinh(Integer.parseInt(namsinh));
            if(vBenhnhanNtDattDetail.getGioiTinh() == null){
                dataNoiTru.setGioiTinh(Short.parseShort("0"));
            }else{
                dataNoiTru.setGioiTinh(Short.parseShort(vBenhnhanNtDattDetail.getGioiTinh()));
            }

            String maTheBHYT = "";
            if(vBenhnhanNtDattDetail.getMathe() != null){
                maTheBHYT = vBenhnhanNtDattDetail.getMathe();
            }
            dataNoiTru.setMaThe(maTheBHYT);
            String tinhKCB = "";
            String maKCB = "";
            if(vBenhnhanNtDattDetail.getTinhKcb() != null){
                tinhKCB = vBenhnhanNtDattDetail.getTinhKcb();
            }
            if(vBenhnhanNtDattDetail.getMaKcb() != null){
                maKCB = vBenhnhanNtDattDetail.getMaKcb();
            }
            dataNoiTru.setMaDkbd(tinhKCB + maKCB);
            String maBenh = "";
            if(vBenhnhanNtDattDetail.getMaBenh() != null){
                maBenh = vBenhnhanNtDattDetail.getMaBenh();
            }
            dataNoiTru.setMaBenh(maBenh);

            String ngayVao = "";
            String ngayRa = "";
            int songayDT = 0;
            if(vBenhnhanNtDattDetail.getNgayVao() != null && vBenhnhanNtDattDetail.getNgayRa() != null){
                ngayVao = format.format(vBenhnhanNtDattDetail.getNgayVao());
                ngayRa = format.format(vBenhnhanNtDattDetail.getNgayRa());
                int congthemSoNDT = 0;
                if(congSoNgayDieuTri.equals("1")){
                    congthemSoNDT = 1;
                }
                if(ngayRa.equals(ngayVao)){
                    songayDT = 1;
                }else{
                    songayDT = Utils.subtractDates(vBenhnhanNtDattDetail.getNgayVao(),vBenhnhanNtDattDetail.getNgayRa()) + congthemSoNDT;
                }
            }else{
                ngayVao = format.format(new Date());
                ngayRa = format.format(new Date());
            }
            dataNoiTru.setNgayVao(ngayVao);
            dataNoiTru.setNgayRa(ngayRa);
            //Long songayDT = (ngayRaDate.getTime() - ngayVaoDate.getTime()) / (60 * 60 * 1000L * 24) ;
            dataNoiTru.setNgayDtr(songayDT < 0 ? 0 : songayDT);

            arrThuocVTYTs = new ArrayList<VThuocNoitruGroupbysovaovienSttnhexcel>();
            arrCLSs = new ArrayList<VClsNoitruGroupbysovaovienSttnhexcel>();
            if(hmNhomThuocNTs.size() > 0){
                arrThuocVTYTs = hmNhomThuocNTs.get(strSovaovien);
                if(arrThuocVTYTs != null && arrThuocVTYTs.size()>0){
                    for(VThuocNoitruGroupbysovaovienSttnhexcel vThuocNoitruGroupbysovaovienSttnh:arrThuocVTYTs){
                        if(vThuocNoitruGroupbysovaovienSttnh.getSttNh() == 1){//Thuoc
                            tienThuoc += vThuocNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vThuocNoitruGroupbysovaovienSttnh.getSttNh() == 10){//VTYT
                            tienVTYTTH += vThuocNoitruGroupbysovaovienSttnh.getTongTien();
                        }
                        tienBNTra += vThuocNoitruGroupbysovaovienSttnh.getTienBn();
                        tienBHXH += (vThuocNoitruGroupbysovaovienSttnh.getTongTien() - vThuocNoitruGroupbysovaovienSttnh.getTienBn());
                    }
                }
            }
            if(hmNhomThuocDYNTs.size() > 0){
                arrThuocDYVTYTs = hmNhomThuocDYNTs.get(strSovaovien);
                if(arrThuocDYVTYTs != null && arrThuocDYVTYTs.size()>0){
                    for(VThuocDongyNoitruexcel vThuocDongyNoitru:arrThuocDYVTYTs){
                        if(vThuocDongyNoitru.getSttNh() == 1){//Thuoc
                            tienThuoc += vThuocDongyNoitru.getTongTien();
                        }else if(vThuocDongyNoitru.getSttNh() == 10){//VTYT
                            tienVTYTTH += vThuocDongyNoitru.getTongTien();
                        }
                        tienBNTra += vThuocDongyNoitru.getTienBn();
                        tienBHXH += (vThuocDongyNoitru.getTongTien() - vThuocDongyNoitru.getTienBn());
                    }
                }
            }
            if(hmNhomCLSs.size() > 0){
                arrCLSs = hmNhomCLSs.get(strSovaovien);
                if(arrCLSs != null && arrCLSs.size()>0){
                    for(VClsNoitruGroupbysovaovienSttnhexcel vClsNoitruGroupbysovaovienSttnh:arrCLSs){
                        if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 2){//Tien mau
                            tienMau += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 4){//Tien xet nghiem
                            tienXN += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 5){//Tien CDHA
                            tienCDHA += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 6){//Tien Phau thuat
                            tienPhauThuat += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 7){//Tien cong kham
                            tienKham += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 8){//Tien thu thuat
                            tienThuThuat += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 9){//Tien DVKTC
                            tienDVKTC += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 11){//Tien ngay giuong
                            tienGiuong += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }else if(vClsNoitruGroupbysovaovienSttnh.getSttNh() == 12){//Tien VC
                            tienVanChuyen += vClsNoitruGroupbysovaovienSttnh.getTongTien();
                        }
                        tienBNTra += vClsNoitruGroupbysovaovienSttnh.getTienBn();
                        tienBHXH += (vClsNoitruGroupbysovaovienSttnh.getTongTien() - vClsNoitruGroupbysovaovienSttnh.getTienBn());
                    }
                }
            }
            //tongchiphi = tienThuoc + tienVTYTTH + tienMau + tienXN + tienCDHA + tienPhauThuat + tienKham + tienThuThuat + tienDVKTC +tienGiuong + tienVanChuyen;
            tongchiphi = tienThuoc + tienVTYTTH + tienMau + tienXN + tienCDHA + tienPhauThuat + tienThuThuat + tienDVKTC +tienGiuong + tienVanChuyen;
            dataNoiTru.setTienXetnghiem(tienXN);
            dataNoiTru.setTienCdha(tienCDHA);
            dataNoiTru.setTienThuoc(tienThuoc);
            dataNoiTru.setTienMau(tienMau);
            dataNoiTru.setTienPttt(tienPhauThuat + tienThuThuat);
            dataNoiTru.setTienVtytth(tienVTYTTH);
            dataNoiTru.setTienDvktc(tienDVKTC);
            dataNoiTru.setTienKtg(tienKTG);
            //dataNoiTru.setTienKham(tienKham + tienGiuong);
            dataNoiTru.setTienKham(tienGiuong);
            dataNoiTru.setTienVanchuyen(tienVanChuyen);
            dataNoiTru.setTongChi(tongchiphi);
            if(tienBNTra < 0.0000001){
                tienBNTra = 0.0;
            }
            dataNoiTru.setTienBntra(tienBNTra);
            dataNoiTru.setTienBhxh(tienBHXH);
            Double tienngoaiDS = tongchiphi -(tienBNTra +tienBHXH);
            if(tienngoaiDS < 0.0000001){
                tienngoaiDS = 0.0;
            }
            dataNoiTru.setTienNgoaids(tienngoaiDS);
            //Xet Lý do vào viện (1 - đúng tuyến; 0 - trái tuyến)
            Short dieutri = vBenhnhanNtDattDetail.getDieuTri();
            //Xac dinh dung tuyen, trai tuyen, cap cuu tu YTDT ntn
            if(dieutri != null && dieutri == 1){
                dataNoiTru.setLydoVv(1);
            }else{
                //kiem tra neu co giay chuyen vien thi dung tuyen nguoc lai la trai tuyen
                Short cogiaygioithieu = vBenhnhanNtDattDetail.getGiayCv();
                if(cogiaygioithieu == 1){
                    dataNoiTru.setLydoVv(1);
                }else{
                    dataNoiTru.setLydoVv(0);
                }
            }
            String tenBenh = "";
            if(vBenhnhanNtDattDetail.getTenBenh() != null){
                tenBenh = vBenhnhanNtDattDetail.getTenBenh();
            }
            dataNoiTru.setBenhKhac(tenBenh);

           //tach dau "."
            String maBV = vBenhnhanNtDattDetail.getNoiKham();
            String noiKham = " ";
            String maNoiKham ="";
            if (maBV != null){
                String maTinh = maBV.substring(0,maBV.indexOf("."));//96.077
                maNoiKham = maBV.substring(maBV.indexOf(".")+1,maBV.length());
                noiKham = maTinh + maNoiKham;
            }
            dataNoiTru.setNoiKcb(noiKham);
            Integer maKhoa = 0;
            if(vBenhnhanNtDattDetail.getMaKhoa() != null){
                maKhoa = vBenhnhanNtDattDetail.getMaKhoa();
            }
            dataNoiTru.setMaKhoa(maKhoa);
            dataNoiTru.setThangQt(vBenhnhanNtDattDetail.getThangqt());
            dataNoiTru.setNamQt(vBenhnhanNtDattDetail.getNamqt());
            String gtTu = "";
            if(vBenhnhanNtDattDetail.getGtriTu() != null){
                gtTu = format.format(vBenhnhanNtDattDetail.getGtriTu());
            }
            String gtDen = "";
            if(vBenhnhanNtDattDetail.getGtriDen() != null){
                gtDen = format.format(vBenhnhanNtDattDetail.getGtriDen());
            }
            dataNoiTru.setGtTu(gtTu);
            dataNoiTru.setGtDen(gtDen);

            String diachi = "";
            if(vBenhnhanNtDattDetail.getDiaChi() != null){
                diachi = vBenhnhanNtDattDetail.getDiaChi();
            }
            dataNoiTru.setDiaChi(diachi);
            dataNoiTru.setGiamDinh("");
            dataNoiTru.setXuatToan("");
            dataNoiTru.setLydoXuattoan("");
            dataNoiTru.setDaTuyen(0);
            dataNoiTru.setVuotTran(0);
            String loaiKCB = "";
            if(vBenhnhanNtDattDetail.getLoaikcb() != null){
                loaiKCB = vBenhnhanNtDattDetail.getLoaikcb();
            }
            dataNoiTru.setLoaiKcb(loaiKCB);
            dataNoiTru.setNoiThanhtoan(vBenhnhanNtDattDetail.getNoiTtoan());

            String sophieu = "";
            if(vBenhnhanNtDattDetail.getSophieu() != null){
                sophieu = vBenhnhanNtDattDetail.getSophieu();
            }
            dataNoiTru.setSoPhieu(sophieu);
            //A - Bệnh nhân đăng ký KCB tại cơ sở
            //B - Bệnh nhân đăng ký KCB khác cơ sở, nội tỉnh
            //C - Bệnh nhân nội tỉnh
            if(dieutri != null && dieutri == 1){
                dataNoiTru.setTuyen("A");
            }else if(dieutri != null && dieutri == 2){
                dataNoiTru.setTuyen("B");
            }else{
                dataNoiTru.setTuyen("C");
            }
           //insert vao bang TmpDataNgoaitru
            getEm().persist(dataNoiTru);
            lstTmpDataNoitru.add(dataNoiTru);
        }
        return lstTmpDataNoitru;
    }

    /*
     * Thanh add 29/09/2011
     * Oracle: syntax ok
     */
    public List<Hsba> findBySoTheBHYT(String sothe) {
         String sSQL = "select * from Hsba o" +
            " where  o.HSBA_SOVAOVIEN IN " +
            " (select o2.HSBA_SOVAOVIEN from hsba_bhyt o2 where o2.HSBABHYT_SOTHEBH = :sothe)";
        Query q = getEm().createNativeQuery(sSQL, Hsba.class);
        q.setParameter("sothe", sothe);
        List<Hsba> lstHsba = q.getResultList();

        //System.out.println("findKhoaDangDieuTri END: " + lstHsba);
        return lstHsba;
    }
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    } 

    public boolean deleteHsbarv(String hsbaSovaovien) {
        HsbaDelegate hsbaDG = HsbaDelegate.getInstance();
        Hsba hsba = hsbaDG.find(hsbaSovaovien);
        hsba.setHsbaMachdravien(null);
        hsba.setHsbaChandoanrav(null);
        hsba.setHsbaYeuCau(null);
        hsba.setHsbaKetqua(null);
        hsba.setHsbaNgaygiorav(null);
        hsbaDG.edit(hsba);
		
		HsbaChuyenMonDelegate hsbaCMDelegate= HsbaChuyenMonDelegate.getInstance();
        HsbaChuyenMon hsbaCMLast = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsbaSovaovien);
        hsbaCMLast.setHsbacmHuongdieutri(null);
        hsbaCMDelegate.edit(hsbaCMLast);
		
		HsThtoanDelegate hsTTDelegate = HsThtoanDelegate.getInstance();
        HsThtoan hsThtoan = hsTTDelegate.findBySovaovien(hsbaSovaovien);
        //String maPhieu = hsThtoan.getHsthtoanMa();

        ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(hsbaSovaovien);
        HsThtoank hsttk;
        if (hsThtoan != null && hsThtoan.getHsthtoanNgaytt() != null) {
            cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(hsba.getHsbaSovaovien());
            if (cvnt != null) {
                hsttk = HsThtoankDelegate.getInstance().findBytiepdonMaLast(cvnt.getTiepDon().getTiepdonMa());
                if (hsttk != null ) {
                    ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
                    List<ClsKham> listCls = clsKhamDel.findByMaPhieu(hsttk.getHsthtoankMa());
                    if (listCls != null && listCls.size() > 0) {
                            for(ClsKham eachCls : listCls) {
                                    eachCls.setClskhamDatt(null);
                                    eachCls.setClskhamNgaygiott(null);
                                    eachCls.setClskhamMaphieu(null);
                                    clsKhamDel.edit(eachCls);
                            }
                    }
                    ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
                    List<ThuocPhongKham> listTpk = tpkDelegate.findByMaPhieu(hsttk.getHsthtoankMa());
                    if (listTpk != null && listTpk.size() > 0) {
                            for(ThuocPhongKham eachTpk : listTpk) {
                                    eachTpk.setThuocphongkhamDatt(null);
                                    eachTpk.setThuocphongkhamNgaygiott(null);
                                    eachTpk.setThuocphongkhamMaphieud(null);
                                    tpkDelegate.edit(eachTpk);
                            }
                    }
                    HsThtoankDelegate.getInstance().remove(hsttk);
                }
            }
			  hsTTDelegate.remove(hsThtoan);
        }

		
		
        return true;
    }

	
	
	public boolean deleteHsbacmCuoi(String hsbaSovaovien)
    {
        if(checkHaveHsbacm(hsbaSovaovien))
        {
            return false;
        }
        HsbaChuyenMonDelegate hsbaCMDelegate = HsbaChuyenMonDelegate.getInstance();
        HsbaChuyenMon hsbaCM = hsbaCMDelegate.findBySoVaoVien_LastHSBACM(hsbaSovaovien);
       // System.out.println("hsbacmCuoi = "+hsbaCM.getKhoaMa(true).getDmkhoaTen());
        if((hsbaCM!=null)&&(hsbaCM.getHsbacmMa()!=null)){
            Integer hsbacmMa = hsbaCM.getHsbacmMa();
            deleteHsbaCMDetail("hsba_chi_tiet_mat", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_noitru_yhct", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_rhm", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_noi", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_tmh", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_nhikhoa", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_sosinh", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_phukhoa", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_sankhoa", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_ngoaitru_yhct", hsbacmMa);
            deleteHsbaCMDetail("hsba_chi_tiet_naophathai", "hsba_chi_tiet_naophathai_ct", "HSBACTNAOPHATHAI_MA", hsbacmMa);
            //deleteHsbaDetail("hsba_lap_trich_bien_ban_hoi_chan", "hsba_lap_trich_bien_ban_hoi_chan_bacsi", "HSBALTBBHC_MASO", hsbaSovaovien);
            deleteHsbaCMDetail("hsba_chi_tiet_naophathai", hsbacmMa);
            hsbaCMDelegate.remove(hsbaCM);

        }


        HsbaChuyenMon hsbaCMLast = hsbaCMDelegate.findBySoVaoVien_LastHSBACM(hsbaSovaovien);
        hsbaCMLast.setHsbacmNgaygiorak(null);
        hsbaCMLast.setHsbacmHuongdieutri(null);
        hsbaCMLast.setHsbacmChuyenkhoa(null);
        hsbaCMDelegate.edit(hsbaCMLast);
        System.out.println("hsbacmLast= "+hsbaCMLast.getKhoaMa(true).getDmkhoaTen());

        HsbaDelegate hsbaDG = HsbaDelegate.getInstance();
        Hsba hsba = hsbaDG.find(hsbaSovaovien);
        hsba.setHsbaKhoadangdtCm(hsbaCMLast.getKhoaMa());
        hsbaDG.edit(hsba);
        return true;
    }
    public boolean deleteHsba(String hsbaSovaovien)
    {
        boolean result = true;
        if(checkHaveThanhToan(hsbaSovaovien))
            {
                return false;
            }

            if(checkHaveThuoc(hsbaSovaovien)){
                return false;
            }


        try {
            Hsba hsba = HsbaDelegate.getInstance().find(hsbaSovaovien);

            String tiepdonMa = hsba.getTiepdonMa();
            if((tiepdonMa!=null)&&(!tiepdonMa.equals("")))
            {
                //System.out.println("tiepdonMa="+tiepdonMa);
                TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
                TiepDon tiepdon = tdDelegate.findBenhNhanByTiepdonMa(tiepdonMa);
                if(tiepdon!=null){
                    tiepdon.setTiepdonChkhoa(null);
                    tiepdon.setTiepdonBaotin(null);
                    tdDelegate.edit(tiepdon);

                    ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
                    ThamKham thamkham = tkDelegate.findByMaTiepDon(tiepdonMa);
                    //System.out.println("Thamkhamma="+thamkham);

                    if(thamkham!=null){
                        thamkham.setThamkhamChuyenSoLieuVaoNoiTru(null);
                        thamkham.setThamkhamDieutri(null);
                        tkDelegate.edit(thamkham);
                    }
                }
            }
            deleteHsbaDetail("HSBA_BIEN_BAN_HOI_CHAN_PT", "HSBA_BIEN_BAN_HOI_CHAN_PT_GM", "HSBABBHCPT_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_BIEN_BAN_HOI_CHAN_PT", "HSBA_BIEN_BAN_HOI_CHAN_PT_PTV", "HSBABBHCPT_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_BIEN_BAN_HOI_CHAN_PT", "HSBA_BIEN_BAN_HOI_CHAN_PT_TPK", "HSBABBHCPT_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_BIEN_BAN_HOI_CHAN_PT", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_mat", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_noitru_yhct", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_rhm", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_giay_chung_nhan", hsbaSovaovien);
            deleteHsbaDetail("hsba_giay_chuyen_xac", hsbaSovaovien);
            deleteHsbaDetail("HSBA_LAP_TRICH_BIEN_BAN_HCHAN", "HSBA_LAP_TRICH_BIEN_BAN_HC_BSI", "HSBALTBBHC_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_LAP_TRICH_BIEN_BAN_HCHAN", hsbaSovaovien);
            deleteHsbaDetail("hsba_phieu_gui_xac", hsbaSovaovien);
            deleteHsbaDetail("HSBA_PHIEU_PHAU_THUAT_THUTHUAT", "HSBA_PHIEU_PHAU_THUAT_TT_BSIGM", "HSBAPPTTT_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_PHIEU_PHAU_THUAT_THUTHUAT", "HSBA_PHIEU_PHAU_THUAT_TT_BACSI", "HSBAPPTTT_MASO", hsbaSovaovien);
            deleteHsbaDetail("HSBA_PHIEU_PHAU_THUAT_THUTHUAT", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_san", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_tom_tat_benh_an", hsbaSovaovien);
            deleteHsbaDetail("hoan_thu", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_mo", "ket_qua_mo","HSBAMO_MA","HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_mo", "lich_mo","HSBAMO_MA","HSBACM_MA", hsbaSovaovien);

            deleteHsbaDetail("mo_yeu_cau", hsbaSovaovien);
            deleteHsbaDetail("phieu_theo_doi_truyen_dich", hsbaSovaovien);

            deleteHsbaDetail("phieu_xuat_bh_xuat_vien", "ct_xuat_bh_xuat_vien", "PHIEUXUATBHXV_MA", hsbaSovaovien);
            deleteHsbaDetail("phieu_xuat_bh_xuat_vien", hsbaSovaovien);
            deleteHsbaDetail("hsba_khoa", "thuoc_noi_tru", "HSBAKHOA_MASO","HSBA_KHOA", hsbaSovaovien);
            deleteHsbaDetail("hsba_khoa", "thuoc_noi_tru_xuat_vien", "HSBAKHOA_MASO","HSBA_KHOA", hsbaSovaovien);
            deleteHsbaDetail("thuoc_dong_y_noi_tru", hsbaSovaovien);

            deleteHsbaDetail("to_dieu_tri", hsbaSovaovien);
            deleteHsbaDetail("hoan_ung", hsbaSovaovien);
            deleteHsbaDetail("tam_ung", hsbaSovaovien);
            deleteHsbaDetail("mien_giam", hsbaSovaovien);
            deleteHsbaDetail("hsba_giay_ra_vien", hsbaSovaovien);

            deleteHsbaDetail("hsba_nop", hsbaSovaovien);
            deleteHsbaDetail("HSBA_GIAY_CHUNG_NHAN_PHTHUAT", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_vien", hsbaSovaovien);


            deleteHsbaDetail("hs_thtoan", hsbaSovaovien);
            deleteHsbaDetail("hsba_giay_chung_thuong", hsbaSovaovien);
            deleteHsbaDetail("hsba_giay_tom_tat", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_mo", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_bien_ban_hoi_chan", "HSBA_BIEN_BAN_HOI_CHAN_TV","HSBABBHC_MASO","HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_bien_ban_hoi_chan", "HSBACM_MA", hsbaSovaovien);

            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_noi", "HSBACM_MA", hsbaSovaovien);

            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_tmh", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_nhikhoa", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_sosinh", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_phukhoa", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_sankhoa", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_ngoaitru_yhct", "HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_naophathai", "hsba_chi_tiet_naophathai_ct","HSBACTNAOPHATHAI_MA","HSBACM_MA", hsbaSovaovien);
            deleteHsbaDetail("hsba_chuyen_mon", "hsba_chi_tiet_naophathai", "HSBACM_MA", hsbaSovaovien);

            deleteHsbaDetail("hsba_bhyt", hsbaSovaovien);
            deleteHsbaDetail("hsba_khoa", "cls_mo", "HSBAKHOA_MASO", hsbaSovaovien);

            deleteHsbaDetail("hsba_chuyen_mon", hsbaSovaovien);


            deleteHsbaDetail("hsba_khoa", hsbaSovaovien);
            deleteHsbaDetail("chuyen_vao_noi_tru","cvnt_SOVAOVIEN", hsbaSovaovien);
            deleteHsbaDetail("hsba", hsbaSovaovien);
            System.out.println("Ket thuc xoa");
        } catch (Exception e) {
            System.out.println("Loi: "+e.toString());
            result = false;
        }finally{
            return result;
        }

    }

    public boolean checkHaveThuoc(String hsbaSovaovien)
    {
        try {
            Object o = em.createNativeQuery("select sum(thuoc_noi_tru.THUOCNOITRU_SOLUONG)  "
                                + " from thuoc_noi_tru left join hsba_khoa ON thuoc_noi_tru.HSBA_KHOA = hsba_khoa.HSBAKHOA_MASO "
                                + " where hsba_khoa.HSBA_SOVAOVIEN = ?1 "
                                + "group by hsba_khoa.HSBA_SOVAOVIEN ").setParameter(1,hsbaSovaovien).getSingleResult().toString();
            if(o!=null){
                double d = Double.valueOf(o.toString());
                System.out.println("thuoc_noi_tru sum = "+d);
                if(d>0)
                {
                    return true;
                }

            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return false;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public boolean checkHaveThanhToan(String hsbaSovaovien){
        try {
            Object o = em.createNativeQuery("select count(*) as c from hs_thtoan where hs_thtoan.HSTHTOAN_NGAYTT is not null and hs_thtoan.HSBA_SOVAOVIEN = ?1 ").setParameter(1,hsbaSovaovien).getSingleResult().toString();
            if(o!=null){
                double d = Double.valueOf(o.toString());
                System.out.println("hsthanhtoan count(*) = "+d);
                if(d>0)
                {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return false;
    }

    public boolean checkHaveHsbacm(String hsbaSovaovien)
    {
        try {
            Object o = em.createNativeQuery("select count(*) from hsba_chuyen_mon where HSBA_SOVAOVIEN = ?1 ").setParameter(1,hsbaSovaovien).getSingleResult().toString();
            if(o!=null){
                double d = Double.valueOf(o.toString());
                System.out.println("hsbacm count(*) = "+d);
                if(d<=1)
                {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        return false;
    }

    public boolean deleteHsbaCMDetail(String tablename, Integer hsbacmMa)
    {
        em.createNativeQuery(" delete from "+tablename+" where HSBACM_MA = ?1 ")
                .setParameter(1, hsbacmMa).executeUpdate();
        return true;
    }
    public boolean deleteHsbaCMDetail(String ptablename,String tablename, String pidname, Integer hsbacmMa)
    {
        em.createNativeQuery("delete from "+tablename+" where "+pidname+" in (select "+ptablename+"."+pidname+" from "+ptablename+" where "+ptablename+".HSBACM_MA = ?1)")
                .setParameter(1, hsbacmMa).executeUpdate();
        return true;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax
     */
    public boolean deleteHsbaDetail(String tablename, String hsbaSovaovien)
    {
        em.createNativeQuery(" delete from "+tablename+" where HSBA_SOVAOVIEN = ?1 ")
                .setParameter(1, hsbaSovaovien).executeUpdate();
        return true;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public boolean deleteHsbaDetail(String tablename,String hsbaSovaovienColName, String hsbaSovaovien)
    {
        em.createNativeQuery(" delete from "+tablename+" where "+hsbaSovaovienColName+" = ?1 ")
                .setParameter(1, hsbaSovaovien).executeUpdate();
        return true;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public boolean deleteHsbaDetail(String ptablename,String tablename, String pidname, String hsbaSovaovien)
    {
        em.createNativeQuery("delete from "+tablename+" where "+pidname+" in (select "+ptablename+"."+pidname+" from "+ptablename+" where "+ptablename+".HSBA_SOVAOVIEN = ?1)")
                .setParameter(1, hsbaSovaovien).executeUpdate();
        return true;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public boolean deleteHsbaDetail(String ptablename,String tablename, String pidname,String foreignname, String hsbaSovaovien)
    {
        em.createNativeQuery("delete from "+tablename+" where "+foreignname+" in (select "+ptablename+"."+pidname+" from "+ptablename+" where "+ptablename+".HSBA_SOVAOVIEN = ?1)")
                .setParameter(1, hsbaSovaovien).executeUpdate();
        return true;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public boolean deleteHsbaDetail(String gptablename,String ptablename,String tablename, String pidname,String gpipname, String hsbaSovaovien)
    {
        em.createNativeQuery("delete from "+tablename+" where "+pidname+" in ( select "+ptablename+"."+pidname+" from "+ptablename+" left join "+gptablename+" ON "+ptablename+"."+gpipname+" = "+gptablename+"."+gpipname+"  where "+gptablename+".HSBA_SOVAOVIEN = ?1)")
                .setParameter(1, hsbaSovaovien).executeUpdate();
        return true;
    }

    public List<ThuocNoiTruXuatVien> findTntXuatVienBySoBenhAn(String sovaovien) {
        String sSQL = "select * from thuoc_noi_tru_xuat_vien o" +
            " where o.HSBA_KHOA IN" +
            " ( select HSBAKHOA_MASO from hsba_khoa " +
            " where HSBA_SOVAOVIEN = :sovaovien ) ";
        Query q = getEm().createNativeQuery(sSQL, ThuocNoiTruXuatVien.class);
        q.setParameter("sovaovien", sovaovien);
        List<ThuocNoiTruXuatVien> lstTntXv = q.getResultList();

        //System.out.println("findKhoaDangDieuTri END: " + lstHsba);
        return lstTntXv;
    }
    public Hsba findByThongTinBenhNhan(String hoten, Integer gtMaso, String namsinh, String gio, String ngay) {
        String sSQL = "select * from hsba o" +
            " where o.HSBA_NGAYGIORAV Is Null AND o.BENHNHAN_MA IN" +
            " ( select BENHNHAN_MA from benh_nhan " +
            " where BENHNHAN_HOTEN like :hoten AND DMGT_MASO = :gtMaso AND BENHNHAN_NAMSINH like :namsinh ) ";
        Query q = getEm().createNativeQuery(sSQL, Hsba.class);
        q.setParameter("hoten", hoten);
        q.setParameter("gtMaso", gtMaso);
        q.setParameter("namsinh", namsinh);
        List<Hsba> lstHsba = q.getResultList();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Hsba hsba = null;
        if(lstHsba != null) {
            String ngayVaoVien = "";
            String ngaygio = gio + " " + ngay;
            try {
            for(Hsba eachHsba : lstHsba) {
                ngayVaoVien = sdf.format(eachHsba.getHsbaNgaygiovaov());
                //System.out.println("ngayVaoVien = " + ngayVaoVien + ", ngaygio = " + ngaygio);
                if(ngayVaoVien.equals(ngaygio)) {
                    hsba = eachHsba;
                    break;
                }
            }
            }catch(Exception ex) {}
        }
        return hsba;
    }
}