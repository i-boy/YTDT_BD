/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.session;

import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.utils.Utils;
import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.dieutri.entity.CtXuatBhThuocbk;
import java.math.BigDecimal;
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
public class TonKhoFacade implements TonKhoFacadeLocal, TonKhoFacadeRemote {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext context;

    public void create(TonKho tonKho) {
        getEm().persist(tonKho);
    }
    
    public Integer createTonKho(TonKho tonKho) {
        getEm().persist(tonKho);
        return tonKho.getTonkhoMa();
    }

    public void edit(TonKho tonKho) {
        getEm().merge(tonKho);
    }

    public void remove(TonKho tonKho) {
        getEm().remove(getEm().merge(tonKho));
    }

    public TonKho find(Object id) {
        return getEm().find(com.iesvn.yte.dieutri.entity.TonKho.class, id);
    }

    public List<TonKho> findAll() {
        return getEm().createQuery("select object(o) from TonKho as o").getResultList();
    }

    /**
     * Tinh ton dau, nhap, xuat, tra, ton cuoi
     * @param tk
     * @return boolean
     */
    public boolean insertTonKho(TonKho tk) {
        String dongia = "" + Utils.formatNumber(tk.getTonkhoDongia(), "###.##");
        dongia = dongia.replace(".", "");
        String malk = tk.getTonkhoMalienket();
        if (malk == null || malk.equals("")) {
            malk = tk.getDmthuocMaso(true).getDmthuocMa() +
                    tk.getDmnctMaso(true).getDmnctMa() +
                    tk.getDmnguonkinhphiMaso(true).getDmnguonkinhphiMa() +
                    tk.getDmquocgiaMaso(true).getDmquocgiaMa() +
                    tk.getDmnhasanxuatMaso(true).getDmnhasanxuatMa() +
                    //tk.getPhieunhapkhoMa(true).getPhieunhapkhoMa() +
                    tk.getTonkhoNamnhap() +
                    tk.getTonkhoNgayhandung() +
                    tk.getTonkhoThanghandung() +
                    tk.getTonkhoNamhandung() +
                    tk.getTonkhoLo() +
                    tk.getTonkhoSodangky() +
                    dongia;
            tk.setTonkhoMalienket(malk.toUpperCase());
        }
        //System.out.println("malk: "+ malk);
        //System.out.println("Kho: "+ tk.getDmkhoaMaso().getDmkhoaMaso());
        TonKho tkHt = getTonKhoHienTai(malk, tk.getDmkhoaMaso().getDmkhoaMaso());
        //System.out.println("Ton khoHT: "+ tkHt);
        // cap nhat gia tri ton hien tai.
        Double nhap = Double.valueOf("0");
        if (tk.getTonkhoNhap() != null) {
            nhap = tk.getTonkhoNhap();
        }
        tk.setTonkhoNhap(nhap);
        //System.out.println("Nhap: "+ nhap);
        Double xuat = Double.valueOf("0");
        if (tk.getTonkhoXuat() != null) {
            xuat = tk.getTonkhoXuat();
        }
        tk.setTonkhoXuat(xuat);
        //System.out.println("Xuat: "+ xuat);
        Double tra = Double.valueOf("0");
        if (tk.getTonkhoTra() != null) {
            tra = tk.getTonkhoTra();
        }
        tk.setTonkhoTra(tra);
        //System.out.println("Tra: "+ tra);
        Double ton = Double.valueOf("0");
        if (tkHt != null) {
            if (tkHt.getTonkhoTon() != null) {
                ton = tkHt.getTonkhoTon();
            }
        }
        //System.out.println("tondau: "+ ton);
        //*** triet them vao cap nhat ton dau
        tk.setTonkhoTondau(ton);
        //******
        ton = ton + ((nhap + tra) - xuat);
        //ThoVNA - kiem tra lai lan cuoi, neu so luong ton < sl xuat thi khong cho thuc hien xuat thuoc
        if(ton <0){
            return false;
        }
        tk.setTonkhoTon(ton);

        if (tk.getPhieunhapkhoMa() != null &&
                (tk.getPhieunhapkhoMa().getPhieunhapkhoMa() == null || tk.getPhieunhapkhoMa().getPhieunhapkhoMa().equals(""))) {
            tk.setPhieunhapkhoMa(null);
        }


        tk.setTonkhoNgaygiocn(new Date());
        try {
            tk.setTonkhoHienthi(true);
            getEm().persist(tk);
            return true;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }

    public List<TonKho> findByTonkhoMalienket(String malk) {
        //System.out.println("-----findByTonkhoMalienket()-----");
        Query q = getEm().createQuery("SELECT t FROM TonKho t WHERE t.tonkhoMalienket = :malk");
        q.setParameter("malk", malk);
        try {
            List<TonKho> listTk = q.getResultList();
            if (listTk != null) {
                return listTk;
            }
        } catch (Exception ex) {
            System.out.println("Error findByTonkhoMalienket: " + ex.toString());
        }
        return null;
    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public Double findByTonkhoKhoaMalienketHienTai(String malk, Integer khoaMaso) {
        //System.out.println("-----findByTonkhoKhoaMalienketHienTai()-----");
        String sql = "select t.tonkho_ton from v_tonkho_khoa_hientai t where t.dmkhoa_maso = " + khoaMaso +" and tonkho_malienket = '"+malk+"'";
        Query q = getEm().createNativeQuery(sql);
        try {
            Double tondauky = new Double(((BigDecimal)q.getSingleResult()).doubleValue());
            return tondauky;
        } catch (Exception ex) {
            System.out.println("Error findByTonkhoKhoaMalienketHienTai: " + ex.toString());
        }
        return 0.0;
    }
    
    public Double findByTonkhoDauMalienket(String malk, Integer khoaMaso, Date fromDateReport, Date toDateReport) {
        //System.out.println("-----findByTonkhoDauMalienket()-----");
        Query q = getEm().createQuery("SELECT t FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and t.tonkhoMalienket = :malk and ( to_date(t.tonkhoNgaygiocn) between :tonkhoTuNgay and :tonkhoDenNgay) order by t.tonkhoMa asc");
        
        q.setParameter("dmkhoaMaso", khoaMaso);
        q.setParameter("malk", malk);
        q.setParameter("tonkhoTuNgay", fromDateReport);
        q.setParameter("tonkhoDenNgay", toDateReport);
        try {            
            List<TonKho> lstTonKho = q.getResultList();
            if(lstTonKho.size()>0)
            {
                Double tonkhoDau = (Double)lstTonKho.get(0).getTonkhoTondau();
                return tonkhoDau;
            }else{
                Double tonkhoDau = findByTonkhoKhoaMalienketHienTai(malk, khoaMaso);
                return tonkhoDau;
            }
        } catch (Exception ex) {
            System.out.println("Error findByTonkhoDauMalienket: " + ex.toString());
        }
        return null;
    }

    public TonKho getTonKhoHienTai(String malk, Integer maKhoa) {
        //System.out.println("-----getTonKhoHienTai-----"+ malk + "--"+ maKhoa);
        Query q = getEm().createQuery("SELECT t FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMaso = :maKhoa AND  t.tonkhoMalienket = :malk AND t.tonkhoMa = (SELECT MAX(t1.tonkhoMa) FROM TonKho t1 WHERE t1.tonkhoMalienket = :malk AND t1.dmkhoaMaso.dmkhoaMaso = :maKhoa)");
        q.setParameter("malk", malk);
        q.setParameter("maKhoa", maKhoa);
        try {
            List<TonKho> listTk = q.getResultList();
            if (listTk != null && listTk.size() > 0) {
                return listTk.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error getTonKhoHienTai: " + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    public TonKho getTonKhoHienTai(Integer masoThuoc, String soLo, Double dongiaThuoc, Integer masoKhoa){
        //System.out.println("-----Begin - getTonKhoHienTai-----");
        String sSQL = "Select tk From TonKho tk where tk.tonkhoMa = (Select MAX(t.tonkhoMa) from TonKho t where t.dmthuocMaso.dmthuocMaso = :dmthuocMaso and t.dmkhoaMaso.dmkhoaMaso = :dmkhoaMaso and t.tonkhoLo = :tonkhoLo and t.tonkhoDongia = :tonkhoDongia)";
        Query q = getEm().createQuery(sSQL);
        q.setParameter("dmthuocMaso", masoThuoc);
        q.setParameter("dmkhoaMaso", masoKhoa);
        q.setParameter("tonkhoLo", soLo);
        q.setParameter("tonkhoDongia", dongiaThuoc);
        try {
            List<TonKho> listTk = q.getResultList();
            if (listTk != null) {
                if (listTk.size() > 0) {
                    return listTk.get(0);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error getTonKhoHienTai: " + ex.toString());
            ex.printStackTrace();
        }
        //System.out.println("-----End - getTonKhoHienTai-----");
        return null;
    }

    /**
     * 
     * @param dmtMa
     * @return
     */
    public List<TonKho> getTonKho(String dmtMa, String maKho) {
        try {
            Query q = getEm().createQuery("SELECT tk FROM TonKho tk WHERE tk.tonkhoMa = (SELECT MAX(t.tonkhoMa) FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMa like :maKho AND t.dmthuocMaso.dmthuocMa = :dmthuocMa)");
            q.setParameter("dmthuocMa", dmtMa);
            q.setParameter("maKho", maKho);
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error getTonKho: " + ex.toString());
            return null;
        }
    }

    /**
     * 
     * @param dmtMa
     * @return
     */
    public List<TonKho> getTonKhoByDmThuocMaSoAndMaKho(String dmtMaSo, String maKho) {
        try {
            Query q = getEm().createQuery("SELECT tk FROM TonKho tk WHERE tk.tonkhoMa = (SELECT MAX(t.tonkhoMa) FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMa like :maKho AND t.dmthuocMaso.dmthuocMaso = :dmthuocMa)");
            q.setParameter("dmthuocMa", dmtMaSo);
            q.setParameter("maKho", maKho);
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error getTonKhoByDmThuocMaSoAndMaKho: " + ex.toString());
            return null;
        }

    }
    // chua check cau query 23/11/2011
    public List<TonKho> getTonKhoHienTai(Integer dmtMaSo, Integer khoMaso, String priority) {
        try {
            //System.out.println("-------Begin getTonKhoHienTai-----------------");
            List<TonKho> lstTonHt = new ArrayList<TonKho>();
            String sSQLLIFO_FIFO = "SELECT TK.* FROM TON_KHO TK "
                    + "LEFT JOIN PHIEU_NHAP_KHO P ON TK.PHIEUNHAPKHO_MA = P.PHIEUNHAPKHO_MA "
                    + "INNER JOIN (SELECT MAX(T.TONKHO_MA) TONKHO_MA FROM TON_KHO T WHERE T.DMKHOA_MASO = " + khoMaso + " AND T.DMTHUOC_MASO = " + dmtMaSo +" GROUP BY T.TONKHO_MALIENKET) TKMAX ON TK.TONKHO_MA = TKMAX.TONKHO_MA "
                    + "WHERE TK.DMTHUOC_MASO = " + dmtMaSo + " AND TK.TONKHO_TON > 0 ";
            String sSQL_UUTIENCONLAI = "SELECT TK.* FROM TON_KHO TK, (SELECT MAX(T.TONKHO_MA) TONKHO_MA FROM TON_KHO T WHERE T.DMKHOA_MASO = " + khoMaso + " AND T.DMTHUOC_MASO = " + dmtMaSo + " GROUP BY T.TONKHO_MALIENKET) TKMAX "
                    + "WHERE TK.TONKHO_MA = TKMAX.TONKHO_MA AND TK.DMTHUOC_MASO = " + dmtMaSo + " AND TK.TONKHO_TON > 0 ";

            //Dua vao do uu tien, de lay lo thuoc thich hop va so sanh so luong neu vua du thi hien thi thong tin cua lo thuoc do
            if(priority.equals("1")){
		/*1:HSD: lo nao gan het han cho xuat truoc*/
		sSQL_UUTIENCONLAI = sSQL_UUTIENCONLAI + " ORDER BY TK.TONKHO_NAMHANDUNG, TK.TONKHO_THANGHANDUNG, TK.TONKHO_NGAYHANDUNG";
            }else if(priority.equals("2")){
		/*2:LIFO: thuoc nao nhap vao kho sau cho xuat truoc*/
		sSQLLIFO_FIFO = sSQLLIFO_FIFO + " ORDER BY P.PHIEUNHAPKHO_NGAYGIOCN desc";
            }else if(priority.equals("3")){
		/*3:FIFO: thuoc nao nhap vao truoc cho xuat truoc*/
		sSQLLIFO_FIFO = sSQLLIFO_FIFO + " ORDER BY P.PHIEUNHAPKHO_NGAYGIOCN asc";
            }else if(priority.equals("4")){
		/*4:TONTHAP: uu tien so luong ton it xuat truoc*/
		sSQL_UUTIENCONLAI = sSQL_UUTIENCONLAI + " ORDER BY TK.TONKHO_TON asc";
            }else if(priority.equals("5")){
		/*5:TONCAO: uu tien so luong ton nhieu xuat truoc*/
		sSQL_UUTIENCONLAI = sSQL_UUTIENCONLAI + " ORDER BY TK.TONKHO_TON desc";
            }else if(priority.equals("6")){
		/*6:DONGIATHAP: uu tien don gia thap cho xuat truoc*/
		sSQL_UUTIENCONLAI = sSQL_UUTIENCONLAI + " ORDER BY TK.TONKHO_DONGIA asc";
            }else{
		/*= 7: DONGIACAO: uu tien don gia thap cho xuat truoc*/
		sSQL_UUTIENCONLAI = sSQL_UUTIENCONLAI + " ORDER BY TK.TONKHO_DONGIA desc";
            }


            if(priority.equals("2") || priority.equals("3")){
                Query q = getEm().createNativeQuery(sSQLLIFO_FIFO,TonKho.class);
                lstTonHt = q.getResultList();
            }else{
                Query q = getEm().createNativeQuery(sSQL_UUTIENCONLAI,TonKho.class);
                lstTonHt = q.getResultList();
            }
            
            if(lstTonHt != null && lstTonHt.size() > 0){
                //System.out.println("-------End getTonKhoHienTai-----------------"+ lstTonHt.size());
                return lstTonHt;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        return null;
    }

    public String getSoLuongTon(String dmtMa, String maKho) {
        String result = "";
        try {
            Query q = getEm().createQuery("SELECT SUM(tk.tonkhoTon) FROM TonKho tk WHERE tk.tonkhoMa = (SELECT MAX(t.tonkhoMa) FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMa like :maKho AND t.dmthuocMaso.dmthuocMa = :dmthuocMa)");
            q.setParameter("dmthuocMa", dmtMa);
            q.setParameter("maKho", maKho);
            Object obj = q.getSingleResult();
            if (obj != null) {
                result += obj.toString();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }

    public String getGiaBQ(String dmtMa, String maKho) {
        String result = "";
        try {
            Query q = getEm().createQuery("SELECT AVG(tk.tonkhoDongia) FROM TonKho tk WHERE tk.dmthuocMaso.dmthuocMa = :dmthuocMa and tk.dmkhoaMaso.dmkhoaMa = :maKho");
            q.setParameter("dmthuocMa", dmtMa);
            q.setParameter("maKho", maKho);
            Object obj = q.getSingleResult();
            if (obj != null) {
                result += obj.toString();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }

    public String getGiaCuoi(String dmtMa, String maKho) {
        String result = "";
        try {
            Query q = getEm().createQuery("SELECT tk.tonkhoDongia FROM TonKho tk WHERE tk.tonkhoMa = (SELECT MAX(t.tonkhoMa) FROM TonKho t WHERE t.dmkhoaMaso.dmkhoaMa = :maKho AND t.dmthuocMaso.dmthuocMa = :dmthuocMa)");
            q.setParameter("dmthuocMa", dmtMa);
            q.setParameter("maKho", maKho);
            Object obj = q.getSingleResult();
            if (obj != null) {
                result += obj.toString();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return result;
    }

    /**
     * 
     * @param dmtMa
     * @param kp
     * @param nguon
     * @param khoMa
     * @return
     */
    public List<TonKho> findDanhSachTonKho(String dmtMa, String kp, String nguon, String khoMa) {
        if (dmtMa == null || dmtMa.trim().equals("")) {
            return null;
        }
        if (kp == null || kp.trim().equals("")) {
            kp = "%";
        }
        if (nguon == null || nguon.trim().equals("")) {
            nguon = "%";
        }

        List<TonKho> listTk = null;
        try {
            listTk = Utils.getTonKhoNew(getEm(), khoMa, dmtMa, nguon, kp);
        } catch (Exception ex) {
            System.out.println("Error at findDanhSachTonKho(): " + ex.toString());
        }
        return listTk;
    }

    public List<TonKho> findTonkhoByDmThuocMa(String dmtMa) {
        //System.out.println("Begin findTonkhoByDmThuocMa(String dmtMa method");
        List<TonKho> result = null;
        try {
            DmThuoc dmt = (DmThuoc) getEm().createQuery("Select d from DmThuoc d Where d.dmthuocMa = :dmthuocMa").setParameter("dmthuocMa", dmtMa).getSingleResult();
            Query q = getEm().createQuery("Select Distinct tk from TonKho tk Where tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso ");
            q.setParameter("dmthuocMaso", dmt.getDmthuocMaso());
            result = q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        //System.out.println("End findTonkhoByDmThuocMa(String dmtMa method");
        return result;
    }

    public List<TonKho> findTonkhoByDmThuocMaso(Integer dmtMaso) {
        //System.out.println("Begin findTonkhoByDmThuocMa(String dmtMa method");
        List<TonKho> result = null;
        try {
            Query q = getEm().createQuery("Select Distinct tk from TonKho tk Where tk.dmthuocMaso.dmthuocMaso = :dmthuocMaso ");
            q.setParameter("dmthuocMaso", dmtMaso);
            result = q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        //System.out.println("End findTonkhoByDmThuocMaso(String dmtMa method");
        return result;
    }

    //Manh added
    public String getSoLuongTon(String maKho, String maNguon, String maKinhphi, String maThuoc, String quyCach, String donGia) {
        String result = "";
        Double tongton = new Double(0);
        //System.out.println("----Begin getSoLuongTon----");
        try {
            if (maThuoc == null || maThuoc.trim().equals("")) {
                return "";
            }
            if (maKinhphi == null || maKinhphi.trim().equals("")) {
                maKinhphi = "%";
            }
            if (maNguon == null || maNguon.trim().equals("")) {
                maNguon = "%";
            }


            List<TonKho> listTk = null;
            try {
                Double dDonGia = null;
                if (donGia != null && !donGia.equals("")) {
                    dDonGia = new Double(donGia);
                }
                Integer iQuyCach = null;
                if (quyCach != null && !quyCach.equals("")) {
                    iQuyCach = new Integer(quyCach);
                }

                listTk = Utils.getTonKho(getEm(), maKho, maThuoc, maNguon, maKinhphi, iQuyCach, dDonGia);
                if (listTk != null && listTk.size() > 0) {

                    for (TonKho tk : listTk) {
                        tongton += tk.getTonkhoTon();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error at getSoLuongTon(): " + ex.toString());
                ex.printStackTrace();
            }

            result = tongton.toString();
            //System.out.println("result: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println("----End getSoluongton_Manh----" + result);
        return result;
    }

    public List<TonKho> findTonKhoByDtmMaAndKhoMa(String dmtMa, String khoMa) {
        return Utils.getTonKho(getEm(), khoMa, dmtMa);
    }

    public List<TonKho> findTonKhoByKhoaMasoThuocMaso(Integer thuocMaso, Integer khoMaso) {
        return Utils.getTonKhoByKhoaMaThuocMaso(getEm(), khoMaso, thuocMaso);
    }

    public List<TonKho> getTonKhoForTheKho(String dmtMa, String khoMa) {
        return Utils.getTonKhoForTheKho(getEm(), khoMa, dmtMa);
    }

    public List<TonKho> getTonKhoForTheKho(Integer dmtMaso, Integer khoMaso, Date fromDate, Date toDate) {
        return Utils.getTonKhoForTheKho(getEm(), khoMaso, dmtMaso, fromDate, toDate);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<TonKho> findListTonKhoForKiemKe(String maKP, String makhoa, String maCT, String listPL, String temp, String lthuoc) {
        //System.out.println("Begin findListTonKhoForKiemKe");
        List<TonKho> result = null;
        try {
            Query q = getEm().createQuery("Select tk from TonKho tk where (tk.tonkhoMa in (select max(tk1.tonkhoMa) from TonKho tk1 where (:khoa is null or tk1.dmkhoaMaso.dmkhoaMa = :khoa) group by tk1.tonkhoMalienket )) and (:kinhphi is null or tk.dmnguonkinhphiMaso.dmnguonkinhphiMa = :kinhphi) and (:chuongtrinh is null or tk.dmnctMaso.dmnctMa = :chuongtrinh) and (:khoa is null or tk.dmkhoaMaso.dmkhoaMa = :khoa) and (:phanloai is null or tk.dmthuocMaso.dmphanloaithuocMaso.dmphanloaithuocMa in " + listPL + ") and (:loaithuoc is null or tk.dmthuocMaso.dmphanloaithuocMaso.dmloaithuocMaso.dmloaithuocMa = :loaithuoc)");
            q.setParameter("kinhphi", maKP);
            q.setParameter("chuongtrinh", maCT);
            q.setParameter("khoa", makhoa);
            q.setParameter("phanloai", temp);
            q.setParameter("loaithuoc", lthuoc);
            result = q.getResultList();
            //System.out.println("%%%" + listPL + "gghfhgf" + result.size());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        //System.out.println("End findListTonKhoForKiemKe");
        return result;
    }
    //Tho add
    public long getTotalListTonKhoForKiemKe(String makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong){
        //System.out.println("Begin getTotalListTonKhoForKiemKe");
        String sSQL = "";
        long result = 0;
        if(kecaTonBangKhong){
            sSQL = "select max(tk.tonkhoMa) from TonKho tk where tk.dmkhoaMaso.dmkhoaMa = :khoa and tk.tonkhoMaphieukiem = null ";
        }else{
            sSQL = "select max(tk.tonkhoMa) from TonKho tk where tk.dmkhoaMaso.dmkhoaMa = :khoa and tk.tonkhoTon > 0 and tk.tonkhoMaphieukiem = null ";
        }
        if(!listPL.equals("('')")){
             sSQL +=" and tk.dmthuocMaso.dmphanloaithuocMaso.dmphanloaithuocMa in " + listPL + " ";
        }
        if(lthuoc != null){
            sSQL +=" and tk.dmthuocMaso.dmphanloaithuocMaso.dmloaithuocMaso.dmloaithuocMa = :loaithuoc ";
        }
        Query q = em.createQuery(sSQL);
        q.setParameter("khoa", makhoa);
        if(lthuoc != null){
            q.setParameter("loaithuoc", lthuoc);
        }

        List lstResult = q.getResultList();
        if (lstResult != null && lstResult.size() > 0) {
            return lstResult.size();
        }
        return result;
    }
    public List<TonKho> findListTonKhoForKiemKe(Integer makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong) {
        //System.out.println("Begin findListTonKhoForKiemKeNew");
        List<TonKho> result = null;
        String sSQL = "";
        if(kecaTonBangKhong){
            sSQL = "(select max(tk.tonkho_Ma) tonkho_ma from Ton_Kho tk where tk.dmkhoa_Maso = :khoa group by tk.tonkho_Malienket) tkht";
        }else{
            sSQL = "(select t.tonkho_ma from ton_kho t, (select max(tk.tonkho_Ma) tonkho_ma from Ton_Kho tk where tk.dmkhoa_Maso= :khoa group by tk.tonkho_Malienket) tkht " +
                   "where t.tonkho_ma = tkht.tonkho_ma and t.tonkho_ton > 0) tkht";
        }

        String tonkhoSQL = "select tk.* from Ton_Kho tk, Dm_Thuoc t, Dm_Phan_loai_thuoc plt, Dm_loai_thuoc lt, " + sSQL +
                " where tk.tonkho_ma = tkht.tonkho_ma and tk.dmthuoc_maso = t.dmthuoc_maso and t.dmphanloaithuoc_maso = plt.dmphanloaithuoc_maso " +
                "and plt.dmloaithuoc_maso = lt.dmloaithuoc_maso ";
        tonkhoSQL += "and tk.tonkho_Maphieukiem IS NULL ";
        if(!listPL.equals("('')")){
             tonkhoSQL +=" and plt.dmphanloaithuoc_Ma in " + listPL + " ";
        }
        if(lthuoc != null){
            tonkhoSQL +=" and lt.dmloaithuoc_Ma = :loaithuoc ";
        }
        tonkhoSQL +="order by tk.tonkho_Malienket";
        try {
            Query q = getEm().createNativeQuery(tonkhoSQL, TonKho.class);
            q.setParameter("khoa", makhoa);
            if(lthuoc != null){
                q.setParameter("loaithuoc", lthuoc);
            }
            result = q.getResultList();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        //System.out.println("End findListTonKhoForKiemKe");
        return result;
    }

    public TonKho findTonKhoForUpdatingOfKiemKeKho(String malk, String khoMa) {
        Query q = getEm().createQuery("Select tk from TonKho tk where (tk.tonkhoMa in (select max(tk1.tonkhoMa) from TonKho tk1  where (:khoa is null or tk1.dmkhoaMaso.dmkhoaMa = :khoa) and tk1.tonkhoMalienket = :malk ))");

        q.setParameter("khoa", khoMa);
        q.setParameter("malk", malk);
        List<TonKho> result = q.getResultList();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public boolean insertXuatKhoXuLyThuocBK(String maTiepDon, Integer tonkhoMa, String malk, DmThuoc dmThuoc, Integer thuocbkMa)
    {
        CtXuatBhThuocbk ctXuatBhThuocbk = new CtXuatBhThuocbk();
        try {
            ctXuatBhThuocbk.setCtxuatbhtdMalk(malk);
            ctXuatBhThuocbk.setTiepdonMa(maTiepDon);
            ctXuatBhThuocbk.setTonkhoMa(tonkhoMa);
            ctXuatBhThuocbk.setDmthuocMaso(dmThuoc);
            ctXuatBhThuocbk.setThuocphongkhamMa(thuocbkMa);
            getEm().persist(ctXuatBhThuocbk);
            return true;
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
        }
        return false;
    }
    //Tho add: Kiem tra xem voi ma so thuoc nay co so luong ton thuoc >0 tai cac khoa/kho hay khong (ngoai tru kho thanh ly)?
    public boolean checkTonKhoHienTai(Integer masoThuoc){
        //System.out.println("-----checkTonKhoHienTai()-----");
        boolean conTonThuoc = false;
        String sSQL = "SELECT * from Ton_kho t, " +
                    "(SELECT MAX(t.tonkho_Ma) as tonkho_ma FROM Ton_Kho t, Dm_khoa k WHERE t.dmkhoa_maso = k.dmkhoa_maso and t.dmthuoc_Maso = :dmthuocMaso AND (k.dmkhoa_Ma NOT IN ('KTL','KTD')) GROUP BY t.tonkho_Malienket) a " +
                    "where t.tonkho_ma = a.tonkho_ma and t.tonkho_ton > 0";
        Query q = getEm().createNativeQuery(sSQL, TonKho.class);
        q.setParameter("dmthuocMaso", masoThuoc);
        List result = q.getResultList();
        if (result != null && result.size() > 0) {
            conTonThuoc = true;
        }
        return conTonThuoc;
    }
    public TonKho findTonkhoByMalienket(String malk) {
        
        Query q = getEm().createQuery("SELECT t FROM TonKho t WHERE t.tonkhoMalienket = :malk");
        q.setParameter("malk", malk);
        try {
            List<TonKho> listTk = q.getResultList();
            if (listTk != null && listTk.size() > 0) {
                return listTk.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        return null;
    }
}


