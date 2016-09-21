/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ThanhDo
 */
public interface HsbaKhoaInterface {

    void create(HsbaKhoa hsbaKhoa);

    void edit(HsbaKhoa hsbaKhoa);

    void remove(HsbaKhoa hsbaKhoa);

    HsbaKhoa find(Object id);

    List<HsbaKhoa> findAll();

    public List<HsbaKhoa> findBySoVaoVien(String soVaoVien);

    public HsbaKhoa findBySoVaoVienAndKhoaMa(String soVaoVien, String khoaMa);
    
    public HsbaKhoa findBySoVaoVienAndKhoaMaAndTang(String soVaoVien, String khoaMa, Integer dmtangMaso);

    public HsbaKhoa findBySoVaoVienLastHsbaKhoa(String soVaoVien);

    public String benhNhanChuyenKhoa(HsbaKhoa hsbaKhoa, DmKhoa khoaChuyenDen, Date ngayGioChuyenKhoa, DmTang tangChuyenDen);
}
