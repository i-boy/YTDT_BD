/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmLoaiAn2Interface {

    public void create(DtDmLoaiAn2 dtDmLoaiAn2);

    public void edit(DtDmLoaiAn2 dtDmLoaiAn2);

    public void remove(DtDmLoaiAn2 dtDmLoaiAn2);

    public DtDmLoaiAn2 find(Object id);

    public List<DtDmLoaiAn2> findAll();
     public List<DtDmLoaiAn2> findByLoai(int loaian);
}
