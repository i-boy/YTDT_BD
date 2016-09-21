package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtTraKho;
import com.iesvn.yte.dieutri.entity.CtTraKhoTmp;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.PhieuTraKho;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.sun.org.apache.commons.beanutils.BeanUtils;
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
public class PhieuTraKhoFacade implements PhieuTraKhoFacadeLocal, PhieuTraKhoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @EJB
    private TonKhoFacadeLocal tkFacade;
    @EJB
    private PhieuDuTruFacadeLocal phieuDuTrufacade;
    @Resource
    private SessionContext context;

    public void create(PhieuTraKho phieuTraKho) {
        getEm().persist(phieuTraKho);
    }

    public void edit(PhieuTraKho phieuTraKho) {
        getEm().merge(phieuTraKho);
    }

    public void remove(PhieuTraKho phieuTraKho) {
        getEm().remove(getEm().merge(phieuTraKho));
    }

    public PhieuTraKho find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.PhieuTraKho.class, id);
    }

    public List<PhieuTraKho> findAll() {
        return getEm().createQuery("select object(o) from PhieuTraKho as o").getResultList();
    }

    public PhieuTraKho findByPhieutrakhoMa(String maPhieu) {
        System.out.println("-----findByPhieutrakhoMa()-----");
        System.out.println("-----ma phieu (input): " + maPhieu);
        maPhieu = Utils.formatMaPhieu(maPhieu);
        System.out.println("-----ma phieu (format): " + maPhieu);
        try {
            Object obj = getEm().find(PhieuTraKho.class, maPhieu);
            if (obj != null) {
                return (PhieuTraKho) obj;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public PhieuTraKho findByPhieutrakhoByKhoNhan(String maPhieu, Integer khoaNhanMaso) {
        System.out.println("-----findByPhieutrakhoByKhoNhan()-----");
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuTraKho c ";
        sQuery += " WHERE  c.phieutrakhoMa like :maPhieu AND c.dmkhoaNhan.dmkhoaMaso = :maKhoa";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("maKhoa", khoaNhanMaso);
        List<PhieuTraKho> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public PhieuTraKho findPhieuTraKhoByKhoaTra(String maPhieu, Integer maKhoa) {
        System.out.print("-----Tim theo maPhieu: " + maPhieu + " maKhoa: " + maKhoa);
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuTraKho c ";
        sQuery += " WHERE  c.phieutrakhoMa like :maPhieu AND c.dmkhoaTra.dmkhoaMaso = :maKhoa";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("maKhoa", maKhoa);
        List<PhieuTraKho> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public String createPhieuTra(PhieuTraKho ptk, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra) {
        System.out.println("-----createPhieuTra()-----");
        String result = "";
        String ptkMa = "";

        System.out.println("-----Insert phieu tra");
        ptkMa = Utils.maPhieuTra(getEm());
        ptk.setPhieutrakhoMa(ptkMa);
        getEm().persist(ptk);
        System.out.println("Insert phieu tra: " + ptkMa);

        for (int i = 0; i < listCtTraKho.size(); i++) {
            System.out.println("-----Insert ct tra");
            CtTraKho ctx = listCtTraKho.get(i);
            TonKho tkNhan = listTkNhan.get(i);
            TonKho tkTra = listTkTra.get(i);
            tkNhan.setDmkhoaMaso(ptk.getDmkhoaNhan());
            tkTra.setDmkhoaMaso(ptk.getDmkhoaTra());

            if (tkFacade.insertTonKho(tkNhan) && tkFacade.insertTonKho(tkTra)) {
                System.out.println("ton kho nhan: " + tkNhan.getTonkhoMa());
                System.out.println("ton kho tra: " + tkTra.getTonkhoMa());
                ctx.setPhieutrakhoMa(ptk);
                ctx.setTonkhoMa(tkNhan.getTonkhoMa());
                getEm().persist(ctx);
                System.out.println("ct xuat ma: " + ctx.getCttrakhoMa());
                result = ptkMa;
            } else {
                result = "";
            }
        }
        System.out.println("result: " + result);
        return result;
    }

    public boolean daTraPhieuDuTru(String phieuDt) {

        boolean ketqua = true;

        //maPhieuDuTru = Utils.formatMaPhieu(maPhieuDuTru);
        //System.out.println("maPhieuDuTru:" + maPhieuDuTru);

        Query q = getEm().createQuery("select object(o) from PhieuTraKho as o Where  o.phieudtMa.phieudtMa like :phieuDt");
        q.setParameter("phieuDt", phieuDt);
        List<ThuocNoiTru> list = q.getResultList();
        if (list == null || list.size() == 0) {
            return false;
        }
        return ketqua;
    }
    //Tho da sua
    public String TraPhieuDuTru(List<CtTraKho> listCTX, PhieuTraKho ptk) throws Exception {
        System.out.println("Begin--------------Tra hang theo phieu du tru---------------------");
        String result = "";

        System.out.println("ptk.getPhieudtMa().getPhieudtMa()):" + ptk.getPhieudtMa().getPhieudtMa());
        if (daTraPhieuDuTru(ptk.getPhieudtMa().getPhieudtMa())) {
            System.out.println("daTraPhieuDuTru");
            return "";
        }
        PhieuDuTru pdt = phieuDuTrufacade.find(ptk.getPhieudtMa().getPhieudtMa());

        Utils.setInfor(pdt, getEm());
        //Cap nhat phieu tra kho
        ptk.setPhieudtMa(pdt);
        if (ptk.getPhieutrakhoMa() != null && !ptk.getPhieutrakhoMa().equals("")) {
            getEm().merge(ptk);
        } else {
            ptk.setPhieutrakhoMa(Utils.maPhieuTra(getEm()));
            getEm().persist(ptk);
        }
        TonKhoFacade tonkhoFacade = new TonKhoFacade();
        tonkhoFacade.setEm(getEm());
        try {            
            //Cap nhat chi tiet phieu tra kho
            for (CtTraKho cttk : listCTX) {
                cttk.setPhieutrakhoMa(ptk);
                Double soluong = cttk.getCttrakhoSoluong();
                CtPhieuDtFacade chiTietPhieuDuTruFacade = new CtPhieuDtFacade();
                
                
                chiTietPhieuDuTruFacade.setEm(getEm());
                DmKhoa khoNhan = ptk.getDmkhoaNhan();

                
                
                //get ton kho hien tai
                TonKho tonkhoHT = tonkhoFacade.getTonKhoHienTai(cttk.getCttrakhoMalk(),khoNhan.getDmkhoaMaso());
                if (tonkhoHT == null) {
                   
                    continue;
                }
                TonKho newtknhap = null;
                newtknhap = (TonKho) BeanUtils.cloneBean(tonkhoHT);
                newtknhap.setTonkhoNhap(new Double(0));
                newtknhap.setTonkhoTra(soluong);
                newtknhap.setTonkhoXuat(new Double(0));
                newtknhap.setDmkhoaMaso(ptk.getDmkhoaNhan());
                newtknhap.setTonkhoMa(null);
                
                tonkhoFacade.insertTonKho(newtknhap);

                cttk.setTonkhoMa(newtknhap.getTonkhoMa());
                cttk.setCttrakhoLo(newtknhap.getTonkhoLo());
                cttk.setCtxuatkhoSodangky(newtknhap.getTonkhoSodangky());
                cttk.setCttrakhoNgaygiocn(new Date());
                cttk.setCttrakhoDongiaban(newtknhap.getTonkhoDongiaban());
                cttk.setCttrakhoNgayhandung(newtknhap.getTonkhoNgayhandung());
                cttk.setCttrakhoThanghandung(newtknhap.getTonkhoThanghandung());
                cttk.setCttrakhoNamhandung(newtknhap.getTonkhoNamhandung());
                cttk.setCttrakhoNamnhap(newtknhap.getTonkhoNamnhap());
                cttk.setDmnctMaso(newtknhap.getDmnctMaso());
                cttk.setDmnguonkinhphiMaso(newtknhap.getDmnguonkinhphiMaso());
                cttk.setDmnhasanxuatMaso(newtknhap.getDmnhasanxuatMaso());
                cttk.setDmquocgiaMaso(newtknhap.getDmquocgiaMaso());
                //ghi nhan chi tiet tra kho
                getEm().persist(cttk);
                //cap nhat lai trang thai thuoc noi tru
                List<ThuocNoiTru> lstTNT = findByPhieuDuTruMaAndMaLK(ptk.getPhieudtMa().getPhieudtMa(), cttk.getCttrakhoMalk());
                System.out.print("lstTNT:" + lstTNT);

                if (lstTNT != null) {
                    for (ThuocNoiTru tnt : lstTNT) {
                        System.out.print("tnt:" + tnt);
                        tnt.setThuocnoitruStatus("5");
                        tnt.setThuocnoitruSoluong((tnt.getThuocnoitruSoluong() == null ? 0 : tnt.getThuocnoitruSoluong()) - tnt.getThuocnoitruTra());
                        tnt.setThuocnoitruTra(new Double(0));
                        tnt.setThuocnoitruMaphieupdttra(null);
                        getEm().merge(tnt);
                    }
                }
            }
            //***** cap nhat lai phieu du tru, cho biet phieu nay da duoc xuat roi hay tra roi
            System.out.println("-----cap nhat lai PDT: " + pdt.getPhieudtMa());
            pdt.setPhieudtDaXuat(true);
            phieuDuTrufacade.edit(pdt);
            //******
            result = ptk.getPhieutrakhoMa();
            System.out.println("End--------------Tra hang theo phieu du tru---------------------");
        } catch (Exception ex) {
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong TraPhieuDuTru(): " + ex);
            throw ex;
        }
        return result;
    }
    public List<ThuocNoiTru> findByPhieuDuTruMaAndMaLK(String phieudttraMa, String maLK) {

        System.out.print("phieuDuTruMa:" + phieudttraMa);
        phieudttraMa = Utils.formatMaPhieu(phieudttraMa);
        return getEm().createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruMalk =:thuocnoitruMalk and o.thuocnoitruMaphieupdttra like :thuocnoitruMaphieupdttra").setParameter("thuocnoitruMalk", maLK).setParameter("thuocnoitruMaphieupdttra", phieudttraMa).getResultList();
    }

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
        tonKhoNew.setTonkhoNamnhap(tk.getTonkhoNamnhap());
        tonKhoNew.setTonkhoNgaygiocn(new Date());
        return tonKhoNew;
    }

    public String thucHienTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho, List<TonKho> listTkNhan, List<TonKho> listTkTra) {
        String result = "";
        try {
            // Truong hop cap nhat
            if (objPhieuTraKho.getPhieutrakhoMa() != null && !"".equals(objPhieuTraKho.getPhieutrakhoMa())) {
                getEm().merge(objPhieuTraKho);
                System.out.println("----- UPDATE SUCCESS PhieuTraKho -----" + objPhieuTraKho.getPhieutrakhoMa());

                for (int i = 0; i < listCtTraKho.size(); i++) {
                    System.out.println("-----Insert ct xuat -----");
                    CtTraKho ctx = listCtTraKho.get(i);
                    TonKho tkNhan = listTkNhan.get(i);
                    TonKho tkTra = listTkTra.get(i);
                    tkNhan.setDmkhoaMaso(objPhieuTraKho.getDmkhoaNhan());
                    tkTra.setDmkhoaMaso(objPhieuTraKho.getDmkhoaTra());

                    if (tkFacade.insertTonKho(tkNhan) && tkFacade.insertTonKho(tkTra)) {
                        System.out.println("ton kho nhan: " + tkNhan.getTonkhoMa());
                        System.out.println("ton kho tra: " + tkTra.getTonkhoMa());
                        ctx.setPhieutrakhoMa(objPhieuTraKho);
                        ctx.setTonkhoMa(tkNhan.getTonkhoMa());
                        ctx.setCttrakhoNamnhap(tkNhan.getTonkhoNamnhap());
                        ctx.setCttrakhoNgayhandung(tkNhan.getTonkhoNgayhandung());
                        ctx.setCttrakhoThanghandung(tkNhan.getTonkhoThanghandung());
                        ctx.setCttrakhoNamhandung(tkNhan.getTonkhoNamhandung());
                        ctx.setCttrakhoLo(tkNhan.getTonkhoLo());
                        ctx.setCtxuatkhoSodangky(tkNhan.getTonkhoSodangky());
                        ctx.setCttrakhoNgaygiocn(new Date());
                        ctx.setDmnctMaso(tkNhan.getDmnctMaso());
                        ctx.setDmnguonkinhphiMaso(tkNhan.getDmnguonkinhphiMaso());
                        ctx.setDmnctMaso(tkNhan.getDmnctMaso());
                        ctx.setDmnhasanxuatMaso(tkNhan.getDmnhasanxuatMaso());
                        ctx.setDmquocgiaMaso(tkNhan.getDmquocgiaMaso());
                        ctx.setCtxuatkhoSodangky(ctx.getCtxuatkhoSodangky());
                        ctx.setCttrakhoNgaygiocn(ctx.getCttrakhoNgaygiocn());
                        em.merge(ctx);
                        System.out.println("ct tra ma: " + ctx.getCttrakhoMa());

                        CtTraKhoTmp tkTmp = new CtTraKhoTmp();
                        tkTmp.setCttrakhoMa(ctx.getCttrakhoMa());
                        tkTmp.setCttrakhoThutu(ctx.getCttrakhoThutu());
                        tkTmp.setCttrakhoSoluong(ctx.getCttrakhoSoluong());
                        tkTmp.setCttrakhoMalk(ctx.getCttrakhoMalk());
                        tkTmp.setCttrakhoNamnhap(ctx.getCttrakhoNamnhap());
                        tkTmp.setCttrakhoNgayhandung(ctx.getCttrakhoNgayhandung());
                        tkTmp.setCttrakhoThanghandung(ctx.getCttrakhoThanghandung());
                        tkTmp.setCttrakhoNamhandung(ctx.getCttrakhoNamhandung());
                        tkTmp.setCttrakhoDongia(ctx.getCttrakhoDongia());
                        tkTmp.setCttrakhoDongiaban(ctx.getCttrakhoDongiaban());
                        tkTmp.setCttrakhoLo(ctx.getCttrakhoLo());
                        tkTmp.setCtxuatkhoSodangky(ctx.getCtxuatkhoSodangky());
                        tkTmp.setCttrakhoNgaygiocn(ctx.getCttrakhoNgaygiocn());
                        tkTmp.setTonkhoMa(tkTra.getTonkhoMa());
                        tkTmp.setDmnctMaso(ctx.getDmnctMaso());
                        tkTmp.setDmnguonkinhphiMaso(ctx.getDmnguonkinhphiMaso());
                        tkTmp.setDmnctMaso(ctx.getDmnctMaso());
                        tkTmp.setDmnhasanxuatMaso(ctx.getDmnhasanxuatMaso());
                        tkTmp.setDmquocgiaMaso(ctx.getDmquocgiaMaso());
                        tkTmp.setDmthuocMaso(ctx.getDmthuocMaso());
                        tkTmp.setPhieutrakhoMa(ctx.getPhieutrakhoMa());
                        getEm().merge(tkTmp);

                        result = objPhieuTraKho.getPhieutrakhoMa();
                    } else {
                        result = "";
                    }

                }
                result = objPhieuTraKho.getPhieutrakhoMa();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public String updatePhieuTraKho(PhieuTraKho objPhieuTraKho, List<CtTraKho> listCtTraKho) {
        String result = "";
        try {

            // Truong hop cap nhat
            if (objPhieuTraKho.getPhieutrakhoMa() != null && !"".equals(objPhieuTraKho.getPhieutrakhoMa())) {
                getEm().merge(objPhieuTraKho);
                System.out.println("UPDATE SUCCESS PhieuTraKho" + objPhieuTraKho.getPhieutrakhoMa());

                CtTraKhoFacade objCtTraKho = new CtTraKhoFacade();
                objCtTraKho.setEm(getEm());
                objCtTraKho.updateCtTraKho(objPhieuTraKho, listCtTraKho);
                result = objPhieuTraKho.getPhieutrakhoMa();
            } // Truong hop them moi
            else {
                String sMaPhieu = Utils.maPhieuTra(getEm());
                objPhieuTraKho.setPhieutrakhoMa(sMaPhieu);
                getEm().persist(objPhieuTraKho);
                System.out.println("INSERT SUCCESS PhieuTraKho" + objPhieuTraKho.getPhieutrakhoMa());

                CtTraKhoFacade objCtTraKho = new CtTraKhoFacade();
                objCtTraKho.setEm(getEm());
                objCtTraKho.updateCtTraKho(objPhieuTraKho, listCtTraKho);
                result = objPhieuTraKho.getPhieutrakhoMa();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    /****
     *    Luu phieu tra kho du tren phieu du tru` tra cua tu truc khoa phong
     *    Khong update thuoc noi tru
     */
    public String updatePhieuDTTuTrucTraKho(List<CtTraKho> listCTT, PhieuTraKho ptk, String priority) throws Exception {
        System.out.print("-------------Begin: updatePhieuDTTuTrucTraKho----------------");
        String result = "";
        com.iesvn.yte.dieutri.session.CtPhieuDtFacade chiTietPhieuDuTruFacade = new CtPhieuDtFacade();
        chiTietPhieuDuTruFacade.setEm(getEm());
        TonKhoFacade tonkhoFacade = new TonKhoFacade();
        tonkhoFacade.setEm(getEm());
        CtPhieuDtFacade ctPDTFacade = new CtPhieuDtFacade();
        ctPDTFacade.setEm(getEm());
        System.out.println("ptk.getPhieudtMa().getPhieudtMa()):" + ptk.getPhieudtMa().getPhieudtMa());
        if (daTraPhieuDuTru(ptk.getPhieudtMa().getPhieudtMa())) {
            System.out.println("daTraPhieuDuTru");
            return "";
        }
        PhieuDuTru pdt = phieuDuTrufacade.find(ptk.getPhieudtMa().getPhieudtMa());
        List<CtPhieuDt> listCtPDT = ctPDTFacade.findByPhieuDuTruMa(ptk.getPhieudtMa(true).getPhieudtMa());
        Utils.setInfor(pdt, getEm());
        Integer dmKhoMaso = ptk.getDmkhoaTra().getDmkhoaMaso();
        String ketquaKiemTra = kiemTraTruocKhiTraHangTheoPhieuDuTru(listCTT, ptk.getDmkhoaTra().getDmkhoaMaso(), priority);
        if (!ketquaKiemTra.equals("")) {
            return ketquaKiemTra;
        }

        /*Thay doi dong listCTT neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
         * Thay doi lo thuoc cho listCTPhieuDuTru
         */
        int vitriThaythe = -1;
        int i = 0;
        for(CtTraKho ctTraKho : listCTT){
            CtTraKho ctTraKhoTemp = new CtTraKho();
            ctTraKhoTemp = (CtTraKho)BeanUtils.cloneBean(ctTraKho);
            if (ctTraKho.getPhieutrakhoMa(true).getPhieutrakhoMa() == null) {
                Double soluongCanTra = ctTraKho.getCttrakhoSoluong();
                String maLK = ctTraKho.getCttrakhoMalk();
                Integer thuocMaso = ctTraKho.getDmthuocMaso(true).getDmthuocMaso();

                List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, dmKhoMaso, priority);
                TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, dmKhoMaso);
                Double soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                TonKho tonkhoThayThe = new TonKho();
                if(soluongCanTra.doubleValue() > soluongTonLoHienTai.doubleValue()){
                    for(TonKho tonkho:listTonKhoHT){
                        if(soluongCanTra <= tonkho.getTonkhoTon()){
                            tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                            vitriThaythe = i;
                            break;
                        }
                    }
                    if(vitriThaythe > -1){
                        ctTraKhoTemp.setCttrakhoMalk(tonkhoThayThe.getTonkhoMalienket());
                        ctTraKhoTemp.setCttrakhoDongia(tonkhoThayThe.getTonkhoDongia());
                        ctTraKhoTemp.setCttrakhoLo(tonkhoThayThe.getTonkhoLo());
                        ctTraKhoTemp.setCttrakhoNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                        ctTraKhoTemp.setCttrakhoNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                        ctTraKhoTemp.setCttrakhoThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                        ctTraKhoTemp.setCttrakhoNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                        ctTraKhoTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                        ctTraKhoTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                        ctTraKhoTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                        ctTraKhoTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                        listCTT.set(vitriThaythe, ctTraKhoTemp);

                        //Thay the maLK moi, gia thuoc moi cho chi tiet phieu du tru
                        int j = 0;
                        for(CtPhieuDt ctPDT:listCtPDT){
                            if(ctPDT.getCtdtMalk().equals(maLK)){
                                ctPDT.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                                ctPDT.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                                listCtPDT.set(j, ctPDT);
                            }
                            j++;
                        }
                    }
                }
                i++;
            }
        }

        ptk.setPhieudtMa(pdt);
        if (ptk.getPhieutrakhoMa() != null && !ptk.getPhieutrakhoMa().equals("")) {
            getEm().merge(ptk);
        } else {
            ptk.setPhieutrakhoMa(Utils.maPhieuTra(getEm()));
            getEm().persist(ptk);
        }

        try {
            //***** cap nhat lai phieu du tru, cho biet phieu nay da duoc xuat roi hay tra roi
            System.out.println("-----" + pdt);
            pdt.setPhieudtDaXuat(true);
            phieuDuTrufacade.edit(pdt);
            //******
            for (CtTraKho cttk : listCTT) {
                cttk.setPhieutrakhoMa(ptk);
                Double soluongTra = cttk.getCttrakhoSoluong();//So luong tra
                String malienket = cttk.getCttrakhoMalk();
                DmKhoa kho = ptk.getDmkhoaTra();
                System.out.println("khoa tra:" + kho);
                //get ton kho hien tai cua khoa tra
                TonKho tonkhoHT = tonkhoFacade.getTonKhoHienTai(malienket, kho.getDmkhoaMaso());
                if(tonkhoHT != null && tonkhoHT.getTonkhoTon().doubleValue() < soluongTra.doubleValue()){
                    result = cttk.getDmthuocMaso().getDmthuocMa()+"-"+tonkhoHT.getTonkhoTon();
                    getContext().setRollbackOnly();
                    return result;
                }
                TonKho newtknhap = (TonKho) BeanUtils.cloneBean(tonkhoHT);//copyTonkho(tonkhoHT);
                newtknhap.setTonkhoMa(null);
                newtknhap.setTonkhoNhap(new Double(0));
                newtknhap.setTonkhoTra(soluongTra);
                newtknhap.setTonkhoXuat(new Double(0));
                newtknhap.setDmkhoaMaso(ptk.getDmkhoaNhan());
                

                TonKho newtkxuat = (TonKho) BeanUtils.cloneBean(tonkhoHT);//copyTonkho(tonkhoHT);
                newtkxuat.setTonkhoMa(null);
                newtkxuat.setTonkhoTra(new Double(0));
                newtkxuat.setTonkhoNhap(new Double(0));
                newtkxuat.setTonkhoXuat(soluongTra);
                newtkxuat.setDmkhoaMaso(ptk.getDmkhoaTra());
                

                tonkhoFacade.insertTonKho(newtknhap);
                tonkhoFacade.insertTonKho(newtkxuat);

                cttk.setCttrakhoLo(tonkhoHT.getTonkhoLo());
                cttk.setCttrakhoNamnhap(tonkhoHT.getTonkhoNamnhap());
                cttk.setCttrakhoNamhandung(tonkhoHT.getTonkhoNamhandung());
                cttk.setCttrakhoThanghandung(tonkhoHT.getTonkhoThanghandung());
                cttk.setCttrakhoNgayhandung(tonkhoHT.getTonkhoNgayhandung());
                cttk.setDmnctMaso(tonkhoHT.getDmnctMaso(true));
                cttk.setDmnguonkinhphiMaso(tonkhoHT.getDmnguonkinhphiMaso(true));
                cttk.setDmnhasanxuatMaso(tonkhoHT.getDmnhasanxuatMaso(true));
                cttk.setDmquocgiaMaso(tonkhoHT.getDmquocgiaMaso(true));
                cttk.setTonkhoMa(newtknhap.getTonkhoMa());
                cttk.setCttrakhoNgaygiocn(new Date());
                if (cttk.getCttrakhoMa() != null) {
                    getEm().merge(cttk);
                } else {
                    
                    
                    getEm().persist(cttk);
                }
            } // end  for (CtTraKho ctxk : listCTT)
            //update lai ct phieu du tru voi ma lien ket moi neu co thay the
            for(CtPhieuDt ctPDT:listCtPDT){
                ctPDTFacade.edit(ctPDT);
            }
            result = ptk.getPhieutrakhoMa();
        } catch (Exception ex) {
            getContext().setRollbackOnly();
            ex.printStackTrace();
            System.out.println("Loi trong TraPhieuDuTru(): " + ex);
            throw ex;
        }
        System.out.print("-------------End: updatePhieuDTTuTrucTraKho----------------");
        return result;
    }

    public PhieuTraKho findByPhieuDuTruMa(String phieudttraMa, Integer dmKhoNhanMaso){
        phieudttraMa = Utils.formatMaPhieu(phieudttraMa);
        String sSQL = "SELECT object(o) FROM PhieuTraKho o WHERE o.phieudtMa.phieudtMa = :phieudtMa and o.dmkhoaNhan.dmkhoaMaso = :dmkhoaMaso";
        Query q = getEm().createQuery(sSQL);
        q.setParameter("phieudtMa", phieudttraMa);
        q.setParameter("dmkhoaMaso", dmKhoNhanMaso);
        List<PhieuTraKho> list = q.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    private String kiemTraTruocKhiTraHangTheoPhieuDuTru(List<CtTraKho> listCTT, Integer khoMaso, String priority) {
        String ketqua = "";
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(getEm());
        if (listCTT != null && listCTT.size() > 0) {
            for (CtTraKho ctTK : listCTT) {
                String maLK = ctTK.getCttrakhoMalk();
                Integer thuocMaso = ctTK.getDmthuocMaso(true).getDmthuocMaso();

                List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, khoMaso, priority);
                TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, khoMaso);
                Double soluongCanTra = ctTK.getCttrakhoSoluong();
                Double soluongTonLoHienTai = 0.0;
                if(tonLoHienTai != null){
                    soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                }
                //Lay don vi tinh ten de hien thi len cau thong bao neu het thuoc
                String donVi = "";
                if (ctTK.getDmthuocMaso().getDmdonvitinhMaso() != null) {
                    DieuTriUtilFacade dieuTriUtilFacade = new DieuTriUtilFacade();
                    dieuTriUtilFacade.setEm(getEm());
                    Object obj = dieuTriUtilFacade.findByMaSo(ctTK.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
                    if (obj != null) {
                        DmDonViTinh dmDonViTinh = (DmDonViTinh) obj;
                        donVi = dmDonViTinh.getDmdonvitinhTen();
                    }
                }

                boolean isSLTonDuTra = false;
                if(soluongCanTra > soluongTonLoHienTai){
                    if(listTonKhoHT != null){
                        for(TonKho tonkho:listTonKhoHT){
                            if(soluongCanTra <= tonkho.getTonkhoTon()){
                                isSLTonDuTra = true;
                                break;
                            }
                        }
                    }
                }else{
                    isSLTonDuTra = true;
                }

                if(!isSLTonDuTra){
                    String temp = "Mặt hàng " + ctTK.getDmthuocMaso().getDmthuocMa() + " thiếu " + (soluongCanTra.doubleValue() - soluongTonLoHienTai.doubleValue()) + " " + donVi;
                    ketqua += "." + temp;
                }
            }
        }
        return ketqua;
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
}


