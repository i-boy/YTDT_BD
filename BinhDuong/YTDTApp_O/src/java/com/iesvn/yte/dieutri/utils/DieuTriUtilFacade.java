/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.utils;

import com.iesvn.yte.dieutri.entity.CauHinh;
import com.iesvn.yte.dieutri.entity.TmpBaocaoThekho;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.VTonkhoKhochinh;
import com.iesvn.yte.dieutri.entity.VTonkhoKhole;
import com.iesvn.yte.entity.*;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author LENOVO 3000 Y410
 */
@Stateless
public class DieuTriUtilFacade implements DieuTriUtilFacadeLocal, DieuTriUtilFacadeRemote {

    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
    @PersistenceContext
    private EntityManager em;

    public List findByNgayGioCN(Double ngaycn, String clsName, String fieldNgayGioCNName) {
        List list = null;
        System.out.print("ngaycn::::::"+ngaycn);
        try {
            Query q = getEm().createQuery("select o from " + clsName + " o where o." + fieldNgayGioCNName + " > :fieldNgayGioCNName");
            q.setParameter("fieldNgayGioCNName", ngaycn);
            list = q.getResultList();
            System.out.println("list: " + list.size());
        } catch (Exception e) {
            System.out.println("e: " + e);
            e.printStackTrace();
        }
        return list;
    }
    public Object findByMaWithFormatMaPhieu(String ma, String clsName, String fieldMaName) {
    	ma = Utils.formatMaPhieu(ma);
    	
        return findByMa(ma, clsName, fieldMaName);
    }
    public Object findByMaWithFormatMa(String ma, String clsName, String fieldMaName) {
    	ma = Utils.formatMa(getEm(),ma);
    	
        return findByMa(ma, clsName, fieldMaName);
    }
    public Object findByMa(String ma, String clsName, String fieldMaName) {
        Object dm = null;
        try {

            Query q = getEm().createQuery("select o from " + clsName + " o where o." + fieldMaName + " = :dmMa");
            q.setParameter("dmMa", ma);
            dm = (Object) q.getSingleResult();
        } catch (Exception e) {
            dm = null;
            
        }
        return dm;
    }

    /**
     *  chua su dung
     **/
    public Object findByTenNotEqualMaso(String ma, String clsName, String fieldMaName, String fieldMaSo,Integer maSo) {
        Object dm = null;
        try {

            Query q = getEm().createQuery("select o from " + clsName + " o where o." + fieldMaName + " = :dmMa and " + fieldMaSo + " <> :maSo ");
            q.setParameter("dmMa", ma);
            q.setParameter("maSo", maSo);

            dm = (Object) q.getSingleResult();
        } catch (Exception e) {
            dm = null;
        }
        return dm;
    }
     
     public Object findByMaSo(Integer maso, String clsName, String fieldMaSoName) {
        Object dm = null;
        try {

            Query q = getEm().createQuery("select o from " + clsName + " o where o." + fieldMaSoName + " = :dmMa");
            q.setParameter("dmMa", maso);
            dm = (Object) q.getSingleResult();
        } catch (Exception e) {
            dm = null;
        }
        return dm;
    }
     
     
     public  String formatMa( String ma) {
         
       return Utils.formatMa(getEm(), ma);
    }

    /**
     * @auhor thanhdo
     * @param ma
     * @return
     */
    public  String formatMaPhieu(String ma) {
       return Utils.formatMaPhieu(ma);

    }
    
     /**
      * 
      * Get tinh chat trong table cau hinh 
      * @param myKey
      * @return
      */
    public String getProperty(String myKey){
        CauHinh cauHinh = em.find(CauHinh.class, myKey);
        if (cauHinh != null){
            return cauHinh.getChbvGiatri();
        }else{
            return "";
        }
        
    } 
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<VaiTro> getVaiTroByNguoiDungTenDangNhap(String tenDangNhap) {

        return em.createQuery("select vt from VaiTro as vt, NguoiDung as nd, NguoiDungVaiTro as ndvt where ndvt.vaitroMaso = vt and ndvt.ndMaso = nd and upper(nd.ndTendangnhap) like upper(:tenDangNhap)").setParameter("tenDangNhap", tenDangNhap).getResultList();

    }

