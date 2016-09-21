/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ThuocPhongKhamInterface;

import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
/**
 *
 * @author LENOVO 3000 Y410
 */
public class ThuocPhongKhamDelegate {

    private ThuocPhongKhamInterface thuocphongkhamService;

    public static ThuocPhongKhamDelegate getInstance() {
        return new ThuocPhongKhamDelegate();
    }

    private ThuocPhongKhamInterface lookupService() {
        return (ThuocPhongKhamInterface) LookupServiceUtils.lookupService("ThuocPhongKhamFacade");
    }

    public void create(ThuocPhongKham thuocPhongKham) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        thuocphongkhamService.create(thuocPhongKham);
    }

    public void edit(ThuocPhongKham thuocPhongKham) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        thuocphongkhamService.edit(thuocPhongKham);
    }

    public void remove(ThuocPhongKham thuocPhongKham) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        thuocphongkhamService.remove(thuocPhongKham);
    }

    public ThuocPhongKham find(Object id) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.find(id);
    }

    public List<ThuocPhongKham> findAll() {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findAll();
    }

    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai) {

        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByMaTiepDonAndMaBanKham(maTiepDon, maBanKham, loai);
    }

    public List<ThuocPhongKham> find2LoaiByMaTiepDonAndMaBanKham(String maTiepDon, String maBanKham, String loai1, String loai2) {

        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.find2LoaiByMaTiepDonAndMaBanKham(maTiepDon, maBanKham, loai1, loai2);
    }

    public List<ThuocPhongKham> findDanhSachTNGTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMaso) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findDanhSachTNGTDuTruLinh(tuNgay, denNgay, loaiMa, dmKhoaMaso, doituongMaso);
    }

    public String createToaThuocPhongKham(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru) {

        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.createToaThuocPhongKham(listTpk, thamKham, loaiThuocPhongKham, hsmListThuocDYNgoaiTru);
    }

    public String createKeToaQuayBenhVien(List<ThuocPhongKham> listTpk, ThamKham thamKham, String loaiThuocPhongKham, HashMap<Integer,List> hsmListThuocDYNgoaiTru) {

        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.createKeToaQuayBenhVien(listTpk, thamKham, loaiThuocPhongKham, hsmListThuocDYNgoaiTru);
    }

    public List<ThuocPhongKham> findByThamKham(Integer thamkhamMa, String loai) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByThamKham(thamkhamMa, loai);
    }

    public List<ThuocPhongKham> findByThamKhamVaNgay(Integer thamkhamMa, Date tuNgay, Date denNgay) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByThamKhamVaNgay(thamkhamMa, tuNgay, denNgay);
    }

    public String createThuocPhongKham(List<ThuocPhongKham> listTpk, HsThtoank hsttk, String loai) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.createThuocPhongKham(listTpk, hsttk, loai);
    }

    public List<ThuocPhongKham> findByMaPhieu(String maPhieu) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByMaPhieu(maPhieu);
    }

    public List<ThuocPhongKham> findByThamKhamHoanThu(Integer thamkhamMa) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByThamKhamHoanThu(thamkhamMa);
    }

    public Boolean createHoanThu(List<ThuocPhongKham> listTpk, HsThtoank hsttk) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.createHoanThu(listTpk, hsttk);
    }

    public List<ThuocPhongKham> findByMaTiepDonAndMaBanKhamForCapNhatPhieuXuatBH(String maTiepDon, String maBanKham, String loai) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByMaTiepDonAndMaBanKhamForCapNhatPhieuXuatBH(maTiepDon, maBanKham, loai);
    }

    public List<ThuocPhongKham> findByMaTiepDon(String maTiepDon, String loai) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByMaTiepDon(maTiepDon, loai);
    }

    public List<ThuocPhongKham> find2LoaiByMaTiepDon(String maTiepDon, String loai1, String loai2) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.find2LoaiByMaTiepDon(maTiepDon, loai1, loai2);
    }

    public List<ThuocPhongKham> findByKhoaKham(String maTiepDon, String loai, int khoaMaSo) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.findByKhoaKham(maTiepDon, loai, khoaMaSo);
    }
    
    public int updateDaTT2Null(Date tungay, Date denngay) {
        if (thuocphongkhamService == null) {
            thuocphongkhamService = lookupService();
        }
        return thuocphongkhamService.updateDaTT2Null(tungay, denngay);
    }
}


