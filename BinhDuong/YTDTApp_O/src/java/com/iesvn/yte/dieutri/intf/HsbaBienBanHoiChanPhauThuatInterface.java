/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChanPhauThuat;
import java.util.List;

/**
 *
 * @author quang
 */
public interface HsbaBienBanHoiChanPhauThuatInterface {
public     void create(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat);

public     void edit(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat);

//public     HsbaBienBanHoiChanPhauThuat createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList);
//public     String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList);
//public     String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList);
public     String createHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList,  List<DtDmNhanVien> bacsitpkList);

//public     HsbaBienBanHoiChanPhauThuat editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList);
//public     String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiList);
public     String editHsbaBienBanHoiChanPhauThuat(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat, List<DtDmNhanVien> bacsiptvList, List<DtDmNhanVien> bacsigmList,  List<DtDmNhanVien> bacsitpkList);

public     void remove(HsbaBienBanHoiChanPhauThuat hsbaBienBanHoiChanPhauThuat);

public     HsbaBienBanHoiChanPhauThuat find(Object id);

public     List<HsbaBienBanHoiChanPhauThuat> findAll();
public String insert(HsbaBienBanHoiChanPhauThuat obj);
public String update(HsbaBienBanHoiChanPhauThuat obj);
public HsbaBienBanHoiChanPhauThuat findByHsbabbhcptMa(String ma);
public List<DtDmNhanVien> findBacsiptvByHsbabbhcptMa(String ma);
public List<DtDmNhanVien> findBacsigmByHsbabbhcptMa(String ma);
public List<DtDmNhanVien> findBacsitpkByHsbabbhcptMa(String ma);

}
