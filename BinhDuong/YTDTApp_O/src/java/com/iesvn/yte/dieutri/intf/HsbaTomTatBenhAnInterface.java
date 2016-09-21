/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn;
import java.util.List;

/**
 *
 * @author quang
 */
public interface HsbaTomTatBenhAnInterface {
public     void create(HsbaTomTatBenhAn hsbaTomTatBenhAn);

public     void edit(HsbaTomTatBenhAn hsbaTomTatBenhAn);

public     void remove(HsbaTomTatBenhAn hsbaTomTatBenhAn);

public     HsbaTomTatBenhAn find(Object id);

public     List<HsbaTomTatBenhAn> findAll();

    public HsbaTomTatBenhAn findBySoVaoVien(java.lang.String soVaoVien);

    public String insert(HsbaTomTatBenhAn obj);
    public String update(HsbaTomTatBenhAn obj);
    public HsbaTomTatBenhAn findByHsbattbaMa(String ma);
}
