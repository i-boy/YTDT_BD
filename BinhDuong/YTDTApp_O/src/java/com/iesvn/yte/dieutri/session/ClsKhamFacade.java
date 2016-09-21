/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.entity.ClsKetQua;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacadeLocal;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.session.DmKhoaFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
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
public class ClsKhamFacade implements ClsKhamFacadeLocal, ClsKhamFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private DmKhoaFacadeLocal khoaDAO;
    @EJB
    private DieuTriUtilFacadeLocal dieuTriUtils;

    public void create(ClsKham clsKham) {
        em.persist(clsKham);
    }

    public void edit(ClsKham clsKham) {
        em.merge(clsKham);
    }

    public void remove(ClsKham clsKham) {
        em.remove(em.merge(clsKham));
    }

    public ClsKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ClsKham.class, id);
    }

    public List<ClsKham> findAll() {
        return em.createQuery("select object(o) from ClsKham as o").getResultList();
    }

    public List<ClsKham> findByTiepdonma(String tiepdonMa) {

        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);

        Query q = getEm().createQuery("Select c from ClsKham c Where c.clskhamThamkham.tiepdonMa.tiepdonMa = :tiepdonMa AND (c.clskhamStatus = '0' OR clskhamStatus Is Null)");
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }

    public List<ClsKham> findByTiepdonmaAndKhoa(String tiepdonMa, String khoaMaso) {
        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);
        Query q = getEm().createQuery("Select c from ClsKham c Where c.clskhamThamkham.tiepdonMa.tiepdonMa = :tiepdonMa and c.clskhamMahang.dtdmclsbgMaso in (select o.dtdmclsMaso.dtdmclsbgMaso from DtDmClsKhoa o where o.dmkhoaMaso.dmkhoaMaso = :khoaMaso)");
        q.setParameter("tiepdonMa", tiepdonMa);
        q.setParameter("khoaMaso", Integer.parseInt(khoaMaso));
        return q.getResultList();
    }

    public List<ClsKham> findByTiepdonMaVaLoaiClsKham(String tiepdonMa) {

        tiepdonMa = Utils.formatMa(getEm(), tiepdonMa);

        Query q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamThamkham.tiepdonMa.tiepdonMa = :tiepdonMa AND clskhamMaloai.dtdmclsMa <> 'KH' ");
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }

    public List<ClsKham> findByMaPhieu(String maPhieu) {

        maPhieu = Utils.formatMaPhieu(maPhieu);

        Query q = getEm().createQuery("Select c from ClsKham c Where c.clskhamMaphieu = :maPhieu");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<ClsKham> findByMaPhieuHoan(String maPhieu) {

        maPhieu = Utils.formatMaPhieu(maPhieu);

        Query q = getEm().createQuery("Select c from ClsKham c Where c.clskhamMaphieud = :maPhieu");
        q.setParameter("maPhieu", maPhieu);
        return q.getResultList();
    }

    public List<ClsKham> findByBanKhamVaMaTiepDon(String banKham, String maTiepDon) {
        maTiepDon = Utils.formatMa(getEm(), maTiepDon);


        Query q = getEm().createQuery("select object(o) from ClsKham as o where o.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa ");
        q.setParameter("dtdmbankhamMa", banKham);
        q.setParameter("tiepdonMa", maTiepDon);
        return q.getResultList();

    }

    public Double getSoTienTuPhieu(String maTiepDon, String maPhieu) {
        maTiepDon = Utils.formatMa(getEm(), maTiepDon);
        Double soTien = new Double(0);

        Query q = getEm().createQuery("select object(o) from ClsKham as o where o.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa and o.clskhamMaphieu like :maPhieu ");

        q.setParameter("tiepdonMa", maTiepDon);
        q.setParameter("maPhieu", maPhieu);

        List<ClsKham> lst = q.getResultList();
        if (lst != null && lst.size() > 0) {
            for (ClsKham clskham : lst) {

                DtDmClsBangGiaDelegate banggiaDelegate = DtDmClsBangGiaDelegate.getInstance();
                DtDmClsBangGia banggia = banggiaDelegate.find(clskham.getClskhamMahang().getDtdmclsbgMaso());

                Short lanKham = clskham.getClskhamLan();
                if (lanKham == null) {
                    lanKham = 0;
                }
                Short tra = clskham.getClskhamTra();
                if (tra == null) {
                    tra = 0;
                }
                if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true) {
                    soTien += banggia.getDtdmclsbgDongiayc() * (lanKham - tra);
                } else {
                    soTien += banggia.getDtdmclsbgDongia() * (lanKham - tra);
                }
            }
        }
        return soTien;

    }

    public ClsKham findByBanKhamVaMaTiepDonVaKham(String banKham, String maTiepDon) {
        maTiepDon = Utils.formatMa(getEm(), maTiepDon);


        Query q = getEm().createQuery("select object(o) from ClsKham as o where o.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa and o.clskhamMahang.dtdmclsbgMaloai.dtdmclsPhanbiet.dtdmpbclsMa like 'KH' ");
        q.setParameter("dtdmbankhamMa", banKham);
        q.setParameter("tiepdonMa", maTiepDon);
        List<ClsKham> lst = q.getResultList();
        if (lst != null && lst.size() > 0) {
            return lst.get(0);
        }
        return null;

    }

    public String createClsKhamHoanTra(List<ClsKham> listCLS, String maTiepDon, String maBanKham) {

        if (listCLS == null) {
            return "";
        }

        System.out.println("+++++++++++++++++++");
        boolean bGenerateMaPhieuD = false;
        for (ClsKham cls : listCLS) {
            System.out.println("---------------");
            System.out.println("cls.getClskhamMaphieud():" + cls.getClskhamMaphieud());
            System.out.println("---------------");

            if (cls.getClskhamTra() == null || cls.getClskhamTra().shortValue() <= 0) {
                continue;
            }
            if (cls.getClskhamMaphieud() == null) {
                bGenerateMaPhieuD = true;
                break;
            }
        }
        System.out.println("+++++++++++++++++++");

        System.out.println("bGenerateMaPhieu:" + bGenerateMaPhieuD);

//        String maPhieu = listCLS.get(0).getClskhamMaphieu();

//        boolean capnhat = true;
//        if (maPhieu == null || maPhieu.equals("")){
        String maPhieu = "";
        if (bGenerateMaPhieuD == true) {

            maPhieu = Utils.createMaPhieu(em);
            for (ClsKham canLamSangKham : listCLS) {
                if (canLamSangKham.getClskhamMaphieud() != null) {
                    continue;
                }

                canLamSangKham.setClskhamMaphieud(maPhieu);

            }

        }
//            capnhat = false;
//        }


        String result = "";

        HsThtoankFacade hsThtoankFacade = new HsThtoankFacade();
        hsThtoankFacade.setEm(em);
        HsThtoank hsThanhToan = hsThtoankFacade.findBytiepdonMa(maTiepDon);


        TiepDonFacade tdFacade = new TiepDonFacade();
        tdFacade.setEm(em);
        TiepDon tiepDonObj = tdFacade.find(maTiepDon);


        try {
//            String listclsMa = "";
//            for (int i = 0; i < listCLS.size(); i++) {
//                ClsKham cls = listCLS.get(i);
//                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {
//
//                    if (listclsMa.equals("")) {
//                        listclsMa += cls.getClskhamMa();
//                    } else {
//                        listclsMa += "," + cls.getClskhamMa();
//                    }
//                }
//            }
//            System.out.println(listclsMa);
//            //if (!listclsMa.equals("")) {
//            this.removeItem(listclsMa, maTiepDon, maBanKham, hsThanhToan, tiepDonObj);
//            // }
            for (ClsKham clsKham : listCLS) {

                System.out.println("clsKham.getClskhamTra():" + clsKham.getClskhamTra());
                if (clsKham.getClskhamTra() == null || clsKham.getClskhamTra().shortValue() <= 0) {
                    System.out.println("continue:" + clsKham.getClskhamTra());
                    continue;
                }

                if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }
                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }
                }
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }
                }
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }
                }

