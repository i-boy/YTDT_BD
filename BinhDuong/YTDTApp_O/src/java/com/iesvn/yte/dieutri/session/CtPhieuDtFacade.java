package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.dieutri.entity.CtPhieudutruTonghop;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmDonViTinh;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class CtPhieuDtFacade implements CtPhieuDtFacadeLocal, CtPhieuDtFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private ThuocPhongKhamFacadeLocal tpkFacade;
    @EJB
    private ThuocNoiTruFacadeLocal tntFacade;

    public void create(CtPhieuDt ctPhieuDt) {
        em.persist(ctPhieuDt);
    }

    public void edit(CtPhieuDt ctPhieuDt) {
        em.merge(ctPhieuDt);
    }

    public void remove(CtPhieuDt ctPhieuDt) {
        em.remove(em.merge(ctPhieuDt));
    }

    public CtPhieuDt find(Object id) {
        return em.find(com.iesvn.yte.dieutri.entity.CtPhieuDt.class, id);
    }

    public List<CtPhieuDt> findAll() {
        return em.createQuery("select object(o) from CtPhieuDt as o").getResultList();
    }

    public List<CtPhieuDt> findByPhieuDuTruMa(String phieudtMa) {
        phieudtMa = Utils.formatMaPhieu(phieudtMa);
        String sSQL = "select object(o) from CtPhieuDt as o where o.phieudtMa.phieudtMa like :phieudtMa order by o.dmthuocMaso.dmthuocTen";
        phieudtMa = Utils.formatMaPhieu(phieudtMa);
        return em.createQuery(sSQL).setParameter("phieudtMa", phieudtMa).getResultList();
    }

    public List<ThuocNoiTru> findByPhieuDuTruMaAndMaLK(String phieudtMa, String maLK) {
        phieudtMa = Utils.formatMaPhieu(phieudtMa);
        return em.createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruMalk =:thuocnoitruMalk and o.thuocnoitruMaPhieuDT like :thuocnoitruMaPhieuDT").setParameter("thuocnoitruMalk", maLK).setParameter("thuocnoitruMaPhieuDT", phieudtMa).getResultList();
    }

    /**
     *  Tim theo phieu du tru, phan biet giua phieu du tru cua tu truc va PDT cua Khoa Phong
     *
     **/
    public PhieuDuTru findByPhieuDuTruPhanBiet(String maPhieu, Integer phanBiet) {

        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuDuTru c ";
        sQuery += " WHERE  c.phieudtMa like :maPhieu AND c.phieudtPhanBiet = :phanBiet";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("phanBiet", phanBiet);

        try {
            List<PhieuDuTru> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PhieuDuTru findByPhieuDuTruPhanBietKho(String maPhieu, Integer phanBiet, Integer khoNhanMa) {

        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuDuTru c ";
        sQuery += " WHERE  c.phieudtMa like :maPhieu AND c.phieudtMakho.dmkhoaMaso = :dmkhoaMaso AND c.phieudtPhanBiet = :phanBiet";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("phanBiet", phanBiet);
        q.setParameter("dmkhoaMaso", khoNhanMa);
        try {
            List<PhieuDuTru> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PhieuDuTru findByPhieuDuTruKhoXuatPhanBiet(String maPhieu, Integer phanBiet, Integer khoXuatMa) {
        maPhieu = Utils.formatMaPhieu(maPhieu);
        Query q;
        String sQuery = "SELECT object(c) FROM PhieuDuTru c ";
        sQuery += " WHERE  c.phieudtMa like :maPhieu AND c.phieudtMakho.dmkhoaMaso = :dmkhoaMaso AND c.phieudtPhanBiet = :phanBiet";
        q = getEm().createQuery(sQuery);
        q.setParameter("maPhieu", maPhieu);
        q.setParameter("phanBiet", phanBiet);
        q.setParameter("dmkhoaMaso", khoXuatMa);

        try {
            List<PhieuDuTru> list = q.getResultList();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    //  Neu  TNT co' : status = 2 , phieu du tru ma = @maphieudutru  ->return true
    //
    //
    private boolean daXuatThuocTheoPhieuDT(String maPhieuDuTru) {
        boolean ketqua = false;

        Query q = em.createQuery("select object(o) from ThuocNoiTru as o Where o.thuocnoitruStatus = '2' and o.thuocnoitruMaPhieuDT = :thuocnoitruMaPhieuDT");
        q.setParameter("thuocnoitruMaPhieuDT", maPhieuDuTru);
        List<ThuocNoiTru> list = q.getResultList();
        if (list != null && list.size() > 0) {
            ketqua = true;
        }
        if (ketqua == false) {
            q = em.createQuery("select object(o) from PhieuDuTru as o Where o.phieudtMa.phieudtMa = :phieudtMa");
            q.setParameter("phieudtMa", maPhieuDuTru);
            list = q.getResultList();
            if (list != null && list.size() > 0) {
                ketqua = true;
            }
        }

        return ketqua;
    }
    //Tho add
    //Neu PHIEU_DU_TRU.PHIEUDT_DAXUAT = 1 thi khong duoc huy phieu nay
    //Neu = null thi duoc huy phieu
    public boolean daXuatThuocTheoPhieuDT_New(String maPhieuDuTru) {
        boolean ketqua = false;
        String sSQL = "select object(o) from PhieuDuTru as o where o.phieudtMa = :phieudtMa and o.phieudtDaXuat = 1";
        Query q = em.createQuery(sSQL);
        q.setParameter("phieudtMa", maPhieuDuTru);
        List<PhieuDuTru> list = q.getResultList();
        if (list != null && list.size() > 0) {
            ketqua = true;
        }
        return ketqua;
    }

    private boolean daXuatThuocTheoPhieuDTTra(String maPhieuDuTru) {
        boolean ketqua = false;

        Query q = em.createQuery("select object(o) from ThuocNoiTru as o where o.thuocnoitruMaphieupdttra = :thuocnoitruMaphieupdttra");
        q.setParameter("thuocnoitruMaphieupdttra", maPhieuDuTru);
        List<ThuocNoiTru> list = q.getResultList();
        if (list == null || list.size() == 0) {
            ketqua = true;
        }
        return ketqua;
    }

    //Tho add
    public String huyPhieuDuTruTra_New(String maPhieuDuTru) {
        if (daXuatThuocTheoPhieuDT_New(maPhieuDuTru) == true) {
            return "DT";
        }
        List<CtPhieuDt> ctList = findByPhieuDuTruMa(maPhieuDuTru);
        if (ctList != null && ctList.size() > 0) {
            PhieuDuTru phieuDT = ctList.get(0).getPhieudtMa();
            for (CtPhieuDt ctphieu : ctList) {
                em.remove(ctphieu);
            }
            em.remove(phieuDT);
            //Reset lai field THUOCNOITRU_MAPHIEUPDTTRA va status lai ve = 3 (da tao phieu du tru linh va da linh)
             String sSQL = "select object(o) from ThuocNoiTru as o where o.thuocnoitruMaphieupdttra = :thuocnoitruMaphieupdttra";
            Query q = em.createQuery(sSQL);
            q.setParameter("thuocnoitruMaphieupdttra", maPhieuDuTru);
            List<ThuocNoiTru> list = q.getResultList();
            if(list != null && list.size()>0){
                for(ThuocNoiTru tnt :list){
                    tnt.setThuocnoitruMaphieupdttra(null);
                    tnt.setThuocnoitruStatus("3");
                    em.merge(tnt);
                }
            }
        }
        return maPhieuDuTru;
    }

    public String huyPhieuDuTruTra(String maPhieuDuTru) {
        if (daXuatThuocTheoPhieuDTTra(maPhieuDuTru) == true) {
            return "";
        }
        List<CtPhieuDt> ctList = findByPhieuDuTruMa(maPhieuDuTru);
        if (ctList != null && ctList.size() > 0) {
            PhieuDuTru phieuDT = ctList.get(0).getPhieudtMa();
            for (CtPhieuDt ctphieu : ctList) {
                em.remove(ctphieu);
            }
            em.remove(phieuDT);
        }
        return maPhieuDuTru;
    }
    //Tho add
    public String huyPhieuDuTru_New(String maPhieuDuTru) {
        if (daXuatThuocTheoPhieuDT_New(maPhieuDuTru) == true) {
            return "DX";
        }
        List<CtPhieuDt> ctList = findByPhieuDuTruMa(maPhieuDuTru);
        if (ctList != null && ctList.size() > 0) {
            PhieuDuTru phieuDT = ctList.get(0).getPhieudtMa();
            for (CtPhieuDt ctphieu : ctList) {
                em.remove(ctphieu);
            }
            em.remove(phieuDT);
            //Can reset lai bang thuoc noi tru voi field THUOCNOITRU_MAPHIEUDT = maPhieuDuTru set lai = null de lap lai phieu du tru linh khac
            String sSQL = "select object(o) from ThuocNoiTru as o where o.thuocnoitruMaPhieuDT = :thuocnoitruMaPhieuDT";
            Query q = em.createQuery(sSQL);
            q.setParameter("thuocnoitruMaPhieuDT", maPhieuDuTru);
            List<ThuocNoiTru> list = q.getResultList();
            if(list != null && list.size()>0){
                for(ThuocNoiTru tnt :list){
                    tnt.setThuocnoitruMaPhieuDT(null);
                    tnt.setThuocnoitruStatus("0");
                    em.merge(tnt);
                }
            }
        }
        return maPhieuDuTru;
    }

    public String huyPhieuDuTru(String maPhieuDuTru) {
        if (daXuatThuocTheoPhieuDT_New(maPhieuDuTru) == true) {
            return "";
        }
        List<CtPhieuDt> ctList = findByPhieuDuTruMa(maPhieuDuTru);
        if (ctList != null && ctList.size() > 0) {
            PhieuDuTru phieuDT = ctList.get(0).getPhieudtMa();
            for (CtPhieuDt ctphieu : ctList) {
                em.remove(ctphieu);
            }
            em.remove(phieuDT);
        }
        return maPhieuDuTru;
    }

    /* Tho update lai:
     * get so luong ton hien tai cua ma lien ket thuoc do
     * get so luong thuoc can xuat cua lo thuoc
     *    Kiem tra:
     *      + Neu so luong can xuat > so luong ton hien tai cua lo thuoc do thi kiem tra voi lo khac theo thu tu uu tien, neu lo ke tiep khong co so luong thoa so luong can xuat thi bao loi
     *      + Nguoc lai thi cho xuat binh thuong
     */
    private String kiemTraTruocKhiTaoPhieuDuTru(List<CtPhieuDt> listCTPhieuDuTru, PhieuDuTru phieuDuTru, String priority) {
        System.out.println("Begin ---kiemTraTruocKhiTaoPhieuDuTru---");
        String ketqua = "";
        TonKhoFacade tkFacade = new TonKhoFacade();
        tkFacade.setEm(em);
        if (listCTPhieuDuTru != null && listCTPhieuDuTru.size() > 0) {
            for (CtPhieuDt ctPDT : listCTPhieuDuTru) {
                Integer thuocMaso = ctPDT.getDmthuocMaso(true).getDmthuocMaso();
                String maLK = ctPDT.getCtdtMalk();
                List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, phieuDuTru.getPhieudtMakho().getDmkhoaMaso(), priority);
                TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
                Double soluongTonLoHienTai = 0.0;
                if(tonLoHienTai != null){
                    soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                }
                Double soluongCanXuat = ctPDT.getCtdtSoluong();
                System.out.println("Ma lien ket: "+ maLK);
                System.out.println("Kho: " + phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
                System.out.println("So luong Can Xuat: " + soluongCanXuat);
                System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                System.out.println("listTonKhoHT: " + listTonKhoHT);
                //Lay don vi tinh ten de hien thi len cau thong bao neu het thuoc
                String donVi = "";
                if (ctPDT.getDmthuocMaso().getDmdonvitinhMaso() != null) {
                    DieuTriUtilFacade dieuTriUtilFacade = new DieuTriUtilFacade();
                    dieuTriUtilFacade.setEm(em);
                    Object obj = dieuTriUtilFacade.findByMaSo(ctPDT.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
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
                            }
                        }
                    }
                }else{
                    isSLTonDuCap = true;
                }

                if(!isSLTonDuCap){
                    String temp = "Mặt hàng " + ctPDT.getDmthuocMaso().getDmthuocMa() + " thiếu " + (soluongCanXuat.doubleValue() - soluongTonLoHienTai.doubleValue()) + " " + donVi;
                    ketqua += "." + temp;
                }
            }
        }
        System.out.println("End ---kiemTraTruocKhiTaoPhieuDuTru---");
        return ketqua;
    }

    public String capNhatPhieuDuTru(List<CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, PhieuDuTru phieuDuTru, String phieudtMa, String priority) {
        //        System.out.println("capNhatKetQuaMo:" + lstKqMo.size());
        String result = "";
        ArrayList<Integer> listNew = new ArrayList<Integer>();
        try{
            String ketquaKiemTra = kiemTraTruocKhiTaoPhieuDuTru(listCTPhieuDuTru, phieuDuTru, priority);
            if (!ketquaKiemTra.equals("")) {
                return ketquaKiemTra;
            }
            TonKhoFacade tkFacade = new TonKhoFacade();
            tkFacade.setEm(em);
            /*Thay doi dong listCTPhieuDuTru neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
             * Thay doi lo thuoc cho ca thuoc noi tru, listCTPhieuDuTru
             */
            int vitriThaythe = -1;
            int i = 0;
            for(CtPhieuDt ctPhieuDT : listCTPhieuDuTru){
                CtPhieuDt ctPhieuDTTemp = new CtPhieuDt();
                ctPhieuDTTemp = (CtPhieuDt)BeanUtils.cloneBean(ctPhieuDT);
                if (ctPhieuDT.getCtdtMa() == null) {
                    Double soluongCanXuat = ctPhieuDT.getCtdtSoluong();
                    String maLK = ctPhieuDT.getCtdtMalk();
                    Integer thuocMaso = ctPhieuDT.getDmthuocMaso(true).getDmthuocMaso();

                    List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, phieuDuTru.getPhieudtMakho().getDmkhoaMaso(), priority);
                    TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
                    Double soluongTonLoHienTai = 0.0;
                    if(tonLoHienTai != null){
                        soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                    }
                    TonKho tonkhoThayThe = new TonKho();
                    System.out.println("Ma lien ket: "+ maLK);
                    System.out.println("So luong Can Xuat: " + soluongCanXuat);
                    System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                    if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                        System.out.println("So luong ton hien tai khong du de xuat, phai tim chon lo khac");
                        for(TonKho tonkho:listTonKhoHT){
                            if(soluongCanXuat <= tonkho.getTonkhoTon()){
                                tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                                vitriThaythe = i;
                                break;
                            }
                        }
                        System.out.println("Vi tri thay the: "+ vitriThaythe);
                        if(vitriThaythe > -1){
                            ctPhieuDTTemp.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                            ctPhieuDTTemp.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                            ctPhieuDTTemp.setCtdtLo(tonkhoThayThe.getTonkhoLo());
                            ctPhieuDTTemp.setCtdtNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                            ctPhieuDTTemp.setCtdtNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                            ctPhieuDTTemp.setCtdtThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                            ctPhieuDTTemp.setCtdtNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                            ctPhieuDTTemp.setCtdtSodangky(tonkhoThayThe.getTonkhoSodangky());
                            ctPhieuDTTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                            ctPhieuDTTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                            ctPhieuDTTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                            ctPhieuDTTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                            listCTPhieuDuTru.set(vitriThaythe, ctPhieuDTTemp);

                            //thay the maLK cua cac dong trong listTNT thanh maLKThayThe voi don gia thay the di kem neu don gia > 0 vi don gia dang = 0 la dung cho doi tuong mien phi
                            int j = 0;
                            for(ThuocNoiTru tnt:listTNT){
                                ThuocNoiTru tntTemp = new ThuocNoiTru();
                                tntTemp = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
                                if(tnt.getThuocnoitruMalk().equals(maLK)){
                                    tntTemp.setThuocnoitruMalk(tonkhoThayThe.getTonkhoMalienket());
                                    if(tnt.getThuocnoitruDongia() > 0){
                                        System.out.println("Thay the don gia thuoc: "+ tonkhoThayThe.getTonkhoDongia());
                                        tntTemp.setThuocnoitruDongia(tonkhoThayThe.getTonkhoDongia());
                                        tntTemp.setThuocnoitruDongiabh(tonkhoThayThe.getTonkhoDongia());
                                        tntTemp.setThuocnoitruNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                                        tntTemp.setThuocnoitruNamhd(tonkhoThayThe.getTonkhoNamhandung());
                                        tntTemp.setThuocnoitruThanghd(tonkhoThayThe.getTonkhoThanghandung());
                                        tntTemp.setThuocnoitruNgayhd(tonkhoThayThe.getTonkhoNgayhandung());
                                        tntTemp.setThuocnoitruSodangky(tonkhoThayThe.getTonkhoSodangky());
                                        tntTemp.setThuocnoitruNguon(tonkhoThayThe.getDmnctMaso(true));
                                        tntTemp.setThuocnoitruQuocgia(tonkhoThayThe.getDmquocgiaMaso(true));
                                        tntTemp.setThuocnoitruHangsx(tonkhoThayThe.getDmnhasanxuatMaso(true));
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

            if (phieuDuTru.getPhieudtMa() != null && !phieuDuTru.getPhieudtMa().equals("")) {
                Utils.setInfor(phieuDuTru, em);
                em.merge(phieuDuTru);
            } else {
                Utils.setInfor(phieuDuTru, em);
                phieuDuTru.setPhieudtMa(Utils.maPhieuDuTru(em));
                em.persist(phieuDuTru);
            }
            result = phieuDuTru.getPhieudtMa();

            //Cong don cac dong thuoc neu co cung malk truoc khi ghi nhan vao DB
            List<CtPhieuDt> listCTPhieuDT = new ArrayList<CtPhieuDt>();
            HashMap<String,CtPhieuDt> hsmCtPDT = new HashMap<String,CtPhieuDt>();

            for(i = 0; i< listCTPhieuDuTru.size(); i++){
                CtPhieuDt ctPhieuDT = (CtPhieuDt)BeanUtils.cloneBean(listCTPhieuDuTru.get(i));
                String maLK = ctPhieuDT.getCtdtMalk();
                if(i == 0){
                    hsmCtPDT.put(maLK, ctPhieuDT);
                }else{
                    CtPhieuDt ct = hsmCtPDT.get(maLK);
                    if(ct != null){//giong ma lk
                        Double soluong = ct.getCtdtSoluong() + ctPhieuDT.getCtdtSoluong();
                        ct.setCtdtSoluong(soluong);
                        hsmCtPDT.put(maLK, ct);
                    }else{
                        hsmCtPDT.put(maLK, ctPhieuDT);
                    }
                }
            }
        
            if(hsmCtPDT != null && hsmCtPDT.size() > 0){
                java.util.Set set = hsmCtPDT.entrySet();
                Iterator iter = set.iterator();
                while(iter.hasNext()){
                    Map.Entry me = (Map.Entry)iter.next();
                    CtPhieuDt ctPhieuDT = (CtPhieuDt)me.getValue();
                    listCTPhieuDT.add(ctPhieuDT);
                }
            }
            for (CtPhieuDt ctPhieuDT : listCTPhieuDT) {
                if (ctPhieuDT.getCtdtMa() != null) {
                    Utils.setInfor(ctPhieuDT, em);
                    em.merge(ctPhieuDT);
                } else {
                    Utils.setInfor(ctPhieuDT, em);
                    ctPhieuDT.setPhieudtMa(phieuDuTru);
                    em.persist(ctPhieuDT);
                    listNew.add(ctPhieuDT.getCtdtMa());
                }
            }
            //Remove cac dong thuoc cu da luu duoi DB ma khac
            if (phieudtMa != null && !phieudtMa.equals("")) {
                List<CtPhieuDt> list = findByPhieuDuTruMa(phieudtMa);
                if (list != null && list.size() > 0) {
                    for (CtPhieuDt ctPhieu : list) {
                        if (!contain(listCTPhieuDuTru, ctPhieu)) {
                            if (containNew(listNew, ctPhieu.getCtdtMa())) {
                                continue;
                            }
                            em.remove(ctPhieu);
                        }
                    }
                }
            }

            if (listTNT != null) {
                for (ThuocNoiTru tnt : listTNT) {
                    tnt.setThuocnoitruMaPhieuDT(result);
                    System.out.println("Ma lien ket: "+ tnt.getThuocnoitruMalk());
                    System.out.println("Don gia: "+ tnt.getThuocnoitruDongia());
                    tnt.setThuocnoitruStatus("1");//Da lap phieu du tru, chua xuat
                    em.merge(tnt);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public String capNhatPhieuDuTruTra(List<CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, PhieuDuTru phieuDuTru, String phieudtMa) {
        //        System.out.println("capNhatKetQuaMo:" + lstKqMo.size());
        String result = "";
        ArrayList<Integer> listNew = new ArrayList<Integer>();
        if (phieuDuTru.getPhieudtMa() != null && !phieuDuTru.getPhieudtMa().equals("")) {
            Utils.setInfor(phieuDuTru, em);
            em.merge(phieuDuTru);
        } else {
            Utils.setInfor(phieuDuTru, em);
            phieuDuTru.setPhieudtMa(Utils.maPhieuDuTru(em));
            em.persist(phieuDuTru);

        }
        result = phieuDuTru.getPhieudtMa();
        for (CtPhieuDt ctPhieuDT : listCTPhieuDuTru) {
            if (ctPhieuDT.getCtdtMa() != null) {
                Utils.setInfor(ctPhieuDT, em);
                em.merge(ctPhieuDT);
            } else {
                Utils.setInfor(ctPhieuDT, em);
                ctPhieuDT.setPhieudtMa(phieuDuTru);
                em.persist(ctPhieuDT);
                listNew.add(ctPhieuDT.getCtdtMa());
            }
        }
        if (phieudtMa != null && !phieudtMa.equals("")) {
            List<CtPhieuDt> list = findByPhieuDuTruMa(phieudtMa);
            if (list != null && list.size() > 0) {
                for (CtPhieuDt ctPhieu : list) {
                    if (!contain(listCTPhieuDuTru, ctPhieu)) {
                        if (containNew(listNew, ctPhieu.getCtdtMa())) {
                            continue;
                        }
                        em.remove(ctPhieu);
                    }
                }
            }
        }
        if (listTNT != null) {
            for (ThuocNoiTru tnt : listTNT) {
                tnt.setThuocnoitruMaphieupdttra(result);
                tnt.setThuocnoitruStatus("4");//Da lap xong phieu du tru - Tho add
                em.merge(tnt);
            }
        }
        return result;
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
    private boolean contain(List<CtPhieuDt> lstKqMo, CtPhieuDt ctPhieuDuTru) {
        for (CtPhieuDt ct : lstKqMo) {
            if (ctPhieuDuTru.getCtdtMa() != null && ct.getCtdtMa() != null && ctPhieuDuTru.getCtdtMa().intValue() == ct.getCtdtMa().intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     *  INSERT VA UPDATE Phieu Du Tru, CT Phieu Du Tru
     *  Triet code, Tho update - 29/03/2011
     * =====================================================
     **/
    public String updatePhieuDuTru(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel) {
        String result = "";
        try {
            // Truong hop cap nhat
            if (objPhieuDuTru.getPhieudtMa() != null && !"".equals(objPhieuDuTru.getPhieudtMa())) {
                getEm().merge(objPhieuDuTru);
                System.out.println("UPDATE SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDt(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel);
                result = objPhieuDuTru.getPhieudtMa();
            } // Truong hop them moi
            else {
                String sMaPhieu = Utils.maPhieuDuTru(getEm());
                objPhieuDuTru.setPhieudtMa(sMaPhieu);
                getEm().persist(objPhieuDuTru);
                System.out.println("INSERT SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDt(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel);
                result = objPhieuDuTru.getPhieudtMa();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public void updateCtPhieuDt(PhieuDuTru objPhieuTraKho, List<CtPhieuDt> listCtTraKho, List<CtPhieuDt> listCtDtLanhThuocDel) {
        String sListIdDetail = "";

        try {
            if(listCtDtLanhThuocDel.size()>0){
                for (CtPhieuDt ctPDt : listCtDtLanhThuocDel) {
                    if (ctPDt.getCtdtMa() != null) {
                        System.out.println("-----Delete Chi tiet phieu du tru: " + ctPDt.getCtdtMa());
                        CtPhieuDt ctPDtDelete = em.find(CtPhieuDt.class, ctPDt.getCtdtMa());
                        if (ctPDtDelete != null){
                             em.remove(ctPDtDelete);
                        }
                    }
                }
            }
            for (int i = 0; i < listCtTraKho.size(); i++) {
                listCtTraKho.get(i).setPhieudtMa(objPhieuTraKho);
            }

            for (int i = 0; i < listCtTraKho.size(); i++) {
                CtPhieuDt obj = listCtTraKho.get(i);
                if (obj != null) {
                    if (sListIdDetail.equals("")) {
                        sListIdDetail += obj.getCtdtMa();
                    } else {
                        sListIdDetail += "," + obj.getCtdtMa();
                    }
                }
            }
            this.removeItem(sListIdDetail, objPhieuTraKho.getPhieudtMa());

            for (CtPhieuDt obj : listCtTraKho) {
                if (obj.getCtdtMa() != null) {
                    getEm().merge(obj);
                    System.out.println("UPDATE SUCCESS CtPhieuDt");
                } else {
                    getEm().persist(obj);
                    System.out.println("UPDATE SUCCESS CtPhieuDt");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        //getContext().setRollbackOnly();
        }
    }

    //Phieu linh thuoc du tru tu truc ngoai tru
    public String updatePhieuDuTruLinhNGT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority) {
        String result = "";
        try {
            String ketquaKiemTra = kiemTraTruocKhiTaoPhieuDuTru(listCtPhieuDt, objPhieuDuTru, priority);
            if (!ketquaKiemTra.equals("")) {
                return ketquaKiemTra;
            }
            // Truong hop cap nhat
            if (objPhieuDuTru.getPhieudtMa() != null && !"".equals(objPhieuDuTru.getPhieudtMa())) {
                getEm().merge(objPhieuDuTru);
                System.out.println("UPDATE SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDtLinhNGT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocPhongKhamMa, ctdt_matpk, priority);
                result = objPhieuDuTru.getPhieudtMa();
            } // Truong hop them moi
            else {
                String sMaPhieu = Utils.maPhieuDuTru(getEm());
                objPhieuDuTru.setPhieudtMa(sMaPhieu);
                getEm().persist(objPhieuDuTru);
                System.out.println("INSERT SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDtLinhNGT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocPhongKhamMa, ctdt_matpk, priority);
                result = objPhieuDuTru.getPhieudtMa();

                //update thuocphongkham_maphieudt = objPhieuDtLinh.getPhieudtMa() where each listThuocPhongKhamMa
                for(int i = 0; i<listThuocPhongKhamMa.size();i++){
                    ThuocPhongKham tpk = tpkFacade.find(listThuocPhongKhamMa.get(i));
                    tpk.setThuocphongkhamMaPhieuDT(objPhieuDuTru.getPhieudtMa());
                    tpk.setThuocphongkhamNgaygiocn(new Date());
                    tpk.setThuocphongkhamNhanviencn(objPhieuDuTru.getDtdmnhanvienCn());
                    getEm().merge(tpk);
                    System.out.println("UPDATE MaPhieuDT SUCCESS in ThuocPhongKham");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public void updateCtPhieuDtLinhNGT(PhieuDuTru objPhieuDtLinh, List<CtPhieuDt> listCtPhieuDtLinh, List<CtPhieuDt> listCtDtLanhThuocDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority) {
        String sListIdDetail = "";
        try {
            //set thuocphongkham_maphieudt = null ung voi cac ctDt trong bang cthieudutrutonghop.CtDtMa get CtDuTruPhongKham.danhsachmaso
            if(listCtDtLanhThuocDel.size()>0){
                for (CtPhieuDt ctPDt : listCtDtLanhThuocDel) {
                    if (ctPDt.getCtdtMa() != null) {
                        CtPhieuDt ctPDtDelete = em.find(CtPhieuDt.class, ctPDt.getCtdtMa());
                        if (ctPDtDelete != null){
                            //select danh sach tpk ma where ctphieudt ma trong bang CtPhieudutruTonghop: duoc danh sach,
                            //tach chuoi de where thuoc phong kham ma, set thuocphongkham_maphieudt = null
                            Query q = getEm().createQuery("SELECT ct.danhsachMaso FROM CtPhieudutruTonghop ct WHERE ct.ctdtMa = :ctdtMa and ct.loai = 'NGT'");
                            q.setParameter("ctdtMa", ctPDtDelete.getCtdtMa());
                            String danhsachMaTPK = (String)q.getSingleResult();
                            System.out.println("danhsachMaTPK: "+danhsachMaTPK);
                            if(danhsachMaTPK != null){
                                int numRecordUpdated = 0;
                                String sSQL = "UPDATE FROM ThuocPhongKham c SET thuocphongkhamMaPhieuDT = null WHERE c.thuocphongkhamMa IN (" + danhsachMaTPK + ")  ";
                                q = getEm().createQuery(sSQL);
                                numRecordUpdated = q.executeUpdate();
                            }
                            System.out.println("-----Delete ctdt ma va danh sach tpk ma trong bang CtPhieudutruTonghop: " + ctPDt.getCtdtMa());
                            q = getEm().createQuery("DELETE FROM CtPhieudutruTonghop c WHERE c.ctdtMa = :ctdtMa and c.loai = 'NGT'");
                            q.setParameter("ctdtMa", ctPDtDelete.getCtdtMa());
                            int numRecordDeleted = q.executeUpdate();
                        }
                    }
                }
            }
            if(listCtDtLanhThuocDel.size()>0){
                for (CtPhieuDt ctPDt : listCtDtLanhThuocDel) {
                    if (ctPDt.getCtdtMa() != null) {
                        System.out.println("-----Delete Chi tiet phieu du tru: " + ctPDt.getCtdtMa());
                        CtPhieuDt ctPDtDelete = em.find(CtPhieuDt.class, ctPDt.getCtdtMa());
                        if (ctPDtDelete != null){
                             em.remove(ctPDtDelete);
                        }
                    }
                }
            }
            for (int i = 0; i < listCtPhieuDtLinh.size(); i++) {
                listCtPhieuDtLinh.get(i).setPhieudtMa(objPhieuDtLinh);
            }

            for (int i = 0; i < listCtPhieuDtLinh.size(); i++) {
                CtPhieuDt obj = listCtPhieuDtLinh.get(i);
                if (obj != null) {
                    if (sListIdDetail.equals("")) {
                        sListIdDetail += obj.getCtdtMa();
                    } else {
                        sListIdDetail += "," + obj.getCtdtMa();
                    }
                }
            }
            this.removeItem(sListIdDetail, objPhieuDtLinh.getPhieudtMa());

            TonKhoFacade tkFacade = new TonKhoFacade();
            tkFacade.setEm(em);
            /*Thay doi dong listCtPhieuDtLinh neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
             * Thay doi lo thuoc cho listCtPhieuDtLinh
             */
            int vitriThaythe = -1;
            int i = 0;
            for(CtPhieuDt ctPhieuDT : listCtPhieuDtLinh){
                CtPhieuDt ctPhieuDTTemp = new CtPhieuDt();
                ctPhieuDTTemp = (CtPhieuDt)BeanUtils.cloneBean(ctPhieuDT);
                if (ctPhieuDT.getCtdtMa() == null) {
                    Double soluongCanXuat = ctPhieuDT.getCtdtSoluong();
                    String maLK = ctPhieuDT.getCtdtMalk();
                    Integer thuocMaso = ctPhieuDT.getDmthuocMaso(true).getDmthuocMaso();

                    List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, objPhieuDtLinh.getPhieudtMakho().getDmkhoaMaso(), priority);
                    TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, objPhieuDtLinh.getPhieudtMakho().getDmkhoaMaso());
                    Double soluongTonLoHienTai = 0.0;
                    if(tonLoHienTai != null){
                        soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                    }
                    TonKho tonkhoThayThe = new TonKho();
                    
                    System.out.println("Ma lien ket: "+ maLK);
                    System.out.println("So luong Can Xuat: " + soluongCanXuat);
                    System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                    if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                        System.out.println("So luong ton hien tai khong du de xuat, phai tim chon lo khac");
                        for(TonKho tonkho:listTonKhoHT){
                            if(soluongCanXuat <= tonkho.getTonkhoTon()){
                                tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                                vitriThaythe = i;
                                break;
                            }
                        }
                        System.out.println("Vi tri thay the: "+ vitriThaythe);
                        if(vitriThaythe > -1){
                            ctPhieuDTTemp.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                            ctPhieuDTTemp.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                            ctPhieuDTTemp.setCtdtLo(tonkhoThayThe.getTonkhoLo());
                            ctPhieuDTTemp.setCtdtNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                            ctPhieuDTTemp.setCtdtNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                            ctPhieuDTTemp.setCtdtThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                            ctPhieuDTTemp.setCtdtNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                            ctPhieuDTTemp.setCtdtSodangky(tonkhoThayThe.getTonkhoSodangky());
                            ctPhieuDTTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                            ctPhieuDTTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                            ctPhieuDTTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                            ctPhieuDTTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                            listCtPhieuDtLinh.set(vitriThaythe, ctPhieuDTTemp);
                        }
                    }
                    i++;
                }
            }

            for (i = 0; i<listCtPhieuDtLinh.size(); i++) {
                CtPhieuDt obj = listCtPhieuDtLinh.get(i);
                if (obj.getCtdtMa() != null) {
                    getEm().merge(obj);
                    System.out.println("UPDATE SUCCESS CtPhieuDt");
                } else {
                    getEm().persist(obj);
                    System.out.println("INSERT SUCCESS CtPhieuDt");
                    //insert list ma thuoc phong kham ung voi ct ma du tru vao bang cthieudutrutonghop
                    System.out.println("INSERT CTDTMa va danh sach TPK Ma in CtPhieudutruTonghop");
                    if(ctdt_matpk != null){
                        List<Integer> listMaTPK = ctdt_matpk.get(i);
                        String danhsachMaTPK = " ";
                        if(listMaTPK != null){
                            for(int j=0;j<listMaTPK.size();j++){
                                if(j == 0) {
                                    danhsachMaTPK += listMaTPK.get(j);
                                }else {
                                    danhsachMaTPK += "," + listMaTPK.get(j);
                                }
                            }
                            CtPhieudutruTonghop ctPhieudutruTonghop = new CtPhieudutruTonghop();
                            ctPhieudutruTonghop.setCtdtMa(obj.getCtdtMa());
                            ctPhieudutruTonghop.setDanhsachMaso(danhsachMaTPK);
                            ctPhieudutruTonghop.setLoai("NGT");
                            getEm().merge(ctPhieudutruTonghop);
                        }
                        System.out.println("INSERT CTDTMa va danh sach TPK Ma SUCCESS in CtPhieudutruTonghop");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        //getContext().setRollbackOnly();
        }
    }

    //Phieu linh thuoc du tru tu truc noi tru
    public String updatePhieuDuTruLinhNT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocNoiTruMa, HashMap<Integer,List> ctdt_matnt, String priority) {
        String result = "";
        try {
            String ketquaKiemTra = kiemTraTruocKhiTaoPhieuDuTru(listCtPhieuDt, objPhieuDuTru, priority);
            if (!ketquaKiemTra.equals("")) {
                return ketquaKiemTra;
            }
            // Truong hop cap nhat
            if (objPhieuDuTru.getPhieudtMa() != null && !"".equals(objPhieuDuTru.getPhieudtMa())) {
                getEm().merge(objPhieuDuTru);
                System.out.println("UPDATE SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDtLinhNT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocNoiTruMa, ctdt_matnt, priority);
                result = objPhieuDuTru.getPhieudtMa();
            } // Truong hop them moi
            else {
                String sMaPhieu = Utils.maPhieuDuTru(getEm());
                objPhieuDuTru.setPhieudtMa(sMaPhieu);
                getEm().persist(objPhieuDuTru);
                System.out.println("INSERT SUCCESS PhieuDuTru" + objPhieuDuTru.getPhieudtMa());

                updateCtPhieuDtLinhNT(objPhieuDuTru, listCtPhieuDt, listCtPhieuDtDel, listThuocNoiTruMa, ctdt_matnt, priority);
                result = objPhieuDuTru.getPhieudtMa();

                //update thuocnoitru_maphieudt = objPhieuDtLinh.getPhieudtMa() where each listThuocPhongKhamMa
                for(int i = 0; i<listThuocNoiTruMa.size();i++){
                    ThuocNoiTru tnt = tntFacade.find(listThuocNoiTruMa.get(i));
                    tnt.setThuocnoitruMaPhieuDT(objPhieuDuTru.getPhieudtMa());
                    tnt.setThuocnoitruNgaygiocn(new Date());
                    tnt.setThuocnoitruNhanviencn(objPhieuDuTru.getDtdmnhanvienCn());
                    getEm().merge(tnt);
                    System.out.println("UPDATE MaPhieuDT SUCCESS in ThuocNoiTru");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getContext().setRollbackOnly();
        }
        return result;
    }

    public void updateCtPhieuDtLinhNT(PhieuDuTru objPhieuDtLinh, List<CtPhieuDt> listCtPhieuDtLinh, List<CtPhieuDt> listCtDtLanhThuocDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority) {
        String sListIdDetail = "";
        try {
            //set thuocphongkham_maphieudt = null ung voi cac ctDt trong bang cthieudutrutonghop.CtDtMa get CtDuTruPhongKham.danhsachmaso
            if(listCtDtLanhThuocDel.size()>0){
                for (CtPhieuDt ctPDt : listCtDtLanhThuocDel) {
                    if (ctPDt.getCtdtMa() != null) {
                        CtPhieuDt ctPDtDelete = em.find(CtPhieuDt.class, ctPDt.getCtdtMa());
                        if (ctPDtDelete != null){
                            //select danh sach tpk ma where ctphieudt ma trong bang CtPhieudutruTonghop: duoc danh sach,
                            //tach chuoi de where thuoc phong kham ma, set thuocphongkham_maphieudt = null
                            Query q = getEm().createQuery("SELECT ct.danhsachMaso FROM CtPhieudutruTonghop ct WHERE ct.ctdtMa = :ctdtMa and ct.loai = 'NT'");
                            q.setParameter("ctdtMa", ctPDtDelete.getCtdtMa());
                            String danhsachMaTNT = (String)q.getSingleResult();
                            System.out.println("danhsachMaTPK: "+danhsachMaTNT);
                            if(danhsachMaTNT != null){
                                int numRecordUpdated = 0;
                                String sSQL = "UPDATE FROM ThuocNoiTru c SET thuocnoitruMaPhieuDT = null WHERE c.thuocnoitruMa IN (" + danhsachMaTNT + ")  ";
                                q = getEm().createQuery(sSQL);
                                numRecordUpdated = q.executeUpdate();
                            }
                            System.out.println("-----Delete ctdt ma va danh sach tnt ma trong bang CtPhieudutruTonghop: " + ctPDt.getCtdtMa());
                            q = getEm().createQuery("DELETE FROM CtPhieudutruTonghop c WHERE c.ctdtMa = :ctdtMa and c.loai = 'NT'");
                            q.setParameter("ctdtMa", ctPDtDelete.getCtdtMa());
                            int numRecordDeleted = q.executeUpdate();
                        }
                    }
                }
            }
            if(listCtDtLanhThuocDel.size()>0){
                for (CtPhieuDt ctPDt : listCtDtLanhThuocDel) {
                    if (ctPDt.getCtdtMa() != null) {
                        System.out.println("-----Delete Chi tiet phieu du tru: " + ctPDt.getCtdtMa());
                        CtPhieuDt ctPDtDelete = em.find(CtPhieuDt.class, ctPDt.getCtdtMa());
                        if (ctPDtDelete != null){
                             em.remove(ctPDtDelete);
                        }
                    }
                }
            }
            for (int i = 0; i < listCtPhieuDtLinh.size(); i++) {
                listCtPhieuDtLinh.get(i).setPhieudtMa(objPhieuDtLinh);
            }

            for (int i = 0; i < listCtPhieuDtLinh.size(); i++) {
                CtPhieuDt obj = listCtPhieuDtLinh.get(i);
                if (obj != null) {
                    if (sListIdDetail.equals("")) {
                        sListIdDetail += obj.getCtdtMa();
                    } else {
                        sListIdDetail += "," + obj.getCtdtMa();
                    }
                }
            }
            this.removeItem(sListIdDetail, objPhieuDtLinh.getPhieudtMa());

            TonKhoFacade tkFacade = new TonKhoFacade();
            tkFacade.setEm(em);
            /*Thay doi dong listCtPhieuDtLinh neu lo thuoc do khong du de xuat se thay the bang lo thuoc khac theo thu tu uu tien
             * Thay doi lo thuoc cho listCtPhieuDtLinh
             */
            int vitriThaythe = -1;
            int i = 0;
            for(CtPhieuDt ctPhieuDT : listCtPhieuDtLinh){
                CtPhieuDt ctPhieuDTTemp = new CtPhieuDt();
                ctPhieuDTTemp = (CtPhieuDt)BeanUtils.cloneBean(ctPhieuDT);
                if (ctPhieuDT.getCtdtMa() == null) {
                    Double soluongCanXuat = ctPhieuDT.getCtdtSoluong();
                    String maLK = ctPhieuDT.getCtdtMalk();
                    Integer thuocMaso = ctPhieuDT.getDmthuocMaso(true).getDmthuocMaso();

                    List<TonKho> listTonKhoHT = tkFacade.getTonKhoHienTai(thuocMaso, objPhieuDtLinh.getPhieudtMakho().getDmkhoaMaso(), priority);
                    TonKho tonLoHienTai = tkFacade.getTonKhoHienTai(maLK, objPhieuDtLinh.getPhieudtMakho().getDmkhoaMaso());
                    Double soluongTonLoHienTai = 0.0;
                    if(tonLoHienTai != null){
                        soluongTonLoHienTai = tonLoHienTai.getTonkhoTon();
                    }
                    TonKho tonkhoThayThe = new TonKho();
                    
                    System.out.println("Ma lien ket: "+ maLK);
                    System.out.println("So luong Can Xuat: " + soluongCanXuat);
                    System.out.println("So luong ton hien tai: " + soluongTonLoHienTai);
                    if(soluongCanXuat.doubleValue() > soluongTonLoHienTai.doubleValue()){
                        System.out.println("So luong ton hien tai khong du de xuat, phai tim chon lo khac");
                        for(TonKho tonkho:listTonKhoHT){
                            if(soluongCanXuat <= tonkho.getTonkhoTon()){
                            tonkhoThayThe = (TonKho)BeanUtils.cloneBean(tonkho);
                                vitriThaythe = i;
                                break;
                            }
                        }
                        System.out.println("Vi tri thay the: "+ vitriThaythe);
                        if(vitriThaythe > -1){
                            ctPhieuDTTemp.setCtdtMalk(tonkhoThayThe.getTonkhoMalienket());
                            ctPhieuDTTemp.setCtdtDongia(tonkhoThayThe.getTonkhoDongia());
                            ctPhieuDTTemp.setCtdtLo(tonkhoThayThe.getTonkhoLo());
                            ctPhieuDTTemp.setCtdtNamnhap(tonkhoThayThe.getTonkhoNamnhap());
                            ctPhieuDTTemp.setCtdtNamhandung(tonkhoThayThe.getTonkhoNamhandung());
                            ctPhieuDTTemp.setCtdtThanghandung(tonkhoThayThe.getTonkhoThanghandung());
                            ctPhieuDTTemp.setCtdtNgayhandung(tonkhoThayThe.getTonkhoNgayhandung());
                            ctPhieuDTTemp.setCtdtSodangky(tonkhoThayThe.getTonkhoSodangky());
                            ctPhieuDTTemp.setDmnctMaso(tonkhoThayThe.getDmnctMaso(true));
                            ctPhieuDTTemp.setDmnguonkinhphiMaso(tonkhoThayThe.getDmnguonkinhphiMaso(true));
                            ctPhieuDTTemp.setDmnhasanxuatMaso(tonkhoThayThe.getDmnhasanxuatMaso(true));
                            ctPhieuDTTemp.setDmquocgiaMaso(tonkhoThayThe.getDmquocgiaMaso(true));
                            listCtPhieuDtLinh.set(vitriThaythe, ctPhieuDTTemp);
                        }
                    }
                    i++;
                }
            }

            for (i = 0; i<listCtPhieuDtLinh.size(); i++) {
                CtPhieuDt obj = listCtPhieuDtLinh.get(i);
                if (obj.getCtdtMa() != null) {
                    getEm().merge(obj);
                    System.out.println("UPDATE SUCCESS CtPhieuDt");
                } else {
                    getEm().persist(obj);
                    System.out.println("INSERT SUCCESS CtPhieuDt");
                    //insert list ma thuoc phong kham ung voi ct ma du tru vao bang cthieudutrutonghop
                    System.out.println("INSERT CTDTMa va danh sach TPK Ma in CtPhieudutruTonghop");
                    if(ctdt_matpk != null){
                        List<Integer> listMaTPK = ctdt_matpk.get(i);
                        String danhsachMaTNT = " ";
                        if(listMaTPK != null){
                            for(int j=0;j<listMaTPK.size();j++){
                                if(j == 0) {
                                    danhsachMaTNT += listMaTPK.get(j);
                                }else {
                                    danhsachMaTNT += "," + listMaTPK.get(j);
                                }
                            }
                            CtPhieudutruTonghop ctPhieudutruTonghop = new CtPhieudutruTonghop();
                            ctPhieudutruTonghop.setCtdtMa(obj.getCtdtMa());
                            ctPhieudutruTonghop.setDanhsachMaso(danhsachMaTNT);
                            ctPhieudutruTonghop.setLoai("NT");
                            getEm().merge(ctPhieudutruTonghop);
                        }
                        System.out.println("INSERT CTDTMa va danh sach TNT Ma SUCCESS in CtPhieudutruTonghop");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        //getContext().setRollbackOnly();
        }
    }

    private void removeItem(String listIdMaSo, String maPhieuDt) {
        System.out.println("***** CtPhieuDt listIdMaSo: " + listIdMaSo);
        try {
            Query q;
            int numRecordDeleted = 0;
            if (listIdMaSo == null || listIdMaSo.equals("")) {
                q = getEm().createQuery("DELETE FROM CtPhieuDt c WHERE  c.phieudtMa.phieudtMa = :maPhieuDt");
            } else {
                q = getEm().createQuery("DELETE FROM CtPhieuDt c WHERE  c.phieudtMa.phieudtMa = :maPhieuDt AND  c.ctdtMa NOT IN (" + listIdMaSo + ")  ");
                System.out.println("***** CtPhieuDt WERE DELETING: " + listIdMaSo);
            }
            q.setParameter("maPhieuDt", maPhieuDt);
            numRecordDeleted = q.executeUpdate();
            System.out.println("***** CtPhieuDt NUMBER RECORDS WERE DELETED: " + numRecordDeleted);
        } catch (Exception e) {
            System.out.println(e.toString());
            getContext().setRollbackOnly();
        }
    }

    /**
     *
     *  ==========================================================
     **/

    /**
     * Xoa tat ca chi tiet phieu du tru theo ma phieu du tru
     *
     */

    public void removeAllCtPhieuDuTru(PhieuDuTru objPhieuDt) {
        Query q=null;
        String sMaPhieuDt="";
        int numRecordDeleted=0;
        try {
            if (objPhieuDt != null) {
                sMaPhieuDt=objPhieuDt.getPhieudtMa();
                q = getEm().createQuery("DELETE FROM CtPhieuDt WHERE phieudtMa.phieudtMa = :sMaPhieuDt");
            }
            q.setParameter("sMaPhieuDt", sMaPhieuDt);

            numRecordDeleted=q.executeUpdate();
            System.out.println("----- Number of records CtPhieuDt were deleted: "+numRecordDeleted);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            getContext().setRollbackOnly();
        }
    }

    /* =====================================================*/

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