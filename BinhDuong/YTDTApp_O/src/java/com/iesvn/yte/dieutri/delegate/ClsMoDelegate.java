/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.delegate;

import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.ClsMoInterface;

import com.iesvn.yte.dieutri.entity.ClsMo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class ClsMoDelegate {

    private ClsMoInterface clsmoService;

    public static ClsMoDelegate getInstance() {
        return new ClsMoDelegate();
    }

    private ClsMoInterface lookupService() {
        return (ClsMoInterface) LookupServiceUtils.lookupService("ClsMoFacade");
    }

    public void create(ClsMo clsMo) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        clsmoService.create(clsMo);
    }

    public void edit(ClsMo clsMo) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        clsmoService.edit(clsMo);
    }

    public void remove(ClsMo clsMo) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        clsmoService.remove(clsMo);
    }

    public ClsMo find(Object id) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.find(id);
    }

    public List<ClsMo> findAll() {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findAll();
    }

    public List<ClsMo> findByMaPhieu(String maPhieu) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findByMaPhieu(maPhieu);
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findBySoVaoVienAndKhoaMa(soVaoVien, khoaMa);
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer tangMaso) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findBySoVaoVienAndKhoaMaAndTang(soVaoVien, khoaMa, tangMaso);
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findBySoVaoVienAndKhoaMaAndTangAndNgay(soVaoVien, khoaMa, tangMaso, ngay);
    }

    public String createClsMoForCLSPhauThuat(List<ClsMo> listCLS, String soVaoVien, String khoaMa, Integer dmtangMaso, String ngay) {

        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.createClsMoForCLSPhauThuat(listCLS, soVaoVien, khoaMa, dmtangMaso, ngay);
    }

    public List<ClsMo> findBySoVaoVien(String soVaoVien) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findBySoVaoVien(soVaoVien);
    }

    public List<ClsMo> findBySoVaoVienAndKhoaMaAnhLan(String soVaoVien, String khoaMa, Integer lan) {

        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.findBySoVaoVienAndKhoaMaAnhLan(soVaoVien, khoaMa, lan);
    }

    public List<ClsMo> checkClsmoTruocNgayVaoVien(String sovaovien) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.checkClsmoTruocNgayVaoVien(sovaovien);
    }

    public List<ClsMo> checkClsmoSauNgayRaVien(String sovaovien, Date ngayravien) {
        if (clsmoService == null) {
            clsmoService = lookupService();
        }
        return clsmoService.checkClsmoSauNgayRaVien(sovaovien, ngayravien);
    }
}
