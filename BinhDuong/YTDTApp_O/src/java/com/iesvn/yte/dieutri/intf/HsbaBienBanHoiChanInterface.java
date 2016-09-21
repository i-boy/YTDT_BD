/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaBienBanHoiChanInterface {

public     HsbaBienBanHoiChan createHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList);
public     HsbaBienBanHoiChan editHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan, List<DtDmNhanVien> thanhVienList);

public     void create(HsbaBienBanHoiChan hsbaBienBanHoiChan);

public     void edit(HsbaBienBanHoiChan hsbaBienBanHoiChan);

public     void remove(HsbaBienBanHoiChan hsbaBienBanHoiChan);

public     HsbaBienBanHoiChan find(Object id);

public     List<HsbaBienBanHoiChan> findAll();

public List<DtDmNhanVien> findThanhVienByHsbabbhcMa(String ma);

}