//                if (clsKham.getTiepdonMa().getTiepdonMa().length() < Utils.LENGTH_MA) {
//                    String maTiepDon = clsKham.getTiepdonMa().getTiepdonMa();
//                    maTiepDon = Utils.formatMa(getEm(), maTiepDon);
//                    TiepDon tiepdon = clsKham.getTiepdonMa();
//
//                    tiepdon.setTiepdonMa(maTiepDon);
//
//                    //thanh do add here
//                    Utils.setInfor(tiepdon, getEm());
//
//                    //end thanh do add here
//
//                    clsKham.setTiepdonMa(tiepdon);
//                }
                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);
//                if (clsKham.getClskhamMa() != null) {
//                    getEm().merge(clsKham);
//                    System.out.println("update thanh cong chi tiet cls kham");
//                } else {
//
//                    getEm().persist(clsKham);
//                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
//                }

                if (clsKham.getClskhamMa() != null) {


                    //
                    // Trong truong hop nay: ta phai get cai cu, so sanh CLS-> gia' va` so luong
                    //
                    ClsKham clsKham_DB = find(clsKham.getClskhamMa());

                    // thay doi CLS
                    // xoa so tien da tinh toan tai ho so benh an cu

                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");

//                    capNhatThongTinHoSoThanhToanKhamHoanTra(hsThanhToan, clsKham,
//                            tiepDonObj, false);
                    System.out.println("update thanh cong ho so thanh toan kham");

                }

            }
            result = maPhieu;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    public String createClsKhamHoanTra(List<ClsKham> listCLS, String maTiepDon) {

        if (listCLS == null) {
            return "";
        }
        System.out.println("+++++++++++++++++++");
        boolean bGenerateMaPhieuD = false;
        for (ClsKham cls : listCLS) {
            System.out.println("---------------");
            System.out.println("cls.getClskhamMaphieud():" + cls.getClskhamMaphieud());
            System.out.println("---------------");
            if (cls.getClskhamMaphieud() == null) {
                bGenerateMaPhieuD = true;
                break;
            }
        }
        System.out.println("+++++++++++++++++++");

        System.out.println("bGenerateMaPhieu:" + bGenerateMaPhieuD);

//        String maPhieu = listCLS.get(0).getClskhamMaphieu();

//        boolean capnhat = true;
//        if (maPhieu == null || maPhieu.equals("")){
        String maPhieu = "";
        if (bGenerateMaPhieuD == true) {

            maPhieu = Utils.createMaPhieu(em);
            for (ClsKham canLamSangKham : listCLS) {
                if (canLamSangKham.getClskhamMaphieud() != null) {
                    continue;
                }

                canLamSangKham.setClskhamMaphieud(maPhieu);

            }

        }
//            capnhat = false;
//        }


        String result = "";

        HsThtoankFacade hsThtoankFacade = new HsThtoankFacade();
        hsThtoankFacade.setEm(em);
        HsThtoank hsThanhToan = hsThtoankFacade.findBytiepdonMa(maTiepDon);


        TiepDonFacade tdFacade = new TiepDonFacade();
        tdFacade.setEm(em);
        TiepDon tiepDonObj = tdFacade.find(maTiepDon);


        try {
//            String listclsMa = "";
//            for (int i = 0; i < listCLS.size(); i++) {
//                ClsKham cls = listCLS.get(i);
//                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {
//
//                    if (listclsMa.equals("")) {
//                        listclsMa += cls.getClskhamMa();
//                    } else {
//                        listclsMa += "," + cls.getClskhamMa();
//                    }
//                }
//            }
//            System.out.println(listclsMa);
//            //if (!listclsMa.equals("")) {
//            this.removeItem(listclsMa, maTiepDon, maBanKham, hsThanhToan, tiepDonObj);
//            // }
            for (ClsKham clsKham : listCLS) {
                System.out.println("clsKham.getClskhamTra():" + clsKham.getClskhamTra());
                if (clsKham.getClskhamTra() == null || clsKham.getClskhamTra().shortValue() <= 0) {
                    System.out.println("continue:" + clsKham.getClskhamTra());
                    continue;
                }
               /* if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }
                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }
                }
                 
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }
                }
                * */
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }
                }

                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);


                if (clsKham.getClskhamMa() != null) {


                    //
                    // Trong truong hop nay: ta phai get cai cu, so sanh CLS-> gia' va` so luong
                    //
                    ClsKham clsKham_DB = find(clsKham.getClskhamMa());

                    // thay doi CLS
                    // xoa so tien da tinh toan tai ho so benh an cu

                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");

//                    capNhatThongTinHoSoThanhToanKhamHoanTra(hsThanhToan, clsKham,
//                            tiepDonObj, false);
                    System.out.println("update thanh cong ho so thanh toan kham");

                }

            }
            result = maPhieu;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    /**
     * 
     * @param listCLS
     * @param maTiepDon
     * @param maBanKham
     * @return
     */
    public String createClsKham(List<ClsKham> listCLS, String maTiepDon, String maBanKham) {

        if (listCLS == null) {
            return "";
        }
        System.out.println("+++++++++++++++++++22");
        boolean bGenerateMaPhieu = false;
        for (ClsKham cls : listCLS) {
            if (cls.getClskhamMaphieu() != null) {
                continue;
            }
            if (cls.getClskhamDatt()) {
                bGenerateMaPhieu = true;
                break;
            }
        }

        System.out.println("bGenerateMaPhieu:" + bGenerateMaPhieu);

//        String maPhieu = listCLS.get(0).getClskhamMaphieu();

//        boolean capnhat = true;
//        if (maPhieu == null || maPhieu.equals("")){
        String maPhieu = "";
        if (bGenerateMaPhieu == true) {

            maPhieu = Utils.createMaPhieu(em);
            for (ClsKham canLamSangKham : listCLS) {
                if (canLamSangKham.getClskhamMaphieu() != null) {
                    continue;
                }
                if (canLamSangKham.getClskhamDatt()) {
                    canLamSangKham.setClskhamMaphieu(maPhieu);
                }
            }

        }
//            capnhat = false;
//        }


        String result = "";

        HsThtoankFacade hsThtoankFacade = new HsThtoankFacade();
        hsThtoankFacade.setEm(em);
        HsThtoank hsThanhToan = hsThtoankFacade.findBytiepdonMa(maTiepDon);


        TiepDonFacade tdFacade = new TiepDonFacade();
        tdFacade.setEm(em);
        TiepDon tiepDonObj = tdFacade.find(maTiepDon);


        try {
            String listclsMa = "";
            for (int i = 0; i < listCLS.size(); i++) {
                ClsKham cls = listCLS.get(i);
                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {

                    if (listclsMa.equals("")) {
                        listclsMa += cls.getClskhamMa();
                    } else {
                        listclsMa += "," + cls.getClskhamMa();
                    }
                }
            }
            System.out.println(listclsMa);
            //if (!listclsMa.equals("")) {
            this.removeItem(listclsMa, maTiepDon, maBanKham, hsThanhToan, tiepDonObj);
            // }
            for (ClsKham clsKham : listCLS) {
                if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }
                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }
                }
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }
                }
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }
                }