    /*
     * Thanh add 30/09/2011
     * Oracle: syntax ok
     */
    public Integer getSoKhamBenh(String maBanKham) {
        String currentValue = null;
        try {
            Query q = em.createNativeQuery("select CURRENT_VALUE from DT_DM_BAN_KHAM_SEQUENCE where MA like " + "'" + maBanKham + "'");

            if(q.getResultList() == null || q.getResultList().size() == 0 ){
                return 0;
            }

            currentValue = String.valueOf(q.getSingleResult());
            //currentValue = currentValue.substring(1, currentValue.length() - 1);
            q = em.createNativeQuery("update DT_DM_BAN_KHAM_SEQUENCE set  CURRENT_VALUE = CURRENT_NEXT, CURRENT_NEXT = CURRENT_NEXT + STEP where MA like " + "'" + maBanKham + "'");
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            currentValue = "0";
        }
        return Integer.valueOf(currentValue);
    }

    public List findByAllConditions(String clsName, String fieldMa, String fieldTen,String fieldDT, String ma,String ten, boolean dt) {
        String sql = "";

		sql = "select object(o) from " + clsName +  " as o "
				+ " where (trim(:Ma) = null " + " or UPPER(" + fieldMa + ") = UPPER(:Ma)) "
				+ " and (trim(:Ten) = null " + " or UPPER(" + fieldTen + ") like ('%' || UPPER(:Ten) || '%'))"
				+ " and :DT = " + fieldDT;

		return em.createQuery(sql)
				.setParameter("Ma", ma)
				.setParameter("Ten", ten)
				.setParameter("DT", dt)
				.getResultList();
 
    }

