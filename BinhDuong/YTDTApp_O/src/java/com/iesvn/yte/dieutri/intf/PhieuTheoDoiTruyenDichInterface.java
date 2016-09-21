/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuTheoDoiTruyenDichInterface {

    public void create(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich);

    public void edit(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich);

    public void remove(PhieuTheoDoiTruyenDich phieuTheoDoiTruyenDich);

    public PhieuTheoDoiTruyenDich find(Object id);

    public List<PhieuTheoDoiTruyenDich> findAll();
    
    public List<PhieuTheoDoiTruyenDich> findByHsba(String hsbaMaso) ;
    
    public PhieuTheoDoiTruyenDich createPhieuTheoDoiTruyenDich(PhieuTheoDoiTruyenDich ptdtd) ;
}


