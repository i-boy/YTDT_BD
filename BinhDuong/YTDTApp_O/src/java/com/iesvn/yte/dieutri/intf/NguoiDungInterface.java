/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.NguoiDung;
import java.util.List;

/**
 *
 * @author ThanhDo
 */
public interface NguoiDungInterface {

    void create(NguoiDung nguoiDung);

    void edit(NguoiDung nguoiDung);

    void remove(NguoiDung nguoiDung);

    NguoiDung find(Object id);

    public NguoiDung findByMa(String maND);

    List<NguoiDung> findAll();
    
    public Boolean createNguoiDung(NguoiDung nd, DtDmNhanVien nv);
}
