/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.GiayCvNbBhyt;
import java.util.List;

/**
 *
 * @author james
 */
public interface GiayCvNbBhytInterface {

    public void create(GiayCvNbBhyt giayCvNbBhyt);

    public void edit(GiayCvNbBhyt giayCvNbBhyt);

    public void remove(GiayCvNbBhyt giayCvNbBhyt);

    public GiayCvNbBhyt find(Object id);

    public List<GiayCvNbBhyt> findAll();

    public List<GiayCvNbBhyt> findByGiayCvNbBhyt(String maPhieu);

    public GiayCvNbBhyt findByMaThamKham(Integer maThamKham);

    public String capNhatGiayCvNbBhyt(GiayCvNbBhyt obj, String sMaPhieu);
}
