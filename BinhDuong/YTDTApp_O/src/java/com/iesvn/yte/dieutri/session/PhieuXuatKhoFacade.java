/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.CtXuatKho;
import com.iesvn.yte.dieutri.entity.CtXuatKhoTmp;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmDonViTinh;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
public class PhieuXuatKhoFacade implements PhieuXuatKhoFacadeLocal, PhieuXuatKhoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private ThuocNoiTruFacadeLocal thuocNoiTruFacade;
    @EJB
    private PhieuDuTruFacadeLocal phieuDuTrufacade;
    @EJB
    private CtPhieuDtFacadeLocal ctPhieuDtFacade;
    @EJB
    private HsThtoanFacadeLocal hsThToanFacade;
    @Resource
    private SessionContext context;

    public void create(PhieuXuatKho phieuXuatKho) {
        getEm().persist(phieuXuatKho);
    }

    public void edit(PhieuXuatKho phieuXuatKho) {
        getEm().merge(phieuXuatKho);
    }

    public void remove(PhieuXuatKho phieuXuatKho) {
        getEm().remove(getEm().merge(phieuXuatKho));
    }

    public PhieuXuatKho find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.PhieuXuatKho.class, id);
    }

    public List<PhieuXuatKho> findAll() {
        return getEm().createQuery("select object(o) from PhieuXuatKho as o").getResultList();
    }

    /**
     * Tim kiem phieu xuat theo ma phieu
     * @param maPhieu
     * @return
     */
    public PhieuXuatKho findByPhieuxuatkhoMa(String maPhieu) {
        System.out.println("-----findByPhieuxuatkhoMa()-----");
        System.out.println("-----ma phieu (input): " + maPhieu);
        maPhieu = Utils.formatMaPhieu(maPhieu);
        System.out.println("-----ma phieu (format): " + maPhieu);
        try {
            Object obj = getEm().find(PhieuXuatKho.class, maPhieu);
            if (obj != null) {
                return (PhieuXuatKho) obj;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public PhieuXuatKho findPhieuXuatKhoByKhoXuat(String maPhieu, Integer maKhoa) {
        System.out.print("-----Tim theo maPhieu: " + maPhieu + " maKhoa xuat: " + maKhoa);
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuXuatKho c ";
        sQuery += " WHERE  c.phieuxuatkhoMa like :maPhieu AND c.dmkhoaXuat.dmkhoaMaso = :maKhoa";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("maKhoa", maKhoa);
        List<PhieuXuatKho> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public PhieuXuatKho findPhieuXuatKhoByKhoaNhan(String maPhieu, Integer maKhoa) {
        System.out.print("-----Tim theo maPhieu: " + maPhieu + " maKhoa: " + maKhoa);
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuXuatKho c ";
        sQuery += " WHERE  c.phieuxuatkhoMa like :maPhieu AND c.dmkhoaNhan.dmkhoaMaso = :maKhoa";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("maKhoa", maKhoa);
        List<PhieuXuatKho> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public PhieuXuatKho findByPhieuDuTru(String maPhieuDT) {
        maPhieuDT = Utils.formatMaPhieu(maPhieuDT);
        System.out.println("-----ma phieu (format): " + maPhieuDT);
        try {
            Query query = getEm().createQuery("select object(o) from PhieuXuatKho  as o where o.phieudtMa.phieudtMa like :maPhieuDT");
            query.setParameter("maPhieuDT", maPhieuDT);
            List<PhieuXuatKho> listPxk = query.getResultList();
            if (listPxk != null) {
                return (PhieuXuatKho) listPxk.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public PhieuXuatKho findByPhieuDuTruAndKhoXuat(String maPhieuDT, Integer dmKhoXuatMaso) {
        maPhieuDT = Utils.formatMaPhieu(maPhieuDT);
        System.out.println("-----ma phieu (format): " + maPhieuDT);
        try {
            Query query = getEm().createQuery("select object(o) from PhieuXuatKho o where o.phieudtMa.phieudtMa like :maPhieuDT and o.dmkhoaXuat.dmkhoaMaso = :dmkhoaMaso");
            query.setParameter("maPhieuDT", maPhieuDT);
            query.setParameter("dmkhoaMaso", dmKhoXuatMaso);
            List<PhieuXuatKho> listPxk = query.getResultList();
            if (listPxk != null) {
                return (PhieuXuatKho) listPxk.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    /**
     * Tim phieu xuat theo ma phieu, ngay lap, loai phieu, nguoi lap, nguoi nhap, kho nhan, kho xuat
     * @param pnkMa
     * @param ngayLap
     * @param loaiPhieuMa
     * @param maNguoiLap
     * @param maNguoiNhap
     * @param maKhoNhan
     * @param maKhoXuat
     * @return ArrayList
     */
    public List<PhieuXuatKho> find(String pxkMa, Date ngayLap, Integer loaiPhieuMaSo, Integer maNguoiLap, Integer maNguoiNhap, Integer maKhoNhan, Integer maKhoXuat) {
        pxkMa = pxkMa.trim();
        String sql = "";
        sql = "SELECT c FROM PhieuXuatKho c WHERE 1 = 1 ";
        if(!pxkMa.equals("") ){
            pxkMa = Utils.formatMaPhieu(pxkMa);
            sql += "AND c.phieuxuatkhoMa like :phieuxuatkhoMa ";
        }
        if(ngayLap !=null)
            sql += "AND c.phieuxuatkhoNgaylap = :phieuxuatkhoNgaylap ";
        if(loaiPhieuMaSo>0)
            sql += "AND c.dmloaithuocMaso.dmloaithuocMaso = :dmloaithuocMaso ";
        if(maNguoiLap>0)
            sql += "AND c.dtdmnhanvienPhat.dtdmnhanvienMaso = :dtdmnhanvienLapMaso ";
        if(maNguoiNhap>0)
            sql += "AND c.dtdmnhanvienCn.dtdmnhanvienMaso = :dtdmnhanvienNhapMaso ";
        if(maKhoNhan>0)
            sql += "AND c.dmkhoaNhan.dmkhoaMaso = :dmkhoaNhanMaso ";

        sql += " AND c.dmkhoaXuat.dmkhoaMaso = :dmkhoaXuatMaso order by c.phieuxuatkhoNgaygiocn desc";
        try {
            Query q = getEm().createQuery(sql);
            System.out.println("Query: "+sql);
            if(!pxkMa.equals("") )
                q.setParameter("phieuxuatkhoMa", pxkMa);
            if(ngayLap !=null)
                q.setParameter("phieuxuatkhoNgaylap", ngayLap);
            if(loaiPhieuMaSo>0)
                q.setParameter("dmloaithuocMaso", loaiPhieuMaSo);
            if(maNguoiLap>0)
                q.setParameter("dtdmnhanvienLapMaso", maNguoiLap);
            if(maNguoiNhap>0)
                q.setParameter("dtdmnhanvienNhapMaso", maNguoiNhap);
            if(maKhoNhan>0)
                q.setParameter("dmkhoaNhanMaso", maKhoNhan);
            q.setParameter("dmkhoaXuatMaso", maKhoXuat);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    /**
     * Cap nhat phieu xuat va chi tiet xuat
     * @param pxk
     * @param listCtXuatKho
     * @return
     */
    public String createPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat) {

       // TonKhoFacade tkFacade = new TonKhoFacade();
      //  tkFacade.setEm(getEm());

        System.out.println("-----Begin: createPhieuXuat()-----");
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(getEm());
        String result = "";
        String pxkMa = ""; //+ pxk.getPhieuxuatkhoMa();
        //kiem tra so luong xuat <= so luong ton? neu thoa thi duoc phep insert, khong thoa thi return ve loi
        if (listCtXuatKho != null && listCtXuatKho.size() > 0) {
            for (CtXuatKho ctXK : listCtXuatKho) {
                String maLK = ctXK.getCtxuatkhoMalk();
                TonKho tonKho = tkFacade.getTonKhoHienTai(maLK, pxk.getDmkhoaXuat().getDmkhoaMaso());
                Double soluongXuat = ctXK.getCtxuatkhoSoluong();
                Double soluongTonHT = tonKho.getTonkhoTon();
                if(soluongXuat > soluongTonHT){
                    return "Soluongxuatkhonghople";
                }
            }
        }

        if (pxkMa.equals("") || pxkMa.equals("null")) {
            // them moi phieu xuat
            System.out.println("-----Insert phieu xuat");
            pxkMa = Utils.maPhieuXuat(getEm());
            pxk.setPhieuxuatkhoMa(pxkMa);
            getEm().persist(pxk);
        }

        for (int i = 0; i < listCtXuatKho.size(); i++) {
            System.out.println("-----Insert ct xuat");
            CtXuatKho ctx = listCtXuatKho.get(i);
            TonKho tkNhan = listTkNhan.get(i);
            TonKho tkXuat = listTkXuat.get(i);
            tkNhan.setDmkhoaMaso(pxk.getDmkhoaNhan());
            tkXuat.setDmkhoaMaso(pxk.getDmkhoaXuat());

            if (tkFacade.insertTonKho(tkNhan) && tkFacade.insertTonKho(tkXuat)) {
                ctx.setPhieuxuatkhoMa(pxk);
                ctx.setTonKhoMa(tkXuat.getTonkhoMa());
                getEm().persist(ctx);
                result = pxk.getPhieuxuatkhoMa();
            } else {
                result = "";
            }

        }
        System.out.println("-----End: createPhieuXuat()-----result "+ result);
        return result;
    }

    public List<ThuocNoiTru> findByPhieuDuTruMaAndMaLK(String phieudtMa, String maLK) {

        System.out.print("phieuDuTruMa:" + phieudtMa);
        phieudtMa = Utils.formatMaPhieu(phieudtMa);
        return getEm().createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruMalk =:thuocnoitruMalk and o.thuocnoitruMaPhieuDT like :thuocnoitruMaPhieuDT").setParameter("thuocnoitruMalk", maLK).setParameter("thuocnoitruMaPhieuDT", phieudtMa).getResultList();


    }

    //Tho update again
    private String kiemTraTruocKhiXuatHangTheoPhieuDuTru(List<CtXuatKho> listCTX, Integer khoMaso, String priority) {
        String ketqua = "";
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(getEm());
        if (listCTX != null && listCTX.size() > 0) {
            for (CtXuatKho ctXK : listCTX) {
                String maLK = ctXK.getCtxuatkhoMalk();
                Integer thuocMaso = ctXK.getDmthuocMaso(true).getDmthuocMaso();
//                System.out.println("Ma LK: "+maLK);
//                System.out.println("Kho ma: "+khoMaso);
//                System.out.println("thuocMaso: "+thuocMaso);
//                System.out.println("priority: "+priority);
                List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, khoMaso, priority);
                TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, khoMaso);
                Double soluongCanXuat = ctXK.getCtxuatkhoSoluong();
                Double soluongTonLoHienTai = 0.0;
                if(tonLoHienTai != null){
                    soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                }
//                System.out.println("soluongCanXuat: "+soluongCanXuat);
//                System.out.println("soluongTonLoHienTai: "+soluongTonLoHienTai);
                //Lay don vi tinh ten de hien thi len cau thong bao neu het thuoc
                String donVi = "";
                if (ctXK.getDmthuocMaso().getDmdonvitinhMaso() != null) {
                    DieuTriUtilFacade dieuTriUtilFacade = new DieuTriUtilFacade();
                    dieuTriUtilFacade.setEm(getEm());
                    Object obj = dieuTriUtilFacade.findByMaSo(ctXK.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
                    if (obj != null) {
                        DmDonViTinh dmDonViTinh = (DmDonViTinh) obj;
                        donVi = dmDonViTinh.getDmdonvitinhTen();
                    }
                }

                boolean isSLTonDuCap = false;
                if(soluongCanXuat > soluongTonLoHienTai){
                    if(listTonKhoHT != null){
                        for(TonKho tonkho:listTonKhoHT){
                            if(soluongCanXuat <= tonkho.getTonkhoTon()){
                                isSLTonDuCap = true;
                                break;
                            }
                        }
                    }
                }else{
                    isSLTonDuCap = true;
                }

                if(!isSLTonDuCap){
                    String temp = "Mặt hàng " + ctXK.getDmthuocMaso().getDmthuocMa() + " thiếu " + (soluongCanXuat.doubleValue() - soluongTonLoHienTai.doubleValue()) + " " + donVi;
                    ketqua += "." + temp;
                }
            }
        }
        return ketqua;
    }

    //
    //  Neu  TNT co' : status = 2 , phieu du tru ma = @maphieudutru  ->return true
    // 
    //
    public boolean daXuatThuocTheoPhieuDT(String maPhieuDuTru) {
        boolean ketqua = false;

        maPhieuDuTru = Utils.formatMaPhieu(maPhieuDuTru);

        Query q = getEm().createQuery("select object(o) from ThuocNoiTru as o Where o.thuocnoitruStatus = '2' and o.thuocnoitruMaPhieuDT like :thuocnoitruMaPhieuDT");
        q.setParameter("thuocnoitruMaPhieuDT", maPhieuDuTru);
        List<ThuocNoiTru> list = q.getResultList();
        if (list != null && list.size() > 0) {
            ketqua = true;
        }

        // truong hop da xuat  va` da tra lai  (khi tra lai thuoc thi` ta xoa ma phieu du tru trong tnt)
        if (ketqua == false) {
            q = getEm().createQuery("select object(o) from ThuocNoiTru as o Where  o.thuocnoitruMaPhieuDT like :thuocnoitruMaPhieuDT");
            q.setParameter("thuocnoitruMaPhieuDT", maPhieuDuTru);
            list = q.getResultList();
            if (list == null || list.size() == 0) {
                ketqua = true;
            }

        }
        return ketqua;
    }

    public String XuatPhieuDuTru(List<CtXuatKho> listCTX, PhieuXuatKho pxk, String priority) throws Exception {
        String result = "";
        TonKhoFacade tonKhoFacade = new TonKhoFacade();
        tonKhoFacade.setEm(getEm());
        ThuocNoiTruFacade thuocNTFacade = new ThuocNoiTruFacade();
        thuocNTFacade.setEm(getEm());

        PhieuDuTru phieuDuTru = phieuDuTrufacade.find(pxk.getPhieudtMa().getPhieudtMa());
        Utils.setInfor(phieuDuTru, getEm());

        CtPhieuDtFacade ctPDTFacade = new CtPhieuDtFacade();
        ctPDTFacade.setEm(getEm());

        ThuocNoiTruFacade tntFacade = new ThuocNoiTruFacade();
        tntFacade.setEm(getEm());

        TonKhoFacade facadeTK = new TonKhoFacade();
        facadeTK.setEm(getEm());

        List<CtPhieuDt> listCtPDT = ctPDTFacade.findByPhieuDuTruMa(phieuDuTru.getPhieudtMa());
        Integer dmKhoMaso = phieuDuTru.getPhieudtMakho(true).getDmkhoaMaso();
        //get danh sach thuoc noi tru tuong ung voi phieu du tru
        List<ThuocNoiTru> listTNT = thuocNTFacade.findTNTByMaPDT(phieuDuTru.getDmkhoaMaso().getDmkhoaMaso(), phieuDuTru.getPhieudtMa());

        String ketquaKiemTra = kiemTraTruocKhiXuatHangTheoPhieuDuTru(listCTX, phieuDuTru.getPhieudtMakho().getDmkhoaMaso(), priority);
        if (!ketquaKiemTra.equals("")) {
            return ketquaKiemTra;
        }

        /*Thay doi dong listCTPhieuDuTru neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
         * Thay doi lo thuoc cho ca thuoc noi tru, listCTPhieuDuTru
         */
        int vitriThaythe = -1;
        int i = 0;
        for(CtXuatKho ctXuatKho : listCTX){
            CtXuatKho ctXuatKhoTemp = new CtXuatKho();
            ctXuatKhoTemp = (CtXuatKho)BeanUtils.cloneBean(ctXuatKho);
            if (ctXuatKho.getPhieuxuatkhoMa(true).getPhieuxuatkhoMa() == null) {
                Double soluongCanXuat = ctXuatKho.getCtxuatkhoSoluong();
                String maLK = ctXuatKho.getCtxuatkhoMalk();
                Integer thuocMaso = ctXuatKho.getDmthuocMaso(true).getDmthuocMaso();

                List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, dmKhoMaso, priority);
                TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, dmKhoMaso);
                Double soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                TonKho tonkhoThayThe = new TonKho();
                if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                    for(TonKho tonkho:listTonKhoHT){
                        if(soluongCanXuat <= tonkho.getTonkhoTon()){
                            tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                            vitriThaythe = i;
                            break;
                        }
                    }
                    if(vitriThaythe > -1){
                        ctXuatKhoTemp.setCtxuatkhoMalk(tonkhoThayThe.getTonkhoMalienket());
                        ctXuatKhoTemp.setCtxuatkhoDongia(tonkhoThayThe.getTonkhoDongia());
                        ctXuatKhoTemp.setCtxuatkhoLo(tonkhoThayThe.getTonkhoLo());
                        ctXuatKhoTemp.setCtxuatkhoNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                        ctXuatKhoTemp.setCtxuatkhoNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                        ctXuatKhoTemp.setCtxuatkhoThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                        ctXuatKhoTemp.setCtxuatkhoNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                        ctXuatKhoTemp.setCtxuatkhoSodangky(tonkhoThayThe.getTonkhoSodangky());
                        ctXuatKhoTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                        ctXuatKhoTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                        ctXuatKhoTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                        ctXuatKhoTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                        listCTX.set(vitriThaythe, ctXuatKhoTemp);

                        //Thay the maLK moi, gia thuoc moi cho chi tiet phieu du tru
                        int j = 0;
                        for(CtPhieuDt ctPDT:listCtPDT){
                            CtPhieuDt ctPDTTemp = (CtPhieuDt)BeanUtils.cloneBean(ctPDT);
                            if(ctPDT.getCtdtMalk().equals(maLK)){
                                ctPDTTemp.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                                ctPDTTemp.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                                ctPDTTemp.setCtdtLo(tonkhoThayThe.getTonkhoLo());
                                ctPDTTemp.setCtdtNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                                ctPDTTemp.setCtdtNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                                ctPDTTemp.setCtdtThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                                ctPDTTemp.setCtdtNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                                ctPDTTemp.setCtdtSodangky(tonkhoThayThe.getTonkhoSodangky());
                                ctPDTTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                                ctPDTTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                                ctPDTTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                                ctPDTTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                                listCtPDT.set(j, ctPDTTemp);
                                break;
                            }
                            j++;
                        }

                        //thay the maLK cua cac dong trong listTNT thanh maLKThayThe voi don gia thay the di kem neu don gia > 0 vi don gia dang = 0 la dung cho doi tuong mien phi
                        j = 0;
                        for(ThuocNoiTru tnt:listTNT){
                            ThuocNoiTru tntTemp = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
                            if(tnt.getThuocnoitruMalk().equals(maLK)){
                                tntTemp.setThuocnoitruMalk(tonkhoThayThe.getTonkhoMalienket());
                                tntTemp.setThuocnoitruDongia(tonkhoThayThe.getTonkhoDongia());
                                tntTemp.setThuocnoitruNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                                tntTemp.setThuocnoitruNamhd(tonkhoThayThe.getTonkhoNamhandung());
                                tntTemp.setThuocnoitruThanghd(tonkhoThayThe.getTonkhoThanghandung());
                                tntTemp.setThuocnoitruNgayhd(tonkhoThayThe.getTonkhoNgayhandung());
                                tntTemp.setThuocnoitruSodangky(tonkhoThayThe.getTonkhoSodangky());
                                if(tnt.getThuocnoitruDongia() > 0){
                                    tntTemp.setThuocnoitruDongia(tonkhoThayThe.getTonkhoDongia());
                                    tntTemp.setThuocnoitruDongiabh(tonkhoThayThe.getTonkhoDongia());
                                    tntTemp.setThuocnoitruDongiatt(Utils.roundDoubleNumber(tonkhoThayThe.getTonkhoDongia()));
                                    Double thanhTien = ( (tntTemp.getThuocnoitruSoluong() - (tntTemp.getThuocnoitruTra() == null ? 0 : tntTemp.getThuocnoitruTra())) * tntTemp.getThuocnoitruDongiatt() );
                                    tntTemp.setThuocnoitruThanhtien(Utils.roundDoubleNumber(thanhTien));
                                }
                                listTNT.set(j,tntTemp);
                            }
                            j++;
                        }
                    }
                }
                i++;
            }
        }             

        pxk.setPhieudtMa(phieuDuTru);
        if (pxk.getPhieuxuatkhoMa() != null && !pxk.getPhieuxuatkhoMa().equals("")) {
            getEm().merge(pxk);
        } else {
            pxk.setPhieuxuatkhoMa(Utils.maPhieuXuat(getEm()));
            getEm().persist(pxk);
        }

        PhieuDuTru pdt_DB = phieuDuTrufacade.find(pxk.getPhieudtMa().getPhieudtMa());
        Integer khoaMaso = pdt_DB.getPhieudtMakho().getDmkhoaMaso();

        try {
            pdt_DB.setPhieudtDaXuat(true);
            phieuDuTrufacade.edit(pdt_DB);
            
            for (CtXuatKho ctxk : listCTX) {
                ctxk.setPhieuxuatkhoMa(pxk);
                Double soluong = ctxk.getCtxuatkhoSoluong();
                //thanh do add here
                Double soLuongThuocThucTeCapPhat = new Double(0);
                TonKho tk = tonKhoFacade.getTonKhoHienTai(ctxk.getCtxuatkhoMalk(), khoaMaso);

                if (tk == null || tk.getTonkhoTon() <= 0) {
                    continue;
                }

                TonKho newtkxuat = null;

                if (soluong.doubleValue() > 0) {
                    if (tk.getTonkhoTon().doubleValue() >= soluong.doubleValue()) {// du so luong thuoc can thiet
                        //tk.getTonkhoTon().doubleValue()  >=  soluong.doubleValue()  

                        newtkxuat = (TonKho) BeanUtils.cloneBean(tk);
                        newtkxuat.setTonkhoMa(null);

                        
                        

                        newtkxuat.setTonkhoNhap(new Double(0));
                        newtkxuat.setTonkhoTra(new Double(0));
                        newtkxuat.setTonkhoXuat(soluong);
                        newtkxuat.setTonkhoMa(null);
                        
                        
                        
                        
                        
                        
                        

                      
                        facadeTK.insertTonKho(newtkxuat);

                        soLuongThuocThucTeCapPhat += soluong.doubleValue();

                        ctxk.setCtxuatkhoLo(tk.getTonkhoLo());
                        ctxk.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
                        ctxk.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
                        ctxk.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
                        ctxk.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
                        ctxk.setCtxuatkhoSodangky(tk.getTonkhoSodangky());
                        ctxk.setDmnctMaso(tk.getDmnctMaso(true));
                        ctxk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso(true));
                        ctxk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso(true));
                        ctxk.setDmquocgiaMaso(tk.getDmquocgiaMaso(true));
                    }
                } else {
                    soLuongThuocThucTeCapPhat = new Double(0);
                }

                ctxk.setCtxuatkhoSoluong(soLuongThuocThucTeCapPhat);
                ctxk.setTonKhoMa(newtkxuat.getTonkhoMa());
                if (ctxk.getCtxuatkhoMa() != null) {
                    getEm().merge(ctxk);
                } else {
                    ctxk.setCtxuatkhoNgaygiocn(new Date());
                    getEm().persist(ctxk);
                }
                System.out.println(" begin capnhatThuocNoiTruStatus");

                capnhatThuocNoiTruStatus(pdt_DB.getDmkhoaMaso().getDmkhoaMaso(), pxk.getPhieudtMa().getPhieudtMa());

                System.out.println(" end capnhatThuocNoiTruStatus");

            } // end  for (CtXuatKho ctxk : listCTX) {
            //ghinhan ctPhieuDt
            for(CtPhieuDt ctPDT:listCtPDT){
                ctPDTFacade.edit(ctPDT);
            }

            //ghinhan list thuoc noi tru
            for(ThuocNoiTru tnt:listTNT){
                tnt.setThuocnoitruStatus("2");
                tntFacade.edit(tnt);
            }
            result = pxk.getPhieuxuatkhoMa();
        } catch (Exception ex) {
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong XuatPhieuDuTru(): " + ex);
            throw ex;
        }

        return result;
    }

    public String XuatPhieuDuTruTuTruc(List<CtXuatKho> listCTX, PhieuXuatKho pxk, String priority) throws Exception {
        String result = "";

        PhieuDuTru pdt = phieuDuTrufacade.find(pxk.getPhieudtMa().getPhieudtMa());
        List<CtPhieuDt> listCtPDT = new ArrayList<CtPhieuDt>();
        listCtPDT = ctPhieuDtFacade.findByPhieuDuTruMa(pxk.getPhieudtMa().getPhieudtMa());
        TonKhoFacade facadeTK = new TonKhoFacade();
        facadeTK.setEm(getEm());
        Utils.setInfor(pdt, getEm());
        
        CtPhieuDtFacade chiTietPhieuDuTruFacade = new CtPhieuDtFacade();
        chiTietPhieuDuTruFacade.setEm(getEm());

        Integer dmKhoMaso = pdt.getPhieudtMakho(true).getDmkhoaMaso();

        String ketquaKiemTra = kiemTraTruocKhiXuatHangTheoPhieuDuTru(listCTX, dmKhoMaso, priority);
        if (!ketquaKiemTra.equals("")) {
            return ketquaKiemTra;
        }

        /*Thay doi dong listCTPhieuDuTru neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
         * Thay doi lo thuoc cho listCTPhieuDuTru
         */
        int vitriThaythe = -1;
        int i = 0;
        for(CtXuatKho ctXuatKho : listCTX){
            CtXuatKho ctXuatKhoTemp = new CtXuatKho();
            ctXuatKhoTemp = (CtXuatKho)BeanUtils.cloneBean(ctXuatKho);
            if (ctXuatKho.getPhieuxuatkhoMa(true).getPhieuxuatkhoMa() == null) {
                Double soluongCanXuat = ctXuatKho.getCtxuatkhoSoluong();
                String maLK = ctXuatKho.getCtxuatkhoMalk();
                Integer thuocMaso = ctXuatKho.getDmthuocMaso(true).getDmthuocMaso();
                List<TonKho> listTonKhoHT = facadeTK.getTonKhoHienTai(thuocMaso, dmKhoMaso, priority);
                TonKho tonLoHienTai = facadeTK.getTonKhoHienTai(maLK, dmKhoMaso);
                Double soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                TonKho tonkhoThayThe = new TonKho();
//                System.out.println("Ma lien ket: "+ maLK);
//                System.out.println("So luong Can Xuat: " + soluongCanXuat);
//                System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                    //System.out.println("So luong ton hien tai khong du de xuat, phai tim chon lo khac");
                    for(TonKho tonkho:listTonKhoHT){
                        if(soluongCanXuat <= tonkho.getTonkhoTon()){
                            tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                            vitriThaythe = i;
                            break;
                        }
                    }
                    
                    if(vitriThaythe > -1){
                        System.out.println("Vi tri thay the: "+ vitriThaythe);
                        ctXuatKhoTemp.setCtxuatkhoMalk(tonkhoThayThe.getTonkhoMalienket());
                        ctXuatKhoTemp.setCtxuatkhoDongia(tonkhoThayThe.getTonkhoDongia());
                        ctXuatKhoTemp.setCtxuatkhoLo(tonkhoThayThe.getTonkhoLo());
                        ctXuatKhoTemp.setCtxuatkhoNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                        ctXuatKhoTemp.setCtxuatkhoNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                        ctXuatKhoTemp.setCtxuatkhoThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                        ctXuatKhoTemp.setCtxuatkhoNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                        ctXuatKhoTemp.setCtxuatkhoSodangky(tonkhoThayThe.getTonkhoSodangky());
                        ctXuatKhoTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                        ctXuatKhoTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                        ctXuatKhoTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                        ctXuatKhoTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                        listCTX.set(vitriThaythe, ctXuatKhoTemp);

                        //Thay the maLK moi, gia thuoc moi cho chi tiet phieu du tru
                        int j = 0;
                        if(listCtPDT != null && listCtPDT.size()>0){
                            for(CtPhieuDt ctPDT:listCtPDT){
                                CtPhieuDt ctPDTTemp = new CtPhieuDt();
                                ctPDTTemp = (CtPhieuDt)BeanUtils.cloneBean(ctPDT);
                                if(ctPDT.getCtdtMalk().equals(maLK)){
                                    System.out.println("Thay the trong thuoc noi tru voi "+ maLK);
                                    ctPDTTemp.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                                    ctPDTTemp.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                                    ctPDTTemp.setCtdtLo(tonkhoThayThe.getTonkhoLo());
                                    ctPDTTemp.setCtdtNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                                    ctPDTTemp.setCtdtNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                                    ctPDTTemp.setCtdtThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                                    ctPDTTemp.setCtdtNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                                    ctPDTTemp.setCtdtSodangky(tonkhoThayThe.getTonkhoSodangky());
                                    ctPDTTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                                    ctPDTTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                                    ctPDTTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                                    ctPDTTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                                    listCtPDT.set(j, ctPDTTemp);
                                    break;
                                }
                                j++;
                            }
                        }
                    }
                }
                i++;
            }
        }

        pxk.setPhieudtMa(pdt);
        if (pxk.getPhieuxuatkhoMa() != null && !pxk.getPhieuxuatkhoMa().equals("")) {
            getEm().merge(pxk);
        } else {
            pxk.setPhieuxuatkhoMa(Utils.maPhieuXuat(getEm()));
            getEm().persist(pxk);
        }

        PhieuDuTru pdt_DB = phieuDuTrufacade.find(pxk.getPhieudtMa().getPhieudtMa());
        Integer khoaMaso = pdt_DB.getPhieudtMakho().getDmkhoaMaso();

        try {
            pdt_DB.setPhieudtDaXuat(true);
            phieuDuTrufacade.edit(pdt_DB);

            for (CtXuatKho ctxk : listCTX) {

                ctxk.setPhieuxuatkhoMa(pxk);
                Double soluong = ctxk.getCtxuatkhoSoluong();
                //thanh do add here
                Double soLuongThuocThucTeCapPhat = new Double(0);
                //System.out.println("Ma lien ket cua Ct xuat: "+ctxk.getCtxuatkhoMalk());
                TonKho tk = facadeTK.getTonKhoHienTai(ctxk.getCtxuatkhoMalk(), khoaMaso);

                if (tk == null || tk.getTonkhoTon() <= 0) {
                    continue;
                }

                TonKho newtkxuat = null;

                if (soluong.doubleValue() > 0) {
                    if (tk.getTonkhoTon().doubleValue() >= soluong.doubleValue()) {// du so luong thuoc can thiet
                        newtkxuat = (TonKho) BeanUtils.cloneBean(tk);
                        

                        TonKho newtknhap = (TonKho) BeanUtils.cloneBean(tk);
                        

                        newtkxuat.setTonkhoNhap(new Double(0));
                        newtkxuat.setTonkhoTra(new Double(0));
                        newtkxuat.setTonkhoXuat(soluong);

                        newtknhap.setTonkhoNhap(soluong);
                        newtknhap.setTonkhoTra(new Double(0));
                        newtknhap.setTonkhoXuat(new Double(0));
                        newtknhap.setDmkhoaMaso(pxk.getDmkhoaNhan());
                        
                        newtknhap.setTonkhoMa(null);
                        newtkxuat.setTonkhoMa(null);

                        facadeTK.insertTonKho(newtknhap);
                        facadeTK.insertTonKho(newtkxuat);

                        soLuongThuocThucTeCapPhat += soluong.doubleValue();

                        ctxk.setCtxuatkhoLo(tk.getTonkhoLo());
                        ctxk.setCtxuatkhoNamnhap(tk.getTonkhoNamnhap());
                        ctxk.setCtxuatkhoNamhandung(tk.getTonkhoNamhandung());
                        ctxk.setCtxuatkhoThanghandung(tk.getTonkhoThanghandung());
                        ctxk.setCtxuatkhoNgayhandung(tk.getTonkhoNgayhandung());
                        ctxk.setCtxuatkhoSodangky(tk.getTonkhoSodangky());
                        ctxk.setDmnctMaso(tk.getDmnctMaso(true));
                        ctxk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso(true));
                        ctxk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso(true));
                        ctxk.setDmquocgiaMaso(tk.getDmquocgiaMaso(true));
                    }
                } else {
                    soLuongThuocThucTeCapPhat = new Double(0);
                }

                ctxk.setCtxuatkhoSoluong(soLuongThuocThucTeCapPhat);
                ctxk.setTonKhoMa(newtkxuat.getTonkhoMa());
                if (ctxk.getCtxuatkhoMa() != null) {
                    getEm().merge(ctxk);
                } else {
                    ctxk.setCtxuatkhoNgaygiocn(new Date());
                    getEm().persist(ctxk);
                }

                //capNhatVaTinhTienChoThuocNoiTru(listCTX, pxk.getDmkhoaNhan().getDmkhoaMaso());

            } // end  for (CtXuatKho ctxk : listCTX) {

            //cap nhat lai ctPhieuDt
            for(CtPhieuDt ctPDT:listCtPDT){
                ctPhieuDtFacade.edit(ctPDT);
            }

            result = pxk.getPhieuxuatkhoMa();
        } catch (Exception ex) {
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong XuatPhieuDuTruTuTruc(): " + ex);
            throw ex;
        }

        return result;
    }

    private void capNhatVaTinhTienChoThuocNoiTru(String maLK, Integer khoaMaso) {
        List<ThuocNoiTru> listCTietTNT = thuocNoiTruFacade.findTNTByKhoaMaSoAndMaLK(khoaMaso, maLK);
        for (ThuocNoiTru tnt : listCTietTNT) {
            /* luon luon du thuoc cap nhat cho tat ca*/
            tnt.setThuocnoitruStatus("2");
            getEm().merge(tnt);

        }
    }
    //Tho add
    private void capnhatThuocNoiTruStatus(Integer khoaMaso, String maphieuDT) {
//        System.out.println("khoaMaso:" + khoaMaso);
//        System.out.println("maphieuDT:" + maphieuDT);

        List<ThuocNoiTru> listCTietTNT = thuocNoiTruFacade.findTNTByMaPDT(khoaMaso, maphieuDT);
        for (ThuocNoiTru tnt : listCTietTNT) {
            tnt.setThuocnoitruStatus("2");
            getEm().merge(tnt);

        }

    }

    /**
     * 
     * @param tk
     * @return
     */
    private TonKho copyTonkho(TonKho tk) {
        TonKho tonKhoNew = new TonKho();
        tonKhoNew.setDmkhoaMaso(tk.getDmkhoaMaso());
        tonKhoNew.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
        tonKhoNew.setDmquocgiaMaso(tk.getDmquocgiaMaso());
        tonKhoNew.setDmthuocMaso(tk.getDmthuocMaso());
        tonKhoNew.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
        tonKhoNew.setDmnctMaso(tk.getDmnctMaso());
        tonKhoNew.setTonkhoDongia(tk.getTonkhoDongia());
        tonKhoNew.setTonkhoDongiaban(tk.getTonkhoDongia());
        tonKhoNew.setTonkhoMalienket(tk.getTonkhoMalienket());
        tonKhoNew.setTonkhoLo(tk.getTonkhoLo());

        tonKhoNew.setTonkhoNamhandung(tk.getTonkhoNamhandung());
        tonKhoNew.setTonkhoNgayhandung(tk.getTonkhoNgayhandung());
        tonKhoNew.setTonkhoThanghandung(tk.getTonkhoThanghandung());

//        tonKhoNew.setTonkhoThangnhap(tk.getTonkhoThangnhap());
        tonKhoNew.setTonkhoNamnhap(tk.getTonkhoNamnhap());
        tonKhoNew.setTonkhoNgaygiocn(new Date());
        return tonKhoNew;
    }

    /**
     * 
     * @param ctxk
     * @return
     */
    private CtXuatKho copyChiTietXuatKho(CtXuatKho ctxk) {
        CtXuatKho ctxkNew = new CtXuatKho();

        //ctxkNew.setCtdtMa(ctxk.getCtdtMa());
        ctxkNew.setCtxuatkhoDongia(ctxk.getCtxuatkhoDongia());
        ctxkNew.setCtxuatkhoDongiaban(ctxk.getCtxuatkhoDongiaban());
        ctxkNew.setCtxuatkhoLo(ctxk.getCtxuatkhoLo());

        //ctxkNew.setCtxuatkhoMa(ctxk.getCtxuatkhoMa());

        ctxkNew.setCtxuatkhoMalk(ctxk.getCtxuatkhoMalk());
        ctxkNew.setCtxuatkhoNamhandung(ctxk.getCtxuatkhoNamhandung());
        ctxkNew.setCtxuatkhoNamnhap(ctxk.getCtxuatkhoNamhandung());
        ctxkNew.setCtxuatkhoThanghandung(ctxk.getCtxuatkhoThanghandung());
        ctxkNew.setCtxuatkhoNgayhandung(ctxk.getCtxuatkhoNgayhandung());

        ctxkNew.setPhieuxuatkhoMa(ctxkNew.getPhieuxuatkhoMa());
        return ctxkNew;
    }

    /**
     * 
     * @param listCtxkMa
     * @param pxkMa
     */
    private void removeCtXuatKho(String listCtxkMa, String pxkMa) {
        /*
        Query q = em.createQuery("SELECT c FROM CtXuatKho c WHERE c.ctxuatkhoMa NOT IN (" + listCtxkMa + ") AND c.phieuxuatkhoMa.phieuxuatkhoMa = :pxkMa");
        q.setParameter("pxkMa", pxkMa);
        List<CtXuatKho> listCtXuat = q.getResultList();
        if (listCtXuat != null) {
        for (CtXuatKho ct : listCtXuat) {
        TonKho tkXuat = ct.getTonkhoXuat();
        TonKho tkNhan = ct.getTonkhoNhap();
        Double sl = ct.getCtxuatkhoSoluong();
        Double ton = tkXuat.getTonkhoXuat() - sl;
        tkXuat.setTonkhoXuat(ton);
        
        Double nhap = tkXuat.getTonkhoNhap() != null ? tkXuat.getTonkhoNhap() : 0;
        Double xuat = tkXuat.getTonkhoXuat() != null ? tkXuat.getTonkhoXuat() : 0;
        xuat = xuat + ct.getCtxuatkhoSoluong();
        Double tra = tkXuat.getTonkhoTra() != null ? tkXuat.getTonkhoTra() : 0;
        Double tonDau = tkXuat.getTonkhoTondau() != null ? tkXuat.getTonkhoTondau() : 0;
        tkXuat.setTonkhoXuat(xuat);
        tkXuat.setTonkhoTon((nhap + tra + tonDau) - xuat);
        
        em.remove(ct);
        em.remove(tkNhan);
        em.merge(tkXuat);
        System.out.println("Remove ct phieu xuat: " + ct.getCtxuatkhoMa());
        }
        }
         */
    }

    private Double tinhTon() {
        return null;
    }

    private PhieuXuatKho createPXK(PhieuDuTru pdt) {
        PhieuXuatKho pxk = new PhieuXuatKho();
        try {
            pxk.setDmkhoaNhan(pdt.getDmkhoaMaso());
            pxk.setDmkhoaXuat(pdt.getPhieudtMakho());
            pxk.setDmdoituongMaso(pdt.getDmdoituongMaso());

            pxk.setDmloaithuocMaso(pdt.getDmloaithuocMaso());
            pxk.setDtdmnhanvienBacsi(null);
            pxk.setDtdmnhanvienCn(null);
            pxk.setDtdmnhanvienNhan(null);
            pxk.setDtdmnhanvienPhat(null);
            pxk.setPhieudtMa(pdt);
        } catch (Exception ex) {
            System.out.println("Loi khi tao phieu nhap kho: " + ex.toString());
        }
        return pxk;
    }

    private CtXuatKho createCTX(CtPhieuDt ctpdt, PhieuXuatKho pxk) {
        CtXuatKho ctx = new CtXuatKho();
        try {
            //ctx.setCtxuatkhoSoluong(ctpdt.getCtdtPhat());
            ctx.setPhieuxuatkhoMa(pxk);
            TonKho tkNhap = new TonKho();
            TonKho tkXuat = new TonKho();
            tkXuat.setDmthuocMaso(ctpdt.getDmthuocMaso());
            tkNhap.setDmthuocMaso(ctpdt.getDmthuocMaso());

            tkXuat.setDmnguonkinhphiMaso(ctpdt.getDmnguonkinhphiMaso());
            tkNhap.setDmnguonkinhphiMaso(ctpdt.getDmnguonkinhphiMaso());
            tkXuat.setDmquocgiaMaso(ctpdt.getDmquocgiaMaso());
            tkNhap.setDmquocgiaMaso(ctpdt.getDmquocgiaMaso());
            tkXuat.setDmnhasanxuatMaso(ctpdt.getDmnhasanxuatMaso());
            tkNhap.setDmnhasanxuatMaso(ctpdt.getDmnhasanxuatMaso());
            tkXuat.setDmnctMaso(ctpdt.getDmnctMaso());
            tkNhap.setDmnctMaso(ctpdt.getDmnctMaso());
            tkXuat.setTonkhoDongia(ctpdt.getCtdtDongia());
            tkNhap.setTonkhoDongia(ctpdt.getCtdtDongia());
            tkXuat.setTonkhoDongiaban(ctpdt.getCtdtDongianhap());
            tkNhap.setTonkhoDongiaban(ctpdt.getCtdtDongianhap());


        } catch (Exception e) {
            System.out.println("Loi trong khi tao chi tiet nhap" + e.toString());
        }
        return ctx;
    }

    public ThuocNoiTruFacadeLocal getThuocNoiTruFacade() {
        return thuocNoiTruFacade;
    }

    public void setThuocNoiTruFacade(ThuocNoiTruFacadeLocal thuocNoiTruFacade) {
        this.thuocNoiTruFacade = thuocNoiTruFacade;
    }

    public HsThtoanFacadeLocal getHsThToanFacade() {
        return hsThToanFacade;
    }

    public void setHsThToanFacade(HsThtoanFacadeLocal hsThToanFacade) {
        this.hsThToanFacade = hsThToanFacade;
    }

    /**
     *  Ham luu phieu du tru xuat thuoc cua kho le
     *  luc nay chua cap nhat ton kho chi luu 2 table phieuxuat va chi tiet phieu xuat
     *
     */
    public String upDatePhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho) {
        System.out.println("-----Begin: upDatePhieuXuat()-----");
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(getEm());
        String result = "";
        String pxkMa = ""; 

        if (pxkMa.equals("") || pxkMa.equals("null")) {
            pxkMa = Utils.maPhieuXuat(getEm());
            pxk.setPhieuxuatkhoMa(pxkMa);
            getEm().persist(pxk);
        }

        for (int i = 0; i < listCtXuatKho.size(); i++) {
            CtXuatKho ctx = listCtXuatKho.get(i);
            getEm().persist(ctx);
            result = pxk.getPhieuxuatkhoMa();
        }
        System.out.println("result: " + result);
        return result;
    }

    public String thucHienPhieuXuat(PhieuXuatKho pxk, List<CtXuatKho> listCtXuatKho, List<TonKho> listTkNhan, List<TonKho> listTkXuat, String priority) {
        System.out.println("-----Begin: thucHienPhieuXuat()-----");
        String result = "";
        TonKhoFacade tonkhoFacade = new TonKhoFacade();
        tonkhoFacade.setEm(em);
        try{
            String ketquaKiemTra = kiemTraTruocKhiXuatHangTheoPhieuDuTru(listCtXuatKho, pxk.getDmkhoaXuat().getDmkhoaMaso(), priority);
            if (!ketquaKiemTra.equals("")) {
                return ketquaKiemTra;
            }

            /*Thay doi dong listCtXuatKho neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
             * Thay doi lo thuoc cho listCtXuatKho
             */
            Integer dmKhoMaso = pxk.getDmkhoaXuat().getDmkhoaMaso();
            int vitriThaythe = -1;
            TonKho tonkhoThayThe = new TonKho();
            int i = 0;
            for(CtXuatKho ctXuatKho : listCtXuatKho){
                Double soluongCanXuat = ctXuatKho.getCtxuatkhoSoluong();
                String maLK = ctXuatKho.getCtxuatkhoMalk();
                Integer thuocMaso = ctXuatKho.getDmthuocMaso(true).getDmthuocMaso();

                List<TonKho> listTonKhoHT = tonkhoFacade.getTonKhoHienTai(thuocMaso, dmKhoMaso, priority);
                TonKho tonLoHienTai = tonkhoFacade.getTonKhoHienTai(maLK, dmKhoMaso);
                Double soluongTonLoHienTai = 0.0;
                if(tonLoHienTai != null){
                    soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                }

    //            System.out.println("Ma lien ket: "+ maLK);
    //            System.out.println("So luong Can Xuat: " + soluongCanXuat);
    //            System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                    //System.out.println("So luong ton hien tai khong du de xuat, phai tim chon lo khac");
                    for(TonKho tonkho:listTonKhoHT){
                        if(soluongCanXuat <= tonkho.getTonkhoTon()){
                            tonkhoThayThe = (TonKho) BeanUtils.cloneBean(tonkho);
                            vitriThaythe = i;
                            break;
                        }
                    }
    //                System.out.println("Vi tri thay the: "+ vitriThaythe);
    //                System.out.println("Ma lien ket thay the: "+ maLKThayThe);
    //                System.out.println("Don gia thay the: "+ dongiaThaythe);
                    if(vitriThaythe > -1){
                        ctXuatKho.setCtxuatkhoMalk(tonkhoThayThe.getTonkhoMalienket());
                        ctXuatKho.setCtxuatkhoDongia(tonkhoThayThe.getTonkhoDongia());
                        listCtXuatKho.set(vitriThaythe, ctXuatKho);
                    }
                }
                i++;
            }

            if (pxk.getPhieuxuatkhoMa() != null || !pxk.getPhieuxuatkhoMa().equals("")) {
                getEm().merge(pxk);
                System.out.println("UPDATE phieu xuat: " + pxk.getPhieuxuatkhoMa());

                for (i = 0; i < listCtXuatKho.size(); i++) {
                    System.out.println("-----Insert ct xuat -----");
                    CtXuatKho ctx = listCtXuatKho.get(i);
                    TonKho tkNhan = listTkNhan.get(i);
                    TonKho tkXuat = listTkXuat.get(i);
                    tkNhan.setDmkhoaMaso(pxk.getDmkhoaNhan());
                    tkXuat.setDmkhoaMaso(pxk.getDmkhoaXuat());
                    
                    TonKho tonkhoInfo = tonkhoFacade.findByTonkhoMalienket(ctx.getCtxuatkhoMalk()).get(0);
                    //Kiem tra xem neu ctx.malk khac voi tknhan.malk (tkxuat.malk) tuc la co thay doi lo thuoc, phai switch ca cac thong tin di kem cua lo voi malk do
                    if(!ctx.getCtxuatkhoMalk().equals(tkNhan.getTonkhoMalienket()) && tonkhoInfo != null){
                        tkNhan.setDmNhaCungCap(tonkhoInfo.getDmNhaCungCap());
                        tkNhan.setDmnctMaso(tonkhoInfo.getDmnctMaso());
                        tkNhan.setDmnguonkinhphiMaso(tonkhoInfo.getDmnguonkinhphiMaso());
                        tkNhan.setDmnhasanxuatMaso(tonkhoInfo.getDmnhasanxuatMaso());
                        tkNhan.setDmquocgiaMaso(tonkhoInfo.getDmquocgiaMaso());
                        tkNhan.setPhieunhapkhoMa(tonkhoInfo.getPhieunhapkhoMa());
                        tkNhan.setTonkhoDongiaban(tonkhoInfo.getTonkhoDongiaban());
                        tkNhan.setTonkhoLo(tonkhoInfo.getTonkhoLo());
                        tkNhan.setTonkhoNamhandung(tonkhoInfo.getTonkhoNamhandung());
                        tkNhan.setTonkhoNamnhap(tonkhoInfo.getTonkhoNamnhap());
                        tkNhan.setTonkhoNgayhandung(tonkhoInfo.getTonkhoNgayhandung());
                        tkNhan.setTonkhoThanghandung(tonkhoInfo.getTonkhoThanghandung());
                        tkNhan.setTonkhoSodangky(tonkhoInfo.getTonkhoSodangky());
                        tkNhan.setTonkhoPhanbiet(tonkhoInfo.getTonkhoPhanbiet());
                        tkNhan.setTonkhoCapphat(tonkhoInfo.getTonkhoCapphat());
                        tkNhan.setTonkhoMucthue(tonkhoInfo.getTonkhoMucthue());
                        tkNhan.setTonkhoHienthi(tonkhoInfo.getTonkhoHienthi());

                        //tkXuat
                        tkXuat.setDmNhaCungCap(tonkhoInfo.getDmNhaCungCap());
                        tkXuat.setDmnctMaso(tonkhoInfo.getDmnctMaso());
                        tkXuat.setDmnguonkinhphiMaso(tonkhoInfo.getDmnguonkinhphiMaso());
                        tkXuat.setDmnhasanxuatMaso(tonkhoInfo.getDmnhasanxuatMaso());
                        tkXuat.setDmquocgiaMaso(tonkhoInfo.getDmquocgiaMaso());
                        tkXuat.setPhieunhapkhoMa(tonkhoInfo.getPhieunhapkhoMa());
                        tkXuat.setTonkhoDongiaban(tonkhoInfo.getTonkhoDongiaban());
                        tkXuat.setTonkhoLo(tonkhoInfo.getTonkhoLo());
                        tkXuat.setTonkhoNamhandung(tonkhoInfo.getTonkhoNamhandung());
                        tkXuat.setTonkhoNamnhap(tonkhoInfo.getTonkhoNamnhap());
                        tkXuat.setTonkhoNgayhandung(tonkhoInfo.getTonkhoNgayhandung());
                        tkXuat.setTonkhoThanghandung(tonkhoInfo.getTonkhoThanghandung());
                        tkXuat.setTonkhoSodangky(tonkhoInfo.getTonkhoSodangky());
                        tkXuat.setTonkhoPhanbiet(tonkhoInfo.getTonkhoPhanbiet());
                        tkXuat.setTonkhoCapphat(tonkhoInfo.getTonkhoCapphat());
                        tkXuat.setTonkhoMucthue(tonkhoInfo.getTonkhoMucthue());
                        tkXuat.setTonkhoHienthi(tonkhoInfo.getTonkhoHienthi());
                    }
                    tkNhan.setTonkhoMalienket(ctx.getCtxuatkhoMalk());
                    tkXuat.setTonkhoMalienket(ctx.getCtxuatkhoMalk());
                    tkNhan.setTonkhoDongia(ctx.getCtxuatkhoDongia());
                    tkXuat.setTonkhoDongia(ctx.getCtxuatkhoDongia());
                    if (tonkhoFacade.insertTonKho(tkNhan) && tonkhoFacade.insertTonKho(tkXuat)) {
                        ctx.setPhieuxuatkhoMa(pxk);
                        ctx.setTonKhoMa(tkXuat.getTonkhoMa());
                        getEm().merge(ctx);

                        CtXuatKhoTmp xkTmp = new CtXuatKhoTmp();
                        xkTmp.setCtxuatkhoMa(ctx.getCtxuatkhoMa());
                        xkTmp.setCtxuatkhoNamnhap(ctx.getCtxuatkhoNamnhap());
                        xkTmp.setCtxuatkhoNgayhandung(ctx.getCtxuatkhoNgayhandung());
                        xkTmp.setCtxuatkhoThanghandung(ctx.getCtxuatkhoThanghandung());
                        xkTmp.setCtxuatkhoNamhandung(ctx.getCtxuatkhoNamhandung());
                        xkTmp.setCtxuatkhoDongia(ctx.getCtxuatkhoDongia());
                        xkTmp.setCtxuatkhoDongiaban(ctx.getCtxuatkhoDongiaban());
                        xkTmp.setCtxuatkhoLo(ctx.getCtxuatkhoLo());
                        xkTmp.setCtxuatkhoMalk(ctx.getCtxuatkhoMalk());
                        xkTmp.setCtxuatkhoSodangky(ctx.getCtxuatkhoSodangky());
                        xkTmp.setCtxuatkhoThutu(ctx.getCtxuatkhoThutu());
                        xkTmp.setCtxuatkhoSoluong(ctx.getCtxuatkhoSoluong());
                        xkTmp.setCtxuatkhoNgaygiocn(ctx.getCtxuatkhoNgaygiocn());
                        xkTmp.setDmnctMaso(ctx.getDmnctMaso());
                        xkTmp.setDmnguonkinhphiMaso(ctx.getDmnguonkinhphiMaso());
                        xkTmp.setDmnhasanxuatMaso(ctx.getDmnhasanxuatMaso());
                        xkTmp.setDmquocgiaMaso(ctx.getDmquocgiaMaso());
                        xkTmp.setDmthuocMaso(ctx.getDmthuocMaso());
                        xkTmp.setPhieuxuatkhoMa(ctx.getPhieuxuatkhoMa());
                        xkTmp.setTonkhoMa(tkNhan.getTonkhoMa());
                        getEm().merge(xkTmp);

                        result = pxk.getPhieuxuatkhoMa();
                    } else {
                        result = "";
                    }
                }
            }
        }catch(Exception ex){
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong thucHienPhieuXuat(): " + ex);
        }
        System.out.println("-----End: thucHienPhieuXuat()-----with result = "+ result);
        return result;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the context
     */
    public SessionContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(SessionContext context) {
        this.context = context;
    }
}


