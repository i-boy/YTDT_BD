/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmLoaiKhoa;
import java.util.List;
/**
 *
 * @author ThoVNA
 */
public interface DmKhoaInterface {
    public void create(DmKhoa dmKhoa);
    public void edit(DmKhoa dmKhoa);
    public void remove(DmKhoa dmKhoa);
    public DmKhoa find(Object id);
    public List<DmKhoa> findAll();
    public Integer findMaSoByMa(String dmkhoaMa);
    public List<DmLoaiKhoa> findAllLoaiKhoaHavingKhoa();
    public List<DmKhoa> getKhoaHavingLoaiKhoaMa(String loaiKhoaMa);
    //Tho add
    public List<DmKhoa> getKhoLe();
     public List<DmKhoa> getKhoChinh_KhoLe();
    public List<DmKhoa> getKhoaLamSang();
    // phuc.lc add
    public List<DmKhoa> getKhoaCanLamSang() ;
    public List<DmKhoa> getKhoLe_TL_TD();
    public List<DmKhoa> getKhoaNT();
    public List<DmKhoa> getKhoaNgT();
    public Integer findMaSoByMasoThuoc(Integer dmthuocMaso);
    public List<DmKhoa> getKhoaNhom12();
    public List<DmKhoa> getKhoaNhom12NOTINKhoDuoc();
}
