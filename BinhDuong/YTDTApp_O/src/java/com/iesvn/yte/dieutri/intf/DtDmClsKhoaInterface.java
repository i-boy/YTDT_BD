/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import java.util.List;

/**
 *
 * @author James Bond
 */
public interface DtDmClsKhoaInterface {

    public void create(DtDmClsKhoa dtDmClsKhoa);

    public void edit(DtDmClsKhoa dtDmClsKhoa);

    public void remove(DtDmClsKhoa dtDmClsKhoa);

    public DtDmClsKhoa find(Object id);

    public List<DtDmClsKhoa> findAll();
    
     public List<DtDmClsKhoa> findByMaSoKhoa(Integer maSoKhoa);
   
     public DtDmClsKhoa findByMaClsBangGia(Integer maSoClsBg) ;     
      
     public List<DtDmClsKhoa> findByMaSoCLS(Integer masoCLS);

}
