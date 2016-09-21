/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtTraNhaCungCap;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface CtTraNhaCungCapInterface {

    void create(CtTraNhaCungCap ctTraNhaCungCap);

    void edit(CtTraNhaCungCap ctTraNhaCungCap);

    void remove(CtTraNhaCungCap ctTraNhaCungCap);

    CtTraNhaCungCap find(Object id);

    List<CtTraNhaCungCap> findAll();

    public List<CtTraNhaCungCap> findCtTraNhaCungCapByMaPhieu(String maPhieu);
}
