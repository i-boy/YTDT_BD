/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmLoaiAnInterface {

    public void create(DtDmLoaiAn dtDmLoaiAn);

    public void edit(DtDmLoaiAn dtDmLoaiAn);

    public void remove(DtDmLoaiAn dtDmLoaiAn);

    public DtDmLoaiAn find(Object id);

    public List<DtDmLoaiAn> findAll();
}
