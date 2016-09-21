/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;


import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtBenhAnNgoaiTruInterface {

   void create(CtBenhAnNgoaiTru ctBenhAnNgoaiTru);

    void edit(CtBenhAnNgoaiTru ctBenhAnNgoaiTru);

    void remove(CtBenhAnNgoaiTru ctBenhAnNgoaiTru);

    CtBenhAnNgoaiTru find(Object id);

    List<CtBenhAnNgoaiTru> findAll();
    
    public List<CtBenhAnNgoaiTru> findByBANTMa(String bantMa);
}


