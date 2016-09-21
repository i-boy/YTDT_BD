/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BaNgoaiTruYhct;
import java.util.List;

/**
 *
 * @author i-boy
 */
public interface BaNgoaiTruYhctInterface {

    void create(BaNgoaiTruYhct baNgoaiTruYhct);

    void edit(BaNgoaiTruYhct baNgoaiTruYhct);

    void remove(BaNgoaiTruYhct baNgoaiTruYhct);

    BaNgoaiTruYhct find(Object id);

    List<BaNgoaiTruYhct> findAll();

    public BaNgoaiTruYhct getBANgoaiTruYhct(Integer thamkham);

    public String capNhatBenhAnNgoaiTruYhct(BaNgoaiTruYhct obj, String sMaPhieu);

    public BaNgoaiTruYhct findBySotheBHAndMaBNAndBanKhamYhct(String sotheBH, String maBN, Integer bankham);

}
