/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.utils;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import com.iesvn.yte.dieutri.entity.HsbaNop;
import com.iesvn.yte.dieutri.entity.KetQuaMo;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TonKho;

import com.iesvn.yte.dieutri.session.YteSequenceFacade;
import com.iesvn.yte.dieutri.session.YteSequenceFacadeLocal;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmThuoc;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author root
 */
public class Utils {

    public static int LENGTH_MA = 12;
    public static int LENGTH_MA_PHIEU = 11;

    public static String createMaPhieu(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 4);
    }

    public static String maGiayChungThuong(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 11);
    }

    public static String maGiayChungNhanPhauThuat(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 12);
    }

    public static String maGiayRaVien(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 13);
    }

    public static String maGiayChuyenXac(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 31);
    }

    public static String maPhieuGuiXac(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 32);
    }

    public static String maPhieuPhauThuatThuThuat(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 33);
    }
     public static String maLapTrichBienBanHoiChan(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 34);
    }
      public static String maBienBanHoiChanPhauThuat(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 35);
    }

    public static String maHsbaChuyenVien(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 14);
    }

    public static String maGiayTomTatHsba(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 16);
    }

    public static String maPhieuDuTru(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 5);
    }

    public static String maPhieuNhap(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 0);
    }

    public static String maPhieuXuat(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 1);
    }

    public static String maPhieuTra(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 2);
    }

    public static String maPhieuThanhToan(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 3);
    }

    public static String maPhieuXuatBaoHiem(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 6);
    }

    public static String maPhieuXuatBaoHiemXuatVien(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 36);
    }

    public static String maPhieuThuocPk(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 7);
    }

    public static String maPhieuTamUng(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 8);
    }

    public static String maHsthtoank(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 9);
    }

    public static String maPhieuBienBanHoiChan(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 10);
    }

    public static String maPhieuMienGiam(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 15);
    }

    public static String maPhieuKiemKe(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 17);
    }

    public static String maPhieuBaoAn(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 18);
    }

    public static String maPhieuGiaoBan(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 19);
    }

    public static String maBenhAnNgoai(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 20);
    }

    public static String maGiayCvNbBhyt(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 21);
    }

    public static String maPhieuKhamDtNgoaiTru(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 22);
    }

    public static String maPhieuChamSoc(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 23);
    }

    public static String maPhieuToDieuTri(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 24);
    }

    public static String maPhieuKhamChuyenKhoa(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 25);
    }

    public static String maPhieuKbVaoVien(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 26);
    }

    public static String maGiayCnSucKhoe(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 27);
    }

    public static String maPhieuTraNhacCungCap(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 28);
    }

    public static String maBenhAnTuvongTruoc(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 37);
    }

    public static String maPhieuXuatVien(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 38);
    }

    public static String maGiayChungNhan(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 39);
    }

    public static String maTomTatBenhAn(EntityManager em) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMaPhieu(em, 40);
    }

    /*
     * Thanh add 30/09/2011
     * not complete
     */
    public static List<TonKho> getTonKhoForTheKho(EntityManager em, String khoMa, String dmtMa) {

        String sSQL = " select z.TONKHO_MA from TON_KHO as z group by ";

        String groupBy = " z.TONKHO_MA, z.DMTHUOC_MASO, z.DMKHOA_MASO  ";

        sSQL += groupBy;

        String having = " having z.DMKHOA_MASO = (select DMKHOA_MASO from DM_KHOA where DMKHOA_MA like '" + khoMa + "'  ) ";

        having += " and z.DMTHUOC_MASO in  ( select  DMTHUOC_MASO from DM_THUOC where DMTHUOC_MA like '" + dmtMa + "'  ) ";


        sSQL += having;



        //System.out.println("sSQL:" + sSQL);
        Query q = em.createNativeQuery(sSQL);
        //q.setParameter("khoMa", khoMa);
        //q.setParameter("dmtMa", dmtMa);

        //System.out.println("sSQL:" + sSQL);
        List lstResult = q.getResultList();

        //System.out.println("getResultList:" + lstResult);

        ArrayList<Integer> sTonKhoMa = new ArrayList<Integer>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                String str = String.valueOf(lstResult.get(i));

                //System.out.println("str:" + str);

                //str = str.substring(1, str.length() - 1);

                //System.out.println("str:" + str);

                sTonKhoMa.add(Integer.parseInt(str));

            }
        }
        System.out.print("sTonKhoMa:" + sTonKhoMa);

        List<TonKho> lstTonkho = Utils.getTonKhoFromListOfMa(em, sTonKhoMa);

        List<String> lstString = new ArrayList<String>();
        List<TonKho> result = new ArrayList<TonKho>();
        for (TonKho tk : lstTonkho) {
            String malk = tk.getTonkhoMalienket();
            if (lstString.contains(malk)) {
                continue;
            }
            lstString.add(malk);
            result.add(tk);
        }
        return result;
    }

    public static List<TonKho> getTonKhoForTheKho(EntityManager em, Integer khoMaso, Integer dmThuocMaso, Date fromDate, Date toDate) {
        System.out.println("Begin getTonKhoForTheKho");
      //String sSQL = "select object(tk) from TonKho as tk where tk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso and (Date(tk.tonkhoNgaygiocn) between :tonkhoTuNgay and :tonkhoDenNgay ) group by tk.tonkhoMalienket";
        String sSQL = "select tk.* from ton_kho tk, (select max(tonkho_ma) tonkho_ma from ton_kho where dmkhoa_maso = :dmkhoaMaso and dmthuoc_maso = :dmthuocMaso and to_date(tonkho_ngaygiocn) <= :tonkhoDenNgay group by tonkho_malienket) tkmax " +
                "where tk.tonkho_ma = tkmax.tonkho_ma";
        Query q = em.createNativeQuery(sSQL,TonKho.class);
        //System.out.println("sSQL:" + sSQL);
        //q.setParameter("tonkhoTuNgay", fromDate);
        q.setParameter("tonkhoDenNgay", toDate);
        q.setParameter("dmkhoaMaso", khoMaso);
        q.setParameter("dmthuocMaso", dmThuocMaso);

        System.out.println("getTonKhoForTheKho: " + q.getResultList().size());
        List<TonKho> lstResult = q.getResultList();
        if (lstResult != null && lstResult.size()>0)
            return lstResult;
        else{//tuc khong xay ra giao dich ghi tang hay giam trong bang ton kho cua khoa (k co ngay cn nam trong pham vi tren) nhung so luong ton van >0 thi cho show ma lien ket do len
            lstResult = getTonKho(em, khoMaso, dmThuocMaso);
            return lstResult;
        }
    }

    public static List<TonKho> getTonKho(EntityManager em, Integer khoMaso, Integer dmThuocMaso)
    {
        String sSQL = "select object(tk) from TonKho as tk where tk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso and tk.tonkhoTon>0 group by tk.tonkhoMalienket";
        Query q = em.createQuery(sSQL);
        q.setParameter("dmkhoaMaso", khoMaso);
        q.setParameter("dmthuocMaso", dmThuocMaso);

        System.out.println("getTonKho: " + q.getResultList().size());
        List<TonKho> lstResult = q.getResultList();
        if (lstResult != null && lstResult.size()>0)
            return lstResult;
        else return null;
    }

    public static List<TonKho> getTonKhoByKhoaMaThuocMaso(EntityManager em, Integer khoMaso, Integer dmThuocMaso)
    {
        System.out.println("khoMaso: "+khoMaso);
        System.out.println("dmThuocMaso: "+dmThuocMaso);
        if(khoMaso == 0)//lay ton kho dua vao kho nao trong nhom2 cua bang phan loai thuoc
        {
            String sSQLKho = "select pnt.dmphanloaithuocNhom2 from DmThuoc dmt, DmPhanLoaiThuoc pnt where dmt.dmphanloaithuocMaso.dmphanloaithuocMaso = pnt.dmphanloaithuocMaso and dmt.dmthuocMaso = :dmthuocMaso";
            Query qKho = em.createQuery(sSQLKho);
            qKho.setParameter("dmthuocMaso", dmThuocMaso);
            String strDMKhoaMaso = qKho.getSingleResult().toString();
            khoMaso = Integer.parseInt(strDMKhoaMaso);
        }
        String sSQL = "select max(tk.tonkhoMa) from TonKho tk where tk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso group by tk.tonkhoMalienket";
        Query q = em.createQuery(sSQL);
        q.setParameter("dmkhoaMaso", khoMaso);
        q.setParameter("dmthuocMaso", dmThuocMaso);
        List lstResult = q.getResultList();
        ArrayList<Integer> sTonKhoMa = new ArrayList<Integer>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                String str = String.valueOf(lstResult.get(i));
                sTonKhoMa.add(Integer.parseInt(str));
            }
        }
        System.out.print("sTonKhoMa:" + sTonKhoMa);

        return Utils.getTonKhoFromListOfMa(em, sTonKhoMa);
    }

    /*
     * Thanh add 30/09/2011
     * not complete
     */
    public static List<TonKho> getTonKho(EntityManager em, String khoMa, String dmtMa) {

        String sSQL = " select max(z.TONKHO_MA) from TON_KHO as z group by ";

        String groupBy = " z.DMTHUOC_MASO, z.DMKHOA_MASO, z.DMNCT_MASO, z.DMQUOCGIA_MASO, z.TONKHO_DONGIA, z.TONKHO_NAMNHAP, z.TONKHO_NAMHANDUNG , z.DMNGUONKINHPHI_MASO , z.DMNHASANXUAT_MASO  ";

        sSQL += groupBy;

        String having = " having z.DMKHOA_MASO in (select DMKHOA_MASO from DM_KHOA where DMKHOA_MA like '" + khoMa + "'  ) ";

        having += " and z.DMTHUOC_MASO in  ( select  DMTHUOC_MASO from DM_THUOC where DMTHUOC_MA like '" + dmtMa + "'  ) ";


        sSQL += having;



        //System.out.println("sSQL:" + sSQL);
        Query q = em.createNativeQuery(sSQL);
        //q.setParameter("khoMa", khoMa);
        //q.setParameter("dmtMa", dmtMa);

        //System.out.println("sSQL:" + sSQL);
        List lstResult = q.getResultList();

        //System.out.println("getResultList:" + lstResult);

        ArrayList<Integer> sTonKhoMa = new ArrayList<Integer>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                String str = String.valueOf(lstResult.get(i));

                //System.out.println("str:" + str);

                //str = str.substring(1, str.length() - 1);

                //System.out.println("str:" + str);

                sTonKhoMa.add(Integer.parseInt(str));

            }
        }
        System.out.print("sTonKhoMa:" + sTonKhoMa);

        return Utils.getTonKhoFromListOfMa(em, sTonKhoMa);



    }

    /*
     * Thanh add 30/09/2011
     * Da kiem tra
     */
    public static List<TonKho> getTonKhoNew(EntityManager em, String khoMa, String dmtMa, String nguon, String kinhphi){
        System.out.println("Begin getTonKhoNew --------------");
        System.out.println("khoMa:" + khoMa);
        System.out.println("dmtMa:" + dmtMa);
        System.out.println("nguon:" + nguon);
        System.out.println("kinhphi:" + kinhphi);
        List<TonKho> result = new ArrayList<TonKho>();
        String sSQL = "SELECT TK.* FROM TON_KHO TK, "
                    +    "(SELECT MAX(TONKHO_MA) TONKHO_MA FROM TON_KHO TK1 "
                    +    "INNER JOIN DM_THUOC T ON TK1.DMTHUOC_MASO = T.DMTHUOC_MASO "
                    +    "INNER JOIN DM_KHOA K ON TK1.DMKHOA_MASO = K.DMKHOA_MASO "
                    +    "LEFT JOIN DM_NGUON_CHUONG_TRINH NCT ON TK1.DMNCT_MASO = NCT.DMNCT_MASO "
                    +    "LEFT JOIN DM_NGUON_KINH_PHI NKP ON TK1.DMNGUONKINHPHI_MASO = NKP.DMNGUONKINHPHI_MASO "
                    +    "WHERE K.DMKHOA_MA LIKE :khoMa AND T.DMTHUOC_MA = :dmtMa ";
        if (nguon != null && !nguon.equals("") && !"%".equals(nguon)) {
                sSQL +=  "AND NCT.DMNCT_MA LIKE :nguon ";
        }
        if (kinhphi != null && !kinhphi.equals("") && !"%".equals(kinhphi)) {
                sSQL +=  "AND NKP.DMNGUONKINHPHI_MA LIKE :kinhphi ";
        }
        sSQL +=          "GROUP BY TK1.TONKHO_MALIENKET) TKMAX "
                    + "WHERE TKMAX.TONKHO_MA = TK.TONKHO_MA AND TK.TONKHO_TON > 0";
        System.out.println("sSQL:" + sSQL);
        Query q = em.createNativeQuery(sSQL,TonKho.class);
        q.setParameter("khoMa", khoMa);
        q.setParameter("dmtMa", dmtMa);
        if (nguon != null && !"%".equals(nguon)) {
            q.setParameter("nguon", nguon);
        }

        if (kinhphi != null && !"%".equals(kinhphi)) {
            q.setParameter("kinhphi", kinhphi);
        }
        result = q.getResultList();
        System.out.println("End getTonKhoNew --------------"+ result);
        return result;
    }

    /*
     * Thanh add 30/09/2011
     * not complete: Ham nay khong su dung
     */
    public static List<TonKho> getTonKho(EntityManager em, String khoMa, String dmtMa,
            String nguon, String kinhphi, Integer quyCach, Double donGia) {

        //System.out.println("khoMa:" + khoMa);
        //System.out.println("dmtMa:" + dmtMa);
        //System.out.println("nguon:" + nguon);
        //System.out.println("kinhphi:" + kinhphi);
        //System.out.println("quyCach:" + quyCach);
        //System.out.println("donGia:" + donGia);

        String sSQL = " select max(z.TONKHO_MA) from TON_KHO as z group by ";

        String groupBy = " z.DMTHUOC_MASO , z.DMKHOA_MASO, z.DMNCT_MASO, z.DMQUOCGIA_MASO, z.TONKHO_DONGIA, z.TONKHO_NAMNHAP, z.TONKHO_NAMHANDUNG , z.DMNGUONKINHPHI_MASO , z.DMNHASANXUAT_MASO, z.TONKHO_MALIENKET ";


        sSQL += groupBy;

        String having = " having z.DMKHOA_MASO = (select DMKHOA_MASO from DM_KHOA where DMKHOA_MA like :khoMa ) ";

        if (nguon != null && !nguon.equals("") && !"%".equals(nguon)) {
            having += " and z.DMNCT_MASO in (select DMNCT_MASO from DM_NGUON_CHUONG_TRINH where DMNCT_MA like :nguon ) ";
        }

        if (kinhphi != null && !kinhphi.equals("") && !"%".equals(kinhphi)) {

            having += " and z.DMNGUONKINHPHI_MASO in  ( select  DMNGUONKINHPHI_MASO from DM_NGUON_KINH_PHI where DMNGUONKINHPHI_MA like :kinhphi ) ";

        }

        having += " and z.DMTHUOC_MASO in  ( select  DMTHUOC_MASO from DM_THUOC where DMTHUOC_MA like :dmtMa ) ";




//        if (quyCach != null) {
//            having += " and z.TONKHO_QUYCACH =  :quyCach";
//        }
        if (donGia != null) {
            having += " and z.TONKHO_DONGIA =  :donGia";
        }

        sSQL += having;

        //System.out.println("sSQL:" + sSQL);
        Query q = em.createNativeQuery(sSQL);



        //System.out.println("sSQL:" + sSQL);
        q.setParameter("khoMa", khoMa);
        if (nguon != null && !"%".equals(nguon)) {
            q.setParameter("nguon", nguon);

        }

        if (kinhphi != null && !"%".equals(kinhphi)) {
            q.setParameter("kinhphi", kinhphi);

        }
//        if (quyCach != null) {
//            q.setParameter("quyCach", quyCach);
//        }
        if (donGia != null) {
            q.setParameter("donGia", donGia);
        }
        q.setParameter("dmtMa", dmtMa);

        //System.out.println("sSQL:" + sSQL);
        List lstResult = q.getResultList();


        //System.out.println("getResultList:" + lstResult);

        ArrayList<Integer> sTonKhoMa = new ArrayList<Integer>();
        if (lstResult != null && lstResult.size() > 0) {
            for (int i = 0; i < lstResult.size(); i++) {
                String str = String.valueOf(lstResult.get(i));
                //System.out.println("str1 = " + str);
                //str = str.substring(1, str.length() - 1);
                //System.out.println("str2 = " + str);
                sTonKhoMa.add(Integer.parseInt(str));

            }
        }
        System.out.print("sTonKhoMa:" + sTonKhoMa);

        return Utils.getTonKhoFromListOfMa(em, sTonKhoMa);

    }

    public static List<TonKho> getTonKho_New(EntityManager em, Integer khoNhanMaso, String malk,
            Integer nguonMaso, Integer kinhphiMaso, Integer quyCach, Double donGia) {

        //System.out.println("khoa :" + khoNhanMaso);
        //System.out.println("Thuoc malk:" + malk);
        //System.out.println("nguon:" + nguonMaso);
        //System.out.println("kinhphi:" + kinhphiMaso);
        //System.out.println("quyCach:" + quyCach);
        //System.out.println("donGia:" + donGia);

        String sSQL = "select object(t) from TonKho t where 1 = 1 ";

        if ( nguonMaso > 0 ) {
            sSQL +=" and t.dmnctMaso.dmnctMaso = :dmnctMaso ";
        }

        if ( kinhphiMaso >0 ) {
            sSQL +=" and t.dmnguonkinhphiMaso.dmnguonkinhphiMaso = :dmnguonkinhphiMaso ";
        }

        if (donGia != null) {
            sSQL += " and t.tonkhoDongia = :tonkhoDongia ";
        }
        sSQL += " and t.tonkhoMa in (select max(tk.tonkhoMa) from TonKho tk where tk.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and tk.tonkhoMalienket = :tonkhoMalienket group by tk.tonkhoMalienket) ";

        //System.out.println("sSQL:" + sSQL);
        Query q = em.createQuery(sSQL);
        q.setParameter("dmkhoaMaso", khoNhanMaso);
        q.setParameter("tonkhoMalienket", malk);
        if ( nguonMaso > 0 ) {
            q.setParameter("dmnctMaso", nguonMaso);
        }

        if ( kinhphiMaso >0 ) {
            q.setParameter("dmnguonkinhphiMaso", kinhphiMaso);
        }

        if (donGia != null) {
            q.setParameter("tonkhoDongia", donGia);
        }
        List<TonKho> lstResult = q.getResultList();
        System.out.println("getResultList: ---Tho add --" + lstResult.size());        
        return lstResult;
    }

    /*
     * Thanh add 30/09/2011
     * not complete
     */
    /***
     *   lay ton kho theo ma so thuoc  va ma khoa
     *   cho ra 1 gia tri ton kho cuoi cung
     */
    public static double getTonKhoByMaThuocAndMaKhoa(EntityManager em, String maThuoc, String maKho) {
        StringBuffer sQuery = new StringBuffer();
        DmThuoc dmthuoc = (DmThuoc) DieuTriUtilDelegate.getInstance().findByMa(maThuoc, "DmThuoc", "dmthuocMa");
        DmKhoa dmkhoa = (DmKhoa) DieuTriUtilDelegate.getInstance().findByMa(maKho, "DmKhoa", "dmkhoaMa");
        double tonkho = 0;
        System.out.println("----- DmThuoc " +dmthuoc);
        System.out.println("----- dmkhoa " +dmkhoa);
        if (dmthuoc != null) {
//            sQuery.append(" select sum(z.TONKHO_TON) from ton_kho as z" +
//                    " where z.TONKHO_MA IN " +
//                    " (select max(o.TONKHO_MA) from ton_kho as o " +
//                    " where o.dmthuoc_maso= :maThuoc " +
//                    " and o.DMKHOA_MASO= :maKho " +
//                    " group by o.TONKHO_MALIENKET) " );
             sQuery.append(" select sum(TONKHO_TON) from (" +
                     "select * from (" +
                     "SELECT * FROM ton_kho WHERE DMKHOA_MASO = :maKho AND DMTHUOC_MASO = :maThuoc ORDER BY TONKHO_MA DESC" +
                     ") as temp group by TONKHO_MALIENKET" +
                     ") as temp1" );         
            //System.out.println("----- " +sQuery.toString());
            //System.out.println("----- maThuoc" +maThuoc);
            //System.out.println("----- maKho" +maKho);
            try {                
                Query q = em.createNativeQuery(sQuery.toString());                    
                q.setParameter("maThuoc", dmthuoc.getDmthuocMaso());                
                q.setParameter("maKho", dmkhoa.getDmkhoaMaso());                   
                List<Object> result = q.getResultList();               
                if (result == null || result.size() == 0) {                   
                    tonkho = 0;
                }
                else{
                    //System.out.println("----- list " + result);
                    //System.out.println("----- size " + result.size());
                    if ( result.get(0) != null ){
                        //System.out.println("----- q.getResultList().get(0) " + result.get(0));
                        tonkho = Double.parseDouble(result.get(0).toString());
                    }
                }
                System.out.println("-----Ton kho: "+tonkho);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tonkho;

    }

    /******************************************/
    public static List<TonKho> getTonKhoFromListOfMa(EntityManager em, ArrayList<Integer> tonkhomalist) {

        System.out.print("sTonKhoMa:" + tonkhomalist);
        if (tonkhomalist == null || tonkhomalist.size() == 0) {
            return null;
        }


        String tonkhoSQL = "select object(o) from TonKho as o where (";
        for (int i = 0; i < tonkhomalist.size(); i++) {
            if (i == 0) {
                tonkhoSQL += " o.tonkhoMa = ?" + tonkhomalist.get(i);
            } else {
                tonkhoSQL += " or o.tonkhoMa = ?" + tonkhomalist.get(i);
            }

        }
        tonkhoSQL += " ) ";

        //System.out.print("tonkhoSQL:" + tonkhoSQL);

        Query q = em.createQuery(tonkhoSQL);
        for (int i = 0; i < tonkhomalist.size(); i++) {
            q.setParameter(String.valueOf(tonkhomalist.get(i)), tonkhomalist.get(i));
        }

        List<TonKho> resultList = q.getResultList();
        //System.out.print("resultList:" + resultList);
        return resultList;
    }

   
    /**
     * 
     * 
     * @return
     */
    public static String getMaBenhNhan(EntityManager em) {
        //YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        //return yteSequenceFacadeLocal.getMa(0);

        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMa(em, 0);

//        try { // Call Web Service Operation
//
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService service = new com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService();
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWS port = service.getYTeSequenceWSPort();
//            // TODO initialize WS operation arguments here
//            int loaiMa = 0;
//            // TODO process result here
//            java.lang.String result = port.getMa(loaiMa);
//            System.out.println("Result = " + result);
//            return result;
//        } catch (Exception ex) {
//            return "-1";
//        }


    }

    /**
     * 
     * 
     * @return
     */
    public static String getMaTiepDon(EntityManager em) {
        //YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        //return yteSequenceFacadeLocal.getMa(1);
//        try { // Call Web Service Operation
//
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService service = new com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService();
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWS port = service.getYTeSequenceWSPort();
//            // TODO initialize WS operation arguments here
//            int loaiMa = 1;
//            // TODO process result here
//            java.lang.String result = port.getMa(loaiMa);
//            System.out.println("Result = " + result);
//            return result;
//        } catch (Exception ex) {
//            return "-1";
//        }
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMa(em, 1);
    }

    public static String formatMa(EntityManager em, String ma) {
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.formatMa(em, ma);
    }

    /**
     * @auhor thanhdo
     * @param ma
     * @return
     */
    public static String formatMaPhieu(String ma) {
        if (ma != null && ma.length() < Utils.LENGTH_MA_PHIEU) {
            String ketQua = "";

            Calendar cal = new GregorianCalendar();
            String sYear = String.valueOf(cal.get(Calendar.YEAR));

            ketQua += sYear;

            String sMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
            if (sMonth.length() == 1) {
                sMonth = "0" + sMonth;
            }
            ketQua += sMonth;
            while (ma.length() < Utils.LENGTH_MA_PHIEU - 6) {
                ma = "0" + ma;
            }
            ketQua += ma;

            return ketQua;
        } else {
            return ma;
        }

    }

    /**
     * 
     * 
     * @return
     */
    public static String getSoVaoVien(EntityManager em, int loaiMa) {
        //YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        //return yteSequenceFacadeLocal.getMa(2);
//        try { // Call Web Service Operation
//
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService service = new com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService();
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWS port = service.getYTeSequenceWSPort();
//            // TODO initialize WS operation arguments here
//            int loaiMa = 3;
//            // TODO process result here
//            java.lang.String result = port.getMa(loaiMa);
//            System.out.println("Result = " + result);
//            return result;
//        } catch (Exception ex) {
//            return "-1";
//        }
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMa(em, loaiMa);
    }

    /**
     * 
     * 
     * @return
     */
    public static String getSoLuuTru(EntityManager em) {
        //YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        //return yteSequenceFacadeLocal.getMa(3);
//        try { // Call Web Service Operation
//
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService service = new com.iesvn.yte.dieutri.service.yte.YTeSequenceWSService();
//            com.iesvn.yte.dieutri.service.yte.YTeSequenceWS port = service.getYTeSequenceWSPort();
//            // TODO initialize WS operation arguments here
//            int loaiMa = 4;
//            // TODO process result here
//            java.lang.String result = port.getMa(loaiMa);
//            System.out.println("Result = " + result);
//            return result;
//        } catch (Exception ex) {
//            return "-1";
//        }
        YteSequenceFacadeLocal yteSequenceFacadeLocal = new YteSequenceFacade();
        return yteSequenceFacadeLocal.getMa(em, 3);
    }

    public static void setInfor(PhieuDuTru phieuDt, EntityManager em) {//        if (phieuDt.getPhieudtPhphieu() != null && phieuDt.getPhieudtPhphieu().getDmphanloaiMa() != null && !phieuDt.getPhieudtPhphieu().getDmphanloaiMa().equals("")) {
//            DmPhanLoaiFacade facade = new DmPhanLoaiFacade();
//            facade.setEm(em);
//            phieuDt.setPhieudtPhphieu(facade.findByDmphanloaiMa(phieuDt.getPhieudtPhphieu().getDmphanloaiMa()));
//        }
//
//        if (phieuDt.getPhieudtMakho() != null && phieuDt.getPhieudtMakho().getDmkhoaMa() != null && !phieuDt.getPhieudtMakho().getDmkhoaMa().equals("")) {
//            DmKhoaFacade facade = new DmKhoaFacade();
//            facade.setEm(em);
//            phieuDt.setPhieudtMakho(facade.findByDmkhoaMa(phieuDt.getPhieudtMakho().getDmkhoaMa()));
//        }
//
//
//        if (phieuDt.getDmkhoaMaso() != null && phieuDt.getDmkhoaMaso().getDmkhoaMa() != null && !phieuDt.getDmkhoaMaso().getDmkhoaMa().equals("")) {
//            DmKhoaFacade facade = new DmKhoaFacade();
//            facade.setEm(em);
//            phieuDt.setDmkhoaMaso(facade.findByDmkhoaMa(phieuDt.getDmkhoaMaso().getDmkhoaMa()));
//        }
//        if (phieuDt.getDmKinhphi() != null && phieuDt.getDmKinhphi().getDmkinhphiMa() != null && !phieuDt.getDmKinhphi().getDmkinhphiMa().equals("")) {
//            DmKinhPhiFacade facade = new DmKinhPhiFacade();
//            facade.setEm(em);
//            phieuDt.setDmKinhphi(facade.findByDmkinhphiMa(phieuDt.getDmKinhphi().getDmkinhphiMa()));
//        }
    }

    /**
     * @author thanhdo
     * @param tiepdon
     * @param em
     */
    public static void setInfor(CtPhieuDt ctPhieuDT, EntityManager em) {//        if (ctPhieuDT.getDmthuocMaso() != null && ctPhieuDT.getDmthuocMaso().getDmthuocMa() != null && !ctPhieuDT.getDmthuocMaso().getDmthuocMa().equals("")) {
//            DmThuocFacade facade = new DmThuocFacade();
//            facade.setEm(em);
//            ctPhieuDT.setDmthuocMaso(facade.findByDmthuocMa(ctPhieuDT.getDmthuocMaso().getDmthuocMa()));
//        }
    }

    /**
     * @author thanhdo
     * @param tiepdon
     * @param em
     */
    public static void setInfor(TiepDon tiepdon, EntityManager em) {//        if (tiepdon.getTiepdonDonvigoi() != null && tiepdon.getTiepdonDonvigoi().getDmbenhvienMa() != null && !tiepdon.getTiepdonDonvigoi().getDmbenhvienMa().equals("")) {
//            DmBenhVienFacade facade = new DmBenhVienFacade();
//            facade.setEm(em);
//            tiepdon.setTiepdonDonvigoi(facade.findByDmbenhvienMa(tiepdon.getTiepdonDonvigoi().getDmbenhvienMa()));
//        }
//
//        if (tiepdon.getTiepdonMachdoanb0() != null && tiepdon.getTiepdonMachdoanb0().getDmbenhicdMa() != null && !tiepdon.getTiepdonMachdoanb0().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            tiepdon.setTiepdonMachdoanb0(facade.findByDmbenhicdMa(tiepdon.getTiepdonMachdoanb0().getDmbenhicdMa()));
//        }
//        if (tiepdon.getTiepdonTuvong() != null && tiepdon.getTiepdonTuvong().getDmbenhicdMa() != null && !tiepdon.getTiepdonTuvong().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            tiepdon.setTiepdonTuvong(facade.findByDmbenhicdMa(tiepdon.getTiepdonTuvong().getDmbenhicdMa()));
//        }
//
//        if (tiepdon.getTiepdonChvien() != null && tiepdon.getTiepdonChvien().getDmbenhvienMa() != null && !tiepdon.getTiepdonChvien().getDmbenhvienMa().equals("")) {
//            DmBenhVienFacade facade = new DmBenhVienFacade();
//            facade.setEm(em);
//            tiepdon.setTiepdonChvien(facade.findByDmbenhvienMa(tiepdon.getTiepdonChvien().getDmbenhvienMa()));
//        }
//
//        if (tiepdon.getTiepdonLydochvi() != null && tiepdon.getTiepdonLydochvi().getDmlydocvMa() != null && !tiepdon.getTiepdonLydochvi().getDmlydocvMa().equals("")) {
//            DmLyDoCvFacade facade = new DmLyDoCvFacade();
//            facade.setEm(em);
//            tiepdon.setTiepdonLydochvi(facade.findByDmlydocvMa(tiepdon.getTiepdonLydochvi().getDmlydocvMa()));
//        }
//        if (tiepdon.getTiepdonChkhoa() != null && tiepdon.getTiepdonChkhoa().getDmkhoaMa() != null && !tiepdon.getTiepdonChkhoa().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(tiepdon.getTiepdonChkhoa().getDmkhoaMa(), em);
//            tiepdon.setTiepdonChkhoa(dmKhoa);
//        }
//
//        if (tiepdon.getDiadiemMa() != null && tiepdon.getDiadiemMa().getDmdiadiemMa() != null && !tiepdon.getDiadiemMa().getDmdiadiemMa().equals("")) {
//            DmDiaDiemFacade facade = new DmDiaDiemFacade();
//            facade.setEm(em);
//            tiepdon.setDiadiemMa(facade.findByDmdiadiemMa(tiepdon.getDiadiemMa().getDmdiadiemMa()));
//        }
    }

    /**
     * @author thanhdo
     * @param thamkham
     * @param em
     */
    public static void setInfor(ThamKham thamkham, EntityManager em) {
//        if (thamkham.getBenhicd10() != null && thamkham.getBenhicd10().getDmbenhicdMa() != null && !thamkham.getBenhicd10().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            thamkham.setBenhicd10(facade.findByDmbenhicdMa(thamkham.getBenhicd10().getDmbenhicdMa()));
//        }
//
//        if (thamkham.getThamkhamDieutri() != null && thamkham.getThamkhamDieutri().getDmdieutriMa() != null && !thamkham.getThamkhamDieutri().getDmdieutriMa().equals("")) {
//            DmDieuTriFacade facade = new DmDieuTriFacade();
//            facade.setEm(em);
//            thamkham.setThamkhamDieutri(facade.findByDmdieutriMa(thamkham.getThamkhamDieutri().getDmdieutriMa()));
//        }
    }

    /**
     * @author thanhdo
     * @param hsba
     * @param em
     */
    public static void setInfor(Hsba hoSoBenhAn, EntityManager em) {//        if (hoSoBenhAn.getHsbaDonvigoi() != null && hoSoBenhAn.getHsbaDonvigoi().getDmbenhvienMa() != null && !hoSoBenhAn.getHsbaDonvigoi().getDmbenhvienMa().equals("")) {
//            DmBenhVienFacade facade = new DmBenhVienFacade();
//            facade.setEm(em);
//            hoSoBenhAn.setHsbaDonvigoi(facade.findByDmbenhvienMa(hoSoBenhAn.getHsbaDonvigoi().getDmbenhvienMa()));
//        }
//        if (hoSoBenhAn.getHsbaMachdoanbd() != null && hoSoBenhAn.getHsbaMachdoanbd().getDmbenhicdMa() != null && !hoSoBenhAn.getHsbaMachdoanbd().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAn.setHsbaMachdoanbd(facade.findByDmbenhicdMa(hoSoBenhAn.getHsbaMachdoanbd().getDmbenhicdMa()));
//        }
//        if (hoSoBenhAn.getHsbaMachdoantuyent() != null && hoSoBenhAn.getHsbaMachdoantuyent().getDmbenhicdMa() != null && !hoSoBenhAn.getHsbaMachdoantuyent().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAn.setHsbaMachdoantuyent(facade.findByDmbenhicdMa(hoSoBenhAn.getHsbaMachdoantuyent().getDmbenhicdMa()));
//        }
//
//        if (hoSoBenhAn.getHsbaKhoavaov() != null && hoSoBenhAn.getHsbaKhoavaov().getDmkhoaMa() != null && !hoSoBenhAn.getHsbaKhoavaov().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(hoSoBenhAn.getHsbaKhoavaov().getDmkhoaMa(), em);
//            hoSoBenhAn.setHsbaKhoavaov(dmKhoa);
//        }
//        if (hoSoBenhAn.getHsbaKhoadangdt() != null && hoSoBenhAn.getHsbaKhoadangdt().getDmkhoaMa() != null && !hoSoBenhAn.getHsbaKhoadangdt().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(hoSoBenhAn.getHsbaKhoadangdt().getDmkhoaMa(), em);
//            hoSoBenhAn.setHsbaKhoadangdt(dmKhoa);
//        }
//        if (hoSoBenhAn.getHsbaKhoarav() != null && hoSoBenhAn.getHsbaKhoarav().getDmkhoaMa() != null && !hoSoBenhAn.getHsbaKhoarav().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(hoSoBenhAn.getHsbaKhoarav().getDmkhoaMa(), em);
//            hoSoBenhAn.setHsbaKhoarav(dmKhoa);
//        }
//        if (hoSoBenhAn.getHsbaDieutri() != null && hoSoBenhAn.getHsbaDieutri().getDmdieutriMa() != null && !hoSoBenhAn.getHsbaDieutri().getDmdieutriMa().equals("")) {
//            DmDieuTriFacade facade = new DmDieuTriFacade();
//            facade.setEm(em);
//            hoSoBenhAn.setHsbaDieutri(facade.findByDmdieutriMa(hoSoBenhAn.getHsbaDieutri().getDmdieutriMa()));
//        }
//        
//         if (hoSoBenhAn.getHsbaTuvong() != null && hoSoBenhAn.getHsbaTuvong().getDmbenhicdMa() != null && !hoSoBenhAn.getHsbaTuvong().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAn.setHsbaTuvong(facade.findByDmbenhicdMa(hoSoBenhAn.getHsbaTuvong().getDmbenhicdMa()));
//        }
    }

    /**
     * @author thanhdo
     * @param hsbaCV
     * @param em
     */
    public static void setInfor(HsbaChuyenVien hsbaCV, EntityManager em) {
//        if (hsbaCV.getHsbacvChvienden() != null && hsbaCV.getHsbacvChvienden().getDmbenhvienMa() != null && !hsbaCV.getHsbacvChvienden().getDmbenhvienMa().equals("")) {
//            DmBenhVienFacade facade = new DmBenhVienFacade();
//            facade.setEm(em);
//            DmBenhVien dmbenhvien = facade.findByDmbenhvienMa(hsbaCV.getHsbacvChvienden().getDmbenhvienMa());
//            hsbaCV.setHsbacvChvienden(dmbenhvien);
//        }
//        if (hsbaCV.getHsbacvLydochuyenv() != null && hsbaCV.getHsbacvLydochuyenv().getDmlydocvMa() != null && !hsbaCV.getHsbacvLydochuyenv().getDmlydocvMa().equals("")) {
//            DmLyDoCvFacade facade = new DmLyDoCvFacade();
//            facade.setEm(em);
//
//            DmLyDoCv dmlydocv = facade.findByDmlydocvMa(hsbaCV.getHsbacvLydochuyenv().getDmlydocvMa());
//            hsbaCV.setHsbacvLydochuyenv(dmlydocv);
//        }
    }

    /**
     * @author thanhdo
     * @param hsbacm
     * @param em
     */
    public static void setInfor(HsbaChuyenMon hoSoBenhAnCM, EntityManager em) {//        if (hoSoBenhAnCM.getKhoaMa() != null && hoSoBenhAnCM.getKhoaMa().getDmkhoaMa() != null && !hoSoBenhAnCM.getKhoaMa().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(hoSoBenhAnCM.getKhoaMa().getDmkhoaMa(), em);
//
//            hoSoBenhAnCM.setKhoaMa(dmKhoa);
//        }
//        if (hoSoBenhAnCM.getHsbacmBenhchinh() != null && hoSoBenhAnCM.getHsbacmBenhchinh().getDmbenhicdMa() != null && !hoSoBenhAnCM.getHsbacmBenhchinh().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAnCM.setHsbacmBenhchinh(facade.findByDmbenhicdMa(hoSoBenhAnCM.getHsbacmBenhchinh().getDmbenhicdMa()));
//        }
//        if (hoSoBenhAnCM.getHsbacmTacnhan() != null && hoSoBenhAnCM.getHsbacmTacnhan().getDmbenhicdMa() != null && !hoSoBenhAnCM.getHsbacmTacnhan().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAnCM.setHsbacmTacnhan(facade.findByDmbenhicdMa(hoSoBenhAnCM.getHsbacmTacnhan().getDmbenhicdMa()));
//        }
//
//        if (hoSoBenhAnCM.getHsbacmBenhphu() != null && hoSoBenhAnCM.getHsbacmBenhphu().getDmbenhicdMa() != null && !hoSoBenhAnCM.getHsbacmBenhphu().getDmbenhicdMa().equals("")) {
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            hoSoBenhAnCM.setHsbacmBenhphu(facade.findByDmbenhicdMa(hoSoBenhAnCM.getHsbacmBenhphu().getDmbenhicdMa()));
//        }
//
//
//        if (hoSoBenhAnCM.getHsbacmHuongdieutri() != null && hoSoBenhAnCM.getHsbacmHuongdieutri().getDtdmhuongdtMa() != null && !hoSoBenhAnCM.getHsbacmHuongdieutri().getDtdmhuongdtMa().equals("")) {
//            DtDmHuongDtFacade facade = new DtDmHuongDtFacade();
//            facade.setEm(em);
//            hoSoBenhAnCM.setHsbacmHuongdieutri(facade.findByDtDmHuongDTMa(hoSoBenhAnCM.getHsbacmHuongdieutri().getDtdmhuongdtMa()));
//        }
//        if (hoSoBenhAnCM.getHsbacmChuyenkhoa() != null && hoSoBenhAnCM.getHsbacmChuyenkhoa().getDmkhoaMa() != null && !hoSoBenhAnCM.getHsbacmChuyenkhoa().getDmkhoaMa().equals("")) {
//            DmKhoa dmKhoa = DmKhoaFacade.findKhoaByMa(hoSoBenhAnCM.getHsbacmChuyenkhoa().getDmkhoaMa(), em);
//
//            hoSoBenhAnCM.setHsbacmChuyenkhoa(dmKhoa);
//        }
    }

    /**
     * @author thanhdo
     * @param kqMo
     * @param em
     */
    public static void setInfor(KetQuaMo kqMo, EntityManager em) {
//         System.out.println(" kqMo.getMacd1().getDmbenhicdMawww:" + kqMo.getMacd1().getDmbenhicdMa());    
//        if (kqMo.getMacd1() != null && kqMo.getMacd1().getDmbenhicdMa() != null && !kqMo.getMacd1().getDmbenhicdMa().equals("")) {
//
//            DmBenhIcdFacade facade = new DmBenhIcdFacade();
//            facade.setEm(em);
//            System.out.println(" kqMo.getMacd1().getDmbenhicdMawww:" + kqMo.getMacd1().getDmbenhicdMa());
//
//            DmBenhIcd dmBenhIcd = facade.findByDmbenhicdMa(kqMo.getMacd1().getDmbenhicdMa());
//             System.out.println(" dmBenhIcddmBenhIcddmBenhIcd:" + dmBenhIcd);
//            if (dmBenhIcd == null) {
//                kqMo.setMacd1(null);
//            } else {
//                kqMo.setMacd1(dmBenhIcd);
//            }
//

//            System.out.println(" kqMo.getMacd1().getDmbenhicdMawww2:" + kqMo.getMacd1().getDmbenhicdMaso());
//        }
    }

    /**
     * @author thanhdo
     * @param hsbaBhyt
     * @param em
     */
    public static void setInfor(HsbaNop hsbaNop, EntityManager em) {
    }

    /**
     * @author thanhdo
     * @param hsbaBhyt
     * @param em
     */
    public static void setInfor(HsbaBhyt hsbaBhyt, EntityManager em) {
    }

    /**
     * @author thanhdo
     * @param benhnhan
     * @param em
     */
    public static void setInfor(BenhNhan benhnhan, EntityManager em) {
//        if (benhnhan.getTinhMa() != null && benhnhan.getTinhMa().getDmtinhMa() != null && !benhnhan.getTinhMa().getDmtinhMa().equals("")) {
//            DmTinhFacade facade = new DmTinhFacade();
//            facade.setEm(em);
//            benhnhan.setTinhMa(facade.findByDmtinhMa(benhnhan.getTinhMa().getDmtinhMa()));
//        }
//
//        if (benhnhan.getHuyenMa() != null && benhnhan.getHuyenMa().getDmhuyenMa() != null && !benhnhan.getHuyenMa().getDmhuyenMa().equals("")) {
//            DmHuyenFacade facade = new DmHuyenFacade();
//            facade.setEm(em);
//            benhnhan.setHuyenMa(facade.findByDmhuyenMa(benhnhan.getHuyenMa().getDmhuyenMa()));
//        }
//        if (benhnhan.getXaMa() != null && benhnhan.getXaMa().getDmxaMa() != null && !benhnhan.getXaMa().getDmxaMa().equals("")) {
//            DmXaFacade facade = new DmXaFacade();
//            facade.setEm(em);
//            benhnhan.setXaMa(facade.findByDmxaMa(benhnhan.getXaMa().getDmxaMa()));
//        }
//
//        if (benhnhan.getBenhnhanNghe() != null && benhnhan.getBenhnhanNghe().getDmngheMa() != null && !benhnhan.getBenhnhanNghe().getDmngheMa().equals("")) {
//            DmNgheFacade facade = new DmNgheFacade();
//            facade.setEm(em);
//            benhnhan.setBenhnhanNghe(facade.findByDmngheMa(benhnhan.getBenhnhanNghe().getDmngheMa()));
//        }
    }

    /**
     * 
     * @param hsthanhtoan
     * @return
     */
    public static double getBenhNhanTra(HsThtoan hsthanhtoan) {
        return hsthanhtoan.getHsthtoanTongchi() - hsthanhtoan.getHsthtoanBhxh() - hsthanhtoan.getHsthtoanBhyt() - hsthanhtoan.getHsthtoanMiendt() - hsthanhtoan.getHsthtoanMienmau() - hsthanhtoan.getHsthtoanMienphong() - hsthanhtoan.getHsthtoanMiente() - hsthanhtoan.getHsthtoanMienthuoc() - hsthanhtoan.getHsthtoanMienthuoclao() - hsthanhtoan.getHsthtoanNgansach() - hsthanhtoan.getHsthtoanTamung() - hsthanhtoan.getHsthtoanXetgiam() + hsthanhtoan.getHsthtoanHoanthu() + hsthanhtoan.getHsthtoanHoanung() - hsthanhtoan.getHsthtoanKhongthu();
    }

    /**
     * 
     * @param hstt
     */
    public static void setInitInforHstt(HsThtoan hstt) {
        hstt.setHsthtoanBhxh(Double.valueOf(0));
        hstt.setHsthtoanBhyt(Double.valueOf(0));
        hstt.setHsthtoanBntra(Double.valueOf(0));
        hstt.setHsthtoanCls(Double.valueOf(0));
        hstt.setHsthtoanClsndm(Double.valueOf(0));
        hstt.setHsthtoanCongkham(Double.valueOf(0));
        hstt.setHsthtoanDm(Double.valueOf(0));
        hstt.setHsthtoanHoanthu(Double.valueOf(0));
        hstt.setHsthtoanHoanung(Double.valueOf(0));
        hstt.setHsthtoanKhongthu(Double.valueOf(0));
        hstt.setHsthtoanMiendt(Double.valueOf(0));
        hstt.setHsthtoanMienmau(Double.valueOf(0));
        hstt.setHsthtoanMienphong(Double.valueOf(0));
        hstt.setHsthtoanMiente(Double.valueOf(0));
        hstt.setHsthtoanMienthuoc(Double.valueOf(0));
        hstt.setHsthtoanMienthuoclao(Double.valueOf(0));
        hstt.setHsthtoanNdm(Double.valueOf(0));
        hstt.setHsthtoanNgansach(Double.valueOf(0));
        hstt.setHsthtoanPhauthuat(Double.valueOf(0));
        hstt.setHsthtoanPhauthuatndm(Double.valueOf(0));
        hstt.setHsthtoanPhong(Double.valueOf(0));
        hstt.setHsthtoanPhongndm(Double.valueOf(0));
        hstt.setHsthtoanTamung(Double.valueOf(0));
        hstt.setHsthtoanThatthu(Double.valueOf(0));
        hstt.setHsthtoanThtoan(Double.valueOf(0));
        hstt.setHsthtoanThuoc(Double.valueOf(0));
        hstt.setHsthtoanThuocndm(Double.valueOf(0));
        hstt.setHsthtoanTongchi(Double.valueOf(0));
        hstt.setHsthtoanVtth(Double.valueOf(0));
        hstt.setHsthtoanVtthndm(Double.valueOf(0));
        hstt.setHsthtoanXetgiam(Double.valueOf(0));
        hstt.setHsthtoanYeucau(Double.valueOf(0));
    }

    /**
     * Format so double sang chuoi
     * @param d
     * @param pattern
     * @return
     */
    public static String formatNumber(Double d, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }

    public static String thuNhoSoVaoVien(String soVaoVien) {
        soVaoVien = soVaoVien.substring(4);
        while (soVaoVien.startsWith("0")) {
            soVaoVien = soVaoVien.substring(1);
        }
        return soVaoVien;

    }

    public static int roundDoubleNumber(Double dNum) {
        if (dNum == null) return 0;
        double dVal = dNum.doubleValue();
        int iVal = dNum.intValue();
        if(dVal - iVal > 0) {
                iVal = iVal + 1;
        }
        return iVal;
    }

    public static Double roundDoublePattern(Double dNum, String pattern) {
        if (dNum == null) {
            return new Double(0);
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return new Double(df.format(dNum.doubleValue()));
    }

    public static int subtractDates(Date tuNgay, Date denNgay){
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String ngayVao = formatDate.format(tuNgay);
        String ngayRa = formatDate.format(denNgay);
        Long songay = new Long(0);
        try{
            Date tuNgay1 = formatDate.parse(ngayVao);
            Date denNgay1 = formatDate.parse(ngayRa);
            songay = (denNgay1.getTime() - tuNgay1.getTime()) / (60 * 60 * 1000L * 24) ;
        }catch(Exception er){
        }
        return songay.intValue();
    }
}


