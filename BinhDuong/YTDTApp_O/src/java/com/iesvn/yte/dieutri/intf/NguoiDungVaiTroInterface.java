/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.NguoiDungVaiTro;
import java.util.List;

/**
 *
 * @author Thanh
 */
public interface NguoiDungVaiTroInterface {
void create(NguoiDungVaiTro nguoiDungVaiTro);

    void edit(NguoiDungVaiTro nguoiDungVaiTro);

    void remove(NguoiDungVaiTro nguoiDungVaiTro);

    public NguoiDungVaiTro find(Object id);
    
    public  NguoiDungVaiTro findByMaSoNguoiDungAndMaSoVaiTro( Integer nguoiDungMaSo, Integer nguoiDungVaiTro);

    List<NguoiDungVaiTro> findAll();
     public List<NguoiDungVaiTro> findByNguoiDung(Integer nguoiDungMaSo);
}
