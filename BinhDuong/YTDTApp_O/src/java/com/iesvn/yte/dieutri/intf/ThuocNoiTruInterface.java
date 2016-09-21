/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;

import java.util.List;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ThuocNoiTruInterface {

    public void create(ThuocNoiTru thuocNoiTru);

    public void edit(ThuocNoiTru thuocNoiTru);

    public void remove(ThuocNoiTru thuocNoiTru);

    public ThuocNoiTru find(Object id);

    public List<ThuocNoiTru> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> findByMaPhieu(java.lang.String thuocnoitruMaphieu);

    public java.lang.String delHuyTNT(com.iesvn.yte.dieutri.entity.ThuocNoiTru tnt);

    public java.lang.String createThuocNoiTru(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc, List<ThuocNoiTru> listThuocDel, HashMap<Integer, List> hsmListThuocDYNoiTru);

    public String createThuocNoiTruXuatVien(List<ThuocNoiTruXuatVien> listThuoc, List<ThuocNoiTruXuatVien> listThuocDel);

    public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay);

    public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer dmTangMaso, String ngay);

    public List<ThuocNoiTru> findDanhSachTNTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmkhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso);

    public List<ThuocNoiTru> findDanhSachTNTForLapPhieuDuTruTra(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso);

    public int getStt();

    public void removeAll(java.lang.String tntMa);

    public java.lang.String findMaPhieu(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc);

    public int findStt(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc);

    public void removeItem(java.lang.String listtntMa, java.lang.String tntMa);

    public List<ThuocNoiTru> findByHsbaAndKhoaAndTuTruc(String soVaoVien, String khoaMa);

    public List<ThuocNoiTru> findByHsbaKhoa(Integer hsbaKhoaMaso);

    public Boolean benhNhanTraThuoc(List<ThuocNoiTru> listTnt);

    public List<ThuocNoiTru> findTNTByKhoaMaSoAndMaLK(Integer khoaMaSo, String maLK);

    public List<ThuocNoiTru> findTNTByMaPDT(Integer khoaMaSo, String maphieuDT);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa);

    public List<ThuocNoiTru> findBySoVaoVien(String soVaoVien);

    public List<ThuocNoiTru> findTNTXuatVienBySoVaoVien(String soVaoVien);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLan(String soVaoVien, String khoaMa, Integer lan);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndNgay(String soVaoVien, String khoaMa, String ngay);

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(String soVaoVien, String khoaMa, Integer lan, Date ngayNhanThuoc);

    public List<ThuocNoiTru> findDanhSachTNTDuTruLinhTuTruc(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMa, Integer dmTangMaso);

    public List<ThuocNoiTru> checkThuocNoiTruTruocNgayVaoVien(String sovaovien);

    public List<ThuocNoiTru> checkThuocNoiTruSauNgayRaVien(String sovaovien, Date ngayravien);
}
