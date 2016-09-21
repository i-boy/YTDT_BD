/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import java.util.List;

/**
 *
 * @author root
 */
public interface DtDmNhanVienKhoaInterface {

  public  void create(DtDmNhanvienKhoa dtDmNhanvienKhoa);

  public  void edit(DtDmNhanvienKhoa dtDmNhanvienKhoa);

  public  void remove(DtDmNhanvienKhoa dtDmNhanvienKhoa);

  public  DtDmNhanvienKhoa find(Object id);

 public   List<DtDmNhanvienKhoa> findAll();
 public DtDmNhanvienKhoa findByMaNguoiDung(DtDmNhanVien tenNd);

}


