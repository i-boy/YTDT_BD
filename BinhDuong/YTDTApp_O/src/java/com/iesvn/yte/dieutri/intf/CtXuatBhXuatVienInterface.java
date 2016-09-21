/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtXuatBhXuatVienInterface {

    public     void create(CtXuatBhXuatVien ctXuatBh);

    public     void edit(CtXuatBhXuatVien ctXuatBh);

    public     void remove(CtXuatBhXuatVien ctXuatBh);

    public     CtXuatBhXuatVien find(Object id);

    public     List<CtXuatBhXuatVien> findAll();

    public java.util.List<CtXuatBhXuatVien> findByPhieuxuatBhXuatVienMa(String phieuxuatBhMa);

}


