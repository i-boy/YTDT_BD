/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmClsBangGiaInterface {

    public void create(DtDmClsBangGia dtDmClsBangGia);

    public void edit(DtDmClsBangGia dtDmClsBangGia);

    public void remove(DtDmClsBangGia dtDmClsBangGia);

    public DtDmClsBangGia find(Object id);

    public List<DtDmClsBangGia> findAll();

    public List<DtDmClsBangGia> getDtDmClsBangGiaByMaSoKhoa(Integer maSoKhoa);
    
    //public List<DtDmClsBangGia> findByAllConditions(String ma, String diengiai, Integer phanloai) ;
    public List<DtDmClsBangGia> findByAllConditions(String ma, String diengiai, Integer phanloai, boolean searchDaxoa) ;
    
    public void create(DtDmClsBangGia dtDmClsBangGia, DmKhoa dmkhoa) ;
    
    public void edit(DtDmClsBangGia dtDmClsBangGia, DtDmClsKhoa clsKhoa) ;

    public DtDmClsBangGia findByMa(String ma);
}