    public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen,String fieldDT, String ma,String ten, boolean dt) {
        String sql = "";

		sql = "select object(o) from " + clsName +  " as o "
				+ " where (:Ma = '' " + " or " + fieldMa + " = :Ma) "
				+ " and (:Ten = '' " + " or " + fieldTen + " like ('%' || :Ten || '%'))"
				+ " and :DT = " + fieldDT
                                + " order by " + fieldTen + " asc";

		return em.createQuery(sql)
				.setParameter("Ma", ma)
				.setParameter("Ten", ten)
				.setParameter("DT", dt)
				.getResultList();
 
    }
    //Phuc add below method 
    public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen,String fieldDT, String fieldPhanloai, String ma,String ten, Integer phanloai) {
        String sql = "select object(o) from  " + clsName +  " as o where " + fieldDT + " = true ";
        if (ma.trim().length() > 0) {
            sql += "and " + fieldMa + " like ('%' || :ma || '%') ";

        }
        if (ten.trim().length() > 0) {
            sql += "and " + fieldTen + " like ('%' || :ten || '%') ";
        }
        if (phanloai != null) {
            sql += "and " + fieldPhanloai +" = :phanloai ";
        }
        sql += "order by o.dmthuocTen asc";
        Query qry = em.createQuery(sql);
        if (ma.trim().length() > 0) {
            qry.setParameter("ma", ma);
        }
        if (ten.trim().length() > 0) {
            qry.setParameter("ten", ten);
        }
        if (phanloai != null) {
            qry.setParameter("phanloai", phanloai);
        }
        return qry.getResultList();                
    }

    public List findByAllConditions(String clsName, String fieldMa, String fieldTen, String ma,String ten) {
        String sql = "";

		sql = "select object(o) from " + clsName +  " as o "
				+ " where (:Ma = '' " + " or " + fieldMa + " = :Ma) "
				+ " and (:Ten = '' " + " or " + fieldTen + " like ('%' || :Ten || '%'))"
				;

		return em.createQuery(sql)
				.setParameter("Ma", ma)
				.setParameter("Ten", ten)			
				.getResultList();

    }
    
    public List findMaLike(String clsName, String fieldMa, String fieldDT, String ma, boolean dt){
        String sql="";
        
            sql = "select object(o) from " + clsName +  " as o "
				+ " where (:Ma = '' " + " or " + fieldMa + " like concat(:Ma, '%')) "
				+ " and :DT = " + fieldDT;

		return em.createQuery(sql)
				.setParameter("Ma", ma)
				.setParameter("DT", dt)
				.getResultList();
    }

    public List findTenLike(String clsName, String fieldTen, String ten){
        String sql="";

            sql = "select object(o) from " + clsName +  " as o "
				+ " where (:Ten = '' " + " or UPPER(" + fieldTen + ") like ('%' || UPPER(:Ten) || '%'))";

		return em.createQuery(sql)
				.setParameter("Ten", ten)
				.getResultList();
    }
    
     public List findAll(String clsName,String fieldDT, boolean dt) {
        String sql = "";

		sql = "select object(o) from " + clsName +  " as o "
				+ " where :DT = " + fieldDT;

		return em.createQuery(sql)
				.setParameter("DT", dt)
				.getResultList();
 
    }
    
    public List findAll(String clsName) {
        String sql = "";

		sql = "select object(o) from " + clsName +  " as o ";

		return em.createQuery(sql)
				.getResultList();
 
    }
    
   public List findAllChacon(String clsNameCon, String fieldMasoCon ,Integer masocon) {
        String sql = "";

		sql = "select object(con) from " + clsNameCon   
                        + " where (:Macon = " +fieldMasoCon + " ) " ;
			//where ndvt.vaitroMaso = vt and ndvt.ndMaso	

		return em.createQuery(sql)
				.setParameter("Macon", masocon)
				.getResultList();
 
    }

    public void create(Object ob) {
        em.persist(ob);
    }

    public void edit(Object ob) {
        em.merge(ob);
    }

    public void remove(Object ob) {
        em.remove(em.merge(ob));
    }

    public double getGiaCuoi(String maThuoc){
        double result = 0;
         System.out.println("maThuoc:"+maThuoc);
        String sql = "select object(tk) from TonKho tk where tk.dmthuocMaso.dmthuocMa like :maThuoc order by tk.tonkhoMa desc" ;
        Query q = em.createQuery(sql);
        q.setParameter("maThuoc", maThuoc);
        List<TonKho> lisTonKho = q.getResultList();
        System.out.println("lisTonKho:"+lisTonKho);
        if (lisTonKho != null && lisTonKho.size() > 0){
            TonKho tk = lisTonKho.get(0);
            System.out.println("tk:"+tk);
            result = tk.getTonkhoDongia();
            System.out.println("result:"+result);
        }
        System.out.println("result:"+result);
        return result;
    }
    
    public double getTonKhoByMaThuocAndMaKhoa(String maThuoc,String maKhoa) {
        double result = 0;
        System.out.println("maThuoc:" + maThuoc);
        //sua lai theo cach tim theo ma thuoc by max ngay gio cap nhat cua ton_kho
        // ngay 06-04-2010
       // List<TonKho> lisTonKho = Utils.getTonKho(em, "%", maThuoc);
        double tonkho = Utils.getTonKhoByMaThuocAndMaKhoa(em, maThuoc,maKhoa);
        System.out.println("TonKho:" + tonkho);
        /*if (lisTonKho != null) {
            for (TonKho tk : lisTonKho) {
                result = +tk.getTonkhoTon();
                System.out.println("result:" + result);
            }
        }*/
        
        return tonkho;
    }
    // phuc.lc 22-12-2010 : Lay danh sach khoa nhap nuoc (phan he Dinh duong)
    public List findKhoaNhapNuoc() {
        String sql = "";

		sql = "select object(o) from DmKhoa as o "
                        + "where o.dmkhoaMa not like 'BGD' and o.dmkhoaMa not like 'KCH' and o.dmkhoaMa not like 'KBH' and o.dmkhoaMa not like 'KTE' " 
                        + " and o.dmkhoaMa not like 'KTL' and o.dmkhoaMa not like 'KNT' and o.dmkhoaMa not like 'KTD'"; 
				

		return em.createQuery(sql)			
				.getResultList();

    }

    public HashMap<String,DmDoiTuong> findByDoiTuongThuPhi() {
        List<DmDoiTuong> lst = em.createQuery("select object(o) from DmDoiTuong as o where o.dmdoituongThuphi = 1 and dmdoituongDt = 1").getResultList();
        HashMap<String,DmDoiTuong> hm = new HashMap<String,DmDoiTuong>();
        for(DmDoiTuong o : lst){
            hm.put(o.getDmdoituongMa(), o);
        }
        return hm;
    }

    public DmTang findByDmTangDefault(Integer dmKhoaMaso) {
        System.out.println("findByDmTangDefault -- dmKhoaMaso: " + dmKhoaMaso);

        String sSQL = "Select object(o) from DmTang o where o.dmkhoaMaso.dmkhoaMaso = :dmKhoaMaso and o.dmtangDefault = 1";
        Query q = em.createQuery(sSQL);
        q.setParameter("dmKhoaMaso", dmKhoaMaso);

        List<DmTang> lstTang = q.getResultList();
        if (lstTang != null && lstTang.size() > 0) {
            return (DmTang) lstTang.get(0);
        }
        
        // 20120315 bao.ttc: danh cho TH mot so Khoa khong co tang default
        System.out.println("findByDmTangDefault -- TH Khong co tang default: " + dmKhoaMaso);
        String sSQL2 = "Select object(o) from DmTang o where o.dmkhoaMaso.dmkhoaMaso = :dmKhoaMaso";
        Query q2 = em.createQuery(sSQL2);
        q2.setParameter("dmKhoaMaso", dmKhoaMaso);

        lstTang = q2.getResultList();
        if (lstTang != null && lstTang.size() > 0) {
            return (DmTang) lstTang.get(0);
        }

        return null;
    }
    
    private int xoaDuLieuTam(){
         Query q = em.createQuery("delete from TmpBaocaoThekho");
         return q.executeUpdate();
    }
   //chưa chỉnh sửa query :11/23/2011
     public boolean exportDataForTheKho(Integer khoMaso, Date tuNgay, Date denNgay, String maLienket, Double tonDau, Double dongia, String loaiIn){
        System.out.println("---------Begin: getDataForTheKho ------------");
        System.out.println("Loai In: " + loaiIn);
        xoaDuLieuTam();
        try{
            //Neu loai In la in the kho kho chinh thi dung cau query sSQL1, nguoc lai dung sSQL2
            String sSQL1 = "SELECT CONCAT(a.PHIEUNHAPKHO,a.LOAIPHIEU,a.TONKHOMA) Id, a.NGAYTHANG, a.CHUNGTUNXT, a.PHIEUNHAPKHO, a.LOAIPHIEU, a.DIENGIAI, " +
                    "a.SOLUONGNHAP, 0 THANHTIENNHAP, a.SOLUONGXUAT, 0 THANHTIENXUAT, a.SOLUONGTON, 0 THANHTIENTON " +
                    "FROM " +
                    "(SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, CTXK.PHIEUXUATKHO_MA PHIEUNHAPKHO, 'N' LOAIPHIEU, 'Nhập thuốc từ Kho chính' DIENGIAI, " +
                        "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO  TK, CT_XUAT_KHO_TMP CTXK, DM_NHA_CUNG_CAP NCC, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') " +
                    "AND TK.TONKHO_NHAP >0 " +
                    "AND TK.TONKHO_MA = CTXK.TONKHO_MA " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "GROUP BY TK.TONKHO_MA " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                       "'' CHUNGTUNXT, PXK.PHIEUXUATKHO_MA PHIEUNHAPKHO, 'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PXK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI, " +
                       "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_KHO CTXK, PHIEU_XUAT_KHO PXK, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') " +
                    "AND CTXK.PHIEUXUATKHO_MA = PXK.PHIEUXUATKHO_MA " +
                    "AND CTXK.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 "+
                    "AND (PXK.PHIEUDT_MA IS NOT NULL OR PXK.PHIEUXUATKHO_NGAYGIOPHAT IS NOT NULL) " +
                    "AND (PXK.PHIEUDT_MA IS NULL OR PXK.PHIEUXUATKHO_NGAYGIOPHAT IS NULL) " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                       "'' CHUNGTUNXT, PXBH.TIEPDON_MA PHIEUNHAPKHO, 'X' LOAIPHIEU, 'Xuất BN' DIENGIAI, " +
                       "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON "+
                    "FROM TON_KHO TK, CT_XUAT_BH CTXBH, PHIEU_XUAT_BH PXBH, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') " +
                    "AND CTXBH.PHIEUXUATBH_MA = PXBH.PHIEUXUATBH_MA " +
                    "AND CTXBH.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL "+
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                       "'' CHUNGTUNXT, PXBH.HSBA_SOVAOVIEN PHIEUNHAPKHO, 'X' LOAIPHIEU, 'Xuất BN' DIENGIAI, " +
                       "TK.TONKHO_NHAP SOLUONGNHAP,	TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_BH_XUAT_VIEN CTXBH, PHIEU_XUAT_BH_XUAT_VIEN PXBH, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') "+
                    "AND CTXBH.PHIEUXUATBHXV_MA = PXBH.PHIEUXUATBHXV_MA " +
                    "AND CTXBH.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                       "'' CHUNGTUNXT, PTK.PHIEUTRAKHO_MA PHIEUNHAPKHO, 'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI, " +
                       "TK.TONKHO_TRA SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_TRA_KHO_TMP CTTK, PHIEU_TRA_KHO PTK, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') " +
                    "AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA " +
                    "AND CTTK.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT CTTK.TONKHO_MA TONKHOMA , CTTK.CTTRAKHO_MALK MALIENKET, PTK.DMKHOA_NHAN MAKHO, PTK.PHIEUTRAKHO_NGAYGIOCN NGAYTHANG, " +
                       "'' CHUNGTUNXT, PTK.PHIEUTRAKHO_MA PHIEUNHAPKHO, 'T' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PTK.DMKHOA_TRA = DMK.DMKHOA_MASO) DIENGIAI, " +
                       "CTTK.CTTRAKHO_SOLUONG SOLUONGNHAP, 0 SOLUONGXUAT, 0 SOLUONGTON " +
                    "FROM CT_TRA_KHO CTTK, PHIEU_TRA_KHO PTK, DM_KHOA DMK " +
                    "WHERE PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO " +
                    "AND (DMK.DMKHOA_MA = 'KBH' or DMK.DMKHOA_MA = 'KTE' or DMK.DMKHOA_MA = 'KNT') " +
                    "AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA " +
                    "AND PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO " +
                    "AND DATE(PTK.PHIEUTRAKHO_NGAYGIOCN) >= :tuNgay AND DATE(PTK.PHIEUTRAKHO_NGAYGIOCN) <= :denNgay "
                    + "UNION ALL"
                    + "SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,  "
                    + "'' CHUNGTUNXT, '' PHIEUNHAPKHO, 'K' LOAIPHIEU, 'Kiểm kê kho tăng' DIENGIAI,  "
                    + "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON  "
                    + "FROM TON_KHO TK, KIEM_KE_KHO KK, DM_KHOA DMK  "
                    + "WHERE TK.TONKHO_MA = KK.TONKHO_MA "
                    + "AND TK.DMKHOA_MASO = DMK.DMKHOA_MASO  "
                    + "AND (DMK.DMKHOA_MA = 'KBH' OR DMK.DMKHOA_MA = 'KTE' OR DMK.DMKHOA_MA = 'KNT')  "
                    + "AND TK.TONKHO_NHAP >0  "
                    + "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay  "
                    + "GROUP BY TK.TONKHO_MA "
                    + "UNION ALL "
                    + "SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,  "
                    + " '' CHUNGTUNXT, '' PHIEUNHAPKHO, 'K' LOAIPHIEU, 'Kiểm kê kho giảm' DIENGIAI,  "
                    + "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON  "
                    + "FROM TON_KHO TK, KIEM_KE_KHO KK, DM_KHOA DMK  "
                    + "WHERE TK.TONKHO_MA = KK.TONKHO_MA "
                    + "AND TK.DMKHOA_MASO = DMK.DMKHOA_MASO  "
                    + "AND (DMK.DMKHOA_MA = 'KBH' OR DMK.DMKHOA_MA = 'KTE' OR DMK.DMKHOA_MA = 'KNT')  "
                    + "AND TK.TONKHO_XUAT >0  "
                    + "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay  "
                    + "GROUP BY TK.TONKHO_MA "
                    +  ") a " +
                    "WHERE a.MALIENKET = :malienket AND a.MAKHO = :khoMaso ORDER BY a.NGAYTHANG";
            String sSQL2 = "SELECT CONCAT(a.PHIEUNHAPKHO,a.LOAIPHIEU,a.TONKHOMA) Id, a.NGAYTHANG, a.CHUNGTUNXT, a.PHIEUNHAPKHO, a.LOAIPHIEU, a.DIENGIAI, " +
                    "a.SOLUONGNHAP, 0 THANHTIENNHAP, a.SOLUONGXUAT, 0 THANHTIENXUAT, a.SOLUONGTON, 0 THANHTIENTON " +
                    "FROM " +
                    "(SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET , TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "PNK.PHIEUNHAPKHO_SOHOADON CHUNGTUNXT, TK.PHIEUNHAPKHO_MA PHIEUNHAPKHO, 'N' LOAIPHIEU, NCC.DMNHACUNGCAP_TEN DIENGIAI, " +
			"TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO  TK, PHIEU_NHAP_KHO PNK, DM_NHA_CUNG_CAP NCC, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND TK.TONKHO_NHAP >0 " +
                    "AND PNK.PHIEUNHAPKHO_MA = TK.PHIEUNHAPKHO_MA " +
                    "AND NCC.DMNHACUNGCAP_MASO = TK.TONKHO_NOIBAN " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, PXK.PHIEUXUATKHO_MA PHIEUNHAPKHO,'X' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PXK.DMKHOA_NHAN = DMK.DMKHOA_MASO) DIENGIAI, " +
			"TONKHO_NHAP SOLUONGNHAP, TONKHO_XUAT SOLUONGXUAT, TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_KHO CTXK, PHIEU_XUAT_KHO PXK, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND CTXK.PHIEUXUATKHO_MA = PXK.PHIEUXUATKHO_MA " +
                    "AND CTXK.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND (PXK.PHIEUDT_MA IS NOT NULL OR PXK.PHIEUXUATKHO_NGAYGIOPHAT IS NOT NULL) " +
                    "AND (PXK.PHIEUDT_MA IS NULL OR PXK.PHIEUXUATKHO_NGAYGIOPHAT IS NULL) " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +                    
                        "'' CHUNGTUNXT, PTNCC.PHIEUTRANHACUNGCAP_MA PHIEUNHAPKHO,'X' LOAIPHIEU, CONCAT(DMK.DMKHOA_TEN,' trả nhà cung cấp') DIENGIAI, " +
        		"TONKHO_NHAP SOLUONGNHAP, TONKHO_XUAT SOLUONGXUAT, TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_TRA_NHA_CUNG_CAP CTTNCC, PHIEU_TRA_NHA_CUNG_CAP PTNCC, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND CTTNCC.PHIEUTRANHACUNGCAP_MA = PTNCC.PHIEUTRANHACUNGCAP_MA " +
                    "AND CTTNCC.TONKHO_MA = TK.TONKHO_MA " +
                    "AND PTNCC.DMKHOA_XUAT = DMK.DMKHOA_MASO " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, CTXBHBK.TIEPDON_MA PHIEUNHAPKHO,'X' LOAIPHIEU, 'Xuất BN' DIENGIAI, " +
			"TONKHO_NHAP SOLUONGNHAP, TONKHO_XUAT SOLUONGXUAT, TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_BH_THUOCBK CTXBHBK, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND CTXBHBK.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, PXBH.TIEPDON_MA PHIEUNHAPKHO,'X' LOAIPHIEU, 'Xuất BN' DIENGIAI, " +
			"TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_BH CTXBH, PHIEU_XUAT_BH PXBH, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND CTXBH.PHIEUXUATBH_MA = PXBH.PHIEUXUATBH_MA " +
                    "AND CTXBH.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT TK.TONKHO_MA TONKHOMA, TK.TONKHO_MALIENKET MALIENKET, TK.TONKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, PXBH.HSBA_SOVAOVIEN PHIEUNHAPKHO,'X' LOAIPHIEU, 'Xuất BN' DIENGIAI, " +
			"TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON " +
                    "FROM TON_KHO TK, CT_XUAT_BH_XUAT_VIEN CTXBH, PHIEU_XUAT_BH_XUAT_VIEN PXBH, DM_KHOA DMK " +
                    "WHERE TK.DMKHOA_MASO = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND CTXBH.PHIEUXUATBHXV_MA = PXBH.PHIEUXUATBHXV_MA " +
                    "AND CTXBH.TONKHO_MA = TK.TONKHO_MA " +
                    "AND TK.TONKHO_XUAT >0 " +
                    "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay " +
                    "UNION ALL " +
                    "SELECT CTTK.TONKHO_MA TONKHOMA , CTTK.CTTRAKHO_MALK MALIENKET, PTK.PHIEUTRAKHO_NGAYGIOCN NGAYTHANG, " +
                        "'' CHUNGTUNXT, PTK.PHIEUTRAKHO_MA PHIEUNHAPKHO, 'T' LOAIPHIEU, (SELECT DMK.DMKHOA_TEN FROM DM_KHOA DMK WHERE PTK.DMKHOA_TRA = DMK.DMKHOA_MASO) DIENGIAI, " +
			"CTTK.CTTRAKHO_SOLUONG SOLUONGNHAP, 0 SOLUONGXUAT, 0 SOLUONGTON " +
                    "FROM CT_TRA_KHO CTTK, PHIEU_TRA_KHO PTK, DM_KHOA DMK " +
                    "WHERE PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO " +
                    "AND DMK.DMKHOA_MA = 'KCH' " +
                    "AND PTK.PHIEUTRAKHO_MA = CTTK.PHIEUTRAKHO_MA " +
                    "AND PTK.DMKHOA_NHAN = DMK.DMKHOA_MASO " +
                    "AND DATE(PTK.PHIEUTRAKHO_NGAYGIOCN) >= :tuNgay AND DATE(PTK.PHIEUTRAKHO_NGAYGIOCN) <= :denNgay "
                    + "UNION ALL"
                    + "SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,  "
                    + "'' CHUNGTUNXT, '' PHIEUNHAPKHO, 'K' LOAIPHIEU, 'Kiểm kê kho tăng' DIENGIAI,  "
                    + "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON  "
                    + "FROM TON_KHO TK, KIEM_KE_KHO KK, DM_KHOA DMK  "
                    + "WHERE TK.TONKHO_MA = KK.TONKHO_MA "
                    + "AND TK.DMKHOA_MASO = DMK.DMKHOA_MASO  "
                    + "AND DMK.DMKHOA_MA = 'KCH' "
                    + "AND TK.TONKHO_NHAP >0  "
                    + "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay  "
                    + "GROUP BY TK.TONKHO_MA "
                    + "UNION ALL "
                    + "SELECT TK.TONKHO_MA TONKHOMA , TK.TONKHO_MALIENKET MALIENKET, TK.DMKHOA_MASO MAKHO, TK.TONKHO_NGAYGIOCN NGAYTHANG,  "
                    + " '' CHUNGTUNXT, '' PHIEUNHAPKHO, 'K' LOAIPHIEU, 'Kiểm kê kho giảm' DIENGIAI,  "
                    + "TK.TONKHO_NHAP SOLUONGNHAP, TK.TONKHO_XUAT SOLUONGXUAT, TK.TONKHO_TON SOLUONGTON  "
                    + "FROM TON_KHO TK, KIEM_KE_KHO KK, DM_KHOA DMK  "
                    + "WHERE TK.TONKHO_MA = KK.TONKHO_MA "
                    + "AND TK.DMKHOA_MASO = DMK.DMKHOA_MASO  "
                    + "AND DMK.DMKHOA_MA = 'KCH' "
                    + "AND TK.TONKHO_XUAT >0  "
                    + "AND DATE(TK.TONKHO_NGAYGIOCN) >= :tuNgay AND DATE(TK.TONKHO_NGAYGIOCN) <= :denNgay  "
                    + "GROUP BY TK.TONKHO_MA "
                    + ") a " +
                    "WHERE a.MALIENKET = :malienket ORDER BY a.NGAYTHANG";
            if(loaiIn.equals("KC")){
                Query q = em.createNativeQuery(sSQL2, VTonkhoKhochinh.class);
                q.setParameter("tuNgay", tuNgay);
                q.setParameter("denNgay", denNgay);
                q.setParameter("malienket", maLienket);
                List<VTonkhoKhochinh> lstDataTheKho = q.getResultList();
                if(lstDataTheKho != null && lstDataTheKho.size() > 0){
                    System.out.println("lstDataTheKho.size= "+lstDataTheKho.size());
                    //Insert vao tmp_baocao_thekho neu khac null va update cac cot thanh tien nhap, thanh tien xuat, soluong ton, thanh tien ton
                    Double soluongTon = 0.0;
                    Double soluongTonOld = 0.0;
                    int i = 0;
                    for(VTonkhoKhochinh dataTheKho:lstDataTheKho){
                        TmpBaocaoThekho data = new TmpBaocaoThekho();
                        if(i == 0){
                            soluongTon = tonDau + (dataTheKho.getSoluongnhap() - dataTheKho.getSoluongxuat());
                        }else{
                            soluongTon = soluongTonOld + (dataTheKho.getSoluongnhap() - dataTheKho.getSoluongxuat());
                        }
                        data.setChungtunxt(dataTheKho.getChungtunxt());
                        data.setDiengiai(dataTheKho.getDiengiai());
                        data.setLoaiphieu(dataTheKho.getLoaiphieu());
                        data.setPhieunhapkho(dataTheKho.getPhieunhapkho());
                        data.setNgaythang(dataTheKho.getNgaythang());

                        data.setSoluongnhap(dataTheKho.getSoluongnhap());
                        data.setThanhtiennhap(dataTheKho.getSoluongnhap()* dongia);
                        data.setSoluongxuat(dataTheKho.getSoluongxuat());
                        data.setThanhtienxuat(dataTheKho.getSoluongxuat() * dongia);
                        data.setSoluongton(soluongTon);
                        data.setThanhtienton(soluongTon * dongia);
                        soluongTonOld = soluongTon;
                        em.persist(data);
                        i++;
                    }
                }
            }else{//Kho le
                Query q = em.createNativeQuery(sSQL1, VTonkhoKhole.class);
                q.setParameter("tuNgay", tuNgay);
                q.setParameter("denNgay", denNgay);
                q.setParameter("malienket", maLienket);
                q.setParameter("khoMaso", khoMaso);
                List<VTonkhoKhole> lstDataTheKho = q.getResultList();
                if(lstDataTheKho != null && lstDataTheKho.size() > 0){
                    System.out.println("lstDataTheKho.size= "+lstDataTheKho.size());
                    //Insert vao tmp_baocao_thekho neu khac null va update cac cot thanh tien nhap, thanh tien xuat, soluong ton, thanh tien ton
                    Double soluongTon = 0.0;
                    Double soluongTonOld = 0.0;
                    int i = 0;
                    for(VTonkhoKhole dataTheKho:lstDataTheKho){
                        TmpBaocaoThekho data = new TmpBaocaoThekho();
                        if(i == 0){
                            soluongTon = tonDau + (dataTheKho.getSoluongnhap() - dataTheKho.getSoluongxuat());
                        }else{
                            soluongTon = soluongTonOld + (dataTheKho.getSoluongnhap() - dataTheKho.getSoluongxuat());
                        }
                        data.setChungtunxt(dataTheKho.getChungtunxt());
                        data.setDiengiai(dataTheKho.getDiengiai());
                        data.setLoaiphieu(dataTheKho.getLoaiphieu());
                        data.setPhieunhapkho(dataTheKho.getPhieunhapkho());
                        data.setNgaythang(dataTheKho.getNgaythang());

                        data.setSoluongnhap(dataTheKho.getSoluongnhap());
                        data.setThanhtiennhap(dataTheKho.getSoluongnhap()* dongia);
                        data.setSoluongxuat(dataTheKho.getSoluongxuat());
                        data.setThanhtienxuat(dataTheKho.getSoluongxuat() * dongia);
                        data.setSoluongton(soluongTon);
                        data.setThanhtienton(soluongTon * dongia);
                        soluongTonOld = soluongTon;
                        em.persist(data);
                        i++;
                    }
                }
            }
        }catch(Exception er){
            System.out.println("Error: " + er.getMessage());
            return false;
        }
        return true;
    }

    public List<DmPhanLoaiThuoc> getListDmPhanLoaiThuoc(Integer dmLoaiThuocMaso, boolean notIn){
        String sSQL = "";
        if(notIn == true){//Lay tat ca dmphanloaithuoc khong co dmLoaithuocMaso
            sSQL = "SELECT object(o) FROM DmPhanLoaiThuoc o WHERE o.dmloaithuocMaso.dmloaithuocMaso <> :dmloaithuocMaso";
        }else{//Chi lay dmphanloaithuoc voi dmLoaiThuocMaso
            sSQL = "SELECT object(o) FROM DmPhanLoaiThuoc o WHERE o.dmloaithuocMaso.dmloaithuocMaso = :dmloaithuocMaso";
        }
        Query q = em.createQuery(sSQL);
        q.setParameter("dmloaithuocMaso", dmLoaiThuocMaso);
	return q.getResultList();
    }
}


