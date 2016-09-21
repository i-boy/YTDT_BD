/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.CauHinh;
import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.HoanUngKham;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import com.iesvn.yte.dieutri.entity.TamUngKham;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.utils.CauHinhFacade;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import java.text.SimpleDateFormat;
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
public class ThamKhamFacade implements ThamKhamFacadeLocal, ThamKhamFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    @EJB
    private ThuocPhongKhamFacadeLocal thuocPhongKhamFacade;

    public void create(ThamKham thamKham) {
        em.persist(thamKham);
    }

    public void edit(ThamKham thamKham) {
        em.merge(thamKham);
    }

    public void remove(ThamKham thamKham) {
        em.remove(em.merge(thamKham));
    }

    public ThamKham find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.ThamKham.class, id);
    }

    public String getMaTiepDonByGoiBN(String maBanKham){
        String result = null;
        List<TiepDon> lstTD = getEm().createQuery("select object(o) from TiepDon as o where o.tiepdonSoTT is not null and o.tiepdonBankham.dtdmbankhamMa like :maBanKham order by o.tiepdonSoTT  ").setParameter("maBanKham", maBanKham).getResultList();
      
        if (lstTD != null && lstTD.size() > 0) {
           
            TiepDon td = lstTD.get(0);
            result = td.getTiepdonMa();
        }
        return result;
    }
 public String xoaSoThuThuBNKham(String maTiepDon){
       
        Query q = getEm().createQuery("update TiepDon o set o.tiepdonSoTT = null where o.tiepdonMa like :maTiepDon  ");
        q.setParameter("maTiepDon", maTiepDon);
        q.executeUpdate();
        return maTiepDon;
    }

    /*
     * Thanh add 05/10/2011
     * Đã check, Date ok
     */
    /**
     * 
     * @param banKhamMa
     * @param ngayThamKham
     * @return
     */
    public List<ThamKham> findThamKhamByBanKhamMaAndNgay(String banKhamMa, Date ngayThamKham) {

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

            /*
             xem lại câu query chưa xử lý được ,tạm thời là ở đây bỏ group by
             *  Query q = getEm().createQuery("select object(o) from ThamKham as o where ( o.thamkhamNgaygio >= :dDauNgay  and o.thamkhamNgaygio <= :dCuoiNgay) and o.thamkhamBankham.dtdmbankhamMa = :banKhamMa group by o.tiepdonMa, o.thamkhamBankham");
             */
            Query q = getEm().createQuery("select object(o) from ThamKham as o where ( o.thamkhamNgaygio >= :dDauNgay  and o.thamkhamNgaygio <= :dCuoiNgay) and o.thamkhamBankham.dtdmbankhamMa = :banKhamMa");
            q.setParameter("dDauNgay", dDauNgay);
            q.setParameter("dCuoiNgay", dCuoiNgay);
            q.setParameter("banKhamMa", banKhamMa);
            return q.getResultList();
        } catch (Exception ex) {

            return null;
        }
    }

    public ThamKham findByBanKhamVaMaTiepDon(String banKham, String maTiepDon) {
        if(maTiepDon != null) {
            if (maTiepDon.length() < Utils.LENGTH_MA) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }
        // 20110914 bao.ttc: TH he thong bi loi tao ra nhieu thamkham co cung MaTD va BanKham thi chon thamkham dau tien de khong bi mat thong tin
        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.thamkhamBankham.dtdmbankhamMa like :dtdmbankhamMa and o.tiepdonMa.tiepdonMa like :tiepdonMa  order by o.thamkhamMa ASC").setParameter("dtdmbankhamMa", banKham).setParameter("tiepdonMa", maTiepDon).getResultList();
        //System.out.println(hsbaCV);
        if (thamKham != null && thamKham.size() > 0) {
            //System.out.println("-------------------------");
            return thamKham.get(0);
        } else {
            return null;
        }
    }

    private ThamKham findByBanKhamMaSoVaMaTiepDon(Integer banKhamMaSo, String maTiepDon) {
        if(maTiepDon != null) {
            if (maTiepDon.length() < 12) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }
        // 20110914 bao.ttc: TH he thong bi loi tao ra nhieu thamkham co cung MaTD va BanKham thi chon thamkham dau tien de khong bi mat thong tin
        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.thamkhamBankham.dtdmbankhamMaso like :banKhamMaSo and o.tiepdonMa.tiepdonMa like :tiepdonMa  order by o.thamkhamMa ASC").setParameter("banKhamMaSo", banKhamMaSo).setParameter("tiepdonMa", maTiepDon).getResultList();
        //System.out.println(hsbaCV);
        if (thamKham != null && thamKham.size() > 0) {
            //System.out.println("-------------------------");
            return thamKham.get(0);
        } else {
            return null;
        }
    }

    public ThamKham findByMaTiepDon(String maTiepDon) {
        if (maTiepDon != null) {
            if (maTiepDon.length() < 12) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }
        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.tiepdonMa.tiepdonMa like :tiepdonMa order by o.thamkhamMa DESC").setParameter("tiepdonMa", maTiepDon).getResultList();
        //System.out.println(hsbaCV);
        if (thamKham != null && thamKham.size() > 0) {
            //System.out.println("-------------------------");
            return thamKham.get(0);
        } else {
            return null;
        }
    }

    public ThamKham findThamKhamByMaTiepDonFirst(String maTiepDon) {
        if (maTiepDon != null) {
            if (maTiepDon.length() < 12) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }

        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.tiepdonMa.tiepdonMa = :tiepdonMa order by o.thamkhamMa ASC").setParameter("tiepdonMa", maTiepDon).getResultList();

        if (thamKham != null && thamKham.size() > 0) {
            return thamKham.get(0);
        } else {
            return null;
        }
    }

    public List<ThamKham> findAllByMaTiepDon(String maTiepDon) {
        if (maTiepDon != null) {
            if (maTiepDon.length() < 12) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }
        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.tiepdonMa.tiepdonMa like :tiepdonMa order by o.thamkhamMa DESC").setParameter("tiepdonMa", maTiepDon).getResultList();
        return thamKham;
    }

    /*
     * Thanh add 05/10/2011
     * Date ok
     */
    public List<ThamKham> findThamKhamByNgay(Date ngayThamKham) {

        try {


            SimpleDateFormat formatterSQL;

            formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
            String ngayGio = formatterSQL.format(ngayThamKham);

            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            String sDauNgay = ngayGio + " 00:00:00";
            String sCuoiNgay = ngayGio + " 23:59:59";

            Date dDauNgay = formatter.parse(sDauNgay);
            Date dCuoiNgay = formatter.parse(sCuoiNgay);

            Query q = getEm().createQuery("select object(o) from ThamKham as o where ( o.thamkhamNgaygio >= :dDauNgay  and o.thamkhamNgaygio <= :dCuoiNgay)  ");
            q.setParameter("dDauNgay", dDauNgay);
            q.setParameter("dCuoiNgay", dCuoiNgay);

            return q.getResultList();
        } catch (Exception ex) {

            return null;
        }
    }

    public ThamKham findByBanKhamWithLoaiKhoaVaMaTiepDon(String dtdmloaikhoaMa, String maTiepDon) {
        if(maTiepDon != null) {
            if (maTiepDon.length() < 12) {
                maTiepDon = Utils.formatMa(getEm(), maTiepDon);
            }
        }
        List<ThamKham> thamKham = getEm().createQuery("select object(o) from ThamKham as o where o.thamkhamBankham.dtdmLoaiKhoa.dtdmloaikhoaMa like :dtdmloaikhoaMa and o.tiepdonMa.tiepdonMa like :tiepdonMa").setParameter("dtdmloaikhoaMa", dtdmloaikhoaMa).setParameter("tiepdonMa", maTiepDon).getResultList();
        //System.out.println(hsbaCV);
        if (thamKham != null && thamKham.size() > 0) {
            //System.out.println("-------------------------");
            return thamKham.get(0);
        } else {
            return null;
        }
    }

    // 20111207 bao.ttc: Option 1 (Khanh Hoa) Toan bo chi phi CLS va Thuoc se thuoc phieu thanh toan Noi tru
    public String chuyenSoLieuVaoNoiTru(Hsba hsba, HsbaKhoa hsbaKhoa, DmKhoa khoa, String matiepdon) {

        System.out.println("--- chuyenSoLieuVaoNoiTru --- Option 1");
        String cvnt = "";
        try {
            // phuc.lc 04-11-2010 : BEGIN  chuyen tam ung cap cuu vao noi tru (tu bang tam_ung_kham vao bang tam_ung)
    //            Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon");
    //            q.setParameter("maTiepdon", matiepdon);
    //            List<TamUngKham> lstTamUngKham =  q.getResultList();
    //            if (lstTamUngKham.size() > 0) {
    //                String tamUngMa = "";
    //                for(TamUngKham tuk : lstTamUngKham) {
    //                    TamUng tu = new TamUng();
    //                    tamUngMa = com.iesvn.yte.dieutri.utils.Utils.maPhieuTamUng(em);
    //                    tu.setTamungMa(tamUngMa);
    //                    tu.setHsbaSovaovien(hsba);
    //                    tu.setTamungKhoa(khoa);
    //
    //                    tu.setTamungSotien(tuk.getTamungkhamSotien());
    //                    tu.setTamungNgaygio(tuk.getTamungkhamNgay());
    //                    tu.setTamungCum(tuk.getTamungkhamCum());
    //                    tu.setTamungLydo(tuk.getTamungkhamLydo());
    //                    tu.setTamungThungan(tuk.getTamungkhamThungan());
    //                    em.persist(tu);
    //                    // Sau khi chuyen so lieu tam ung kham vao  noi tru, cap nhat lai trang thai cua tam ung kham (set Status = 1)
    //                    // de khi thanh toan vien phi khong tinh tien tam ung kham da chuyen vao noi tru nua
    //                    tuk.setTamungkhamStatus("1");
    //                    em.merge(tuk);
    //                }
    //            }
            // phuc.lc 04-11-2010 : END chuyen tam ung cap cuu vao noi tru

            //truong hop 1: chuyen can lam sang
            System.out.println("Chuyen Can lam sang kham vao Can lam sang mo");
            ClsKhamFacade clsKhamFacade = new ClsKhamFacade();
            clsKhamFacade.setEm(em);
            List<ClsKham> lstClsKham = clsKhamFacade.findByTiepdonma(matiepdon);

            if (lstClsKham != null && lstClsKham.size() > 0) {
                for (ClsKham clskham : lstClsKham) {

                    if ( (clskham.getClskhamDatt() != null && clskham.getClskhamDatt()) || clskham.getClskhamNgaygiott() != null ){
                        //do nothing: da thanh toan thi khong chuyen vao (BN Thu phi)
                        System.out.println("CLS da thanh toan thi khong chuyen vao (BN Thu phi) --> Continue");
                        continue;
                    }

                    if (clskham.getClskhamMaloai() != null && clskham.getClskhamMaloai().getDtdmclsMaso() == 7) {
                        // khong chuyen tien cong kham doi voi BN BHYT
                        em.remove(clskham);
                        System.out.println("Khong chuyen tien cong kham (BN BHYT) --> Delete, Continue");
                        continue;
                    }


                    ClsMo clsMo = new ClsMo();

                    clsMo.setHsbaKhoa(hsbaKhoa);
                    clsMo.setClsmoMaphieu(clskham.getClskhamMaphieu());
                    clsMo.setClsmoKhoa(clskham.getClskhamKhoa());
                    clsMo.setClsmoNgay(clskham.getClskhamNgaygio());
                    clsMo.setClsmoMahang(clskham.getClskhamMahang());

                    clsMo.setClsmoLoaicls(clskham.getClskhamMaloai());
                    clsMo.setClsmoLoai(clskham.getClskhamLoai());
                    clsMo.setClsmoYeucau(clskham.getClskhamYeucau());
                    clsMo.setClsmoKhongthu(clskham.getClskhamKhongthu());
                    clsMo.setClsmoMien(clskham.getClskhamMien());

                    //clsMo.setClsmoNhi(Boolean.TRUE);
                    //clsMo.setClsmoLao(Boolean.FALSE);
                    clsMo.setClsmoDichvu(clskham.getClskhamDichvu());
                    clsMo.setClsmoKtcao(clskham.getClskhamKtcao());
                    clsMo.setClsmoLan(clskham.getClskhamLan());

                    clsMo.setClsmoTra(clskham.getClskhamTra());
                    clsMo.setClsmoPhandv(clskham.getClskhamPhandv());
                    clsMo.setClsmoDongia(clskham.getClskhamDongia());
                    clsMo.setClsmoDongiabh(clskham.getClskhamDongiabh());
                    //clsMo.setClsmoDongiadv

                    //clsMo.setClsmoDongiatp
                    //clsMo.setClsmoTylebh
                    clsMo.setClsmoTylebhktc(clskham.getClskhamTylebhktc());
                    clsMo.setClsmoBacsi(clskham.getClskhamMabs());
                    clsMo.setClsmoKhoathuchien(clskham.getClskhamKhoa());

                    //clsMo.setClsmoNgaygiocn
                    //clsMo.setClsmoMaduyet
                    clsMo.setClsmoNhanviencn(clskham.getClskhamNhanviencn());
                    clsMo.setClsmoThungan(clskham.getClskhamThungan());
                    clsMo.setClsmoChandoan(clskham.getClskhamChandoan());

                    clsMo.setClsmoKetqua(clskham.getClskhamKetqua());
                    clsMo.setClsmoThuchien(clskham.getClskhamThuchien());
                    clsMo.setClsmoDuongtinh(clskham.getClskhamDuongtinh());
                    clsMo.setClsmoTieuban(clskham.getClskhamTieuban());
                    clsMo.setClsmoLam(clskham.getClskhamLam());

                    clsMo.setClsmoOrd(clskham.getClskhamOrd());
                    clsMo.setClsmoDatt(clskham.getClskhamDatt());
                    clsMo.setClsmoNDM(clskham.getClskhamNdm());
                    clsMo.setClsmoGhichu(clskham.getClskhamGhichu());
                    clsMo.setClsmoDongiabntra(clskham.getClskhamDongiabntra());

                    //clsMo.setClsmoLoai2
                    //clsMo.setClsmoSoml
                    clsMo.setClsmoDongiatt(clskham.getClskhamDongiatt());
                    clsMo.setClsmoThanhtien(clskham.getClskhamThanhtien());
                    clsMo.setClsmoTh(clskham.getClskhamTh());

                    em.persist(clsMo);
                    em.remove(clskham);

                } // for
            } //  if (lstClsKham != null && lstClsKham.size() > 0)


            //truong hop 2: chuyen tien thuoc
            System.out.println("chuyen Thuoc phong kham vao Thuoc noi tru");
            List<ThuocPhongKham> listThuocPhongKham = thuocPhongKhamFacade.find2LoaiByMaTiepDon(matiepdon, "1", "3");// thuoc Ban kham, Ke toa Quay BV

            if (listThuocPhongKham != null && listThuocPhongKham.size() > 0) {

                for (ThuocPhongKham thuocPKham : listThuocPhongKham) {

                    if ( (thuocPKham.getThuocphongkhamDatt() != null && thuocPKham.getThuocphongkhamDatt()) || thuocPKham.getThuocphongkhamNgaygiott() != null ){
                        //do nothing: da thanh toan thi khong chuyen vao (BN Thu phi)
                        System.out.println("Thuoc da thanh toan thi khong chuyen vao (BN Thu phi) --> Continue");
                        continue;
                    }

                    if (thuocPKham != null && thuocPKham.getThuocphongkhamLoai().equals("3") && thuocPKham.getThuocphongkhamNgaygiott() == null && thuocPKham.getThuocphongkhamMaphieud() == null) {
                        // Thuoc Quay BV da ke toa nhung chua linh, xoa di khong chuyen vao noi tru
                        System.out.println("Thuoc Quay BV da ke toa nhung chua linh, xoa di khong chuyen vao noi tru - continue");
                        em.remove(thuocPKham);
                        continue;
                    }

                    if (thuocPKham != null){
                        ThuocNoiTru thuocNoiTru = new ThuocNoiTru();

                        thuocNoiTru.setHsbaKhoa(hsbaKhoa);
                        //thuocNoiTru.setThuocnoitruMaphieupdttra
                        //thuocNoiTru.setThuocnoitruMaphieu
                        thuocNoiTru.setThuocnoitruKhoa(khoa);
                        //thuocNoiTru.setThuocnoitruPhong

                        //thuocNoiTru.setThuocnoitruBosung
                        thuocNoiTru.setThuocnoitruNgaygio(thuocPKham.getThuocphongkhamNgaygio());
                        thuocNoiTru.setThuocnoitruMathuoc(thuocPKham.getThuocphongkhamMathuoc());
                        //thuocNoiTru.setThuocnoitruMaphong
                        thuocNoiTru.setThuocnoitruQuocgia(thuocPKham.getThuocphongkhamQuocgia());

                        thuocNoiTru.setThuocnoitruNguon(thuocPKham.getDmnctMaso());
                        thuocNoiTru.setThuocnoitruPhanloai(thuocPKham.getThuocphongkhamPhanloai());
                        thuocNoiTru.setThuocnoitruLoai("");
                        thuocNoiTru.setThuocnoitruYeucau(thuocPKham.getThuocphongkhamYeucau());
                        thuocNoiTru.setThuocnoitruMien(thuocPKham.getThuocphongkhamMien());

                        //thuocNoiTru.setThuocnoitruNhi
                        //thuocNoiTru.setThuocnoitruLao
                        thuocNoiTru.setThuocnoitruSoluong(thuocPKham.getThuocphongkhamSoluong());
                        thuocNoiTru.setThuocnoitruTra(thuocPKham.getThuocphongkhamTra());
                        thuocNoiTru.setThuocnoitruDongia(thuocPKham.getThuocphongkhamDongia());

                        thuocNoiTru.setThuocnoitruDongiabh(thuocPKham.getThuocphongkhamDongiabh());
                        thuocNoiTru.setThuocnoitruNgaytt(thuocPKham.getThuocphongkhamNgaygiott());
                        //thuocNoiTru.setThuocnoitruCum
                        //thuocNoiTru.setThuocnoitruLanlinh
                        thuocNoiTru.setThuocnoitruHangsx(thuocPKham.getThuocphongkhamHangsx());

                        //thuocNoiTru.setThuocnoitruSodangky
                        thuocNoiTru.setThuocnoitruKhongthu(thuocPKham.getThuocphongkhamKhongthu());
                        thuocNoiTru.setThuocnoitruBacsi(thuocPKham.getThuocphongkhamBacsi());
                        thuocNoiTru.setThuocnoitruKho(khoa); // set tam thoi
                        thuocNoiTru.setThuocnoitruStatus("0"); // set tam thoi

                        //thuocNoiTru.setThuocnoitruNgayinlinh
                        //thuocNoiTru.setThuocnoitruLanintra
                        //thuocNoiTru.setThuocnoitruNgayintra
                        //thuocNoiTru.setThuocnoitruDongianhap
                        thuocNoiTru.setThuocnoitruDongiaban(thuocPKham.getThuocphongkhamDongiaban());

                        thuocNoiTru.setThuocnoitruNgaygiocn(new Date());
                        thuocNoiTru.setThuocnoitruNhanviencn(thuocPKham.getThuocphongkhamNhanviencn());
                        thuocNoiTru.setThuocnoitruThungan(thuocPKham.getThuocphongkhamThungan());
                        //thuocNoiTru.setThuocnoitruStt
                        if (thuocPKham.getThuocphongkhamLoai().equals("1")){
                            thuocNoiTru.setThuocnoitruTutrucPdt(1); // Ban kham: 1
                        } else {
                            thuocNoiTru.setThuocnoitruTutrucPdt(0); // Quay BV: 0
                        }
                        thuocNoiTru.setThuocnoitruMalk(thuocPKham.getThuocphongkhamMalk());
                        thuocNoiTru.setThuocnoitruNamnhap(thuocPKham.getThuocphongkhamNamnhap());
                        thuocNoiTru.setThuocnoitruNgayhd(thuocPKham.getThuocphongkhamNgayhd());
                        thuocNoiTru.setThuocnoitruThanghd(thuocPKham.getThuocphongkhamThanghd());
                        thuocNoiTru.setThuocnoitruNamhd(thuocPKham.getThuocphongkhamNamhd());

                        thuocNoiTru.setThuocnoitruNDM(thuocPKham.getThuocphongkhamNdm());
                        //thuocNoiTru.setThuocnoitruMaPhieuDT
                        thuocNoiTru.setThuocnoitruTienbntra(thuocPKham.getThuocphongkhamTienbntra());
                        //thuocNoiTru.setThuocdongyNoiTru
                        thuocNoiTru.setDmbaithuocMaso(thuocPKham.getDmbaithuocMaso());

                        thuocNoiTru.setDmdoituongMaso(hsba.getDoituongMa());
                        thuocNoiTru.setThuocnoitruDongiatt(thuocPKham.getThuocphongkhamDongiatt());
                        thuocNoiTru.setThuocnoitruThanhtien(thuocPKham.getThuocphongkhamThanhtien());
                        thuocNoiTru.setThuocnoitruMaChidan(thuocPKham.getThuocphongkhamMaChidan());
                        thuocNoiTru.setThuocnoitruTenchidan(thuocPKham.getThuocphongkhamChidan());

                        em.persist(thuocNoiTru);
                        em.remove(thuocPKham);
                    }

                } // for
            } // if (listThuocPhongKham != null && listThuocPhongKham.size() > 0)
            cvnt = "ok";
            System.out.println("--- END chuyenSoLieuVaoNoiTru --- Option 1: " + cvnt);

        } catch (Exception ex) {
            cvnt = "";
            ex.printStackTrace();
            context.setRollbackOnly();
        }

        return cvnt;

    }

    /**
     * @author thanhdo
     * @param thamkham
     * @param tiepdon
     * @param benhnhan
     * @param chuyenVaoNT
     * @return
     */
    public String thamKhamVaXuTri(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String cvntOption) {

        String returnSoBenhAn = "";
        // 20101110 bao.ttc: remove vi ham ben Utils da comment toan bo
        //for thamkham
        //Utils.setInfor(thamkham, getEm());
        //for tiepdon		
        //Utils.setInfor(tiepdon, getEm());
        //for benh nhan
        //Utils.setInfor(benhnhan, getEm());

        
        // 20101110 bao.ttc: tiepdon.setBenhnhanMa(null);
        if (thamkham.getTiepdonMa() != null) {
            getEm().merge(thamkham);
            System.out.println("cap nhat tham kham");
        } else {
            //new
            thamkham.setTiepdonMa(tiepdon);
            getEm().persist(thamkham);
            System.out.println("them moi tham kham");
        }

        getEm().merge(tiepdon);
        getEm().merge(benhnhan);


        //chuyen ban kham
        if (thamkham.getThamkhamChbankham() != null && thamkham.getThamkhamChbankham().getDtdmbankhamMaso() != null) {
            //find tham kham with ban kham (chuyen ban kham) va ma tiep don
            System.out.println("thamkham.getThamkhamChbankham() != null:" + thamkham.getThamkhamChbankham());
            ThamKham thamkhamChuyenBanKham = findByBanKhamMaSoVaMaTiepDon(thamkham.getThamkhamChbankham().getDtdmbankhamMaso(), tiepdon.getTiepdonMa());
            if (thamkhamChuyenBanKham == null) { //not exist

                System.out.println("thamkhamChuyenBanKham:" + thamkhamChuyenBanKham);

                thamkhamChuyenBanKham = new ThamKham();
                thamkhamChuyenBanKham = thamkham;
                thamkhamChuyenBanKham.setThamkhamMa(null);

                //set new data
                thamkhamChuyenBanKham.setThamkhamBankham(thamkham.getThamkhamChbankham());
                thamkhamChuyenBanKham.setThamkhamChbankham(null);
                thamkhamChuyenBanKham.setThamkhamDieutri(null);
                //20101229 bao.ttc: set BS null de lay ten BS mac dinh doi voi tung ban kham
                thamkhamChuyenBanKham.setThamkhamBacsi(null);


                getEm().persist(thamkhamChuyenBanKham);
                System.out.println("luu tru tham kham chuyen ban kham thanh cong:" + thamkhamChuyenBanKham);
            } else {
                //do nothing
                System.out.println("Da co tham kham tai ban kham ma so: " + thamkham.getThamkhamChbankham().getDtdmbankhamMaso());
            }
        }

        // 20110519 bao.ttc: khong can thiet
        //tiepdon.setBenhnhanMa(benhnhan);
        //getEm().merge(tiepdon);
        //System.out.println("cap nhat tiep don");

        if (tiepdon.getTiepdonChkhoa() != null) {
            System.out.println("tiepdon.getTiepdonChkhoa() != null):" + tiepdon.getTiepdonChkhoa());
            System.out.println("thamkham.getThamkhamDieutri(true).getDmdieutriMa(): " + thamkham.getThamkhamDieutri(true).getDmdieutriMa());
            String maDieuTri = thamkham.getThamkhamDieutri(true).getDmdieutriMa().toUpperCase();
            if(maDieuTri != null){
                returnSoBenhAn = vaoNoiTru_ChuyenSoLieu(hsttk, thamkham, tiepdon, benhnhan, chuyenVaoNT, maDieuTri, cvntOption);
            }
        }

        return returnSoBenhAn;
    }

    public String vaoNoiTru_ChuyenSoLieu(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String huongXuLy, String cvntOption) {
        String soVaoVien = "";
        System.out.println("vaoNoiTru_ChuyenSoLieu");

        Hsba hsba = null;
        HsbaKhoa hsbaKhoa = null;
        HsbaChuyenMon hsbaCm = null;
        
        try {

        HsbaFacade hsbaFacade = new HsbaFacade();
        hsbaFacade.setEm(em);
        hsba = hsbaFacade.findByTiepDonMa(tiepdon.getTiepdonMa());

        // 20110914 bao.ttc: Truong hop da lap HSBA Luu, neu bay gio chon Huong xu ly Vao noi tru nen se tao 1 HSBA moi (yeu cau Bug #4007)
        if (hsba != null){
            CauHinhFacade facade = new CauHinhFacade();
            facade.setEm(em);
            CauHinh cauHinhMaDonVi = facade.findByMa("BA_LUU_PREFIX");
            if (cauHinhMaDonVi != null) {
                String ketQua = cauHinhMaDonVi.getChbvGiatri();
                if (hsba.getHsbaSovaovien().startsWith(ketQua)){
                    System.out.println("Da lap HSBA Luu !!! Hien tai chon Huong xu ly Vao noi tru nen se tao 1 HSBA moi !!!");
                    hsba = null; // Gan NULL de tao moi hsba chu khong dung HSBA Cap Cuu Luu
                }
            }
        }
        // END -- 20110914 bao.ttc: Truong hop da lap HSBA Luu, neu bay gio chon Huong xu ly Vao noi tru nen se tao 1 HSBA moi (yeu cau Bug #4007)
        
        //Lay tang default cua tiepdon.getTiepdonChkhoa().getDmkhoaMa()
        DieuTriUtilFacade dtUtil = new DieuTriUtilFacade();
        dtUtil.setEm(em);
        DmTang tangChuyenDen = dtUtil.findByDmTangDefault(tiepdon.getTiepdonChkhoa().getDmkhoaMaso());
        
        if (hsba == null) {
            hsba = new Hsba();
            hsba.setBenhnhanMa(benhnhan);
            hsba.setTiepdonMa(tiepdon.getTiepdonMa());
            //Tho add
            if(huongXuLy.equals("V")){
                hsba.setHsbaIsNoitru(true);
                hsba.setHsbaType("NGOAI");
                hsba.setHsbaSovaovien(Utils.getSoVaoVien(getEm(),2));
            }else if(huongXuLy.equals("N")){
                hsba.setHsbaIsNoitru(false);
                hsba.setHsbaType("DT_NGOAITRU");
                hsba.setHsbaSovaovien(Utils.getSoVaoVien(getEm(),5));
            }
            //End Tho add - 19/7/2011
            hsba.setHsbaNgaygiovaov(new Date());

            // Chuyen cac so lieu tu tham kham vao HSBA
            hsba.setHsbaDonvigoi(tiepdon.getTiepdonDonvigoi());
            hsba.setHsbaMachdoanbd(thamkham.getBenhicd10());
            hsba.setDmptgtnMaso(tiepdon.getDmptgtnMaso());
            hsba.setHsbaRuoubia(tiepdon.getTiepdonRuoubia());
            hsba.setDoituongMa(tiepdon.getDoituongMa());
            hsba.setHsbaBaotin(tiepdon.getTiepdonBaotin()); // 20120220 bao.ttc: them thong tin "Bao tin"

            hsba.setHsbaKhoadangdt(tiepdon.getTiepdonChkhoa());
            hsba.setHsbaKhoadangdtCm(tiepdon.getTiepdonChkhoa()); // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
            hsba.setTangDangdt(tangChuyenDen); // 20120315 bao.ttc: set Tang Dang Dieu Tri
            hsba.setHsbaKhoavaov(tiepdon.getTiepdonChkhoa());

            getEm().persist(hsba);

        } //else {
            // 20111213 bao.ttc: remove vi khong thay doi khoa cua cac HSBA dang ton tai, da co HSBA thi phai thay doi khoa tai form "BN chuyen khoa"
            // --> Khong can thay doi gi

            // hsba.setHsbaKhoadangdt(tiepdon.getTiepdonChkhoa());
            // hsba.setHsbaKhoadangdtCm(tiepdon.getTiepdonChkhoa());
            // hsba.setTangDangdt(tangChuyenDen); // 20120315 bao.ttc: set Tang Dang Dieu Tri
            // em.merge(hsba);
        //}

        soVaoVien = hsba.getHsbaSovaovien();

        if (tiepdon.getDoituongMa() != null && "BH".equals(tiepdon.getDoituongMa(true).getDmdoituongMa()) ) {

            HsbaBhytFacade facade = new HsbaBhytFacade();
            facade.setEm(em);
            List<HsbaBhyt> lstBhyt = facade.findBySoVaoVien(hsba.getHsbaSovaovien());
            if (lstBhyt == null || lstBhyt.size() == 0) {
                HsbaBhyt bhyt = new HsbaBhyt();
                bhyt.setHsbaSovaovien(hsba);
                bhyt.setHsbabhytSothebh(tiepdon.getTiepdonSothebh());
                bhyt.setHsbabhytSothete(tiepdon.getTiepdonSothete());
                bhyt.setHsbabhytNambhyt(tiepdon.getTiepdonNambhyt());
                bhyt.setHsbabhytTylebh(tiepdon.getTiepdonTylebh());
                bhyt.setHsbabhytTuyen(tiepdon.getTiepdonTuyen());
                bhyt.setHsbabhytMakcb(tiepdon.getKcbbhytMa());

                bhyt.setHsbabhytGiatri0(tiepdon.getTiepdonGiatri1());
                bhyt.setHsbabhytGiatri1(tiepdon.getTiepdonGiatri2());
                bhyt.setHsbabhytGiatri2(tiepdon.getTiepdonGiatri3());
                bhyt.setHsbabhytGiatri3(tiepdon.getTiepdonGiatri4());
                bhyt.setHsbabhytMoc1(tiepdon.getTiepdonMoc1());
                bhyt.setHsbabhytMoc2(tiepdon.getTiepdonMoc2());
                bhyt.setHsbabhytMoc3(tiepdon.getTiepdonMoc3());

                bhyt.setHsbabhytKhoibh(tiepdon.getKhoibhytMa());
                bhyt.setHsbabhytTinhbh(tiepdon.getTinhbhytMa());
                bhyt.setHsbabhytCoquanbh(tiepdon.getTiepdonMacoquan()); // 20120220 bao.ttc: them thong tin Co quan

                //
                //  TODO: Cac thong tin khac se chuyen vao sau  giai doan demo
                //
                getEm().persist(bhyt);
                } //else {
                //do nothing vi` chi co' duy nhat 1 HSBABHYT gan lien voi 1 hsba
                // bao.ttc: xem xet truong hop chinh thong tin ?
                //}
        }


        // 20101103 bao.ttc: Them HSBA Chuyen mon:
        HsbaChuyenMonFacade hsbacmFacade = new HsbaChuyenMonFacade();
        hsbacmFacade.setEm(em);
        hsbaCm = hsbacmFacade.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), tiepdon.getTiepdonChkhoa().getDmkhoaMa());
        if (hsbaCm == null) {
            hsbaCm = new HsbaChuyenMon();
            hsbaCm.setHsbaSovaovien(hsba);
            hsbaCm.setKhoaMa(tiepdon.getTiepdonChkhoa());
            hsbaCm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
            hsbaCm.setHsbacmlan(1);

            // 201110719 bao.ttc: dua so giuong cua BN o tiepdon Cap cuu vao hsbaCM khi lap BA Luu
            if (tiepdon.getTiepdonGiuong() != null && !tiepdon.getTiepdonGiuong().equals("")) {
                hsbaCm.setHsbacmSogiuong(tiepdon.getTiepdonGiuong());
            }

            em.persist(hsbaCm);
        } else {
            Integer soLan = hsbaCm.getHsbacmlan();
            if (soLan == null)
                soLan = 1; // phai la` 1 vi hsbacm da ton tai roi
            else
                soLan = soLan + 1;  // day la lan chuyen khac

            //hsbaCm.setHsbaSovaovien(hsba);
            //hsbaCm.setKhoaMa(tiepdon.getTiepdonChkhoa());
            hsbaCm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
            hsbaCm.setHsbacmlan(soLan); // so lan da duoc tang len

            em.merge(hsbaCm);
        }
        // 20101103 bao.ttc: Them HSBA Chuyen mon -- END

        HsbaKhoaFacade hsbaKhoaFacade = new HsbaKhoaFacade();
        hsbaKhoaFacade.setEm(em);
        
        // 20120315 bao.ttc: tim hsbaKhoa theo: Sovaovien - Khoa - Tang de xem xet co tao moi hay khong
        hsbaKhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), tiepdon.getTiepdonChkhoa().getDmkhoaMa(), tangChuyenDen.getDmtangMaso());
        if (hsbaKhoa == null) {
            hsbaKhoa = new HsbaKhoa();
            hsbaKhoa.setHsbaSovaovien(hsba);
            hsbaKhoa.setKhoaMa(tiepdon.getTiepdonChkhoa());
            hsbaKhoa.setHsbakhoaLan(1);
            hsbaKhoa.setDmtangMaso(tangChuyenDen);
            em.persist(hsbaKhoa);
        } else {
            Integer soLan = hsbaKhoa.getHsbakhoaLan();
            if (soLan == null)
                soLan = 1; // phai la` 1 vi hsbaKhoa da ton tai roi
            else
                soLan = soLan + 1;  // day la lan chuyen khac
            //hsbaKhoa.setHsbaSovaovien(hsba);
            //hsbaKhoa.setKhoaMa(tiepdon.getTiepdonChkhoa());
            hsbaKhoa.setHsbakhoaLan(soLan);
            //hsbaKhoa.setDmtangMaso(tangChuyenDen);
            em.merge(hsbaKhoa);
        }

        if (cvntOption.equals("0")){
                // Option 0: Chuyen so lieu (tien chua thanh toan) vao noi tru de thanh toan chung 1 lan khi thanh toan ra vien
        if (chuyenVaoNT != null && chuyenVaoNT.booleanValue()) {

            ChuyenVaoNoiTru cvnt = null;
            ChuyenVaoNoiTruFacade cvntFacace = new ChuyenVaoNoiTruFacade();
            cvntFacace.setEm(em);
            cvnt = cvntFacace.findByMaTiepDon(tiepdon.getTiepdonMa());

            if (cvnt == null) {
                cvnt = new ChuyenVaoNoiTru();
                cvnt.setTiepDon(tiepdon);
                cvnt.setCvntTIEPDONMA(tiepdon.getTiepdonMa());
                cvnt.setCvntSOVAOVIEN(hsba);
                cvnt.setCvntNGAYGIOCHUYEN(new Date());
                cvnt.setCvntDOITUONGMA(tiepdon.getDoituongMa());
                cvnt.setCvntBANKHAM(tiepdon.getTiepdonBankham());
                cvnt.setCvntKHOA(tiepdon.getTiepdonChkhoa());
                cvnt.setCvntMACHDOANBD(tiepdon.getTiepdonMachdoanbd());
                cvnt.setCvntNHANVIENMA(thamkham.getThamkhamBacsi());
                cvnt.setCvntTIENCHUATT(hsttk.getHsthtoankThtoan());
                // can luu lai: Tam ung - hoan ung de tinh tien

                getEm().persist(cvnt);

            } else {
                System.out.println("BN da chuyen vao noi tru");
                soVaoVien = cvnt.getCvntSOVAOVIEN().getHsbaSovaovien();
            }

            // Doi status cua tiepdon thanh "CSL", qua do khong cho thay doi thamkham, clsk,
            // tpk, thanh toan ngoai tru (vi se set tat ca khi thanh toan noi tru)
            tiepdon.setTiepdonStatus("CSL");
            em.merge(tiepdon);

            // Tam ung cap cuu da tinh trong HSTTK, set trang thai de khong tinh nhieu lan (bang tam_ung_kham)
            Query q = em.createQuery("Select c from TamUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon AND c.tamungkhamStatus Is Null");
            q.setParameter("maTiepdon", tiepdon.getTiepdonMa());
            List<TamUngKham> lstTamUngKham =  q.getResultList();
            if (lstTamUngKham.size() > 0) {

                for(TamUngKham tuk : lstTamUngKham) {
                    tuk.setTamungkhamStatus("CSL");
                    em.merge(tuk);
                }
            }

            // Hoan ung cap cuu da tinh trong HSTTK, set trang thai de khong tinh nhieu lan (bang hoan_ung_kham)
            Query qr = em.createQuery("Select c from HoanUngKham c Where c.tiepdonMa.tiepdonMa = :maTiepdon AND c.hoanungkhamStatus Is Null");
            qr.setParameter("maTiepdon", tiepdon.getTiepdonMa());
            List<HoanUngKham> lstHoanUngKham =  qr.getResultList();
            if (lstHoanUngKham.size() > 0) {

                for(HoanUngKham huk : lstHoanUngKham) {
                    huk.setHoanungkhamStatus("CSL");
                    em.merge(huk);
                }
            }
        }       // END Option 0: Chuyen so lieu (tien chua thanh toan) vao noi tru de thanh toan chung 1 lan khi thanh toan ra vien

                // Option 1:  Toan bo chi phi CLS va Thuoc se thuoc phieu thanh toan Noi tru
        } else if ( cvntOption.equals("1") ){

            if (chuyenVaoNT != null && chuyenVaoNT.booleanValue()) {
                chuyenSoLieuVaoNoiTru(hsba, hsbaKhoa, tiepdon.getTiepdonChkhoa(), tiepdon.getTiepdonMa());
                // Doi status cua tiepdon thanh "CSL", qua do khong cho thay doi thamkham, clsk,
                // tpk, thanh toan ngoai tru (vi se set tat ca khi thanh toan noi tru)
                tiepdon.setTiepdonStatus("CSL");
                em.merge(tiepdon);
            }
        }       // END Option 1:  Toan bo chi phi CLS va Thuoc se thuoc phieu thanh toan Noi tru

        } catch (Exception ex) {
            soVaoVien = "";
            ex.printStackTrace();
            context.setRollbackOnly();
        }

        return soVaoVien;
    }

    public List<ThamKham> findAll() {
        return getEm().createQuery("select object(o) from ThamKham as o").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ThuocPhongKhamFacadeLocal getThuocPhongKhamFacade() {
        return thuocPhongKhamFacade;
    }

    public void setThuocPhongKhamFacade(ThuocPhongKhamFacadeLocal thuocPhongKhamFacade) {
        this.thuocPhongKhamFacade = thuocPhongKhamFacade;
    }

    public ThamKham getLastThamKhamWithSoTheBHYTAndBanKham(String tiepdonSothebh, Integer dtdmbankhamMaso) {
        String sql = "select object(t) From ThamKham as t where t.tiepdonMa.tiepdonSothebh = :tiepdonSothebh and t.thamkhamBankham.dtdmbankhamMaso = :dtdmbankhamMaso order by t.thamkhamMa DESC";
        Query q = getEm().createQuery(sql);
        q.setParameter("tiepdonSothebh", tiepdonSothebh);
        q.setParameter("dtdmbankhamMaso", dtdmbankhamMaso);
        List lstTD = q.getResultList();
        if (lstTD != null && lstTD.size() > 1) {
            return (ThamKham) lstTD.get(1);
        } else {
            return null;
        }
    }

    /**
     * 20110523 bao.ttc:
     * Tao hsba, hsba_khoa, hsba_cm, hsba_bhyt tu thong tin tiepdon va tham kham o ngoai tru
     * Cac BA la tam thoi nen :
     * HsbaIsNoitru = 0
     * hsbaType: NGOAI, YHCT, BA_LUU
     *
     */
    public String vaoNoiTru_Temp(ThamKham thamkham, TiepDon tiepdon, String hsbaType, DmKhoa khoaNoitru) {
        String soVaoVien = "";
        System.out.println("vaoNoiTru_Temp");

        getEm().merge(tiepdon);
        getEm().merge(thamkham);

        Hsba hsba = null;
        HsbaKhoa hsbaKhoa = null;
        HsbaChuyenMon hsbaCm = null;
        
        //Lay tang default cua khoaNoiTru.getDmkhoaMa()
        DieuTriUtilFacade dtUtil = new DieuTriUtilFacade();
        dtUtil.setEm(em);
        DmTang tangChuyenDen = dtUtil.findByDmTangDefault(khoaNoitru.getDmkhoaMaso());
        
        HsbaFacade hsbaFacade = new HsbaFacade();
        hsbaFacade.setEm(em);
        hsba = hsbaFacade.findByTiepDonMa(tiepdon.getTiepdonMa());
        if (hsba == null) {
            hsba = new Hsba();
            hsba.setHsbaSovaovien(Utils.getSoVaoVien(getEm(), 4));//loaiMa = 4 la ho so cap cuu luu
            hsba.setBenhnhanMa(tiepdon.getBenhnhanMa());
            hsba.setTiepdonMa(tiepdon.getTiepdonMa());
            hsba.setHsbaIsNoitru(false); // BN nay chi tao hsba tam thoi, khong xuat hien trong cac bao cao, so lieu noi tru
            hsba.setHsbaType(hsbaType.toUpperCase());

            hsba.setHsbaNgaygiovaov(new Date());

            // Chuyen cac so lieu tu tham kham vao HSBA
            hsba.setHsbaDonvigoi(tiepdon.getTiepdonDonvigoi());
            hsba.setHsbaMachdoanbd(thamkham.getBenhicd10());
            hsba.setDmptgtnMaso(tiepdon.getDmptgtnMaso());
            hsba.setHsbaRuoubia(tiepdon.getTiepdonRuoubia());
            hsba.setDoituongMa(tiepdon.getDoituongMa());
            hsba.setHsbaBaotin(tiepdon.getTiepdonBaotin()); // 20120220 bao.ttc: them thong tin "Bao tin"

            hsba.setHsbaKhoadangdt(khoaNoitru);
            hsba.setHsbaKhoadangdtCm(khoaNoitru); // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
            hsba.setTangDangdt(tangChuyenDen); // 20120315 bao.ttc: set Tang Dang Dieu Tri
            hsba.setHsbaKhoavaov(khoaNoitru);

            getEm().persist(hsba);

        } else {
            hsba.setHsbaKhoadangdt(khoaNoitru);
            hsba.setHsbaKhoadangdtCm(khoaNoitru); // 20110708 bao.ttc: Khoa dang dieu tri dung cho HSBA Chuyen mon, phan biet voi khoa dang DT that su
            hsba.setTangDangdt(tangChuyenDen); // 20120315 bao.ttc: set Tang Dang Dieu Tri
            hsba.setHsbaNgaygiocn(new Date());
            
            em.merge(hsba);
        }

        soVaoVien = hsba.getHsbaSovaovien();

        if (tiepdon.getDoituongMa() != null && "BH".equals(tiepdon.getDoituongMa(true).getDmdoituongMa()) ) {

            HsbaBhytFacade facade = new HsbaBhytFacade();
            facade.setEm(em);
            List<HsbaBhyt> lstBhyt = facade.findBySoVaoVien(hsba.getHsbaSovaovien());
            if (lstBhyt == null || lstBhyt.size() == 0) {
                HsbaBhyt bhyt = new HsbaBhyt();
                bhyt.setHsbaSovaovien(hsba);
                bhyt.setHsbabhytSothebh(tiepdon.getTiepdonSothebh());
                bhyt.setHsbabhytSothete(tiepdon.getTiepdonSothete());
                bhyt.setHsbabhytNambhyt(tiepdon.getTiepdonNambhyt());
                bhyt.setHsbabhytTylebh(tiepdon.getTiepdonTylebh());
                bhyt.setHsbabhytTuyen(tiepdon.getTiepdonTuyen());
                bhyt.setHsbabhytMakcb(tiepdon.getKcbbhytMa());

                bhyt.setHsbabhytGiatri0(tiepdon.getTiepdonGiatri1());
                bhyt.setHsbabhytGiatri1(tiepdon.getTiepdonGiatri2());
                bhyt.setHsbabhytGiatri2(tiepdon.getTiepdonGiatri3());
                bhyt.setHsbabhytGiatri3(tiepdon.getTiepdonGiatri4());
                bhyt.setHsbabhytMoc1(tiepdon.getTiepdonMoc1());
                bhyt.setHsbabhytMoc2(tiepdon.getTiepdonMoc2());
                bhyt.setHsbabhytMoc3(tiepdon.getTiepdonMoc3());

                bhyt.setHsbabhytKhoibh(tiepdon.getKhoibhytMa());
                bhyt.setHsbabhytTinhbh(tiepdon.getTinhbhytMa());
                bhyt.setHsbabhytCoquanbh(tiepdon.getTiepdonMacoquan()); // 20120220 bao.ttc: them thong tin Co quan

                //
                //  TODO: Cac thong tin khac se chuyen vao sau  giai doan demo
                //
                getEm().persist(bhyt);
            } else {
                //do nothing vi` chi co' duy nhat 1 HSBABHYT gan lien voi 1 hsba
                // bao.ttc: xem xet truong hop chinh thong tin ?
            }
        }


        // 20101103 bao.ttc: Them HSBA Chuyen mon:
        HsbaChuyenMonFacade hsbacmFacade = new HsbaChuyenMonFacade();
        hsbacmFacade.setEm(em);
        hsbaCm = hsbacmFacade.findBySoVaoVien_MaKhoa(hsba.getHsbaSovaovien(), khoaNoitru.getDmkhoaMa());
        if (hsbaCm == null) {
            hsbaCm = new HsbaChuyenMon();
            hsbaCm.setHsbaSovaovien(hsba);
            hsbaCm.setKhoaMa(khoaNoitru);
            hsbaCm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
            hsbaCm.setHsbacmlan(1);

            // 201110719 bao.ttc: dua so giuong cua BN o tiepdon Cap cuu vao hsbaCM khi lap BA Luu
            if (tiepdon.getTiepdonGiuong() != null && !tiepdon.getTiepdonGiuong().equals("")) {
                hsbaCm.setHsbacmSogiuong(tiepdon.getTiepdonGiuong());
            }

            em.persist(hsbaCm);
        } else {
            Integer soLan = hsbaCm.getHsbacmlan();
            if (soLan == null)
                soLan = 1; // phai la` 1 vi hsbacm da ton tai roi
            else
                soLan = soLan + 1;  // day la lan chuyen khac
            hsbaCm.setHsbacmNgaygiovaok(hsba.getHsbaNgaygiovaov());
            hsbaCm.setHsbacmlan(soLan); // so lan da duoc tang len
            em.merge(hsbaCm);
        }
        // 20101103 bao.ttc: Them HSBA Chuyen mon -- END

        HsbaKhoaFacade hsbaKhoaFacade = new HsbaKhoaFacade();
        hsbaKhoaFacade.setEm(em);
        
        // 20120315 bao.ttc: tim hsbaKhoa theo: Sovaovien - Khoa - Tang de xem xet co tao moi hay khong
        hsbaKhoa = hsbaKhoaFacade.findBySoVaoVienAndKhoaMaAndTang(hsba.getHsbaSovaovien(), khoaNoitru.getDmkhoaMa(), tangChuyenDen.getDmtangMaso());
        if (hsbaKhoa == null) {
            hsbaKhoa = new HsbaKhoa();
            hsbaKhoa.setHsbaSovaovien(hsba);
            hsbaKhoa.setKhoaMa(khoaNoitru);
            hsbaKhoa.setHsbakhoaLan(1);
            hsbaKhoa.setDmtangMaso(tangChuyenDen);
            em.persist(hsbaKhoa);
        } else {
            Integer soLan = hsbaKhoa.getHsbakhoaLan();
            if (soLan == null)
                soLan = 1; // phai la` 1 vi hsbaKhoa da ton tai roi
            else
                soLan = soLan + 1;  // day la lan chuyen khac
            hsbaKhoa.setHsbakhoaLan(soLan);
            hsbaKhoa.setDmtangMaso(tangChuyenDen);
            em.merge(hsbaKhoa);
        }

        return soVaoVien;
    }

    public List<ClsKham> getListClsBanKhamTruoc(String tiepdonMa, Integer thamkhamMa){
        String sSQL = "select object(o) from ClsKham o where o.clskhamThamkham.tiepdonMa.tiepdonMa = :tiepdonMa and o.clskhamThamkham.thamkhamMa != :thamkhamMa order by o.clskhamThamkham.thamkhamBankham.dtdmbankhamMaso";
        Query q = getEm().createQuery(sSQL);
        q.setParameter("thamkhamMa", thamkhamMa);
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }

    public List<ThuocPhongKham> getListThuocBanKhamTruoc(String tiepdonMa, Integer thamkhamMa){
        String sSQL = "select object(o) from ThuocPhongKham o where o.thuocphongkhamThamkham.tiepdonMa.tiepdonMa = :tiepdonMa and o.thuocphongkhamThamkham.thamkhamMa != :thamkhamMa order by o.thuocphongkhamThamkham.thamkhamBankham.dtdmbankhamMaso";
        Query q = getEm().createQuery(sSQL);
        q.setParameter("thamkhamMa", thamkhamMa);
        q.setParameter("tiepdonMa", tiepdonMa);
        return q.getResultList();
    }
}
