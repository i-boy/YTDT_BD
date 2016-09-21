/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
import java.util.Date;
import java.util.List;

/**
 *
 * @author root
 */
public interface PcCumThuPhiInterface {

    void create(PcCumThuPhi pcCumThuPhi);

    void edit(PcCumThuPhi pcCumThuPhi);

    void remove(PcCumThuPhi pcCumThuPhi);

    PcCumThuPhi find(Object id);

    List<PcCumThuPhi> findAll();
    
    public int ghinhan(List<PcCumThuPhi> list);
    
    public PcCumThuPhi findPcCumThuPhiByNgayAndNhanVien(Date date, String nvMa);
    
    public List<PcCumThuPhi> findPcCumThuPhi(Date tungay, Date denngay, String cum);

}
