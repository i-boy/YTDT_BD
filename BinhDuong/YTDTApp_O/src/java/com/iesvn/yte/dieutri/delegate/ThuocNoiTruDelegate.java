/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ThuocNoiTruInterface;

import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;

import java.util.List;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ThuocNoiTruDelegate {

    private ThuocNoiTruInterface thuocnoitruService;

    public static ThuocNoiTruDelegate getInstance() {
        return new ThuocNoiTruDelegate();
    }

    private ThuocNoiTruInterface lookupService() {
        return (ThuocNoiTruInterface) LookupServiceUtils.lookupService("ThuocNoiTruFacade");
    }

    public void create(ThuocNoiTru thuocNoiTru) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        thuocnoitruService.create(thuocNoiTru);
    }

    public void edit(ThuocNoiTru thuocNoiTru) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        thuocnoitruService.edit(thuocNoiTru);
    }

    public void remove(ThuocNoiTru thuocNoiTru) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        thuocnoitruService.remove(thuocNoiTru);
    }

    public ThuocNoiTru find(Object id) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.find(id);
    }

    public List<ThuocNoiTru> findAll() {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findAll();
    }

    public java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> findByMaPhieu(java.lang.String thuocnoitruMaphieu) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findByMaPhieu(thuocnoitruMaphieu);
    }

    public java.lang.String delHuyTNT(com.iesvn.yte.dieutri.entity.ThuocNoiTru tnt) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.delHuyTNT(tnt);
    }

    public java.lang.String createThuocNoiTru(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc, List<ThuocNoiTru> listThuocDel, HashMap<Integer, List> hsmListThuocDYNoiTru) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.createThuocNoiTru(listThuoc, listThuocDel, hsmListThuocDYNoiTru);
    }

    public String createThuocNoiTruXuatVien(List<ThuocNoiTruXuatVien> listThuoc, List<ThuocNoiTruXuatVien> listThuocDel) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.createThuocNoiTruXuatVien(listThuoc, listThuocDel);
    }

    public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(soVaoVien, khoaMa, lan, ngay);
    }

    public List<ThuocNoiTruXuatVien> findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer dmTangMaso, String ngay) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findThuocXVBySoVaoVienAndKhoaMaAndTangAndNgay(soVaoVien, khoaMa, dmTangMaso, ngay);
    }

    public List<ThuocNoiTru> findDanhSachTNTDuTruLinh(Date tuNgay, Date denNgay, String loaiMa, Integer dmkhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findDanhSachTNTDuTruLinh(tuNgay, denNgay, loaiMa, dmkhoaMaso, dmKhoMaso, doituongMaso, dmTangMaso);
    }

    public List<ThuocNoiTru> findDanhSachTNTForLapPhieuDuTruTra(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer dmKhoMaso, Integer doituongMaso, Integer dmTangMaso) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findDanhSachTNTForLapPhieuDuTruTra(tuNgay, denNgay, loaiMa, dmKhoaMaso, dmKhoMaso, doituongMaso, dmTangMaso);
    }

    public int getStt() {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.getStt();
    }

    public void removeAll(java.lang.String tntMa) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        thuocnoitruService.removeAll(tntMa);
    }

//    public void Cacul(com.iesvn.yte.dieutri.entity.ThuocNoiTru tnt) {
//        if (thuocnoitruService == null) {
//            thuocnoitruService = lookupService();
//        }
//        thuocnoitruService.Cacul(tnt);
//    }
    public java.lang.String findMaPhieu(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findMaPhieu(listThuoc);
    }

    public int findStt(java.util.List<com.iesvn.yte.dieutri.entity.ThuocNoiTru> listThuoc) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findStt(listThuoc);
    }

    public void removeItem(java.lang.String listtntMa, java.lang.String tntMa) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        thuocnoitruService.removeItem(listtntMa, tntMa);
    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMa(soVaoVien, khoaMa);
    }

    public Boolean benhNhanTraThuoc(List<ThuocNoiTru> listTnt) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.benhNhanTraThuoc(listTnt);
    }

    public List<ThuocNoiTru> findByHsbaAndKhoaAndTuTruc(String hsbaSovaovien, String khoaMa) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findByHsbaAndKhoaAndTuTruc(hsbaSovaovien, khoaMa);
    }

    public List<ThuocNoiTru> findByHsbaKhoa(Integer hsbaKhoaMaso) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findByHsbaKhoa(hsbaKhoaMaso);
    }

    public List<ThuocNoiTru> findBySoVaoVien(String soVaoVien) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVien(soVaoVien);
    }

    public List<ThuocNoiTru> findTNTXuatVienBySoVaoVien(String soVaoVien) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findTNTXuatVienBySoVaoVien(soVaoVien);
    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLan(String soVaoVien, String khoaMa, Integer lan) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMaAndLan(soVaoVien, khoaMa, lan);

    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgay(String soVaoVien, String khoaMa, Integer lan, String ngay) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMaAndLanAndNgay(soVaoVien, khoaMa, lan, ngay);
    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMaAndTangAndNgay(soVaoVien, khoaMa, tangMaso, ngay);
    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndNgay(String soVaoVien, String khoaMa, String ngay) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMaAndNgay(soVaoVien, khoaMa, ngay);
    }

    public List<ThuocNoiTru> findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(String soVaoVien, String khoaMa, Integer lan, Date ngayNhanThuoc) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(soVaoVien, khoaMa, lan, ngayNhanThuoc);
    }

    public List<ThuocNoiTru> findDanhSachTNTDuTruLinhTuTruc(Date tuNgay, Date denNgay, String loaiMa, Integer dmKhoaMaso, Integer doituongMa, Integer dmTangMaso) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.findDanhSachTNTDuTruLinhTuTruc(tuNgay, denNgay, loaiMa, dmKhoaMaso, doituongMa, dmTangMaso);
    }

    public List<ThuocNoiTru> checkThuocNoiTruTruocNgayVaoVien(String sovaovien) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.checkThuocNoiTruTruocNgayVaoVien(sovaovien);
    }

    public List<ThuocNoiTru> checkThuocNoiTruSauNgayRaVien(String sovaovien, Date ngayravien) {
        if (thuocnoitruService == null) {
            thuocnoitruService = lookupService();
        }
        return thuocnoitruService.checkThuocNoiTruSauNgayRaVien(sovaovien, ngayravien);
    }
}
