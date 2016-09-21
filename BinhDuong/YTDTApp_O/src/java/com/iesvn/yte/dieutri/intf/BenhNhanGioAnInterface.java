/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface BenhNhanGioAnInterface {

    public void create(BenhNhanGioAn benhNhanGioAn);

    public void edit(BenhNhanGioAn benhNhanGioAn);

    public void remove(BenhNhanGioAn benhNhanGioAn);

    public BenhNhanGioAn find(Object id);

    public List<BenhNhanGioAn> findAll();
    
    public List<BenhNhanGioAn> findByBnPbaMaso(Integer bnpbaMaso) ;
}
