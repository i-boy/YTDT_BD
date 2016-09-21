/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaLapTrichBienBanHoiChan;
import java.util.List;

/**
 *
 * @author quang
 */
public interface HsbaLapTrichBienBanHoiChanInterface {
public     void create(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan);

public     void edit(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan);

//public     HsbaLapTrichBienBanHoiChan createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList);
public     String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList);
//public     String createHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList);

//public     HsbaLapTrichBienBanHoiChan editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList);
public     String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList);
//public     String editHsbaLapTrichBienBanHoiChan(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList);

public     void remove(HsbaLapTrichBienBanHoiChan hsbaLapTrichBienBanHoiChan);

public     HsbaLapTrichBienBanHoiChan find(Object id);

public     List<HsbaLapTrichBienBanHoiChan> findAll();
public String insert(HsbaLapTrichBienBanHoiChan obj);
public String update(HsbaLapTrichBienBanHoiChan obj);
public HsbaLapTrichBienBanHoiChan findByHsbaltbbhcMa(String ma);
public List<DtDmNhanVien> findBacsiByHsbaltbbhcMa(String ma);
//public List<DtDmNhanVien> findBacsigmByHsbapptttMa(String ma);

}
