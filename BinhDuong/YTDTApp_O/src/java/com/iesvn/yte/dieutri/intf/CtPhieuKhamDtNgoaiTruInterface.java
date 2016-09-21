/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;


import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import java.util.List;


/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtPhieuKhamDtNgoaiTruInterface {

   void create(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru);

    void edit(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru);

    void remove(CtPhieuKhamDtNgoaiTru ctPhieuKhamDtNgoaiTru);

    CtPhieuKhamDtNgoaiTru find(Object id);

    List<CtPhieuKhamDtNgoaiTru> findAll();
    
    public List<CtPhieuKhamDtNgoaiTru> findByPKDTNTMa(String pkdtntMa);
}


