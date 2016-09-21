/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ClsMo;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ClsMoInterface {

    public void create(ClsMo clsMo);

    public void edit(ClsMo clsMo);

    public void remove(ClsMo clsMo);

    public ClsMo find(Object id);

    public List<ClsMo> findAll();

    public List<ClsMo> findByMaPhieu(String maPhieu);

    public List<ClsMo> findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa);

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer tangMaso);

    public List<ClsMo> findBySoVaoVienAndKhoaMaAndTangAndNgay(String soVaoVien, String khoaMa, Integer tangMaso, String ngay);

    public String createClsMoForCLSPhauThuat(List<ClsMo> listCLS, String soVaoVien, String khoaMa, Integer dmtangMaso, String ngay);

    public List<ClsMo> findBySoVaoVien(String soVaoVien);

    public List<ClsMo> findBySoVaoVienAndKhoaMaAnhLan(String soVaoVien, String khoaMa, Integer lan);

    public List<ClsMo> checkClsmoTruocNgayVaoVien(String sovaovien);

    public List<ClsMo> checkClsmoSauNgayRaVien(String sovaovien, Date ngayravien);
}