//                if (clsKham.getTiepdonMa().getTiepdonMa().length() < Utils.LENGTH_MA) {
//                    String maTiepDon = clsKham.getTiepdonMa().getTiepdonMa();
//                    maTiepDon = Utils.formatMa(getEm(), maTiepDon);
//                    TiepDon tiepdon = clsKham.getTiepdonMa();
//
//                    tiepdon.setTiepdonMa(maTiepDon);
//
//                    //thanh do add here
//                    Utils.setInfor(tiepdon, getEm());
//
//                    //end thanh do add here
//
//                    clsKham.setTiepdonMa(tiepdon);
//                }
                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);
//                if (clsKham.getClskhamMa() != null) {
//                    getEm().merge(clsKham);
//                    System.out.println("update thanh cong chi tiet cls kham");
//                } else {
//
//                    getEm().persist(clsKham);
//                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
//                }

                if (clsKham.getClskhamMa() != null) {


                    //
                    // Trong truong hop nay: ta phai get cai cu, so sanh CLS-> gia' va` so luong
                    //
                    ClsKham clsKham_DB = find(clsKham.getClskhamMa());

                    // thay doi CLS
                    // xoa so tien da tinh toan tai ho so benh an cu



                    //
                    //
                    //
                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");
                } else {

                    getEm().persist(clsKham);

                    /**
                     * 
                     *  Tinh tien
                     * 
                     * 
                     */
                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
                }

//                capNhatThongTinHoSoThanhToanKham(hsThanhToan, listCLS,
//                        tiepDonObj, true);

            }
            result = maPhieu;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    /**
     * 
     * @param listCLS
     * @param maTiepDon
     * @param maBanKham
     * @return
     */
    public String createClsKham(List<ClsKham> listCLS, String maTiepDon) {



        if (listCLS == null) {
            return "";
        }

        System.out.println("+++++++++++++++++++createClsKham");
        for (ClsKham cls : listCLS) {

            System.out.println(cls.getClskhamDatt());
            System.out.println(cls.getClskhamMaphieu());
            System.out.println(cls.getClskhamMaphieu() == null);
            System.out.println("+++");
        }
        System.out.println("+++++++++++++++++++223");
        boolean bGenerateMaPhieu = false;
        for (ClsKham cls : listCLS) {
            if (cls.getClskhamMaphieu() != null) {
                continue;
            }
            if (cls.getClskhamDatt()) {
                bGenerateMaPhieu = true;
                break;
            }
        }

        System.out.println("bGenerateMaPhieu:" + bGenerateMaPhieu);

//        String maPhieu = listCLS.get(0).getClskhamMaphieu();

//        boolean capnhat = true;
//        if (maPhieu == null || maPhieu.equals("")){
        String maPhieu = "";
        if (bGenerateMaPhieu == true) {

            maPhieu = Utils.createMaPhieu(em);
            for (ClsKham canLamSangKham : listCLS) {
                if (canLamSangKham.getClskhamMaphieu() != null) {
                    continue;
                }
                if (canLamSangKham.getClskhamDatt()) {
                    canLamSangKham.setClskhamMaphieu(maPhieu);
                }
            }

        }
//            capnhat = false;
//        }


        String result = "";

        HsThtoankFacade hsThtoankFacade = new HsThtoankFacade();
        hsThtoankFacade.setEm(em);
        HsThtoank hsThanhToan = hsThtoankFacade.findBytiepdonMa(maTiepDon);


        TiepDonFacade tdFacade = new TiepDonFacade();
        tdFacade.setEm(em);
        TiepDon tiepDonObj = tdFacade.find(maTiepDon);


        try {
            String listclsMa = "";
            for (int i = 0; i < listCLS.size(); i++) {
                ClsKham cls = listCLS.get(i);
                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {

                    if (listclsMa.equals("")) {
                        listclsMa += cls.getClskhamMa();
                    } else {
                        listclsMa += "," + cls.getClskhamMa();
                    }
                }
            }
            System.out.println(listclsMa);
            //if (!listclsMa.equals("")) {
            this.removeItem(listclsMa, maTiepDon, hsThanhToan, tiepDonObj);
            // }
            for (ClsKham clsKham : listCLS) {
                if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }
                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }
                }
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }
                }
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }
                }

