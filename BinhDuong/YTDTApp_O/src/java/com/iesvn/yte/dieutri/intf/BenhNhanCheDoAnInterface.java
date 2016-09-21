/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface BenhNhanCheDoAnInterface {

    public void create(BenhNhanCheDoAn benhNhanCheDoAn);

    public void edit(BenhNhanCheDoAn benhNhanCheDoAn);

    public void remove(BenhNhanCheDoAn benhNhanCheDoAn);

    public BenhNhanCheDoAn find(Object id);

    public List<BenhNhanCheDoAn> findAll();
    
    public List<BenhNhanCheDoAn> findByBnPbaMaso(Integer bnpbaMaso) ;
}
