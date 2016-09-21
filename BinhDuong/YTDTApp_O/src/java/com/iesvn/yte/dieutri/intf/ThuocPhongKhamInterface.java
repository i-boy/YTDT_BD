/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ThuocPhongKhamInterface {

    public void create(ThuocPhongKham thuocPhongKham);

    public void edit(ThuocPhongKham thuocPhongKham);

    public void remove(ThuocPhongKham thuocPhongKham);

    public ThuocPhongKham find(Object id);

    public List<ThuocPhongKham> findAll();

    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai);

    public List<ThuocPhongKham> find2LoaiByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai1, String loai2);

    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKhamForCapNhatPhieuXuatBH(String maTiepDon, String maBanKham, String loai);

    public List<ThuocPhongKham> findByThamKham(Integer thamkhamMa, String loai);

    public List<ThuocPhongKham> findByThamKhamVaNgay(Integer thamkhamMa, Date tuNgay, Date denNgay);

    public List<ThuocPhongKham> findDanhSachTNGTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMaso);

    public String createThuocPhongKham(List<ThuocPhongKham> listTpk, HsThtoank hsttk, String loai);

    public List<ThuocPhongKham> findByMaPhieu(String maPhieu);

    public List<ThuocPhongKham> findByThamKhamHoanThu(Integer thamkhamMa);

    public Boolean createHoanThu(List<ThuocPhongKham> listTpk, HsThtoank hsttk);

    public List<ThuocPhongKham> findByMaTiepDon(String maTiepDon, String loai);

    public List<ThuocPhongKham> find2LoaiByMaTiepDon(String maTiepDon, String loai1, String loai2);

    public List<ThuocPhongKham> findByKhoaKham(String maTiepDon, String loai, int khoaMaSo);

    public String createToaThuocPhongKham(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru);

    public String createKeToaQuayBenhVien(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru);

    public int updateDaTT2Null(Date tungay, Date denngay);
}