//                if (clsKham.getTiepdonMa().getTiepdonMa().length() < Utils.LENGTH_MA) {
//                    String maTiepDon = clsKham.getTiepdonMa().getTiepdonMa();
//                    maTiepDon = Utils.formatMa(getEm(), maTiepDon);
//                    TiepDon tiepdon = clsKham.getTiepdonMa();
//
//                    tiepdon.setTiepdonMa(maTiepDon);
//
//                    //thanh do add here
//                    Utils.setInfor(tiepdon, getEm());
//
//                    //end thanh do add here
//
//                    clsKham.setTiepdonMa(tiepdon);
//                }
                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);
//                if (clsKham.getClskhamMa() != null) {
//                    getEm().merge(clsKham);
//                    System.out.println("update thanh cong chi tiet cls kham");
//                } else {
//
//                    getEm().persist(clsKham);
//                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
//                }

                if (clsKham.getClskhamMa() != null) {


                    //
                    // Trong truong hop nay: ta phai get cai cu, so sanh CLS-> gia' va` so luong
                    //
                    ClsKham clsKham_DB = find(clsKham.getClskhamMa());

                    // thay doi CLS
                    // xoa so tien da tinh toan tai ho so benh an cu

                    //
                    //
                    //
                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");
                } else {

                    System.out.println("****************:" + clsKham.getClskhamMaphieu());
                    getEm().persist(clsKham);
                    System.out.println("****************:" + clsKham.getClskhamMaphieu());
                    /**
                     * 
                     *  Tinh tien
                     * 
                     * 
                     */
                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
                }

//                capNhatThongTinHoSoThanhToanKham(hsThanhToan, listCLS,
//                        tiepDonObj, true);


            }
            result = maPhieu;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }

    /**
     * 
     * @param hsThanhToan
     * @param clsKham
     * @param tiepDonObj
     * @param add
     */
