/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TuvongTruoc;
import java.util.Date;
import java.util.List;

/**
 *
 * @author quang
 */
public interface TuvongTruocInterface {

 void create(TuvongTruoc tuvongTruoc);

    void edit(TuvongTruoc tuvongTruoc);

    void remove(TuvongTruoc tuvongTruoc);

    TuvongTruoc find(Object id);

    List<TuvongTruoc> findAll();

    public TuvongTruoc getTuvongTruoc(Integer thamkham);
     //public String capNhatBenhAnNgoaiTru(BenhAnNgoaiTru obj, String sMaPhieu, List<CtBenhAnNgoaiTru> lstCT) ;
}

