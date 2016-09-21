/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.NguoiDung;
import com.iesvn.yte.entity.VaiTro;
import java.util.List;

/**
 *
 * @author Thanh
 */
public interface VaiTroInterface {
   void create(VaiTro vaiTro);

    void edit(VaiTro vaiTro);

    void remove(VaiTro vaiTro);

    VaiTro find(Object id);
 public VaiTro findByMa(String  maVT);
    List<VaiTro> findAll();
    public String capNhatVaiTro(Integer maSoNhanVien,NguoiDung nguoiDung, List<VaiTro> lstVaiTro);
}