//    private void capNhatThongTinHoSoThanhToanKham(HsThtoank hsThanhToan, List<ClsKham> clsKham,
//            TiepDon tiepDonObj, boolean add) {
//        if (hsThanhToan != null) {
//            double permiengiam = 0;
//            Double miengiam = new Double(0);
//            Double thatthu = new Double(0);
//            int perbhytchi = 0;
//            Double bhytchi = new Double(0);
//            Double thanhtien1 = new Double(0);
//            int perbntra = 0;
//            Double bntra = new Double(0);
//
//            double minktc = 0;
//            double maxktc = 0;
//            int tylebhyt = 0;
//            int tylebhyt2 = 0;
//            double tyleminktc = 0; // ty le min ktc
//
//            double tylemaxktc = 0; // ty le max ktc
//
//            int tyleKTC = 0;
//            double gioiHanTyLe = 0;
//            DtDmKhoiBhyt khoiBHYT = tiepDonObj.getKhoibhytMa();
//            if (khoiBHYT != null) {
//                if (khoiBHYT.getDtdmkhoibhytMinKTC() != null) {
//                    minktc = khoiBHYT.getDtdmkhoibhytMinKTC();
//                }
//                if (khoiBHYT.getDtdmkhoibhytMaxKTC() != null) {
//                    maxktc = khoiBHYT.getDtdmkhoibhytMaxKTC();
//                }
//                if (khoiBHYT.getDtdmkhoibhytTylebhyt1() != null) {
//                    tylebhyt = khoiBHYT.getDtdmkhoibhytTylebhyt1();
//                }
//                if (khoiBHYT.getDtdmkhoibhytTylebhyt2() != null) {
//                    tylebhyt2 = khoiBHYT.getDtdmkhoibhytTylebhyt2();
//                }
//                if (khoiBHYT.getDtdmkhoibhytTLMinKTC() != null) {
//                    tyleminktc = khoiBHYT.getDtdmkhoibhytTLMinKTC();
//                }
//                if (khoiBHYT.getDtdmkhoibhytTLMaxKTC() != null) {
//                    tylemaxktc = khoiBHYT.getDtdmkhoibhytTLMaxKTC();
//                }
//                if (khoiBHYT.getDtdmkhoibhytTylektc() != null) {
//                    tyleKTC = khoiBHYT.getDtdmkhoibhytTylektc();
//                }
//                if (khoiBHYT.getDtdmkhoibhytGioiHanTyLe() != null) {
//                    gioiHanTyLe = khoiBHYT.getDtdmkhoibhytGioiHanTyLe();
//                }
//
//            }
//
//
//            System.out.println("minktc:" + minktc);
//            System.out.println("maxktc:" + maxktc);
//            System.out.println("tylebhyt:" + tylebhyt);
//            System.out.println("tylebhyt2:" + tylebhyt2);
//            System.out.println("tyleminktc:" + tyleminktc);
//            System.out.println("tylemaxktc:" + tylemaxktc);
//            System.out.println("tyleKTC:" + tyleKTC);
//            System.out.println("gioiHanTyLe:" + gioiHanTyLe);
//
//            if (clsKham != null && clsKham.size() > 0) {
//
//                for (ClsKham clskham : clsKham) {
//
//                    DtDmClsBangGiaDelegate banggiaDelegate = DtDmClsBangGiaDelegate.getInstance();
//                    DtDmClsBangGia banggia = banggiaDelegate.find(clskham.getClskhamMahang().getDtdmclsbgMaso());
//
//                    if (clskham.getClskhamDongia() == null) {
//                        clskham.setClskhamDongia(new Double(0));
//                    }
//                    System.out.println("clskham.getClskhamDongia():" + clskham.getClskhamDongia());
//                    Short lanKham = clskham.getClskhamLan();
//                    if (lanKham == null) {
//                        lanKham = 0;
//                    }
//                    clskham.setClskhamLan(lanKham);
//                    System.out.println("lanKham:" + lanKham);
//
////				thanhtien1 += clskham.getClskhamDongia().doubleValue()
////						* clskham.getClskhamLan().intValue();
//
//                    System.out.println("thanhtien1:" + thanhtien1);
//
//                    if (clskham.getClskhamMien() == null || clskham.getClskhamMien().booleanValue() == false) {
//
//                        System.out.println("clskham.getClskhamMien() == null || clskham.getClskhamMien().booleanValue() == false");
//
//                        if (tiepDonObj.getDoituongMa() != null && tiepDonObj.getDoituongMa().getDmdoituongMa().equals("TP")) {
//                            System.out.println("thamKham.getTiepdonMa().getDoituongMa() != null	&& thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals('TP')");
//                            if (clskham.getClskhamYeucau().booleanValue() == true) {
//                                bntra = bntra + banggia.getDtdmclsbgDongiayc();
//                            } else {
//                                bntra = bntra + clskham.getClskhamDongia();
//                            }
//                            System.out.println("bntra:" + bntra);
//
//                        } else {// la cac doi tuong khac: BH, TE, MP
//
//                            if (clskham.getClskhamKtcao() != null || clskham.getClskhamKtcao().booleanValue() == true) {
//                                // tylebh ktc
//                                System.out.println("clskham.getClskhamKtcao() != null || clskham.getClskhamKtcao().booleanValue() == true");
//                                if (clskham.getClskhamDongia() < minktc) {
//                                    System.out.println("clskham.getClskhamDongia() < minktc");
//
//                                    perbhytchi = (int) tyleminktc;
//                                    bhytchi = bhytchi + perbhytchi * clskham.getClskhamDongia() / 100;
//                                    bntra = bntra + (clskham.getClskhamDongia() - bhytchi);
//
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("bntra:" + bntra);
//
//                                } else if (clskham.getClskhamDongia() >= minktc && clskham.getClskhamDongia() <= maxktc) {
//
//                                    System.out.println("maxktc >= clskham.getClskhamDongia() >= minktc");
//
//                                    perbhytchi = (int) tyleKTC;
//                                    bhytchi = bhytchi + perbhytchi * clskham.getClskhamDongia() / 100;
//                                    bntra = bntra + (clskham.getClskhamDongia() - bhytchi);
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("bntra:" + bntra);
//                                } else { // > max ktc
//
//                                    System.out.println("maxktc <= clskham.getClskhamDongia() ");
//                                    bhytchi = bhytchi + maxktc * tyleKTC / 100; // phan
//                                    // duoi
//                                    // gia
//                                    // ti
//                                    // maxKTC
//
//                                    bhytchi = bhytchi + (maxktc - clskham.getClskhamDongia()) * tyleKTC / 100; // phan tren gia tri
//                                    // maxKTC
//
//                                    bntra = bntra + (clskham.getClskhamDongia() - bhytchi);
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("bntra:" + bntra);
//                                }
//
//                            } else {
//                            }
//
//                        }
//                    } else {
//                        // mienphi, bn ko tra, bhyt tra 1 phan cua ho.
//
//                        // ghi vao o^ mien giam: so tien bn duoc mien giam, phan con
//                        // lai bhyt van phai tra
//
//                        // miengiam
//                        // bhyt
//
//                        if (tiepDonObj.getDoituongMa() != null && tiepDonObj.getDoituongMa().getDmdoituongMa().equals("TP")) {
//
//                            System.out.println("thamKham.getTiepdonMa().getDoituongMa() != null && thamKham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equals('TP')");
//                            if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() ==
//                                    true) {
//                                System.out.println("clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true");
//                                miengiam = miengiam + banggia.getDtdmclsbgDongiayc();
//                            } else {
//                                miengiam = miengiam + clskham.getClskhamDongia();
//                            }
//                            System.out.println("miengiam:" + miengiam);
//
//
//                        } else {// la cac doi tuong khac: BH, TE, MP
//
//                            if (clskham.getClskhamKtcao() != null || clskham.getClskhamKtcao().booleanValue() == true) {
//                                // tylebh ktc
//                                System.out.println("clskham.getClskhamKtcao() != null || clskham.getClskhamKtcao().booleanValue() == true");
//                                double sotien_tmp = 0;
//                                if (clskham.getClskhamYeucau() != null && clskham.getClskhamYeucau().booleanValue() == true) {
//                                    sotien_tmp = banggia.getDtdmclsbgDongiayc();
//                                } else {
//                                    sotien_tmp = banggia.getDtdmclsbgDongia();
//                                }
//                                if (sotien_tmp < minktc) {
//                                    System.out.println("clskham.getClskhamDongia() < minktc");
//                                    perbhytchi = (int) tyleminktc;
//                                    bhytchi = bhytchi + perbhytchi * clskham.getClskhamDongia() / 100;
//                                    miengiam = miengiam + (sotien_tmp -
//                                            bhytchi);
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("miengiam:" + miengiam);
//
//                                } else if (sotien_tmp >= minktc && sotien_tmp <= maxktc) {
//                                    System.out.println("maxktc >= clskham.getClskhamDongia() >= minktc");
//                                    perbhytchi = (int) tyleKTC;
//                                    bhytchi = bhytchi + perbhytchi * clskham.getClskhamDongia() / 100;
//                                    miengiam = miengiam + (clskham.getClskhamDongia() -
//                                            bhytchi);
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("miengiam:" + miengiam);
//                                } else { // > max ktc
//
//                                    System.out.println("maxktc <= clskham.getClskhamDongia() ");
//                                    bhytchi = bhytchi + maxktc * tyleKTC / 100; // phan
//                                    // duoi
//                                    // gia
//                                    // ti
//                                    // maxKTC
//
//                                    bhytchi = bhytchi + (maxktc - clskham.getClskhamDongia()) * tyleKTC / 100; // phan tren gia tri
//                                    // maxKTC
//
//                                    miengiam = miengiam + (clskham.getClskhamDongia() -
//                                            bhytchi);
//                                    System.out.println("perbhytchi:" + perbhytchi);
//                                    System.out.println("bhytchi:" + bhytchi);
//                                    System.out.println("miengiam:" + miengiam);
//                                }
//
//                            } else {
//                            }
//                        }
//                    }
//                }
//            }
//
//            double tongDonGiaBH = 0; // BHYT + Ngoai tru + ko phai KTC
//
//            if (clsKham != null && clsKham.size() > 0) {
//
//                for (ClsKham clskham : clsKham) {
//
//                    if (tiepDonObj.getDoituongMa() != null && tiepDonObj.getDoituongMa().getDmdoituongMa().equals("TP")) {
//                        //do nothing
//                    } else {
//                        if (clskham.getClskhamKtcao() != null || clskham.getClskhamKtcao().booleanValue() == true) {
//                            //do nothing
//                        } else {
//                            DtDmClsBangGiaDelegate banggiaDelegate = DtDmClsBangGiaDelegate.getInstance();
//                            DtDmClsBangGia banggia = banggiaDelegate.find(clskham.getClskhamMahang().getDtdmclsbgMaso());
//
//
//                            if (clskham.getClskhamYeucau() != null || clskham.getClskhamYeucau().booleanValue() == true) {
//                                bntra = bntra + banggia.getDtdmclsbgPhandv();
//                                System.out.println("bntra2:" + bntra);
//                            } else {
//                                System.out.println("vi tri 2");
//                            }
//
//
//                            tongDonGiaBH = tongDonGiaBH + clskham.getClskhamDongia();
//                            System.out.println("tongDonGiaBH:" + tongDonGiaBH);
//
//                        }
//
//                    }
//                }
//            }
//            if (tongDonGiaBH > 0) {
//                if (tongDonGiaBH < gioiHanTyLe) {
//                    System.out.println("tongDonGiaBH < gioiHanTyLe");
//                    perbhytchi = tylebhyt2;
//                    bhytchi += perbhytchi * tongDonGiaBH / 100;
//                    miengiam += tongDonGiaBH - bhytchi;
//                    System.out.println("perbhytchi:" + perbhytchi);
//                    System.out.println("bhytchi:" + bhytchi);
//                    System.out.println("miengiam:" + miengiam);
//                } else { //>= gioi han ty le
//
//                    System.out.println("11: tylebhyt: " + tylebhyt);
//                    perbhytchi = tylebhyt;
//
//                    System.out.println("11: bhytchi: " + bhytchi);
//
//                    bhytchi += perbhytchi * tongDonGiaBH / 100;
//                    miengiam += tongDonGiaBH - bhytchi;
//                    System.out.println("perbhytchi:" + perbhytchi);
//                    System.out.println("bhytchi:" + bhytchi);
//                    System.out.println("miengiam:" + miengiam);
//                }
//
//            }
//            Double thtoanCLS = hsThanhToan.getHsthtoankCls();
//            if(thtoanCLS == null){
//                thtoanCLS = new Double(0);
//            }
//            if (add) {
//                
//                hsThanhToan.setHsthtoankCls(thtoanCLS + bntra);
//            } else {
//                hsThanhToan.setHsthtoankCls(thtoanCLS - bntra);
//            }
//            
//            Double thtoankBNTra = hsThanhToan.getHsthtoankBntra();
//            if (thtoankBNTra == null){
//                thtoankBNTra = new Double(0);
//            }
//            hsThanhToan.setHsthtoankBntra( thtoankBNTra + bntra);
//            Utils.setInforForHsThToan(hsThanhToan);
//
//
//
//
//        } else {
//            System.out.println("Ho So Thanh Toan Kham null tai capNhatThongTinHoSoThanhToanKham() ??????????");
//        }
//
//    }

