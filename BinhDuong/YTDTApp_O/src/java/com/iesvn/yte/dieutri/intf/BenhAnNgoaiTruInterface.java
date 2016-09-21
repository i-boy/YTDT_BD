/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface BenhAnNgoaiTruInterface {

    void create(BenhAnNgoaiTru benhAnNgoaiTru);

    void edit(BenhAnNgoaiTru benhAnNgoaiTru);

    void remove(BenhAnNgoaiTru benhAnNgoaiTru);

    BenhAnNgoaiTru find(Object id);

    List<BenhAnNgoaiTru> findAll();

    public BenhAnNgoaiTru getBANgoaiTru(Integer thamkham);

    public String capNhatBenhAnNgoaiTru(BenhAnNgoaiTru obj, String sMaPhieu, List<CtBenhAnNgoaiTru> lstCT);

    public BenhAnNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham);

}
