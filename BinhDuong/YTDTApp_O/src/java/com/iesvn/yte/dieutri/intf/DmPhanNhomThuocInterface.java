/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;
import com.iesvn.yte.entity.DmPhanNhomThuoc;
import java.util.List;
/**
 *
 * @author user01
 */
public interface DmPhanNhomThuocInterface {
    void create(DmPhanNhomThuoc dmPhanNhomThuoc);
    void edit(DmPhanNhomThuoc dmPhanNhomThuoc);
    void remove(DmPhanNhomThuoc dmPhanNhomThuoc);
    DmPhanNhomThuoc find(Object id);
    List<DmPhanNhomThuoc> findAll();
}