//    private void capNhatThongTinHoSoThanhToanKhamHoanTra(HsThtoank hsThanhToan, ClsKham clsKham,
//            TiepDon tiepDonObj, boolean add) {
//        if (hsThanhToan != null) {
//            DtDmClsBangGia bangGia = clsKham.getClskhamMahang();
//
//            // bang gia chi co' ma va` ma so,
//            // phai tim dung trong database
//            DtDmClsBangGiaFacade bangGiaFacade = new DtDmClsBangGiaFacade();
//            bangGiaFacade.setEm(em);
//
//            bangGia = bangGiaFacade.find(bangGia.getDtdmclsbgMaso());
//
//            System.out.println("bangGia.getDtdmclsbgDongia():" + bangGia.getDtdmclsbgDongia());
//            System.out.println("bangGia.getDtdmclsbgDongiayc():" + bangGia.getDtdmclsbgDongiayc());
//            System.out.println("bangGia.getDtdmclsbgDongiamp():" + bangGia.getDtdmclsbgDongiamp());
//
//            Short soLuongTra = clsKham.getClskhamTra();
//            if (soLuongTra == null) {
//                soLuongTra = 0;
//            }
//            if (clsKham.getClskhamYeucau() != null && clsKham.getClskhamYeucau() == true) {
//                double donGiaYC = bangGia.getDtdmclsbgDongiayc() == null ? 0 : bangGia.getDtdmclsbgDongiayc().doubleValue();
//
//                Double clsOld = hsThanhToan.getHsthtoankCls();
//                if (clsOld == null) {
//                    clsOld = new Double(0);
//                }
//
//                System.out.println("1clsOld:" + clsOld);
//                System.out.println("1donGiaYC:" + donGiaYC);
//                if (add) {
//                    hsThanhToan.setHsthtoankCls(clsOld + donGiaYC * soLuongTra);
//                } else {
//                    hsThanhToan.setHsthtoankCls(clsOld - donGiaYC * soLuongTra);
//                }
//
//            } else if (clsKham.getClskhamMien() != null && clsKham.getClskhamMien() == true) {
//
//                double donGiaMien = bangGia.getDtdmclsbgDongiamp() == null ? 0 : bangGia.getDtdmclsbgDongiamp();
//
//                Double clsOld = hsThanhToan.getHsthtoankCls();
//                if (clsOld == null) {
//                    clsOld = new Double(0);
//                }
//                System.out.println("2clsOld:" + clsOld);
//                System.out.println("2donGiaMien:" + donGiaMien);
//                if (add) {
//                    hsThanhToan.setHsthtoankCls(clsOld + donGiaMien * soLuongTra);
//                } else {
//                    hsThanhToan.setHsthtoankCls(clsOld - donGiaMien * soLuongTra);
//                }
//
//            } else if (tiepDonObj.getDoituongMa() != null && tiepDonObj.getDoituongMa().getDmdoituongMa() != null && tiepDonObj.getDoituongMa().getDmdoituongMa().equals("BH")) {
//                double donGiaBH = bangGia.getDtdmclsbgDongiabh() == null ? 0 : bangGia.getDtdmclsbgDongiabh();
//
//                Double clsOld = hsThanhToan.getHsthtoankCls();
//                if (clsOld == null) {
//                    clsOld = new Double(0);
//                }
//                if (add) {
//                    hsThanhToan.setHsthtoankCls(clsOld + donGiaBH * soLuongTra);
//                } else {
//                    hsThanhToan.setHsthtoankCls(clsOld - donGiaBH * soLuongTra);
//                }
//                Double bhytOld = hsThanhToan.getHsthtoankBhyt();
//                if (bhytOld == null) {
//                    bhytOld = new Double(0);
//                }
//                System.out.println("3clsOld:" + clsOld);
//                System.out.println("3donGiaBH:" + donGiaBH);
//
//                if (add) {
//                    hsThanhToan.setHsthtoankBhyt(bhytOld + donGiaBH * soLuongTra);
//                } else {
//                    hsThanhToan.setHsthtoankBhyt(bhytOld - donGiaBH * soLuongTra);
//                }
//
//
//
//
//            } else {
//
//                double donGia = bangGia.getDtdmclsbgDongia() == null ? 0 : bangGia.getDtdmclsbgDongia();
//
//                Double clsOld = hsThanhToan.getHsthtoankCls();
//                if (clsOld == null) {
//                    clsOld = new Double(0);
//                }
//
//                System.out.println("4clsOld:" + clsOld);
//                System.out.println("4donGia:" + donGia);
//
//                if (add) {
//                    hsThanhToan.setHsthtoankCls(clsOld + donGia * soLuongTra);
//                } else {
//                    hsThanhToan.setHsthtoankCls(clsOld - donGia * soLuongTra);
//                }
//
//            }
//
//            ///
//            System.out.println("Luu Tru Ho So Thanh Toan Kham");
//            Utils.setInforForHsThToan(hsThanhToan);
//
//
//
//
//        } else {
//            System.out.println("Ho So Thanh Toan Kham null tai capNhatThongTinHoSoThanhToanKham() ??????????");
//        }
//
//    }
// B121_2
    public String createClsKhamForCLSPhauThuat(
            List<ClsKham> listCLS, String maTiepDon, String maBanKham) {

        if (listCLS == null) {
            return "";
        }

        String result = "";
        try {
            String listclsMa = "";
            for (int i = 0; i < listCLS.size(); i++) {
                ClsKham cls = listCLS.get(i);
                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {

                    if (listclsMa.equals("")) {
                        listclsMa += cls.getClskhamMa();
                    } else {
                        listclsMa += "," + cls.getClskhamMa();
                    }
                    
                    //thanh add 24/12/2010
                    if (dieuTriUtils == null) {
                        dieuTriUtils = new DieuTriUtilFacade();
                        dieuTriUtils.setEm(em);
                    }
                    List<ClsKetQua> listClsKetQua = dieuTriUtils.findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", cls.getClskhamMa() + "", "");
                     for (ClsKetQua clsKetQua: listClsKetQua) {
                         getEm().remove(clsKetQua);
                     }
                    //thanh add 24/12/2010
                }
            }
            System.out.println(listclsMa);
            //if (!listclsMa.equals("")) {

            this.removeItem(listclsMa, maTiepDon, maBanKham);
            //}
            int count = 0;
            for (ClsKham clsKham : listCLS) {
                if (clsKham.getClskhamMaphieu() != null) {
                    continue;
                }

                if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }

                }
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }

                }
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }

                }

                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);
                clsKham.setClskhamNgaygio(cal);    
                if (clsKham.getClskhamMa() != null) {
                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");
                } else {

                    getEm().persist(clsKham);
                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
                }
                
                //thanh add 24/12/2010
                if (dieuTriUtils == null) {
                    dieuTriUtils = new DieuTriUtilFacade();
                    dieuTriUtils.setEm(em);
                }
                count++;
                System.out.println("count " + count);
                DtDmClsBangGia dtDmClsBangGia = clsKham.getClskhamMahang();
                if (dtDmClsBangGia.getDtdmclsbgXn() != null) {
                    List<DtDmClsKetQua> listDtDmClsKetQua = dieuTriUtils.findByAllConditions("DtDmClsKetQua", "dtdmclsbgMaso", "dtdmclskqTen", dtDmClsBangGia.getDtdmclsbgMaso() + "", "");
                    for (int j = 0; j < listDtDmClsKetQua.size(); j++) {
                        System.out.println("j " + j);
                        ClsKetQua clsKetQua = new ClsKetQua();
                        clsKetQua.setClskqMa(listDtDmClsKetQua.get(j).getDtdmclskqMa());
                        clsKetQua.setClskqTen(listDtDmClsKetQua.get(j).getDtdmclskqTen());
                        clsKetQua.setClskqGhiChu(listDtDmClsKetQua.get(j).getDtdmclskqGhiChu());
                        clsKetQua.setClskhamMaso(clsKham);
                        getEm().persist(clsKetQua);
                    }
                }
                
                //thanh add 24/12/2010


            }
            result = "";

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }

        System.out.println("result " + result);
        return result;
    }

    private void removeItem(String listclsMa, String tiepdonMa, String maBanKham, HsThtoank hsThanhToan, TiepDon tiepDonObj) {
        Query q;
        if (listclsMa.equals("")) {

            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null   AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND c.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :maBanKham");

        } else {
            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null  and c.clskhamMa NOT IN (" + listclsMa + ") AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND c.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :maBanKham");


        }

        q.setParameter("tiepdonMa", tiepdonMa);
        q.setParameter("maBanKham", maBanKham);

        List<ClsKham> listCLS = q.getResultList();
        for (ClsKham clsKham : listCLS) {
            getEm().remove(clsKham);
        }

    }

    private void removeItem(String listclsMa, String tiepdonMa, HsThtoank hsThanhToan, TiepDon tiepDonObj) {
        Query q;
        if (listclsMa.equals("")) {

            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null   AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa ");

        } else {
            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null  and c.clskhamMa NOT IN (" + listclsMa + ") AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa ");


        }

        q.setParameter("tiepdonMa", tiepdonMa);


        List<ClsKham> listCLS = q.getResultList();
        for (ClsKham clsKham : listCLS) {
            getEm().remove(clsKham);
        }

    }

    public void removeItem(String listclsMa, String tiepdonMa, String maBanKham) {
        Query q;
        if (listclsMa.equals("")) {

            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null   AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND c.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :maBanKham");

        } else {
            q = getEm().createQuery("SELECT c FROM ClsKham c WHERE c.clskhamMaphieu is null  and c.clskhamMa NOT IN (" + listclsMa + ") AND c.clskhamThamkham.tiepdonMa.tiepdonMa like :tiepdonMa AND c.clskhamThamkham.thamkhamBankham.dtdmbankhamMa like :maBanKham");


        }

        q.setParameter("tiepdonMa", tiepdonMa);
        q.setParameter("maBanKham", maBanKham);

        List<ClsKham> listCLS = q.getResultList();
        System.out.println("remove**********************");
        for (ClsKham clsKham : listCLS) {

            System.out.println("remove" + clsKham.getClskhamMa());
            //thanh add 24/12/2010
            if (dieuTriUtils == null) {
                dieuTriUtils = new DieuTriUtilFacade();
                dieuTriUtils.setEm(em);
            }
            List<ClsKetQua> listClsKetQua = dieuTriUtils.findByAllConditions("ClsKetQua", "clskhamMaso", "clskqTen", clsKham.getClskhamMa() + "", "");
             for (ClsKetQua clsKetQua: listClsKetQua) {
                 getEm().remove(clsKetQua);
             }
            //thanh add 24/12/2010
            getEm().remove(clsKham);
        }
        System.out.println("remove**********************");
    }

    public String findTiepdonMa(
            List<ClsKham> listCLS) {
        String result = "";
        for (ClsKham tnt : listCLS) {
            if (tnt.getClskhamThamkham().getTiepdonMa() != null) {
                result = tnt.getClskhamThamkham().getTiepdonMa().getTiepdonMa();
            }

        }
        return result;
    }

    public List<ClsKham> findByTiepdonMaBhyt(String tiepdonMa) {
        // tiepdonMa = Utils.formatMa(em, tiepdonMa);

        Query q = getEm().createQuery("Select c from ClsKham c Where c.tiepdonMa.tiepdonMa = :tiepdonMa " +
                " and c.clskhamDatt = false ");
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    // phuc.lc 07/01/2011
    public String createClsKham_v2(List<ClsKham> listCLS, String maTiepDon, String maPhieuTT) {
        System.out.println("Begin createClsKham_v2, maPhieuTT = " + maPhieuTT); 
        if (listCLS == null) {
            return "";
        }
       
        boolean bGenerateMaPhieu = false;
        for (ClsKham cls : listCLS) {
            if (cls.getClskhamMaphieu() != null) {
                continue;
            }
            if (cls.getClskhamDatt()) {
                bGenerateMaPhieu = true;
                break;
            }
        }
        System.out.println("bGenerateMaPhieu:" + bGenerateMaPhieu);

       // String maPhieu = "";
        if (bGenerateMaPhieu == true) {
            //maPhieu = Utils.createMaPhieu(em);
            for (ClsKham canLamSangKham : listCLS) {
                if (canLamSangKham.getClskhamMaphieu() != null) {
                    continue;
                }
                if (canLamSangKham.getClskhamDatt()) {
                    canLamSangKham.setClskhamMaphieu(maPhieuTT);
                }
            }
        }

        String result = "";

        HsThtoankFacade hsThtoankFacade = new HsThtoankFacade();
        hsThtoankFacade.setEm(em);
        HsThtoank hsThanhToan = hsThtoankFacade.findBytiepdonMa(maTiepDon);


        TiepDonFacade tdFacade = new TiepDonFacade();
        tdFacade.setEm(em);
        TiepDon tiepDonObj = tdFacade.find(maTiepDon);

        try {
            String listclsMa = "";
            for (int i = 0; i < listCLS.size(); i++) {
                ClsKham cls = listCLS.get(i);
                if (cls.getClskhamMa() != null && !cls.getClskhamMa().equals("")) {

                    if (listclsMa.equals("")) {
                        listclsMa += cls.getClskhamMa();
                    } else {
                        listclsMa += "," + cls.getClskhamMa();
                    }
                }
            }
            System.out.println(listclsMa);
            //if (!listclsMa.equals("")) {
            this.removeItem(listclsMa, maTiepDon, hsThanhToan, tiepDonObj);
            // }
            for (ClsKham clsKham : listCLS) {
                if (clsKham.getClskhamKhoa() != null) {
                    String khoaMa = clsKham.getClskhamKhoa().getDmkhoaMa();
                    System.out.println("khoa ma " + khoaMa);
                    if (khoaMa != null && !"".equals(khoaMa)) {
                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }
                        DmKhoa khoa = (DmKhoa) dieuTriUtils.findByMa(khoaMa, "DmKhoa", "dmkhoaMa");
                        System.out.println("khoa ma sau khi set " + khoa.getDmkhoaMa());
                        clsKham.setClskhamKhoa(khoa);
                    } else {
                        clsKham.setClskhamKhoa(null);
                    }
                }
                if (clsKham.getClskhamKhoa2() != null) {
                    String khoaMa2 = clsKham.getClskhamKhoa2().getDmkhoaMa();
                    if (khoaMa2 != null && !"".equals(khoaMa2)) {

                        if (dieuTriUtils == null) {
                            dieuTriUtils = new DieuTriUtilFacade();
                            dieuTriUtils.setEm(em);

                        }

                        DmKhoa khoa2 = (DmKhoa) dieuTriUtils.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
                        clsKham.setClskhamKhoa2(khoa2);
                        System.out.println("khoa ma 2 " + khoaMa2);
                    } else {

                        clsKham.setClskhamKhoa2(null);
                        System.out.println("khoa ma 2 null");
                    }
                }
                if (clsKham.getClskhamMahang() != null) {
                    String maHang = clsKham.getClskhamMahang().getDtdmclsbgMa();
                    if (maHang != null && !"".equals(maHang)) {
                        System.out.println("ma hang  " + maHang);
                        System.out.println("ma hang so: " + clsKham.getClskhamMahang().getDtdmclsbgMaso());
                    } else {
                        clsKham.setClskhamMahang(null);
                        System.out.println("ma hang null");
                    }
                }


                Date cal = new Date();
                clsKham.setClskhamNgaygiocn(cal);
                if (clsKham.getClskhamMa() != null) {

                    //
                    // Trong truong hop nay: ta phai get cai cu, so sanh CLS-> gia' va` so luong
                    //
                    ClsKham clsKham_DB = find(clsKham.getClskhamMa());

                    // thay doi CLS
                    // xoa so tien da tinh toan tai ho so benh an cu

                    //
                    //
                    //
                    getEm().merge(clsKham);
                    System.out.println("update thanh cong chi tiet cls kham");
                } else {

                    System.out.println("****************:" + clsKham.getClskhamMaphieu());
                    getEm().persist(clsKham);
                    System.out.println("****************:" + clsKham.getClskhamMaphieu());
                    /**
                     * 
                     *  Tinh tien
                     * 
                     * 
                     */
                    System.out.println("insert them thanh cong chi tiet thuoc " + clsKham.getClskhamMa());
                }



            }
            result = maPhieuTT;

        } catch (Exception ex) {
            result = "";
            System.out.println(ex.toString());
            ex.printStackTrace();
            context.setRollbackOnly();
        }
        System.out.println("result " + result);
        return result;
    }    
}


