/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TiepDon;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface BenhNhanInterface {

    public void create(BenhNhan benhNhan);

    public void edit(BenhNhan benhNhan);

    public void remove(BenhNhan benhNhan);

    public BenhNhan find(Object id);

    public List<BenhNhan> findAll();
    
    public Long getLanVao(String benhnhanMa); 
        
    public List<BenhNhan> findByHoTen(String hoTen);
    public TiepDon findBySoTheBHYT(String sothebhyt);
    public List<BenhNhan> findByMaBN(String mabenhnhan);
}


