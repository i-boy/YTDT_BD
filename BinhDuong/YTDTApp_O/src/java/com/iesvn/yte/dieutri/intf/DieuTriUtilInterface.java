/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TmpBaocaoThekho;
import com.iesvn.yte.entity.VaiTro;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DieuTriUtilInterface {

    public java.util.List<java.lang.Object> findByNgayGioCN(java.lang.Double ngaycn, java.lang.String clsName, java.lang.String fieldNgayGioCNName);
    
    public Object findByMaWithFormatMaPhieu(String ma, String clsName, String fieldMaName);
    
    public Object findByMaWithFormatMa(String ma, String clsName, String fieldMaName);
    
    public java.lang.Object findByMa(java.lang.String ma, java.lang.String clsName, java.lang.String fieldMaName);

    public Object findByMaSo(Integer maso, String clsName, String fieldMaSoName);

    public javax.persistence.EntityManager getEm();

    public void setEm(javax.persistence.EntityManager em);
    
     public List<VaiTro> getVaiTroByNguoiDungTenDangNhap(String tenDangNhap);
     
     public Integer getSoKhamBenh(String maBanKham);
     
     public String getProperty(String myKey);

     public String formatMa( String ma) ;

     public String formatMaPhieu(String ma) ;

     public List findByAllConditions(String clsName, String fieldMa, String fieldTen,String fieldDT, String ma,String ten, boolean dt);

     public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen,String fieldDT, String ma,String ten, boolean dt);

     public List findByAllConditions(String clsName, String fieldMa, String fieldTen, String ma,String ten);

     public void create(Object ob);

     public void edit(Object ob);

     public void remove(Object ob) ;
     
     public List findAll(String clsName,String fieldDT, boolean dt);
     
     public List findAll(String clsName);
     
     public List findAllChacon(String clsNameCon, String fieldMasoCon ,Integer masocon);

     public List findMaLike(String clsName, String fieldMa, String fieldDT, String ma, boolean dt);

     public List findTenLike(String clsName, String fieldTen, String ten);

     public double getGiaCuoi(String maThuoc);

     public double getTonKhoByMaThuocAndMaKhoa(String maThuoc, String maKhoa);
     
     public List findByAllConditionsSortAsc(String clsName, String fieldMa, String fieldTen,String fieldDT, String fieldPhanloai, String ma,String ten, Integer phanloai) ;
     public List findKhoaNhapNuoc() ;
     public HashMap<String,DmDoiTuong> findByDoiTuongThuPhi();
 public boolean exportDataForTheKho(Integer khoMaso, Date tuNgay, Date denNgay, String maLienket, Double tonDau, Double dongia, String loaiIn);

     public List<DmPhanLoaiThuoc> getListDmPhanLoaiThuoc(Integer dmLoaiThuocMaso, boolean notIn);
}


