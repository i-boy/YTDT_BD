/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.DmThuoc;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DmThuocInterface {

public     void create(DmThuoc dtDmTinhBhyt);

public     void edit(DmThuoc dtDmTinhBhyt);

public     void remove(DmThuoc dtDmTinhBhyt);

public     DmThuoc find(Object id);

public List<DmThuoc> findAll();
public List<DmThuoc> findAll(Integer dmKhoaMaso);
public List<DmThuoc> findAll(String dmKhoaMa);
public List<DmThuoc> findAll(Integer dmKhoaMaso1, Integer dmKhoaMaso2);
public List<DmThuoc> findDongYAll();
public List<DmThuoc> findDmThuocBHYT();
public List<DmThuoc> findByLoaiPhanLoaiThuoc(String loaiThuocMa, String phanloaiThuocMa);
public List<DmThuoc> findByLoaiPhanLoaiThuocNhomThuocDVTKho(String loaiThuocMa, String phanloaiThuocMa, Integer khoMaso);
public List<DmThuoc> findByLoai_ListPhanLoaiThuoc(String loaiThuocMa, List<DmPhanLoaiThuoc> listPhanloaiThuocMa);
public DmThuoc findByMaThuoc(String dmthuocMa);
public DmThuoc findByTenThuoc(String dmthuocTen);
public boolean hasThuoInPhanLoaiThuoc(Integer phanloaiThuocMaso);
public boolean updateFieldTonKho();

}


