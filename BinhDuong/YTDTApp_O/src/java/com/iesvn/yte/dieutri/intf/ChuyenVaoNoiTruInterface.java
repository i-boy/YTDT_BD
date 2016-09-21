/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
import java.util.List;

/**
 *
 * @author ies
 */
public interface ChuyenVaoNoiTruInterface {

    void create(ChuyenVaoNoiTru chuyenVaoNoiTru);

    void edit(ChuyenVaoNoiTru chuyenVaoNoiTru);

    void remove(ChuyenVaoNoiTru chuyenVaoNoiTru);

    ChuyenVaoNoiTru find(Object id);

    ChuyenVaoNoiTru findByMaTiepDon(String maTiepDon);

    List<ChuyenVaoNoiTru> findAll();

    List<ChuyenVaoNoiTru> findRange(int[] range);

    int count();
    
    ChuyenVaoNoiTru findBySoVaoVien(String sovaovien) ;

}
