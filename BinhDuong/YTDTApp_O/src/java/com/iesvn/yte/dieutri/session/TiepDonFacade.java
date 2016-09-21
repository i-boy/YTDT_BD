/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
import com.iesvn.yte.dieutri.entity.VBenhnhanNgtDattDetail;
import com.iesvn.yte.dieutri.entity.VClsKhamGroupbytiepdonSttnhexcel;
import com.iesvn.yte.dieutri.entity.VThuocDongyNgoaitruexcel;
import com.iesvn.yte.dieutri.entity.VThuocNgoaitruGroupbytiepdonSttnhexcel;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class TiepDonFacade implements TiepDonFacadeLocal, TiepDonFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(TiepDon tiepDon) {
        em.persist(tiepDon);
    }

    public void edit(TiepDon tiepDon) {
        em.merge(tiepDon);
    }

    public void remove(TiepDon tiepDon) {
        em.remove(em.merge(tiepDon));
    }

    public TiepDon find(Object id) {
//        String maTiepDon = (String) id;
//        System.out.println("------------ma tiep don nhap: " + maTiepDon);
//        maTiepDon = Utils.formatMa(getEm(), maTiepDon);
//        System.out.println("------------ma tiep don nhap sau khi format: " + maTiepDon);
//        System.out.println("------------tim tiepdon obj");
//
//        return getEm().find(com.iesvn.yte.dieutri.entity.TiepDon.class, maTiepDon);
        String tiepdonMa = String.valueOf(id);
        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);
        String sql = "select object(t) From TiepDon as t where t.tiepdonMa = :tiepdonMa";

        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonMa", tiepdonMa);
        List<TiepDon> lstTiepDon = q.getResultList();
        if (lstTiepDon != null && lstTiepDon.size() > 0) {
            return (TiepDon) lstTiepDon.get(0);
        }
        return null;
    }

    public TiepDon findByTiepdonMa(String tiepdonMa) {
        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);
        String sql = "select object(t) From TiepDon as t where t.tiepdonMa = :tiepdonMa";

        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonMa", tiepdonMa);
        return (TiepDon) q.getSingleResult();
    }
    //Tho add, de phuc vu cho viec tim kiem Tiep Don tuong ung voi kho xuat thuoc ke toa quay BV

    public TiepDon findByTiepdonMa_Kho(String tiepdonMa, Integer khoMaSo) {
        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);
        String sql = "select object(td) From TiepDon as td, ThamKham as tk, ThuocPhongKham as tpk " +
                "where td.tiepdonMa = tk.tiepdonMa.tiepdonMa " +
                "and tk.thamkhamMa = tpk.thuocphongkhamThamkham.thamkhamMa " +
                "and td.tiepdonMa = :tiepdonMa and tpk.thuocphongkhamKhoa.dmkhoaMaso = :dmkhoaMaso";
        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonMa", tiepdonMa);
        q.setParameter("dmkhoaMaso", khoMaSo);
        List<TiepDon> lstTiepDon = q.getResultList();
        if (lstTiepDon != null && lstTiepDon.size() > 0) {
            return (TiepDon) lstTiepDon.get(0);
        }
        return null;
    }

    /**
     * 
     * @param sothebhyt
     * @param ngayTiepDon
     * @return
     */
    public TiepDon testBHYTTrungTrongNgay(String tiepdonSothebh, Date tiepdonNgaygio) {

        System.out.println("tiepdonSothebh:" + tiepdonSothebh);
        System.out.println("tiepdonNgaygio:" + tiepdonNgaygio);

        Date dDauNgay = new Date();
        dDauNgay.setTime(tiepdonNgaygio.getTime());

        Date dCuoiNgay = new Date();
        dCuoiNgay.setTime(tiepdonNgaygio.getTime());
        dCuoiNgay.setHours(23);
        dCuoiNgay.setMinutes(59);
        dCuoiNgay.setSeconds(59);


        String sql = "select object(t) From TiepDon as t where t.tiepdonSothebh = :tiepdonSothebh and t.tiepdonNgaygio >= :dDauNgay and t.tiepdonNgaygio <= :dCuoiNgay";

        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonSothebh", tiepdonSothebh);
        q.setParameter("dDauNgay", dDauNgay);
        q.setParameter("dCuoiNgay", dCuoiNgay);


        List lstTD = q.getResultList();
        if (lstTD != null && lstTD.size() > 0) {
            return (TiepDon) lstTD.get(0);
        }
        return null;
    }

    public TiepDon getTiepDonWithSoTheBHYTLast(String tiepdonSothebh) {


        String sql = "select object(t) From TiepDon as t where t.tiepdonSothebh = :tiepdonSothebh and t.tiepdonNgaygio is not null order by t.tiepdonNgaygio DESC";

        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonSothebh", tiepdonSothebh);


        List lstTD = q.getResultList();
        if (lstTD != null && lstTD.size() > 0) {
            return (TiepDon) lstTD.get(0);
        }
        return null;
    }

    public List<TiepDon> findFinalByBenhNhan(String bnMa) {
        bnMa = Utils.formatMa(em, bnMa);
        String sql = "select object(t) From TiepDon as t where t.benhnhanMa.benhnhanMa = :bnMa order by t.tiepdonMa DESC limit 1";
        Query q = getEm().createQuery(sql);
        q.setParameter("bnMa", bnMa);
        return q.getResultList();
    }

    public List<TiepDon> findAll() {
        return em.createQuery("select object(o) from TiepDon as o").getResultList();
    }

    public List<TiepDon> findTiepDonByLoaiKhoaMaAndTuNgayAndDenNgay(String dtdmLoaiKhoaMa, Date tuNgay, Date denNgay) { //note: loai khoa (not khoa )

        Query q = getEm().createQuery("select object(o) from TiepDon as o where ( o.tiepdonNgaygio between :tuNgay and :denNgay ) and o.tiepdonBankham.dtdmLoaiKhoa.dtdmloaikhoaMa like :dtdmLoaiKhoaMa ");
        q.setParameter("tuNgay", tuNgay);
        q.setParameter("denNgay", denNgay);
        q.setParameter("dtdmLoaiKhoaMa", dtdmLoaiKhoaMa);
        return q.getResultList();
    }

    /*
     * Thanh add 05/10/2011
     * Hàm không sử dụng, không cần sửa
     */
    public List<TiepDon> findTiepDonByBanKhamMaAndNgay(String banKhamMa, Date ngayThamKham) {

        try {
            if (banKhamMa == null || banKhamMa.equals("")) {
                banKhamMa = "%";
            }

            SimpleDateFormat formatterSQL;

            formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
            String ngayGio = formatterSQL.format(ngayThamKham);

            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            String sDauNgay = ngayGio + " 00:00:00";
            String sCuoiNgay = ngayGio + " 23:59:59";

            Date dDauNgay = formatter.parse(sDauNgay);
            Date dCuoiNgay = formatter.parse(sCuoiNgay);

            Query q = getEm().createQuery("select object(o) from TiepDon as o where ( o.tiepdonNgaygio >= :dDauNgay  and o.tiepdonNgaygio <= :dCuoiNgay) and o.tiepdonBankham.dtdmbankhamMa like :banKhamMa order by o.tiepdonMa  ");
            q.setParameter("dDauNgay", dDauNgay);
            q.setParameter("dCuoiNgay", dCuoiNgay);
            q.setParameter("banKhamMa", banKhamMa);
            return q.getResultList();
        } catch (Exception ex) {

            return null;
        }
    }

    /*
     * Thanh add 05/10/2011
     * Hàm không sử dụng, không cần sửa
     */
    public List<TiepDon> findTiepDonByNgay(Date ngayThamKham) {

        try {
            String banKhamMa = "%";

            SimpleDateFormat formatterSQL;

            formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
            String ngayGio = formatterSQL.format(ngayThamKham);

            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            String sDauNgay = ngayGio + " 00:00:00";
            String sCuoiNgay = ngayGio + " 23:59:59";

            Date dDauNgay = formatter.parse(sDauNgay);
            Date dCuoiNgay = formatter.parse(sCuoiNgay);

            Query q = getEm().createQuery("select object(o) from TiepDon as o where ( o.tiepdonNgaygio >= :dDauNgay  and o.tiepdonNgaygio <= :dCuoiNgay) and o.tiepdonBankham.dtdmbankhamMa like :banKhamMa order by o.tiepdonMa  ");
            q.setParameter("dDauNgay", dDauNgay);
            q.setParameter("dCuoiNgay", dCuoiNgay);
            q.setParameter("banKhamMa", banKhamMa);
            return q.getResultList();
        } catch (Exception ex) {

            return null;
        }
    }

    /**
     * 
     * @param td
     * @return
     */
    public String createTiepDon(TiepDon td) {
        String result = "";
        BenhNhan bn = td.getBenhnhanMa();
        try {
            String bnMa = bn.getBenhnhanMa() == null ? "" : bn.getBenhnhanMa();
            System.out.println("benhnhanma:" + bnMa);
            System.out.println("tiepdonMa:" + td.getTiepdonMa());
            //BenhNhanFacade bnFacade = new BenhNhanFacade();
            //bnFacade.setEm(em);
            BenhNhan bnFound = em.find(BenhNhan.class, bnMa);

            if (bnFound != null) {
                //if (!("".equals(bnMa))) {
                System.out.println("tim thay benh nhan " + bn.getBenhnhanMa());
//                if (bn.getBenhnhanLanvao() != null ) {
//                    if (td.getTiepdonMa() == null || td.getTiepdonMa().equals("")) {
//                        int lanvao = bn.getBenhnhanLanvao();
//                        bn.setBenhnhanLanvao( new Short ( String.valueOf( lanvao + 1)));
//                    }
//                    
//                }
                //bn.setBenhnhanLanvao(bn.getBenhnhanLanvao() + 1);
                getEm().merge(bn);
                System.out.println("cap nhat benh nhan " + bn.getBenhnhanMa());
            } else {

                if ("".equals(bnMa)) {
                    bnMa = Utils.getMaBenhNhan(em);
                }
                bn.setBenhnhanMa(bnMa);
                System.out.println("-----bnMa: " + bnMa);
                System.out.println("-----bn: " + bn);
//                bn.setBenhnhanLanvao(new Short("1"));

                getEm().persist(bn);
                System.out.println("them benh nhan " + bn.getBenhnhanMa());
            }
            String tdMa = td.getTiepdonMa() == null ? "" : td.getTiepdonMa();

            //TiepDonFacade tdFacade = new TiepDonFacade();
            //tdFacade.setEm(em);
            TiepDon tdFound = em.find(TiepDon.class, tdMa);
            System.out.println("-----tdFound: " + tdFound);
            if (tdFound != null) {
                //if (!("".equals(tdMa))) {
                System.out.println("tim thay tiep don" + tdMa);
                //td.setBenhnhanMa(bn);
                getEm().merge(td);
                System.out.println("cap nhat tiep don" + tdMa);
            } else {
                if ("".equals(tdMa)) {
                    tdMa = Utils.getMaTiepDon(em);
                }
                System.out.println("----- " + tdMa);
                td.setTiepdonMa(tdMa);
//                td.setBenhnhanMa(bn);
                getEm().persist(td);
                //getEm().flush();
                System.out.println("them tiep don " + td.getTiepdonMa());
            }

            // luu tru tham kham voi ban kham
            // them vao cls bang gia
            ThamKhamFacade tkFacade = new ThamKhamFacade();
            tkFacade.setEm(em);
            //ThamKham thamKham = tkFacade.findByBanKhamVaMaTiepDon(td.getTiepdonBankham().getDtdmbankhamMa(), td.getTiepdonMa());
            ThamKham thamKham = tkFacade.findByMaTiepDon(td.getTiepdonMa());
            if (thamKham == null) { // Truơng hop them moi tiep don
                thamKham = new ThamKham();
                thamKham.setTiepdonMa(td);
                // phuc.lc 22-10-2010 : Ngay gio cua tham kham lay theo ngay gio tiep don, khong lay ngay he thong
                //thamKham.setThamkhamNgaygio(new Date());
                thamKham.setThamkhamNgaygio(td.getTiepdonNgaygio());
                thamKham.setThamkhamBankham(td.getTiepdonBankham());
                em.persist(thamKham);
                System.out.println("After insert tham kham, thamKham =  " + thamKham);
            } else { // Truơng hop cap nhat tiep don
                List<ThamKham> listTk = tkFacade.findAllByMaTiepDon(td.getTiepdonMa());
                // Truong hop tiep don co nhieu tham kham thi khong can cap nhat lai tham kham (dac biet la thong tin ban kham)
                // vi luc nay tren giao dien thong tin ban kham da bi khoa, khong cho thay doi
                if (listTk != null && listTk.size() == 1) {
                    thamKham = listTk.get(0);
                    thamKham.setTiepdonMa(td);
                    thamKham.setThamkhamNgaygio(td.getTiepdonNgaygio());
                    thamKham.setThamkhamBankham(td.getTiepdonBankham());
                    em.merge(thamKham);
                    System.out.println("After update tham kham, thamKham =  " + thamKham);
                } else {
                    // Truong hop co nhieu hon 1 tham kham thi khong cap nhat gi het
                }
            }

            
                ClsKham clsKham = new ClsKham();

                DieuTriUtilFacade dieutriUtilFacade = new DieuTriUtilFacade();
                dieutriUtilFacade.setEm(em);
                DtDmClsBangGia banggia = (DtDmClsBangGia) dieutriUtilFacade.findByMa(td.getTiepdonLoaikham(), "DtDmClsBangGia", "dtdmclsbgMa");

                clsKham.setClskhamMahang(banggia);
                clsKham.setClskhamThamkham(thamKham);
                clsKham.setClskhamLan(new Short("1"));
                // phuc.lc 12-10-2010 : set thong tin loai CLS de tinh vien phi
                if (banggia != null) {
                    clsKham.setClskhamMaloai(banggia.getDtdmclsbgMaloai());
                    clsKham.setClskhamLoai(banggia.getDtdmclsbgMaloai().getDtdmclsMa());
                    clsKham.setClskhamPhandv(banggia.getDtdmclsbgPhandv());
                    clsKham.setClskhamDongiabh(banggia.getDtdmclsbgDongiabh());
                    clsKham.setClskhamNdm(banggia.getDtdmclsbgNDM());
                }
                // if ("KSK03".equals(td.getTiepdonLoaikham())){
                //        clsKham.setClskhamDongia(new Double(0));
                // }else{
                //Calendar cal = Calendar.getInstance();
                // cal.setTime(td.getTiepdonNgaygio());
                Double donGiaChinhXac;
                // phuc.lc 19-10-2010 : Tam thoi khong xet gia thu 7 & CN
                // if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                //         cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) &&
                //         banggia.getDtdmclsbgMa().equals("KB")) {
                // lay gi la o cot gia2
                //     donGiaChinhXac = banggia.getDtdmclsbgDongia2();
                // } else {
                //donGiaChinhXac = banggia.getDtdmclsbgDongia();
                donGiaChinhXac = new Double(td.getTiepdonBntra().intValue());
                //}
                clsKham.setClskhamDongia(donGiaChinhXac);
                clsKham.setClskhamKtcao(false);
                clsKham.setClskhamYeucau(false);
                clsKham.setClskhamMien(false);
                //  }
                // phuc.lc 22-10-2010 : Ngay gio cua CLS lay theo ngay gio tiep don, khong lay ngay he thong
                //clsKham.setClskhamNgaygio(new Date());
                //clsKham.setClskhamNgaygiocn(new Date());
                clsKham.setClskhamNgaygio(td.getTiepdonNgaygio());
                clsKham.setClskhamNgaygiocn(td.getTiepdonNgaygio());

                if (!"BH".equals(td.getDoituongMa(true).getDmdoituongMa()) &&
                        !"MP".equals(td.getDoituongMa(true).getDmdoituongMa()) &&
                        !"TE".equals(td.getDoituongMa(true).getDmdoituongMa())) { // ko phai doi tuong BH

                    DieuTriUtilFacade dieuTriUtilFacade = new DieuTriUtilFacade();
                    dieuTriUtilFacade.setEm(em);

                    DmKhoa khoaKham = (DmKhoa) dieuTriUtilFacade.findByMa("KHA", "DmKhoa", "dmkhoaMa");
                    if (khoaKham != null) {
                        clsKham.setClskhamKhoa(khoaKham);
                    }

                    clsKham.setClskhamDongiabntra(clsKham.getClskhamDongia());

                    clsKham.setClskhamThungan(td.getTiepdonThungan());

                    DtDmCum cumtiepdon = (DtDmCum) dieuTriUtilFacade.findByMa(td.getTiepdonCum(), "DtDmCum", "dtdmcumMa");
                    clsKham.setClskhamCum(cumtiepdon);

                    clsKham.setClskhamKyHieu(td.getTiepdonKyhieu());
                    clsKham.setClskhamQuyen(td.getTiepdonQuyen());
                    clsKham.setClskhamBienLai(td.getTiepdonBienlai());

                    clsKham.setClskhamMaphieu(Utils.createMaPhieu(em));

                    clsKham.setClskhamDatt(true);
                }
            ClsKhamFacade clsKhamFacade = new ClsKhamFacade();
            clsKhamFacade.setEm(em);
            ClsKham clsKham_tmp = clsKhamFacade.findByBanKhamVaMaTiepDonVaKham(td.getTiepdonBankham().getDtdmbankhamMa(), td.getTiepdonMa());
            // phuc.lc : 03/11/2011 : Fix bug 4070
            if (clsKham_tmp == null) {
                em.persist(clsKham);
            } else {
                clsKham.setClskhamMa(clsKham_tmp.getClskhamMa());
                em.merge(clsKham);
                capnhatGiaClsTheoThoiGianBaoHiem(td);
            }

            result = td.getTiepdonMa();
        } catch (Exception ex) {
            result = "";
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    private boolean checkCanDelete(TiepDon td) {


        // tim cls ko phai kha'm
        ClsKhamFacade clsKham = new ClsKhamFacade();
        clsKham.setEm(em);
        List<ClsKham> lstClsKham = clsKham.findByTiepdonMaVaLoaiClsKham(td.getTiepdonMa());

        if (lstClsKham != null && lstClsKham.size() > 0) {
            return false;
        }

        ThuocPhongKhamFacade tpkFacade = new ThuocPhongKhamFacade();
        tpkFacade.setEm(em);
        List<ThuocPhongKham> lstTPK = tpkFacade.findByMaTiepDon(td.getTiepdonMa(), "1");

        if (lstTPK != null && lstTPK.size() > 0) {
            return false;
        }

        lstTPK = tpkFacade.findByMaTiepDon(td.getTiepdonMa(), "2");

        if (lstTPK != null && lstTPK.size() > 0) {
            return false;
        }

        lstTPK = tpkFacade.findByMaTiepDon(td.getTiepdonMa(), "3");

        if (lstTPK != null && lstTPK.size() > 0) {
            return false;
        }



        return true;
    }

    public String delHuyKham(TiepDon td) {
        String result = "";
        boolean canDelete = checkCanDelete(td);

        if (canDelete == false) {
            return "";
        }
        System.out.print("*****Xoa ma tiep don: " + td.getTiepdonMa());

        try {

            HsThtoankFacade hstoankFace = new HsThtoankFacade();
            hstoankFace.setEm(em);
            HsThtoank objHsThtoank = hstoankFace.findAllBytiepdonMa(td.getTiepdonMa());
            if (objHsThtoank != null) {
                em.remove(objHsThtoank);
            }

            ClsKhamFacade clsKham = new ClsKhamFacade();
            clsKham.setEm(em);
            List<ClsKham> lstClsKham = clsKham.findByTiepdonma(td.getTiepdonMa());

            if (lstClsKham != null && lstClsKham.size() > 0) {
                for (ClsKham clskham : lstClsKham) {
                    em.remove(clskham);
                }
            }


            ThamKhamFacade tkFacade = new ThamKhamFacade();
            tkFacade.setEm(em);
            List<ThamKham> lstthamKham = tkFacade.findAllByMaTiepDon(td.getTiepdonMa());
            if (lstthamKham != null && lstthamKham.size() > 0) {
                for (ThamKham tk : lstthamKham) {
                    em.remove(tk);
                }
            }

            HsThtoankBackupFacade hstoankBKFace = new HsThtoankBackupFacade();
           hstoankBKFace.setEm(em);
            HsThtoankBackup objHsThtoankBK = hstoankBKFace.findBytiepdonMa(td.getTiepdonMa(), 0);
            if (objHsThtoankBK != null) {
                em.remove(objHsThtoankBK);
            }

            TiepDon tiepDon = getEm().find(TiepDon.class, td.getTiepdonMa());
            if (tiepDon != null) {
                getEm().remove(tiepDon);
                result = td.getTiepdonMa();
            }
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
        }
        return result;
    }

    public String dangkyTiepDonCapCuu(TiepDon tiepdon, ThamKham thamkham) {
        System.out.println("-- dangkyTiepDonCapCuu");
        BenhNhan bn = tiepdon.getBenhnhanMa();
        if (bn == null) {
            BenhNhan _bn = new BenhNhan(Utils.getMaBenhNhan(em));
            em.persist(_bn);
            tiepdon.setBenhnhanMa(_bn);
        } else {
            if (em.find(BenhNhan.class, bn.getBenhnhanMa()) != null) {
                em.merge(bn);
            } else {
                bn.setBenhnhanMa(Utils.getMaBenhNhan(em));
                em.persist(bn);
            }
        }
        System.out.println("-- create benh nhan cho tiep don: " + bn.getBenhnhanMa());
        String _tdMa = tiepdon.getTiepdonMa();
        if (_tdMa == null) {
            tiepdon.setTiepdonMa(Utils.getMaTiepDon(em));
            em.persist(tiepdon);
        } else {
            if (em.find(TiepDon.class, _tdMa) != null) {
                em.merge(tiepdon);
            } else {
                tiepdon.setTiepdonMa(Utils.getMaTiepDon(em));
                em.persist(tiepdon);
            }
        }
        System.out.println("-- create tiep don: " + tiepdon.getTiepdonMa());



       









        // luu tru tham kham voi ban kham
        // them vao cls bang gia
       /* ThamKhamFacade tkFacade = new ThamKhamFacade();
        tkFacade.setEm(em);
        ThamKham thamKham = tkFacade.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
        if (thamKham == null) {
            thamKham = new ThamKham();
            thamKham.setTiepdonMa(tiepdon);

            // 20101125 bao.ttc: set ngay gio tham kham la ngay gio tiep don, them thong tin TiepdonBacsi vao thamkham
            if (tiepdon.getTiepdonNgaygio() != null) {
                thamKham.setThamkhamNgaygio(tiepdon.getTiepdonNgaygio());
            } else {
                thamKham.setThamkhamNgaygio(new Date());
            }
            thamKham.setThamkhamBankham(tiepdon.getTiepdonBankham());
            thamKham.setThamkhamBacsi(tiepdon.getTiepdonBacsi());
            em.persist(thamKham);
        }
        *
        */
        
        // luu tru tham kham voi ban kham
            // them vao cls bang gia
        ThamKhamFacade tkFacade = new ThamKhamFacade();
        tkFacade.setEm(em);
        //ThamKham thamKham = tkFacade.findByBanKhamVaMaTiepDon(tiepdon.getTiepdonBankham().getDtdmbankhamMa(), tiepdon.getTiepdonMa());
        ThamKham thamKham = tkFacade.findByMaTiepDon(tiepdon.getTiepdonMa());
        if (thamKham == null) { // Truơng hop them moi tiep don
            thamKham = new ThamKham();
         
                
				thamKham.setTiepdonMa(tiepdon);
                if (tiepdon.getTiepdonNgaygio() != null) {
                    thamKham.setThamkhamNgaygio(tiepdon.getTiepdonNgaygio());
                } else {
                    thamKham.setThamkhamNgaygio(new Date());
                }
				thamKham.setThamkhamBankham(tiepdon.getTiepdonBankham());
                thamKham.setThamkhamBacsi(tiepdon.getTiepdonBacsi());
                em.persist(thamKham);
                System.out.println("After update tham kham, thamKham =  " + thamKham);
            } else {// Truơng hop cap nhat tiep don
                List<ThamKham> listTk = tkFacade.findAllByMaTiepDon(tiepdon.getTiepdonMa());
                // Truong hop tiep don co nhieu tham kham thi khong can cap nhat lai tham kham (dac biet la thong tin ban kham)
                // vi luc nay tren giao dien thong tin ban kham da bi khoa, khong cho thay doi
                if (listTk != null && listTk.size() == 1) {
                    thamKham = listTk.get(0);
                    thamKham.setTiepdonMa(tiepdon);
                    thamKham.setThamkhamNgaygio(tiepdon.getTiepdonNgaygio());
                    thamKham.setThamkhamBankham(tiepdon.getTiepdonBankham());
                     thamKham.setThamkhamBacsi(tiepdon.getTiepdonBacsi());
                    em.merge(thamKham);
                    System.out.println("After update tham kham, thamKham =  " + thamKham);
                } else {
                    // Truong hop co nhieu hon 1 tham kham thi khong cap nhat gi het
                }
            }
        
        capnhatGiaClsTheoThoiGianBaoHiem(tiepdon);
        return tiepdon.getTiepdonMa();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public SessionContext getContext() {
        return context;
    }

    public void setContext(SessionContext context) {
        this.context = context;
    }

    public TiepDon findTiepDonCCByMaTiepDon(String ma, String maBanKham) {
        System.out.println("---findTiepDonCCByMaTiepDon");
        try {
            ma = Utils.formatMa(em, ma);
            String sql = "select object(t) From TiepDon as t where t.tiepdonMa = :ma and t.tiepdonBankham.dtdmbankhamMa = :maBanKham";
            Query q = getEm().createQuery(sql);
            q.setParameter("ma", ma);
            q.setParameter("maBanKham", maBanKham);
            List<TiepDon> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return (TiepDon) list.get(0);
            }
        } catch (Exception ex) {
            System.out.println("---findTiepDonCCByMaTiepDon Err: " + ex);
            return null;
        }
        return null;
    }

    public TiepDon findTiepDonCCByMaBenhNhan(String ma, String maBanKham) {
        System.out.println("---findTiepDonCCByMaBenhNhan");
        try {
            ma = Utils.formatMa(em, ma);
            System.out.println("---format maBenhNhan: " + ma);
            String sql = "select object(t) From TiepDon as t where t.benhnhanMa.benhnhanMa = :ma and t.tiepdonBankham.dtdmbankhamMa = :maBanKham order by t.tiepdonMa DESC limit 1";
            Query q = getEm().createQuery(sql);
            q.setParameter("ma", ma);
            q.setParameter("maBanKham", maBanKham);
            List<TiepDon> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return (TiepDon) list.get(0);
            }
        } catch (Exception ex) {
            System.out.println("---findTiepDonCCByMaBenhNhan Err: " + ex);
        }
        return null;
    }

    public List<TiepDon> findTiepDonForTimKiemBN(String mabn, String ten, String matd) {
        if (mabn != null) {
            mabn = Utils.formatMa(em, mabn);
        }
        if (matd != null) {
            matd = Utils.formatMa(em, matd);
        }
        String sql = "select object(o) from TiepDon as o where " +
                "(:matd is null or o.tiepdonMa = :matd) " +
                "and (:mabn is null or o.benhnhanMa.benhnhanMa = :mabn) " +
                "and (:ten is null or lower(o.benhnhanMa.benhnhanHoten) like concat('',lower(:ten)) ) " +
                "order by o.benhnhanMa.benhnhanHoten";
        // 20101207 bao.ttc: chuoi search dua vao da duoc chia theo Ho, Ten va add space de gioi han KQ tim kiem
        //"and (:ten is null or lower(o.benhnhanMa.benhnhanHoten) like concat('%',lower(:ten),'%') )";

        Query q = em.createQuery(sql);
        q.setParameter("matd", matd);
        q.setParameter("mabn", mabn);
        q.setParameter("ten", ten);

        return q.getResultList();
    }

    /*
     * Thanh add 05/10/2011
     * Date ok
     */
    // phuc.lc
    // Ham cap nhat gia CLS da chi dinh khi tiep don ngoai tru va cap cuu

    public void capnhatGiaClsTheoThoiGianBaoHiem(TiepDon td) {
        // phuc.lc : 27-05-2011
        // Truong hop chinh sua thong tin tiep don, thi co the cung thay doi thoi gian bao hiem, 
        // khi do phai kiem tra cac CLS da chi dinh de cap nhat lai don gia
        // vi thoi gian chi dinh can lam sang khi het han bao hiem va con han bao hiem co don gia khac nhau
        try {
            if (td.getDoituongMa(true).getDmdoituongMa().equals("BH")) {
                ClsKhamFacade clsKhamFacade = new ClsKhamFacade();
                clsKhamFacade.setEm(em);
                List<ClsKham> listCLSKham = clsKhamFacade.findByTiepdonma(td.getTiepdonMa());
                if (listCLSKham.size() > 0) {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngayBatDauBh = (td.getTiepdonGiatri3() != null ? td.getTiepdonGiatri3() : td.getTiepdonGiatri1());
                    Date ngayHetHanBh = (td.getTiepdonGiatri4() != null ? td.getTiepdonGiatri4() : td.getTiepdonGiatri2());
                    ngayBatDauBh = (ngayBatDauBh != null ? df.parse(df.format(ngayBatDauBh)) : null);
                    ngayHetHanBh = (ngayHetHanBh != null ? df.parse(df.format(ngayHetHanBh)) : null);
                    Double dongia;
                    Double dongiaBH;
                    Date ngayCls;
                    for (ClsKham eachClsKham : listCLSKham) {
                        // Neu CLS da thanh toan hoac thuoc loai Mien, hoac NDM hoac Yeu cau thi khong can cap nhat lai don gia
                        if ((eachClsKham.getClskhamMien() != null && eachClsKham.getClskhamMien().booleanValue() == true) || (eachClsKham.getClskhamNdm() != null && eachClsKham.getClskhamNdm().booleanValue() == true) || (eachClsKham.getClskhamYeucau() != null && eachClsKham.getClskhamYeucau().booleanValue() == true) || (eachClsKham.getClskhamDatt() != null && eachClsKham.getClskhamDatt().booleanValue() == true)) {
                            continue;
                        // Cap nhat lai don gia
                        }
                        dongia = eachClsKham.getClskhamMahang().getDtdmclsbgDongia();
                        dongiaBH = eachClsKham.getClskhamMahang().getDtdmclsbgDongiabh();
                        ngayCls = df.parse(df.format(eachClsKham.getClskhamNgaygio()));
                        if (ngayBatDauBh != null && ngayHetHanBh != null) {
                            if (ngayCls.before(ngayBatDauBh) || ngayCls.after(ngayHetHanBh)) {
                                eachClsKham.setClskhamDongia(dongia);
                            } else {
                                eachClsKham.setClskhamDongia(dongiaBH);
                            }
                        } else { // Neu ngayBatDauBh = null hoac ngayHetHatBh = null thi coi nhu het han bao hiem

                            eachClsKham.setClskhamDongia(dongia);
                        }
                        clsKhamFacade.edit(eachClsKham);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR in method capnhatGiaClsTheoThoiGianBaoHiem(...) : " + ex.toString());
            ex.printStackTrace();
        }
    }

    private int xoaDuLieuTam(){
         Query q = em.createQuery("delete from TmpDataBhyt");
         return q.executeUpdate();
    }
    /*
     * Thanh add 05/10/2011
     * Không cần sửa Date
     */
public List<TmpDataBhyt> exportDataNgoaiTru(Date fromDate, Date toDate){
        System.out.println("------------Begin exportDataNgoaiTru---------------");
        xoaDuLieuTam();
        String sSQL  = "select A.TIEPDON_MA, B.HSTHTOANK_NGAYGIOTT NGAYGIOTT, C.BENHNHAN_HOTEN ho_ten, C.BENHNHAN_NAMSINH nam_sinh, " +
                        " (select DMGT_MA from dm_gioi_tinh where (DMGT_MASO = C.DMGT_MASO)) gioi_tinh, A.TIEPDON_SOTHEBH mathe, " +
                        " D.DMTINH_MABHYT tinh_kcb, substr(E.DMBENHVIEN_MA,-3,3) ma_kcb, " +
                        " A.TIEPDON_NGAYGIO ngay_vao, A.TIEPDON_NGAYGIO ngay_ra, 1 ngaydtr, " +
                        " E.DMBENHVIEN_MA noikcb, extract(month from B.HSTHTOANK_NGAYGIOTT) thangqt, extract(year from B.HSTHTOANK_NGAYGIOTT) namqt, A.TIEPDON_GIATRI1 gtri_tu, A.TIEPDON_GIATRI2 gtri_den, " +
                        " (decode(C.BENHNHAN_DIACHI, NULL,' ',CONCAT(C.BENHNHAN_DIACHI,', '))||decode(X.DMXA_TEN , NULL,' ',CONCAT(X.DMXA_TEN,', '))||decode(H.DMHUYEN_TEN , NULL,' ',CONCAT(H.DMHUYEN_TEN,', '))||decode(T.DMTINH_TEN , NULL,' ',T.DMTINH_TEN)) dia_chi, " +
                        " 'NGOAI' loaikcb, 'CSKCB' noi_ttoan, B.HSTHTOANK_MA sophieu, " +
                        " A.KHOIBHYT_MA ma_dt,substr(A.TIEPDON_SOTHEBH,3,1) loai_tt, (select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where (DTDMMQLBHYT_MASO = substr(A.TIEPDON_SOTHEBH,3,1))) vanchuyen, " +
                        " A.TIEPDON_MOC1 AS ngay_g1, A.TIEPDON_MOC2 AS ngay_g2, A.TIEPDON_MOC3 AS ngay_g3, " +
                        " A.TIEPDON_TUYEN AS dieu_tri, A.TIEPDON_CO_GIAY_GIOI_THIEU giay_gt, D.DMTINH_MABHYT AS ma_bhxh, " +
                        " (select DMBENHVIEN_TEN from dm_benh_vien where (DMBENHVIEN_MASO = A.TIEPDON_DONVIGOI)) AS noi_den, " +
                        " F.THAMKHAM_BANKHAM ma_khoa, g.DMBENHICD_MA mabenh, g.DMBENHICD_TEN benhkhac, (select DMBENHICD_TEN from dm_benh_icd where (DMBENHICD_MASO = F.BENHICD10_PHU1)) BENH_PHU " +
                        " from tiep_don A INNER JOIN hs_thtoank B ON A.TIEPDON_MA = B.TIEPDON_MA " +
                        " INNER JOIN benh_nhan C ON A.BENHNHAN_MA = C.BENHNHAN_MA " +
                        " INNER JOIN tham_kham F ON f.tiepdon_ma = a.tiepdon_ma " +
                        " LEFT JOIN dm_tinh D ON A.TINHBHYT_MA = D.DMTINH_MASO " +
                        " LEFT JOIN dm_benh_vien E ON A.KCBBHYT_MA = E.DMBENHVIEN_MASO " +
                        " LEFT JOIN dm_benh_icd g ON g.DMBENHICD_MASO = F.BENHICD10 " +
                        " LEFT JOIN DT_DM_KHOI_BHYT KHOIBHYT ON A.KHOIBHYT_MA = KHOIBHYT.DTDMKHOIBHYT_MA " +
                        " LEFT JOIN DT_DM_NHOM_BHYT NHOMBHYT ON KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO " +
                        " LEFT JOIN DM_TINH T ON C.TINH_MA = T.DMTINH_MASO " +
                        " LEFT JOIN DM_HUYEN H ON C.HUYEN_MA = H.DMHUYEN_MASO " +
                        " LEFT JOIN DM_XA X ON C.XA_MA = X.DMXA_MASO " +
                        " WHERE B.HSTHTOANK_NGAYGIOTT Is Not Null  AND A.DOITUONG_MA = 2 " +
                        " AND A.TIEPDON_BANKHAM is not null " +
                        " AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 " +
                        " AND B.HSTHTOANK_BHYT Is Not Null AND B.HSTHTOANK_BHYT > 0 " +
                        " AND F.THAMKHAM_MA IN (Select F2.THAMKHAM_MA From (Select Max(F3.THAMKHAM_MA) THAMKHAM_MA, F3.tiepdon_ma from tham_kham F3 group by F3.tiepdon_ma ) F2) " +
                        " AND to_date(B.HSTHTOANK_NGAYGIOTT) >= :fromDate AND to_date(B.HSTHTOANK_NGAYGIOTT) <= :toDate " +
                        // ########################################
                     // phuc.lc : 24/11/2011
                     // Khong can Group By theo TIEPDON_MA, truoc day do loi code mot tiep don co nhieu hon 1 hs_thtoank
                     // nen moi can Group By, code bay gio da fix loi nay nen khong can Group By nua
                     // khi xuat du lieu sai, can kiem tra lai van de nay
                     // phai bao dam moi tiep don chi co 1 hs_thtoank
                     //##############################################
                       /*    " group by A.TIEPDON_MA " +  */
                        " order by A.TIEPDON_MA asc";
		Query q = em.createNativeQuery(sSQL,VBenhnhanNgtDattDetail.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VBenhnhanNgtDattDetail> listVBenhnhanNgtDattDetail = q.getResultList();
        System.out.println("listVBenhnhanNgtDattDetail.size: " +listVBenhnhanNgtDattDetail.size());
        //Thuoc
        sSQL = "SELECT concat(tpk.TIEPDON_MA, tpk.stt_nh) Id, tpk.TIEPDON_MA, tpk.stt_nh, sum(tpk.tong_tien) tong_tien, sum(tpk.tien_bn) tien_bn "
              +"FROM "
              +"( "
              +"    SELECT B.TIEPDON_MA, A.THUOCPHONGKHAM_MATHUOC, DECODE(D.DMTHUOC_PLBHYT, NULL, 1,D.DMTHUOC_PLBHYT) stt_nh, D.DMTHUOC_MA ma_hieu, "
              +"        (A.THUOCPHONGKHAM_SOLUONG - coalesce(A.THUOCPHONGKHAM_TRA,0)) sl, A.THUOCPHONGKHAM_DONGIATT gia, "
              +"        coalesce(A.THUOCPHONGKHAM_DONGIABH,0) gia_bhxh, coalesce(A.THUOCPHONGKHAM_TIENBNTRA,0) tien_bn, "
              +"        coalesce(A.THUOCPHONGKHAM_THANHTIEN,0) tong_tien, "
              +"        (A.THUOCPHONGKHAM_SOLUONG - coalesce(A.THUOCPHONGKHAM_TRA,0))*( A.THUOCPHONGKHAM_DONGIATT-coalesce(A.THUOCPHONGKHAM_DONGIABH,0)) as tien_chenh, "
              +"        (coalesce(A.THUOCPHONGKHAM_THANHTIEN,0) - coalesce(A.THUOCPHONGKHAM_TIENBNTRA,0)) as tien_bhxh "
              +"    FROM thuoc_phong_kham A, HS_THTOANK B, tham_kham C, dm_thuoc D, TIEP_DON E, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT "
              +"    WHERE B.TIEPDON_MA = C.TIEPDON_MA AND B.HSTHTOANK_NGAYGIOTT Is Not Null AND to_date(B.HSTHTOANK_NGAYGIOTT)>= :fromDate AND to_date(B.HSTHTOANK_NGAYGIOTT) <= :toDate "
              +"    AND A.THUOCPHONGKHAM_THAMKHAM = C.THAMKHAM_MA "
              +"    AND B.TIEPDON_MA = E.TIEPDON_MA "
              +"    AND A.THUOCPHONGKHAM_MATHUOC = D.DMTHUOC_MASO "
              +"    AND (A.THUOCPHONGKHAM_LOAI = 1 or A.THUOCPHONGKHAM_LOAI = 3) "
              +"    AND E.DOITUONG_MA = 2 AND E.TIEPDON_BANKHAM is not null "
              +"    AND E.KHOIBHYT_MA = KHOIBHYT.DTDMKHOIBHYT_MA "
              +"    AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
              +"    AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
              +"    AND B.HSTHTOANK_BHYT Is Not Null AND B.HSTHTOANK_BHYT > 0 "

              +") tpk "
              +"GROUP BY tpk.TIEPDON_MA, tpk.stt_nh ORDER BY tpk.TIEPDON_MA";

//        sSQL ="SELECT a.* FROM v_thuoc_ngoaitru_groupbytiepdon_sttnhExcel a, " +
//                    "(SELECT v.tiepdon_ma FROM v_benhnhan_ngt_datt_detail v where DATE(NGAYGIOTT)>= :fromDate "+
//                     "and DATE(NGAYGIOTT) <= :toDate) b" +
//                    " where a.tiepdon_ma = b.tiepdon_ma order by a.tiepdon_ma";
        q = em.createNativeQuery(sSQL, VThuocNgoaitruGroupbytiepdonSttnhexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VThuocNgoaitruGroupbytiepdonSttnhexcel> listVThuocNgoaitruGroupbytiepdonSttnh = q.getResultList();
        System.out.println("listVThuocNgoaitruGroupbytiepdonSttnh.size: " +listVThuocNgoaitruGroupbytiepdonSttnh.size());
        java.util.HashMap<String, ArrayList> hmNhomThuocNGTs = new java.util.HashMap<String, ArrayList>();
        ArrayList<VThuocNgoaitruGroupbytiepdonSttnhexcel> arrThuocVTYTs = new ArrayList<VThuocNgoaitruGroupbytiepdonSttnhexcel>();
        String strMaTiepDon_Temp = "";
        for(VThuocNgoaitruGroupbytiepdonSttnhexcel vThuocNgoaitruGroupbytiepdonSttnh:listVThuocNgoaitruGroupbytiepdonSttnh){
            String strMaTiepDon = vThuocNgoaitruGroupbytiepdonSttnh.getTiepdonMa();
            if(!strMaTiepDon_Temp.equals(strMaTiepDon)){
                arrThuocVTYTs = new ArrayList<VThuocNgoaitruGroupbytiepdonSttnhexcel>();
            }else//maTiepDon cu = maTiepDon moi
            {
                hmNhomThuocNGTs.remove(strMaTiepDon);
            }
            arrThuocVTYTs.add(vThuocNgoaitruGroupbytiepdonSttnh);
            strMaTiepDon_Temp = strMaTiepDon;
            hmNhomThuocNGTs.put(vThuocNgoaitruGroupbytiepdonSttnh.getTiepdonMa(), arrThuocVTYTs);
        }

        //Bai thuoc
        sSQL = "SELECT concat(A.TIEPDON_MA,'1') Id, A.TIEPDON_MA, 1 stt_nh, sum(coalesce(A.THUOCDONGY_TIENBNTRA,0)) tien_bn, "
             + "sum(A.THUOCDONGY_SOLUONG * A.THUOCDONGY_DONGIA) tong_tien "
             + "FROM thuoc_dong_y_ngoai_tru A, HS_THTOANK B, TIEP_DON C, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT "
             + "WHERE A.TIEPDON_MA = B.TIEPDON_MA AND B.HSTHTOANK_NGAYGIOTT Is Not Null AND to_date(B.HSTHTOANK_NGAYGIOTT)>= :fromDate AND to_date(B.HSTHTOANK_NGAYGIOTT) <= :toDate "
             +"AND B.TIEPDON_MA = C.TIEPDON_MA AND C.DOITUONG_MA = 2 AND C.TIEPDON_BANKHAM is not null "
             +"AND (A.THUOCDONGY_LOAI = 1 or A.THUOCDONGY_LOAI = 3) "
             +"AND C.KHOIBHYT_MA = KHOIBHYT.DTDMKHOIBHYT_MA "
             +"AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
             +"AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
             +"AND B.HSTHTOANK_BHYT Is Not Null AND B.HSTHTOANK_BHYT > 0 "
             +"GROUP BY A.TIEPDON_MA ORDER BY A.TIEPDON_MA";
//        sSQL ="SELECT a.* FROM v_thuoc_dongy_ngoaitruExcel a, " +
//                    "(SELECT v.tiepdon_ma FROM v_benhnhan_ngt_datt_detail v where DATE(NGAYGIOTT)>= :fromDate "+
//                     "and DATE(NGAYGIOTT) <= :toDate) b" +
//                    " where a.tiepdon_ma = b.tiepdon_ma";
        q = em.createNativeQuery(sSQL, VThuocDongyNgoaitruexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VThuocDongyNgoaitruexcel> listVThuocDongyNgoaitru = q.getResultList();
        System.out.println("listVThuocDongyNgoaitru.size: " +listVThuocDongyNgoaitru.size());
        java.util.HashMap<String, ArrayList> hmNhomThuocDYNGTs = new java.util.HashMap<String, ArrayList>();
        ArrayList<VThuocDongyNgoaitruexcel> arrThuocDYVTYTs = new ArrayList<VThuocDongyNgoaitruexcel>();
        strMaTiepDon_Temp = "";
        for(VThuocDongyNgoaitruexcel vThuocDongyNgoaitru:listVThuocDongyNgoaitru){
            String strMaTiepDon = vThuocDongyNgoaitru.getTiepdonMa();
            if(!strMaTiepDon_Temp.equals(strMaTiepDon)){
                arrThuocDYVTYTs = new ArrayList<VThuocDongyNgoaitruexcel>();
            }else//maTiepDon cu = maTiepDon moi
            {
                hmNhomThuocNGTs.remove(strMaTiepDon);
            }
            arrThuocDYVTYTs.add(vThuocDongyNgoaitru);
            strMaTiepDon_Temp = strMaTiepDon;
            hmNhomThuocDYNGTs.put(vThuocDongyNgoaitru.getTiepdonMa(), arrThuocVTYTs);
        }

        //CLS
        sSQL = "SELECT concat(clsk.TIEPDON_MA, clsk.stt_nh) Id, clsk.TIEPDON_MA, clsk.stt_nh, sum(clsk.tong_tien) tong_tien, sum(clsk.tien_bn) tien_bn "
              +"FROM "
              +"( "
              +"    SELECT B.TIEPDON_MA, D.DTDMCLSBG_MALOAI stt_nh, A.CLSKHAM_MAHANG, "
              +"        SUM(coalesce(A.CLSKHAM_DONGIABNTRA,0)) tien_bn, "
              +"        SUM((A.CLSKHAM_LAN - coalesce(A.CLSKHAM_TRA,0))* coalesce(A.CLSKHAM_DONGIA,0)) tong_tien "
              +"    FROM cls_kham A, HS_THTOANK B, tham_kham C, dt_dm_cls_bang_gia D, TIEP_DON E, DT_DM_KHOI_BHYT KHOIBHYT, DT_DM_NHOM_BHYT NHOMBHYT "
              +"    WHERE B.TIEPDON_MA = C.TIEPDON_MA AND B.HSTHTOANK_NGAYGIOTT Is Not Null AND to_date(B.HSTHTOANK_NGAYGIOTT)>= :fromDate AND to_date(B.HSTHTOANK_NGAYGIOTT) <= :toDate "
              +"    AND A.CLSKHAM_THAMKHAM = C.THAMKHAM_MA "
              +"    AND B.TIEPDON_MA = E.TIEPDON_MA "
              +"    AND A.CLSKHAM_MAHANG = D.DTDMCLSBG_MASO "              
              +"    AND E.DOITUONG_MA = 2 "
              +"    AND E.TIEPDON_BANKHAM is not null "
              +"    AND E.KHOIBHYT_MA = KHOIBHYT.DTDMKHOIBHYT_MA "
              +"    AND KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO "
              +"    AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 "
              +"    AND B.HSTHTOANK_BHYT Is Not Null AND B.HSTHTOANK_BHYT > 0 "
              +"    group by B.TIEPDON_MA, A.CLSKHAM_MAHANG, D.DTDMCLSBG_MALOAI "
              +") clsk "
              +"GROUP BY clsk.stt_nh, clsk.TIEPDON_MA ORDER BY clsk.tiepdon_ma";
//        sSQL ="SELECT a.* FROM v_cls_kham_groupbytiepdon_sttnhExcel a, " +
//                    "(SELECT v.tiepdon_ma FROM v_benhnhan_ngt_datt_detail v where DATE(NGAYGIOTT)>= :fromDate "+
//                     "and DATE(NGAYGIOTT) <= :toDate) b" +
//                    " where a.tiepdon_ma = b.tiepdon_ma order by a.tiepdon_ma";
        q = em.createNativeQuery(sSQL, VClsKhamGroupbytiepdonSttnhexcel.class);
        q.setParameter("fromDate", fromDate);
        q.setParameter("toDate", toDate);
        List<VClsKhamGroupbytiepdonSttnhexcel> listVClsKhamGroupbytiepdonSttnh = q.getResultList();
        System.out.println("listVClsKhamGroupbytiepdonSttnh.size: " +listVClsKhamGroupbytiepdonSttnh.size());
        java.util.HashMap<String, ArrayList> hmNhomCLSKhams = new java.util.HashMap<String, ArrayList>();
        ArrayList<VClsKhamGroupbytiepdonSttnhexcel> arrCLSs = new ArrayList<VClsKhamGroupbytiepdonSttnhexcel>();
        strMaTiepDon_Temp = "";
        for(VClsKhamGroupbytiepdonSttnhexcel vClsKhamGroupbytiepdonSttnh:listVClsKhamGroupbytiepdonSttnh){
            String strMaTiepDon = vClsKhamGroupbytiepdonSttnh.getTiepdonMa();
            if(!strMaTiepDon_Temp.equals(strMaTiepDon)){
                arrCLSs = new ArrayList<VClsKhamGroupbytiepdonSttnhexcel>();
            }else//maTiepDon cu = maTiepDon moi
            {
                hmNhomCLSKhams.remove(strMaTiepDon);
            }
            arrCLSs.add(vClsKhamGroupbytiepdonSttnh);
            strMaTiepDon_Temp = strMaTiepDon;
            hmNhomCLSKhams.put(vClsKhamGroupbytiepdonSttnh.getTiepdonMa(), arrCLSs);
        }

        List<TmpDataBhyt> lstTmpDataNgoaitru = new ArrayList<TmpDataBhyt>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for(VBenhnhanNgtDattDetail vBenhnhanNgtDattDetail:listVBenhnhanNgtDattDetail){
            TmpDataBhyt dataNgoaiTru = new TmpDataBhyt();
            dataNgoaiTru.setTiepDon(vBenhnhanNgtDattDetail.getTiepdonMa());
            dataNgoaiTru.setHoTen(vBenhnhanNgtDattDetail.getHoTen());
            dataNgoaiTru.setNamSinh(Integer.parseInt(vBenhnhanNgtDattDetail.getNamSinh()));
            dataNgoaiTru.setGioiTinh(Short.parseShort(vBenhnhanNgtDattDetail.getGioiTinh()));
            dataNgoaiTru.setMaThe(vBenhnhanNgtDattDetail.getMathe());
            dataNgoaiTru.setMaBenh(vBenhnhanNgtDattDetail.getMabenh());
            
            String ngayVao = format.format(vBenhnhanNgtDattDetail.getNgayVao());
            String ngayRa = format.format(vBenhnhanNgtDattDetail.getNgayRa());
            // Date ngayVaoDate = vBenhnhanNgtDattDetail.getNgayVao();
            // Date ngayRaDate = vBenhnhanNgtDattDetail.getNgayRa();
            dataNgoaiTru.setNgayVao(ngayVao);
            dataNgoaiTru.setNgayRa(ngayRa);
            //dataNgoaiTru.setNgayDtr(vBenhnhanNgtDattDetail.getNgaydtr());
            dataNgoaiTru.setNgayVao(ngayVao);
            dataNgoaiTru.setNgayRa(ngayRa);

            int songayDT = 0;
            if(ngayRa.equals(ngayVao)){
                songayDT = 1;
            }else{
                songayDT = Utils.subtractDates(vBenhnhanNgtDattDetail.getNgayVao(),vBenhnhanNgtDattDetail.getNgayRa());
            }
            //Long songayDT = ( ((ngayRaDate.getTime() - ngayVaoDate.getTime()) / (60 * 60 * 1000L * 24)) + 1 );
            dataNgoaiTru.setNgayDtr(songayDT);
            
            //Tien
            arrThuocVTYTs = new ArrayList<VThuocNgoaitruGroupbytiepdonSttnhexcel>();
            arrCLSs = new ArrayList<VClsKhamGroupbytiepdonSttnhexcel>();
            Double tienBNTra = new Double(0.0);
            Double tienThuoc = new Double(0.0);
            Double tienVTYTTH = new Double(0.0);
            Double tienBHXH = new Double(0.0);
            if(hmNhomThuocNGTs.size() > 0){
                arrThuocVTYTs = hmNhomThuocNGTs.get(vBenhnhanNgtDattDetail.getTiepdonMa());                
                if(arrThuocVTYTs != null && arrThuocVTYTs.size()>0){
                    for(VThuocNgoaitruGroupbytiepdonSttnhexcel vThuocNgoaitruGroupbytiepdonSttnh:arrThuocVTYTs){
                        if(vThuocNgoaitruGroupbytiepdonSttnh.getSttNh() == 1){//Thuoc
                            tienThuoc = vThuocNgoaitruGroupbytiepdonSttnh.getTongTien();                            
                        }else if(vThuocNgoaitruGroupbytiepdonSttnh.getSttNh() == 10){//VTYT
                            tienVTYTTH = vThuocNgoaitruGroupbytiepdonSttnh.getTongTien();
                        }
                        tienBNTra += vThuocNgoaitruGroupbytiepdonSttnh.getTienBn();
                        tienBHXH += (vThuocNgoaitruGroupbytiepdonSttnh.getTongTien() - vThuocNgoaitruGroupbytiepdonSttnh.getTienBn());
                    }
                }                
            }
            //Bai thuoc
            if(hmNhomThuocDYNGTs.size()>0){
                arrThuocDYVTYTs = hmNhomThuocDYNGTs.get(vBenhnhanNgtDattDetail.getTiepdonMa());                
                if(arrThuocDYVTYTs != null && arrThuocDYVTYTs.size()>0){
                    for(VThuocDongyNgoaitruexcel vThuocDongyNgoaitru:arrThuocDYVTYTs){
                        if(vThuocDongyNgoaitru.getSttNh() == 1){//Thuoc
                            tienThuoc = vThuocDongyNgoaitru.getTongTien();
                        }else if(vThuocDongyNgoaitru.getSttNh() == 10){//VTYT
                            tienVTYTTH = vThuocDongyNgoaitru.getTongTien();
                        }
                        tienBNTra += vThuocDongyNgoaitru.getTienBn();
                        tienBHXH += (vThuocDongyNgoaitru.getTongTien() - vThuocDongyNgoaitru.getTienBn());
                    }
                }
            }
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
            
            if(hmNhomCLSKhams.size() > 0){
                arrCLSs = hmNhomCLSKhams.get(vBenhnhanNgtDattDetail.getTiepdonMa());                
                if(arrCLSs != null && arrCLSs.size()>0){
                    for(int j=0;j<arrCLSs.size();j++){
                        VClsKhamGroupbytiepdonSttnhexcel vClsKhamGroupbytiepdonSttnh = (VClsKhamGroupbytiepdonSttnhexcel)arrCLSs.get(j);
                        if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 2){//Tien mau
                            tienMau = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 4){//Tien xet nghiem
                            tienXN = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 5){//Tien CDHA
                            tienCDHA = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 6){//Tien Phau thuat
                            tienPhauThuat = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 7){//Tien cong kham
                            tienKham = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 8){//Tien thu thuat
                            tienThuThuat = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 9){//Tien DVKTC
                            tienDVKTC = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 11){//Tien ngay giuong
                            tienGiuong = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }else if(vClsKhamGroupbytiepdonSttnh.getSttNh() == 12){//Tien VC
                            tienVanChuyen = vClsKhamGroupbytiepdonSttnh.getTongTien();
                        }
                        tienBNTra += vClsKhamGroupbytiepdonSttnh.getTienBn();
                        tienBHXH += (vClsKhamGroupbytiepdonSttnh.getTongTien() - vClsKhamGroupbytiepdonSttnh.getTienBn());
                    }
                }                  
            }
            tongchiphi = tienThuoc + tienVTYTTH + tienMau + tienXN + tienCDHA + tienPhauThuat + tienKham + tienThuThuat + tienDVKTC + tienVanChuyen;
            dataNgoaiTru.setTienXetnghiem(tienXN);
            dataNgoaiTru.setTienCdha(tienCDHA);
            dataNgoaiTru.setTienThuoc(tienThuoc);
            dataNgoaiTru.setTienMau(tienMau);
            dataNgoaiTru.setTienPttt(tienPhauThuat + tienThuThuat);
            dataNgoaiTru.setTienVtytth(tienVTYTTH);
            dataNgoaiTru.setTienDvktc(tienDVKTC);
            dataNgoaiTru.setTienKtg(tienKTG);
            dataNgoaiTru.setTienKham(tienKham+ tienGiuong);
            dataNgoaiTru.setTienVanchuyen(tienVanChuyen);
            dataNgoaiTru.setTongChi(tongchiphi);
            if(tienBNTra < 0.0000001){
                tienBNTra = 0.0;
            }
            dataNgoaiTru.setTienBntra(tienBNTra);
            dataNgoaiTru.setTienBhxh(tienBHXH);
            Double tienngoaiDS = tongchiphi -(tienBNTra +tienBHXH);
            if(tienngoaiDS < 0.0000001){
                tienngoaiDS = 0.0;
            }
            dataNgoaiTru.setTienNgoaids(tienngoaiDS); 

            //Xet Lý do vào viện (1 - đúng tuyến; 0 - trái tuyến)
            Short dieutri = (vBenhnhanNgtDattDetail.getDieuTri() == null ? new Short("0") : vBenhnhanNgtDattDetail.getDieuTri());
            //Xac dinh dung tuyen, trai tuyen, cap cuu tu YTDT ntn
            if(dieutri == 1){
                dataNgoaiTru.setLydoVv(1);
            }else{
                //kiem tra neu co giay gioi thieu thi dung tuyen nguoc lai la trai tuyen
                Boolean cogiaygioithieu = (vBenhnhanNgtDattDetail.getGiayGt() == null ? false : vBenhnhanNgtDattDetail.getGiayGt());
                if(cogiaygioithieu == true){
                    dataNgoaiTru.setLydoVv(1);
                }else{
                    dataNgoaiTru.setLydoVv(0);
                }
            }
            
            dataNgoaiTru.setBenhKhac(vBenhnhanNgtDattDetail.getBenhkhac());
            //tach dau "."
            String maBV = vBenhnhanNgtDattDetail.getNoikcb();
            String noiKham = " ";
            String maNoiKham ="";
            if (maBV != null){
                if(maBV.indexOf(".") != -1) {
                    String maTinh = maBV.substring(0,maBV.indexOf("."));//96.077
                    maNoiKham = maBV.substring(maBV.indexOf(".")+1,maBV.length());
                    noiKham = maTinh + maNoiKham;
                }
            }
            dataNgoaiTru.setNoiKcb(noiKham);
            dataNgoaiTru.setMaDkbd(noiKham);
            dataNgoaiTru.setMaKhoa(vBenhnhanNgtDattDetail.getMaKhoa());
            dataNgoaiTru.setThangQt(vBenhnhanNgtDattDetail.getThangqt());
            dataNgoaiTru.setNamQt(vBenhnhanNgtDattDetail.getNamqt());
            
            String  gtriTu = format.format(vBenhnhanNgtDattDetail.getGtriTu());
            String  gtriDen = format.format(vBenhnhanNgtDattDetail.getGtriDen());
            dataNgoaiTru.setGtTu(gtriTu);
            dataNgoaiTru.setGtDen(gtriDen);
            dataNgoaiTru.setDiaChi(vBenhnhanNgtDattDetail.getDiaChi());  
            dataNgoaiTru.setGiamDinh("");  
            dataNgoaiTru.setXuatToan("");
            dataNgoaiTru.setLydoXuattoan("");
            dataNgoaiTru.setDaTuyen(0);
            dataNgoaiTru.setVuotTran(0);
            dataNgoaiTru.setLoaiKcb(vBenhnhanNgtDattDetail.getLoaikcb());
            dataNgoaiTru.setNoiThanhtoan(vBenhnhanNgtDattDetail.getNoiTtoan());
            dataNgoaiTru.setSoPhieu(vBenhnhanNgtDattDetail.getSophieu());
            dataNgoaiTru.setMaKhoa(vBenhnhanNgtDattDetail.getMaKhoa());
            //A - Bệnh nhân đăng ký KCB tại cơ sở
            //B - Bệnh nhân đăng ký KCB khác cơ sở, nội tỉnh
            //C - Bệnh nhân nội tỉnh
            if(dieutri == 1){
                dataNgoaiTru.setTuyen("A");
            }else if(dieutri == 2){
                dataNgoaiTru.setTuyen("B");
            }else{
                dataNgoaiTru.setTuyen("C");
            }
            //insert vao bang TmpDataNgoaitru
            getEm().persist(dataNgoaiTru);
            lstTmpDataNgoaitru.add(dataNgoaiTru);
        }
        System.out.println("------------End exportDataNgoaiTru---------------");
        return lstTmpDataNgoaitru;
    }
    
    public List<TiepDon> findBySoTheBHYT(String sothebhyt){
        
        Query q = em.createQuery("Select object(td) From TiepDon td Where td.tiepdonSothebh like :sothebhyt order by td.tiepdonMa DESC");
        q.setParameter("sothebhyt",sothebhyt);        
        return q.getResultList();
        
    }
}


